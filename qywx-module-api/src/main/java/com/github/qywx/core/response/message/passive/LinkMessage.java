package com.github.qywx.core.response.message.passive;

/**
 * Created by kjtang on 2017/12/15.
 */
public class LinkMessage extends Message {

    // 消息标题
    private String Title;
    // 消息描述
    private String Description;
    // 封面缩略图的url
    private String PicUrl;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

}
