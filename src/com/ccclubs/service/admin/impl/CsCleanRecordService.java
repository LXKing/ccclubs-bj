package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsCleanRecordDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCleanRecord;
import com.ccclubs.service.admin.ICsCleanRecordService;
import com.lazy3q.web.helper.$;

/**
 * 保洁记录的Service实现
 * @author Joel
 */
public class CsCleanRecordService implements ICsCleanRecordService
{
	ICsCleanRecordDao csCleanRecordDao;
	

	/**
	 * 获取所有保洁记录
	 * @return
	 */
	public List<CsCleanRecord> getCsCleanRecordList(Map params,Integer size)
	{
		return csCleanRecordDao.getCsCleanRecordList(params,size);
	}
	
	/**
	 * 获取保洁记录统计
	 * @return
	 */
	public List getCsCleanRecordStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csCleanRecordDao.getCsCleanRecordStats(params,groups,sums);
	}
	
	/**
	 * 获取保洁记录总数
	 * @return
	 */
	public Long getCsCleanRecordCount(Map params)
	{
		return csCleanRecordDao.getCsCleanRecordCount(params);
	}
	
	/**
	 * 获取保洁记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCleanRecordEval(String eval,Map params)
	{
		return csCleanRecordDao.getCsCleanRecordEval(eval,params);
	}

	/**
	 * 获取保洁记录分页
	 * @return
	 */
	public Page getCsCleanRecordPage(int page,int size,Map params)
	{
		return csCleanRecordDao.getCsCleanRecordPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取保洁记录
	 * @param params
	 * @return
	 */
	public CsCleanRecord getCsCleanRecord(Map params)
	{
		return csCleanRecordDao.getCsCleanRecord(params);
	}

	/**
	 * 根据ID取保洁记录
	 * @param id
	 * @return
	 */
	public CsCleanRecord getCsCleanRecordById(Long id)
	{
		return csCleanRecordDao.getCsCleanRecordById(id);
	}

	/**
	 * 保存保洁记录
	 * @param csCleanRecord
	 */
	public CsCleanRecord saveCsCleanRecord(CsCleanRecord csCleanRecord)
	{
		return csCleanRecordDao.saveCsCleanRecord(csCleanRecord);
	}
	
	/**
	 * 更新保洁记录
	 * @param csCleanRecord
	 */
	public void updateCsCleanRecord(CsCleanRecord csCleanRecord)
	{
		csCleanRecordDao.updateCsCleanRecord(csCleanRecord);
	}
	/**
	 * 更新保洁记录非空字段
	 * @param csCleanRecord
	 */
	public void updateCsCleanRecord$NotNull(CsCleanRecord csCleanRecord)
	{
		csCleanRecordDao.updateCsCleanRecord$NotNull(csCleanRecord);
	}
	
	/**
	 * 根据ID删除一个保洁记录
	 * @param id
	 */
	public void deleteCsCleanRecordById(Long id)
	{
		csCleanRecordDao.deleteCsCleanRecordById(id);
	}
	
	/**
	 * 根据ID逻辑删除一个保洁记录
	 * @param id
	 */
	public void removeCsCleanRecordById(Long id)
	{
		csCleanRecordDao.removeCsCleanRecordById(id);
	}
	
	/**
	 * 根据条件更新保洁记录
	 * @param values
	 * @param params
	 */
	public void updateCsCleanRecordByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsCleanRecordBy时无条件");
		csCleanRecordDao.updateCsCleanRecordByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除保洁记录
	 * @param params
	 */
	public void deleteCsCleanRecordByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsCleanRecordBy时无条件");
		csCleanRecordDao.deleteCsCleanRecordByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsCleanRecordDao getCsCleanRecordDao()
	{
		return csCleanRecordDao;
	}

	public void setCsCleanRecordDao(ICsCleanRecordDao csCleanRecordDao)
	{
		this.csCleanRecordDao = csCleanRecordDao;
	}
}