package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsGrowRecord;

/**
 * 成长记录的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsGrowRecordService
{
	/**
	 * 获取所有成长记录
	 * @return
	 */
	public List<CsGrowRecord> getCsGrowRecordList(Map params,Integer size);
	
	/**
	 * 获取成长记录统计
	 * @return
	 */
	public List getCsGrowRecordStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取成长记录总数
	 * @return
	 */	
	public Long getCsGrowRecordCount(Map params);
	
	
	/**
	 * 获取成长记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsGrowRecordEval(String eval,Map params);

	/**
	 * 获取成长记录分页
	 * @return
	 */	
	public Page getCsGrowRecordPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取成长记录
	 * @param params
	 * @return
	 */
	public CsGrowRecord getCsGrowRecord(Map params);

	/**
	 * 根据ID取成长记录
	 * @param id
	 * @return
	 */
	public CsGrowRecord getCsGrowRecordById(Long id);


	/**
	 * 保存成长记录
	 * @param csGrowRecord
	 */
	public CsGrowRecord saveCsGrowRecord(CsGrowRecord csGrowRecord);

	/**
	 * 更新成长记录
	 * @param csGrowRecord
	 */
	public void updateCsGrowRecord(CsGrowRecord csGrowRecord);
	/**
	 * 更新成长记录非空字段
	 * @param csGrowRecord
	 */
	public void updateCsGrowRecord$NotNull(CsGrowRecord csGrowRecord);

	/**
	 * 根据ID删除一个成长记录
	 * @param id
	 */
	public void deleteCsGrowRecordById(Long id);
	/**
	 * 根据ID逻辑删除一个成长记录
	 * @param id
	 */
	public void removeCsGrowRecordById(Long id);
	/**
	 * 根据条件更新成长记录
	 * @param values
	 * @param params
	 */
	public void updateCsGrowRecordByConfirm(Map values, Map params);
	/**
	 * 根据条件删除成长记录
	 * @param params
	 */
	public void deleteCsGrowRecordByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}