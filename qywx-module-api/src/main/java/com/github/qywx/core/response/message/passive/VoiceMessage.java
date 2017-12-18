package com.github.qywx.core.response.message.passive;

/**
 * Created by kjtang on 2017/12/14.
 * 语音类消息
 */
public class VoiceMessage extends Message {

    // 语音
    private Media Voice;

    public Media getVoice() {
        return Voice;
    }

    public void setVoice(Media voice) {
        Voice = voice;
    }


}
