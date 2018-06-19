package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsOperRecord;

/**
 * 收支记录的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsOperRecordDao
{
	/**
	 * 获取所有收支记录
	 * @return
	 */
	public List<CsOperRecord> getCsOperRecordList(Map params,Integer size);
	
	/**
	 * 获取收支记录统计
	 * @return
	 */
	public List getCsOperRecordStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取收支记录总数
	 * @return
	 */	
	public Long getCsOperRecordCount(Map params);
	
	/**
	 * 获取收支记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsOperRecordEval(String eval,Map params);
	
	/**
	 * 获取收支记录分页
	 * @return
	 */	
	public Page getCsOperRecordPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取收支记录
	 * @param params
	 * @return
	 */
	public CsOperRecord getCsOperRecord(Map params);	

	/**
	 * 根据ID取收支记录
	 * @param id
	 * @return
	 */
	public CsOperRecord getCsOperRecordById(Long id);


	
	

	/**
	 * 保存收支记录
	 * @param csOperRecord
	 */
	public CsOperRecord saveCsOperRecord(CsOperRecord csOperRecord);

	/**
	 * 更新收支记录
	 * @param csOperRecord
	 */
	public void updateCsOperRecord(CsOperRecord csOperRecord);
	/**
	 * 更新收支记录非空字段
	 * @param csOperRecord
	 */
	public void updateCsOperRecord$NotNull(CsOperRecord csOperRecord);

	/**
	 * 根据ID删除一个收支记录
	 * @param id
	 */
	public void deleteCsOperRecordById(Long id);
	/**
	 * 根据ID逻辑删除一个收支记录
	 * @param id
	 */
	public void removeCsOperRecordById(Long id);
	/**
	 * 根据条件更新收支记录
	 * @param values
	 * @param params
	 */
	public void updateCsOperRecordByConfirm(Map values, Map params);
	/**
	 * 根据条件删除收支记录
	 * @param params
	 */
	public void deleteCsOperRecordByConfirm(Map params);

}