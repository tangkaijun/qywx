package com.github.qywx;

import com.alibaba.fastjson.JSON;
import com.github.qywx.api.AccessTokenAPI;
import com.github.qywx.api.WxApplicationAPI;
import com.github.qywx.core.request.WxApp;
import com.github.qywx.core.response.AccessToken;
import com.github.qywx.core.response.wxapp.ApplicationRes;
import org.junit.Test;

/**
 * Created by kjtang on 2017/12/11.
 */
public class WxApplicationTest {

    @Test
    public void testGetApplication() throws Exception{
        AccessToken accessToken = AccessTokenAPI.getAccessToken("wwc8cefdb9d53fd9f0","08IOCnadzFkypgz09nrmuQBMudmPT9PiNERaJimXXck");
        ApplicationRes applicationRes=WxApplicationAPI.getApplication(1000002,accessToken.getAccess_token());
        System.out.println(JSON.toJSONString(applicationRes));
    }

    @Test
    public void testSetApplication() throws Exception{
        AccessToken accessToken = AccessTokenAPI.getAccessToken("wwc8cefdb9d53fd9f0","08IOCnadzFkypgz09nrmuQBMudmPT9PiNERaJimXXck");
        WxApp wxApp = new WxApp();
        wxApp.setAgentid(1000002);
        wxApp.setName("修改了名字的应用");
        WxApplicationAPI.setApplication(wxApp,accessToken.getAccess_token());
    }
}
