package com.github.qywx.core.response.wxuser;

import com.github.qywx.core.response.Response;

/**
 * Created by kaijun on 2017/12/10.
 */
public class UserIdRes extends Response {

    private String userid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
