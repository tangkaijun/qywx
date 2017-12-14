package com.github.qywx.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.qywx.core.constant.WxAPI;
import com.github.qywx.core.constant.WxRCode;
import com.github.qywx.core.request.User;
import com.github.qywx.core.response.*;
import com.github.qywx.core.response.user.OpenIdRes;
import com.github.qywx.core.response.user.UserIdRes;
import com.github.qywx.core.response.user.UserListResp;
import com.github.qywx.core.response.user.WxUserRes;
import com.github.qywx.exception.RCodeException;
import com.github.qywx.utils.httpclient.HttpClientUtils;
import com.github.qywx.utils.httpclient.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by kaijun on 2017/12/10.
 *  Authro:tangkaijun
 *  微信成员管理API
 *  说明:系统应用须拥有指定部门的管理权限。注意，每个部门下的节点不能超过3万个。建议保证创建department对应的部门和创建成员是串行化处理。
 */
public class WxUserAPI {

    private static Logger logger = LoggerFactory.getLogger(WxUserAPI.class);

    /**
     *  wxUser:用户信息实体
     *  accessToken:访问token
     * */
    public static Response createUser(User wxUser, String accessToken) throws RCodeException{
           Response response = null;
            String wxUserJson = JSON.toJSONString(wxUser);
            HttpResult httpResult = HttpClientUtils.doPost(WxAPI.USER_CREATE_URL.replace("ACCESS_TOKEN", accessToken), wxUserJson);
            if (httpResult.getStatus() == 200) {
                JSONObject jsonObject = JSON.parseObject(httpResult.getData());
                response = JSON.toJavaObject(jsonObject,Response.class);
                int rcode = response.getErrcode();
                if(rcode!=0){
                    logger.info("创建成员失败", "createUser params wxUser:{},accessToken:{}  response:{}",new Object[]{wxUser,accessToken,response});
                    throw new RCodeException(rcode, WxRCode.getErrorMsg(rcode));
                }
            }
        logger.info("创建成员成功", "createUser params wxUser:{},accessToken:{}  response:{}",new Object[]{wxUser,accessToken,response});
        return response;
    }

    /**
     * @param userid      用户id
     * @param accessToken 访问Token
     * @return            查询的用户信息
     * @throws RCodeException
     */
    public static WxUserRes getUser(String userid, String accessToken) throws RCodeException{
        WxUserRes wxUserRes = null;
        HttpResult httpResult = HttpClientUtils.doGet(WxAPI.USER_GET_URL.replace("USERID",userid).replace("ACCESS_TOKEN",accessToken));
        if (httpResult.getStatus() == 200) {
            JSONObject jsonObject = JSON.parseObject(httpResult.getData());
            wxUserRes = JSON.toJavaObject(jsonObject,WxUserRes.class);
            int rcode = wxUserRes.getErrcode();
            if(rcode!=0){
                logger.info("获取用户失败", "getUser params userid:{},accessToken:{}  response:{}",new Object[]{userid,accessToken,wxUserRes});
                throw new RCodeException(rcode, WxRCode.getErrorMsg(rcode));
            }
            logger.info("获取用户成功", "getUser params userid:{},accessToken:{}  response:{}",new Object[]{userid,accessToken,wxUserRes});
        }
        return wxUserRes;
    }

    /**
     * @param wxUser       微信用户信息
     * @param accessToken  访问token
     * @return
     * @throws RCodeException
     */
    public static Response updateUser(User wxUser, String accessToken) throws RCodeException{
        Response response = null;
        String wxUserJson = JSON.toJSONString(wxUser);
        HttpResult httpResult = HttpClientUtils.doPost(WxAPI.USER_UPDATE_URL.replace("ACCESS_TOKEN", accessToken), wxUserJson);
        if (httpResult.getStatus() == 200) {
            JSONObject jsonObject = JSON.parseObject(httpResult.getData());
            response = JSON.toJavaObject(jsonObject,Response.class);
            int rcode = response.getErrcode();
            if(rcode!=0){
                logger.info("更新成员失败", "updateUser params wxUser:{},accessToken:{}  response:{}",new Object[]{wxUser,accessToken,response});
                throw new RCodeException(rcode, WxRCode.getErrorMsg(rcode));
            }
        }
        logger.info("更新成员成功", "updateUser params wxUser:{},accessToken:{}  response:{}",new Object[]{wxUser,accessToken,response});
        return response;
    }

