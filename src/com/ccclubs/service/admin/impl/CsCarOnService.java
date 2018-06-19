package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsCarOnDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCarOn;
import com.ccclubs.service.admin.ICsCarOnService;
import com.lazy3q.web.helper.$;

/**
 * 车辆上线记录的Service实现
 * @author 飞啊飘啊
 */
public class CsCarOnService implements ICsCarOnService
{
	ICsCarOnDao csCarOnDao;
	

	/**
	 * 获取所有车辆上线记录
	 * @return
	 */
	public List<CsCarOn> getCsCarOnList(Map params,Integer size)
	{
		return csCarOnDao.getCsCarOnList(params,size);
	}
	
	/**
	 * 获取车辆上线记录统计
	 * @return
	 */
	public List getCsCarOnStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csCarOnDao.getCsCarOnStats(params,groups,sums);
	}
	
	/**
	 * 获取车辆上线记录总数
	 * @return
	 */
	public Long getCsCarOnCount(Map params)
	{
		return csCarOnDao.getCsCarOnCount(params);
	}
	
	/**
	 * 获取车辆上线记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCarOnEval(String eval,Map params)
	{
		return csCarOnDao.getCsCarOnEval(eval,params);
	}

	/**
	 * 获取车辆上线记录分页
	 * @return
	 */
	public Page getCsCarOnPage(int page,int size,Map params)
	{
		return csCarOnDao.getCsCarOnPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取车辆上线记录
	 * @param params
	 * @return
	 */
	public CsCarOn getCsCarOn(Map params)
	{
		return csCarOnDao.getCsCarOn(params);
	}

	/**
	 * 根据ID取车辆上线记录
	 * @param id
	 * @return
	 */
	public CsCarOn getCsCarOnById(Long id)
	{
		return csCarOnDao.getCsCarOnById(id);
	}

	/**
	 * 保存车辆上线记录
	 * @param csCarOn
	 */
	public CsCarOn saveCsCarOn(CsCarOn csCarOn)
	{
		return csCarOnDao.saveCsCarOn(csCarOn);
	}
	
	/**
	 * 更新车辆上线记录
	 * @param csCarOn
	 */
	public void updateCsCarOn(CsCarOn csCarOn)
	{
		csCarOnDao.updateCsCarOn(csCarOn);
	}
	/**
	 * 更新车辆上线记录非空字段
	 * @param csCarOn
	 */
	public void updateCsCarOn$NotNull(CsCarOn csCarOn)
	{
		csCarOnDao.updateCsCarOn$NotNull(csCarOn);
	}
	
	/**
	 * 根据ID删除一个车辆上线记录
	 * @param id
	 */
	public void deleteCsCarOnById(Long id)
	{
		csCarOnDao.deleteCsCarOnById(id);
	}
	/**
	 * 根据ID逻辑删除一个车辆上线记录
	 * @param id
	 */
	public void removeCsCarOnById(Long id)
	{
		csCarOnDao.removeCsCarOnById(id);
	}
	
	/**
	 * 根据条件更新车辆上线记录
	 * @param values
	 * @param params
	 */
	public void updateCsCarOnByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsCarOnBy时无条件");
		csCarOnDao.updateCsCarOnByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除车辆上线记录
	 * @param params
	 */
	public void deleteCsCarOnByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsCarOnBy时无条件");
		csCarOnDao.deleteCsCarOnByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsCarOnDao getCsCarOnDao()
	{
		return csCarOnDao;
	}

	public void setCsCarOnDao(ICsCarOnDao csCarOnDao)
	{
		this.csCarOnDao = csCarOnDao;
	}
}