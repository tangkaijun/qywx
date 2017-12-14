package com.github.qywx.core.request.menu;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Created by kaijun on 2017/12/11.
 */
public class Menu {

    private List<Button> button;

    public List<Button> getButton() {
        return button;
    }

    public void setButton(List<Button> button) {
        this.button = button;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
