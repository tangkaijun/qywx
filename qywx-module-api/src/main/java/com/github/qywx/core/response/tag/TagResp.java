package com.github.qywx.core.response.tag;

import com.alibaba.fastjson.JSON;
import com.github.qywx.core.request.User;
import com.github.qywx.core.response.Response;

import java.util.List;

/**
 * Created by kjtang on 2017/12/11.
 */
public class TagResp extends Response{

    //标签名称
    private String tagname;
    //用户列表
    private List<User> userlist;
    //标签的可见范围
    private List<Integer> partylist;
    //非法的用户列表
    private String invalidlist;
    //非法的部门列表
    private List<Integer> invalidparty;

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }

    public List<User> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<User> userlist) {
        this.userlist = userlist;
    }

    public List<Integer> getPartylist() {
        return partylist;
    }

    public void setPartylist(List<Integer> partylist) {
        this.partylist = partylist;
    }

    public String getInvalidlist() {
        return invalidlist;
    }

    public void setInvalidlist(String invalidlist) {
        this.invalidlist = invalidlist;
    }

    public List<Integer> getInvalidparty() {
        return invalidparty;
    }

    public void setInvalidparty(List<Integer> invalidparty) {
        this.invalidparty = invalidparty;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
