package com.github.qywx.utils.httpclient;

import com.github.qywx.exception.RCodeException;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.File;

/**
 * Created by kaijun on 2017/12/10.
 */
public class HttpClientUtils {

    private static final int CONNECT_TIMEOUT = 5000;
    private static final int SOCKET_TIMEOUT = 10000;
    private static final int CONNECTION_REQUEST_TIMEOUT = 5000;

    private static final CloseableHttpClient httpClient;

    static {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(200);
        connectionManager.setDefaultMaxPerRoute(50);

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT)
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                .build();

        httpClient = HttpClientBuilder.create()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .build();
    }

    public static HttpResult executeRequest(CloseableHttpClient httpClient, HttpRequestBase http) {
        try (CloseableHttpResponse response = httpClient.execute(http)) {
            return new HttpResult(response.getStatusLine().getStatusCode(),
                    EntityUtils.toString(response.getEntity(), "UTF-8"));
        } catch (Exception e) {
            throw new RuntimeException(e);
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
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            if (response.getStatusLine().getStatusCode() == 200) {
                return new HttpFileResult(response.getStatusLine().getStatusCode(), response.getEntity(), localUrl).doHandle();
            }
            return new HttpResult(response.getStatusLine().getStatusCode(),
                    EntityUtils.toString(response.getEntity(), "UTF-8"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
