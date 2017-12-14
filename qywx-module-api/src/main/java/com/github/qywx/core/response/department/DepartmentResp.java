package com.github.qywx.core.response.department;

import com.alibaba.fastjson.JSON;
import com.github.qywx.core.request.Department;
import com.github.qywx.core.response.Response;

import java.util.List;

/**
 * Created by kjtang on 2017/12/11.
 * 部门响应信息
 */
public class DepartmentResp extends Response{

    //部门列表信息
    private List<Department> department;

    public List<Department> getDepartment() {
        return department;
    }

    public void setDepartment(List<Department> department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
