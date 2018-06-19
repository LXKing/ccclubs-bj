package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsUserType;

/**
 * 计费方式的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsUserTypeService
{
	/**
	 * 获取所有计费方式
	 * @return
	 */
	public List<CsUserType> getCsUserTypeList(Map params,Integer size);
	
	/**
	 * 获取计费方式统计
	 * @return
	 */
	public List getCsUserTypeStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取计费方式总数
	 * @return
	 */	
	public Long getCsUserTypeCount(Map params);
	
	
	/**
	 * 获取计费方式自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsUserTypeEval(String eval,Map params);

	/**
	 * 获取计费方式分页
	 * @return
	 */	
	public Page getCsUserTypePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取计费方式
	 * @param params
	 * @return
	 */
	public CsUserType getCsUserType(Map params);

	/**
	 * 根据ID取计费方式
	 * @param id
	 * @return
	 */
	public CsUserType getCsUserTypeById(Long id);


	/**
	 * 保存计费方式
	 * @param csUserType
	 */
	public CsUserType saveCsUserType(CsUserType csUserType);

	/**
	 * 更新计费方式
	 * @param csUserType
	 */
	public void updateCsUserType(CsUserType csUserType);
	/**
	 * 更新计费方式非空字段
	 * @param csUserType
	 */
	public void updateCsUserType$NotNull(CsUserType csUserType);

	/**
	 * 根据ID删除一个计费方式
	 * @param id
	 */
	public void deleteCsUserTypeById(Long id);
	/**
	 * 根据ID逻辑删除一个计费方式
	 * @param id
	 */
	public void removeCsUserTypeById(Long id);
	/**
	 * 根据条件更新计费方式
	 * @param values
	 * @param params
	 */
	public void updateCsUserTypeByConfirm(Map values, Map params);
	/**
	 * 根据条件删除计费方式
	 * @param params
	 */
	public void deleteCsUserTypeByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}