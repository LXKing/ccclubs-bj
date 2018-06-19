package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsUnitUserDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsUnitUser;
import com.ccclubs.service.admin.ICsUnitUserService;
import com.lazy3q.web.helper.$;

/**
 * 企业车管的Service实现
 * @author 飞啊飘啊
 */
public class CsUnitUserService implements ICsUnitUserService
{
	ICsUnitUserDao csUnitUserDao;
	

	/**
	 * 获取所有企业车管
	 * @return
	 */
	public List<CsUnitUser> getCsUnitUserList(Map params,Integer size)
	{
		return csUnitUserDao.getCsUnitUserList(params,size);
	}
	
	/**
	 * 获取企业车管统计
	 * @return
	 */
	public List getCsUnitUserStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csUnitUserDao.getCsUnitUserStats(params,groups,sums);
	}
	
	/**
	 * 获取企业车管总数
	 * @return
	 */
	public Long getCsUnitUserCount(Map params)
	{
		return csUnitUserDao.getCsUnitUserCount(params);
	}
	
	/**
	 * 获取企业车管自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsUnitUserEval(String eval,Map params)
	{
		return csUnitUserDao.getCsUnitUserEval(eval,params);
	}

	/**
	 * 获取企业车管分页
	 * @return
	 */
	public Page getCsUnitUserPage(int page,int size,Map params)
	{
		return csUnitUserDao.getCsUnitUserPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取企业车管
	 * @param params
	 * @return
	 */
	public CsUnitUser getCsUnitUser(Map params)
	{
		return csUnitUserDao.getCsUnitUser(params);
	}

	/**
	 * 根据ID取企业车管
	 * @param id
	 * @return
	 */
	public CsUnitUser getCsUnitUserById(Long id)
	{
		return csUnitUserDao.getCsUnitUserById(id);
	}

	/**
	 * 保存企业车管
	 * @param csUnitUser
	 */
	public CsUnitUser saveCsUnitUser(CsUnitUser csUnitUser)
	{
		return csUnitUserDao.saveCsUnitUser(csUnitUser);
	}
	
	/**
	 * 更新企业车管
	 * @param csUnitUser
	 */
	public void updateCsUnitUser(CsUnitUser csUnitUser)
	{
		csUnitUserDao.updateCsUnitUser(csUnitUser);
	}
	/**
	 * 更新企业车管非空字段
	 * @param csUnitUser
	 */
	public void updateCsUnitUser$NotNull(CsUnitUser csUnitUser)
	{
		csUnitUserDao.updateCsUnitUser$NotNull(csUnitUser);
	}
	
	/**
	 * 根据ID删除一个企业车管
	 * @param id
	 */
	public void deleteCsUnitUserById(Long id)
	{
		csUnitUserDao.deleteCsUnitUserById(id);
	}
	/**
	 * 根据ID逻辑删除一个企业车管
	 * @param id
	 */
	public void removeCsUnitUserById(Long id)
	{
		csUnitUserDao.removeCsUnitUserById(id);
	}
	
	/**
	 * 根据条件更新企业车管
	 * @param values
	 * @param params
	 */
	public void updateCsUnitUserByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsUnitUserBy时无条件");
		csUnitUserDao.updateCsUnitUserByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除企业车管
	 * @param params
	 */
	public void deleteCsUnitUserByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsUnitUserBy时无条件");
		csUnitUserDao.deleteCsUnitUserByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsUnitUserDao getCsUnitUserDao()
	{
		return csUnitUserDao;
	}

	public void setCsUnitUserDao(ICsUnitUserDao csUnitUserDao)
	{
		this.csUnitUserDao = csUnitUserDao;
	}
}