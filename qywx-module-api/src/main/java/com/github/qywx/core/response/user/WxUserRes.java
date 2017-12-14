package com.github.qywx.core.response.user;

import com.alibaba.fastjson.JSON;
import com.github.qywx.core.response.Response;

import java.util.List;
import java.util.Map;

/**
 * Created by kaijun on 2017/12/10.
 */
public class WxUserRes extends Response {

    //成员UserID。对应管理端的帐号，企业内必须唯一。不区分大小写，长度为1~64个字节
    private String userid;
    //成员名称。长度为1~64个字节
    private String name;
    //成员所属部门id列表,不超过20个
    private List<Integer> department;
    //部门内的排序值，默认为0，成员次序以创建时间从小到大排列。数量必须和department一致，数值越大排序越前面。有效的值范围是[0, 2^32)
    private List<Integer> order;
    //职位信息。长度为0~64个字节
    private String position;
    //手机号码。企业内必须唯一，mobile/email二者不能同时为空
    private String mobile;
    //性别。1表示男性，2表示女性
    private String gender;
    //邮箱。长度为0~64个字节。企业内必须唯一，mobile/email二者不能同时为空
    private String email;
    //上级字段，标识是否为上级。
    private Integer isleader;
    //成员头像的mediaid，通过多媒体接口上传图片获得的mediaid
    private String avatar;
    //座机。长度0-64个字节。
    private String telephone;
    //英文名。长度为1-64个字节。
    private String english_name;
    //扩展属性，第三方仅通讯录套件可获取
    private Extattr extattr;
    //激活状态: 1=已激活，2=已禁用，4=未激活。已激活代表已激活企业微信或已关注微信插件。未激活代表既未激活企业微信又未关注微信插件。
    private Integer status;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getDepartment() {
        return department;
    }

    public void setDepartment(List<Integer> department) {
        this.department = department;
    }

    public List<Integer> getOrder() {
        return order;
    }

    public void setOrder(List<Integer> order) {
        this.order = order;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIsleader() {
        return isleader;
    }

    public void setIsleader(Integer isleader) {
        this.isleader = isleader;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEnglish_name() {
        return english_name;
    }

    public void setEnglish_name(String english_name) {
        this.english_name = english_name;
    }

    public Extattr getExtattr() {
        return extattr;
    }

    public void setExtattr(Extattr extattr) {
        this.extattr = extattr;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    static class Extattr{

        private List<Map<String,String>> attrs;

        public List<Map<String,String>> getAttrs() {
            return attrs;
        }

        public void setAttrs(List<Map<String,String>> attrs) {
            this.attrs = attrs;
        }
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
