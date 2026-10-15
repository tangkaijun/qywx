package com.github.qywx;
import com.github.qywx.core.constant.WxParam;

import com.github.qywx.api.AccessTokenAPI;
import com.github.qywx.core.response.AccessToken;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by kaijun on 2017/12/10.
 */
@Ignore("需要真实企业微信凭证与网络，默认跳过；参见 issue #18")
public class AccessTokenTest {

    @Test
    public void testToken(){
        AccessToken accessToken = AccessTokenAPI.getAccessToken(WxParam.CORPID,System.getenv("WX_CORP_SECRET"));
        System.out.println(accessToken);
    }

}
