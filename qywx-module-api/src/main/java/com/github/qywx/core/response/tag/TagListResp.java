package com.github.qywx.core.response.tag;

import com.alibaba.fastjson.JSON;
import com.github.qywx.core.request.Tag;
import com.github.qywx.core.response.Response;

import java.util.List;

/**
 * Created by kjtang on 2017/12/14.
 */
public class TagListResp extends Response{

    private List<Tag> taglist;

    public List<Tag> getTaglist() {
        return taglist;
    }

    public void setTaglist(List<Tag> taglist) {
        this.taglist = taglist;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
