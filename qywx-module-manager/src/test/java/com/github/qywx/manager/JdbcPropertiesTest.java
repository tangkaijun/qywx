package com.github.qywx.manager;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.PropertyPlaceholderHelper;

import java.io.InputStream;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * 回归测试：jdbc.properties 不再硬编码数据库账号口令，改为占位符 + 环境变量/系统属性注入。
 */
public class JdbcPropertiesTest {

    private Properties loadJdbcProperties() throws Exception {
        Properties props = new Properties();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("jdbc.properties")) {
            assertNotNull("jdbc.properties 应存在于 classpath", in);
            props.load(in);
        }
        return props;
    }

    @Test
    public void shouldNotContainHardcodedCredentials() throws Exception {
        Properties props = loadJdbcProperties();
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");
        assertNotNull(username);
        assertNotNull(password);
        assertTrue("jdbc.username 应为占位符而非明文 root", username.startsWith("${") && username.endsWith("}"));
        assertTrue("jdbc.password 应为占位符而非明文", password.startsWith("${") && password.endsWith("}"));
        assertTrue("jdbc.url 不应再指向 root 账号配置", props.getProperty("jdbc.url").startsWith("${"));
    }

    @Test
    public void shouldResolvePlaceholdersFromExternalSources() throws Exception {
        Properties props = loadJdbcProperties();
        PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}", ":", true);

        System.setProperty("JDBC_USERNAME", "app_user");
        try {
            // 模拟 Spring 的解析顺序：系统属性 / 环境变量 优先于文件内的默认值
            Properties external = new Properties();
            external.putAll(props);
            external.putAll(System.getenv());
            external.putAll(System.getProperties());

            String resolved = helper.replacePlaceholders(props.getProperty("jdbc.username"), external::getProperty);
            assertEquals("外部注入的账号应生效", "app_user", resolved);
        } finally {
            System.clearProperty("JDBC_USERNAME");
        }

        Properties fallbackSources = new Properties();
        fallbackSources.putAll(props);
        fallbackSources.putAll(System.getenv());
        String fallback = helper.replacePlaceholders(props.getProperty("jdbc.username"), fallbackSources::getProperty);
        assertEquals("未设置外部变量时应回落到默认值 qywx", "qywx", fallback);
    }
}
