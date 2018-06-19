package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsWorkRecord;

/**
 * 后勤工作记录的Dao接口
 * @author Joel
 */
@SuppressWarnings("unchecked")
public interface ICsWorkRecordDao
{
	/**
	 * 获取所有后勤工作记录
	 * @return
	 */
	public List<CsWorkRecord> getCsWorkRecordList(Map params,Integer size);
	
	/**
	 * 获取后勤工作记录统计
	 * @return
	 */
	public List getCsWorkRecordStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取后勤工作记录总数
	 * @return
	 */	
	public Long getCsWorkRecordCount(Map params);
	
	/**
	 * 获取后勤工作记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsWorkRecordEval(String eval,Map params);
	
	/**
	 * 获取后勤工作记录分页
	 * @return
	 */	
	public Page getCsWorkRecordPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取后勤工作记录
	 * @param params
	 * @return
	 */
	public CsWorkRecord getCsWorkRecord(Map params);	

	/**
	 * 根据ID取后勤工作记录
	 * @param id
	 * @return
	 */
	public CsWorkRecord getCsWorkRecordById(Long id);


	
	

	/**
	 * 保存后勤工作记录
	 * @param csWorkRecord
	 */
	public CsWorkRecord saveCsWorkRecord(CsWorkRecord csWorkRecord);

	/**
	 * 更新后勤工作记录
	 * @param csWorkRecord
	 */
	public void updateCsWorkRecord(CsWorkRecord csWorkRecord);
	/**
	 * 更新后勤工作记录非空字段
	 * @param csWorkRecord
	 */
	public void updateCsWorkRecord$NotNull(CsWorkRecord csWorkRecord);

	/**
	 * 根据ID删除一个后勤工作记录
	 * @param id
	 */
	public void deleteCsWorkRecordById(Long id);
	/**
	 * 根据ID逻辑删除一个后勤工作记录
	 * @param id
	 */
	public void removeCsWorkRecordById(Long id);
	/**
	 * 根据条件更新后勤工作记录
	 * @param values
	 * @param params
	 */
	public void updateCsWorkRecordByConfirm(Map values, Map params);
	/**
	 * 根据条件删除后勤工作记录
	 * @param params
	 */
	public void deleteCsWorkRecordByConfirm(Map params);

}