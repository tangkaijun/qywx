package com.github.qywx.manager;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * 回归测试（issue #19）：确保根 pom 中标记为存在已知漏洞的第三方组件已升级。
 * 版本号直接从 classpath 上解析到的 jar 文件名读取，验证的是 Maven 实际生效的版本。
 */
public class DependencyVersionTest {

    private static final String MYBATIS_CONFIG = "src/main/resources/applicationContext-mybatis.xml";

    private String versionOf(Class<?> type) throws Exception {
        assertNotNull("classpath 上应存在 " + type.getName(), type);
        File jar = new File(type.getProtectionDomain().getCodeSource().getLocation().toURI());
        Matcher m = Pattern.compile("-(\\d[\\w.]*)\\.jar$").matcher(jar.getName());
        assertTrue("无法从 jar 文件名解析版本: " + jar.getName(), m.find());
        return m.group(1);
    }

    private int compareVersion(String left, String right) {
        String[] a = left.split("[.]");
        String[] b = right.split("[.]");
        for (int i = 0; i < Math.max(a.length, b.length); i++) {
            int ai = i < a.length ? Integer.parseInt(a[i].replaceAll("[^0-9].*$", "")) : 0;
            int bi = i < b.length ? Integer.parseInt(b[i].replaceAll("[^0-9].*$", "")) : 0;
            if (ai != bi) {
                return Integer.compare(ai, bi);
            }
        }
        return 0;
    }

    private void assertAtLeast(String artifact, Class<?> type, String minVersion) throws Exception {
        String actual = versionOf(type);
        assertTrue(artifact + " 应升级到 >= " + minVersion + "，当前为 " + actual,
                compareVersion(actual, minVersion) >= 0);
    }

    @Test
    public void vulnerableDependenciesShouldBeUpgraded() throws Exception {
        assertAtLeast("fastjson", com.alibaba.fastjson.JSON.class, "1.2.83");
        assertAtLeast("dom4j", org.dom4j.Document.class, "2.1.4");
        assertAtLeast("xstream", com.thoughtworks.xstream.XStream.class, "1.4.21");
        assertAtLeast("log4j-core", org.apache.logging.log4j.core.Logger.class, "2.17.1");
    }

    @Test
    public void legacyLog4jOneApiShouldNotBeOnClasspath() {
        try {
            Class.forName("org.apache.log4j.Logger");
            fail("log4j 1.x（org.apache.log4j）不应出现在 classpath 上，应统一使用 log4j2");
        } catch (ClassNotFoundException expected) {
            // 预期：log4j 1.x 已移除
        }
    }

    @Test
    public void log4j2ConfigurationShouldBeOnClasspath() {
        assertNotNull("应存在 log4j2 配置 log4j2.xml",
                getClass().getClassLoader().getResourceAsStream("log4j2.xml"));
        assertTrue("旧的 log4j.properties 应被移除",
                getClass().getClassLoader().getResourceAsStream("log4j.properties") == null);
    }

    @Test
    public void druidFilterShouldUseLog4j2Alias() throws Exception {
        File file = new File(MYBATIS_CONFIG);
        assertTrue("文件应存在: " + file.getAbsolutePath(), file.isFile());
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);

        String filters = null;
        NodeList properties = doc.getElementsByTagName("property");
        for (int i = 0; i < properties.getLength(); i++) {
            Element property = (Element) properties.item(i);
            if ("filters".equals(property.getAttribute("name"))) {
                filters = property.getAttribute("value").trim();
            }
        }

        assertNotNull("数据源应配置 filters", filters);
        boolean usesLog4j2 = false;
        for (String filter : filters.split(",")) {
            String name = filter.trim();
            assertFalse("druid 日志过滤应使用 log4j2（依赖 org.apache.logging.log4j），"
                    + "不能使用已移除的 log4j 1.x 过滤: " + filters, "log4j".equals(name));
            usesLog4j2 |= "log4j2".equals(name);
        }
        assertTrue("druid filters 应包含 log4j2: " + filters, usesLog4j2);
    }
}
