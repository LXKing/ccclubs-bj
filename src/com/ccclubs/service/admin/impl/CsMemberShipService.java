package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsMemberShipDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsMemberShip;
import com.ccclubs.service.admin.ICsMemberShipService;
import com.lazy3q.web.helper.$;

/**
 * 会员关系的Service实现
 * @author 飞啊飘啊
 */
public class CsMemberShipService implements ICsMemberShipService
{
	ICsMemberShipDao csMemberShipDao;
	

	/**
	 * 获取所有会员关系
	 * @return
	 */
	public List<CsMemberShip> getCsMemberShipList(Map params,Integer size)
	{
		return csMemberShipDao.getCsMemberShipList(params,size);
	}
	
	/**
	 * 获取会员关系统计
	 * @return
	 */
	public List getCsMemberShipStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csMemberShipDao.getCsMemberShipStats(params,groups,sums);
	}
	
	/**
	 * 获取会员关系总数
	 * @return
	 */
	public Long getCsMemberShipCount(Map params)
	{
		return csMemberShipDao.getCsMemberShipCount(params);
	}
	
	/**
	 * 获取会员关系自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsMemberShipEval(String eval,Map params)
	{
		return csMemberShipDao.getCsMemberShipEval(eval,params);
	}

	/**
	 * 获取会员关系分页
	 * @return
	 */
	public Page getCsMemberShipPage(int page,int size,Map params)
	{
		return csMemberShipDao.getCsMemberShipPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取会员关系
	 * @param params
	 * @return
	 */
	public CsMemberShip getCsMemberShip(Map params)
	{
		return csMemberShipDao.getCsMemberShip(params);
	}

	/**
	 * 根据ID取会员关系
	 * @param id
	 * @return
	 */
	public CsMemberShip getCsMemberShipById(Long id)
	{
		return csMemberShipDao.getCsMemberShipById(id);
	}

	/**
	 * 保存会员关系
	 * @param csMemberShip
	 */
	public CsMemberShip saveCsMemberShip(CsMemberShip csMemberShip)
	{
		return csMemberShipDao.saveCsMemberShip(csMemberShip);
	}
	
	/**
	 * 更新会员关系
	 * @param csMemberShip
	 */
	public void updateCsMemberShip(CsMemberShip csMemberShip)
	{
		csMemberShipDao.updateCsMemberShip(csMemberShip);
	}
	/**
	 * 更新会员关系非空字段
	 * @param csMemberShip
	 */
	public void updateCsMemberShip$NotNull(CsMemberShip csMemberShip)
	{
		csMemberShipDao.updateCsMemberShip$NotNull(csMemberShip);
	}
	
	/**
	 * 根据ID删除一个会员关系
	 * @param id
	 */
	public void deleteCsMemberShipById(Long id)
	{
		csMemberShipDao.deleteCsMemberShipById(id);
	}
	/**
	 * 根据ID逻辑删除一个会员关系
	 * @param id
	 */
	public void removeCsMemberShipById(Long id)
	{
		csMemberShipDao.removeCsMemberShipById(id);
	}
	
	/**
	 * 根据条件更新会员关系
	 * @param values
	 * @param params
	 */
	public void updateCsMemberShipByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsMemberShipBy时无条件");
		csMemberShipDao.updateCsMemberShipByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除会员关系
	 * @param params
	 */
	public void deleteCsMemberShipByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsMemberShipBy时无条件");
		csMemberShipDao.deleteCsMemberShipByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsMemberShipDao getCsMemberShipDao()
	{
		return csMemberShipDao;
	}

	public void setCsMemberShipDao(ICsMemberShipDao csMemberShipDao)
	{
		this.csMemberShipDao = csMemberShipDao;
	}
}