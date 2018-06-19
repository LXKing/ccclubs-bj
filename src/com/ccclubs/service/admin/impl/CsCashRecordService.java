package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsCashRecordDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCashRecord;
import com.ccclubs.service.admin.ICsCashRecordService;
import com.lazy3q.web.helper.$;

/**
 * 提现记录的Service实现
 * @author 飞啊飘啊
 */
public class CsCashRecordService implements ICsCashRecordService
{
	ICsCashRecordDao csCashRecordDao;
	

	/**
	 * 获取所有提现记录
	 * @return
	 */
	public List<CsCashRecord> getCsCashRecordList(Map params,Integer size)
	{
		return csCashRecordDao.getCsCashRecordList(params,size);
	}
	
	/**
	 * 获取提现记录统计
	 * @return
	 */
	public List getCsCashRecordStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csCashRecordDao.getCsCashRecordStats(params,groups,sums);
	}
	
	/**
	 * 获取提现记录总数
	 * @return
	 */
	public Long getCsCashRecordCount(Map params)
	{
		return csCashRecordDao.getCsCashRecordCount(params);
	}
	
	/**
	 * 获取提现记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCashRecordEval(String eval,Map params)
	{
		return csCashRecordDao.getCsCashRecordEval(eval,params);
	}

	/**
	 * 获取提现记录分页
	 * @return
	 */
	public Page getCsCashRecordPage(int page,int size,Map params)
	{
		return csCashRecordDao.getCsCashRecordPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取提现记录
	 * @param params
	 * @return
	 */
	public CsCashRecord getCsCashRecord(Map params)
	{
		return csCashRecordDao.getCsCashRecord(params);
	}

	/**
	 * 根据ID取提现记录
	 * @param id
	 * @return
	 */
	public CsCashRecord getCsCashRecordById(Long id)
	{
		return csCashRecordDao.getCsCashRecordById(id);
	}

	/**
	 * 保存提现记录
	 * @param csCashRecord
	 */
	public CsCashRecord saveCsCashRecord(CsCashRecord csCashRecord)
	{
		return csCashRecordDao.saveCsCashRecord(csCashRecord);
	}
	
	/**
	 * 更新提现记录
	 * @param csCashRecord
	 */
	public void updateCsCashRecord(CsCashRecord csCashRecord)
	{
		csCashRecordDao.updateCsCashRecord(csCashRecord);
	}
	/**
	 * 更新提现记录非空字段
	 * @param csCashRecord
	 */
	public void updateCsCashRecord$NotNull(CsCashRecord csCashRecord)
	{
		csCashRecordDao.updateCsCashRecord$NotNull(csCashRecord);
	}
	
	/**
	 * 根据ID删除一个提现记录
	 * @param id
	 */
	public void deleteCsCashRecordById(Long id)
	{
		csCashRecordDao.deleteCsCashRecordById(id);
	}
	/**
	 * 根据ID逻辑删除一个提现记录
	 * @param id
	 */
	public void removeCsCashRecordById(Long id)
	{
		csCashRecordDao.removeCsCashRecordById(id);
	}
	
	/**
	 * 根据条件更新提现记录
	 * @param values
	 * @param params
	 */
	public void updateCsCashRecordByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsCashRecordBy时无条件");
		csCashRecordDao.updateCsCashRecordByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除提现记录
	 * @param params
	 */
	public void deleteCsCashRecordByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsCashRecordBy时无条件");
		csCashRecordDao.deleteCsCashRecordByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsCashRecordDao getCsCashRecordDao()
	{
		return csCashRecordDao;
	}

	public void setCsCashRecordDao(ICsCashRecordDao csCashRecordDao)
	{
		this.csCashRecordDao = csCashRecordDao;
	}
}