    /**
     * @param userid       用户id
     * @param accessToken  访问 token
     * @return
     * @throws RCodeException
     */
    public static Response deleteUser(String userid,String accessToken) throws RCodeException{
        Response response = null;
        HttpResult httpResult = HttpClientUtils.doGet(WxAPI.USER_DELETE_URL.replace("ACCESS_TOKEN", accessToken).replace("USERID",userid));
        if (httpResult.getStatus() == 200) {
            JSONObject jsonObject = JSON.parseObject(httpResult.getData());
            response = JSON.toJavaObject(jsonObject,Response.class);
            int rcode = response.getErrcode();
            if(rcode!=0){
                logger.info("删除成员失败", "deleteUser params userid:{},accessToken:{}  response:{}",new Object[]{userid,accessToken,response});
                throw new RCodeException(rcode, WxRCode.getErrorMsg(rcode));
            }
        }
        logger.info("删除成员成功", "deleteUser params userid:{},accessToken:{}  response:{}",new Object[]{userid,accessToken,response});
        return response;
    }

    /**
     * @param userids   多个用户id集合
     * @return
     * @throws RCodeException
     */
    public static Response batchDeleteUser(List<String> userids,String accessToken) throws RCodeException{
        Response response = null;
        Map map = new HashMap();
        map.put("useridlist",userids);
        String jsonMap = JSON.toJSONString(map);
        HttpResult httpResult = HttpClientUtils.doPost(WxAPI.USER_BATCH_DELETE_URL.replace("ACCESS_TOKEN", accessToken),jsonMap);
        if (httpResult.getStatus() == 200) {
            JSONObject jsonObject = JSON.parseObject(httpResult.getData());
            response = JSON.toJavaObject(jsonObject,Response.class);
            int rcode = response.getErrcode();
            if(rcode!=0){
                logger.info("批量删除成员失败", "batchDeleteUser params userids:{},accessToken:{}  response:{}",new Object[]{userids,accessToken,response});
                throw new RCodeException(rcode, WxRCode.getErrorMsg(rcode));
            }
        }
        logger.info("批量删除成员成功", "batchDeleteUser params userids:{},accessToken:{}  response:{}",new Object[]{userids,accessToken,response});
        return response;
    }

    /**
     * @param departmentId  部门id
     * @param fetchChild    1/0：是否递归获取子部门下面的成员
     * @param accessToken   访问token
     * @return
     * @throws RCodeException
     */
    public static UserListResp getUserSimpleList(Integer departmentId, boolean fetchChild, String accessToken) throws RCodeException{
        UserListResp userListRes = null;
        HttpResult httpResult = HttpClientUtils.doGet(WxAPI.USER__SIMPLELIST_URl.replace("DEPARTMENT_ID",String.valueOf(departmentId)).replace("FETCH_CHILD",fetchChild?"1":"0").replace("ACCESS_TOKEN",accessToken));
        if(httpResult.getStatus()==200){
            JSONObject jsonObject = JSON.parseObject(httpResult.getData());
            userListRes = JSON.toJavaObject(jsonObject,UserListResp.class);
            int rcode = userListRes.getErrcode();
            if(rcode!=0){
                logger.info("获取成员失败", "getUserSimpleList params departmentId:{},fetchChild:{},accessToken:{},response:{}",new Object[]{departmentId,fetchChild,accessToken,accessToken,userListRes});
                throw new RCodeException(rcode, WxRCode.getErrorMsg(rcode));
            }
        }
        logger.info("获取成员成功", "getUserSimpleList params departmentId:{},fetchChild:{},accessToken:{},response:{}",new Object[]{departmentId,fetchChild,accessToken,accessToken,userListRes});
        return userListRes;
    }

    /**
     * @param departmentId 部门编号
     * @param fetchChild   是否递归获取子部门
     * @param accessToken  访问token
     * @return
     * @throws RCodeException
     */
    public static UserListResp getUserList(Integer departmentId, boolean fetchChild, String accessToken) throws RCodeException{
        UserListResp userListRes = null;
        HttpResult httpResult = HttpClientUtils.doGet(WxAPI.USER_LIST_URL.replace("DEPARTMENT_ID",String.valueOf(departmentId)).replace("FETCH_CHILD",fetchChild?"1":"0").replace("ACCESS_TOKEN",accessToken));
        if(httpResult.getStatus()==200){
            JSONObject jsonObject = JSON.parseObject(httpResult.getData());
            userListRes = JSON.toJavaObject(jsonObject,UserListResp.class);
            int rcode = userListRes.getErrcode();
            if(rcode!=0){
                logger.info("获取成员详细信息失败", "getUserList params departmentId:{},fetchChild:{},accessToken:{},response:{}",new Object[]{departmentId,fetchChild,accessToken,accessToken,userListRes});
                throw new RCodeException(rcode, WxRCode.getErrorMsg(rcode));
            }
        }
        logger.info("获取成员详细信息成功", "getUserList params departmentId:{},fetchChild:{},accessToken:{},response:{}",new Object[]{departmentId,fetchChild,accessToken,accessToken,userListRes});
        return userListRes;
    }

