package com.github.qywx.manager.controller;

import com.github.qywx.aes.WXBizMsgCrypt;
import com.github.qywx.core.constant.WxParam;
import com.github.qywx.manager.service.MessageProcessException;
import com.github.qywx.manager.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by kjtang on 2017/12/14.
 */

@RequestMapping("/api/")
@Controller
public class CoreController {

    private static final Logger logger = LoggerFactory.getLogger(CoreController.class);

    /**
     * 验证URL
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/handle",method = RequestMethod.GET)
    public void  verifyURL(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String msg_signature = request.getParameter("msg_signature"); // 微信加密签名
        String timestamp = request.getParameter("timestamp"); // 时间戳
        String nonce = request.getParameter("nonce"); // 随机数
        String echostr = request.getParameter("echostr"); // 随机字符串
        logger.debug("URL 验证请求, msg_signature:{}, timestamp:{}, nonce:{}", msg_signature, timestamp, nonce);
        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(WxParam.TOKEN, WxParam.ENCODING_AES_KEY,WxParam.CORPID);
        String result = wxcpt.VerifyURL(msg_signature, timestamp, nonce, echostr);
        PrintWriter out = response.getWriter();
        out.print(result);
        out.close();
    }

    /**
     * 处理微信服务器发来的消息
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/handle",method = RequestMethod.POST)
    public void handleMessage(HttpServletRequest request,HttpServletResponse response) throws Exception{
        //1.将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //2.调用消息业务类接收消息、处理消息
        MessageService msgsv=new MessageService();
        PrintWriter out = response.getWriter();
        try {
            String respMessage = msgsv.getEncryptRespMessage(request);
            //3.响应消息
            out.print(respMessage);
        } catch (MessageProcessException e) {
            // 解密 / 验签 / 读取请求体失败：返回明确的错误响应，不再静默输出 "null"
            logger.warn("处理企业微信回调消息失败, msg_signature:{}, timestamp:{}, nonce:{}",
                    request.getParameter("msg_signature"), request.getParameter("timestamp"),
                    request.getParameter("nonce"), e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("处理消息失败");
        } finally {
            out.close();
        }
    }

    public void secondVerify(HttpServletRequest request,HttpServletResponse response) throws Exception{
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        PrintWriter out = response.getWriter();
        String code = request.getParameter("code");
        if (!"authdeny".equals(code)) {
            // 调取凭证
            String access_token = "";
            // 打印凭证
            out.print("凭证:"+access_token);
            // agentid 跳转链接时所在的企业应用ID 管理员须拥有agent的使用权限；agentid必须和跳转链接时所在的企业应用ID相同
           // String UserID = GOauth2Core.GetUserID(access_token, code, "1");
            // 二次验证地址
          //  String RequestURL = ParamesAPI.Secondary_verification.replace("ACCESS_TOKEN",access_token).replace("USERID", UserID);
            // 二次验证返回数据
         //   JSONObject jsonObject = WeixinUtil.HttpRequest(RequestURL, "GET", null);
            // 二次验证结果
           // out.print("二次验证返回结果：\n"+jsonObject);
            // 传值
          //  request.setAttribute("jsonObject", jsonObject);
        }
        else{
            out.print("Code获取失败，至于为什么，自己找原因。。。");
        }
    }

}
