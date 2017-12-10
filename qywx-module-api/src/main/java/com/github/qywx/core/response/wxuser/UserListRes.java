package com.github.qywx.core.response.wxuser;

import com.github.qywx.core.response.Response;

import java.util.List;

/**
 * Created by kaijun on 2017/12/10.
 */
public class UserListRes extends Response {

    private List<WxUserRes> userlist;

    public List<WxUserRes> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<WxUserRes> userlist) {
        this.userlist = userlist;
    }

    @Override
    public String toString() {
        return "UserListRes{" +
                "errcode=" + errcode +
                ", userlist=" + userlist +
                ", errmsg='" + errmsg + '\'' +
                '}';
    }
}
