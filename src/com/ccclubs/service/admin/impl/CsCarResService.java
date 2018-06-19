package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsCarResDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCarRes;
import com.ccclubs.service.admin.ICsCarResService;
import com.lazy3q.web.helper.$;

/**
 * 专车资源的Service实现
 * @author Joel
 */
public class CsCarResService implements ICsCarResService
{
	ICsCarResDao csCarResDao;
	

	/**
	 * 获取所有专车资源
	 * @return
	 */
	public List<CsCarRes> getCsCarResList(Map params,Integer size)
	{
		return csCarResDao.getCsCarResList(params,size);
	}
	
	/**
	 * 获取专车资源统计
	 * @return
	 */
	public List getCsCarResStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csCarResDao.getCsCarResStats(params,groups,sums);
	}
	
	/**
	 * 获取专车资源总数
	 * @return
	 */
	public Long getCsCarResCount(Map params)
	{
		return csCarResDao.getCsCarResCount(params);
	}
	
	/**
	 * 获取专车资源自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCarResEval(String eval,Map params)
	{
		return csCarResDao.getCsCarResEval(eval,params);
	}

	/**
	 * 获取专车资源分页
	 * @return
	 */
	public Page getCsCarResPage(int page,int size,Map params)
	{
		return csCarResDao.getCsCarResPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取专车资源
	 * @param params
	 * @return
	 */
	public CsCarRes getCsCarRes(Map params)
	{
		return csCarResDao.getCsCarRes(params);
	}

	/**
	 * 根据ID取专车资源
	 * @param id
	 * @return
	 */
	public CsCarRes getCsCarResById(Long id)
	{
		return csCarResDao.getCsCarResById(id);
	}

	/**
	 * 保存专车资源
	 * @param csCarRes
	 */
	public CsCarRes saveCsCarRes(CsCarRes csCarRes)
	{
		return csCarResDao.saveCsCarRes(csCarRes);
	}
	
	/**
	 * 更新专车资源
	 * @param csCarRes
	 */
	public void updateCsCarRes(CsCarRes csCarRes)
	{
		csCarResDao.updateCsCarRes(csCarRes);
	}
	/**
	 * 更新专车资源非空字段
	 * @param csCarRes
	 */
	public void updateCsCarRes$NotNull(CsCarRes csCarRes)
	{
		csCarResDao.updateCsCarRes$NotNull(csCarRes);
	}
	
	/**
	 * 根据ID删除一个专车资源
	 * @param id
	 */
	public void deleteCsCarResById(Long id)
	{
		csCarResDao.deleteCsCarResById(id);
	}
	
	/**
	 * 根据ID逻辑删除一个专车资源
	 * @param id
	 */
	public void removeCsCarResById(Long id)
	{
		csCarResDao.removeCsCarResById(id);
	}
	
	/**
	 * 根据条件更新专车资源
	 * @param values
	 * @param params
	 */
	public void updateCsCarResByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsCarResBy时无条件");
		csCarResDao.updateCsCarResByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除专车资源
	 * @param params
	 */
	public void deleteCsCarResByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsCarResBy时无条件");
		csCarResDao.deleteCsCarResByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsCarResDao getCsCarResDao()
	{
		return csCarResDao;
	}

	public void setCsCarResDao(ICsCarResDao csCarResDao)
	{
		this.csCarResDao = csCarResDao;
	}
}