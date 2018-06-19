package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsUseRecord;

/**
 * 惠免记录的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsUseRecordService
{
	/**
	 * 获取所有惠免记录
	 * @return
	 */
	public List<CsUseRecord> getCsUseRecordList(Map params,Integer size);
	
	/**
	 * 获取惠免记录统计
	 * @return
	 */
	public List getCsUseRecordStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取惠免记录总数
	 * @return
	 */	
	public Long getCsUseRecordCount(Map params);
	
	
	/**
	 * 获取惠免记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsUseRecordEval(String eval,Map params);

	/**
	 * 获取惠免记录分页
	 * @return
	 */	
	public Page getCsUseRecordPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取惠免记录
	 * @param params
	 * @return
	 */
	public CsUseRecord getCsUseRecord(Map params);

	/**
	 * 根据ID取惠免记录
	 * @param id
	 * @return
	 */
	public CsUseRecord getCsUseRecordById(Long id);


	/**
	 * 保存惠免记录
	 * @param csUseRecord
	 */
	public CsUseRecord saveCsUseRecord(CsUseRecord csUseRecord);

	/**
	 * 更新惠免记录
	 * @param csUseRecord
	 */
	public void updateCsUseRecord(CsUseRecord csUseRecord);
	/**
	 * 更新惠免记录非空字段
	 * @param csUseRecord
	 */
	public void updateCsUseRecord$NotNull(CsUseRecord csUseRecord);

	/**
	 * 根据ID删除一个惠免记录
	 * @param id
	 */
	public void deleteCsUseRecordById(Long id);
	/**
	 * 根据条件更新惠免记录
	 * @param values
	 * @param params
	 */
	public void updateCsUseRecordByConfirm(Map values, Map params);
	/**
	 * 根据条件删除惠免记录
	 * @param params
	 */
	public void deleteCsUseRecordByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}