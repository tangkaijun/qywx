package com.github.qywx.core.response.wxdepart;

import com.alibaba.fastjson.JSON;
import com.github.qywx.core.request.WxDepart;
import com.github.qywx.core.response.Response;

import java.util.List;

/**
 * Created by kjtang on 2017/12/11.
 * 部门响应信息
 */
public class DepartmentRes extends Response{

    //部门列表信息
    private List<WxDepart> department;

    public List<WxDepart> getDepartment() {
        return department;
    }

    public void setDepartment(List<WxDepart> department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
