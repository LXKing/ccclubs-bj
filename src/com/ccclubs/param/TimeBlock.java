package com.ccclubs.param;

import java.util.Date;

public class TimeBlock {
    //白天普通计费：分钟计费+小时计费最优惠组合计费
    public static int FEE_DAYTIME = 0;
    //夜租计费：分钟计费+小时计费+夜租计费最优惠组合计费
    public static int FEE_NIGHT1 = 1;
    public static int FEE_NIGHT2 = 2;
    public static int FEE_NIGHT3 = 3;
    
    //开始时间
    private Date startTime;
    //截止时间
    private Date endTime;
    //计费模式
    private int feeMode;
    
    public TimeBlock() {
        super();
    }
    
    public TimeBlock(Date startTime, Date endTime, int feeMode) {
        super();
        this.startTime = startTime;
        this.endTime = endTime;
        this.feeMode = feeMode;
    }
    
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public int getFeeMode() {
        return feeMode;
    }
    public void setFeeMode(int feeMode) {
        this.feeMode = feeMode;
    }
    
}
