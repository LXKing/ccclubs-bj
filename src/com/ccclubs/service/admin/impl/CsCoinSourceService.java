package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsCoinSourceDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCoinSource;
import com.ccclubs.service.admin.ICsCoinSourceService;
import com.lazy3q.web.helper.$;

/**
 * 红包来源的Service实现
 * @author Joel
 */
public class CsCoinSourceService implements ICsCoinSourceService
{
	ICsCoinSourceDao csCoinSourceDao;
	

	/**
	 * 获取所有红包来源
	 * @return
	 */
	public List<CsCoinSource> getCsCoinSourceList(Map params,Integer size)
	{
		return csCoinSourceDao.getCsCoinSourceList(params,size);
	}
	
	/**
	 * 获取红包来源统计
	 * @return
	 */
	public List getCsCoinSourceStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csCoinSourceDao.getCsCoinSourceStats(params,groups,sums);
	}
	
	/**
	 * 获取红包来源总数
	 * @return
	 */
	public Long getCsCoinSourceCount(Map params)
	{
		return csCoinSourceDao.getCsCoinSourceCount(params);
	}
	
	/**
	 * 获取红包来源自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCoinSourceEval(String eval,Map params)
	{
		return csCoinSourceDao.getCsCoinSourceEval(eval,params);
	}

	/**
	 * 获取红包来源分页
	 * @return
	 */
	public Page getCsCoinSourcePage(int page,int size,Map params)
	{
		return csCoinSourceDao.getCsCoinSourcePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取红包来源
	 * @param params
	 * @return
	 */
	public CsCoinSource getCsCoinSource(Map params)
	{
		return csCoinSourceDao.getCsCoinSource(params);
	}

	/**
	 * 根据ID取红包来源
	 * @param id
	 * @return
	 */
	public CsCoinSource getCsCoinSourceById(Long id)
	{
		return csCoinSourceDao.getCsCoinSourceById(id);
	}

	/**
	 * 保存红包来源
	 * @param csCoinSource
	 */
	public CsCoinSource saveCsCoinSource(CsCoinSource csCoinSource)
	{
		return csCoinSourceDao.saveCsCoinSource(csCoinSource);
	}
	
	/**
	 * 更新红包来源
	 * @param csCoinSource
	 */
	public void updateCsCoinSource(CsCoinSource csCoinSource)
	{
		csCoinSourceDao.updateCsCoinSource(csCoinSource);
	}
	/**
	 * 更新红包来源非空字段
	 * @param csCoinSource
	 */
	public void updateCsCoinSource$NotNull(CsCoinSource csCoinSource)
	{
		csCoinSourceDao.updateCsCoinSource$NotNull(csCoinSource);
	}
	
	/**
	 * 根据ID删除一个红包来源
	 * @param id
	 */
	public void deleteCsCoinSourceById(Long id)
	{
		csCoinSourceDao.deleteCsCoinSourceById(id);
	}
	
	/**
	 * 根据ID逻辑删除一个红包来源
	 * @param id
	 */
	public void removeCsCoinSourceById(Long id)
	{
		csCoinSourceDao.removeCsCoinSourceById(id);
	}
	
	/**
	 * 根据条件更新红包来源
	 * @param values
	 * @param params
	 */
	public void updateCsCoinSourceByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsCoinSourceBy时无条件");
		csCoinSourceDao.updateCsCoinSourceByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除红包来源
	 * @param params
	 */
	public void deleteCsCoinSourceByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsCoinSourceBy时无条件");
		csCoinSourceDao.deleteCsCoinSourceByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsCoinSourceDao getCsCoinSourceDao()
	{
		return csCoinSourceDao;
	}

	public void setCsCoinSourceDao(ICsCoinSourceDao csCoinSourceDao)
	{
		this.csCoinSourceDao = csCoinSourceDao;
	}
}