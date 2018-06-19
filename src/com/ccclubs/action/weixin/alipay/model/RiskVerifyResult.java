package com.ccclubs.action.weixin.alipay.model;

/**
 * 高危校验 
 * @author wenfeng.cenwf
 * @version 2014年12月11日 下午7:28:52 
 */
public class RiskVerifyResult extends BasicVerifyResult {

    public static final String IS_NOT_RISK      = "0.0";               //否
    public static final String IS_RISK          = "10.0";              //是
    public static final String NOT_SURE         = "-1.0";              //不确定

    private static final long  serialVersionUID = 8331029255815623695L;
}
