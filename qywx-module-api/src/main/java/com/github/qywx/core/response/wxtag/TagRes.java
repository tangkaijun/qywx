package com.github.qywx.core.response.wxtag;

import com.alibaba.fastjson.JSON;
import com.github.qywx.core.request.WxUser;
import com.github.qywx.core.response.Response;

import java.util.List;

/**
 * Created by kjtang on 2017/12/11.
 */
public class TagRes extends Response{

    //标签名称
    private String tagname;
    //用户列表
    private List<WxUser> userlist;
    //标签的可见范围
    private List<Integer> partylist;

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }

    public List<WxUser> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<WxUser> userlist) {
        this.userlist = userlist;
    }

    public List<Integer> getPartylist() {
        return partylist;
    }

    public void setPartylist(List<Integer> partylist) {
        this.partylist = partylist;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
