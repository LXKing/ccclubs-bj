package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsFreeHourDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsFreeHour;
import com.ccclubs.service.admin.ICsFreeHourService;
import com.lazy3q.web.helper.$;

/**
 * 免费小时的Service实现
 * @author 飞啊飘啊
 */
public class CsFreeHourService implements ICsFreeHourService
{
	ICsFreeHourDao csFreeHourDao;
	

	/**
	 * 获取所有免费小时
	 * @return
	 */
	public List<CsFreeHour> getCsFreeHourList(Map params,Integer size)
	{
		return csFreeHourDao.getCsFreeHourList(params,size);
	}
	
	/**
	 * 获取免费小时统计
	 * @return
	 */
	public List getCsFreeHourStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csFreeHourDao.getCsFreeHourStats(params,groups,sums);
	}
	
	/**
	 * 获取免费小时总数
	 * @return
	 */
	public Long getCsFreeHourCount(Map params)
	{
		return csFreeHourDao.getCsFreeHourCount(params);
	}
	
	/**
	 * 获取免费小时自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsFreeHourEval(String eval,Map params)
	{
		return csFreeHourDao.getCsFreeHourEval(eval,params);
	}

	/**
	 * 获取免费小时分页
	 * @return
	 */
	public Page getCsFreeHourPage(int page,int size,Map params)
	{
		return csFreeHourDao.getCsFreeHourPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取免费小时
	 * @param params
	 * @return
	 */
	public CsFreeHour getCsFreeHour(Map params)
	{
		return csFreeHourDao.getCsFreeHour(params);
	}

	/**
	 * 根据ID取免费小时
	 * @param id
	 * @return
	 */
	public CsFreeHour getCsFreeHourById(Long id)
	{
		return csFreeHourDao.getCsFreeHourById(id);
	}

	/**
	 * 保存免费小时
	 * @param csFreeHour
	 */
	public CsFreeHour saveCsFreeHour(CsFreeHour csFreeHour)
	{
		return csFreeHourDao.saveCsFreeHour(csFreeHour);
	}
	
	/**
	 * 更新免费小时
	 * @param csFreeHour
	 */
	public void updateCsFreeHour(CsFreeHour csFreeHour)
	{
		csFreeHourDao.updateCsFreeHour(csFreeHour);
	}
	/**
	 * 更新免费小时非空字段
	 * @param csFreeHour
	 */
	public void updateCsFreeHour$NotNull(CsFreeHour csFreeHour)
	{
		csFreeHourDao.updateCsFreeHour$NotNull(csFreeHour);
	}
	
	/**
	 * 根据ID删除一个免费小时
	 * @param id
	 */
	public void deleteCsFreeHourById(Long id)
	{
		csFreeHourDao.deleteCsFreeHourById(id);
	}
	/**
	 * 根据ID逻辑删除一个免费小时
	 * @param id
	 */
	public void removeCsFreeHourById(Long id)
	{
		csFreeHourDao.removeCsFreeHourById(id);
	}
	
	/**
	 * 根据条件更新免费小时
	 * @param values
	 * @param params
	 */
	public void updateCsFreeHourByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsFreeHourBy时无条件");
		csFreeHourDao.updateCsFreeHourByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除免费小时
	 * @param params
	 */
	public void deleteCsFreeHourByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsFreeHourBy时无条件");
		csFreeHourDao.deleteCsFreeHourByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsFreeHourDao getCsFreeHourDao()
	{
		return csFreeHourDao;
	}

	public void setCsFreeHourDao(ICsFreeHourDao csFreeHourDao)
	{
		this.csFreeHourDao = csFreeHourDao;
	}
}