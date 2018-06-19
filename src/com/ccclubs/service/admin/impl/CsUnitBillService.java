package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsUnitBillDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsUnitBill;
import com.ccclubs.service.admin.ICsUnitBillService;
import com.lazy3q.web.helper.$;

/**
 * 企业对账的Service实现
 * @author Joel
 */
public class CsUnitBillService implements ICsUnitBillService
{
	ICsUnitBillDao csUnitBillDao;
	

	/**
	 * 获取所有企业对账
	 * @return
	 */
	public List<CsUnitBill> getCsUnitBillList(Map params,Integer size)
	{
		return csUnitBillDao.getCsUnitBillList(params,size);
	}
	
	/**
	 * 获取企业对账统计
	 * @return
	 */
	public List getCsUnitBillStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csUnitBillDao.getCsUnitBillStats(params,groups,sums);
	}
	
	/**
	 * 获取企业对账总数
	 * @return
	 */
	public Long getCsUnitBillCount(Map params)
	{
		return csUnitBillDao.getCsUnitBillCount(params);
	}
	
	/**
	 * 获取企业对账自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsUnitBillEval(String eval,Map params)
	{
		return csUnitBillDao.getCsUnitBillEval(eval,params);
	}

	/**
	 * 获取企业对账分页
	 * @return
	 */
	public Page getCsUnitBillPage(int page,int size,Map params)
	{
		return csUnitBillDao.getCsUnitBillPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取企业对账
	 * @param params
	 * @return
	 */
	public CsUnitBill getCsUnitBill(Map params)
	{
		return csUnitBillDao.getCsUnitBill(params);
	}

	/**
	 * 根据ID取企业对账
	 * @param id
	 * @return
	 */
	public CsUnitBill getCsUnitBillById(Long id)
	{
		return csUnitBillDao.getCsUnitBillById(id);
	}

	/**
	 * 保存企业对账
	 * @param csUnitBill
	 */
	public CsUnitBill saveCsUnitBill(CsUnitBill csUnitBill)
	{
		return csUnitBillDao.saveCsUnitBill(csUnitBill);
	}
	
	/**
	 * 更新企业对账
	 * @param csUnitBill
	 */
	public void updateCsUnitBill(CsUnitBill csUnitBill)
	{
		csUnitBillDao.updateCsUnitBill(csUnitBill);
	}
	/**
	 * 更新企业对账非空字段
	 * @param csUnitBill
	 */
	public void updateCsUnitBill$NotNull(CsUnitBill csUnitBill)
	{
		csUnitBillDao.updateCsUnitBill$NotNull(csUnitBill);
	}
	
	/**
	 * 根据ID删除一个企业对账
	 * @param id
	 */
	public void deleteCsUnitBillById(Long id)
	{
		csUnitBillDao.deleteCsUnitBillById(id);
	}
	
	/**
	 * 根据ID逻辑删除一个企业对账
	 * @param id
	 */
	public void removeCsUnitBillById(Long id)
	{
		csUnitBillDao.removeCsUnitBillById(id);
	}
	
	/**
	 * 根据条件更新企业对账
	 * @param values
	 * @param params
	 */
	public void updateCsUnitBillByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsUnitBillBy时无条件");
		csUnitBillDao.updateCsUnitBillByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除企业对账
	 * @param params
	 */
	public void deleteCsUnitBillByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsUnitBillBy时无条件");
		csUnitBillDao.deleteCsUnitBillByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsUnitBillDao getCsUnitBillDao()
	{
		return csUnitBillDao;
	}

	public void setCsUnitBillDao(ICsUnitBillDao csUnitBillDao)
	{
		this.csUnitBillDao = csUnitBillDao;
	}
}