package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsEmailDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsEmail;
import com.ccclubs.service.admin.ICsEmailService;
import com.lazy3q.web.helper.$;

/**
 * 邮件内容的Service实现
 * @author 飞啊飘啊
 */
public class CsEmailService implements ICsEmailService
{
	ICsEmailDao csEmailDao;
	

	/**
	 * 获取所有邮件内容
	 * @return
	 */
	public List<CsEmail> getCsEmailList(Map params,Integer size)
	{
		return csEmailDao.getCsEmailList(params,size);
	}
	
	/**
	 * 获取邮件内容统计
	 * @return
	 */
	public List getCsEmailStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csEmailDao.getCsEmailStats(params,groups,sums);
	}
	
	/**
	 * 获取邮件内容总数
	 * @return
	 */
	public Long getCsEmailCount(Map params)
	{
		return csEmailDao.getCsEmailCount(params);
	}
	
	/**
	 * 获取邮件内容自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsEmailEval(String eval,Map params)
	{
		return csEmailDao.getCsEmailEval(eval,params);
	}

	/**
	 * 获取邮件内容分页
	 * @return
	 */
	public Page getCsEmailPage(int page,int size,Map params)
	{
		return csEmailDao.getCsEmailPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取邮件内容
	 * @param params
	 * @return
	 */
	public CsEmail getCsEmail(Map params)
	{
		return csEmailDao.getCsEmail(params);
	}

	/**
	 * 根据ID取邮件内容
	 * @param id
	 * @return
	 */
	public CsEmail getCsEmailById(Long id)
	{
		return csEmailDao.getCsEmailById(id);
	}

	/**
	 * 保存邮件内容
	 * @param csEmail
	 */
	public CsEmail saveCsEmail(CsEmail csEmail)
	{
		return csEmailDao.saveCsEmail(csEmail);
	}
	
	/**
	 * 更新邮件内容
	 * @param csEmail
	 */
	public void updateCsEmail(CsEmail csEmail)
	{
		csEmailDao.updateCsEmail(csEmail);
	}
	/**
	 * 更新邮件内容非空字段
	 * @param csEmail
	 */
	public void updateCsEmail$NotNull(CsEmail csEmail)
	{
		csEmailDao.updateCsEmail$NotNull(csEmail);
	}
	
	/**
	 * 根据ID删除一个邮件内容
	 * @param id
	 */
	public void deleteCsEmailById(Long id)
	{
		csEmailDao.deleteCsEmailById(id);
	}
	/**
	 * 根据ID逻辑删除一个邮件内容
	 * @param id
	 */
	public void removeCsEmailById(Long id)
	{
		csEmailDao.removeCsEmailById(id);
	}
	
	/**
	 * 根据条件更新邮件内容
	 * @param values
	 * @param params
	 */
	public void updateCsEmailByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsEmailBy时无条件");
		csEmailDao.updateCsEmailByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除邮件内容
	 * @param params
	 */
	public void deleteCsEmailByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsEmailBy时无条件");
		csEmailDao.deleteCsEmailByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsEmailDao getCsEmailDao()
	{
		return csEmailDao;
	}

	public void setCsEmailDao(ICsEmailDao csEmailDao)
	{
		this.csEmailDao = csEmailDao;
	}
}