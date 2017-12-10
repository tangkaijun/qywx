package com.github.qywx.core.response.wxuser;

import com.github.qywx.core.response.Response;

/**
 * Created by kaijun on 2017/12/10.
 */
public class OpenIdRes extends Response {

    private String openid;
    private String appid;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
}
