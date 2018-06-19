package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsIntegralRecord;

/**
 * 积分记录的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsIntegralRecordDao
{
	/**
	 * 获取所有积分记录
	 * @return
	 */
	public List<CsIntegralRecord> getCsIntegralRecordList(Map params,Integer size);
	
	/**
	 * 获取积分记录统计
	 * @return
	 */
	public List getCsIntegralRecordStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取积分记录总数
	 * @return
	 */	
	public Long getCsIntegralRecordCount(Map params);
	
	/**
	 * 获取积分记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsIntegralRecordEval(String eval,Map params);
	
	/**
	 * 获取积分记录分页
	 * @return
	 */	
	public Page getCsIntegralRecordPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取积分记录
	 * @param params
	 * @return
	 */
	public CsIntegralRecord getCsIntegralRecord(Map params);	

	/**
	 * 根据ID取积分记录
	 * @param id
	 * @return
	 */
	public CsIntegralRecord getCsIntegralRecordById(Long id);


	
	

	/**
	 * 保存积分记录
	 * @param csIntegralRecord
	 */
	public CsIntegralRecord saveCsIntegralRecord(CsIntegralRecord csIntegralRecord);

	/**
	 * 更新积分记录
	 * @param csIntegralRecord
	 */
	public void updateCsIntegralRecord(CsIntegralRecord csIntegralRecord);
	/**
	 * 更新积分记录非空字段
	 * @param csIntegralRecord
	 */
	public void updateCsIntegralRecord$NotNull(CsIntegralRecord csIntegralRecord);

	/**
	 * 根据ID删除一个积分记录
	 * @param id
	 */
	public void deleteCsIntegralRecordById(Long id);
	/**
	 * 根据条件更新积分记录
	 * @param values
	 * @param params
	 */
	public void updateCsIntegralRecordByConfirm(Map values, Map params);
	/**
	 * 根据条件删除积分记录
	 * @param params
	 */
	public void deleteCsIntegralRecordByConfirm(Map params);

}