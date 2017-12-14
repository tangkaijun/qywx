package com.github.qywx.core.response.application;

import com.alibaba.fastjson.JSON;
import com.github.qywx.core.request.Application;
import com.github.qywx.core.response.Response;

import java.util.List;

/**
 * Created by kjtang on 2017/12/11.
 */
public class AppListResp extends Response{

    private List<Application> agentlist;

    public List<Application> getAgentlist() {
        return agentlist;
    }

    public void setAgentlist(List<Application> agentlist) {
        this.agentlist = agentlist;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
