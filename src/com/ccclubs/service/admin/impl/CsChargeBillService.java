package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsChargeBillDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsChargeBill;
import com.ccclubs.service.admin.ICsChargeBillService;
import com.lazy3q.web.helper.$;

/**
 * 充电订单的Service实现
 * @author Joel
 */
public class CsChargeBillService implements ICsChargeBillService
{
	ICsChargeBillDao csChargeBillDao;
	

	/**
	 * 获取所有充电订单
	 * @return
	 */
	public List<CsChargeBill> getCsChargeBillList(Map params,Integer size)
	{
		return csChargeBillDao.getCsChargeBillList(params,size);
	}
	
	/**
	 * 获取充电订单统计
	 * @return
	 */
	public List getCsChargeBillStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csChargeBillDao.getCsChargeBillStats(params,groups,sums);
	}
	
	/**
	 * 获取充电订单总数
	 * @return
	 */
	public Long getCsChargeBillCount(Map params)
	{
		return csChargeBillDao.getCsChargeBillCount(params);
	}
	
	/**
	 * 获取充电订单自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsChargeBillEval(String eval,Map params)
	{
		return csChargeBillDao.getCsChargeBillEval(eval,params);
	}

	/**
	 * 获取充电订单分页
	 * @return
	 */
	public Page getCsChargeBillPage(int page,int size,Map params)
	{
		return csChargeBillDao.getCsChargeBillPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取充电订单
	 * @param params
	 * @return
	 */
	public CsChargeBill getCsChargeBill(Map params)
	{
		return csChargeBillDao.getCsChargeBill(params);
	}

	/**
	 * 根据ID取充电订单
	 * @param id
	 * @return
	 */
	public CsChargeBill getCsChargeBillById(Long id)
	{
		return csChargeBillDao.getCsChargeBillById(id);
	}

	/**
	 * 保存充电订单
	 * @param csChargeBill
	 */
	public CsChargeBill saveCsChargeBill(CsChargeBill csChargeBill)
	{
		return csChargeBillDao.saveCsChargeBill(csChargeBill);
	}
	
	/**
	 * 更新充电订单
	 * @param csChargeBill
	 */
	public void updateCsChargeBill(CsChargeBill csChargeBill)
	{
		csChargeBillDao.updateCsChargeBill(csChargeBill);
	}
	/**
	 * 更新充电订单非空字段
	 * @param csChargeBill
	 */
	public void updateCsChargeBill$NotNull(CsChargeBill csChargeBill)
	{
		csChargeBillDao.updateCsChargeBill$NotNull(csChargeBill);
	}
	
	/**
	 * 根据ID删除一个充电订单
	 * @param id
	 */
	public void deleteCsChargeBillById(Long id)
	{
		csChargeBillDao.deleteCsChargeBillById(id);
	}
	
	/**
	 * 根据ID逻辑删除一个充电订单
	 * @param id
	 */
	public void removeCsChargeBillById(Long id)
	{
		csChargeBillDao.removeCsChargeBillById(id);
	}
	
	/**
	 * 根据条件更新充电订单
	 * @param values
	 * @param params
	 */
	public void updateCsChargeBillByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsChargeBillBy时无条件");
		csChargeBillDao.updateCsChargeBillByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除充电订单
	 * @param params
	 */
	public void deleteCsChargeBillByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsChargeBillBy时无条件");
		csChargeBillDao.deleteCsChargeBillByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsChargeBillDao getCsChargeBillDao()
	{
		return csChargeBillDao;
	}

	public void setCsChargeBillDao(ICsChargeBillDao csChargeBillDao)
	{
		this.csChargeBillDao = csChargeBillDao;
	}
}