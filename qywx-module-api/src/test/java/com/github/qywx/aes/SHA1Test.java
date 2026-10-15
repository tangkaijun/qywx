package com.github.qywx.aes;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 回归测试（issue #18）：企业微信消息签名使用固定测试向量离线断言。
 */
public class SHA1Test {

    @Test
    public void getSHA1ShouldMatchReferenceVector() throws Exception {
        assertEquals("b8451388b67a9e50b10a6d79684a1656d7e6a2fe",
                SHA1.getSHA1("QDG6eK", "1409659589", "263014780", "abc"));
    }

    @Test
    public void getSHA1ShouldBeIndependentOfParameterOrder() throws Exception {
        assertEquals(SHA1.getSHA1("token", "123", "456", "encrypt"),
                SHA1.getSHA1("encrypt", "token", "456", "123"));
    }
}
