package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsChargePileDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsChargePile;
import com.ccclubs.service.admin.ICsChargePileService;
import com.lazy3q.web.helper.$;

/**
 * 充电桩的Service实现
 * @author 飞啊飘啊
 */
public class CsChargePileService implements ICsChargePileService
{
	ICsChargePileDao csChargePileDao;
	

	/**
	 * 获取所有充电桩
	 * @return
	 */
	public List<CsChargePile> getCsChargePileList(Map params,Integer size)
	{
		return csChargePileDao.getCsChargePileList(params,size);
	}
	
	/**
	 * 获取充电桩统计
	 * @return
	 */
	public List getCsChargePileStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csChargePileDao.getCsChargePileStats(params,groups,sums);
	}
	
	/**
	 * 获取充电桩总数
	 * @return
	 */
	public Long getCsChargePileCount(Map params)
	{
		return csChargePileDao.getCsChargePileCount(params);
	}
	
	/**
	 * 获取充电桩自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsChargePileEval(String eval,Map params)
	{
		return csChargePileDao.getCsChargePileEval(eval,params);
	}

	/**
	 * 获取充电桩分页
	 * @return
	 */
	public Page getCsChargePilePage(int page,int size,Map params)
	{
		return csChargePileDao.getCsChargePilePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取充电桩
	 * @param params
	 * @return
	 */
	public CsChargePile getCsChargePile(Map params)
	{
		return csChargePileDao.getCsChargePile(params);
	}

	/**
	 * 根据ID取充电桩
	 * @param id
	 * @return
	 */
	public CsChargePile getCsChargePileById(Long id)
	{
		return csChargePileDao.getCsChargePileById(id);
	}

	/**
	 * 保存充电桩
	 * @param csChargePile
	 */
	public CsChargePile saveCsChargePile(CsChargePile csChargePile)
	{
		return csChargePileDao.saveCsChargePile(csChargePile);
	}
	
	/**
	 * 更新充电桩
	 * @param csChargePile
	 */
	public void updateCsChargePile(CsChargePile csChargePile)
	{
		csChargePileDao.updateCsChargePile(csChargePile);
	}
	/**
	 * 更新充电桩非空字段
	 * @param csChargePile
	 */
	public void updateCsChargePile$NotNull(CsChargePile csChargePile)
	{
		csChargePileDao.updateCsChargePile$NotNull(csChargePile);
	}
	
	/**
	 * 根据ID删除一个充电桩
	 * @param id
	 */
	public void deleteCsChargePileById(Long id)
	{
		csChargePileDao.deleteCsChargePileById(id);
	}
	
	/**
	 * 根据条件更新充电桩
	 * @param values
	 * @param params
	 */
	public void updateCsChargePileByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsChargePileBy时无条件");
		csChargePileDao.updateCsChargePileByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除充电桩
	 * @param params
	 */
	public void deleteCsChargePileByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsChargePileBy时无条件");
		csChargePileDao.deleteCsChargePileByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsChargePileDao getCsChargePileDao()
	{
		return csChargePileDao;
	}

	public void setCsChargePileDao(ICsChargePileDao csChargePileDao)
	{
		this.csChargePileDao = csChargePileDao;
	}
}