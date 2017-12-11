package com.github.qywx.core.request.wxmenu;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Created by kaijun on 2017/12/11.
 */
public class ComplexButton extends Button {

    private List<Button> sub_button;

    public List<Button> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<Button> sub_button) {
        this.sub_button = sub_button;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
