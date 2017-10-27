package com.isss.weixin.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 统一的json返回格式
 * @author Administrator
 *
 * @param <T>
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ControllerResult<T> {
    private boolean success;
    private T data;
    private String msg;
    private String code;//错误码

    public ControllerResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }
    
    public ControllerResult(boolean success, T data , String msg) {
        this.success = success;
        this.data = data;
        this.msg = msg;
    }
    
    /**
     * 正确信息
     * @param data
     * @param msg
     */
    public ControllerResult(T data ,String msg){
    	this.success = true;
    	this.data = data;
    	this.msg = msg;
    }
    
    /**
     * 错误信息
     * @param data
     * @param msg
     */
    public ControllerResult(String msg){
    	this.success = false;
    	this.msg = msg;
    }
    
    public ControllerResult(boolean success, String msg) {
        this.msg = msg;
        this.success = success;
    }
    public ControllerResult(boolean success, String msg,String code) {
        this.msg = msg;
        this.success = success;
        this.code = code;
    }
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
    
}
