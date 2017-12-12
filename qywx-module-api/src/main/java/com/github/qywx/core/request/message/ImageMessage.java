package com.github.qywx.core.request.message;

/**
 * Created by kjtang on 2017/12/12.
 * 图片类消息
 */
public class ImageMessage extends Message{

    private Integer safe;

    private Image image;

    public Integer getSafe() {
        return safe;
    }

    public void setSafe(Integer safe) {
        this.safe = safe;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    static class Image{

        private String media_id;

        public String getMedia_id() {
            return media_id;
        }

        public void setMedia_id(String media_id) {
            this.media_id = media_id;
        }
    }

}
