package com.github.qywx.core.request.message.active;

/**
 * Created by kjtang on 2017/12/12.
 * 语音类消息
 */
public class VoiceMessage {

    private Voice voice;

    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }

    static class Voice{

         private String media_id;

        public String getMedia_id() {
            return media_id;
        }

        public void setMedia_id(String media_id) {
            this.media_id = media_id;
        }
    }
}
