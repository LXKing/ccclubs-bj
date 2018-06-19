package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsRecordTypeDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsRecordType;
import com.ccclubs.service.admin.ICsRecordTypeService;
import com.lazy3q.web.helper.$;

/**
 * 消费类型的Service实现
 * @author 飞啊飘啊
 */
public class CsRecordTypeService implements ICsRecordTypeService
{
	ICsRecordTypeDao csRecordTypeDao;
	

	/**
	 * 获取所有消费类型
	 * @return
	 */
	public List<CsRecordType> getCsRecordTypeList(Map params,Integer size)
	{
		return csRecordTypeDao.getCsRecordTypeList(params,size);
	}
	
	/**
	 * 获取消费类型统计
	 * @return
	 */
	public List getCsRecordTypeStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csRecordTypeDao.getCsRecordTypeStats(params,groups,sums);
	}
	
	/**
	 * 获取消费类型总数
	 * @return
	 */
	public Long getCsRecordTypeCount(Map params)
	{
		return csRecordTypeDao.getCsRecordTypeCount(params);
	}
	
	/**
	 * 获取消费类型自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsRecordTypeEval(String eval,Map params)
	{
		return csRecordTypeDao.getCsRecordTypeEval(eval,params);
	}

	/**
	 * 获取消费类型分页
	 * @return
	 */
	public Page getCsRecordTypePage(int page,int size,Map params)
	{
		return csRecordTypeDao.getCsRecordTypePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取消费类型
	 * @param params
	 * @return
	 */
	public CsRecordType getCsRecordType(Map params)
	{
		return csRecordTypeDao.getCsRecordType(params);
	}

	/**
	 * 根据ID取消费类型
	 * @param id
	 * @return
	 */
	public CsRecordType getCsRecordTypeById(Long id)
	{
		return csRecordTypeDao.getCsRecordTypeById(id);
	}

	/**
	 * 保存消费类型
	 * @param csRecordType
	 */
	public CsRecordType saveCsRecordType(CsRecordType csRecordType)
	{
		return csRecordTypeDao.saveCsRecordType(csRecordType);
	}
	
	/**
	 * 更新消费类型
	 * @param csRecordType
	 */
	public void updateCsRecordType(CsRecordType csRecordType)
	{
		csRecordTypeDao.updateCsRecordType(csRecordType);
	}
	/**
	 * 更新消费类型非空字段
	 * @param csRecordType
	 */
	public void updateCsRecordType$NotNull(CsRecordType csRecordType)
	{
		csRecordTypeDao.updateCsRecordType$NotNull(csRecordType);
	}
	
	/**
	 * 根据ID删除一个消费类型
	 * @param id
	 */
	public void deleteCsRecordTypeById(Long id)
	{
		csRecordTypeDao.deleteCsRecordTypeById(id);
	}
	/**
	 * 根据ID逻辑删除一个消费类型
	 * @param id
	 */
	public void removeCsRecordTypeById(Long id)
	{
		csRecordTypeDao.removeCsRecordTypeById(id);
	}
	
	/**
	 * 根据条件更新消费类型
	 * @param values
	 * @param params
	 */
	public void updateCsRecordTypeByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsRecordTypeBy时无条件");
		csRecordTypeDao.updateCsRecordTypeByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除消费类型
	 * @param params
	 */
	public void deleteCsRecordTypeByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsRecordTypeBy时无条件");
		csRecordTypeDao.deleteCsRecordTypeByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsRecordTypeDao getCsRecordTypeDao()
	{
		return csRecordTypeDao;
	}

	public void setCsRecordTypeDao(ICsRecordTypeDao csRecordTypeDao)
	{
		this.csRecordTypeDao = csRecordTypeDao;
	}
}