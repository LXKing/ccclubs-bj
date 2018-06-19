package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsMarketLogDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsMarketLog;
import com.ccclubs.service.admin.ICsMarketLogService;
import com.lazy3q.web.helper.$;

/**
 * 营销跟踪的Service实现
 * @author 飞啊飘啊
 */
public class CsMarketLogService implements ICsMarketLogService
{
	ICsMarketLogDao csMarketLogDao;
	

	/**
	 * 获取所有营销跟踪
	 * @return
	 */
	public List<CsMarketLog> getCsMarketLogList(Map params,Integer size)
	{
		return csMarketLogDao.getCsMarketLogList(params,size);
	}
	
	/**
	 * 获取营销跟踪统计
	 * @return
	 */
	public List getCsMarketLogStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csMarketLogDao.getCsMarketLogStats(params,groups,sums);
	}
	
	/**
	 * 获取营销跟踪总数
	 * @return
	 */
	public Long getCsMarketLogCount(Map params)
	{
		return csMarketLogDao.getCsMarketLogCount(params);
	}
	
	/**
	 * 获取营销跟踪自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsMarketLogEval(String eval,Map params)
	{
		return csMarketLogDao.getCsMarketLogEval(eval,params);
	}

	/**
	 * 获取营销跟踪分页
	 * @return
	 */
	public Page getCsMarketLogPage(int page,int size,Map params)
	{
		return csMarketLogDao.getCsMarketLogPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取营销跟踪
	 * @param params
	 * @return
	 */
	public CsMarketLog getCsMarketLog(Map params)
	{
		return csMarketLogDao.getCsMarketLog(params);
	}

	/**
	 * 根据ID取营销跟踪
	 * @param id
	 * @return
	 */
	public CsMarketLog getCsMarketLogById(Long id)
	{
		return csMarketLogDao.getCsMarketLogById(id);
	}

	/**
	 * 保存营销跟踪
	 * @param csMarketLog
	 */
	public CsMarketLog saveCsMarketLog(CsMarketLog csMarketLog)
	{
		return csMarketLogDao.saveCsMarketLog(csMarketLog);
	}
	
	/**
	 * 更新营销跟踪
	 * @param csMarketLog
	 */
	public void updateCsMarketLog(CsMarketLog csMarketLog)
	{
		csMarketLogDao.updateCsMarketLog(csMarketLog);
	}
	/**
	 * 更新营销跟踪非空字段
	 * @param csMarketLog
	 */
	public void updateCsMarketLog$NotNull(CsMarketLog csMarketLog)
	{
		csMarketLogDao.updateCsMarketLog$NotNull(csMarketLog);
	}
	
	/**
	 * 根据ID删除一个营销跟踪
	 * @param id
	 */
	public void deleteCsMarketLogById(Long id)
	{
		csMarketLogDao.deleteCsMarketLogById(id);
	}
	/**
	 * 根据ID逻辑删除一个营销跟踪
	 * @param id
	 */
	public void removeCsMarketLogById(Long id)
	{
		csMarketLogDao.removeCsMarketLogById(id);
	}
	
	/**
	 * 根据条件更新营销跟踪
	 * @param values
	 * @param params
	 */
	public void updateCsMarketLogByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsMarketLogBy时无条件");
		csMarketLogDao.updateCsMarketLogByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除营销跟踪
	 * @param params
	 */
	public void deleteCsMarketLogByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsMarketLogBy时无条件");
		csMarketLogDao.deleteCsMarketLogByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsMarketLogDao getCsMarketLogDao()
	{
		return csMarketLogDao;
	}

	public void setCsMarketLogDao(ICsMarketLogDao csMarketLogDao)
	{
		this.csMarketLogDao = csMarketLogDao;
	}
}