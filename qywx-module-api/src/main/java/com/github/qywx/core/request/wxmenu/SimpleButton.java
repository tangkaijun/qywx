package com.github.qywx.core.request.wxmenu;

import com.alibaba.fastjson.JSON;

/**
 * Created by kaijun on 2017/12/11.
 * 简单按钮或普通按钮
 */
public class SimpleButton extends Button{

    //按钮类型
    private String type;
    //按钮key
    private String key;
    //按钮的链接
    private String url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
