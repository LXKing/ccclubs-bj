package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsLuckyDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsLucky;
import com.ccclubs.service.admin.ICsLuckyService;
import com.lazy3q.web.helper.$;

/**
 * 抽奖活动的Service实现
 * @author 飞啊飘啊
 */
public class CsLuckyService implements ICsLuckyService
{
	ICsLuckyDao csLuckyDao;
	

	/**
	 * 获取所有抽奖活动
	 * @return
	 */
	public List<CsLucky> getCsLuckyList(Map params,Integer size)
	{
		return csLuckyDao.getCsLuckyList(params,size);
	}
	
	/**
	 * 获取抽奖活动统计
	 * @return
	 */
	public List getCsLuckyStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csLuckyDao.getCsLuckyStats(params,groups,sums);
	}
	
	/**
	 * 获取抽奖活动总数
	 * @return
	 */
	public Long getCsLuckyCount(Map params)
	{
		return csLuckyDao.getCsLuckyCount(params);
	}
	
	/**
	 * 获取抽奖活动自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsLuckyEval(String eval,Map params)
	{
		return csLuckyDao.getCsLuckyEval(eval,params);
	}

	/**
	 * 获取抽奖活动分页
	 * @return
	 */
	public Page getCsLuckyPage(int page,int size,Map params)
	{
		return csLuckyDao.getCsLuckyPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取抽奖活动
	 * @param params
	 * @return
	 */
	public CsLucky getCsLucky(Map params)
	{
		return csLuckyDao.getCsLucky(params);
	}

	/**
	 * 根据ID取抽奖活动
	 * @param id
	 * @return
	 */
	public CsLucky getCsLuckyById(Long id)
	{
		return csLuckyDao.getCsLuckyById(id);
	}

	/**
	 * 保存抽奖活动
	 * @param csLucky
	 */
	public CsLucky saveCsLucky(CsLucky csLucky)
	{
		return csLuckyDao.saveCsLucky(csLucky);
	}
	
	/**
	 * 更新抽奖活动
	 * @param csLucky
	 */
	public void updateCsLucky(CsLucky csLucky)
	{
		csLuckyDao.updateCsLucky(csLucky);
	}
	/**
	 * 更新抽奖活动非空字段
	 * @param csLucky
	 */
	public void updateCsLucky$NotNull(CsLucky csLucky)
	{
		csLuckyDao.updateCsLucky$NotNull(csLucky);
	}
	
	/**
	 * 根据ID删除一个抽奖活动
	 * @param id
	 */
	public void deleteCsLuckyById(Long id)
	{
		csLuckyDao.deleteCsLuckyById(id);
	}
	/**
	 * 根据ID逻辑删除一个抽奖活动
	 * @param id
	 */
	public void removeCsLuckyById(Long id)
	{
		csLuckyDao.removeCsLuckyById(id);
	}
	
	/**
	 * 根据条件更新抽奖活动
	 * @param values
	 * @param params
	 */
	public void updateCsLuckyByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsLuckyBy时无条件");
		csLuckyDao.updateCsLuckyByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除抽奖活动
	 * @param params
	 */
	public void deleteCsLuckyByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsLuckyBy时无条件");
		csLuckyDao.deleteCsLuckyByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsLuckyDao getCsLuckyDao()
	{
		return csLuckyDao;
	}

	public void setCsLuckyDao(ICsLuckyDao csLuckyDao)
	{
		this.csLuckyDao = csLuckyDao;
	}
}