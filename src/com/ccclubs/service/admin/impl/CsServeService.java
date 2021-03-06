package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsServeDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsServe;
import com.ccclubs.service.admin.ICsServeService;
import com.lazy3q.web.helper.$;

/**
 * 客户服务的Service实现
 * @author 飞啊飘啊
 */
public class CsServeService implements ICsServeService
{
	ICsServeDao csServeDao;
	

	/**
	 * 获取所有客户服务
	 * @return
	 */
	public List<CsServe> getCsServeList(Map params,Integer size)
	{
		return csServeDao.getCsServeList(params,size);
	}
	
	/**
	 * 获取客户服务统计
	 * @return
	 */
	public List getCsServeStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csServeDao.getCsServeStats(params,groups,sums);
	}
	
	/**
	 * 获取客户服务总数
	 * @return
	 */
	public Long getCsServeCount(Map params)
	{
		return csServeDao.getCsServeCount(params);
	}
	
	/**
	 * 获取客户服务自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsServeEval(String eval,Map params)
	{
		return csServeDao.getCsServeEval(eval,params);
	}

	/**
	 * 获取客户服务分页
	 * @return
	 */
	public Page getCsServePage(int page,int size,Map params)
	{
		return csServeDao.getCsServePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取客户服务
	 * @param params
	 * @return
	 */
	public CsServe getCsServe(Map params)
	{
		return csServeDao.getCsServe(params);
	}

	/**
	 * 根据ID取客户服务
	 * @param id
	 * @return
	 */
	public CsServe getCsServeById(Long id)
	{
		return csServeDao.getCsServeById(id);
	}

	/**
	 * 保存客户服务
	 * @param csServe
	 */
	public CsServe saveCsServe(CsServe csServe)
	{
		return csServeDao.saveCsServe(csServe);
	}
	
	/**
	 * 更新客户服务
	 * @param csServe
	 */
	public void updateCsServe(CsServe csServe)
	{
		csServeDao.updateCsServe(csServe);
	}
	/**
	 * 更新客户服务非空字段
	 * @param csServe
	 */
	public void updateCsServe$NotNull(CsServe csServe)
	{
		csServeDao.updateCsServe$NotNull(csServe);
	}
	
	/**
	 * 根据ID删除一个客户服务
	 * @param id
	 */
	public void deleteCsServeById(Long id)
	{
		csServeDao.deleteCsServeById(id);
	}
	/**
	 * 根据ID逻辑删除一个客户服务
	 * @param id
	 */
	public void removeCsServeById(Long id)
	{
		csServeDao.removeCsServeById(id);
	}
	
	/**
	 * 根据条件更新客户服务
	 * @param values
	 * @param params
	 */
	public void updateCsServeByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsServeBy时无条件");
		csServeDao.updateCsServeByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除客户服务
	 * @param params
	 */
	public void deleteCsServeByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsServeBy时无条件");
		csServeDao.deleteCsServeByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsServeDao getCsServeDao()
	{
		return csServeDao;
	}

	public void setCsServeDao(ICsServeDao csServeDao)
	{
		this.csServeDao = csServeDao;
	}
}