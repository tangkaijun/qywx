package com.github.qywx.api;

import com.github.qywx.utils.httpclient.HttpClientUtils;
import com.github.qywx.utils.httpclient.HttpResult;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * WxUserAPI 请求地址拼装的回归测试。
 */
public class WxUserAPIUnitTest {

    @Test
    public void getUserByCodeShouldCallGetUserInfoWithCode() {
        ArgumentCaptor<String> url = ArgumentCaptor.forClass(String.class);
        try (MockedStatic<HttpClientUtils> mocked = Mockito.mockStatic(HttpClientUtils.class)) {
            mocked.when(() -> HttpClientUtils.doGet(Mockito.anyString()))
                    .thenReturn(new HttpResult(200, "{\"errcode\":0,\"errmsg\":\"ok\",\"UserId\":\"zhangsan\"}"));
            assertEquals("zhangsan", WxUserAPI.getUserByCode("CODE_VALUE", "TOKEN").getUserid());
            mocked.verify(() -> HttpClientUtils.doGet(url.capture()));
        }
        String requested = url.getValue();
        assertTrue("应按 code 调用 user/getuserinfo，实际: " + requested,
                requested.contains("/user/getuserinfo"));
        assertTrue("CODE 占位符应被替换，实际: " + requested, requested.contains("code=CODE_VALUE"));
        assertTrue("ACCESS_TOKEN 占位符应被替换，实际: " + requested, requested.contains("access_token=TOKEN"));
    }
}
