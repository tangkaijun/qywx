package com.github.qywx.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.qywx.core.constant.WxAPI;
import com.github.qywx.core.constant.WxRCode;
import com.github.qywx.core.request.Application;
import com.github.qywx.core.response.Response;
import com.github.qywx.core.response.application.AppListResp;
import com.github.qywx.core.response.application.ApplicationResp;
import com.github.qywx.exception.RCodeException;
import com.github.qywx.utils.httpclient.HttpClientUtils;
import com.github.qywx.utils.httpclient.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kjtang on 2017/12/11.
 */
public class WxApplicationAPI {

    private static Logger logger = LoggerFactory.getLogger(WxApplicationAPI.class);

    /**
     * @param agentid     授权方应用id
     * @param accessToken 调用接口凭证
     * @return
     * @throws RCodeException
     */
    public static ApplicationResp getApplication(Integer agentid, String accessToken) throws RCodeException{
        ApplicationResp applicationRes = null;
        HttpResult httpResult  = HttpClientUtils.doGet(WxAPI.AGENT_GET_URL.replace("AGENTID",String.valueOf(agentid)).replace("ACCESS_TOKEN",accessToken));
        if(httpResult.getStatus()==200){
            JSONObject jo = JSON.parseObject(httpResult.getData());
            applicationRes = JSON.toJavaObject(jo,ApplicationResp.class);
            int rcode = applicationRes.getErrcode();
            if(rcode!=0){
                logger.info("获取应用失败", "getApplication params agentid:{},accessToken:{}, response:{}", new Object[]{agentid,accessToken,applicationRes});
                throw new RCodeException(rcode, WxRCode.getErrorMsg(rcode));
            }
            logger.info("获取应用成功", "getApplication params agentid:{},accessToken:{}, response:{}", new Object[]{agentid,accessToken,applicationRes});
        }
        return applicationRes;
    }

    /**
     * @param wxApp       应用实体信息
     * @param accessToken 调用接口凭证
     * @return            状态信息
     * @throws RCodeException
     */
    public static Response setApplication(Application wxApp, String accessToken) throws RCodeException{
        Response response = null;
        String json = JSON.toJSONString(wxApp);
        HttpResult httpResult = HttpClientUtils.doPost(WxAPI.AGENT_SET_URL.replace("ACCESS_TOKEN",accessToken),json);
        if(httpResult.getStatus()==200){
            JSONObject jo = JSON.parseObject(httpResult.getData());
            response = JSON.toJavaObject(jo,Response.class);
            int rcode = response.getErrcode();
            if(rcode!=0){
                logger.info("设置应用失败", "setApplication params wxApp:{},accessToken:{}, response:{}", new Object[]{wxApp,accessToken,response});
                throw new RCodeException(rcode, WxRCode.getErrorMsg(rcode));
            }
            logger.info("设置应用成功", "setApplication params wxApp:{},accessToken:{}, response:{}", new Object[]{wxApp,accessToken,response});
        }
        return response;
    }

    /**
     * @param accessToken  调用接口凭证
     * @return
     * @throws RCodeException
     */
    public static AppListResp getApplicationList(String accessToken) throws RCodeException{
        AppListResp appListRes = null;
        HttpResult httpResult = HttpClientUtils.doGet(WxAPI.AGNET_LIST_URL.replace("ACCESS_TOKEN",accessToken));
        if(httpResult.getStatus()==200){
            JSONObject jo =JSON.parseObject(httpResult.getData());
            appListRes = JSON.toJavaObject(jo,AppListResp.class);
            int rcode = appListRes.getErrcode();
            if(rcode!=0){
                logger.info("获取应用列表失败", "getApplicationList params accessToken:{}, response:{}", new Object[]{accessToken,appListRes});
                throw new RCodeException(rcode, WxRCode.getErrorMsg(rcode));
            }
            logger.info("获取应用列表成功", "getApplicationList params accessToken:{}, response:{}", new Object[]{accessToken,appListRes});
        }
        return appListRes;
    }

}
