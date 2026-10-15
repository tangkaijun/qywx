package com.github.qywx.aes;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * 回归测试（issue #18）：加解密、验签、corpid 校验均为纯逻辑，
 * 使用固定密钥离线断言，不依赖真实企业微信接口。
 */
public class WXBizMsgCryptTest {

    private static final String TOKEN = "QDG6eK";
    private static final String AES_KEY = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFG";
    private static final String CORP_ID = "wx5823bf96d3bd56c7";

    private WXBizMsgCrypt crypt;

    @Before
    public void setUp() throws Exception {
        crypt = new WXBizMsgCrypt(TOKEN, AES_KEY, CORP_ID);
    }

    @Test
    public void encryptThenDecryptShouldRoundTrip() throws Exception {
        String plain = "<xml><Content><![CDATA[hello 企业微信]]></Content></xml>";
        String encrypted = crypt.encrypt("0123456789abcdef", plain);
        assertEquals(plain, crypt.decrypt(encrypted));
    }

    @Test
    public void encryptMsgShouldProduceMatchingSignature() throws Exception {
        String xml = crypt.EncryptMsg("<xml>hi</xml>", "1234567890", "nonce-value");
        String signature = extract(xml, "MsgSignature");
        String encrypt = extract(xml, "Encrypt");

        assertEquals(SHA1.getSHA1(TOKEN, "1234567890", "nonce-value", encrypt), signature);
    }

    @Test
    public void decryptMsgShouldRejectTamperedSignature() throws Exception {
        String postData = "<xml><ToUserName><![CDATA[" + CORP_ID + "]]></ToUserName>"
                + "<Encrypt><![CDATA[abc]]></Encrypt></xml>";
        try {
            crypt.DecryptMsg("bad-signature", "1234567890", "nonce-value", postData);
            fail("签名不匹配时应抛出 ValidateSignatureError");
        } catch (AesException e) {
            assertEquals(AesException.ValidateSignatureError, e.getCode());
        }
    }

    @Test
    public void decryptShouldRejectMismatchedCorpId() throws Exception {
        WXBizMsgCrypt sender = new WXBizMsgCrypt(TOKEN, AES_KEY, "wx-other-corp");
        String encrypted = sender.encrypt("0123456789abcdef", "<xml>hi</xml>");

        try {
            crypt.decrypt(encrypted);
            fail("corpid 不一致时应抛出 ValidateCorpidError");
        } catch (AesException e) {
            assertEquals(AesException.ValidateCorpidError, e.getCode());
        }
    }

    @Test
    public void constructorShouldRejectIllegalAesKeyLength() {
        try {
            new WXBizMsgCrypt(TOKEN, "too-short", CORP_ID);
            fail("EncodingAESKey 长度不为 43 时应抛出 IllegalAesKey");
        } catch (AesException e) {
            assertEquals(AesException.IllegalAesKey, e.getCode());
        }
    }

    private static String extract(String xml, String tag) {
        int start = xml.indexOf("<" + tag + ">") + tag.length() + 2;
        int end = xml.indexOf("</" + tag + ">");
        return xml.substring(start, end).replace("<![CDATA[", "").replace("]]>", "");
    }
}
