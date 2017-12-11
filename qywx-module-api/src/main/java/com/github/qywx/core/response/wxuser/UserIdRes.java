package com.github.qywx.core.response.wxuser;

import com.alibaba.fastjson.JSON;
import com.github.qywx.core.response.Response;

/**
 * Created by kaijun on 2017/12/10.
 */
public class UserIdRes extends Response {

    //用户id
    private String userid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
