package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsCreditRecordDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCreditRecord;
import com.ccclubs.service.admin.ICsCreditRecordService;
import com.lazy3q.web.helper.$;

/**
 * 信用记录的Service实现
 * @author 飞啊飘啊
 */
public class CsCreditRecordService implements ICsCreditRecordService
{
	ICsCreditRecordDao csCreditRecordDao;
	

	/**
	 * 获取所有信用记录
	 * @return
	 */
	public List<CsCreditRecord> getCsCreditRecordList(Map params,Integer size)
	{
		return csCreditRecordDao.getCsCreditRecordList(params,size);
	}
	
	/**
	 * 获取信用记录统计
	 * @return
	 */
	public List getCsCreditRecordStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csCreditRecordDao.getCsCreditRecordStats(params,groups,sums);
	}
	
	/**
	 * 获取信用记录总数
	 * @return
	 */
	public Long getCsCreditRecordCount(Map params)
	{
		return csCreditRecordDao.getCsCreditRecordCount(params);
	}
	
	/**
	 * 获取信用记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCreditRecordEval(String eval,Map params)
	{
		return csCreditRecordDao.getCsCreditRecordEval(eval,params);
	}

	/**
	 * 获取信用记录分页
	 * @return
	 */
	public Page getCsCreditRecordPage(int page,int size,Map params)
	{
		return csCreditRecordDao.getCsCreditRecordPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取信用记录
	 * @param params
	 * @return
	 */
	public CsCreditRecord getCsCreditRecord(Map params)
	{
		return csCreditRecordDao.getCsCreditRecord(params);
	}

	/**
	 * 根据ID取信用记录
	 * @param id
	 * @return
	 */
	public CsCreditRecord getCsCreditRecordById(Long id)
	{
		return csCreditRecordDao.getCsCreditRecordById(id);
	}

	/**
	 * 保存信用记录
	 * @param csCreditRecord
	 */
	public CsCreditRecord saveCsCreditRecord(CsCreditRecord csCreditRecord)
	{
		return csCreditRecordDao.saveCsCreditRecord(csCreditRecord);
	}
	
	/**
	 * 更新信用记录
	 * @param csCreditRecord
	 */
	public void updateCsCreditRecord(CsCreditRecord csCreditRecord)
	{
		csCreditRecordDao.updateCsCreditRecord(csCreditRecord);
	}
	/**
	 * 更新信用记录非空字段
	 * @param csCreditRecord
	 */
	public void updateCsCreditRecord$NotNull(CsCreditRecord csCreditRecord)
	{
		csCreditRecordDao.updateCsCreditRecord$NotNull(csCreditRecord);
	}
	
	/**
	 * 根据ID删除一个信用记录
	 * @param id
	 */
	public void deleteCsCreditRecordById(Long id)
	{
		csCreditRecordDao.deleteCsCreditRecordById(id);
	}
	/**
	 * 根据ID逻辑删除一个信用记录
	 * @param id
	 */
	public void removeCsCreditRecordById(Long id)
	{
		csCreditRecordDao.removeCsCreditRecordById(id);
	}
	
	/**
	 * 根据条件更新信用记录
	 * @param values
	 * @param params
	 */
	public void updateCsCreditRecordByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsCreditRecordBy时无条件");
		csCreditRecordDao.updateCsCreditRecordByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除信用记录
	 * @param params
	 */
	public void deleteCsCreditRecordByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsCreditRecordBy时无条件");
		csCreditRecordDao.deleteCsCreditRecordByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsCreditRecordDao getCsCreditRecordDao()
	{
		return csCreditRecordDao;
	}

	public void setCsCreditRecordDao(ICsCreditRecordDao csCreditRecordDao)
	{
		this.csCreditRecordDao = csCreditRecordDao;
	}
}