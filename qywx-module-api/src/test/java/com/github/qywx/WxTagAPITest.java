package com.github.qywx;

import com.github.qywx.api.AccessTokenAPI;
import com.github.qywx.api.WxTagAPI;
import com.github.qywx.core.request.Tag;
import com.github.qywx.core.response.AccessToken;
import com.github.qywx.core.response.Response;
import com.github.qywx.core.response.tag.TagListResp;
import com.github.qywx.core.response.tag.TagResp;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by kjtang on 2017/12/14.
 */
public class WxTagAPITest {

    private AccessToken accessToken;

    @Before
    public void beforeRun(){
        accessToken = AccessTokenAPI.getAccessToken("wwc8cefdb9d53fd9f0","8jLibCI_PHuaY63SzU3VoAK5O68UdQhtzMat26hcCJo");
    }

    /**
     * 创建标签
     * @throws Exception
     */
    @Test
    public void testCreateTag() throws Exception{
        Tag tag = new Tag();
        tag.setTagname("测试标签2");
        Integer id= WxTagAPI.createTag(tag,accessToken.getAccess_token());
        System.out.println("新建标签的ID:"+id);
    }

    /**修改标签*/
    @Test
    public void testUpdateTag() throws Exception{
        Tag tag = new Tag();
        tag.setTagid(1);
        tag.setTagname("测试标签");
        Response response =  WxTagAPI.updateTag(tag,accessToken.getAccess_token());
        System.out.println(response);
    }

    /**删除标签*/
    @Test
    public void testDeleteTag() throws Exception{
        WxTagAPI.deleteTag(1,accessToken.getAccess_token());
    }

    /**新增标签成员*/
    @Test
    public void testAddTagUser() throws Exception{
       TagResp tagResp =  WxTagAPI.addTagUser(1, Arrays.asList("kjtang"),Arrays.asList(1),accessToken.getAccess_token());
       System.out.println(tagResp);
    }

    /**查询指定标签下的成员*/
    @Test
    public void testGetTagUser() throws Exception{
        TagResp tagResp = WxTagAPI.getTagUsers(1,accessToken.getAccess_token());
        System.out.println(tagResp);
    }

    /***删除标签下成员*/
    @Test
    public void testDeleteTagUser() throws Exception{
        TagResp tagResp = WxTagAPI.deleteTagUser(1,Arrays.asList("kjtang"),Arrays.asList(1),accessToken.getAccess_token());
    }

    /**获取所有标签列表*/
    @Test
    public void testGetTagList() throws Exception{
        TagListResp tagListResp  = WxTagAPI.getTagList(accessToken.getAccess_token());
        System.out.println(tagListResp);
    }

}
