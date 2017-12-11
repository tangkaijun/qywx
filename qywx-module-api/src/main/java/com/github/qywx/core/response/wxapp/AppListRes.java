package com.github.qywx.core.response.wxapp;

import com.alibaba.fastjson.JSON;
import com.github.qywx.core.request.WxApp;
import com.github.qywx.core.response.Response;

import java.util.List;

/**
 * Created by kjtang on 2017/12/11.
 */
public class AppListRes extends Response{

    private List<WxApp> agentlist;

    public List<WxApp> getAgentlist() {
        return agentlist;
    }

    public void setAgentlist(List<WxApp> agentlist) {
        this.agentlist = agentlist;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
