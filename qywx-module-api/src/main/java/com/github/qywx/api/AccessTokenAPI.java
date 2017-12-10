package com.github.qywx.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.qywx.core.constant.WxAPI;
import com.github.qywx.core.constant.WxRCode;
import com.github.qywx.core.response.AccessToken;
import com.github.qywx.exception.RCodeException;
import com.github.qywx.utils.httpclient.HttpClientUtils;
import com.github.qywx.utils.httpclient.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kaijun on 2017/12/10.
 * 获取token
 */
public class AccessTokenAPI {

    private static Logger logger = LoggerFactory.getLogger(AccessTokenAPI.class);

    /**
     * @param corpid     企业ID
     * @param corpsecret 应用的凭证密钥
     * @return AccessToken 获取的token
     * @throws RCodeException
     */
    public static AccessToken getAccessToken(String corpid,String corpsecret) throws RCodeException{
           AccessToken accessToken = null;
            HttpResult httpResult = HttpClientUtils.doGet(WxAPI.TOKEN_GET_URL.replace("ID",corpid).replace("SECRECT",corpsecret));
            if(httpResult.getStatus()==200){
                JSONObject jsonObject = JSON.parseObject(httpResult.getData());
                accessToken = JSON.toJavaObject(jsonObject,AccessToken.class);
                int rcode = accessToken.getErrcode();
                if(rcode!=0){
                    logger.info("获取微信Token失败", "getAccessToken params corpid:{},corpsecret:{}, response:{}", new Object[]{corpid,corpsecret,accessToken});
                    throw new RCodeException(rcode, WxRCode.getErrorMsg(rcode));
                }
                logger.info("获取微信Token成功", "getAccessToken params corpid:{},corpsecret:{}, response:{}", new Object[]{corpid,corpsecret,accessToken});
            }
        return accessToken;
    }
}
