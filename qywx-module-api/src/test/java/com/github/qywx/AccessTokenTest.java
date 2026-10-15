package com.github.qywx;
import com.github.qywx.core.constant.WxParam;

import com.github.qywx.api.AccessTokenAPI;
import com.github.qywx.core.response.AccessToken;
import org.junit.Test;

/**
 * Created by kaijun on 2017/12/10.
 */
public class AccessTokenTest {

    @Test
    public void testToken(){
        AccessToken accessToken = AccessTokenAPI.getAccessToken(WxParam.CORPID,System.getenv("WX_CORP_SECRET"));
        System.out.println(accessToken);
    }

}
