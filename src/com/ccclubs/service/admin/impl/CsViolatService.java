package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsViolatDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsViolat;
import com.ccclubs.service.admin.ICsViolatService;
import com.lazy3q.web.helper.$;

/**
 * 车辆违章的Service实现
 * @author 飞啊飘啊
 */
public class CsViolatService implements ICsViolatService
{
	ICsViolatDao csViolatDao;
	

	/**
	 * 获取所有车辆违章
	 * @return
	 */
	public List<CsViolat> getCsViolatList(Map params,Integer size)
	{
		return csViolatDao.getCsViolatList(params,size);
	}
	
	/**
	 * 获取车辆违章统计
	 * @return
	 */
	public List getCsViolatStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csViolatDao.getCsViolatStats(params,groups,sums);
	}
	
	/**
	 * 获取车辆违章总数
	 * @return
	 */
	public Long getCsViolatCount(Map params)
	{
		return csViolatDao.getCsViolatCount(params);
	}
	
	/**
	 * 获取车辆违章自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsViolatEval(String eval,Map params)
	{
		return csViolatDao.getCsViolatEval(eval,params);
	}

	/**
	 * 获取车辆违章分页
	 * @return
	 */
	public Page getCsViolatPage(int page,int size,Map params)
	{
		return csViolatDao.getCsViolatPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取车辆违章
	 * @param params
	 * @return
	 */
	public CsViolat getCsViolat(Map params)
	{
		return csViolatDao.getCsViolat(params);
	}

	/**
	 * 根据ID取车辆违章
	 * @param id
	 * @return
	 */
	public CsViolat getCsViolatById(Long id)
	{
		return csViolatDao.getCsViolatById(id);
	}

	/**
	 * 保存车辆违章
	 * @param csViolat
	 */
	public CsViolat saveCsViolat(CsViolat csViolat)
	{
		return csViolatDao.saveCsViolat(csViolat);
	}
	
	/**
	 * 更新车辆违章
	 * @param csViolat
	 */
	public void updateCsViolat(CsViolat csViolat)
	{
		csViolatDao.updateCsViolat(csViolat);
	}
	/**
	 * 更新车辆违章非空字段
	 * @param csViolat
	 */
	public void updateCsViolat$NotNull(CsViolat csViolat)
	{
		csViolatDao.updateCsViolat$NotNull(csViolat);
	}
	
	/**
	 * 根据ID删除一个车辆违章
	 * @param id
	 */
	public void deleteCsViolatById(Long id)
	{
		csViolatDao.deleteCsViolatById(id);
	}
	/**
	 * 根据ID逻辑删除一个车辆违章
	 * @param id
	 */
	public void removeCsViolatById(Long id)
	{
		csViolatDao.removeCsViolatById(id);
	}
	
	/**
	 * 根据条件更新车辆违章
	 * @param values
	 * @param params
	 */
	public void updateCsViolatByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsViolatBy时无条件");
		csViolatDao.updateCsViolatByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除车辆违章
	 * @param params
	 */
	public void deleteCsViolatByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsViolatBy时无条件");
		csViolatDao.deleteCsViolatByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsViolatDao getCsViolatDao()
	{
		return csViolatDao;
	}

	public void setCsViolatDao(ICsViolatDao csViolatDao)
	{
		this.csViolatDao = csViolatDao;
	}
}