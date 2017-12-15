package com.github.qywx;

import com.github.qywx.api.AccessTokenAPI;
import com.github.qywx.api.WxUserAPI;
import com.github.qywx.core.request.User;
import com.github.qywx.core.response.AccessToken;
import com.github.qywx.core.response.Response;
import com.github.qywx.core.response.user.OpenIdRes;
import com.github.qywx.core.response.user.UserIdRes;
import com.github.qywx.core.response.user.UserListResp;
import com.github.qywx.core.response.user.WxUserRes;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kaijun on 2017/12/10.
 */
public class WxUserAPITest {

    private AccessToken accessToken;

    @Before
    public void beforeRun(){
        accessToken = AccessTokenAPI.getAccessToken("wwc8cefdb9d53fd9f0","8jLibCI_PHuaY63SzU3VoAK5O68UdQhtzMat26hcCJo");
    }

    /**测试创建用户*/
    /**
     * 需要通讯录同步助手的
     * 通讯录同步助手access_token可指定任意成员id；应用access_token仅能指定可见范围配置的成员，以及部门/标签包含的成员（递归展开）
     * @throws Exception
     */
    @Test
    public void testCreateUser() throws Exception{
        User user = new User();
        user.setUserid("kjtang");
        user.setName("唐开军");
        user.setEnglish_name("kjtang");
        user.setMobile("18520890882");
        user.setDepartment(Arrays.asList(1,2));
        user.setOrder(Arrays.asList(10,40));
        user.setPosition("Java开发工程师");
        user.setGender("1");
        user.setEmail("2522893176@qq.com");
        user.setIsleader(0);
        user.setEnable(1);
        //user.setAvatar_mediaid("2-G6nrLmr5EC3MNb_-zL1dDdzkd0p7cNliYu9V5w7o8K0");
        user.setTelephone("19283-233232");
        WxUserAPI.createUser(user,accessToken.getAccess_token());
    }

    /**测试获取用户信息*/
    @Test
    public void testGetUser(){
        WxUserRes wxUserRes = WxUserAPI.getUser("zhaoliu",accessToken.getAccess_token());
        System.out.println(wxUserRes);
    }

    /***更新微信用户*/
    @Test
    public void testUpdateUser() throws Exception{
        User user = new User();
        user.setUserid("zhaoliu");
        user.setName("赵六123");
        user.setEnglish_name("zhaoliu");
        user.setMobile("182928377463");
        user.setDepartment(Arrays.asList(1,2));
        user.setOrder(Arrays.asList(10,40));
        user.setPosition("产品经理33");
        user.setGender("1");
        user.setEmail("zhaoliu@qq.com");
        user.setIsleader(1);
        user.setEnable(1);
        //user.setAvatar_mediaid("2-G6nrLmr5EC3MNb_-zL1dDdzkd0p7cNliYu9V5w7o8K0");
        user.setTelephone("020-123456");
        Response response = WxUserAPI.updateUser(user,accessToken.getAccess_token());
        System.out.println(response);
    }

    /**删除微信用户*/
    @Test
    public void testDeleteUser() throws Exception{
        WxUserAPI.deleteUser("hbeslcyb",accessToken.getAccess_token());
    }

    /**批量删除yoghurt*/
    @Test
    public void testBatchDeleteUser() throws Exception{
        WxUserAPI.batchDeleteUser(Arrays.asList("zhaoliu","wanger"),accessToken.getAccess_token());
    }

    /**获取部门成员*/
    @Test
    public void testGetDepartUserSimpleList() throws Exception{
        UserListResp userListResp = WxUserAPI.getUserSimpleList(2,true,accessToken.getAccess_token());
        System.out.println(userListResp);
    }

    /***获取部门成员详细信息*/
    @Test
    public void testGetDepartmentUserList() throws Exception{
        UserListResp userListResp = WxUserAPI.getUserList(2,true,accessToken.getAccess_token());
        System.out.println(userListResp);
    }

    /**Userid到OpenId互换*/
    /**
     * agentid :整型，仅用于发红包。其它场景该参数不要填，如微信支付、企业转账、电子发票
     * @throws Exception
     */
    @Test
    public void testUseridToOpenId() throws Exception{
        OpenIdRes openIdRes =  WxUserAPI.userIdToOpenid("khtang",null,accessToken.getAccess_token());
        System.out.println(openIdRes);//ovI640cG9DyixVPPGEtL1PHwKW94
    }

    @Test
    public void testOpenIdToUserId() throws Exception{
        UserIdRes userIdRes= WxUserAPI.openIdToUserId("ovI640cG9DyixVPPGEtL1PHwKW94",accessToken.getAccess_token());
        System.out.println(userIdRes);
    }

    //二次验证yoghurt
    @Test
    public void testAuthUser() throws Exception{
        WxUserAPI.authUser("khtang",accessToken.getAccess_token());
    }

}
