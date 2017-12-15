package com.github.qywx.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.qywx.core.constant.WxAPI;
import com.github.qywx.core.constant.WxRCode;
import com.github.qywx.core.request.message.active.Message;
import com.github.qywx.core.response.message.passive.MessageRes;
import com.github.qywx.exception.RCodeException;
import com.github.qywx.utils.httpclient.HttpClientUtils;
import com.github.qywx.utils.httpclient.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kjtang on 2017/12/12.
 * 微信信息发送与接收
 */
public class WxMessageAPI {

    private static Logger logger = LoggerFactory.getLogger(WxMessageAPI.class);

    /**消息发送，可以支持多种类型消息发送
     * @param message    消息实体
     * @param accessToken 调用接口凭证
     * @return
     * @throws RCodeException
     */
    public static MessageRes sendMessage(Message message, String accessToken) throws RCodeException{
        MessageRes messageRes = null;
        String json = JSON.toJSONString(message);
        HttpResult httpResult = HttpClientUtils.doPost(WxAPI.MESSAGE_SEND_URL.replace("ACCESS_TOKEN",accessToken),json);
        if(httpResult.getStatus()==200){
            JSONObject jo = JSON.parseObject(httpResult.getData());
            messageRes = JSON.toJavaObject(jo,MessageRes.class);
            int rcode = messageRes.getErrcode();
            if(rcode!=0){
                logger.info("信息发送失败", "createMenu params message:{},accessToken:{}, response:{}", new Object[]{message,accessToken,messageRes});
                throw new RCodeException(rcode, WxRCode.getErrorMsg(rcode));
            }
            logger.info("信息发送成功", "createMenu params message:{},accessToken:{}, response:{}", new Object[]{message,accessToken,messageRes});
        }
        return messageRes;
    }

    /**
     * 获取企业微信服务器IP
     * @param accessToken
     * @return
     * @throws RCodeException
     */
    public static List<String> getWxServerIps(String accessToken) throws RCodeException{
        List<String> ips = new ArrayList<>();
        HttpResult httpResult = HttpClientUtils.doGet(WxAPI.MESSAGE_SER_IP_URL.replace("ACCESS_TOKEN",accessToken));
        if(httpResult.getStatus()==200){
            System.out.println(httpResult.getData());
            JSONObject jo = JSON.parseObject(httpResult.getData());
            Integer code = jo.getInteger("errcode");
            System.out.println(code);
            if(code!=0){
                logger.info("获取微信服务ip失败", "createMenu params accessToken:{}, response:{}", new Object[]{accessToken,ips});
                throw new RCodeException(code, WxRCode.getErrorMsg(code));
            }
            JSONArray jsonArray = jo.getJSONArray("ip_list");
            ips = jsonArray.toJavaList(String.class);
            logger.info("获取微信服务ip成功", "createMenu params accessToken:{}, response:{}", new Object[]{accessToken,ips});
        }
        return ips;
    }

}
