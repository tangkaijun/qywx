package com.github.qywx.core.request.message;

/**
 * Created by kjtang on 2017/12/14.
 * 语音类消息
 */
public class VoiceMessage extends Message {

    // 语音媒体文件id，可以调用获取媒体文件接口拉取数据
    private String MediaId;
    // 语音格式，如amr，speex等
    private String Format;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }

}
