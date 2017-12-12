package com.github.qywx.core.request.message;

/**
 * Created by kjtang on 2017/12/12.
 * 视频类消息
 */
public class VideoMessage extends Message{

    private Video video;

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    static class Video{

         private String  media_id;
         private String  title;
         private String  description;

        public String getMedia_id() {
            return media_id;
        }

        public void setMedia_id(String media_id) {
            this.media_id = media_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    }
}
