package com.ccclubs.action.vc.dto;

/**
 * 车机中心api调用结果
 * @author Administrator
 * 2018-8-18
 */
public class VcApiResult {
    /**
     * 指令下发结果
     */
    private boolean success;
    
    /**
     * 失败信息
     */
    private String errorMsg;
    
    /**
     * 消息记录
     */
    private Long messageId;

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public Long getMessageId() {
        return messageId;
    }

    private VcApiResult setResult(boolean result) {
        this.success = result;
        return this;
    }

    private VcApiResult setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }

    private VcApiResult setMessageId(Long messageId) {
        this.messageId = messageId;
        return this;
    }

    public static VcApiResult ofOk(Long messageId) {
        return new VcApiResult().setResult(true).setMessageId(messageId);
    }
    
    public static VcApiResult ofFail(String errorMsg) {
        return new VcApiResult().setResult(false).setErrorMsg(errorMsg);
    }
    
}
