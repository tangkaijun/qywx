package com.github.qywx.core.response.message.passive;

/**
 * Created by kjtang on 2017/12/14.
 * 图片消息
 */
public class ImageMessage extends Message {

    private Media Image;

    public Media getImage() {
        return Image;
    }

    public void setImage(Media image) {
        Image = image;
    }

}
