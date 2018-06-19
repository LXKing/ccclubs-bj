package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsLimitTime;

/**
 * 交通限行的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsLimitTimeDao
{
	/**
	 * 获取所有交通限行
	 * @return
	 */
	public List<CsLimitTime> getCsLimitTimeList(Map params,Integer size);
	
	/**
	 * 获取交通限行统计
	 * @return
	 */
	public List getCsLimitTimeStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取交通限行总数
	 * @return
	 */	
	public Long getCsLimitTimeCount(Map params);
	
	/**
	 * 获取交通限行自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsLimitTimeEval(String eval,Map params);
	
	/**
	 * 获取交通限行分页
	 * @return
	 */	
	public Page getCsLimitTimePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取交通限行
	 * @param params
	 * @return
	 */
	public CsLimitTime getCsLimitTime(Map params);	

	/**
	 * 根据ID取交通限行
	 * @param id
	 * @return
	 */
	public CsLimitTime getCsLimitTimeById(Long id);


	
	

	/**
	 * 保存交通限行
	 * @param csLimitTime
	 */
	public CsLimitTime saveCsLimitTime(CsLimitTime csLimitTime);

	/**
	 * 更新交通限行
	 * @param csLimitTime
	 */
	public void updateCsLimitTime(CsLimitTime csLimitTime);
	/**
	 * 更新交通限行非空字段
	 * @param csLimitTime
	 */
	public void updateCsLimitTime$NotNull(CsLimitTime csLimitTime);

	/**
	 * 根据ID删除一个交通限行
	 * @param id
	 */
	public void deleteCsLimitTimeById(Long id);
	/**
	 * 根据条件更新交通限行
	 * @param values
	 * @param params
	 */
	public void updateCsLimitTimeByConfirm(Map values, Map params);
	/**
	 * 根据条件删除交通限行
	 * @param params
	 */
	public void deleteCsLimitTimeByConfirm(Map params);

}