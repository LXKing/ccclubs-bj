package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsIntegralRecordDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsIntegralRecord;
import com.ccclubs.service.admin.ICsIntegralRecordService;
import com.lazy3q.web.helper.$;

/**
 * 积分记录的Service实现
 * @author 飞啊飘啊
 */
public class CsIntegralRecordService implements ICsIntegralRecordService
{
	ICsIntegralRecordDao csIntegralRecordDao;
	

	/**
	 * 获取所有积分记录
	 * @return
	 */
	public List<CsIntegralRecord> getCsIntegralRecordList(Map params,Integer size)
	{
		return csIntegralRecordDao.getCsIntegralRecordList(params,size);
	}
	
	/**
	 * 获取积分记录统计
	 * @return
	 */
	public List getCsIntegralRecordStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csIntegralRecordDao.getCsIntegralRecordStats(params,groups,sums);
	}
	
	/**
	 * 获取积分记录总数
	 * @return
	 */
	public Long getCsIntegralRecordCount(Map params)
	{
		return csIntegralRecordDao.getCsIntegralRecordCount(params);
	}
	
	/**
	 * 获取积分记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsIntegralRecordEval(String eval,Map params)
	{
		return csIntegralRecordDao.getCsIntegralRecordEval(eval,params);
	}

	/**
	 * 获取积分记录分页
	 * @return
	 */
	public Page getCsIntegralRecordPage(int page,int size,Map params)
	{
		return csIntegralRecordDao.getCsIntegralRecordPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取积分记录
	 * @param params
	 * @return
	 */
	public CsIntegralRecord getCsIntegralRecord(Map params)
	{
		return csIntegralRecordDao.getCsIntegralRecord(params);
	}

	/**
	 * 根据ID取积分记录
	 * @param id
	 * @return
	 */
	public CsIntegralRecord getCsIntegralRecordById(Long id)
	{
		return csIntegralRecordDao.getCsIntegralRecordById(id);
	}

	/**
	 * 保存积分记录
	 * @param csIntegralRecord
	 */
	public CsIntegralRecord saveCsIntegralRecord(CsIntegralRecord csIntegralRecord)
	{
		return csIntegralRecordDao.saveCsIntegralRecord(csIntegralRecord);
	}
	
	/**
	 * 更新积分记录
	 * @param csIntegralRecord
	 */
	public void updateCsIntegralRecord(CsIntegralRecord csIntegralRecord)
	{
		csIntegralRecordDao.updateCsIntegralRecord(csIntegralRecord);
	}
	/**
	 * 更新积分记录非空字段
	 * @param csIntegralRecord
	 */
	public void updateCsIntegralRecord$NotNull(CsIntegralRecord csIntegralRecord)
	{
		csIntegralRecordDao.updateCsIntegralRecord$NotNull(csIntegralRecord);
	}
	
	/**
	 * 根据ID删除一个积分记录
	 * @param id
	 */
	public void deleteCsIntegralRecordById(Long id)
	{
		csIntegralRecordDao.deleteCsIntegralRecordById(id);
	}
	
	/**
	 * 根据条件更新积分记录
	 * @param values
	 * @param params
	 */
	public void updateCsIntegralRecordByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsIntegralRecordBy时无条件");
		csIntegralRecordDao.updateCsIntegralRecordByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除积分记录
	 * @param params
	 */
	public void deleteCsIntegralRecordByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsIntegralRecordBy时无条件");
		csIntegralRecordDao.deleteCsIntegralRecordByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsIntegralRecordDao getCsIntegralRecordDao()
	{
		return csIntegralRecordDao;
	}

	public void setCsIntegralRecordDao(ICsIntegralRecordDao csIntegralRecordDao)
	{
		this.csIntegralRecordDao = csIntegralRecordDao;
	}
}