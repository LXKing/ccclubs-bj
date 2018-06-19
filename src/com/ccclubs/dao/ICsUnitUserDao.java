package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsUnitUser;

/**
 * 企业车管的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsUnitUserDao
{
	/**
	 * 获取所有企业车管
	 * @return
	 */
	public List<CsUnitUser> getCsUnitUserList(Map params,Integer size);
	
	/**
	 * 获取企业车管统计
	 * @return
	 */
	public List getCsUnitUserStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取企业车管总数
	 * @return
	 */	
	public Long getCsUnitUserCount(Map params);
	
	/**
	 * 获取企业车管自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsUnitUserEval(String eval,Map params);
	
	/**
	 * 获取企业车管分页
	 * @return
	 */	
	public Page getCsUnitUserPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取企业车管
	 * @param params
	 * @return
	 */
	public CsUnitUser getCsUnitUser(Map params);	

	/**
	 * 根据ID取企业车管
	 * @param id
	 * @return
	 */
	public CsUnitUser getCsUnitUserById(Long id);


	
	

	/**
	 * 保存企业车管
	 * @param csUnitUser
	 */
	public CsUnitUser saveCsUnitUser(CsUnitUser csUnitUser);

	/**
	 * 更新企业车管
	 * @param csUnitUser
	 */
	public void updateCsUnitUser(CsUnitUser csUnitUser);
	/**
	 * 更新企业车管非空字段
	 * @param csUnitUser
	 */
	public void updateCsUnitUser$NotNull(CsUnitUser csUnitUser);

	/**
	 * 根据ID删除一个企业车管
	 * @param id
	 */
	public void deleteCsUnitUserById(Long id);
	/**
	 * 根据ID逻辑删除一个企业车管
	 * @param id
	 */
	public void removeCsUnitUserById(Long id);
	/**
	 * 根据条件更新企业车管
	 * @param values
	 * @param params
	 */
	public void updateCsUnitUserByConfirm(Map values, Map params);
	/**
	 * 根据条件删除企业车管
	 * @param params
	 */
	public void deleteCsUnitUserByConfirm(Map params);

}