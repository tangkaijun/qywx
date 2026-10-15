package com.github.qywx.api;

import com.github.qywx.core.request.Department;
import com.github.qywx.core.request.Tag;
import com.github.qywx.exception.RCodeException;
import com.github.qywx.utils.httpclient.HttpClientUtils;
import com.github.qywx.utils.httpclient.HttpResult;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * 回归测试（issue #14）：企业微信返回的报文缺少 errcode / tagid 时，
 * 不能因自动拆箱抛 NPE，而应抛出带明确错误码的 RCodeException。
 */
public class WxApiErrcodeNullSafetyTest {

    private static MockedStatic<HttpClientUtils> stubPost(String body) {
        MockedStatic<HttpClientUtils> mocked = Mockito.mockStatic(HttpClientUtils.class);
        mocked.when(() -> HttpClientUtils.doPost(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(new HttpResult(200, body));
        return mocked;
    }

    private static int errorCodeOf(Runnable action) {
        try {
            action.run();
        } catch (RCodeException e) {
            return e.getCode();
        }
        fail("缺少 errcode 的响应应抛出 RCodeException，而不是静默返回");
        return Integer.MIN_VALUE;
    }

    @Test
    public void createTagWithoutErrcodeShouldNotThrowNpe() {
        try (MockedStatic<HttpClientUtils> mocked = stubPost("{\"errmsg\":\"invalid credential\"}")) {
            assertEquals(-1, errorCodeOf(() -> WxTagAPI.createTag(new Tag(), "TOKEN")));
        }
    }

    @Test
    public void createTagWithErrcodeShouldPropagateCode() {
        try (MockedStatic<HttpClientUtils> mocked = stubPost("{\"errcode\":40001,\"errmsg\":\"invalid credential\"}")) {
            assertEquals(40001, errorCodeOf(() -> WxTagAPI.createTag(new Tag(), "TOKEN")));
        }
    }

    @Test
    public void createTagSuccessShouldReturnTagId() {
        try (MockedStatic<HttpClientUtils> mocked = stubPost("{\"errcode\":0,\"errmsg\":\"ok\",\"tagid\":12}")) {
            assertEquals(Integer.valueOf(12), WxTagAPI.createTag(new Tag(), "TOKEN"));
        }
    }

    @Test
    public void createDepartmentWithoutErrcodeShouldNotThrowNpe() {
        try (MockedStatic<HttpClientUtils> mocked = stubPost("{}")) {
            assertEquals(-1, errorCodeOf(() -> WxDepartmentAPI.createDepartment(new Department(), "TOKEN")));
        }
    }

    @Test
    public void updateTagWithoutErrcodeShouldNotThrowNpe() {
        try (MockedStatic<HttpClientUtils> mocked = stubPost("{}")) {
            assertEquals(-1, errorCodeOf(() -> WxTagAPI.updateTag(new Tag(), "TOKEN")));
        }
    }
}
