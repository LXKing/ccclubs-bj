package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsArtifRecord;

/**
 * 充值扣款的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsArtifRecordDao
{
	/**
	 * 获取所有充值扣款
	 * @return
	 */
	public List<CsArtifRecord> getCsArtifRecordList(Map params,Integer size);
	
	/**
	 * 获取充值扣款统计
	 * @return
	 */
	public List getCsArtifRecordStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取充值扣款总数
	 * @return
	 */	
	public Long getCsArtifRecordCount(Map params);
	
	/**
	 * 获取充值扣款自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsArtifRecordEval(String eval,Map params);
	
	/**
	 * 获取充值扣款分页
	 * @return
	 */	
	public Page getCsArtifRecordPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取充值扣款
	 * @param params
	 * @return
	 */
	public CsArtifRecord getCsArtifRecord(Map params);	

	/**
	 * 根据ID取充值扣款
	 * @param id
	 * @return
	 */
	public CsArtifRecord getCsArtifRecordById(Long id);


	
	

	/**
	 * 保存充值扣款
	 * @param csArtifRecord
	 */
	public CsArtifRecord saveCsArtifRecord(CsArtifRecord csArtifRecord);

	/**
	 * 更新充值扣款
	 * @param csArtifRecord
	 */
	public void updateCsArtifRecord(CsArtifRecord csArtifRecord);
	/**
	 * 更新充值扣款非空字段
	 * @param csArtifRecord
	 */
	public void updateCsArtifRecord$NotNull(CsArtifRecord csArtifRecord);

	/**
	 * 根据ID删除一个充值扣款
	 * @param id
	 */
	public void deleteCsArtifRecordById(Long id);
	/**
	 * 根据ID逻辑删除一个充值扣款
	 * @param id
	 */
	public void removeCsArtifRecordById(Long id);
	/**
	 * 根据条件更新充值扣款
	 * @param values
	 * @param params
	 */
	public void updateCsArtifRecordByConfirm(Map values, Map params);
	/**
	 * 根据条件删除充值扣款
	 * @param params
	 */
	public void deleteCsArtifRecordByConfirm(Map params);

}