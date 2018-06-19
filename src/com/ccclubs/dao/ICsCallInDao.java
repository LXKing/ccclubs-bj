package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCallIn;

/**
 * 客户来电的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsCallInDao
{
	/**
	 * 获取所有客户来电
	 * @return
	 */
	public List<CsCallIn> getCsCallInList(Map params,Integer size);
	
	/**
	 * 获取客户来电统计
	 * @return
	 */
	public List getCsCallInStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取客户来电总数
	 * @return
	 */	
	public Long getCsCallInCount(Map params);
	
	/**
	 * 获取客户来电自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCallInEval(String eval,Map params);
	
	/**
	 * 获取客户来电分页
	 * @return
	 */	
	public Page getCsCallInPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取客户来电
	 * @param params
	 * @return
	 */
	public CsCallIn getCsCallIn(Map params);	

	/**
	 * 根据ID取客户来电
	 * @param id
	 * @return
	 */
	public CsCallIn getCsCallInById(Long id);


	
	

	/**
	 * 保存客户来电
	 * @param csCallIn
	 */
	public CsCallIn saveCsCallIn(CsCallIn csCallIn);

	/**
	 * 更新客户来电
	 * @param csCallIn
	 */
	public void updateCsCallIn(CsCallIn csCallIn);
	/**
	 * 更新客户来电非空字段
	 * @param csCallIn
	 */
	public void updateCsCallIn$NotNull(CsCallIn csCallIn);

	/**
	 * 根据ID删除一个客户来电
	 * @param id
	 */
	public void deleteCsCallInById(Long id);
	/**
	 * 根据ID逻辑删除一个客户来电
	 * @param id
	 */
	public void removeCsCallInById(Long id);
	/**
	 * 根据条件更新客户来电
	 * @param values
	 * @param params
	 */
	public void updateCsCallInByConfirm(Map values, Map params);
	/**
	 * 根据条件删除客户来电
	 * @param params
	 */
	public void deleteCsCallInByConfirm(Map params);

}