package com.github.qywx;

import com.github.qywx.api.AccessTokenAPI;
import com.github.qywx.api.WxMessageAPI;
import com.github.qywx.core.request.message.TextMessage;
import com.github.qywx.core.response.AccessToken;
import com.github.qywx.core.response.wxmessage.MessageRes;
import org.junit.Test;

/**
 * Created by kjtang on 2017/12/12.
 */
public class WxMessageAPITest {

    @Test
    public void testSendMessage() throws Exception{
        AccessToken accessToken = AccessTokenAPI.getAccessToken("wwc8cefdb9d53fd9f0","08IOCnadzFkypgz09nrmuQBMudmPT9PiNERaJimXXck");
        TextMessage textMessage = new TextMessage();
        textMessage.setSafe(0);
        textMessage.setAgentid(1000002);
        textMessage.setMsgtype("text");
        textMessage.setText(TextMessage.createText("这是我发送的信息................."));
        textMessage.setTouser("yyzq571856518|hbeslcyb");
        MessageRes messageRes = WxMessageAPI.sendMessage(textMessage,accessToken.getAccess_token());
        System.out.println(messageRes);
    }
}