    /**
     * @param userid   企业内的成员id
     * @param agentid  整型，仅用于发红包。其它场景该参数不要填，如微信支付、企业转账、电子发票
     * @param accessToken 调用接口凭证
     * @return
     * @throws RCodeException
     */
    public static OpenIdRes userIdToOpenid(String userid, Integer agentid, String accessToken) throws RCodeException{
        OpenIdRes openIdRes = null;
        JSONObject reUid = new JSONObject();
        reUid.put("userid",userid);
        reUid.put("agentid",agentid);
        String mapJson = JSON.toJSONString(reUid);
        HttpResult httpResult = HttpClientUtils.doPost(WxAPI.USER_TO_OPENID.replace("ACCESS_TOKEN",accessToken),mapJson);
        if(httpResult.getStatus()==200){
            JSONObject jsonObject = JSON.parseObject(httpResult.getData());
            openIdRes = JSON.toJavaObject(jsonObject,OpenIdRes.class);
            int rcode = openIdRes.getErrcode();
            if(rcode!=0){
                logger.info("userid交换openid失败", "userIdToOpenid params userid:{},agentid:{},accessToken:{},response:{}",new Object[]{userid,agentid,accessToken,openIdRes});
                throw new RCodeException(rcode, WxRCode.getErrorMsg(rcode));
            }
        }
        logger.info("userid交换openid成功", "userIdToOpenid params userid:{},agentid:{},accessToken:{},response:{}",new Object[]{userid,agentid,accessToken,openIdRes});
      return openIdRes;
    }

    /**
     * @param openid      openID
     * @param accessToken  访问token
     * @return
     * @throws RCodeException
     */
    public static UserIdRes openIdToUserId(String openid, String accessToken) throws RCodeException{
        UserIdRes userIdRes = null;
        JSONObject jo = new JSONObject();
        jo.put("openid",openid);
        String joMap = JSON.toJSONString(jo);
        HttpResult httpResult = HttpClientUtils.doPost(WxAPI.OPENID_TO_USER.replace("ACCESS_TOKEN",accessToken),joMap);
        if(httpResult.getStatus()==200){
            JSONObject jsonObject = JSON.parseObject(httpResult.getData());
            userIdRes = JSON.toJavaObject(jsonObject,UserIdRes.class);
            int rcode = userIdRes.getErrcode();
            if(rcode!=0){
                logger.info("openid交换userid失败", "openIdToUserId params openid:{},accessToken:{},response:{}",new Object[]{openid,accessToken,userIdRes});
                throw new RCodeException(rcode, WxRCode.getErrorMsg(rcode));
            }
        }
        logger.info("openid交换userid成功", "openIdToUserId params openid:{},accessToken:{},response:{}",new Object[]{openid,accessToken,userIdRes});
        return userIdRes;
    }

    /**
     * @param userid      用户id
     * @param accessToken 访问token
     * @return
     * @throws RCodeException
     */
    public static Response authUser(String userid,String accessToken) throws RCodeException{
        Response response = null;
        HttpResult httpResult = HttpClientUtils.doGet(WxAPI.USER_AUTH_URL.replace("ACCESS_TOKEN", accessToken).replace("USERID",userid));
        if (httpResult.getStatus() == 200) {
            JSONObject jsonObject = JSON.parseObject(httpResult.getData());
            response = JSON.toJavaObject(jsonObject,Response.class);
            int rcode = response.getErrcode();
            if(rcode!=0){
                logger.info("二次验证失败", "authUser params userid:{},accessToken:{}  response:{}",new Object[]{userid,accessToken,response});
                throw new RCodeException(rcode, WxRCode.getErrorMsg(rcode));
            }
        }
        logger.info("二次验证成功", "authUser params userid:{},accessToken:{}  response:{}",new Object[]{userid,accessToken,response});
        return response;
    }

}
