package com.github.qywx;

import com.github.qywx.utils.httpclient.HttpClientUtils;
import com.github.qywx.utils.httpclient.HttpResult;
import org.junit.Test;

/**
 * Created by kaijun on 2017/12/10.
 */
public class HttpClientTest {

    @Test
    public void testHttpClient() throws Exception{
        HttpResult httpResult = HttpClientUtils.doGet("https://www.baidu.com");
        System.out.println(httpResult.getData());
    }

}
