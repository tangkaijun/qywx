package com.github.qywx;

import com.github.qywx.utils.httpclient.HttpClientUtils;
import com.github.qywx.utils.httpclient.HttpResult;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by kaijun on 2017/12/10.
 */
@Ignore("需要真实企业微信凭证与网络，默认跳过；参见 issue #18")
public class HttpClientTest {

    @Test
    public void testHttpClient() throws Exception{
        HttpResult httpResult = HttpClientUtils.doGet("https://www.baidu.com");
        System.out.println(httpResult.getData());
    }

}
