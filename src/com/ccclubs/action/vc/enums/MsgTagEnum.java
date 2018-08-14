package com.ccclubs.action.vc.enums;

public enum MsgTagEnum {
    
    MQTT_STATUS("terminal_status_16", 0x41),
    
    /**
     * CAR_ALARM_FUC_CODE
     * 远程指令结果
     */
    MQTT_REMOTE("terminal_remote_16", 0x43),
    
    /**
     * 订单接收状态回复
     */
    MQTT_ORDER_ACK("terminal_order_16", 0x44);
    
    private String tag;
    private Integer hex;
    MsgTagEnum (String tag, int hex) {
        this.tag = tag;
        this.hex = hex;
    }
    public String getTag() {
        return tag;
    }
    public Integer getHex() {
        return hex;
    }
    
    public static MsgTagEnum getByTag(String tag) {
        for (MsgTagEnum t : MsgTagEnum.values()) {
            if (t.getTag().equals(tag)) {
                return t;
            }
        }
        return null;
    }

}
