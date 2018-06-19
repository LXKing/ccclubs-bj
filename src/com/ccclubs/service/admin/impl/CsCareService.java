package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsCareDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCare;
import com.ccclubs.service.admin.ICsCareService;
import com.lazy3q.web.helper.$;

/**
 * 客户关怀的Service实现
 * @author 飞啊飘啊
 */
public class CsCareService implements ICsCareService
{
	ICsCareDao csCareDao;
	

	/**
	 * 获取所有客户关怀
	 * @return
	 */
	public List<CsCare> getCsCareList(Map params,Integer size)
	{
		return csCareDao.getCsCareList(params,size);
	}
	
	/**
	 * 获取客户关怀统计
	 * @return
	 */
	public List getCsCareStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csCareDao.getCsCareStats(params,groups,sums);
	}
	
	/**
	 * 获取客户关怀总数
	 * @return
	 */
	public Long getCsCareCount(Map params)
	{
		return csCareDao.getCsCareCount(params);
	}
	
	/**
	 * 获取客户关怀自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCareEval(String eval,Map params)
	{
		return csCareDao.getCsCareEval(eval,params);
	}

	/**
	 * 获取客户关怀分页
	 * @return
	 */
	public Page getCsCarePage(int page,int size,Map params)
	{
		return csCareDao.getCsCarePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取客户关怀
	 * @param params
	 * @return
	 */
	public CsCare getCsCare(Map params)
	{
		return csCareDao.getCsCare(params);
	}

	/**
	 * 根据ID取客户关怀
	 * @param id
	 * @return
	 */
	public CsCare getCsCareById(Long id)
	{
		return csCareDao.getCsCareById(id);
	}

	/**
	 * 保存客户关怀
	 * @param csCare
	 */
	public CsCare saveCsCare(CsCare csCare)
	{
		return csCareDao.saveCsCare(csCare);
	}
	
	/**
	 * 更新客户关怀
	 * @param csCare
	 */
	public void updateCsCare(CsCare csCare)
	{
		csCareDao.updateCsCare(csCare);
	}
	/**
	 * 更新客户关怀非空字段
	 * @param csCare
	 */
	public void updateCsCare$NotNull(CsCare csCare)
	{
		csCareDao.updateCsCare$NotNull(csCare);
	}
	
	/**
	 * 根据ID删除一个客户关怀
	 * @param id
	 */
	public void deleteCsCareById(Long id)
	{
		csCareDao.deleteCsCareById(id);
	}
	/**
	 * 根据ID逻辑删除一个客户关怀
	 * @param id
	 */
	public void removeCsCareById(Long id)
	{
		csCareDao.removeCsCareById(id);
	}
	
	/**
	 * 根据条件更新客户关怀
	 * @param values
	 * @param params
	 */
	public void updateCsCareByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsCareBy时无条件");
		csCareDao.updateCsCareByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除客户关怀
	 * @param params
	 */
	public void deleteCsCareByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsCareBy时无条件");
		csCareDao.deleteCsCareByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsCareDao getCsCareDao()
	{
		return csCareDao;
	}

	public void setCsCareDao(ICsCareDao csCareDao)
	{
		this.csCareDao = csCareDao;
	}
}