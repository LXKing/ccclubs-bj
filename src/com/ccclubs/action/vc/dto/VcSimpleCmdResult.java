package com.ccclubs.action.vc.dto;

/**
 * 车机中心简单指令执行结果
 * @author Administrator
 *
 */
public class VcSimpleCmdResult<T> {
    /**
     * 指令执行结果
     */
    private boolean success;
    /**
     * 指令消息ID
     */
    private long messageId;
    /**
     * 结果返回码
     */
    private int code;
    /**
     * 返回码解释
     */
    private String message;
    /**
     * 返回的结果数据
     */
    private T data;
    
    
    public boolean isSuccess() {
        return success;
    }
    public long getMessageId() {
        return messageId;
    }
    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public T getData() {
        return data;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setData(T data) {
        this.data = data;
    }
    
    

}
