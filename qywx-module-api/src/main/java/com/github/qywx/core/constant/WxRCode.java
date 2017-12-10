package com.github.qywx.core.constant;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by kaijun on 2017/12/10.
 */
public class WxRCode {

    private static Map<Integer,String> statusMap = new HashMap<Integer,String>();

    static {
        statusMap.put(-1,"系统繁忙");
        statusMap.put(0,"请求成功");
        statusMap.put(40001,"不合法的secret参数");
        statusMap.put(40003,"无效的UserID");
        statusMap.put(40004,"不合法的媒体文件类型");
        statusMap.put(40005,"不合法的type参数");
        statusMap.put(40006,"不合法的文件大小");
        statusMap.put(40007,"不合法的media_id参数");
        statusMap.put(40008,"不合法的msgtype参数");
        statusMap.put(40009,"上传图片大小不是有效值");
        statusMap.put(40011,"上传视频大小不是有效值");
        statusMap.put(40013,"不合法的CorpID");
    }

    public static String getErrorMsg(int code){
        String msg = "";
        if(statusMap!=null){
                msg = statusMap.get(code);
            if(msg==null||msg==""){
                msg = "未知错误";
            }
        }
        return msg;
    }

}
