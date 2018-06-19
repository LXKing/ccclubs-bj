package com.ccclubs.service.common;

import com.ccclubs.model.CsGift;
import com.ccclubs.model.CsOrderDetail;

public interface IPackPriceScript {
	
	/**
	 * 返回false表示该优惠项不能使用
	 * @param modifyGift
	 * @param csOrderDetail
	 * @return
	 * @throws Exception
	 */
	Boolean execute(CsGift gift,final CsOrderDetail csOrderDetail) throws Exception;
	
}
