package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsChjSignDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsChjSign;
import com.ccclubs.service.admin.ICsChjSignService;
import com.lazy3q.web.helper.$;

/**
 * 春节套餐报名的Service实现
 * @author 飞啊飘啊
 */
public class CsChjSignService implements ICsChjSignService
{
	ICsChjSignDao csChjSignDao;
	

	/**
	 * 获取所有春节套餐报名
	 * @return
	 */
	public List<CsChjSign> getCsChjSignList(Map params,Integer size)
	{
		return csChjSignDao.getCsChjSignList(params,size);
	}
	
	/**
	 * 获取春节套餐报名统计
	 * @return
	 */
	public List getCsChjSignStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csChjSignDao.getCsChjSignStats(params,groups,sums);
	}
	
	/**
	 * 获取春节套餐报名总数
	 * @return
	 */
	public Long getCsChjSignCount(Map params)
	{
		return csChjSignDao.getCsChjSignCount(params);
	}
	
	/**
	 * 获取春节套餐报名自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsChjSignEval(String eval,Map params)
	{
		return csChjSignDao.getCsChjSignEval(eval,params);
	}

	/**
	 * 获取春节套餐报名分页
	 * @return
	 */
	public Page getCsChjSignPage(int page,int size,Map params)
	{
		return csChjSignDao.getCsChjSignPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取春节套餐报名
	 * @param params
	 * @return
	 */
	public CsChjSign getCsChjSign(Map params)
	{
		return csChjSignDao.getCsChjSign(params);
	}

	/**
	 * 根据ID取春节套餐报名
	 * @param id
	 * @return
	 */
	public CsChjSign getCsChjSignById(Long id)
	{
		return csChjSignDao.getCsChjSignById(id);
	}

	/**
	 * 保存春节套餐报名
	 * @param csChjSign
	 */
	public CsChjSign saveCsChjSign(CsChjSign csChjSign)
	{
		return csChjSignDao.saveCsChjSign(csChjSign);
	}
	
	/**
	 * 更新春节套餐报名
	 * @param csChjSign
	 */
	public void updateCsChjSign(CsChjSign csChjSign)
	{
		csChjSignDao.updateCsChjSign(csChjSign);
	}
	/**
	 * 更新春节套餐报名非空字段
	 * @param csChjSign
	 */
	public void updateCsChjSign$NotNull(CsChjSign csChjSign)
	{
		csChjSignDao.updateCsChjSign$NotNull(csChjSign);
	}
	
	/**
	 * 根据ID删除一个春节套餐报名
	 * @param id
	 */
	public void deleteCsChjSignById(Long id)
	{
		csChjSignDao.deleteCsChjSignById(id);
	}
	/**
	 * 根据ID逻辑删除一个春节套餐报名
	 * @param id
	 */
	public void removeCsChjSignById(Long id)
	{
		csChjSignDao.removeCsChjSignById(id);
	}
	
	/**
	 * 根据条件更新春节套餐报名
	 * @param values
	 * @param params
	 */
	public void updateCsChjSignByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsChjSignBy时无条件");
		csChjSignDao.updateCsChjSignByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除春节套餐报名
	 * @param params
	 */
	public void deleteCsChjSignByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsChjSignBy时无条件");
		csChjSignDao.deleteCsChjSignByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsChjSignDao getCsChjSignDao()
	{
		return csChjSignDao;
	}

	public void setCsChjSignDao(ICsChjSignDao csChjSignDao)
	{
		this.csChjSignDao = csChjSignDao;
	}
}