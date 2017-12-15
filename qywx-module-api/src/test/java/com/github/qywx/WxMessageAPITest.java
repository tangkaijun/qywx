package com.github.qywx;

import com.github.qywx.api.AccessTokenAPI;
import com.github.qywx.api.WxMessageAPI;
import com.github.qywx.core.request.message.active.NewsMessage;
import com.github.qywx.core.request.message.active.TextCardMessage;
import com.github.qywx.core.request.message.active.TextMessage;
import com.github.qywx.core.response.AccessToken;
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
    public void testSendTextMessage() throws Exception{
        TextMessage textMessage = new TextMessage();
        textMessage.setSafe(0);
        textMessage.setAgentid(1000002);
        textMessage.setMsgtype("text");
        textMessage.setText(TextMessage.createText("你的快递已到，请携带工卡前往邮件中心领取。\\n出发前可查看<a href=\\\"http://work.weixin.qq.com\\\">邮件中心视频实况</a>，聪明避开排队。"));
        textMessage.setTouser("khtang");
        MessageRes messageRes = WxMessageAPI.sendMessage(textMessage,accessToken.getAccess_token());
        System.out.println(messageRes);
    }

    //发送贺卡
    @Test
    public void testTextCardMessage() throws Exception{
        TextCardMessage textCardMessage = new TextCardMessage();
        textCardMessage.setTextcard(TextCardMessage.createTextCard("代办事宜","http://www.baidu.com","<div class='gray'>2017年12月15日</div> <div class='normal'>恭喜你抽中iPhone 7一台，领奖码：182378327</div><div class='highlight'>"+"请于2017年12月18日前联系行政同事领取</div>",null));
        textCardMessage.setMsgtype("textcard");
        textCardMessage.setTouser("khtang");
        textCardMessage.setAgentid(1000002);
        WxMessageAPI.sendMessage(textCardMessage,accessToken.getAccess_token());
    }

    //发送图文类消息
    @Test
    public void testNewsMessage() throws Exception {
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setMsgtype("news");
        //newsMessage.setNews(NewsMessage.);
       // newsMessage.setNews();
       // newsMessage.setNews();
    }

    @Test
    public void testGetServerIPS() throws Exception{
        List<String> ips = WxMessageAPI.getWxServerIps(accessToken.getAccess_token());
        System.out.println(ips);
    }

}
