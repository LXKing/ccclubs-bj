package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsGrowType;

/**
 * 成长分类型的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsGrowTypeService
{
	/**
	 * 获取所有成长分类型
	 * @return
	 */
	public List<CsGrowType> getCsGrowTypeList(Map params,Integer size);
	
	/**
	 * 获取成长分类型统计
	 * @return
	 */
	public List getCsGrowTypeStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取成长分类型总数
	 * @return
	 */	
	public Long getCsGrowTypeCount(Map params);
	
	
	/**
	 * 获取成长分类型自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsGrowTypeEval(String eval,Map params);

	/**
	 * 获取成长分类型分页
	 * @return
	 */	
	public Page getCsGrowTypePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取成长分类型
	 * @param params
	 * @return
	 */
	public CsGrowType getCsGrowType(Map params);

	/**
	 * 根据ID取成长分类型
	 * @param id
	 * @return
	 */
	public CsGrowType getCsGrowTypeById(Long id);


	/**
	 * 保存成长分类型
	 * @param csGrowType
	 */
	public CsGrowType saveCsGrowType(CsGrowType csGrowType);

	/**
	 * 更新成长分类型
	 * @param csGrowType
	 */
	public void updateCsGrowType(CsGrowType csGrowType);
	/**
	 * 更新成长分类型非空字段
	 * @param csGrowType
	 */
	public void updateCsGrowType$NotNull(CsGrowType csGrowType);

	/**
	 * 根据ID删除一个成长分类型
	 * @param id
	 */
	public void deleteCsGrowTypeById(Long id);
	/**
	 * 根据ID逻辑删除一个成长分类型
	 * @param id
	 */
	public void removeCsGrowTypeById(Long id);
	/**
	 * 根据条件更新成长分类型
	 * @param values
	 * @param params
	 */
	public void updateCsGrowTypeByConfirm(Map values, Map params);
	/**
	 * 根据条件删除成长分类型
	 * @param params
	 */
	public void deleteCsGrowTypeByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}