package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsPackDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsPack;
import com.ccclubs.service.admin.ICsPackService;
import com.lazy3q.web.helper.$;

/**
 * 系统套餐的Service实现
 * @author 飞啊飘啊
 */
public class CsPackService implements ICsPackService
{
	ICsPackDao csPackDao;
	

	/**
	 * 获取所有系统套餐
	 * @return
	 */
	public List<CsPack> getCsPackList(Map params,Integer size)
	{
		return csPackDao.getCsPackList(params,size);
	}
	
	/**
	 * 获取系统套餐统计
	 * @return
	 */
	public List getCsPackStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csPackDao.getCsPackStats(params,groups,sums);
	}
	
	/**
	 * 获取系统套餐总数
	 * @return
	 */
	public Long getCsPackCount(Map params)
	{
		return csPackDao.getCsPackCount(params);
	}
	
	/**
	 * 获取系统套餐自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsPackEval(String eval,Map params)
	{
		return csPackDao.getCsPackEval(eval,params);
	}

	/**
	 * 获取系统套餐分页
	 * @return
	 */
	public Page getCsPackPage(int page,int size,Map params)
	{
		return csPackDao.getCsPackPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取系统套餐
	 * @param params
	 * @return
	 */
	public CsPack getCsPack(Map params)
	{
		return csPackDao.getCsPack(params);
	}

	/**
	 * 根据ID取系统套餐
	 * @param id
	 * @return
	 */
	public CsPack getCsPackById(Long id)
	{
		return csPackDao.getCsPackById(id);
	}

	/**
	 * 保存系统套餐
	 * @param csPack
	 */
	public CsPack saveCsPack(CsPack csPack)
	{
		return csPackDao.saveCsPack(csPack);
	}
	
	/**
	 * 更新系统套餐
	 * @param csPack
	 */
	public void updateCsPack(CsPack csPack)
	{
		csPackDao.updateCsPack(csPack);
	}
	/**
	 * 更新系统套餐非空字段
	 * @param csPack
	 */
	public void updateCsPack$NotNull(CsPack csPack)
	{
		csPackDao.updateCsPack$NotNull(csPack);
	}
	
	/**
	 * 根据ID删除一个系统套餐
	 * @param id
	 */
	public void deleteCsPackById(Long id)
	{
		csPackDao.deleteCsPackById(id);
	}
	/**
	 * 根据ID逻辑删除一个系统套餐
	 * @param id
	 */
	public void removeCsPackById(Long id)
	{
		csPackDao.removeCsPackById(id);
	}
	
	/**
	 * 根据条件更新系统套餐
	 * @param values
	 * @param params
	 */
	public void updateCsPackByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsPackBy时无条件");
		csPackDao.updateCsPackByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除系统套餐
	 * @param params
	 */
	public void deleteCsPackByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsPackBy时无条件");
		csPackDao.deleteCsPackByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsPackDao getCsPackDao()
	{
		return csPackDao;
	}

	public void setCsPackDao(ICsPackDao csPackDao)
	{
		this.csPackDao = csPackDao;
	}
}