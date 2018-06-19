package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsEmail;

/**
 * 邮件内容的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsEmailDao
{
	/**
	 * 获取所有邮件内容
	 * @return
	 */
	public List<CsEmail> getCsEmailList(Map params,Integer size);
	
	/**
	 * 获取邮件内容统计
	 * @return
	 */
	public List getCsEmailStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取邮件内容总数
	 * @return
	 */	
	public Long getCsEmailCount(Map params);
	
	/**
	 * 获取邮件内容自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsEmailEval(String eval,Map params);
	
	/**
	 * 获取邮件内容分页
	 * @return
	 */	
	public Page getCsEmailPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取邮件内容
	 * @param params
	 * @return
	 */
	public CsEmail getCsEmail(Map params);	

	/**
	 * 根据ID取邮件内容
	 * @param id
	 * @return
	 */
	public CsEmail getCsEmailById(Long id);


	
	

	/**
	 * 保存邮件内容
	 * @param csEmail
	 */
	public CsEmail saveCsEmail(CsEmail csEmail);

	/**
	 * 更新邮件内容
	 * @param csEmail
	 */
	public void updateCsEmail(CsEmail csEmail);
	/**
	 * 更新邮件内容非空字段
	 * @param csEmail
	 */
	public void updateCsEmail$NotNull(CsEmail csEmail);

	/**
	 * 根据ID删除一个邮件内容
	 * @param id
	 */
	public void deleteCsEmailById(Long id);
	/**
	 * 根据ID逻辑删除一个邮件内容
	 * @param id
	 */
	public void removeCsEmailById(Long id);
	/**
	 * 根据条件更新邮件内容
	 * @param values
	 * @param params
	 */
	public void updateCsEmailByConfirm(Map values, Map params);
	/**
	 * 根据条件删除邮件内容
	 * @param params
	 */
	public void deleteCsEmailByConfirm(Map params);

}