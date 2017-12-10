package com.github.qywx.core.response;

/**
 * Created by kaijun on 2017/12/10.
 */
public class AccessToken extends Response {

    //获取到的凭证,最长为512字节
    private String access_token;
    //凭证的有效时间（秒）
    private Integer expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "errcode=" + errcode +
                ", access_token='" + access_token + '\'' +
                ", errmsg='" + errmsg + '\'' +
                ", expires_in=" + expires_in +
                '}';
    }
}
