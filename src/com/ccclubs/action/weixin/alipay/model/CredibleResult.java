package com.ccclubs.action.weixin.alipay.model;

/**
 * 可信结果
 * @author wenfeng.cenwf
 * @version 2014年12月11日 下午7:28:05 
 */
public class CredibleResult extends BasicVerifyResult {

    public static final String IS_NOT_CREDIBLE  = "0.0";               //否
    public static final String IS_CREDIBLE      = "10.0";              //是
    public static final String UNKNOWN          = "-1.0";              //未知

    private static final long  serialVersionUID = 1606857851984931242L;
}
