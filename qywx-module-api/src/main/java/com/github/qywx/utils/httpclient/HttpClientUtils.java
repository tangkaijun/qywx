package com.github.qywx.utils.httpclient;

import com.github.qywx.exception.RCodeException;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by kaijun on 2017/12/10.
 */
public class HttpClientUtils {

    public static CloseableHttpClient httpClient;

    static {
        httpClient=HttpClientBuilder.create().build();
    }

    public static HttpResult executeRequest(CloseableHttpClient httpClient,HttpRequestBase http){
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(http);
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

    /**
     * @param url
     * @return
     */
    public static HttpResult doGet(String url){
        // 创建http GET请求
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:54.0) Gecko/20100101 Firefox/54.0");
        httpGet.setHeader("Connection","Connection");
        return executeRequest(httpClient,httpGet);
    }

    /**
     * @param url
     * @param json
     * @return
     */
    public static HttpResult doPost(String url, String json)  {
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);

        if (json != null) {
            // 构造一个form表单式的实体
            StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            // 将请求实体设置到httpPost对象中
            httpPost.setEntity(stringEntity);
        }
        return executeRequest(httpClient,httpPost);
    }

    /**
     * @param localFile
     * @param url
     * @return
     */
    public static HttpResult doUpload(File localFile,String url) {
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);
        //创建待处理的文件
        FileBody file = new FileBody(localFile);
        //普通文本域
       // StringBody comment = new StringBody("文件描述信息", ContentType.TEXT_PLAIN);
        HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("file", file).build();//addPart("comment", comment).
        httpPost.setEntity(reqEntity);
        return executeRequest(httpClient,httpPost);
    }

    /**
     * 文件下载
     * @return
     * @throws RCodeException
     */
    public static HttpResult doDownload(String url,String localUrl) throws RCodeException{
        // 创建http GET请求
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:54.0) Gecko/20100101 Firefox/54.0");
        httpGet.setHeader("Connection","Connection");
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpGet);
            if(response.getStatusLine().getStatusCode()==200) {
                return new HttpFileResult(response.getStatusLine().getStatusCode(), response.getEntity(), localUrl).doHandle();
            }else{
                return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity(), "UTF-8"));
            }
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

}
