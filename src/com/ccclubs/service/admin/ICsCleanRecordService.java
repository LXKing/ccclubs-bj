package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsCleanRecord;

/**
 * 保洁记录的Service接口
 * @author Joel
 */
@SuppressWarnings("unchecked")
public interface ICsCleanRecordService
{
	/**
	 * 获取所有保洁记录
	 * @return
	 */
	public List<CsCleanRecord> getCsCleanRecordList(Map params,Integer size);
	
	/**
	 * 获取保洁记录统计
	 * @return
	 */
	public List getCsCleanRecordStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取保洁记录总数
	 * @return
	 */	
	public Long getCsCleanRecordCount(Map params);
	
	
	/**
	 * 获取保洁记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCleanRecordEval(String eval,Map params);

	/**
	 * 获取保洁记录分页
	 * @return
	 */	
	public Page getCsCleanRecordPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取保洁记录
	 * @param params
	 * @return
	 */
	public CsCleanRecord getCsCleanRecord(Map params);

	/**
	 * 根据ID取保洁记录
	 * @param id
	 * @return
	 */
	public CsCleanRecord getCsCleanRecordById(Long id);


	/**
	 * 保存保洁记录
	 * @param csCleanRecord
	 */
	public CsCleanRecord saveCsCleanRecord(CsCleanRecord csCleanRecord);

	/**
	 * 更新保洁记录
	 * @param csCleanRecord
	 */
	public void updateCsCleanRecord(CsCleanRecord csCleanRecord);
	/**
	 * 更新保洁记录非空字段
	 * @param csCleanRecord
	 */
	public void updateCsCleanRecord$NotNull(CsCleanRecord csCleanRecord);

	/**
	 * 根据ID删除一个保洁记录
	 * @param id
	 */
	public void deleteCsCleanRecordById(Long id);
	/**
	 * 根据ID逻辑删除一个保洁记录
	 * @param id
	 */
	public void removeCsCleanRecordById(Long id);
	/**
	 * 根据条件更新保洁记录
	 * @param values
	 * @param params
	 */
	public void updateCsCleanRecordByConfirm(Map values, Map params);
	/**
	 * 根据条件删除保洁记录
	 * @param params
	 */
	public void deleteCsCleanRecordByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}