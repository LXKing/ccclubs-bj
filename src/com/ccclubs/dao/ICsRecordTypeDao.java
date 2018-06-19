package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsRecordType;

/**
 * 消费类型的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsRecordTypeDao
{
	/**
	 * 获取所有消费类型
	 * @return
	 */
	public List<CsRecordType> getCsRecordTypeList(Map params,Integer size);
	
	/**
	 * 获取消费类型统计
	 * @return
	 */
	public List getCsRecordTypeStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取消费类型总数
	 * @return
	 */	
	public Long getCsRecordTypeCount(Map params);
	
	/**
	 * 获取消费类型自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsRecordTypeEval(String eval,Map params);
	
	/**
	 * 获取消费类型分页
	 * @return
	 */	
	public Page getCsRecordTypePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取消费类型
	 * @param params
	 * @return
	 */
	public CsRecordType getCsRecordType(Map params);	

	/**
	 * 根据ID取消费类型
	 * @param id
	 * @return
	 */
	public CsRecordType getCsRecordTypeById(Long id);


	
	

	/**
	 * 保存消费类型
	 * @param csRecordType
	 */
	public CsRecordType saveCsRecordType(CsRecordType csRecordType);

	/**
	 * 更新消费类型
	 * @param csRecordType
	 */
	public void updateCsRecordType(CsRecordType csRecordType);
	/**
	 * 更新消费类型非空字段
	 * @param csRecordType
	 */
	public void updateCsRecordType$NotNull(CsRecordType csRecordType);

	/**
	 * 根据ID删除一个消费类型
	 * @param id
	 */
	public void deleteCsRecordTypeById(Long id);
	/**
	 * 根据ID逻辑删除一个消费类型
	 * @param id
	 */
	public void removeCsRecordTypeById(Long id);
	/**
	 * 根据条件更新消费类型
	 * @param values
	 * @param params
	 */
	public void updateCsRecordTypeByConfirm(Map values, Map params);
	/**
	 * 根据条件删除消费类型
	 * @param params
	 */
	public void deleteCsRecordTypeByConfirm(Map params);

}