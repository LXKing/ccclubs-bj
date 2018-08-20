package com.ccclubs.action.vc.dto;

/**
 * 车机中心简单指令响应
 * @author Administrator
 *
 */
public class VcSimpleCmdApiResp {
    /**
     * 返回码
     */
    private Integer code;
    /**
     * 消息
     * success
     */
    private String message;
    
    /**
     * 车机中心接口返回的数据
     */
    private RespData data;
    
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public RespData getData() {
        return data;
    }
    public void setData(RespData data) {
        this.data = data;
    }



    /**
     * 车机中心接口返回的数据
     * @author Administrator
     *
     */
    static class RespData {
        private Long messageId;

        public Long getMessageId() {
            return messageId;
        }

        public void setMessageId(Long messageId) {
            this.messageId = messageId;
        }
    }

}

 
