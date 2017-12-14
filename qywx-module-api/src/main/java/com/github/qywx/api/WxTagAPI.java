package com.github.qywx.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.qywx.core.constant.WxAPI;
import com.github.qywx.core.constant.WxRCode;
import com.github.qywx.core.request.Tag;
import com.github.qywx.core.response.Response;
import com.github.qywx.core.response.tag.TagListResp;
import com.github.qywx.core.response.tag.TagResp;
import com.github.qywx.exception.RCodeException;
import com.github.qywx.utils.httpclient.HttpClientUtils;
import com.github.qywx.utils.httpclient.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by kjtang on 2017/12/11.
 * 标签管理API
 */
public class WxTagAPI {

    private static Logger logger = LoggerFactory.getLogger(WxTagAPI.class);

    /**
     * @param wxTag       标签信息实体
     * @param accessToken 调用接口凭证
     * @return 创建标签的id
     * @throws RCodeException
     */
    public static Integer createTag(Tag wxTag, String accessToken) throws RCodeException {
        String json = JSON.toJSONString(wxTag);
        int id = 0;
        HttpResult httpResult = HttpClientUtils.doPost(WxAPI.TAG_CREATE_URL.replace("ACCESS_TOKEN", accessToken), json);
        if (httpResult.getStatus() == 200) {
            JSONObject jo = JSON.parseObject(httpResult.getData());
            int rcode = jo.getInteger("errcode");
            id = jo.getInteger("tagid");
            if (rcode != 0) {
                logger.info("创建标签失败", "createTag params wxTag:{},accessToken:{}, response:{}", new Object[]{wxTag, accessToken, id});
                throw new RCodeException(rcode, WxRCode.getErrorMsg(rcode));
            }
            logger.info("创建标签成功", "createTag params wxTag:{},accessToken:{}, response:{}", new Object[]{wxTag, accessToken, id});
        }
        return id;
    }

    /**
     * @param tag         标签信息实体
     * @param accessToken 调用接口凭证
     * @return 状态信息
     * @throws RCodeException
     */
    public static Response updateTag(Tag wxTag, String accessToken) throws RCodeException {
        Response response = null;
        String json = JSON.toJSONString(wxTag);
        HttpResult httpResult = HttpClientUtils.doPost(WxAPI.TAG_UPDATE_URL.replace("ACCESS_TOKEN", accessToken), json);
        if (httpResult.getStatus() == 200) {
            JSONObject jo = JSON.parseObject(httpResult.getData());
            response = JSON.toJavaObject(jo, Response.class);
            int rcode = response.getErrcode();
            if (rcode != 0) {
                logger.info("更新标签失败", "updateTag params wxTag:{},accessToken:{}, response:{}", new Object[]{wxTag, accessToken, response});
                throw new RCodeException(rcode, WxRCode.getErrorMsg(rcode));
            }
            logger.info("更新标签成功", "updateTag params wxTag:{},accessToken:{}, response:{}", new Object[]{wxTag, accessToken, response});
        }
        return response;
    }

    /**
     * @param tagid       标签id
     * @param accessToken 调用接口凭证
     * @return 响应消息
     * @throws RCodeException
     */
    public static Response deleteTag(Integer tagid, String accessToken) throws RCodeException {
        Response response = null;
        HttpResult httpResult = HttpClientUtils.doGet(WxAPI.TAG_DELETE_URL.replace("ACCESS_TOKEN", accessToken).replace("TAGID", String.valueOf(tagid)));
        if (httpResult.getStatus() == 200) {
            JSONObject jo = JSON.parseObject(httpResult.getData());
            response = JSON.toJavaObject(jo, Response.class);
            int rcode = response.getErrcode();
            if (rcode != 0) {
                logger.info("删除标签失败", "deleteTag params tagid:{},accessToken:{}, response:{}", new Object[]{tagid, accessToken, response});
                throw new RCodeException(rcode, WxRCode.getErrorMsg(rcode));
            }
            logger.info("删除标签成功", "deleteTag params tagid:{},accessToken:{}, response:{}", new Object[]{tagid, accessToken, response});
        }
        return response;
    }

