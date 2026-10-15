package com.github.qywx.api;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 回归测试：issue #5 —— getMenu / deleteMenu 的占位符必须被正确替换。
 */
public class WxMenuAPIUrlTest {

    @Test
    public void getMenuUrlShouldReplaceBothPlaceholders() {
        String url = WxMenuAPI.menuGetUrl(1000002, "TOKEN_ABC");
        assertTrue(url, url.contains("agentid=1000002"));
        assertTrue(url, url.contains("access_token=TOKEN_ABC"));
        assertFalse(url, url.contains("AGENTID"));
        assertFalse(url, url.contains("ACCESS_TOKEN"));
    }

    @Test
    public void deleteMenuUrlShouldReplaceBothPlaceholders() {
        String url = WxMenuAPI.menuDeleteUrl(1000002, "TOKEN_ABC");
        assertTrue(url, url.contains("agentid=1000002"));
        assertTrue(url, url.contains("access_token=TOKEN_ABC"));
        assertFalse(url, url.contains("AGENTID"));
        assertFalse(url, url.contains("ACCESS_TOKEN"));
    }
}
