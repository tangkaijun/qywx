package com.github.qywx.api;

import com.github.qywx.core.request.WxDepart;
import com.github.qywx.exception.RCodeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kaijun on 2017/12/10.
 * 部门管理
 * author: tangkaijun
 * description:部门管理
 */
public class WxDepartmentAPI {

    private Logger logger = LoggerFactory.getLogger(WxDepartmentAPI.class);

    /**
     * @param wxDepart    部门信息实体
     * @param accessToken 访问token
     * @return
     * @throws RCodeException
     */
    public static Integer createDepartment(WxDepart wxDepart,String accessToken) throws RCodeException{
        return 0;
    }

}
