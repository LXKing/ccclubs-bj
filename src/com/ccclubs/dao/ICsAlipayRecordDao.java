package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsAlipayRecord;

/**
 * 接口充值的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsAlipayRecordDao
{
	/**
	 * 获取所有接口充值
	 * @return
	 */
	public List<CsAlipayRecord> getCsAlipayRecordList(Map params,Integer size);
	
	/**
	 * 获取接口充值统计
	 * @return
	 */
	public List getCsAlipayRecordStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取接口充值总数
	 * @return
	 */	
	public Long getCsAlipayRecordCount(Map params);
	
	/**
	 * 获取接口充值自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsAlipayRecordEval(String eval,Map params);
	
	/**
	 * 获取接口充值分页
	 * @return
	 */	
	public Page getCsAlipayRecordPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取接口充值
	 * @param params
	 * @return
	 */
	public CsAlipayRecord getCsAlipayRecord(Map params);	

	/**
	 * 根据ID取接口充值
	 * @param id
	 * @return
	 */
	public CsAlipayRecord getCsAlipayRecordById(Long id);


	
	

	/**
	 * 保存接口充值
	 * @param csAlipayRecord
	 */
	public CsAlipayRecord saveCsAlipayRecord(CsAlipayRecord csAlipayRecord);

	/**
	 * 更新接口充值
	 * @param csAlipayRecord
	 */
	public void updateCsAlipayRecord(CsAlipayRecord csAlipayRecord);
	/**
	 * 更新接口充值非空字段
	 * @param csAlipayRecord
	 */
	public void updateCsAlipayRecord$NotNull(CsAlipayRecord csAlipayRecord);

	/**
	 * 根据ID删除一个接口充值
	 * @param id
	 */
	public void deleteCsAlipayRecordById(Long id);
	/**
	 * 根据ID逻辑删除一个接口充值
	 * @param id
	 */
	public void removeCsAlipayRecordById(Long id);
	/**
	 * 根据条件更新接口充值
	 * @param values
	 * @param params
	 */
	public void updateCsAlipayRecordByConfirm(Map values, Map params);
	/**
	 * 根据条件删除接口充值
	 * @param params
	 */
	public void deleteCsAlipayRecordByConfirm(Map params);

}