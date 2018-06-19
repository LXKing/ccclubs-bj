package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsPlace;

/**
 * 省市地区的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsPlaceDao
{
	/**
	 * 获取所有省市地区
	 * @return
	 */
	public List<CsPlace> getCsPlaceList(Map params,Integer size);
	
	/**
	 * 获取省市地区统计
	 * @return
	 */
	public List getCsPlaceStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取省市地区总数
	 * @return
	 */	
	public Long getCsPlaceCount(Map params);
	
	/**
	 * 获取省市地区自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsPlaceEval(String eval,Map params);
	
	/**
	 * 获取省市地区分页
	 * @return
	 */	
	public Page getCsPlacePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取省市地区
	 * @param params
	 * @return
	 */
	public CsPlace getCsPlace(Map params);	

	/**
	 * 根据ID取省市地区
	 * @param id
	 * @return
	 */
	public CsPlace getCsPlaceById(Long id);


	
	

	/**
	 * 保存省市地区
	 * @param csPlace
	 */
	public CsPlace saveCsPlace(CsPlace csPlace);

	/**
	 * 更新省市地区
	 * @param csPlace
	 */
	public void updateCsPlace(CsPlace csPlace);
	/**
	 * 更新省市地区非空字段
	 * @param csPlace
	 */
	public void updateCsPlace$NotNull(CsPlace csPlace);

	/**
	 * 根据ID删除一个省市地区
	 * @param id
	 */
	public void deleteCsPlaceById(Long id);
	/**
	 * 根据条件更新省市地区
	 * @param values
	 * @param params
	 */
	public void updateCsPlaceByConfirm(Map values, Map params);
	/**
	 * 根据条件删除省市地区
	 * @param params
	 */
	public void deleteCsPlaceByConfirm(Map params);

}