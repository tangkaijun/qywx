package com.github.qywx.manager.service;

/**
 * 企业微信回调消息处理失败（读取请求体失败、解密失败或验签失败）。
 *
 * 用于替代原先「捕获异常后返回 null」的静默失败行为，让调用方能够明确区分
 * 「无消息」与「处理失败」，并返回明确的错误响应。
 */
public class MessageProcessException extends RuntimeException {

    public MessageProcessException(String message, Throwable cause) {
        super(message, cause);
    }
}
