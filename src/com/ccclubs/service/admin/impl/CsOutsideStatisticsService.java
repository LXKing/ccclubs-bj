package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;

import com.ccclubs.dao.ICsOutsideStatisticsDao;
import com.ccclubs.model.CsOutsideStatistics;
import com.ccclubs.service.admin.ICsOutsideStatisticsService;
import com.lazy3q.util.Function;
import com.lazy3q.web.util.Page;



/**
 *外勤整备项统计的Service实现
 * @author 飞啊飘啊
 */
public class CsOutsideStatisticsService implements ICsOutsideStatisticsService{

	ICsOutsideStatisticsDao  csOutsideStatisticsDao;

	
	public ICsOutsideStatisticsDao getCsOutsideStatisticsDao() {
		return csOutsideStatisticsDao;
	}

	public void setCsOutsideStatisticsDao(
			ICsOutsideStatisticsDao csOutsideStatisticsDao) {
		this.csOutsideStatisticsDao = csOutsideStatisticsDao;
	}

	@Override
	public Page getCsOutsideStatisticsPage(int page, int size, Map params) {
		return csOutsideStatisticsDao.getCsOutsideStatisticsPage( page, size,  params);
	}
	
	@Override
	public CsOutsideStatistics getCsOutsideStatisticsById(Long id) {
		return csOutsideStatisticsDao.getCsOutsideStatisticsById(id);
	}


	@Override
	public void removeCsOutsideStatisticsById(Long id) {
		csOutsideStatisticsDao.removeCsOutsideStatisticsById(id);
	}


	@Override
	public void deleteCsOutsideStatisticsById(Long id) {
		csOutsideStatisticsDao.deleteCsOutsideStatisticsById(id);
		
	}


	@Override
	public void updateCsOutsideStatistics$NotNull(CsOutsideStatistics csOutsideStatistics) {
		csOutsideStatisticsDao.updateCsOutsideStatistics$NotNull(csOutsideStatistics);
		
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	/**
	 * 根据条件查询数据
	 */
	@Override
	public CsOutsideStatistics getOutsideStatistics(Map map) {
		return csOutsideStatisticsDao.getOutsideStatistics(map);
	}

	
	/**
	 * 获取所有数据
	 * @return
	 */
	@Override
	public List<CsOutsideStatistics> getCsOutsideStatisticsList(Map params,
			Integer size) {
		
		return csOutsideStatisticsDao.getCsOutsideStatisticsList(params,size);
	}

	/**
	 * 获取数据总数
	 */
	@Override
	public Long getCsOutsideStatisticsCount(Map params) {
		return csOutsideStatisticsDao.getCsOutsideStatisticsCount(params);
	}

	
	/**
	 * 获取商品自定义求和表达式,比如求和:eval="sum(id)"
	 */
	@Override
	public <T> T getCsOutsideStatisticsEval(String eval, Map params) {
		
		return csOutsideStatisticsDao.getCsOutsideStatisticsEval(eval, params);
	}
	
	
	/**
	 * 根据条件查询数据 
	 */
	@Override
	public void updateCsOutsideStatisticsByConfirm(Map values, Map params) {
		if(params.isEmpty())
			throw new RuntimeException("updateCsOutsideStatisticsBy时无条件");
		csOutsideStatisticsDao.updateCsOutsideStatisticsByConfirm(values,params);
	}

	/**
	 * 更新数据
	 */
	@Override
	public void updateCsOutsideStatistics(CsOutsideStatistics csOutsideStatistics) {
		csOutsideStatisticsDao.updateCsOutsideStatistics(csOutsideStatistics);
	}

	/**
	 * 保存数据
	 */
	@Override
	public CsOutsideStatistics saveCsOutsideStatistics(CsOutsideStatistics csOutsideStatistics) {
		return csOutsideStatisticsDao.saveCsOutsideStatistics(csOutsideStatistics);
	}

	/**
	 * 获取车辆整备统计
	 */
	@Override
	public List getCsOutsideStatisticsStats(Map params, Map<String, Object> groups,Map<String, Object> sums) {
		
		return csOutsideStatisticsDao.getCsOutsideStatisticsStats( params,groups,sums);
	}

	
	
	@Override
	public Page getCsOutsideStatisticsOnPage(int page, int size, Map params) {
		
		return csOutsideStatisticsDao.getCsOutsideStatisticsOnPage(page,size,params);
	}
	
	
	
}
