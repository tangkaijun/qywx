package com.github.qywx.manager;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * 回归测试：MyBatis Mapper 扫描包必须与项目包名一致，且资源中不得残留旧项目包名（issue #13）。
 */
public class MapperScanPackageTest {

    private static final String MYBATIS_CONTEXT = "src/main/resources/applicationContext-mybatis.xml";
    private static final String LEGACY_PACKAGE = "net." + "oschina.kjtang";

    private Document parse(File file) throws Exception {
        assertTrue("文件应存在: " + file.getAbsolutePath(), file.isFile());
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(false);
        return factory.newDocumentBuilder().parse(file);
    }

    @Test
    public void mapperScanPackageShouldMatchProjectNamespace() throws Exception {
        Document doc = parse(new File(MYBATIS_CONTEXT));

        NodeList properties = doc.getElementsByTagName("property");
        String basePackage = null;
        for (int i = 0; i < properties.getLength(); i++) {
            Element property = (Element) properties.item(i);
            if ("basePackage".equals(property.getAttribute("name"))) {
                basePackage = property.getAttribute("value");
            }
        }

        assertNotNull("MapperScannerConfigurer 必须配置 basePackage", basePackage);
        assertEquals("com.github.qywx.manager.dao", basePackage);
        assertTrue("basePackage 必须位于项目命名空间下", basePackage.startsWith("com.github.qywx"));
    }

    @Test
    public void resourcesShouldNotContainLegacyPackageName() throws Exception {
        List<String> offenders = new ArrayList<>();
        collectOffenders(new File("src/main/resources"), offenders);
        collectOffenders(new File("src/main/webapp/WEB-INF"), offenders);
        assertTrue("不应残留旧项目包名 " + LEGACY_PACKAGE + ": " + offenders, offenders.isEmpty());
    }

    private void collectOffenders(File dir, List<String> offenders) throws Exception {
        if (!dir.isDirectory()) {
            return;
        }
        File[] children = dir.listFiles();
        if (children == null) {
            return;
        }
        for (File child : children) {
            if (child.isDirectory()) {
                collectOffenders(child, offenders);
            } else if (child.getName().endsWith(".xml")) {
                String content = new String(Files.readAllBytes(child.toPath()), StandardCharsets.UTF_8);
                if (content.contains(LEGACY_PACKAGE)) {
                    offenders.add(child.getPath());
                }
            }
        }
    }
}
