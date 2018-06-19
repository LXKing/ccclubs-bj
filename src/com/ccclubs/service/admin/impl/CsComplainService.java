package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsComplainDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsComplain;
import com.ccclubs.service.admin.ICsComplainService;
import com.lazy3q.web.helper.$;

/**
 * 意见反馈的Service实现
 * @author Joel
 */
public class CsComplainService implements ICsComplainService
{
	ICsComplainDao csComplainDao;
	

	/**
	 * 获取所有意见反馈
	 * @return
	 */
	public List<CsComplain> getCsComplainList(Map params,Integer size)
	{
		return csComplainDao.getCsComplainList(params,size);
	}
	
	/**
	 * 获取意见反馈统计
	 * @return
	 */
	public List getCsComplainStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csComplainDao.getCsComplainStats(params,groups,sums);
	}
	
	/**
	 * 获取意见反馈总数
	 * @return
	 */
	public Long getCsComplainCount(Map params)
	{
		return csComplainDao.getCsComplainCount(params);
	}
	
	/**
	 * 获取意见反馈自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsComplainEval(String eval,Map params)
	{
		return csComplainDao.getCsComplainEval(eval,params);
	}

	/**
	 * 获取意见反馈分页
	 * @return
	 */
	public Page getCsComplainPage(int page,int size,Map params)
	{
		return csComplainDao.getCsComplainPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取意见反馈
	 * @param params
	 * @return
	 */
	public CsComplain getCsComplain(Map params)
	{
		return csComplainDao.getCsComplain(params);
	}

	/**
	 * 根据ID取意见反馈
	 * @param id
	 * @return
	 */
	public CsComplain getCsComplainById(Long id)
	{
		return csComplainDao.getCsComplainById(id);
	}

	/**
	 * 保存意见反馈
	 * @param csComplain
	 */
	public CsComplain saveCsComplain(CsComplain csComplain)
	{
		return csComplainDao.saveCsComplain(csComplain);
	}
	
	/**
	 * 更新意见反馈
	 * @param csComplain
	 */
	public void updateCsComplain(CsComplain csComplain)
	{
		csComplainDao.updateCsComplain(csComplain);
	}
	/**
	 * 更新意见反馈非空字段
	 * @param csComplain
	 */
	public void updateCsComplain$NotNull(CsComplain csComplain)
	{
		csComplainDao.updateCsComplain$NotNull(csComplain);
	}
	
	/**
	 * 根据ID删除一个意见反馈
	 * @param id
	 */
	public void deleteCsComplainById(Long id)
	{
		csComplainDao.deleteCsComplainById(id);
	}
	
	/**
	 * 根据ID逻辑删除一个意见反馈
	 * @param id
	 */
	public void removeCsComplainById(Long id)
	{
		csComplainDao.removeCsComplainById(id);
	}
	
	/**
	 * 根据条件更新意见反馈
	 * @param values
	 * @param params
	 */
	public void updateCsComplainByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsComplainBy时无条件");
		csComplainDao.updateCsComplainByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除意见反馈
	 * @param params
	 */
	public void deleteCsComplainByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsComplainBy时无条件");
		csComplainDao.deleteCsComplainByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsComplainDao getCsComplainDao()
	{
		return csComplainDao;
	}

	public void setCsComplainDao(ICsComplainDao csComplainDao)
	{
		this.csComplainDao = csComplainDao;
	}
}