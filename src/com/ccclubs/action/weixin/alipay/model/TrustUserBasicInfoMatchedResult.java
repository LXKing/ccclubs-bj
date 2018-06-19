package com.ccclubs.action.weixin.alipay.model;

import java.io.Serializable;

/**
 * 姓名与身份证与支付宝是否匹配
 * @author wenfeng.cenwf
 * @version 2014年12月19日 下午5:18:48 
 */
public class TrustUserBasicInfoMatchedResult implements Serializable {

    private static final long serialVersionUID = 6948100043875329805L;

    public final static Short MATCHED          = 1;

    public final static Short NOT_MATCHED      = 0;

    public final static Short NOT_FOUND        = -1;

    private Short             nameMathed;

    private Short             certInfoMathed;

    private Short             mobileMathed;

    private Short             emailMathed;

    public Short getNameMathed() {
        return nameMathed;
    }

    public void setNameMathed(Short nameMathed) {
        this.nameMathed = nameMathed;
    }

    public Short getCertInfoMathed() {
        return certInfoMathed;
    }

    public void setCertInfoMathed(Short certInfoMathed) {
        this.certInfoMathed = certInfoMathed;
    }

    public Short getMobileMathed() {
        return mobileMathed;
    }

    public void setMobileMathed(Short mobileMathed) {
        this.mobileMathed = mobileMathed;
    }

    public Short getEmailMathed() {
        return emailMathed;
    }

    public void setEmailMathed(Short emailMathed) {
        this.emailMathed = emailMathed;
    }

}
