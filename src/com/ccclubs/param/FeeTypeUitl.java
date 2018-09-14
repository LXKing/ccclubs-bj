package com.ccclubs.param;

import com.ccclubs.model.CsFeeTypeSet;
import com.ccclubs.model.CsOrder;

public class FeeTypeUitl {
    
    public static Long getUserType(Long feeType, CsFeeTypeSet csFeeTypeSet, CsOrder oldOrder){
        //////////////////计费策略校验//////////////////
        if(oldOrder!=null)//默认以原订单计费类型
            feeType = oldOrder.getCsoFeeType();
        if(feeType==null || feeType.longValue()==0l)
            feeType = null;
        
        //检查会员的计费类型是否是当前地区所接受的计费类型
        if(csFeeTypeSet!=null && csFeeTypeSet.getCsftsFeeType().indexOf("#"+feeType+"#")==-1)
            feeType = csFeeTypeSet.getCsftsDefault();
        
        return feeType;
    }
    
}
