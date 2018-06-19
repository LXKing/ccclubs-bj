package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsRecord;

/**
 * 消费记录的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsRecordService
{
	/**
	 * 获取所有消费记录
	 * @return
	 */
	public List<CsRecord> getCsRecordList(Map params,Integer size);
	
	/**
	 * 获取消费记录统计
	 * @return
	 */
	public List getCsRecordStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取消费记录总数
	 * @return
	 */	
	public Long getCsRecordCount(Map params);
	
	
	/**
	 * 获取消费记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsRecordEval(String eval,Map params);

	/**
	 * 获取消费记录分页
	 * @return
	 */	
	public Page getCsRecordPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取消费记录
	 * @param params
	 * @return
	 */
	public CsRecord getCsRecord(Map params);

	/**
	 * 根据ID取消费记录
	 * @param id
	 * @return
	 */
	public CsRecord getCsRecordById(Long id);


	/**
	 * 保存消费记录
	 * @param csRecord
	 */
	public CsRecord saveCsRecord(CsRecord csRecord);

	/**
	 * 更新消费记录
	 * @param csRecord
	 */
	public void updateCsRecord(CsRecord csRecord);
	/**
	 * 更新消费记录非空字段
	 * @param csRecord
	 */
	public void updateCsRecord$NotNull(CsRecord csRecord);

	/**
	 * 根据ID删除一个消费记录
	 * @param id
	 */
	public void deleteCsRecordById(Long id);
	/**
	 * 根据条件更新消费记录
	 * @param values
	 * @param params
	 */
	public void updateCsRecordByConfirm(Map values, Map params);
	/**
	 * 根据条件删除消费记录
	 * @param params
	 */
	public void deleteCsRecordByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}