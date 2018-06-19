package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsUseRecordDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsUseRecord;
import com.ccclubs.service.admin.ICsUseRecordService;
import com.lazy3q.web.helper.$;

/**
 * 惠免记录的Service实现
 * @author 飞啊飘啊
 */
public class CsUseRecordService implements ICsUseRecordService
{
	ICsUseRecordDao csUseRecordDao;
	

	/**
	 * 获取所有惠免记录
	 * @return
	 */
	public List<CsUseRecord> getCsUseRecordList(Map params,Integer size)
	{
		return csUseRecordDao.getCsUseRecordList(params,size);
	}
	
	/**
	 * 获取惠免记录统计
	 * @return
	 */
	public List getCsUseRecordStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csUseRecordDao.getCsUseRecordStats(params,groups,sums);
	}
	
	/**
	 * 获取惠免记录总数
	 * @return
	 */
	public Long getCsUseRecordCount(Map params)
	{
		return csUseRecordDao.getCsUseRecordCount(params);
	}
	
	/**
	 * 获取惠免记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsUseRecordEval(String eval,Map params)
	{
		return csUseRecordDao.getCsUseRecordEval(eval,params);
	}

	/**
	 * 获取惠免记录分页
	 * @return
	 */
	public Page getCsUseRecordPage(int page,int size,Map params)
	{
		return csUseRecordDao.getCsUseRecordPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取惠免记录
	 * @param params
	 * @return
	 */
	public CsUseRecord getCsUseRecord(Map params)
	{
		return csUseRecordDao.getCsUseRecord(params);
	}

	/**
	 * 根据ID取惠免记录
	 * @param id
	 * @return
	 */
	public CsUseRecord getCsUseRecordById(Long id)
	{
		return csUseRecordDao.getCsUseRecordById(id);
	}

	/**
	 * 保存惠免记录
	 * @param csUseRecord
	 */
	public CsUseRecord saveCsUseRecord(CsUseRecord csUseRecord)
	{
		return csUseRecordDao.saveCsUseRecord(csUseRecord);
	}
	
	/**
	 * 更新惠免记录
	 * @param csUseRecord
	 */
	public void updateCsUseRecord(CsUseRecord csUseRecord)
	{
		csUseRecordDao.updateCsUseRecord(csUseRecord);
	}
	/**
	 * 更新惠免记录非空字段
	 * @param csUseRecord
	 */
	public void updateCsUseRecord$NotNull(CsUseRecord csUseRecord)
	{
		csUseRecordDao.updateCsUseRecord$NotNull(csUseRecord);
	}
	
	/**
	 * 根据ID删除一个惠免记录
	 * @param id
	 */
	public void deleteCsUseRecordById(Long id)
	{
		csUseRecordDao.deleteCsUseRecordById(id);
	}
	
	/**
	 * 根据条件更新惠免记录
	 * @param values
	 * @param params
	 */
	public void updateCsUseRecordByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsUseRecordBy时无条件");
		csUseRecordDao.updateCsUseRecordByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除惠免记录
	 * @param params
	 */
	public void deleteCsUseRecordByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsUseRecordBy时无条件");
		csUseRecordDao.deleteCsUseRecordByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsUseRecordDao getCsUseRecordDao()
	{
		return csUseRecordDao;
	}

	public void setCsUseRecordDao(ICsUseRecordDao csUseRecordDao)
	{
		this.csUseRecordDao = csUseRecordDao;
	}
}