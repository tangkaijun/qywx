package com.github.qywx.utils.httpclient;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by kaijun on 2017/12/10.
 */
public class HttpClientUtils {

    public static CloseableHttpClient httpClient;

    static {
        httpClient=HttpClientBuilder.create().build();
    }

    public static HttpResult doGet(String url){
        // 创建http GET请求
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:54.0) Gecko/20100101 Firefox/54.0");
        httpGet.setHeader("Connection","Connection");
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpGet);
            return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity(), "UTF-8"));
        }catch (Exception e){
             throw new RuntimeException(e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                }catch (Exception e){
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static HttpResult doPost(String url, String json)  {
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);

        if (json != null) {
            // 构造一个form表单式的实体
            StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            // 将请求实体设置到httpPost对象中
            httpPost.setEntity(stringEntity);
        }

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity(), "UTF-8"));
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            if (response != null) {
                try{
                    response.close();
                }catch (Exception e){
                    throw new RuntimeException(e);
                }

            }
        }
    }

}
