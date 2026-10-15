package com.github.qywx.manager.service;

import com.github.qywx.aes.AesException;
import com.github.qywx.aes.WXBizMsgCrypt;
import com.github.qywx.core.constant.WxParam;
import com.github.qywx.core.response.message.passive.TextMessage;
import com.github.qywx.utils.MessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Map;

/**
 * Created by kjtang on 2017/12/15.
 */
public class MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);


    private String msg_signature ; // 微信加密签名
    private String timestamp ;    // 时间戳
    private String nonce ;       // 随机数


    /**
     * @desc ：获取加密后的回复消息
     *
     * @param request
     * @return String  返回加密后的回复消息
     */
    public String getEncryptRespMessage(HttpServletRequest request){
        String respMessage = null;

        try {
            //1.解密微信发过来的消息
            String xmlMsg=this.getDecryptMsg(request);
            logger.debug("解密后的报文长度:{}", xmlMsg == null ? 0 : xmlMsg.length());
            //2.解析微信发来的请求,解析xml字符串
            Map<String, String> requestMap= MessageUtils.parseXml(xmlMsg);

            //3.获取请求参数
            //3.1 消息类型与事件
            String msgType = requestMap.get("MsgType");
            String eventType = requestMap.get("Event");
            String eventKey = requestMap.get("EventKey");
            logger.debug("收到回调消息, msgType:{}, eventType:{}, eventKey:{}", msgType, eventType, eventKey);

            //4.组装 回复文本消息
            //4.1.获取回复消息的内容 ：消息的分类处理
            String respContent=this.getRespContentByMsgType(msgType, eventType, eventKey);
            TextMessage textMessage = buildReplyTextMessage(requestMap, respContent);
            logger.debug("回复内容:{}", respContent);

            //5.获取xml字符串： 将（被动回复消息型的）文本消息对象 转成  xml字符串
            respMessage = MessageUtils.textMessageToXml(textMessage);


            //6.加密
            WXBizMsgCrypt wxcpt=new WXBizMsgCrypt(WxParam.TOKEN, WxParam.ENCODING_AES_KEY,WxParam.CORPID);
            respMessage = wxcpt.EncryptMsg(respMessage, timestamp, msgType);

        } catch (Exception e) {
            logger.error("处理企业微信回调消息失败", e);
        }

        return respMessage;
    }


    /**
     * @desc ：2.从request中获取消息明文
     *
     * @param request
     * @return String   消息明文
     */
    public String getDecryptMsg(HttpServletRequest request) {

        String postData="";   // 密文，对应POST请求的数据
        String result="";     // 明文，解密之后的结果

        this.msg_signature = request.getParameter("msg_signature"); // 微信加密签名
        this.timestamp = request.getParameter("timestamp");   // 时间戳
        this.nonce = request.getParameter("nonce");          // 随机数

        try {
            //1.获取加密的请求消息：使用输入流获得加密请求消息postData
            ServletInputStream in = request.getInputStream();
            BufferedReader reader =new BufferedReader(new InputStreamReader(in));

            String tempStr="";   //作为输出字符串的临时串，用于判断是否读取完毕
            while(null!=(tempStr=reader.readLine())){
                postData+=tempStr;
            }

            //2.获取消息明文：对加密的请求消息进行解密获得明文
            WXBizMsgCrypt wxcpt=new WXBizMsgCrypt(WxParam.TOKEN, WxParam.ENCODING_AES_KEY,WxParam.CORPID);
            result = wxcpt.DecryptMsg(msg_signature, timestamp, nonce, postData);

        } catch (IOException e) {
            logger.error("读取企业微信回调请求体失败", e);
        } catch (AesException e) {
            logger.error("企业微信回调消息解密或验签失败, msg_signature:{}, timestamp:{}, nonce:{}", msg_signature, timestamp, nonce, e);
        }

        return result;
    }

    /**
     * 组装被动回复的文本消息。
     *
     * 企业微信推送过来的报文中，FromUserName 是成员账号（消息的发送方），
     * ToUserName 是企业 CorpID / 应用；回复方向相反，必须把 ToUserName 设为成员、
     * FromUserName 设为企业应用，否则企业微信无法正确路由，回复无法送达。
     *
     * @param requestMap 企业微信推送的请求参数
     * @param respContent 回复内容
     * @return 待加密的被动回复文本消息
     */
    TextMessage buildReplyTextMessage(Map<String, String> requestMap, String respContent) {
        String toUser = requestMap.get("FromUserName");   // 消息来自的成员
        String fromUser = requestMap.get("ToUserName");   // 企业 CorpID / 应用

        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(toUser);
        textMessage.setFromUserName(fromUser);
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setMsgType(MessageUtils.RESP_MESSAGE_TYPE_TEXT);
        textMessage.setContent(respContent);
        return textMessage;
    }

    /**
     * @desc ：3.处理消息：根据消息类型获取回复内容
     *
     * @param msgType
     * @return String
     */
    public String getRespContentByMsgType(String msgType,String eventType,String eventKey){
        String respContent="";
        //1.文本消息
        if (MessageUtils.REQ_MESSAGE_TYPE_TEXT.equals(msgType)) {
            respContent = "您发送的是文本消息！";

        }
        //2.图片消息
        else if (MessageUtils.REQ_MESSAGE_TYPE_IMAGE.equals(msgType)) {
            respContent = "您发送的是图片消息！";
        }
        //3.地理位置消息：仅上报位置，无需回复
        else if (MessageUtils.REQ_MESSAGE_TYPE_LOCATION.equals(msgType)) {
            respContent = "";
        }
        //4.链接消息
        else if (MessageUtils.REQ_MESSAGE_TYPE_LINK.equals(msgType)) {
            respContent = "您发送的是链接消息！";
        }
        //5.音频消息
        else if (MessageUtils.REQ_MESSAGE_TYPE_VOICE.equals(msgType)) {
            respContent = "您发送的是音频消息！";
        }
        //6.事件推送
        else if (MessageUtils.REQ_MESSAGE_TYPE_EVENT.equals(msgType)) {
            respContent=this.processEevent(eventType, eventKey);
        }
        //7.请求异常
        else {
            respContent="请求处理异常，请稍候尝试！";
        }

        return respContent;
    }

    public String processEevent(String eventType,String eventKey){

        String respContent="";
        // 订阅
        if (MessageUtils.EVENT_TYPE_SUBSCRIBE.equals(eventType)) {
            respContent = "欢迎关注！";
        }
        // 取消订阅
        else if (MessageUtils.EVENT_TYPE_UNSUBSCRIBE.equals(eventType)) {
            // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
        }
        //上报地理位置事件：仅上报，无需回复
        else if("LOCATION".equals(eventType)){

        }
        // 自定义菜单点击事件
        else if (MessageUtils.EVENT_TYPE_CLICK.equals(eventType)) {

            if ("12".equals(eventKey)) {
                // TODO  help
            } else if ("13".equals(eventKey)) {
                respContent = "周边搜索菜单项被点击！";
            } else if ("14".equals(eventKey)) {
                respContent = "历史上的今天菜单项被点击！";
            } else if ("21".equals(eventKey)) {
                respContent = "歌曲点播菜单项被点击！";
            } else if ("22".equals(eventKey)) {

                respContent = "经典游戏菜单项被点击！";
            } else if ("23".equals(eventKey)) {
                respContent = "美女电台菜单项被点击！";
            } else if ("24".equals(eventKey)) {
                respContent = "人脸识别菜单项被点击！";
            } else if ("25".equals(eventKey)) {
                respContent = "聊天唠嗑菜单项被点击！";
            } else if ("31".equals(eventKey)) {
                respContent = "Q友圈菜单项被点击！";
            } else if ("32".equals(eventKey)) {
                respContent = "电影排行榜菜单项被点击！";
            } else if ("33".equals(eventKey)) {
                respContent = "幽默笑话菜单项被点击！";
            }
        }
        return respContent;
    }
}
