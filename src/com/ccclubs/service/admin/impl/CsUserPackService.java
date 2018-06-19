package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsUserPackDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsUserPack;
import com.ccclubs.service.admin.ICsUserPackService;
import com.lazy3q.web.helper.$;

/**
 * 用户套餐的Service实现
 * @author 飞啊飘啊
 */
public class CsUserPackService implements ICsUserPackService
{
	ICsUserPackDao csUserPackDao;
	

	/**
	 * 获取所有用户套餐
	 * @return
	 */
	public List<CsUserPack> getCsUserPackList(Map params,Integer size)
	{
		return csUserPackDao.getCsUserPackList(params,size);
	}
	
	/**
	 * 获取用户套餐统计
	 * @return
	 */
	public List getCsUserPackStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csUserPackDao.getCsUserPackStats(params,groups,sums);
	}
	
	/**
	 * 获取用户套餐总数
	 * @return
	 */
	public Long getCsUserPackCount(Map params)
	{
		return csUserPackDao.getCsUserPackCount(params);
	}
	
	/**
	 * 获取用户套餐自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsUserPackEval(String eval,Map params)
	{
		return csUserPackDao.getCsUserPackEval(eval,params);
	}

	/**
	 * 获取用户套餐分页
	 * @return
	 */
	public Page getCsUserPackPage(int page,int size,Map params)
	{
		return csUserPackDao.getCsUserPackPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取用户套餐
	 * @param params
	 * @return
	 */
	public CsUserPack getCsUserPack(Map params)
	{
		return csUserPackDao.getCsUserPack(params);
	}

	/**
	 * 根据ID取用户套餐
	 * @param id
	 * @return
	 */
	public CsUserPack getCsUserPackById(Long id)
	{
		return csUserPackDao.getCsUserPackById(id);
	}

	/**
	 * 保存用户套餐
	 * @param csUserPack
	 */
	public CsUserPack saveCsUserPack(CsUserPack csUserPack)
	{
		return csUserPackDao.saveCsUserPack(csUserPack);
	}
	
	/**
	 * 更新用户套餐
	 * @param csUserPack
	 */
	public void updateCsUserPack(CsUserPack csUserPack)
	{
		csUserPackDao.updateCsUserPack(csUserPack);
	}
	/**
	 * 更新用户套餐非空字段
	 * @param csUserPack
	 */
	public void updateCsUserPack$NotNull(CsUserPack csUserPack)
	{
		csUserPackDao.updateCsUserPack$NotNull(csUserPack);
	}
	
	/**
	 * 根据ID删除一个用户套餐
	 * @param id
	 */
	public void deleteCsUserPackById(Long id)
	{
		csUserPackDao.deleteCsUserPackById(id);
	}
	/**
	 * 根据ID逻辑删除一个用户套餐
	 * @param id
	 */
	public void removeCsUserPackById(Long id)
	{
		csUserPackDao.removeCsUserPackById(id);
	}
	
	/**
	 * 根据条件更新用户套餐
	 * @param values
	 * @param params
	 */
	public void updateCsUserPackByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsUserPackBy时无条件");
		csUserPackDao.updateCsUserPackByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除用户套餐
	 * @param params
	 */
	public void deleteCsUserPackByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsUserPackBy时无条件");
		csUserPackDao.deleteCsUserPackByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsUserPackDao getCsUserPackDao()
	{
		return csUserPackDao;
	}

	public void setCsUserPackDao(ICsUserPackDao csUserPackDao)
	{
		this.csUserPackDao = csUserPackDao;
	}
}