package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsGrowRecordDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsGrowRecord;
import com.ccclubs.service.admin.ICsGrowRecordService;
import com.lazy3q.web.helper.$;

/**
 * 成长记录的Service实现
 * @author 飞啊飘啊
 */
public class CsGrowRecordService implements ICsGrowRecordService
{
	ICsGrowRecordDao csGrowRecordDao;
	

	/**
	 * 获取所有成长记录
	 * @return
	 */
	public List<CsGrowRecord> getCsGrowRecordList(Map params,Integer size)
	{
		return csGrowRecordDao.getCsGrowRecordList(params,size);
	}
	
	/**
	 * 获取成长记录统计
	 * @return
	 */
	public List getCsGrowRecordStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csGrowRecordDao.getCsGrowRecordStats(params,groups,sums);
	}
	
	/**
	 * 获取成长记录总数
	 * @return
	 */
	public Long getCsGrowRecordCount(Map params)
	{
		return csGrowRecordDao.getCsGrowRecordCount(params);
	}
	
	/**
	 * 获取成长记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsGrowRecordEval(String eval,Map params)
	{
		return csGrowRecordDao.getCsGrowRecordEval(eval,params);
	}

	/**
	 * 获取成长记录分页
	 * @return
	 */
	public Page getCsGrowRecordPage(int page,int size,Map params)
	{
		return csGrowRecordDao.getCsGrowRecordPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取成长记录
	 * @param params
	 * @return
	 */
	public CsGrowRecord getCsGrowRecord(Map params)
	{
		return csGrowRecordDao.getCsGrowRecord(params);
	}

	/**
	 * 根据ID取成长记录
	 * @param id
	 * @return
	 */
	public CsGrowRecord getCsGrowRecordById(Long id)
	{
		return csGrowRecordDao.getCsGrowRecordById(id);
	}

	/**
	 * 保存成长记录
	 * @param csGrowRecord
	 */
	public CsGrowRecord saveCsGrowRecord(CsGrowRecord csGrowRecord)
	{
		return csGrowRecordDao.saveCsGrowRecord(csGrowRecord);
	}
	
	/**
	 * 更新成长记录
	 * @param csGrowRecord
	 */
	public void updateCsGrowRecord(CsGrowRecord csGrowRecord)
	{
		csGrowRecordDao.updateCsGrowRecord(csGrowRecord);
	}
	/**
	 * 更新成长记录非空字段
	 * @param csGrowRecord
	 */
	public void updateCsGrowRecord$NotNull(CsGrowRecord csGrowRecord)
	{
		csGrowRecordDao.updateCsGrowRecord$NotNull(csGrowRecord);
	}
	
	/**
	 * 根据ID删除一个成长记录
	 * @param id
	 */
	public void deleteCsGrowRecordById(Long id)
	{
		csGrowRecordDao.deleteCsGrowRecordById(id);
	}
	/**
	 * 根据ID逻辑删除一个成长记录
	 * @param id
	 */
	public void removeCsGrowRecordById(Long id)
	{
		csGrowRecordDao.removeCsGrowRecordById(id);
	}
	
	/**
	 * 根据条件更新成长记录
	 * @param values
	 * @param params
	 */
	public void updateCsGrowRecordByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsGrowRecordBy时无条件");
		csGrowRecordDao.updateCsGrowRecordByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除成长记录
	 * @param params
	 */
	public void deleteCsGrowRecordByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsGrowRecordBy时无条件");
		csGrowRecordDao.deleteCsGrowRecordByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsGrowRecordDao getCsGrowRecordDao()
	{
		return csGrowRecordDao;
	}

	public void setCsGrowRecordDao(ICsGrowRecordDao csGrowRecordDao)
	{
		this.csGrowRecordDao = csGrowRecordDao;
	}
}