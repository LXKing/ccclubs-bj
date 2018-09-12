package com.ccclubs.param;

import java.io.Serializable;

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
    // app版本
    public String appVersion;

    public String getAccess_token() {
        if (access_token == null) {
            return "";
        }
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getAppVersion() {
        if (appVersion == null) {
            return "";
        }
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

}
