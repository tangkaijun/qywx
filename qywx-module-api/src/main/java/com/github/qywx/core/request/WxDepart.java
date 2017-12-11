package com.github.qywx.core.request;

import com.alibaba.fastjson.JSON;

/**
 * Created by kaijun on 2017/12/10.
 */
public class WxDepart {

    //部门id，32位整型。指定时必须大于1，否则自动生成
    private Integer id;
    //部门名称。长度限制为1~64个字节，字符不能包括\:?”<>｜
    private String name;
    //父部门id，32位整型
    private Integer parentid;
    //在父部门中的次序值。order值大的排序靠前。有效的值范围是[0, 2^32)
    private Integer order;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
