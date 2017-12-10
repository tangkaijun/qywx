package com.github.qywx.core.response;

/**
 * Created by kaijun on 2017/12/10.
 */
public class Response {

    //状态妈
    protected Integer errcode;
    //响应信息
    protected String errmsg;

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
