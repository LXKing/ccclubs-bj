package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsVisitIpDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsVisitIp;
import com.ccclubs.service.admin.ICsVisitIpService;
import com.lazy3q.web.helper.$;

/**
 * 独立IP访问的Service实现
 * @author 飞啊飘啊
 */
public class CsVisitIpService implements ICsVisitIpService
{
	ICsVisitIpDao csVisitIpDao;
	

	/**
	 * 获取所有独立IP访问
	 * @return
	 */
	public List<CsVisitIp> getCsVisitIpList(Map params,Integer size)
	{
		return csVisitIpDao.getCsVisitIpList(params,size);
	}
	
	/**
	 * 获取独立IP访问统计
	 * @return
	 */
	public List getCsVisitIpStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csVisitIpDao.getCsVisitIpStats(params,groups,sums);
	}
	
	/**
	 * 获取独立IP访问总数
	 * @return
	 */
	public Long getCsVisitIpCount(Map params)
	{
		return csVisitIpDao.getCsVisitIpCount(params);
	}
	
	/**
	 * 获取独立IP访问自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsVisitIpEval(String eval,Map params)
	{
		return csVisitIpDao.getCsVisitIpEval(eval,params);
	}

	/**
	 * 获取独立IP访问分页
	 * @return
	 */
	public Page getCsVisitIpPage(int page,int size,Map params)
	{
		return csVisitIpDao.getCsVisitIpPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取独立IP访问
	 * @param params
	 * @return
	 */
	public CsVisitIp getCsVisitIp(Map params)
	{
		return csVisitIpDao.getCsVisitIp(params);
	}

	/**
	 * 根据ID取独立IP访问
	 * @param id
	 * @return
	 */
	public CsVisitIp getCsVisitIpById(Long id)
	{
		return csVisitIpDao.getCsVisitIpById(id);
	}

	/**
	 * 保存独立IP访问
	 * @param csVisitIp
	 */
	public CsVisitIp saveCsVisitIp(CsVisitIp csVisitIp)
	{
		return csVisitIpDao.saveCsVisitIp(csVisitIp);
	}
	
	/**
	 * 更新独立IP访问
	 * @param csVisitIp
	 */
	public void updateCsVisitIp(CsVisitIp csVisitIp)
	{
		csVisitIpDao.updateCsVisitIp(csVisitIp);
	}
	/**
	 * 更新独立IP访问非空字段
	 * @param csVisitIp
	 */
	public void updateCsVisitIp$NotNull(CsVisitIp csVisitIp)
	{
		csVisitIpDao.updateCsVisitIp$NotNull(csVisitIp);
	}
	
	/**
	 * 根据ID删除一个独立IP访问
	 * @param id
	 */
	public void deleteCsVisitIpById(Long id)
	{
		csVisitIpDao.deleteCsVisitIpById(id);
	}
	
	/**
	 * 根据条件更新独立IP访问
	 * @param values
	 * @param params
	 */
	public void updateCsVisitIpByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsVisitIpBy时无条件");
		csVisitIpDao.updateCsVisitIpByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除独立IP访问
	 * @param params
	 */
	public void deleteCsVisitIpByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsVisitIpBy时无条件");
		csVisitIpDao.deleteCsVisitIpByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsVisitIpDao getCsVisitIpDao()
	{
		return csVisitIpDao;
	}

	public void setCsVisitIpDao(ICsVisitIpDao csVisitIpDao)
	{
		this.csVisitIpDao = csVisitIpDao;
	}
}