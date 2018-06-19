package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsWorkRecordDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsWorkRecord;
import com.ccclubs.service.admin.ICsWorkRecordService;
import com.lazy3q.web.helper.$;

/**
 * 后勤工作记录的Service实现
 * @author Joel
 */
public class CsWorkRecordService implements ICsWorkRecordService
{
	ICsWorkRecordDao csWorkRecordDao;
	

	/**
	 * 获取所有后勤工作记录
	 * @return
	 */
	public List<CsWorkRecord> getCsWorkRecordList(Map params,Integer size)
	{
		return csWorkRecordDao.getCsWorkRecordList(params,size);
	}
	
	/**
	 * 获取后勤工作记录统计
	 * @return
	 */
	public List getCsWorkRecordStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csWorkRecordDao.getCsWorkRecordStats(params,groups,sums);
	}
	
	/**
	 * 获取后勤工作记录总数
	 * @return
	 */
	public Long getCsWorkRecordCount(Map params)
	{
		return csWorkRecordDao.getCsWorkRecordCount(params);
	}
	
	/**
	 * 获取后勤工作记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsWorkRecordEval(String eval,Map params)
	{
		return csWorkRecordDao.getCsWorkRecordEval(eval,params);
	}

	/**
	 * 获取后勤工作记录分页
	 * @return
	 */
	public Page getCsWorkRecordPage(int page,int size,Map params)
	{
		return csWorkRecordDao.getCsWorkRecordPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取后勤工作记录
	 * @param params
	 * @return
	 */
	public CsWorkRecord getCsWorkRecord(Map params)
	{
		return csWorkRecordDao.getCsWorkRecord(params);
	}

	/**
	 * 根据ID取后勤工作记录
	 * @param id
	 * @return
	 */
	public CsWorkRecord getCsWorkRecordById(Long id)
	{
		return csWorkRecordDao.getCsWorkRecordById(id);
	}

	/**
	 * 保存后勤工作记录
	 * @param csWorkRecord
	 */
	public CsWorkRecord saveCsWorkRecord(CsWorkRecord csWorkRecord)
	{
		return csWorkRecordDao.saveCsWorkRecord(csWorkRecord);
	}
	
	/**
	 * 更新后勤工作记录
	 * @param csWorkRecord
	 */
	public void updateCsWorkRecord(CsWorkRecord csWorkRecord)
	{
		csWorkRecordDao.updateCsWorkRecord(csWorkRecord);
	}
	/**
	 * 更新后勤工作记录非空字段
	 * @param csWorkRecord
	 */
	public void updateCsWorkRecord$NotNull(CsWorkRecord csWorkRecord)
	{
		csWorkRecordDao.updateCsWorkRecord$NotNull(csWorkRecord);
	}
	
	/**
	 * 根据ID删除一个后勤工作记录
	 * @param id
	 */
	public void deleteCsWorkRecordById(Long id)
	{
		csWorkRecordDao.deleteCsWorkRecordById(id);
	}
	
	/**
	 * 根据ID逻辑删除一个后勤工作记录
	 * @param id
	 */
	public void removeCsWorkRecordById(Long id)
	{
		csWorkRecordDao.removeCsWorkRecordById(id);
	}
	
	/**
	 * 根据条件更新后勤工作记录
	 * @param values
	 * @param params
	 */
	public void updateCsWorkRecordByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsWorkRecordBy时无条件");
		csWorkRecordDao.updateCsWorkRecordByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除后勤工作记录
	 * @param params
	 */
	public void deleteCsWorkRecordByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsWorkRecordBy时无条件");
		csWorkRecordDao.deleteCsWorkRecordByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsWorkRecordDao getCsWorkRecordDao()
	{
		return csWorkRecordDao;
	}

	public void setCsWorkRecordDao(ICsWorkRecordDao csWorkRecordDao)
	{
		this.csWorkRecordDao = csWorkRecordDao;
	}
}