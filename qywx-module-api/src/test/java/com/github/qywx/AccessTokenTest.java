package com.github.qywx;

import com.github.qywx.api.AccessTokenAPI;
import com.github.qywx.core.response.AccessToken;
import org.junit.Test;

/**
 * Created by kaijun on 2017/12/10.
 */
public class AccessTokenTest {

    @Test
    public void testToken(){
        AccessToken accessToken = AccessTokenAPI.getAccessToken("wwc8cefdb9d53fd9f0","zOfT-aTOL5gTRdUWTs76VLHWzV-m43N82ut7qWakuPM");
        System.out.println(accessToken);
    }

}
