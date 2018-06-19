package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsElecHistory;

/**
 * 电桩历史数据的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsElecHistoryDao
{
	/**
	 * 获取所有电桩历史数据
	 * @return
	 */
	public List<CsElecHistory> getCsElecHistoryList(Map params,Integer size);
	
	/**
	 * 获取电桩历史数据统计
	 * @return
	 */
	public List getCsElecHistoryStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取电桩历史数据总数
	 * @return
	 */	
	public Long getCsElecHistoryCount(Map params);
	
	/**
	 * 获取电桩历史数据自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsElecHistoryEval(String eval,Map params);
	
	/**
	 * 获取电桩历史数据分页
	 * @return
	 */	
	public Page getCsElecHistoryPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取电桩历史数据
	 * @param params
	 * @return
	 */
	public CsElecHistory getCsElecHistory(Map params);	

	/**
	 * 根据ID取电桩历史数据
	 * @param id
	 * @return
	 */
	public CsElecHistory getCsElecHistoryById(Long id);


	
	

	/**
	 * 保存电桩历史数据
	 * @param csElecHistory
	 */
	public CsElecHistory saveCsElecHistory(CsElecHistory csElecHistory);

	/**
	 * 更新电桩历史数据
	 * @param csElecHistory
	 */
	public void updateCsElecHistory(CsElecHistory csElecHistory);
	/**
	 * 更新电桩历史数据非空字段
	 * @param csElecHistory
	 */
	public void updateCsElecHistory$NotNull(CsElecHistory csElecHistory);

	/**
	 * 根据ID删除一个电桩历史数据
	 * @param id
	 */
	public void deleteCsElecHistoryById(Long id);
	/**
	 * 根据条件更新电桩历史数据
	 * @param values
	 * @param params
	 */
	public void updateCsElecHistoryByConfirm(Map values, Map params);
	/**
	 * 根据条件删除电桩历史数据
	 * @param params
	 */
	public void deleteCsElecHistoryByConfirm(Map params);

}