package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsRepairAdrDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsRepairAdr;
import com.ccclubs.service.admin.ICsRepairAdrService;
import com.lazy3q.web.helper.$;

/**
 * 维修地点的Service实现
 * @author 飞啊飘啊
 */
public class CsRepairAdrService implements ICsRepairAdrService
{
	ICsRepairAdrDao csRepairAdrDao;
	

	/**
	 * 获取所有维修地点
	 * @return
	 */
	public List<CsRepairAdr> getCsRepairAdrList(Map params,Integer size)
	{
		return csRepairAdrDao.getCsRepairAdrList(params,size);
	}
	
	/**
	 * 获取维修地点统计
	 * @return
	 */
	public List getCsRepairAdrStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csRepairAdrDao.getCsRepairAdrStats(params,groups,sums);
	}
	
	/**
	 * 获取维修地点总数
	 * @return
	 */
	public Long getCsRepairAdrCount(Map params)
	{
		return csRepairAdrDao.getCsRepairAdrCount(params);
	}
	
	/**
	 * 获取维修地点自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsRepairAdrEval(String eval,Map params)
	{
		return csRepairAdrDao.getCsRepairAdrEval(eval,params);
	}

	/**
	 * 获取维修地点分页
	 * @return
	 */
	public Page getCsRepairAdrPage(int page,int size,Map params)
	{
		return csRepairAdrDao.getCsRepairAdrPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取维修地点
	 * @param params
	 * @return
	 */
	public CsRepairAdr getCsRepairAdr(Map params)
	{
		return csRepairAdrDao.getCsRepairAdr(params);
	}

	/**
	 * 根据ID取维修地点
	 * @param id
	 * @return
	 */
	public CsRepairAdr getCsRepairAdrById(Long id)
	{
		return csRepairAdrDao.getCsRepairAdrById(id);
	}

	/**
	 * 保存维修地点
	 * @param csRepairAdr
	 */
	public CsRepairAdr saveCsRepairAdr(CsRepairAdr csRepairAdr)
	{
		return csRepairAdrDao.saveCsRepairAdr(csRepairAdr);
	}
	
	/**
	 * 更新维修地点
	 * @param csRepairAdr
	 */
	public void updateCsRepairAdr(CsRepairAdr csRepairAdr)
	{
		csRepairAdrDao.updateCsRepairAdr(csRepairAdr);
	}
	/**
	 * 更新维修地点非空字段
	 * @param csRepairAdr
	 */
	public void updateCsRepairAdr$NotNull(CsRepairAdr csRepairAdr)
	{
		csRepairAdrDao.updateCsRepairAdr$NotNull(csRepairAdr);
	}
	
	/**
	 * 根据ID删除一个维修地点
	 * @param id
	 */
	public void deleteCsRepairAdrById(Long id)
	{
		csRepairAdrDao.deleteCsRepairAdrById(id);
	}
	
	/**
	 * 根据条件更新维修地点
	 * @param values
	 * @param params
	 */
	public void updateCsRepairAdrByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsRepairAdrBy时无条件");
		csRepairAdrDao.updateCsRepairAdrByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除维修地点
	 * @param params
	 */
	public void deleteCsRepairAdrByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsRepairAdrBy时无条件");
		csRepairAdrDao.deleteCsRepairAdrByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsRepairAdrDao getCsRepairAdrDao()
	{
		return csRepairAdrDao;
	}

	public void setCsRepairAdrDao(ICsRepairAdrDao csRepairAdrDao)
	{
		this.csRepairAdrDao = csRepairAdrDao;
	}
}