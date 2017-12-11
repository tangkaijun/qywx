package com.github.qywx.core.request.wxmenu;

import com.alibaba.fastjson.JSON;

/**
 * Created by kaijun on 2017/12/11.
 * 按钮基类
 */
public class Button {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
