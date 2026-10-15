package com.github.qywx;

import org.junit.Test;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.security.KeyStore;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * 回归测试：确保不再存在「信任所有证书」的 TrustManager，TLS 校验保持开启。
 */
public class WxX509TrustManagerTest {

    @Test
    public void permissiveTrustManagerShouldBeRemoved() {
        try {
            Class.forName("com.github.qywx.core.ssl.WxX509TrustManager");
            fail("信任所有证书的 WxX509TrustManager 不应再存在");
        } catch (ClassNotFoundException expected) {
            // 期望：空实现的 TrustManager 已被删除
        }
    }

    @Test
    public void defaultTrustManagerShouldTrustRealCas() throws Exception {
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init((KeyStore) null);

        X509TrustManager trustManager = null;
        for (TrustManager tm : tmf.getTrustManagers()) {
            if (tm instanceof X509TrustManager) {
                trustManager = (X509TrustManager) tm;
            }
        }

        assertNotNull("JDK 默认应提供 X509TrustManager", trustManager);
        assertTrue("默认信任库应包含 CA 证书，而非空实现", trustManager.getAcceptedIssuers().length > 0);
    }
}
