package com.ccclubs.action.app.official.dto;

/**
 * app提示信息类
 * @author Administrator
 * date: 2018-6-25
 */
public class AppTip {
    
    /**
     * 提示信息
     */
    private String message;
    
    /**
     * 对应不同的提示类型，给出不同的提示数据ID
     *   如：type=1(用户还有订单未支付)，则ObjectId为对应的订单ID
     */
    private Long objectId;
    
    /**
     * 提示类型
     *   0:用户未完成实名认证
     *   1:用户还有订单待支付
     *   2:
     *   3:
     *   4:
     */
    private Integer type;
    
    public static enum Type {
        /**
         * 未实名认证
         */
        AUTH_REAL, 
        /**
         * 还有订单待支付
         */
        UNDO_ORDER
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
