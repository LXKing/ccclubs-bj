package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsArtifRecordDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsArtifRecord;
import com.ccclubs.service.admin.ICsArtifRecordService;
import com.lazy3q.web.helper.$;

/**
 * 充值扣款的Service实现
 * @author 飞啊飘啊
 */
public class CsArtifRecordService implements ICsArtifRecordService
{
	ICsArtifRecordDao csArtifRecordDao;
	

	/**
	 * 获取所有充值扣款
	 * @return
	 */
	public List<CsArtifRecord> getCsArtifRecordList(Map params,Integer size)
	{
		return csArtifRecordDao.getCsArtifRecordList(params,size);
	}
	
	/**
	 * 获取充值扣款统计
	 * @return
	 */
	public List getCsArtifRecordStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csArtifRecordDao.getCsArtifRecordStats(params,groups,sums);
	}
	
	/**
	 * 获取充值扣款总数
	 * @return
	 */
	public Long getCsArtifRecordCount(Map params)
	{
		return csArtifRecordDao.getCsArtifRecordCount(params);
	}
	
	/**
	 * 获取充值扣款自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsArtifRecordEval(String eval,Map params)
	{
		return csArtifRecordDao.getCsArtifRecordEval(eval,params);
	}

	/**
	 * 获取充值扣款分页
	 * @return
	 */
	public Page getCsArtifRecordPage(int page,int size,Map params)
	{
		return csArtifRecordDao.getCsArtifRecordPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取充值扣款
	 * @param params
	 * @return
	 */
	public CsArtifRecord getCsArtifRecord(Map params)
	{
		return csArtifRecordDao.getCsArtifRecord(params);
	}

	/**
	 * 根据ID取充值扣款
	 * @param id
	 * @return
	 */
	public CsArtifRecord getCsArtifRecordById(Long id)
	{
		return csArtifRecordDao.getCsArtifRecordById(id);
	}

	/**
	 * 保存充值扣款
	 * @param csArtifRecord
	 */
	public CsArtifRecord saveCsArtifRecord(CsArtifRecord csArtifRecord)
	{
		return csArtifRecordDao.saveCsArtifRecord(csArtifRecord);
	}
	
	/**
	 * 更新充值扣款
	 * @param csArtifRecord
	 */
	public void updateCsArtifRecord(CsArtifRecord csArtifRecord)
	{
		csArtifRecordDao.updateCsArtifRecord(csArtifRecord);
	}
	/**
	 * 更新充值扣款非空字段
	 * @param csArtifRecord
	 */
	public void updateCsArtifRecord$NotNull(CsArtifRecord csArtifRecord)
	{
		csArtifRecordDao.updateCsArtifRecord$NotNull(csArtifRecord);
	}
	
	/**
	 * 根据ID删除一个充值扣款
	 * @param id
	 */
	public void deleteCsArtifRecordById(Long id)
	{
		csArtifRecordDao.deleteCsArtifRecordById(id);
	}
	/**
	 * 根据ID逻辑删除一个充值扣款
	 * @param id
	 */
	public void removeCsArtifRecordById(Long id)
	{
		csArtifRecordDao.removeCsArtifRecordById(id);
	}
	
	/**
	 * 根据条件更新充值扣款
	 * @param values
	 * @param params
	 */
	public void updateCsArtifRecordByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsArtifRecordBy时无条件");
		csArtifRecordDao.updateCsArtifRecordByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除充值扣款
	 * @param params
	 */
	public void deleteCsArtifRecordByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsArtifRecordBy时无条件");
		csArtifRecordDao.deleteCsArtifRecordByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsArtifRecordDao getCsArtifRecordDao()
	{
		return csArtifRecordDao;
	}

	public void setCsArtifRecordDao(ICsArtifRecordDao csArtifRecordDao)
	{
		this.csArtifRecordDao = csArtifRecordDao;
	}
}