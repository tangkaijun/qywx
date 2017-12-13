package com.github.qywx.utils.httpclient;

import org.apache.http.HttpEntity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by kjtang on 2017/12/13.
 * 文件下载响应
 */
public class HttpFileResult extends HttpResult{

    private HttpEntity entity;
    private String targetUrl;

    public HttpFileResult(Integer status, HttpEntity entity,String localUrl){
        this.setStatus(status);
        this.entity = entity;
        this.targetUrl = localUrl;
    }

    public  HttpFileResult doHandle(){
        try {
            if (entity != null) {
                InputStream is = entity.getContent();
                File file = new File(targetUrl);
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buffer = new byte[4096];
                int len = -1;
                while ((len = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                is.close();
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return this;
    }

}
