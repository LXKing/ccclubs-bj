package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsCreditRecord;

/**
 * 信用记录的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsCreditRecordService
{
	/**
	 * 获取所有信用记录
	 * @return
	 */
	public List<CsCreditRecord> getCsCreditRecordList(Map params,Integer size);
	
	/**
	 * 获取信用记录统计
	 * @return
	 */
	public List getCsCreditRecordStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取信用记录总数
	 * @return
	 */	
	public Long getCsCreditRecordCount(Map params);
	
	
	/**
	 * 获取信用记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCreditRecordEval(String eval,Map params);

	/**
	 * 获取信用记录分页
	 * @return
	 */	
	public Page getCsCreditRecordPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取信用记录
	 * @param params
	 * @return
	 */
	public CsCreditRecord getCsCreditRecord(Map params);

	/**
	 * 根据ID取信用记录
	 * @param id
	 * @return
	 */
	public CsCreditRecord getCsCreditRecordById(Long id);


	/**
	 * 保存信用记录
	 * @param csCreditRecord
	 */
	public CsCreditRecord saveCsCreditRecord(CsCreditRecord csCreditRecord);

	/**
	 * 更新信用记录
	 * @param csCreditRecord
	 */
	public void updateCsCreditRecord(CsCreditRecord csCreditRecord);
	/**
	 * 更新信用记录非空字段
	 * @param csCreditRecord
	 */
	public void updateCsCreditRecord$NotNull(CsCreditRecord csCreditRecord);

	/**
	 * 根据ID删除一个信用记录
	 * @param id
	 */
	public void deleteCsCreditRecordById(Long id);
	/**
	 * 根据ID逻辑删除一个信用记录
	 * @param id
	 */
	public void removeCsCreditRecordById(Long id);
	/**
	 * 根据条件更新信用记录
	 * @param values
	 * @param params
	 */
	public void updateCsCreditRecordByConfirm(Map values, Map params);
	/**
	 * 根据条件删除信用记录
	 * @param params
	 */
	public void deleteCsCreditRecordByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}