package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ITbAbAffirmDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.TbAbAffirm;
import com.ccclubs.service.admin.ITbAbAffirmService;
import com.lazy3q.web.helper.$;

/**
 * 异借确认的Service实现
 * @author 飞啊飘啊
 */
public class TbAbAffirmService implements ITbAbAffirmService
{
	ITbAbAffirmDao tbAbAffirmDao;
	

	/**
	 * 获取所有异借确认
	 * @return
	 */
	public List<TbAbAffirm> getTbAbAffirmList(Map params,Integer size)
	{
		return tbAbAffirmDao.getTbAbAffirmList(params,size);
	}
	
	/**
	 * 获取异借确认统计
	 * @return
	 */
	public List getTbAbAffirmStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return tbAbAffirmDao.getTbAbAffirmStats(params,groups,sums);
	}
	
	/**
	 * 获取异借确认总数
	 * @return
	 */
	public Long getTbAbAffirmCount(Map params)
	{
		return tbAbAffirmDao.getTbAbAffirmCount(params);
	}
	
	/**
	 * 获取异借确认自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getTbAbAffirmEval(String eval,Map params)
	{
		return tbAbAffirmDao.getTbAbAffirmEval(eval,params);
	}

	/**
	 * 获取异借确认分页
	 * @return
	 */
	public Page getTbAbAffirmPage(int page,int size,Map params)
	{
		return tbAbAffirmDao.getTbAbAffirmPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取异借确认
	 * @param params
	 * @return
	 */
	public TbAbAffirm getTbAbAffirm(Map params)
	{
		return tbAbAffirmDao.getTbAbAffirm(params);
	}

	/**
	 * 根据ID取异借确认
	 * @param id
	 * @return
	 */
	public TbAbAffirm getTbAbAffirmById(Long id)
	{
		return tbAbAffirmDao.getTbAbAffirmById(id);
	}

	/**
	 * 保存异借确认
	 * @param tbAbAffirm
	 */
	public TbAbAffirm saveTbAbAffirm(TbAbAffirm tbAbAffirm)
	{
		return tbAbAffirmDao.saveTbAbAffirm(tbAbAffirm);
	}
	
	/**
	 * 更新异借确认
	 * @param tbAbAffirm
	 */
	public void updateTbAbAffirm(TbAbAffirm tbAbAffirm)
	{
		tbAbAffirmDao.updateTbAbAffirm(tbAbAffirm);
	}
	/**
	 * 更新异借确认非空字段
	 * @param tbAbAffirm
	 */
	public void updateTbAbAffirm$NotNull(TbAbAffirm tbAbAffirm)
	{
		tbAbAffirmDao.updateTbAbAffirm$NotNull(tbAbAffirm);
	}
	
	/**
	 * 根据ID删除一个异借确认
	 * @param id
	 */
	public void deleteTbAbAffirmById(Long id)
	{
		tbAbAffirmDao.deleteTbAbAffirmById(id);
	}
	/**
	 * 根据ID逻辑删除一个异借确认
	 * @param id
	 */
	public void removeTbAbAffirmById(Long id)
	{
		tbAbAffirmDao.removeTbAbAffirmById(id);
	}
	
	/**
	 * 根据条件更新异借确认
	 * @param values
	 * @param params
	 */
	public void updateTbAbAffirmByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateTbAbAffirmBy时无条件");
		tbAbAffirmDao.updateTbAbAffirmByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除异借确认
	 * @param params
	 */
	public void deleteTbAbAffirmByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteTbAbAffirmBy时无条件");
		tbAbAffirmDao.deleteTbAbAffirmByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ITbAbAffirmDao getTbAbAffirmDao()
	{
		return tbAbAffirmDao;
	}

	public void setTbAbAffirmDao(ITbAbAffirmDao tbAbAffirmDao)
	{
		this.tbAbAffirmDao = tbAbAffirmDao;
	}
}