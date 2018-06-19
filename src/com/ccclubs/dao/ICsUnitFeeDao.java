package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsUnitFee;

/**
 * 价格配置的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsUnitFeeDao
{
	/**
	 * 获取所有价格配置
	 * @return
	 */
	public List<CsUnitFee> getCsUnitFeeList(Map params,Integer size);
	
	/**
	 * 获取价格配置统计
	 * @return
	 */
	public List getCsUnitFeeStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取价格配置总数
	 * @return
	 */	
	public Long getCsUnitFeeCount(Map params);
	
	/**
	 * 获取价格配置自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsUnitFeeEval(String eval,Map params);
	
	/**
	 * 获取价格配置分页
	 * @return
	 */	
	public Page getCsUnitFeePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取价格配置
	 * @param params
	 * @return
	 */
	public CsUnitFee getCsUnitFee(Map params);	

	/**
	 * 根据ID取价格配置
	 * @param id
	 * @return
	 */
	public CsUnitFee getCsUnitFeeById(Long id);


	
	

	/**
	 * 保存价格配置
	 * @param csUnitFee
	 */
	public CsUnitFee saveCsUnitFee(CsUnitFee csUnitFee);

	/**
	 * 更新价格配置
	 * @param csUnitFee
	 */
	public void updateCsUnitFee(CsUnitFee csUnitFee);
	/**
	 * 更新价格配置非空字段
	 * @param csUnitFee
	 */
	public void updateCsUnitFee$NotNull(CsUnitFee csUnitFee);

	/**
	 * 根据ID删除一个价格配置
	 * @param id
	 */
	public void deleteCsUnitFeeById(Long id);
	/**
	 * 根据条件更新价格配置
	 * @param values
	 * @param params
	 */
	public void updateCsUnitFeeByConfirm(Map values, Map params);
	/**
	 * 根据条件删除价格配置
	 * @param params
	 */
	public void deleteCsUnitFeeByConfirm(Map params);

}