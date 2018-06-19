package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsFreeHour;

/**
 * 免费小时的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsFreeHourDao
{
	/**
	 * 获取所有免费小时
	 * @return
	 */
	public List<CsFreeHour> getCsFreeHourList(Map params,Integer size);
	
	/**
	 * 获取免费小时统计
	 * @return
	 */
	public List getCsFreeHourStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取免费小时总数
	 * @return
	 */	
	public Long getCsFreeHourCount(Map params);
	
	/**
	 * 获取免费小时自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsFreeHourEval(String eval,Map params);
	
	/**
	 * 获取免费小时分页
	 * @return
	 */	
	public Page getCsFreeHourPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取免费小时
	 * @param params
	 * @return
	 */
	public CsFreeHour getCsFreeHour(Map params);	

	/**
	 * 根据ID取免费小时
	 * @param id
	 * @return
	 */
	public CsFreeHour getCsFreeHourById(Long id);


	
	

	/**
	 * 保存免费小时
	 * @param csFreeHour
	 */
	public CsFreeHour saveCsFreeHour(CsFreeHour csFreeHour);

	/**
	 * 更新免费小时
	 * @param csFreeHour
	 */
	public void updateCsFreeHour(CsFreeHour csFreeHour);
	/**
	 * 更新免费小时非空字段
	 * @param csFreeHour
	 */
	public void updateCsFreeHour$NotNull(CsFreeHour csFreeHour);

	/**
	 * 根据ID删除一个免费小时
	 * @param id
	 */
	public void deleteCsFreeHourById(Long id);
	/**
	 * 根据ID逻辑删除一个免费小时
	 * @param id
	 */
	public void removeCsFreeHourById(Long id);
	/**
	 * 根据条件更新免费小时
	 * @param values
	 * @param params
	 */
	public void updateCsFreeHourByConfirm(Map values, Map params);
	/**
	 * 根据条件删除免费小时
	 * @param params
	 */
	public void deleteCsFreeHourByConfirm(Map params);

}