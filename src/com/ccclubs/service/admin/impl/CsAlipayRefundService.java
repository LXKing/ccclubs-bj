package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsAlipayRefundDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsAlipayRefund;
import com.ccclubs.service.admin.ICsAlipayRefundService;
import com.lazy3q.web.helper.$;

/**
 * 接口退款的Service实现
 * @author 飞啊飘啊
 */
public class CsAlipayRefundService implements ICsAlipayRefundService
{
	ICsAlipayRefundDao csAlipayRefundDao;
	

	/**
	 * 获取所有接口退款
	 * @return
	 */
	public List<CsAlipayRefund> getCsAlipayRefundList(Map params,Integer size)
	{
		return csAlipayRefundDao.getCsAlipayRefundList(params,size);
	}
	
	/**
	 * 获取接口退款统计
	 * @return
	 */
	public List getCsAlipayRefundStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csAlipayRefundDao.getCsAlipayRefundStats(params,groups,sums);
	}
	
	/**
	 * 获取接口退款总数
	 * @return
	 */
	public Long getCsAlipayRefundCount(Map params)
	{
		return csAlipayRefundDao.getCsAlipayRefundCount(params);
	}
	
	/**
	 * 获取接口退款自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsAlipayRefundEval(String eval,Map params)
	{
		return csAlipayRefundDao.getCsAlipayRefundEval(eval,params);
	}

	/**
	 * 获取接口退款分页
	 * @return
	 */
	public Page getCsAlipayRefundPage(int page,int size,Map params)
	{
		return csAlipayRefundDao.getCsAlipayRefundPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取接口退款
	 * @param params
	 * @return
	 */
	public CsAlipayRefund getCsAlipayRefund(Map params)
	{
		return csAlipayRefundDao.getCsAlipayRefund(params);
	}

	/**
	 * 根据ID取接口退款
	 * @param id
	 * @return
	 */
	public CsAlipayRefund getCsAlipayRefundById(Long id)
	{
		return csAlipayRefundDao.getCsAlipayRefundById(id);
	}

	/**
	 * 保存接口退款
	 * @param csAlipayRefund
	 */
	public CsAlipayRefund saveCsAlipayRefund(CsAlipayRefund csAlipayRefund)
	{
		return csAlipayRefundDao.saveCsAlipayRefund(csAlipayRefund);
	}
	
	/**
	 * 更新接口退款
	 * @param csAlipayRefund
	 */
	public void updateCsAlipayRefund(CsAlipayRefund csAlipayRefund)
	{
		csAlipayRefundDao.updateCsAlipayRefund(csAlipayRefund);
	}
	/**
	 * 更新接口退款非空字段
	 * @param csAlipayRefund
	 */
	public void updateCsAlipayRefund$NotNull(CsAlipayRefund csAlipayRefund)
	{
		csAlipayRefundDao.updateCsAlipayRefund$NotNull(csAlipayRefund);
	}
	
	/**
	 * 根据ID删除一个接口退款
	 * @param id
	 */
	public void deleteCsAlipayRefundById(Long id)
	{
		csAlipayRefundDao.deleteCsAlipayRefundById(id);
	}
	/**
	 * 根据ID逻辑删除一个接口退款
	 * @param id
	 */
	public void removeCsAlipayRefundById(Long id)
	{
		csAlipayRefundDao.removeCsAlipayRefundById(id);
	}
	
	/**
	 * 根据条件更新接口退款
	 * @param values
	 * @param params
	 */
	public void updateCsAlipayRefundByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsAlipayRefundBy时无条件");
		csAlipayRefundDao.updateCsAlipayRefundByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除接口退款
	 * @param params
	 */
	public void deleteCsAlipayRefundByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsAlipayRefundBy时无条件");
		csAlipayRefundDao.deleteCsAlipayRefundByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsAlipayRefundDao getCsAlipayRefundDao()
	{
		return csAlipayRefundDao;
	}

	public void setCsAlipayRefundDao(ICsAlipayRefundDao csAlipayRefundDao)
	{
		this.csAlipayRefundDao = csAlipayRefundDao;
	}
}