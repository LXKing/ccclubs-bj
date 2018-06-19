package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvProperty;

/**
 * 系统属性的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ISrvPropertyDao
{
	/**
	 * 获取所有系统属性
	 * @return
	 */
	public List<SrvProperty> getSrvPropertyList(Map params,Integer size);
	
	/**
	 * 获取系统属性统计
	 * @return
	 */
	public List getSrvPropertyStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取系统属性总数
	 * @return
	 */	
	public Long getSrvPropertyCount(Map params);
	
	/**
	 * 获取系统属性自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getSrvPropertyEval(String eval,Map params);
	
	/**
	 * 获取系统属性分页
	 * @return
	 */	
	public Page getSrvPropertyPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取系统属性
	 * @param params
	 * @return
	 */
	public SrvProperty getSrvProperty(Map params);	

	/**
	 * 根据ID取系统属性
	 * @param id
	 * @return
	 */
	public SrvProperty getSrvPropertyById(Long id);


	
	

	/**
	 * 保存系统属性
	 * @param srvProperty
	 */
	public SrvProperty saveSrvProperty(SrvProperty srvProperty);

	/**
	 * 更新系统属性
	 * @param srvProperty
	 */
	public void updateSrvProperty(SrvProperty srvProperty);
	/**
	 * 更新系统属性非空字段
	 * @param srvProperty
	 */
	public void updateSrvProperty$NotNull(SrvProperty srvProperty);

	/**
	 * 根据ID删除一个系统属性
	 * @param id
	 */
	public void deleteSrvPropertyById(Long id);
	/**
	 * 根据条件更新系统属性
	 * @param values
	 * @param params
	 */
	public void updateSrvPropertyByConfirm(Map values, Map params);
	/**
	 * 根据条件删除系统属性
	 * @param params
	 */
	public void deleteSrvPropertyByConfirm(Map params);

}