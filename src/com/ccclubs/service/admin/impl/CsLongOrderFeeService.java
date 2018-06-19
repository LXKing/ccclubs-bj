package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsLongOrderFeeDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsLongOrderFee;
import com.ccclubs.service.admin.ICsLongOrderFeeService;
import com.lazy3q.web.helper.$;

/**
 * 长单缴费的Service实现
 * @author 飞啊飘啊
 */
public class CsLongOrderFeeService implements ICsLongOrderFeeService
{
	ICsLongOrderFeeDao csLongOrderFeeDao;
	

	/**
	 * 获取所有长单缴费
	 * @return
	 */
	public List<CsLongOrderFee> getCsLongOrderFeeList(Map params,Integer size)
	{
		return csLongOrderFeeDao.getCsLongOrderFeeList(params,size);
	}
	
	/**
	 * 获取长单缴费统计
	 * @return
	 */
	public List getCsLongOrderFeeStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csLongOrderFeeDao.getCsLongOrderFeeStats(params,groups,sums);
	}
	
	/**
	 * 获取长单缴费总数
	 * @return
	 */
	public Long getCsLongOrderFeeCount(Map params)
	{
		return csLongOrderFeeDao.getCsLongOrderFeeCount(params);
	}
	
	/**
	 * 获取长单缴费自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsLongOrderFeeEval(String eval,Map params)
	{
		return csLongOrderFeeDao.getCsLongOrderFeeEval(eval,params);
	}

	/**
	 * 获取长单缴费分页
	 * @return
	 */
	public Page getCsLongOrderFeePage(int page,int size,Map params)
	{
		return csLongOrderFeeDao.getCsLongOrderFeePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取长单缴费
	 * @param params
	 * @return
	 */
	public CsLongOrderFee getCsLongOrderFee(Map params)
	{
		return csLongOrderFeeDao.getCsLongOrderFee(params);
	}

	/**
	 * 根据ID取长单缴费
	 * @param id
	 * @return
	 */
	public CsLongOrderFee getCsLongOrderFeeById(Long id)
	{
		return csLongOrderFeeDao.getCsLongOrderFeeById(id);
	}

	/**
	 * 保存长单缴费
	 * @param csLongOrderFee
	 */
	public CsLongOrderFee saveCsLongOrderFee(CsLongOrderFee csLongOrderFee)
	{
		return csLongOrderFeeDao.saveCsLongOrderFee(csLongOrderFee);
	}
	
	/**
	 * 更新长单缴费
	 * @param csLongOrderFee
	 */
	public void updateCsLongOrderFee(CsLongOrderFee csLongOrderFee)
	{
		csLongOrderFeeDao.updateCsLongOrderFee(csLongOrderFee);
	}
	/**
	 * 更新长单缴费非空字段
	 * @param csLongOrderFee
	 */
	public void updateCsLongOrderFee$NotNull(CsLongOrderFee csLongOrderFee)
	{
		csLongOrderFeeDao.updateCsLongOrderFee$NotNull(csLongOrderFee);
	}
	
	/**
	 * 根据ID删除一个长单缴费
	 * @param id
	 */
	public void deleteCsLongOrderFeeById(Long id)
	{
		csLongOrderFeeDao.deleteCsLongOrderFeeById(id);
	}
	/**
	 * 根据ID逻辑删除一个长单缴费
	 * @param id
	 */
	public void removeCsLongOrderFeeById(Long id)
	{
		csLongOrderFeeDao.removeCsLongOrderFeeById(id);
	}
	
	/**
	 * 根据条件更新长单缴费
	 * @param values
	 * @param params
	 */
	public void updateCsLongOrderFeeByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsLongOrderFeeBy时无条件");
		csLongOrderFeeDao.updateCsLongOrderFeeByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除长单缴费
	 * @param params
	 */
	public void deleteCsLongOrderFeeByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsLongOrderFeeBy时无条件");
		csLongOrderFeeDao.deleteCsLongOrderFeeByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsLongOrderFeeDao getCsLongOrderFeeDao()
	{
		return csLongOrderFeeDao;
	}

	public void setCsLongOrderFeeDao(ICsLongOrderFeeDao csLongOrderFeeDao)
	{
		this.csLongOrderFeeDao = csLongOrderFeeDao;
	}
}