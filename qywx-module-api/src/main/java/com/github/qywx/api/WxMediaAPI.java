package com.github.qywx.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.qywx.core.constant.WxAPI;
import com.github.qywx.core.constant.WxRCode;
import com.github.qywx.core.request.material.News;
import com.github.qywx.core.response.Response;
import com.github.qywx.core.response.media.MediaRes;
import com.github.qywx.exception.RCodeException;
import com.github.qywx.utils.httpclient.HttpClientUtils;
import com.github.qywx.utils.httpclient.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by kjtang on 2017/12/13.
 * 素材管理
 */
public class WxMediaAPI {

    private static Logger logger = LoggerFactory.getLogger(WxMediaAPI.class);

    /**
     * @param type        媒体文件类型，分别有图片（image）、语音（voice）、视频（video），普通文件（file）
     * @param accessToken 调用接口凭证
     * @return
     * @throws RCodeException
     */
    public static MediaRes mediaUpload(File file,String type, String accessToken) throws RCodeException{
        MediaRes mediaRes= null;
        HttpResult httpResult = HttpClientUtils.doUpload(file, WxAPI.MEDIA_UPLOAD_URL.replace("TYPE",type).replace("ACCESS_TOKEN",accessToken));
        if(httpResult.getStatus()==200){
            JSONObject jo = JSON.parseObject(httpResult.getData());
            mediaRes = JSON.toJavaObject(jo,MediaRes.class);
            int rcode = mediaRes.getErrcode();
            if(rcode!=0){
                logger.info("文件上传失败", "mediaUpload params file:{},type:{},accessToken:{}, response:{}", new Object[]{file,type,accessToken,mediaRes});
                throw new RCodeException(rcode, WxRCode.getErrorMsg(rcode));
            }
            logger.info("文件上传成功", "mediaUpload params file:{},type:{},accessToken:{}, response:{}", new Object[]{file,type,accessToken,mediaRes});
        }
        return mediaRes;
    }

    /**
     * @param media_id    媒体文件id
     * @param accessToken 调用接口的凭证
     * @return
     * @throws RCodeException
     */
    public static Response mediaDownload(String localUrl,String media_id, String accessToken) throws RCodeException{
        Response response = null;
        HttpResult httpResult = HttpClientUtils.doDownload(WxAPI.MEDIA_GET_URL.replace("MEDIA_ID",media_id).replace("ACCESS_TOKEN",accessToken),localUrl);
        if(httpResult.getStatus()!=200){
            JSONObject jo = JSON.parseObject(httpResult.getData());
            response = JSON.toJavaObject(jo,Response.class);
            logger.info("文件下载失败", "mediaDownload params localUrl:{},media_id:{},accessToken:{}, response:{}", new Object[]{localUrl,media_id,accessToken,response});
            throw new RCodeException(response.getErrcode(), WxRCode.getErrorMsg(response.getErrcode()));
        }
        logger.info("文件下载成功", "mediaDownload params localUrl:{},media_id:{},accessToken:{}", new Object[]{localUrl,media_id,accessToken});
        return response;
    }

    /**
     * 删除永久图文素材
     * @return
     * @throws RCodeException
     */
    public static MediaRes mpNewsUpload(News news, String accessToken) throws RCodeException{
        MediaRes mediaRes = null;
        String json = JSON.toJSONString(news);
        HttpResult httpResult = HttpClientUtils.doPost(WxAPI.MEDIA_UPLOAD_MPNEWS_URL.replace("ACCESS_TOKEN",accessToken),json);
        System.out.println(JSON.toJSONString(httpResult));
        if(httpResult.getStatus()!=200){
            JSONObject jo = JSON.parseObject(httpResult.getData());
            mediaRes = JSON.toJavaObject(jo,MediaRes.class);
            logger.info("删除永久图文素材失败", "mediaDownload params mpnews:{},accessToken:{}, response:{}", new Object[]{news,accessToken,mediaRes});
            throw new RCodeException(mediaRes.getErrcode(), WxRCode.getErrorMsg(mediaRes.getErrcode()));
        }
        logger.info("删除永久图文素材成功", "mediaDownload params mpnews:{},accessToken:{},response:{}", new Object[]{news,accessToken,mediaRes});
        return mediaRes;
    }

    /**
     * 永久素材
     * @param media_id
     * @param accessToken
     * @return
     * @throws Exception
     */
    public static Response deleteMedia(String media_id,String accessToken) throws Exception{
        Response response = null;
        HttpResult httpResult = HttpClientUtils.doGet(WxAPI.MEDIA_DELETE_URL.replace("ACCESS_TOKEN",accessToken));
        System.out.println(JSON.toJSONString(httpResult));
        JSONObject jo = JSON.parseObject(httpResult.getData());
        response = JSON.toJavaObject(jo,Response.class);
        if(httpResult.getStatus()==200){
            logger.info("删除永久图文素材失败", "deleteMedia params mpnews:{},accessToken:{}, response:{}", new Object[]{media_id,accessToken,response});
            throw new RCodeException(response.getErrcode(), WxRCode.getErrorMsg(response.getErrcode()));
        }
        logger.info("删除永久图文素材成功", "deleteMedia params mpnews:{},accessToken:{},response:{}", new Object[]{media_id,accessToken,response});
        return response;
    }

}
