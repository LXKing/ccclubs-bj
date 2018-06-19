package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsUnitPerson;

/**
 * 用车人员的Service接口
 * @author Joel
 */
@SuppressWarnings("unchecked")
public interface ICsUnitPersonService
{
	/**
	 * 获取所有用车人员
	 * @return
	 */
	public List<CsUnitPerson> getCsUnitPersonList(Map params,Integer size);
	
	/**
	 * 获取用车人员统计
	 * @return
	 */
	public List getCsUnitPersonStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取用车人员总数
	 * @return
	 */	
	public Long getCsUnitPersonCount(Map params);
	
	
	/**
	 * 获取用车人员自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsUnitPersonEval(String eval,Map params);

	/**
	 * 获取用车人员分页
	 * @return
	 */	
	public Page getCsUnitPersonPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取用车人员
	 * @param params
	 * @return
	 */
	public CsUnitPerson getCsUnitPerson(Map params);

	/**
	 * 根据ID取用车人员
	 * @param id
	 * @return
	 */
	public CsUnitPerson getCsUnitPersonById(Long id);


	/**
	 * 保存用车人员
	 * @param csUnitPerson
	 */
	public CsUnitPerson saveCsUnitPerson(CsUnitPerson csUnitPerson);

	/**
	 * 更新用车人员
	 * @param csUnitPerson
	 */
	public void updateCsUnitPerson(CsUnitPerson csUnitPerson);
	/**
	 * 更新用车人员非空字段
	 * @param csUnitPerson
	 */
	public void updateCsUnitPerson$NotNull(CsUnitPerson csUnitPerson);

	/**
	 * 根据ID删除一个用车人员
	 * @param id
	 */
	public void deleteCsUnitPersonById(Long id);
	/**
	 * 根据ID逻辑删除一个用车人员
	 * @param id
	 */
	public void removeCsUnitPersonById(Long id);
	/**
	 * 根据条件更新用车人员
	 * @param values
	 * @param params
	 */
	public void updateCsUnitPersonByConfirm(Map values, Map params);
	/**
	 * 根据条件删除用车人员
	 * @param params
	 */
	public void deleteCsUnitPersonByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}