    /**
     * @param tagid       标签id
     * @param accessToken 调用接口凭证
     * @return 该标签下的用户信息列表
     * @throws RCodeException
     */
    public static TagResp getTagUsers(Integer tagid, String accessToken) throws RCodeException {
        TagResp tagRes = null;
        HttpResult httpResult = HttpClientUtils.doGet(WxAPI.TAG_GETUSER_URL.replace("TAGID", String.valueOf(tagid)).replace("ACCESS_TOKEN", accessToken));
        if (httpResult.getStatus() == 200) {
            JSONObject jo = JSON.parseObject(httpResult.getData());
            tagRes = JSON.toJavaObject(jo, TagResp.class);
            int rcode = tagRes.getErrcode();
            if (rcode != 0) {
                logger.info("查询标签下的用户失败", "getTagUsers params tagid:{},accessToken:{}, response:{}", new Object[]{tagid, accessToken, tagRes});
                throw new RCodeException(rcode, WxRCode.getErrorMsg(rcode));
            }
            logger.info("查询标签下的用户成功", "getTagUsers params tagid:{},accessToken:{}, response:{}", new Object[]{tagid, accessToken, tagRes});
        }
        return tagRes;
    }

    /**
     * @param tagid
     * @param userlist
     * @param partylist
     * @param accessToken
     * @return
     * @throws RCodeException
     */
    public static TagResp addTagUser(Integer tagid, List<String> userlist, List<Integer> partylist, String accessToken) throws RCodeException{
            TagResp tagRes = null;
            JSONObject reqJo = new JSONObject();
            reqJo.put("tagid",tagid);
            reqJo.put("userlist",userlist);
            reqJo.put("partylist",partylist);
            HttpResult httpResult = HttpClientUtils.doPost(WxAPI.TAG_ADDUSER_URL.replace("ACCESS_TOKEN",accessToken),reqJo.toJSONString());
            if(httpResult.getStatus()==200){
                JSONObject jo = JSON.parseObject(httpResult.getData());
                tagRes = JSON.toJavaObject(jo, TagResp.class);
                int rcode = tagRes.getErrcode();
                if (rcode != 0) {
                    logger.info("标签下新增成员失败", "addTagUser params tagid:{},userlist:{},partylist:{},accessToken:{}, response:{}", new Object[]{tagid,userlist,partylist,accessToken, tagRes});
                    throw new RCodeException(rcode, WxRCode.getErrorMsg(rcode));
                }
                logger.info("标签下新增成员成功", "addTagUser params tagid:{},userlist:{},partylist:{},accessToken:{}, response:{}", new Object[]{tagid,userlist,partylist,accessToken, tagRes});
            }
        return tagRes;
    }

    /**
     * 删除标签成员
     * @param tagid
     * @param userlist
     * @param partylist
     * @return
     * @throws Exception
     */
    public static TagResp deleteTagUser(Integer tagid,List<String> userlist,List<Integer> partylist,String accessToken) throws RCodeException{
        TagResp tagResp = null;
        JSONObject reqJo = new JSONObject();
        reqJo.put("tagid",tagid);
        reqJo.put("userlist",userlist);
        reqJo.put("partylist",partylist);
        HttpResult httpResult = HttpClientUtils.doPost(WxAPI.TAG_DELUSER_URL.replace("ACCESS_TOKEN",accessToken),reqJo.toJSONString());
        if(httpResult.getStatus()==200){
            JSONObject jo = JSON.parseObject(httpResult.getData());
            tagResp = JSON.toJavaObject(jo, TagResp.class);
            int rcode = tagResp.getErrcode();
            if (rcode != 0) {
                logger.info("删除标签下成员失败", "addTagUser params tagid:{},userlist:{},partylist:{},accessToken:{}, response:{}", new Object[]{tagid,userlist,partylist,accessToken, tagResp});
                throw new RCodeException(rcode, WxRCode.getErrorMsg(rcode));
            }
            logger.info("删除标签下成员成功", "addTagUser params tagid:{},userlist:{},partylist:{},accessToken:{}, response:{}", new Object[]{tagid,userlist,partylist,accessToken, tagResp});
        }
        return tagResp;
    }

    /**
     * @param accessToken 接口调用凭证
     * @return
     * @throws RCodeException
     */
    public static TagListResp getTagList(String accessToken) throws RCodeException{
        TagListResp tagListResp = null;
        HttpResult httpResult = HttpClientUtils.doGet(WxAPI.TAG_LIST_URl.replace("ACCESS_TOKEN", accessToken));
        if (httpResult.getStatus() == 200) {
            JSONObject jo = JSON.parseObject(httpResult.getData());
            tagListResp = JSON.toJavaObject(jo, TagListResp.class);
            int rcode = tagListResp.getErrcode();
            if (rcode != 0) {
                logger.info("查询所有标签列表失败", "getTagUsers params accessToken:{}, response:{}", new Object[]{accessToken, tagListResp});
                throw new RCodeException(rcode, WxRCode.getErrorMsg(rcode));
            }
            logger.info("查询所有标签列表成功", "getTagUsers params accessToken:{}, response:{}", new Object[]{accessToken, tagListResp});
        }
        return tagListResp;
    }

}
