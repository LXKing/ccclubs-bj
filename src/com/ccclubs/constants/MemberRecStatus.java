package com.ccclubs.constants;

public interface MemberRecStatus {
    public static final short NEVER_REC = 0;//等待认证
    public static final short REC_PASS = 1;//认证成功
    public static final short WAIT_CHECK = 2;//等待审核
    public static final short REC_FAIL = 3;//认证失败
}
