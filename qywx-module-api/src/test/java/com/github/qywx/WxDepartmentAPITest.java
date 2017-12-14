package com.github.qywx;

import com.github.qywx.api.AccessTokenAPI;
import com.github.qywx.api.WxDepartmentAPI;
import com.github.qywx.core.request.Department;
import com.github.qywx.core.response.AccessToken;
import com.github.qywx.core.response.department.DepartmentResp;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kjtang on 2017/12/14.
 */
public class WxDepartmentAPITest {

    private static Logger logger = LoggerFactory.getLogger(WxDepartmentAPITest.class);

    private AccessToken accessToken;

    @Before
    public void beforeRun(){
        accessToken = AccessTokenAPI.getAccessToken("wwc8cefdb9d53fd9f0","8jLibCI_PHuaY63SzU3VoAK5O68UdQhtzMat26hcCJo");
    }

    /**
     * 测试创建部门
     * @throws Exception
     * 说明:需要开启编辑通讯录权限，普通应用的secret仅有只读权限，可使用通讯录同步助手的secret同步
     */
    @Test
    public void testCreateDepartment() throws Exception{
        Department department = new Department();
        department.setName("创建的测试部门");
        department.setOrder(2);
        department.setParentid(1);
        Integer id = WxDepartmentAPI.createDepartment(department,accessToken.getAccess_token());
        logger.info("新增部门的id:{}",id);
    }

    /**
     * 修改部门
     * @throws Exception
     */
    @Test
    public void testUpdateDepartment() throws Exception{
        Department department = new Department();
        department.setId(3);
        department.setOrder(3);
        department.setName("修改后的部门名称");
        WxDepartmentAPI.updateDepartment(department,accessToken.getAccess_token());
    }

    /**
     * 删除部门
     * @throws Exception
     */
    @Test
    public void testDeleteDepartment() throws Exception{
        WxDepartmentAPI.deleteDepartment(4,accessToken.getAccess_token());
    }

    /**
     * id不是必须参数，如果不传递，则查询全组织架构
     * 如果指定ID则查询改部门及其下的子部门
     * @throws Exception
     */
    @Test
    public void testGetDepartmentList() throws Exception{
        DepartmentResp departmentResp = WxDepartmentAPI.getDepartmentList(null,accessToken.getAccess_token());
        System.out.println(departmentResp);

        DepartmentResp departmentResp1 = WxDepartmentAPI.getDepartmentList(2,accessToken.getAccess_token());
        System.out.println(departmentResp1);
    }

}
