package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsEventHeed;

/**
 * 事件关注的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsEventHeedDao
{
	/**
	 * 获取所有事件关注
	 * @return
	 */
	public List<CsEventHeed> getCsEventHeedList(Map params,Integer size);
	
	/**
	 * 获取事件关注统计
	 * @return
	 */
	public List getCsEventHeedStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取事件关注总数
	 * @return
	 */	
	public Long getCsEventHeedCount(Map params);
	
	/**
	 * 获取事件关注自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsEventHeedEval(String eval,Map params);
	
	/**
	 * 获取事件关注分页
	 * @return
	 */	
	public Page getCsEventHeedPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取事件关注
	 * @param params
	 * @return
	 */
	public CsEventHeed getCsEventHeed(Map params);	

	/**
	 * 根据ID取事件关注
	 * @param id
	 * @return
	 */
	public CsEventHeed getCsEventHeedById(Long id);


	
	

	/**
	 * 保存事件关注
	 * @param csEventHeed
	 */
	public CsEventHeed saveCsEventHeed(CsEventHeed csEventHeed);

	/**
	 * 更新事件关注
	 * @param csEventHeed
	 */
	public void updateCsEventHeed(CsEventHeed csEventHeed);
	/**
	 * 更新事件关注非空字段
	 * @param csEventHeed
	 */
	public void updateCsEventHeed$NotNull(CsEventHeed csEventHeed);

	/**
	 * 根据ID删除一个事件关注
	 * @param id
	 */
	public void deleteCsEventHeedById(Long id);
	/**
	 * 根据ID逻辑删除一个事件关注
	 * @param id
	 */
	public void removeCsEventHeedById(Long id);
	/**
	 * 根据条件更新事件关注
	 * @param values
	 * @param params
	 */
	public void updateCsEventHeedByConfirm(Map values, Map params);
	/**
	 * 根据条件删除事件关注
	 * @param params
	 */
	public void deleteCsEventHeedByConfirm(Map params);

}