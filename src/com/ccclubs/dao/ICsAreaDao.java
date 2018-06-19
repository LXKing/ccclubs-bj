package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsArea;

/**
 * 网点区域的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsAreaDao
{
	/**
	 * 获取所有网点区域
	 * @return
	 */
	public List<CsArea> getCsAreaList(Map params,Integer size);
	
	/**
	 * 获取网点区域统计
	 * @return
	 */
	public List getCsAreaStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取网点区域总数
	 * @return
	 */	
	public Long getCsAreaCount(Map params);
	
	/**
	 * 获取网点区域自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsAreaEval(String eval,Map params);
	
	/**
	 * 获取网点区域分页
	 * @return
	 */	
	public Page getCsAreaPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取网点区域
	 * @param params
	 * @return
	 */
	public CsArea getCsArea(Map params);	

	/**
	 * 根据ID取网点区域
	 * @param id
	 * @return
	 */
	public CsArea getCsAreaById(Long id);


	
	

	/**
	 * 保存网点区域
	 * @param csArea
	 */
	public CsArea saveCsArea(CsArea csArea);

	/**
	 * 更新网点区域
	 * @param csArea
	 */
	public void updateCsArea(CsArea csArea);
	/**
	 * 更新网点区域非空字段
	 * @param csArea
	 */
	public void updateCsArea$NotNull(CsArea csArea);

	/**
	 * 根据ID删除一个网点区域
	 * @param id
	 */
	public void deleteCsAreaById(Long id);
	/**
	 * 根据ID逻辑删除一个网点区域
	 * @param id
	 */
	public void removeCsAreaById(Long id);
	/**
	 * 根据条件更新网点区域
	 * @param values
	 * @param params
	 */
	public void updateCsAreaByConfirm(Map values, Map params);
	/**
	 * 根据条件删除网点区域
	 * @param params
	 */
	public void deleteCsAreaByConfirm(Map params);

}