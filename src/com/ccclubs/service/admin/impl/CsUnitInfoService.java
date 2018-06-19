package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsUnitInfoDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsUnitInfo;
import com.ccclubs.service.admin.ICsUnitInfoService;
import com.lazy3q.web.helper.$;

/**
 * 企业信息的Service实现
 * @author 飞啊飘啊
 */
public class CsUnitInfoService implements ICsUnitInfoService
{
	ICsUnitInfoDao csUnitInfoDao;
	

	/**
	 * 获取所有企业信息
	 * @return
	 */
	public List<CsUnitInfo> getCsUnitInfoList(Map params,Integer size)
	{
		return csUnitInfoDao.getCsUnitInfoList(params,size);
	}
	
	/**
	 * 获取企业信息统计
	 * @return
	 */
	public List getCsUnitInfoStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csUnitInfoDao.getCsUnitInfoStats(params,groups,sums);
	}
	
	/**
	 * 获取企业信息总数
	 * @return
	 */
	public Long getCsUnitInfoCount(Map params)
	{
		return csUnitInfoDao.getCsUnitInfoCount(params);
	}
	
	/**
	 * 获取企业信息自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsUnitInfoEval(String eval,Map params)
	{
		return csUnitInfoDao.getCsUnitInfoEval(eval,params);
	}

	/**
	 * 获取企业信息分页
	 * @return
	 */
	public Page getCsUnitInfoPage(int page,int size,Map params)
	{
		return csUnitInfoDao.getCsUnitInfoPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取企业信息
	 * @param params
	 * @return
	 */
	public CsUnitInfo getCsUnitInfo(Map params)
	{
		return csUnitInfoDao.getCsUnitInfo(params);
	}

	/**
	 * 根据ID取企业信息
	 * @param id
	 * @return
	 */
	public CsUnitInfo getCsUnitInfoById(Long id)
	{
		return csUnitInfoDao.getCsUnitInfoById(id);
	}

	/**
	 * 保存企业信息
	 * @param csUnitInfo
	 */
	public CsUnitInfo saveCsUnitInfo(CsUnitInfo csUnitInfo)
	{
		return csUnitInfoDao.saveCsUnitInfo(csUnitInfo);
	}
	
	/**
	 * 更新企业信息
	 * @param csUnitInfo
	 */
	public void updateCsUnitInfo(CsUnitInfo csUnitInfo)
	{
		csUnitInfoDao.updateCsUnitInfo(csUnitInfo);
	}
	/**
	 * 更新企业信息非空字段
	 * @param csUnitInfo
	 */
	public void updateCsUnitInfo$NotNull(CsUnitInfo csUnitInfo)
	{
		csUnitInfoDao.updateCsUnitInfo$NotNull(csUnitInfo);
	}
	
	/**
	 * 根据ID删除一个企业信息
	 * @param id
	 */
	public void deleteCsUnitInfoById(Long id)
	{
		csUnitInfoDao.deleteCsUnitInfoById(id);
	}
	/**
	 * 根据ID逻辑删除一个企业信息
	 * @param id
	 */
	public void removeCsUnitInfoById(Long id)
	{
		csUnitInfoDao.removeCsUnitInfoById(id);
	}
	
	/**
	 * 根据条件更新企业信息
	 * @param values
	 * @param params
	 */
	public void updateCsUnitInfoByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsUnitInfoBy时无条件");
		csUnitInfoDao.updateCsUnitInfoByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除企业信息
	 * @param params
	 */
	public void deleteCsUnitInfoByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsUnitInfoBy时无条件");
		csUnitInfoDao.deleteCsUnitInfoByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsUnitInfoDao getCsUnitInfoDao()
	{
		return csUnitInfoDao;
	}

	public void setCsUnitInfoDao(ICsUnitInfoDao csUnitInfoDao)
	{
		this.csUnitInfoDao = csUnitInfoDao;
	}
}