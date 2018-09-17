package com.ccclubs.param;

import java.io.Serializable;
import com.ccclubs.service.common.From;

/**
 * 基本请求参数
 * 
 * @function
 * @author cjb
 * @createTime 2018年9月4日
 */
public class BaseParam implements Serializable {

    private static final long serialVersionUID = 2378684227501324707L;

    // 用户token
    public String access_token;
    //应用名称
    public String appName;
    // app版本
    public String appVersion;
    //数据来源
    public From from;
    
    public String getAccess_token() {
        return access_token;
    }
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
    public String getAppName() {
        return appName;
    }
    public void setAppName(String appName) {
        this.appName = appName;
    }
    public String getAppVersion() {
        return appVersion;
    }
    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
    public From getFrom() {
        return from;
    }
    public void setFrom(From from) {
        this.from = from;
    }
    
}
