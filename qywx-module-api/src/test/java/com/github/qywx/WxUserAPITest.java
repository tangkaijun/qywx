package com.github.qywx;

import com.alibaba.fastjson.JSON;
import com.github.qywx.api.AccessTokenAPI;
import com.github.qywx.api.WxUserAPI;
import com.github.qywx.core.response.AccessToken;
import com.github.qywx.core.response.wxuser.WxUserRes;
import org.junit.Test;

/**
 * Created by kaijun on 2017/12/10.
 */
public class WxUserAPITest {

    @Test
    public void testCreateUser(){
        AccessToken accessToken = AccessTokenAPI.getAccessToken("wwc8cefdb9d53fd9f0","zOfT-aTOL5gTRdUWTs76VLHWzV-m43N82ut7qWakuPM");
        WxUserRes wxUserRes = WxUserAPI.getUser("yyzq571856518",accessToken.getAccess_token());
        System.out.println(JSON.toJSONString(wxUserRes));
    }

}
