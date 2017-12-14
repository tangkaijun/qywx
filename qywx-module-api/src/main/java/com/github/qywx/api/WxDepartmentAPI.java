package com.github.qywx.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.qywx.core.constant.WxAPI;
import com.github.qywx.core.constant.WxRCode;
import com.github.qywx.core.request.Department;
import com.github.qywx.core.response.Response;
import com.github.qywx.core.response.department.DepartmentResp;
import com.github.qywx.exception.RCodeException;
import com.github.qywx.utils.httpclient.HttpClientUtils;
import com.github.qywx.utils.httpclient.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kaijun on 2017/12/10.
 * 部门管理
 * author: tangkaijun
 * description:部门管理
 */
public class WxDepartmentAPI {

    private static Logger logger = LoggerFactory.getLogger(WxDepartmentAPI.class);

    /**
     * @param wxDepart    部门信息实体
     * @param accessToken 调用接口凭证
     * @return            返回创建部门的id
     * @throws RCodeException
     */
    public static Integer createDepartment(Department wxDepart, String accessToken) throws RCodeException{
        int id = 0;
        String json = JSON.toJSONString(wxDepart);
        HttpResult httpResult =  HttpClientUtils.doPost(WxAPI.DEPARTMENT_CREATE_URL.replace("ACCESS_TOKEN",accessToken),json);
        if(httpResult.getStatus()==200){
            JSONObject jo =JSON.parseObject(httpResult.getData());
            int rcode = jo.getInteger("errcode");
            if(rcode!=0){
                logger.info("创建部门失败", "createDepartment params wxDepart:{},accessToken:{}, response:{}", new Object[]{wxDepart,accessToken,jo});
                throw new RCodeException(rcode, WxRCode.getErrorMsg(rcode));
            }
            id = jo.getInteger("id");
            logger.info("创建部门成功", "createDepartment params wxDepart:{},accessToken:{}, response:{}", new Object[]{wxDepart,accessToken,jo});
        }
        return id;
    }

    /**
     * @param wxDepart     微信部门信息实体
     * @param accessToken  调用接口凭证
     * @return
     * @throws RCodeException
     */
    public static Response updateDepartment(Department wxDepart, String accessToken) throws RCodeException{
        Response response = null;
        String json = JSON.toJSONString(wxDepart);
        HttpResult httpResult =  HttpClientUtils.doPost(WxAPI.DEPARTMENT_UPDATE_URL.replace("ACCESS_TOKEN",accessToken),json);
        if(httpResult.getStatus()==200){
            JSONObject jo =JSON.parseObject(httpResult.getData());
            response = JSON.toJavaObject(jo,Response.class);
            int rcode = response.getErrcode();
            if(rcode!=0){
                logger.info("更新部门失败", "updateDepartment params wxDepart:{},accessToken:{}, response:{}", new Object[]{wxDepart,accessToken,response});
                throw new RCodeException(rcode, WxRCode.getErrorMsg(rcode));
            }
            logger.info("更新部门成功", "updateDepartment params wxDepart:{},accessToken:{}, response:{}", new Object[]{wxDepart,accessToken,response});
        }
        return response;
    }

    /**
     * @param id   部门id。（注：不能删除根部门；不能删除含有子部门、成员的部门）
     * @param accessToken 调用接口凭证
     * @return
     * @throws RCodeException
     */
    public static Response deleteDepartment(Integer id,String accessToken) throws RCodeException{
        Response response = null;
        HttpResult httpResult =  HttpClientUtils.doGet(WxAPI.DEPARTMENT_DELETE_URL.replace("ACCESS_TOKEN",accessToken).replace("ID",String.valueOf(id)));
        if(httpResult.getStatus()==200){
            JSONObject jo =JSON.parseObject(httpResult.getData());
            response = JSON.toJavaObject(jo,Response.class);
            int rcode = response.getErrcode();
            if(rcode!=0){
                logger.info("删除部门失败", "deleteDepartment params id:{},accessToken:{}, response:{}", new Object[]{id,accessToken,response});
                throw new RCodeException(rcode, WxRCode.getErrorMsg(rcode));
            }
            logger.info("删除部门成功", "deleteDepartment params id:{},accessToken:{}, response:{}", new Object[]{id,accessToken,response});
        }
        return response;
    }

    /**
     * @param id          部门id。获取指定部门及其下的子部门。 如果不填，默认获取全量组织架构
     * @param accessToken 接口访问凭证
     * @return
     * @throws RCodeException
     */
    public static DepartmentResp getDepartmentList(Integer id, String accessToken) throws RCodeException{
        DepartmentResp departmentRes = null;
        HttpResult httpResult = HttpClientUtils.doGet(WxAPI.DEPARTMENT_LIST_URL.replace("ID",String.valueOf(id)).replace("ACCESS_TOKEN",accessToken));
        if(httpResult.getStatus()==200){
            JSONObject jo =JSON.parseObject(httpResult.getData());
            departmentRes = JSON.toJavaObject(jo,DepartmentResp.class);
            int rcode = departmentRes.getErrcode();
            if(rcode!=0){
                logger.info("查询部门失败", "getDepartmentList params id:{},accessToken:{}, response:{}", new Object[]{id,accessToken,departmentRes});
                throw new RCodeException(rcode, WxRCode.getErrorMsg(rcode));
            }
            logger.info("查询部门成功", "getDepartmentList params id:{},accessToken:{}, response:{}", new Object[]{id,accessToken,departmentRes});
        }
        return departmentRes;
    }

}
