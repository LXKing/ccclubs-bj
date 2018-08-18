package com.ccclubs.action.vc.enums;

/**
 * 车机中心-北京出行：车辆颜色映射
 * @author Administrator
 *
 */
public enum VcColorMap {
    /**
     * 黑色
     */
    BLACK(0, 11),
    /**
     * 白色
     */
    WHITE(1, 12),
    /**
     * 红色
     */
    RED(2, 13),
    /**
     * 绿色
     */
    GREEN(3, 14),
    /**
     * 黄色
     */
    YELLOW(4, 15),
    /**
     * 橙色
     */
    ORANGE(5, 16),
    /**
     * 银色
     */
    SILVER(6, 17),
    /**
     * 蓝色
     */
    BLUE(7, 18),
    /**
     * 深蓝
     */
    DARK_BULE(8, 19),
    /**
     * 深灰
     */
    DARK_GRAY(9, 20),
    /**
     * 深红
     */
    DARK_RED(10, 21);
    
    /**
     * 北京平台车身颜色码
     */
    private Integer bjColorCode;
    /**
     * 车机中心车身颜色码
     */
    private Integer vcColorCode;
    VcColorMap(int bjColorCode, int vcColorCode) {
        this.bjColorCode = bjColorCode;
        this.vcColorCode = vcColorCode;
    }
    public Integer getBjColorCode() {
        return bjColorCode;
    }
    public Integer getVcColorCode() {
        return vcColorCode;
    }
    
    /**
     * 根据北京平台车辆颜色代码获取车辆颜色映射
     * @param bjColorCode
     * @return
     */
    public static VcColorMap getByBjColorCode(Integer bjColorCode) {
        for (VcColorMap color: VcColorMap.values()) {
            if (color.getBjColorCode() == bjColorCode) {
                return color;
            }
        }
        return null;
    }
}
