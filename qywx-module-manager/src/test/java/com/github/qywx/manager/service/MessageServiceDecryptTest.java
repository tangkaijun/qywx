package com.github.qywx.manager.service;

import com.github.qywx.aes.AesException;
import com.github.qywx.aes.WXBizMsgCrypt;
import com.github.qywx.core.constant.WxParam;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * 回归测试：issue #11 —— 解密 / 验签失败必须抛出明确异常，不得静默返回 null。
 */
public class MessageServiceDecryptTest {

    private static final String TEST_TOKEN = "QDG6eK";
    private static final String TEST_AES_KEY = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFG";
    private static final String TEST_CORP_ID = "wx5823bf96d3bd56c7";

    static {
        // WxParam 在类初始化时读取配置，必须在它被加载之前注入测试凭证
        System.setProperty("wx.token", TEST_TOKEN);
        System.setProperty("wx.encodingAesKey", TEST_AES_KEY);
        System.setProperty("wx.corpid", TEST_CORP_ID);
    }

    private WXBizMsgCrypt crypt;

    @Before
    public void setUp() throws Exception {
        crypt = new WXBizMsgCrypt(WxParam.TOKEN, WxParam.ENCODING_AES_KEY, WxParam.CORPID);
    }

    private MockHttpServletRequest requestWithBody(String body) {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("msg_signature", "invalid-signature");
        request.setParameter("timestamp", "1234567890");
        request.setParameter("nonce", "nonce-value");
        request.setContent(body.getBytes(java.nio.charset.StandardCharsets.UTF_8));
        return request;
    }

    @Test
    public void signatureMismatchShouldThrowInsteadOfReturningNull() {
        String encrypted = "<xml><ToUserName><![CDATA[corp]]></ToUserName><Encrypt><![CDATA[abc]]></Encrypt></xml>";
        try {
            new MessageService().getDecryptMsg(requestWithBody(encrypted));
            fail("验签失败时应抛出 MessageProcessException");
        } catch (MessageProcessException e) {
            assertEquals(AesException.ValidateSignatureError,
                    ((AesException) e.getCause()).getCode());
        }
    }

    @Test
    public void getEncryptRespMessageShouldNotSwallowException() {
        String encrypted = "<xml><ToUserName><![CDATA[corp]]></ToUserName><Encrypt><![CDATA[abc]]></Encrypt></xml>";
        try {
            new MessageService().getEncryptRespMessage(requestWithBody(encrypted));
            fail("验签失败时应抛出 MessageProcessException，而不是返回 null");
        } catch (MessageProcessException e) {
            assertEquals(AesException.ValidateSignatureError,
                    ((AesException) e.getCause()).getCode());
        }
    }

    @Test
    public void validEncryptedMessageShouldBeDecrypted() throws Exception {
        String plain = "<xml><ToUserName><![CDATA[corp]]></ToUserName>"
                + "<FromUserName><![CDATA[zhangsan]]></FromUserName>"
                + "<CreateTime>1234567890</CreateTime>"
                + "<MsgType><![CDATA[text]]></MsgType>"
                + "<Content><![CDATA[hello]]></Content></xml>";
        // 企业微信推送的密文报文同样包含 ToUserName，解密时需要用它对密文验签
        String encrypted = crypt.EncryptMsg(plain, "1234567890", "nonce-value");
        String signature = extract(encrypted, "MsgSignature");
        String encrypt = extract(encrypted, "Encrypt");
        String postData = "<xml><ToUserName><![CDATA[" + TEST_CORP_ID + "]]></ToUserName>"
                + "<Encrypt><![CDATA[" + encrypt + "]]></Encrypt></xml>";

        MockHttpServletRequest request = requestWithBody(postData);
        request.setParameter("msg_signature", signature);

        String decrypted = new MessageService().getDecryptMsg(request);
        assertEquals(plain, decrypted);
    }

    private static String extract(String xml, String tag) throws UnsupportedEncodingException {
        int start = xml.indexOf("<" + tag + ">") + tag.length() + 2;
        int end = xml.indexOf("</" + tag + ">");
        return xml.substring(start, end).replace("<![CDATA[", "").replace("]]>", "");
    }
}
