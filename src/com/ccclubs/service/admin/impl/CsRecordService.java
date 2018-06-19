package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsRecordDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsRecord;
import com.ccclubs.service.admin.ICsRecordService;
import com.lazy3q.web.helper.$;

/**
 * 消费记录的Service实现
 * @author 飞啊飘啊
 */
public class CsRecordService implements ICsRecordService
{
	ICsRecordDao csRecordDao;
	

	/**
	 * 获取所有消费记录
	 * @return
	 */
	public List<CsRecord> getCsRecordList(Map params,Integer size)
	{
		return csRecordDao.getCsRecordList(params,size);
	}
	
	/**
	 * 获取消费记录统计
	 * @return
	 */
	public List getCsRecordStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csRecordDao.getCsRecordStats(params,groups,sums);
	}
	
	/**
	 * 获取消费记录总数
	 * @return
	 */
	public Long getCsRecordCount(Map params)
	{
		return csRecordDao.getCsRecordCount(params);
	}
	
	/**
	 * 获取消费记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsRecordEval(String eval,Map params)
	{
		return csRecordDao.getCsRecordEval(eval,params);
	}

	/**
	 * 获取消费记录分页
	 * @return
	 */
	public Page getCsRecordPage(int page,int size,Map params)
	{
		return csRecordDao.getCsRecordPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取消费记录
	 * @param params
	 * @return
	 */
	public CsRecord getCsRecord(Map params)
	{
		return csRecordDao.getCsRecord(params);
	}

	/**
	 * 根据ID取消费记录
	 * @param id
	 * @return
	 */
	public CsRecord getCsRecordById(Long id)
	{
		return csRecordDao.getCsRecordById(id);
	}

	/**
	 * 保存消费记录
	 * @param csRecord
	 */
	public CsRecord saveCsRecord(CsRecord csRecord)
	{
		return csRecordDao.saveCsRecord(csRecord);
	}
	
	/**
	 * 更新消费记录
	 * @param csRecord
	 */
	public void updateCsRecord(CsRecord csRecord)
	{
		csRecordDao.updateCsRecord(csRecord);
	}
	/**
	 * 更新消费记录非空字段
	 * @param csRecord
	 */
	public void updateCsRecord$NotNull(CsRecord csRecord)
	{
		csRecordDao.updateCsRecord$NotNull(csRecord);
	}
	
	/**
	 * 根据ID删除一个消费记录
	 * @param id
	 */
	public void deleteCsRecordById(Long id)
	{
		csRecordDao.deleteCsRecordById(id);
	}
	
	/**
	 * 根据条件更新消费记录
	 * @param values
	 * @param params
	 */
	public void updateCsRecordByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsRecordBy时无条件");
		csRecordDao.updateCsRecordByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除消费记录
	 * @param params
	 */
	public void deleteCsRecordByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsRecordBy时无条件");
		csRecordDao.deleteCsRecordByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsRecordDao getCsRecordDao()
	{
		return csRecordDao;
	}

	public void setCsRecordDao(ICsRecordDao csRecordDao)
	{
		this.csRecordDao = csRecordDao;
	}
}