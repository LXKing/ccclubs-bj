package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsAlipayRecordDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsAlipayRecord;
import com.ccclubs.service.admin.ICsAlipayRecordService;
import com.lazy3q.web.helper.$;

/**
 * 接口充值的Service实现
 * @author 飞啊飘啊
 */
public class CsAlipayRecordService implements ICsAlipayRecordService
{
	ICsAlipayRecordDao csAlipayRecordDao;
	

	/**
	 * 获取所有接口充值
	 * @return
	 */
	public List<CsAlipayRecord> getCsAlipayRecordList(Map params,Integer size)
	{
		return csAlipayRecordDao.getCsAlipayRecordList(params,size);
	}
	
	/**
	 * 获取接口充值统计
	 * @return
	 */
	public List getCsAlipayRecordStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csAlipayRecordDao.getCsAlipayRecordStats(params,groups,sums);
	}
	
	/**
	 * 获取接口充值总数
	 * @return
	 */
	public Long getCsAlipayRecordCount(Map params)
	{
		return csAlipayRecordDao.getCsAlipayRecordCount(params);
	}
	
	/**
	 * 获取接口充值自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsAlipayRecordEval(String eval,Map params)
	{
		return csAlipayRecordDao.getCsAlipayRecordEval(eval,params);
	}

	/**
	 * 获取接口充值分页
	 * @return
	 */
	public Page getCsAlipayRecordPage(int page,int size,Map params)
	{
		return csAlipayRecordDao.getCsAlipayRecordPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取接口充值
	 * @param params
	 * @return
	 */
	public CsAlipayRecord getCsAlipayRecord(Map params)
	{
		return csAlipayRecordDao.getCsAlipayRecord(params);
	}

	/**
	 * 根据ID取接口充值
	 * @param id
	 * @return
	 */
	public CsAlipayRecord getCsAlipayRecordById(Long id)
	{
		return csAlipayRecordDao.getCsAlipayRecordById(id);
	}

	/**
	 * 保存接口充值
	 * @param csAlipayRecord
	 */
	public CsAlipayRecord saveCsAlipayRecord(CsAlipayRecord csAlipayRecord)
	{
		return csAlipayRecordDao.saveCsAlipayRecord(csAlipayRecord);
	}
	
	/**
	 * 更新接口充值
	 * @param csAlipayRecord
	 */
	public void updateCsAlipayRecord(CsAlipayRecord csAlipayRecord)
	{
		csAlipayRecordDao.updateCsAlipayRecord(csAlipayRecord);
	}
	/**
	 * 更新接口充值非空字段
	 * @param csAlipayRecord
	 */
	public void updateCsAlipayRecord$NotNull(CsAlipayRecord csAlipayRecord)
	{
		csAlipayRecordDao.updateCsAlipayRecord$NotNull(csAlipayRecord);
	}
	
	/**
	 * 根据ID删除一个接口充值
	 * @param id
	 */
	public void deleteCsAlipayRecordById(Long id)
	{
		csAlipayRecordDao.deleteCsAlipayRecordById(id);
	}
	/**
	 * 根据ID逻辑删除一个接口充值
	 * @param id
	 */
	public void removeCsAlipayRecordById(Long id)
	{
		csAlipayRecordDao.removeCsAlipayRecordById(id);
	}
	
	/**
	 * 根据条件更新接口充值
	 * @param values
	 * @param params
	 */
	public void updateCsAlipayRecordByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsAlipayRecordBy时无条件");
		csAlipayRecordDao.updateCsAlipayRecordByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除接口充值
	 * @param params
	 */
	public void deleteCsAlipayRecordByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsAlipayRecordBy时无条件");
		csAlipayRecordDao.deleteCsAlipayRecordByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsAlipayRecordDao getCsAlipayRecordDao()
	{
		return csAlipayRecordDao;
	}

	public void setCsAlipayRecordDao(ICsAlipayRecordDao csAlipayRecordDao)
	{
		this.csAlipayRecordDao = csAlipayRecordDao;
	}
}