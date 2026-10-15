package com.github.qywx.utils.httpclient;

import com.sun.net.httpserver.HttpServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 回归测试（issue #17）：HttpClientUtils 必须正确释放响应、配置超时，
 * 并且不再发送非法的 Connection 请求头。
 */
public class HttpClientUtilsTest {

    private static HttpServer server;
    private static String baseUrl;
    private static final List<String> receivedConnectionHeaders =
            Collections.synchronizedList(new ArrayList<String>());

    @BeforeClass
    public static void startServer() throws Exception {
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/ok", exchange -> {
            receivedConnectionHeaders.add(String.valueOf(exchange.getRequestHeaders().getFirst("Connection")));
            byte[] body = "hello-qywx".getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(200, body.length);
            try (OutputStream out = exchange.getResponseBody()) {
                out.write(body);
            }
        });
        server.createContext("/error", exchange -> {
            byte[] body = "{\"errcode\":40001}".getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(500, body.length);
            try (OutputStream out = exchange.getResponseBody()) {
                out.write(body);
            }
        });
        server.start();
        baseUrl = "http://127.0.0.1:" + server.getAddress().getPort();
    }

    @AfterClass
    public static void stopServer() {
        if (server != null) {
            server.stop(0);
        }
    }

    @Test
    public void doGetShouldReturnStatusAndBody() {
        HttpResult result = HttpClientUtils.doGet(baseUrl + "/ok");
        assertEquals(Integer.valueOf(200), result.getStatus());
        assertEquals("hello-qywx", result.getData());
    }

    @Test
    public void doGetShouldPropagateErrorStatusAndBody() {
        HttpResult result = HttpClientUtils.doGet(baseUrl + "/error");
        assertEquals(Integer.valueOf(500), result.getStatus());
        assertEquals("{\"errcode\":40001}", result.getData());
    }

    @Test
    public void responsesShouldBeReleasedSoConnectionPoolIsReusable() {
        for (int i = 0; i < 8; i++) {
            assertEquals("第 " + (i + 1) + " 次请求应成功（响应未关闭会耗尽连接池）",
                    "hello-qywx", HttpClientUtils.doGet(baseUrl + "/ok").getData());
        }
    }

    @Test
    public void shouldNotSendIllegalConnectionHeader() {
        receivedConnectionHeaders.clear();
        HttpClientUtils.doGet(baseUrl + "/ok");
        assertFalse("不应再发送 Connection: Connection 这种非法头值",
                receivedConnectionHeaders.contains("Connection"));
    }

    @Test
    public void staticClientShouldBePrivateFinalAndConfiguredWithTimeouts() throws Exception {
        Field client = HttpClientUtils.class.getDeclaredField("httpClient");
        assertTrue("httpClient 应为 private static final，避免被外部替换",
                Modifier.isPrivate(client.getModifiers())
                        && Modifier.isStatic(client.getModifiers())
                        && Modifier.isFinal(client.getModifiers()));

        for (String name : new String[]{"CONNECT_TIMEOUT", "SOCKET_TIMEOUT", "CONNECTION_REQUEST_TIMEOUT"}) {
            Field field = HttpClientUtils.class.getDeclaredField(name);
            field.setAccessible(true);
            assertTrue(name + " 必须配置为正数，否则请求会无限等待",
                    ((Integer) field.get(null)) > 0);
        }
    }
}
