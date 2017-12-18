package com.github.qywx.core.response.message.passive;

/**
 * Created by kjtang on 2017/12/14.
 * 接收文本消息
 */
public class TextMessage extends Message {

    // 消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }


}
