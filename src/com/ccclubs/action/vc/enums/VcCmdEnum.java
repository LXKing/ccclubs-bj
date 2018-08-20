package com.ccclubs.action.vc.enums;

/**
 * 车机中心控制类指令enum
 * @author yaz
 * @since 2018-8-3
 */
public enum VcCmdEnum {
    /**
     * 开门
     */
    CMD_DOOR_OPEN(0, 2),
    
    /**
     * 关门
     */
    CMD_DOOR_CLOSE(1, 3),
    
    /**
     * 允许启动
     */
    CMD_RUN_ALLOW(2, 4),
    
    /**
     * 禁止启动
     */
    CMD_RUN_NOT_ALLOW(3, 5),
    
    /**
     * 初始化
     */
    CMD_INIT(4, 9),
    
    /**
     * 远程取车
     */
    CMD_GET_CAR(6, 13),
    
    /**
     * 远程还车
     */
    CMD_BACK_CAR(7, 14),
    
    /**
     * 远程升级
     * (不提供升级接口)
     */
    
    /**
     * 远程重启
     */
    CMD_RESTART(9, 12),
    
    /**
     * GPS远程开
     */
    
    /**
     * GPS远程关
     */
    
    /**
     * 读取RFID序列号
     */
    
    /**
     * 设置GPS坐标
     * (无该接口提供) 
     */
    
    /**
     * 查询当前设备的手机号
     */
    
    /**
     * 刷卡器开
     */
    
    /**
     * 刷卡器关
     */
    
    
    /**
     * 远程鸣笛
     */
    CMD_STEAM(17, 6);
    
    /**
     * 开门并允许启动
     */
    
    /**
     * 锁门并禁止启动
     */
   
    /**
     * 北京业务平台对应功能的指令码
     */
    private Integer bjCmdCode;
    /**
     * 车机中心对应功能的指令码
     */
    private Integer vcCmdCode;
    private VcCmdEnum (int bjCmdCode, int vcCmdCode) {
        this.bjCmdCode = bjCmdCode;
        this.vcCmdCode = vcCmdCode;
    }
    
    public Integer getBjCmdCode() {
        return bjCmdCode;
    }
    public Integer getVcCmdCode() {
        return vcCmdCode;
    }
    
    /**
     * 根据北京业务平台的指令码返回与车机中心指令码映射
     * @param bjCmdCode
     * @return
     */
    public static VcCmdEnum getByBjCmdCode(int bjCmdCode) {
        for(VcCmdEnum cmd: VcCmdEnum.values()) {
            if (bjCmdCode == cmd.getBjCmdCode()) {
                return cmd;
            }
        }
        return null;
    }

    
    

}
