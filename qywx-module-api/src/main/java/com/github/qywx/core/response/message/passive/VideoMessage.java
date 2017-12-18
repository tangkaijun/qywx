package com.github.qywx.core.response.message.passive;

/**
 * Created by kjtang on 2017/12/14.
 * 视频类消息
 */
public class VideoMessage extends Message {

    // 视频
    private Media Video;

    public Media getVideo() {
        return Video;
    }

    public void setVideo(Media video) {
        Video = video;
    }
}
