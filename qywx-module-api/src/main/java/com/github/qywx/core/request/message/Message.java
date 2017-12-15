package com.github.qywx.core.request.message;

/**
 * 以xml格式并加密处理发送
 * Created by 接收消息的基类 on 2017/12/14.
 * 消息流向:（普通用户 -> 企业微信）
 */
public class Message {

    // 开发者微信号
    private String ToUserName;
    // 发送方帐号（一个OpenID）
    private String FromUserName;
    // 消息创建时间 （整型）
    private Long CreateTime;
    // 消息类型（text/image/location/link）
    private String MsgType;
    // 消息id，64位整型
    private Long MsgId;
    //企业应用的id，整型。可在应用的设置页面查看
    private Integer AgentID;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public Long getMsgId() {
        return MsgId;
    }

    public void setMsgId(Long msgId) {
        MsgId = msgId;
    }

    public Integer getAgentID() {
        return AgentID;
    }

    public void setAgentID(Integer agentID) {
        AgentID = agentID;
    }

}
