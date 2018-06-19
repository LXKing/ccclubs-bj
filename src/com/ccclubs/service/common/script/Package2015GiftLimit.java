package com.ccclubs.service.common.script;

import java.util.Date;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.model.CsGift;
import com.ccclubs.model.CsOrderDetail;
import com.ccclubs.service.common.IPackPriceScript;
import com.lazy3q.web.helper.$;

/**
 * 2015春节套餐工作日天套餐使用限制
 * 判断套餐中
 * @author uiu
 *
 */
public class Package2015GiftLimit implements IPackPriceScript {
	@Override
	public Boolean execute(CsGift gift, CsOrderDetail csOrderDetail)throws Exception {
		return true;
	}

}
