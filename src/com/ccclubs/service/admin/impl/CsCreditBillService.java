package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsCreditBillDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCreditBill;
import com.ccclubs.service.admin.ICsCreditBillService;
import com.lazy3q.web.helper.$;

/**
 * 信用账单的Service实现
 * @author 飞啊飘啊
 */
public class CsCreditBillService implements ICsCreditBillService
{
	ICsCreditBillDao csCreditBillDao;
	

	/**
	 * 获取所有信用账单
	 * @return
	 */
	public List<CsCreditBill> getCsCreditBillList(Map params,Integer size)
	{
		return csCreditBillDao.getCsCreditBillList(params,size);
	}
	
	/**
	 * 获取信用账单统计
	 * @return
	 */
	public List getCsCreditBillStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csCreditBillDao.getCsCreditBillStats(params,groups,sums);
	}
	
	/**
	 * 获取信用账单总数
	 * @return
	 */
	public Long getCsCreditBillCount(Map params)
	{
		return csCreditBillDao.getCsCreditBillCount(params);
	}
	
	/**
	 * 获取信用账单自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCreditBillEval(String eval,Map params)
	{
		return csCreditBillDao.getCsCreditBillEval(eval,params);
	}

	/**
	 * 获取信用账单分页
	 * @return
	 */
	public Page getCsCreditBillPage(int page,int size,Map params)
	{
		return csCreditBillDao.getCsCreditBillPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取信用账单
	 * @param params
	 * @return
	 */
	public CsCreditBill getCsCreditBill(Map params)
	{
		return csCreditBillDao.getCsCreditBill(params);
	}

	/**
	 * 根据ID取信用账单
	 * @param id
	 * @return
	 */
	public CsCreditBill getCsCreditBillById(Long id)
	{
		return csCreditBillDao.getCsCreditBillById(id);
	}

	/**
	 * 保存信用账单
	 * @param csCreditBill
	 */
	public CsCreditBill saveCsCreditBill(CsCreditBill csCreditBill)
	{
		return csCreditBillDao.saveCsCreditBill(csCreditBill);
	}
	
	/**
	 * 更新信用账单
	 * @param csCreditBill
	 */
	public void updateCsCreditBill(CsCreditBill csCreditBill)
	{
		csCreditBillDao.updateCsCreditBill(csCreditBill);
	}
	/**
	 * 更新信用账单非空字段
	 * @param csCreditBill
	 */
	public void updateCsCreditBill$NotNull(CsCreditBill csCreditBill)
	{
		csCreditBillDao.updateCsCreditBill$NotNull(csCreditBill);
	}
	
	/**
	 * 根据ID删除一个信用账单
	 * @param id
	 */
	public void deleteCsCreditBillById(Long id)
	{
		csCreditBillDao.deleteCsCreditBillById(id);
	}
	/**
	 * 根据ID逻辑删除一个信用账单
	 * @param id
	 */
	public void removeCsCreditBillById(Long id)
	{
		csCreditBillDao.removeCsCreditBillById(id);
	}
	
	/**
	 * 根据条件更新信用账单
	 * @param values
	 * @param params
	 */
	public void updateCsCreditBillByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsCreditBillBy时无条件");
		csCreditBillDao.updateCsCreditBillByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除信用账单
	 * @param params
	 */
	public void deleteCsCreditBillByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsCreditBillBy时无条件");
		csCreditBillDao.deleteCsCreditBillByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsCreditBillDao getCsCreditBillDao()
	{
		return csCreditBillDao;
	}

	public void setCsCreditBillDao(ICsCreditBillDao csCreditBillDao)
	{
		this.csCreditBillDao = csCreditBillDao;
	}
}