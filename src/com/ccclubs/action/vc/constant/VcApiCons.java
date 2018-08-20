package com.ccclubs.action.vc.constant;

/**
 * 车机中心对外api请求url常量
 * @author Administrator
 * 2018-8-8
 */
public class VcApiCons {
    /**
     * 车机中心接口响应成功码
     */
    public static final int VC_API_CODE_SUCCESS = 100000;
    /**
     * 车机中心接口响应失败码
     */
    public static final int VC_API_CODE_FAIL = 101022;
    /**
     * 车机中心指令应答执行成功码
     */
    public static final int VC_CMD_CODE_SUCCESS = 1000000;
    
    /**
     * 车机中心接口应答执行失败码
     */
    public static final int VC_CMD_CODE_FAIL = 1001022;
    
    /**
     * 简单指令下发
     */
    public static final String VC_API_CMD_SIMPLE = "/command/sendSimpleCmd";
    
    /**
     * 车辆注册
     */
    public static final String VC_API_CAR_REGISTER = "/operate/vehicleRegister";
    
    /**
     * 车辆基本信息修改
     */
    public static final String VC_API_CAR_INFO_UPDATE = "/operate/vehicleModify";
    
    /**
     * 订单下发
     */
    public static final String VC_API_CAR_ORDER = "/command/issueAuthOrderData";
    
    /**
     * 终端车辆绑定
     */
    public static final String VC_API_CAR_BIND_TER = "/operate/bindVehicle";
    
    /**
     * 终端车辆解除绑定
     */
    public static final String VC_API_CAR_UNBIND_TER = "/operate/unbindVehicle";
}
