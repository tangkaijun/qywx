package com.github.qywx.core.response.message;

import com.alibaba.fastjson.JSON;
import com.github.qywx.core.response.Response;

/**
 * Created by kjtang on 2017/12/12.
 */
public class MessageRes extends Response{

    //不可接收的用户
    private String invaliduser;
    //不可到的部分
    private String invalidparty;
    private String invalidtag;

    public String getInvaliduser() {
        return invaliduser;
    }

    public void setInvaliduser(String invaliduser) {
        this.invaliduser = invaliduser;
    }

    public String getInvalidparty() {
        return invalidparty;
    }

    public void setInvalidparty(String invalidparty) {
        this.invalidparty = invalidparty;
    }

    public String getInvalidtag() {
        return invalidtag;
    }

    public void setInvalidtag(String invalidtag) {
        this.invalidtag = invalidtag;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
