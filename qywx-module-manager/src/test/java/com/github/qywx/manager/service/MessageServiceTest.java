package com.github.qywx.manager.service;

import com.github.qywx.core.response.message.passive.TextMessage;
import com.github.qywx.utils.MessageUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * 回归测试：issue #6 —— 被动回复消息的 ToUserName / FromUserName 方向必须与
 * 企业微信推送的报文相反，否则回复无法路由到成员。
 */
public class MessageServiceTest {

    @Test
    public void replyShouldSwapToAndFromUserName() {
        Map<String, String> requestMap = new HashMap<String, String>();
        requestMap.put("FromUserName", "zhangsan");   // 推送方：成员账号
        requestMap.put("ToUserName", "ww1234567890"); // 推送方：企业 CorpID

        TextMessage reply = new MessageService().buildReplyTextMessage(requestMap, "您好");

        assertEquals("回复的接收方必须是成员账号", "zhangsan", reply.getToUserName());
        assertEquals("回复的发送方必须是企业应用 / CorpID", "ww1234567890", reply.getFromUserName());
        assertEquals(MessageUtils.RESP_MESSAGE_TYPE_TEXT, reply.getMsgType());
        assertEquals("您好", reply.getContent());
        assertNotNull(reply.getCreateTime());
    }
}
