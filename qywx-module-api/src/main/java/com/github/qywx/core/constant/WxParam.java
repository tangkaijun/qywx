package com.github.qywx.core.constant;

/**
 * Created by kjtang on 2017/12/15.
 *
 * 企业微信凭证统一从系统属性 / 环境变量读取，禁止硬编码到源码。
 * 系统属性优先，其次环境变量，例如 -Dwx.corpid=... 或 WX_CORPID=...
 */
public final class WxParam {

    public static final String TOKEN = readConfig("wx.token", "WX_TOKEN");

    public static final String ENCODING_AES_KEY = readConfig("wx.encodingAesKey", "WX_ENCODING_AES_KEY");

    public static final String CORPID = readConfig("wx.corpid", "WX_CORPID");

    private WxParam() {
    }

    private static String readConfig(String propertyName, String envName) {
        String value = System.getProperty(propertyName);
        if (value == null || value.trim().isEmpty()) {
            value = System.getenv(envName);
        }
        return value == null ? "" : value.trim();
    }
}
