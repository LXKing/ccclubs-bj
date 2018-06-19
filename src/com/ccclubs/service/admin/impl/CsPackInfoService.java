package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsPackInfoDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsPackInfo;
import com.ccclubs.service.admin.ICsPackInfoService;
import com.lazy3q.web.helper.$;

/**
 * 套餐内容的Service实现
 * @author 飞啊飘啊
 */
public class CsPackInfoService implements ICsPackInfoService
{
	ICsPackInfoDao csPackInfoDao;
	

	/**
	 * 获取所有套餐内容
	 * @return
	 */
	public List<CsPackInfo> getCsPackInfoList(Map params,Integer size)
	{
		return csPackInfoDao.getCsPackInfoList(params,size);
	}
	
	/**
	 * 获取套餐内容统计
	 * @return
	 */
	public List getCsPackInfoStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csPackInfoDao.getCsPackInfoStats(params,groups,sums);
	}
	
	/**
	 * 获取套餐内容总数
	 * @return
	 */
	public Long getCsPackInfoCount(Map params)
	{
		return csPackInfoDao.getCsPackInfoCount(params);
	}
	
	/**
	 * 获取套餐内容自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsPackInfoEval(String eval,Map params)
	{
		return csPackInfoDao.getCsPackInfoEval(eval,params);
	}

	/**
	 * 获取套餐内容分页
	 * @return
	 */
	public Page getCsPackInfoPage(int page,int size,Map params)
	{
		return csPackInfoDao.getCsPackInfoPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取套餐内容
	 * @param params
	 * @return
	 */
	public CsPackInfo getCsPackInfo(Map params)
	{
		return csPackInfoDao.getCsPackInfo(params);
	}

	/**
	 * 根据ID取套餐内容
	 * @param id
	 * @return
	 */
	public CsPackInfo getCsPackInfoById(Long id)
	{
		return csPackInfoDao.getCsPackInfoById(id);
	}

	/**
	 * 保存套餐内容
	 * @param csPackInfo
	 */
	public CsPackInfo saveCsPackInfo(CsPackInfo csPackInfo)
	{
		return csPackInfoDao.saveCsPackInfo(csPackInfo);
	}
	
	/**
	 * 更新套餐内容
	 * @param csPackInfo
	 */
	public void updateCsPackInfo(CsPackInfo csPackInfo)
	{
		csPackInfoDao.updateCsPackInfo(csPackInfo);
	}
	/**
	 * 更新套餐内容非空字段
	 * @param csPackInfo
	 */
	public void updateCsPackInfo$NotNull(CsPackInfo csPackInfo)
	{
		csPackInfoDao.updateCsPackInfo$NotNull(csPackInfo);
	}
	
	/**
	 * 根据ID删除一个套餐内容
	 * @param id
	 */
	public void deleteCsPackInfoById(Long id)
	{
		csPackInfoDao.deleteCsPackInfoById(id);
	}
	
	/**
	 * 根据条件更新套餐内容
	 * @param values
	 * @param params
	 */
	public void updateCsPackInfoByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsPackInfoBy时无条件");
		csPackInfoDao.updateCsPackInfoByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除套餐内容
	 * @param params
	 */
	public void deleteCsPackInfoByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsPackInfoBy时无条件");
		csPackInfoDao.deleteCsPackInfoByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsPackInfoDao getCsPackInfoDao()
	{
		return csPackInfoDao;
	}

	public void setCsPackInfoDao(ICsPackInfoDao csPackInfoDao)
	{
		this.csPackInfoDao = csPackInfoDao;
	}
}