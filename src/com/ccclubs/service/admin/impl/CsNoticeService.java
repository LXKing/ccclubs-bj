package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsNoticeDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsNotice;
import com.ccclubs.service.admin.ICsNoticeService;
import com.lazy3q.web.helper.$;

/**
 * 系统通知的Service实现
 * @author 飞啊飘啊
 */
public class CsNoticeService implements ICsNoticeService
{
	ICsNoticeDao csNoticeDao;
	

	/**
	 * 获取所有系统通知
	 * @return
	 */
	public List<CsNotice> getCsNoticeList(Map params,Integer size)
	{
		return csNoticeDao.getCsNoticeList(params,size);
	}
	
	/**
	 * 获取系统通知统计
	 * @return
	 */
	public List getCsNoticeStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csNoticeDao.getCsNoticeStats(params,groups,sums);
	}
	
	/**
	 * 获取系统通知总数
	 * @return
	 */
	public Long getCsNoticeCount(Map params)
	{
		return csNoticeDao.getCsNoticeCount(params);
	}
	
	/**
	 * 获取系统通知自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsNoticeEval(String eval,Map params)
	{
		return csNoticeDao.getCsNoticeEval(eval,params);
	}

	/**
	 * 获取系统通知分页
	 * @return
	 */
	public Page getCsNoticePage(int page,int size,Map params)
	{
		return csNoticeDao.getCsNoticePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取系统通知
	 * @param params
	 * @return
	 */
	public CsNotice getCsNotice(Map params)
	{
		return csNoticeDao.getCsNotice(params);
	}

	/**
	 * 根据ID取系统通知
	 * @param id
	 * @return
	 */
	public CsNotice getCsNoticeById(Long id)
	{
		return csNoticeDao.getCsNoticeById(id);
	}

	/**
	 * 保存系统通知
	 * @param csNotice
	 */
	public CsNotice saveCsNotice(CsNotice csNotice)
	{
		return csNoticeDao.saveCsNotice(csNotice);
	}
	
	/**
	 * 更新系统通知
	 * @param csNotice
	 */
	public void updateCsNotice(CsNotice csNotice)
	{
		csNoticeDao.updateCsNotice(csNotice);
	}
	/**
	 * 更新系统通知非空字段
	 * @param csNotice
	 */
	public void updateCsNotice$NotNull(CsNotice csNotice)
	{
		csNoticeDao.updateCsNotice$NotNull(csNotice);
	}
	
	/**
	 * 根据ID删除一个系统通知
	 * @param id
	 */
	public void deleteCsNoticeById(Long id)
	{
		csNoticeDao.deleteCsNoticeById(id);
	}
	/**
	 * 根据ID逻辑删除一个系统通知
	 * @param id
	 */
	public void removeCsNoticeById(Long id)
	{
		csNoticeDao.removeCsNoticeById(id);
	}
	
	/**
	 * 根据条件更新系统通知
	 * @param values
	 * @param params
	 */
	public void updateCsNoticeByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsNoticeBy时无条件");
		csNoticeDao.updateCsNoticeByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除系统通知
	 * @param params
	 */
	public void deleteCsNoticeByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsNoticeBy时无条件");
		csNoticeDao.deleteCsNoticeByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsNoticeDao getCsNoticeDao()
	{
		return csNoticeDao;
	}

	public void setCsNoticeDao(ICsNoticeDao csNoticeDao)
	{
		this.csNoticeDao = csNoticeDao;
	}
}