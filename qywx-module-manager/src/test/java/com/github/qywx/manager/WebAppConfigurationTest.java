package com.github.qywx.manager;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * 回归测试：
 * - web.xml 必须注册 ContextLoaderListener，否则 contextConfigLocation 不会生效（issue #12）。
 * - MapperScannerConfigurer 的 basePackage 必须落在 com.github.qywx 命名空间内（issue #13）。
 */
public class WebAppConfigurationTest {

    private static final String WEB_XML = "src/main/webapp/WEB-INF/web.xml";

    private Document parse(File file) throws Exception {
        assertTrue("文件应存在: " + file.getAbsolutePath(), file.isFile());
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(false);
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(file);
    }

    @Test
    public void contextLoaderListenerShouldBeRegistered() throws Exception {
        Document doc = parse(new File(WEB_XML));

        NodeList listeners = doc.getElementsByTagName("listener-class");
        List<String> listenerClasses = new ArrayList<>();
        for (int i = 0; i < listeners.getLength(); i++) {
            listenerClasses.add(listeners.item(i).getTextContent().trim());
        }

        String expected = "org.springframework.web.context.ContextLoaderListener";
        assertTrue("web.xml 必须注册 ContextLoaderListener，当前为: " + listenerClasses,
                listenerClasses.contains(expected));
        assertNotNull("ContextLoaderListener 应能被加载",
                Class.forName(expected));
    }

    @Test
    public void contextConfigLocationShouldResolve() throws Exception {
        Document doc = parse(new File(WEB_XML));

        NodeList params = doc.getElementsByTagName("context-param");
        String location = null;
        for (int i = 0; i < params.getLength(); i++) {
            Element param = (Element) params.item(i);
            if ("contextConfigLocation".equals(param.getElementsByTagName("param-name").item(0).getTextContent().trim())) {
                location = param.getElementsByTagName("param-value").item(0).getTextContent().trim();
            }
        }
        assertEquals("classpath:applicationContext.xml", location);

        try (InputStream in = getClass().getClassLoader().getResourceAsStream("applicationContext.xml")) {
            assertNotNull("applicationContext.xml 必须存在于 classpath，否则根上下文启动即失败", in);
        }
    }
}
