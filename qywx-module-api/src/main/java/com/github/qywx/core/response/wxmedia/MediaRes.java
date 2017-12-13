package com.github.qywx.core.response.wxmedia;

import com.alibaba.fastjson.JSON;
import com.github.qywx.core.response.Response;

/**
 * Created by kjtang on 2017/12/13.
 */
public class MediaRes extends Response{

    //媒体文件类型，分别有图片（image）、语音（voice）、视频（video），普通文件(file)
    private String type;
    //媒体文件上传后获取的唯一标识，3天内有效
    private String media_id;
    //媒体文件上传时间戳
    private String created_at;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
