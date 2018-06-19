package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsMaintainDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsMaintain;
import com.ccclubs.service.admin.ICsMaintainService;
import com.lazy3q.web.helper.$;

/**
 * 车辆保养的Service实现
 * @author 飞啊飘啊
 */
public class CsMaintainService implements ICsMaintainService
{
	ICsMaintainDao csMaintainDao;
	

	/**
	 * 获取所有车辆保养
	 * @return
	 */
	public List<CsMaintain> getCsMaintainList(Map params,Integer size)
	{
		return csMaintainDao.getCsMaintainList(params,size);
	}
	
	/**
	 * 获取车辆保养统计
	 * @return
	 */
	public List getCsMaintainStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csMaintainDao.getCsMaintainStats(params,groups,sums);
	}
	
	/**
	 * 获取车辆保养总数
	 * @return
	 */
	public Long getCsMaintainCount(Map params)
	{
		return csMaintainDao.getCsMaintainCount(params);
	}
	
	/**
	 * 获取车辆保养自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsMaintainEval(String eval,Map params)
	{
		return csMaintainDao.getCsMaintainEval(eval,params);
	}

	/**
	 * 获取车辆保养分页
	 * @return
	 */
	public Page getCsMaintainPage(int page,int size,Map params)
	{
		return csMaintainDao.getCsMaintainPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取车辆保养
	 * @param params
	 * @return
	 */
	public CsMaintain getCsMaintain(Map params)
	{
		return csMaintainDao.getCsMaintain(params);
	}

	/**
	 * 根据ID取车辆保养
	 * @param id
	 * @return
	 */
	public CsMaintain getCsMaintainById(Long id)
	{
		return csMaintainDao.getCsMaintainById(id);
	}

	/**
	 * 保存车辆保养
	 * @param csMaintain
	 */
	public CsMaintain saveCsMaintain(CsMaintain csMaintain)
	{
		return csMaintainDao.saveCsMaintain(csMaintain);
	}
	
	/**
	 * 更新车辆保养
	 * @param csMaintain
	 */
	public void updateCsMaintain(CsMaintain csMaintain)
	{
		csMaintainDao.updateCsMaintain(csMaintain);
	}
	/**
	 * 更新车辆保养非空字段
	 * @param csMaintain
	 */
	public void updateCsMaintain$NotNull(CsMaintain csMaintain)
	{
		csMaintainDao.updateCsMaintain$NotNull(csMaintain);
	}
	
	/**
	 * 根据ID删除一个车辆保养
	 * @param id
	 */
	public void deleteCsMaintainById(Long id)
	{
		csMaintainDao.deleteCsMaintainById(id);
	}
	/**
	 * 根据ID逻辑删除一个车辆保养
	 * @param id
	 */
	public void removeCsMaintainById(Long id)
	{
		csMaintainDao.removeCsMaintainById(id);
	}
	
	/**
	 * 根据条件更新车辆保养
	 * @param values
	 * @param params
	 */
	public void updateCsMaintainByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsMaintainBy时无条件");
		csMaintainDao.updateCsMaintainByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除车辆保养
	 * @param params
	 */
	public void deleteCsMaintainByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsMaintainBy时无条件");
		csMaintainDao.deleteCsMaintainByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsMaintainDao getCsMaintainDao()
	{
		return csMaintainDao;
	}

	public void setCsMaintainDao(ICsMaintainDao csMaintainDao)
	{
		this.csMaintainDao = csMaintainDao;
	}
}