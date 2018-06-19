package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsOperRecordDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsOperRecord;
import com.ccclubs.service.admin.ICsOperRecordService;
import com.lazy3q.web.helper.$;

/**
 * 收支记录的Service实现
 * @author 飞啊飘啊
 */
public class CsOperRecordService implements ICsOperRecordService
{
	ICsOperRecordDao csOperRecordDao;
	

	/**
	 * 获取所有收支记录
	 * @return
	 */
	public List<CsOperRecord> getCsOperRecordList(Map params,Integer size)
	{
		return csOperRecordDao.getCsOperRecordList(params,size);
	}
	
	/**
	 * 获取收支记录统计
	 * @return
	 */
	public List getCsOperRecordStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csOperRecordDao.getCsOperRecordStats(params,groups,sums);
	}
	
	/**
	 * 获取收支记录总数
	 * @return
	 */
	public Long getCsOperRecordCount(Map params)
	{
		return csOperRecordDao.getCsOperRecordCount(params);
	}
	
	/**
	 * 获取收支记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsOperRecordEval(String eval,Map params)
	{
		return csOperRecordDao.getCsOperRecordEval(eval,params);
	}

	/**
	 * 获取收支记录分页
	 * @return
	 */
	public Page getCsOperRecordPage(int page,int size,Map params)
	{
		return csOperRecordDao.getCsOperRecordPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取收支记录
	 * @param params
	 * @return
	 */
	public CsOperRecord getCsOperRecord(Map params)
	{
		return csOperRecordDao.getCsOperRecord(params);
	}

	/**
	 * 根据ID取收支记录
	 * @param id
	 * @return
	 */
	public CsOperRecord getCsOperRecordById(Long id)
	{
		return csOperRecordDao.getCsOperRecordById(id);
	}

	/**
	 * 保存收支记录
	 * @param csOperRecord
	 */
	public CsOperRecord saveCsOperRecord(CsOperRecord csOperRecord)
	{
		return csOperRecordDao.saveCsOperRecord(csOperRecord);
	}
	
	/**
	 * 更新收支记录
	 * @param csOperRecord
	 */
	public void updateCsOperRecord(CsOperRecord csOperRecord)
	{
		csOperRecordDao.updateCsOperRecord(csOperRecord);
	}
	/**
	 * 更新收支记录非空字段
	 * @param csOperRecord
	 */
	public void updateCsOperRecord$NotNull(CsOperRecord csOperRecord)
	{
		csOperRecordDao.updateCsOperRecord$NotNull(csOperRecord);
	}
	
	/**
	 * 根据ID删除一个收支记录
	 * @param id
	 */
	public void deleteCsOperRecordById(Long id)
	{
		csOperRecordDao.deleteCsOperRecordById(id);
	}
	/**
	 * 根据ID逻辑删除一个收支记录
	 * @param id
	 */
	public void removeCsOperRecordById(Long id)
	{
		csOperRecordDao.removeCsOperRecordById(id);
	}
	
	/**
	 * 根据条件更新收支记录
	 * @param values
	 * @param params
	 */
	public void updateCsOperRecordByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsOperRecordBy时无条件");
		csOperRecordDao.updateCsOperRecordByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除收支记录
	 * @param params
	 */
	public void deleteCsOperRecordByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsOperRecordBy时无条件");
		csOperRecordDao.deleteCsOperRecordByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsOperRecordDao getCsOperRecordDao()
	{
		return csOperRecordDao;
	}

	public void setCsOperRecordDao(ICsOperRecordDao csOperRecordDao)
	{
		this.csOperRecordDao = csOperRecordDao;
	}
}