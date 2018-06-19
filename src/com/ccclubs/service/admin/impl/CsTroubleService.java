package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsTroubleDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsTrouble;
import com.ccclubs.service.admin.ICsTroubleService;
import com.lazy3q.web.helper.$;

/**
 * 车辆事故的Service实现
 * @author 飞啊飘啊
 */
public class CsTroubleService implements ICsTroubleService
{
	ICsTroubleDao csTroubleDao;
	

	/**
	 * 获取所有车辆事故
	 * @return
	 */
	public List<CsTrouble> getCsTroubleList(Map params,Integer size)
	{
		return csTroubleDao.getCsTroubleList(params,size);
	}
	
	/**
	 * 获取车辆事故统计
	 * @return
	 */
	public List getCsTroubleStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csTroubleDao.getCsTroubleStats(params,groups,sums);
	}
	
	/**
	 * 获取车辆事故总数
	 * @return
	 */
	public Long getCsTroubleCount(Map params)
	{
		return csTroubleDao.getCsTroubleCount(params);
	}
	
	/**
	 * 获取车辆事故自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsTroubleEval(String eval,Map params)
	{
		return csTroubleDao.getCsTroubleEval(eval,params);
	}

	/**
	 * 获取车辆事故分页
	 * @return
	 */
	public Page getCsTroublePage(int page,int size,Map params)
	{
		return csTroubleDao.getCsTroublePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取车辆事故
	 * @param params
	 * @return
	 */
	public CsTrouble getCsTrouble(Map params)
	{
		return csTroubleDao.getCsTrouble(params);
	}

	/**
	 * 根据ID取车辆事故
	 * @param id
	 * @return
	 */
	public CsTrouble getCsTroubleById(Long id)
	{
		return csTroubleDao.getCsTroubleById(id);
	}

	/**
	 * 保存车辆事故
	 * @param csTrouble
	 */
	public CsTrouble saveCsTrouble(CsTrouble csTrouble)
	{
		return csTroubleDao.saveCsTrouble(csTrouble);
	}
	
	/**
	 * 更新车辆事故
	 * @param csTrouble
	 */
	public void updateCsTrouble(CsTrouble csTrouble)
	{
		csTroubleDao.updateCsTrouble(csTrouble);
	}
	/**
	 * 更新车辆事故非空字段
	 * @param csTrouble
	 */
	public void updateCsTrouble$NotNull(CsTrouble csTrouble)
	{
		csTroubleDao.updateCsTrouble$NotNull(csTrouble);
	}
	
	/**
	 * 根据ID删除一个车辆事故
	 * @param id
	 */
	public void deleteCsTroubleById(Long id)
	{
		csTroubleDao.deleteCsTroubleById(id);
	}
	/**
	 * 根据ID逻辑删除一个车辆事故
	 * @param id
	 */
	public void removeCsTroubleById(Long id)
	{
		csTroubleDao.removeCsTroubleById(id);
	}
	
	/**
	 * 根据条件更新车辆事故
	 * @param values
	 * @param params
	 */
	public void updateCsTroubleByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsTroubleBy时无条件");
		csTroubleDao.updateCsTroubleByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除车辆事故
	 * @param params
	 */
	public void deleteCsTroubleByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsTroubleBy时无条件");
		csTroubleDao.deleteCsTroubleByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsTroubleDao getCsTroubleDao()
	{
		return csTroubleDao;
	}

	public void setCsTroubleDao(ICsTroubleDao csTroubleDao)
	{
		this.csTroubleDao = csTroubleDao;
	}
}