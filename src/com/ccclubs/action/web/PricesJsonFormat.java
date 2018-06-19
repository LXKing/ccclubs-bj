package com.ccclubs.action.web;

import java.util.ArrayList;
import java.util.List;

import com.ccclubs.model.CsPrice;

public class PricesJsonFormat {
	/**
	 * 车型Id
	 */
	public Long vehicleTypeId = 0l;
	/**
	 * 车型名称
	 */
	public String vehicleTypeName = "";
	/**
	 * 价格列表
	 */
	public List<CsPrice> prices = new ArrayList<CsPrice>();
}
