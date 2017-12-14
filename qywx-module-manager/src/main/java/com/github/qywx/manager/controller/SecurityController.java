package com.github.qywx.manager.controller;

import com.github.qywx.aes.WXBizMsgCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by kjtang on 2017/12/14.
 */

@RequestMapping("/api/")
@Controller
public class SecurityController {

    public SecurityController(){
        System.out.println("SecurityController.....初始化了.....");
    }

    @RequestMapping("/verifyURL")
    public void  verifyURL(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String msg_signature = request.getParameter("msg_signature"); // 微信加密签名
        String timestamp = request.getParameter("timestamp"); // 时间戳
        String nonce = request.getParameter("nonce"); // 随机数
        String echostr = request.getParameter("echostr"); // 随机字符串
        System.out.println("msg_signature="+msg_signature+" timestamp="+timestamp+" nonce="+nonce+" echostr="+echostr);
        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt("tLC8V8", "XRCWjWnk4M71NtHN4S4hSLfllPogoO6aS2TJ1oOLEwm", "wwc8cefdb9d53fd9f0");
        String result = wxcpt.VerifyURL(msg_signature, timestamp, nonce, echostr);
        PrintWriter out = response.getWriter();
        System.out.println("result="+result);
        out.print(result);
        out.close();
        out = null;
    }

}
