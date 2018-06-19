package com.ccclubs.action.weixin.alipay.model;

import java.io.Serializable;

/**
 * 
 * @author wenfeng.cenwf
 * @version 2014年12月11日 下午7:16:36 
 */
public class BasicVerifyResult implements Serializable {
    
    private static final long serialVersionUID = 6923762209171345299L;

    /** 请求是否成功  T/F */
    private String            isSuccess        = "T";

    /** 错误码，当isSuccess为F时有值 */
    private String            errorCode;

    /** 返回码 */
    private String            resultCode;

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
}
