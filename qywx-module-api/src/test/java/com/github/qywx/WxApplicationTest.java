package com.github.qywx;

import com.alibaba.fastjson.JSON;
import com.github.qywx.api.AccessTokenAPI;
import com.github.qywx.api.WxApplicationAPI;
import com.github.qywx.core.request.Application;
import com.github.qywx.core.response.AccessToken;
import com.github.qywx.core.response.application.AppListResp;
import com.github.qywx.core.response.application.ApplicationResp;
import org.junit.Test;

/**
 * Created by kjtang on 2017/12/11.
 */
public class WxApplicationTest {

    /**
     * 获取指定应用
     * @throws Exception
     */
    @Test
    public void testGetApplication() throws Exception{
        AccessToken accessToken = AccessTokenAPI.getAccessToken("wwc8cefdb9d53fd9f0","08IOCnadzFkypgz09nrmuQBMudmPT9PiNERaJimXXck");
        ApplicationResp applicationRes=WxApplicationAPI.getApplication(1000002,accessToken.getAccess_token());
        System.out.println(JSON.toJSONString(applicationRes));
    }

    /***
     * 编辑应用
     * @throws Exception
     */
    @Test
    public void testSetApplication() throws Exception{
        AccessToken accessToken = AccessTokenAPI.getAccessToken("wwc8cefdb9d53fd9f0","08IOCnadzFkypgz09nrmuQBMudmPT9PiNERaJimXXck");
        Application wxApp = new Application();
        wxApp.setAgentid(1000002);
        wxApp.setName("修改了名字的应用");
        WxApplicationAPI.setApplication(wxApp,accessToken.getAccess_token());
    }

    /***
     * 企业仅可获取当前凭证对应的应用；第三方仅可获取被授权的应用。
     * @throws Exception
     */
    @Test
    public void testAppliactionList() throws Exception{
        AccessToken accessToken = AccessTokenAPI.getAccessToken("wwc8cefdb9d53fd9f0","08IOCnadzFkypgz09nrmuQBMudmPT9PiNERaJimXXck");
        AppListResp appListRes = WxApplicationAPI.getApplicationList(accessToken.getAccess_token());
        System.out.println(appListRes);
    }
}
