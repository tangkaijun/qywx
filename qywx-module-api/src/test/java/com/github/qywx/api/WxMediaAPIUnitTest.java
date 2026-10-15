package com.github.qywx.api;

import com.github.qywx.exception.RCodeException;
import com.github.qywx.utils.httpclient.HttpClientUtils;
import com.github.qywx.utils.httpclient.HttpResult;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * WxMediaAPI 成功/失败判定逻辑的回归测试。
 * 企业微信接口以业务错误码 errcode 表示结果，HTTP 200 不代表业务成功。
 */
public class WxMediaAPIUnitTest {

    @Test
    public void deleteMediaShouldSucceedWhenErrcodeIsZero() throws Exception {
        try (MockedStatic<HttpClientUtils> mocked = Mockito.mockStatic(HttpClientUtils.class)) {
            mocked.when(() -> HttpClientUtils.doGet(Mockito.anyString()))
                    .thenReturn(new HttpResult(200, "{\"errcode\":0,\"errmsg\":\"ok\"}"));
            assertEquals(Integer.valueOf(0), WxMediaAPI.deleteMedia("MEDIA_ID", "TOKEN").getErrcode());
        }
    }

    @Test
    public void deleteMediaShouldThrowWhenErrcodeIsNotZero() {
        try (MockedStatic<HttpClientUtils> mocked = Mockito.mockStatic(HttpClientUtils.class)) {
            mocked.when(() -> HttpClientUtils.doGet(Mockito.anyString()))
                    .thenReturn(new HttpResult(200, "{\"errcode\":40007,\"errmsg\":\"invalid media_id\"}"));
            try {
                WxMediaAPI.deleteMedia("MEDIA_ID", "TOKEN");
                fail("errcode 非 0 时应抛出 RCodeException");
            } catch (RCodeException e) {
                assertEquals(40007, e.getCode());
            } catch (Exception e) {
                fail("期望 RCodeException，实际: " + e);
            }
        }
    }

    @Test
    public void deleteMediaShouldNotThrowNpeWhenErrcodeMissing() {
        try (MockedStatic<HttpClientUtils> mocked = Mockito.mockStatic(HttpClientUtils.class)) {
            mocked.when(() -> HttpClientUtils.doGet(Mockito.anyString()))
                    .thenReturn(new HttpResult(200, "{\"errmsg\":\"unexpected\"}"));
            try {
                WxMediaAPI.deleteMedia("MEDIA_ID", "TOKEN");
                fail("errcode 缺失时应视为失败并抛出 RCodeException");
            } catch (RCodeException e) {
                assertEquals(-1, e.getCode());
            } catch (Exception e) {
                fail("期望 RCodeException，实际: " + e);
            }
        }
    }
}
