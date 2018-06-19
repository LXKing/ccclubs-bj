package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsUpdate;

/**
 * App更新的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsUpdateDao
{
	/**
	 * 获取所有App更新
	 * @return
	 */
	public List<CsUpdate> getCsUpdateList(Map params,Integer size);
	
	/**
	 * 获取App更新统计
	 * @return
	 */
	public List getCsUpdateStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取App更新总数
	 * @return
	 */	
	public Long getCsUpdateCount(Map params);
	
	/**
	 * 获取App更新自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsUpdateEval(String eval,Map params);
	
	/**
	 * 获取App更新分页
	 * @return
	 */	
	public Page getCsUpdatePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取App更新
	 * @param params
	 * @return
	 */
	public CsUpdate getCsUpdate(Map params);	

	/**
	 * 根据ID取App更新
	 * @param id
	 * @return
	 */
	public CsUpdate getCsUpdateById(Long id);


	
	

	/**
	 * 保存App更新
	 * @param csUpdate
	 */
	public CsUpdate saveCsUpdate(CsUpdate csUpdate);

	/**
	 * 更新App更新
	 * @param csUpdate
	 */
	public void updateCsUpdate(CsUpdate csUpdate);
	/**
	 * 更新App更新非空字段
	 * @param csUpdate
	 */
	public void updateCsUpdate$NotNull(CsUpdate csUpdate);

	/**
	 * 根据ID删除一个App更新
	 * @param id
	 */
	public void deleteCsUpdateById(Long id);
	/**
	 * 根据ID逻辑删除一个App更新
	 * @param id
	 */
	public void removeCsUpdateById(Long id);
	/**
	 * 根据条件更新App更新
	 * @param values
	 * @param params
	 */
	public void updateCsUpdateByConfirm(Map values, Map params);
	/**
	 * 根据条件删除App更新
	 * @param params
	 */
	public void deleteCsUpdateByConfirm(Map params);

}