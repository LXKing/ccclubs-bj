package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.ccclubs.model.CsGoods;
import com.ccclubs.model.CsOutsideStatistics;
import com.ccclubs.model.TbAbAffirm;
import com.lazy3q.util.Function;
import com.lazy3q.web.util.Page;

public interface ICsOutsideStatisticsService {

	/**
	 * 获取外勤整备项统计分页
	 * @return
	 */	
	public Page getCsOutsideStatisticsPage(int page,int size,Map params);
	
	
	public CsOutsideStatistics   getCsOutsideStatisticsById(Long id);
	
	
	public void  removeCsOutsideStatisticsById(Long id);
	
	public void deleteCsOutsideStatisticsById(Long id);
	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);
	
	/**
	 * 根据条件查询数据
	 * @param map
	 * @return
	 */
	public CsOutsideStatistics	getOutsideStatistics(Map map);
	
	/*
	 * 更新字段
	 */
	public void  updateCsOutsideStatistics$NotNull(CsOutsideStatistics csOutsideStatistics);
	
	//
	/**
	 * 获取所有数据
	 * @param params
	 * @param size
	 * @return
	 */
	public List<CsOutsideStatistics> getCsOutsideStatisticsList(Map params,Integer size);
	
	/**
	 * 获取数据总数
	 * @return
	 */	
	public Long getCsOutsideStatisticsCount(Map params);
	
	/**
	 * 获取商品自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsOutsideStatisticsEval(String eval,Map params);
	
	/**
	 * 根据条件更新商品
	 * @param values
	 * @param params
	 */
	public void updateCsOutsideStatisticsByConfirm(Map values, Map params);
	
	
	/**
	 * 更新数据
	 * @param 
	 */
	public void updateCsOutsideStatistics(CsOutsideStatistics csOutsideStatistics);

	
	/**
	 * 保存数据
	 * @param csGoods
	 */
	public CsOutsideStatistics saveCsOutsideStatistics(CsOutsideStatistics csOutsideStatistics);
	
	/**
	 * 获取车辆整备统计
	 * @return
	 */
	public List getCsOutsideStatisticsStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	
	/**
	 * 获取记录分页
	 * @return
	 */	
	public Page getCsOutsideStatisticsOnPage(int page,int size,Map params);
}
