package com.github.qywx.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.qywx.core.constant.WxAPI;
import com.github.qywx.core.constant.WxRCode;
import com.github.qywx.core.request.message.Message;
import com.github.qywx.core.response.wxmessage.MessageRes;
import com.github.qywx.exception.RCodeException;
import com.github.qywx.utils.httpclient.HttpClientUtils;
import com.github.qywx.utils.httpclient.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
}
