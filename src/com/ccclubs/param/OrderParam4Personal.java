package com.ccclubs.param;

/**
 * @function 套餐下单参数
 * @author cjb
 * @createTime 2018年9月4日
 */
public class OrderParam4Personal extends OrderParam{

    private static final long serialVersionUID = -6571071778633385894L;
    
    // 套餐id
    private Long mealId;

    public Long getMealId() {
        return mealId;
    }

    public void setMealId(Long mealId) {
        this.mealId = mealId;
    }
    
}
