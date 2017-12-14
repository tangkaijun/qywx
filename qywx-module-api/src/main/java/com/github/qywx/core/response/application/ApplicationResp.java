package com.github.qywx.core.response.application;

import com.alibaba.fastjson.JSON;
import com.github.qywx.core.request.User;
import com.github.qywx.core.response.Response;

import java.util.List;

/**
 * Created by kjtang on 2017/12/11.
 */
public class ApplicationResp extends Response{

    private Integer agentid;
    private String name;
    private String square_logo_url;
    private String description;
    private Allow_UserInfo allow_userinfos;
    private Allow_Party allow_partys;
    private Integer close;
    private String redirect_domain;
    private Integer report_location_flag;
    private Integer isreportenter;
    private String home_url;

    public Integer getAgentid() {
        return agentid;
    }

    public void setAgentid(Integer agentid) {
        this.agentid = agentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSquare_logo_url() {
        return square_logo_url;
    }

    public void setSquare_logo_url(String square_logo_url) {
        this.square_logo_url = square_logo_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Allow_UserInfo getAllow_userinfos() {
        return allow_userinfos;
    }

    public void setAllow_userinfos(Allow_UserInfo allow_userinfos) {
        this.allow_userinfos = allow_userinfos;
    }

    public Allow_Party getAllow_partys() {
        return allow_partys;
    }

    public void setAllow_partys(Allow_Party allow_partys) {
        this.allow_partys = allow_partys;
    }

    public Integer getClose() {
        return close;
    }

    public void setClose(Integer close) {
        this.close = close;
    }

    public String getRedirect_domain() {
        return redirect_domain;
    }

    public void setRedirect_domain(String redirect_domain) {
        this.redirect_domain = redirect_domain;
    }

    public Integer getReport_location_flag() {
        return report_location_flag;
    }

    public void setReport_location_flag(Integer report_location_flag) {
        this.report_location_flag = report_location_flag;
    }

    public Integer getIsreportenter() {
        return isreportenter;
    }

    public void setIsreportenter(Integer isreportenter) {
        this.isreportenter = isreportenter;
    }

    public String getHome_url() {
        return home_url;
    }

    public void setHome_url(String home_url) {
        this.home_url = home_url;
    }

    static class Allow_UserInfo{

        private List<User> user;

        public List<User> getUser() {
            return user;
        }

        public void setUser(List<User> user) {
            this.user = user;
        }

    }

    static class Allow_Party{

        private List<Integer> partyid;

        public List<Integer> getPartyid() {
            return partyid;
        }

        public void setPartyid(List<Integer> partyid) {
            this.partyid = partyid;
        }

    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
