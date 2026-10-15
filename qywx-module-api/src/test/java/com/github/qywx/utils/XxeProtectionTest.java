package com.github.qywx.utils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ByteArrayInputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * 回归测试（issue #16）：XML 解析必须禁用 DTD / 外部实体，
 * 避免 XXE 读取本地文件或发起 SSRF。
 */
public class XxeProtectionTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void parseXmlShouldRejectDoctypeDeclaration() throws Exception {
        String xml = "<?xml version=\"1.0\"?>"
                + "<!DOCTYPE foo [<!ENTITY xxe SYSTEM \"file:///etc/passwd\">]>"
                + "<xml><Content>&xxe;</Content></xml>";
        try {
            MessageUtils.parseXml(xml);
            fail("带 DTD 的报文必须被拒绝");
        } catch (Exception expected) {
            // 期望：解析失败，而不是把本地文件内容读进结果
        }
    }

    @Test
    public void parseXmlShouldNotExpandExternalEntity() throws Exception {
        File secret = folder.newFile("secret.txt");
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(secret), StandardCharsets.UTF_8)) {
            writer.write("TOP-SECRET-CONTENT");
        }
        String xml = "<?xml version=\"1.0\"?>"
                + "<!DOCTYPE foo [<!ENTITY xxe SYSTEM \"" + secret.toURI() + "\">]>"
                + "<xml><Content>&xxe;</Content></xml>";
        try {
            Map<String, String> parsed = MessageUtils.parseXml(xml);
            assertNull("外部实体不得被展开", parsed.get("Content"));
        } catch (Exception expected) {
            // 期望：解析被拒绝
        }
    }

    @Test
    public void parseXmlFromStreamShouldRejectDoctypeDeclaration() throws Exception {
        String xml = "<?xml version=\"1.0\"?>"
                + "<!DOCTYPE foo [<!ENTITY xxe SYSTEM \"file:///etc/passwd\">]>"
                + "<xml><Content>&xxe;</Content></xml>";
        try {
            MessageUtils.parseXmlFromStream(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));
            fail("带 DTD 的报文必须被拒绝");
        } catch (Exception expected) {
            // 期望：解析失败
        }
    }

    @Test
    public void parseXmlShouldStillParseNormalMessage() throws Exception {
        String xml = "<xml><ToUserName><![CDATA[corp]]></ToUserName>"
                + "<MsgType><![CDATA[text]]></MsgType>"
                + "<Content><![CDATA[hello]]></Content></xml>";
        Map<String, String> parsed = MessageUtils.parseXml(xml);
        assertEquals("text", parsed.get("MsgType"));
        assertEquals("hello", parsed.get("Content"));
    }
}
