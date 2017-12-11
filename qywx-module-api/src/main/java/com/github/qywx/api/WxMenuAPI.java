package com.github.qywx.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.qywx.core.constant.WxAPI;
import com.github.qywx.core.constant.WxRCode;
import com.github.qywx.core.request.wxmenu.Menu;
import com.github.qywx.core.response.Response;
import com.github.qywx.exception.RCodeException;
import com.github.qywx.utils.httpclient.HttpClientUtils;
import com.github.qywx.utils.httpclient.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kaijun on 2017/12/11.
 * 微信菜单管理接口
 */
public class WxMenuAPI {

    private static Logger logger = LoggerFactory.getLogger(WxMenuAPI.class);

    public static Response createMenu(Menu menu, Integer agentid, String accessToken) throws RCodeException{
        Response response = null;
        String json = JSON.toJSONString(menu);
        HttpResult httpResult = HttpClientUtils.doPost(WxAPI.MENU_CREATE_URL.replace("AGENTID",String.valueOf(agentid)).replace("ACCESS_TOKEN",accessToken),json);
        if(httpResult.getStatus()==200){
            JSONObject jo = JSON.parseObject(httpResult.getData());
            response = JSON.toJavaObject(jo,Response.class);
            int rcode = response.getErrcode();
            if(rcode!=0){
                logger.info("创建菜单失败", "createMenu params menu:{}, agentid:{},accessToken:{}, response:{}", new Object[]{menu,agentid,accessToken,response});
                throw new RCodeException(rcode, WxRCode.getErrorMsg(rcode));
            }
            logger.info("创建菜单成功", "createMenu params menu:{}, agentid:{},accessToken:{}, response:{}", new Object[]{menu,agentid,accessToken,response});
        }
        return response;
    }

    /**
     * @param agentid     授权方应用id
     * @param accessToken 调用接口凭证
     * @return
     * @throws RCodeException
     */
    public static Response getMenu(Integer agentid,String accessToken) throws RCodeException{
        Response response = null;
        HttpResult httpResult = HttpClientUtils.doGet(WxAPI.MENU_GET_URL.replace("AGENTID",String.valueOf(agentid).replace("ACCESS_TOKEN",accessToken)));
        if(httpResult.getStatus()==200){
            JSONObject jo = JSON.parseObject(httpResult.getData());
            response = JSON.toJavaObject(jo,Response.class);
            int rcode = response.getErrcode();
            if(rcode!=0){
                logger.info("获取菜单失败", "getMenu params agentid:{},accessToken:{}, response:{}", new Object[]{agentid,accessToken,response});
                throw new RCodeException(rcode, WxRCode.getErrorMsg(rcode));
            }
            logger.info("获取菜单成功", "getMenu params agentid:{},accessToken:{}, response:{}", new Object[]{agentid,accessToken,response});
        }
        return response;
    }

    /**
     * @param agentid     授权方应用id
     * @param accessToken 调用接口凭证
     * @return
     * @throws RCodeException
     */
    public static Response deleteMenu(Integer agentid,String accessToken) throws RCodeException{
        Response response = null;
        HttpResult httpResult = HttpClientUtils.doGet(WxAPI.MENU_DELETE_URL.replace("AGENTID",String.valueOf(agentid).replace("ACCESS_TOKEN",accessToken)));
        if(httpResult.getStatus()==200){
            JSONObject jo = JSON.parseObject(httpResult.getData());
            response = JSON.toJavaObject(jo,Response.class);
            int rcode = response.getErrcode();
            if(rcode!=0){
                logger.info("删除菜单失败", "deleteMenu params agentid:{},accessToken:{}, response:{}", new Object[]{agentid,accessToken,response});
                throw new RCodeException(rcode, WxRCode.getErrorMsg(rcode));
            }
            logger.info("删除菜单成功", "deleteMenu params agentid:{},accessToken:{}, response:{}", new Object[]{agentid,accessToken,response});
        }
        return response;
    }

}
