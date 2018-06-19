package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsCashRecord;

/**
 * 提现记录的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsCashRecordService
{
	/**
	 * 获取所有提现记录
	 * @return
	 */
	public List<CsCashRecord> getCsCashRecordList(Map params,Integer size);
	
	/**
	 * 获取提现记录统计
	 * @return
	 */
	public List getCsCashRecordStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取提现记录总数
	 * @return
	 */	
	public Long getCsCashRecordCount(Map params);
	
	
	/**
	 * 获取提现记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCashRecordEval(String eval,Map params);

	/**
	 * 获取提现记录分页
	 * @return
	 */	
	public Page getCsCashRecordPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取提现记录
	 * @param params
	 * @return
	 */
	public CsCashRecord getCsCashRecord(Map params);

	/**
	 * 根据ID取提现记录
	 * @param id
	 * @return
	 */
	public CsCashRecord getCsCashRecordById(Long id);


	/**
	 * 保存提现记录
	 * @param csCashRecord
	 */
	public CsCashRecord saveCsCashRecord(CsCashRecord csCashRecord);

	/**
	 * 更新提现记录
	 * @param csCashRecord
	 */
	public void updateCsCashRecord(CsCashRecord csCashRecord);
	/**
	 * 更新提现记录非空字段
	 * @param csCashRecord
	 */
	public void updateCsCashRecord$NotNull(CsCashRecord csCashRecord);

	/**
	 * 根据ID删除一个提现记录
	 * @param id
	 */
	public void deleteCsCashRecordById(Long id);
	/**
	 * 根据ID逻辑删除一个提现记录
	 * @param id
	 */
	public void removeCsCashRecordById(Long id);
	/**
	 * 根据条件更新提现记录
	 * @param values
	 * @param params
	 */
	public void updateCsCashRecordByConfirm(Map values, Map params);
	/**
	 * 根据条件删除提现记录
	 * @param params
	 */
	public void deleteCsCashRecordByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}