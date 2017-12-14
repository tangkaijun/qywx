package com.github.qywx;

import com.github.qywx.api.AccessTokenAPI;
import com.github.qywx.api.WxMessageAPI;
import com.github.qywx.core.request.message.TextMessage;
import com.github.qywx.core.response.AccessToken;
import com.github.qywx.core.response.message.MessageRes;
import com.github.qywx.utils.httpclient.HttpClientUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by kjtang on 2017/12/12.
 */
public class WxMessageAPITest {

    private AccessToken accessToken;

    @Before
    public void beforeMethod(){
         accessToken = AccessTokenAPI.getAccessToken("wwc8cefdb9d53fd9f0","08IOCnadzFkypgz09nrmuQBMudmPT9PiNERaJimXXck");
    }

    /**发送普通文本消息*/
    @Test
    public void testSendMessage() throws Exception{
        TextMessage textMessage = new TextMessage();
        textMessage.setSafe(0);
        textMessage.setAgentid(1000002);
        textMessage.setMsgtype("text");
        textMessage.setText(TextMessage.createText("你的快递已到，请携带工卡前往邮件中心领取。\\n出发前可查看<a href=\\\"http://work.weixin.qq.com\\\">邮件中心视频实况</a>，聪明避开排队。"));
        textMessage.setTouser("khtang");
        MessageRes messageRes = WxMessageAPI.sendMessage(textMessage,accessToken.getAccess_token());
        System.out.println(messageRes);
    }

    @Test
    public void testGetServerIPS() throws Exception{
        List<String> ips = WxMessageAPI.getWxServerIps(accessToken.getAccess_token());
        System.out.println(ips);
    }

}
