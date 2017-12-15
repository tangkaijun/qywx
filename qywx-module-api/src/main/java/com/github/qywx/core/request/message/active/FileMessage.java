package com.github.qywx.core.request.message.active;

/**
 * Created by kjtang on 2017/12/12.
 */
public class FileMessage extends Message{

    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    static class File{

        private String media_id;

        public String getMedia_id() {
            return media_id;
        }

        public void setMedia_id(String media_id) {
            this.media_id = media_id;
        }
    }
}
