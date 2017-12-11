package com.github.qywx.core.request.message;

/**
 * Created by kaijun on 2017/12/11.
 * 文本信息
 */
public class TextMessage extends Message {

    private Text text;
    private Integer safe;

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public Integer getSafe() {
        return safe;
    }

    public void setSafe(Integer safe) {
        this.safe = safe;
    }

    static class Text {

        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
