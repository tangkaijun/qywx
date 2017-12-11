package com.github.qywx.core.request;

import com.alibaba.fastjson.JSON;

/**
 * Created by kjtang on 2017/12/11.
 */
public class WxApp {

    //企业应用的id
    private Integer agentid;
    //企业应用是否打开地理位置上报 0：不上报；1：进入会话上报；
    private Integer report_location_flag;
    //企业应用头像的mediaid，通过多媒体接口上传图片获得mediaid，上传后会自动裁剪成方形和圆形两个头像
    private String logo_mediaid;
    //企业应用名称
    private String name;
    //企业应用详情
    private String description;
    //企业应用可信域名。注意：域名需通过所有权校验，否则jssdk功能将受限，此时返回错误码85005
    private String redirect_domain;
    //是否上报用户进入应用事件。0：不接收；1：接收。
    private Integer isreportenter;
    //应用主页url。url必须以http或者https开头。
    private String home_url;
    //企业应用方形头像url
    private String square_logo_url;

    public Integer getAgentid() {
        return agentid;
    }

    public void setAgentid(Integer agentid) {
        this.agentid = agentid;
    }

    public Integer getReport_location_flag() {
        return report_location_flag;
    }

    public void setReport_location_flag(Integer report_location_flag) {
        this.report_location_flag = report_location_flag;
    }

    public String getLogo_mediaid() {
        return logo_mediaid;
    }

    public void setLogo_mediaid(String logo_mediaid) {
        this.logo_mediaid = logo_mediaid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRedirect_domain() {
        return redirect_domain;
    }

    public void setRedirect_domain(String redirect_domain) {
        this.redirect_domain = redirect_domain;
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

    public String getSquare_logo_url() {
        return square_logo_url;
    }

    public void setSquare_logo_url(String square_logo_url) {
        this.square_logo_url = square_logo_url;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
