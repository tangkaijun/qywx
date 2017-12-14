package com.github.qywx.exception;

/**
 * Created by kaijun on 2017/12/10.
 */
public class RCodeException extends RuntimeException {

    private int code;
    private String msg;

    public RCodeException(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode(){
        return this.code;
    }

    @Override
    public String getMessage() {
        return "code："+this.code+"   description："+this.msg;
    }

}