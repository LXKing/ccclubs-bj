package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsRecordSubjectDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsRecordSubject;
import com.ccclubs.service.admin.ICsRecordSubjectService;
import com.lazy3q.web.helper.$;

/**
 * 消费科目的Service实现
 * @author 飞啊飘啊
 */
public class CsRecordSubjectService implements ICsRecordSubjectService
{
	ICsRecordSubjectDao csRecordSubjectDao;
	

	/**
	 * 获取所有消费科目
	 * @return
	 */
	public List<CsRecordSubject> getCsRecordSubjectList(Map params,Integer size)
	{
		return csRecordSubjectDao.getCsRecordSubjectList(params,size);
	}
	
	/**
	 * 获取消费科目统计
	 * @return
	 */
	public List getCsRecordSubjectStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csRecordSubjectDao.getCsRecordSubjectStats(params,groups,sums);
	}
	
	/**
	 * 获取消费科目总数
	 * @return
	 */
	public Long getCsRecordSubjectCount(Map params)
	{
		return csRecordSubjectDao.getCsRecordSubjectCount(params);
	}
	
	/**
	 * 获取消费科目自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsRecordSubjectEval(String eval,Map params)
	{
		return csRecordSubjectDao.getCsRecordSubjectEval(eval,params);
	}

	/**
	 * 获取消费科目分页
	 * @return
	 */
	public Page getCsRecordSubjectPage(int page,int size,Map params)
	{
		return csRecordSubjectDao.getCsRecordSubjectPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取消费科目
	 * @param params
	 * @return
	 */
	public CsRecordSubject getCsRecordSubject(Map params)
	{
		return csRecordSubjectDao.getCsRecordSubject(params);
	}

	/**
	 * 根据ID取消费科目
	 * @param id
	 * @return
	 */
	public CsRecordSubject getCsRecordSubjectById(Long id)
	{
		return csRecordSubjectDao.getCsRecordSubjectById(id);
	}

	/**
	 * 保存消费科目
	 * @param csRecordSubject
	 */
	public CsRecordSubject saveCsRecordSubject(CsRecordSubject csRecordSubject)
	{
		return csRecordSubjectDao.saveCsRecordSubject(csRecordSubject);
	}
	
	/**
	 * 更新消费科目
	 * @param csRecordSubject
	 */
	public void updateCsRecordSubject(CsRecordSubject csRecordSubject)
	{
		csRecordSubjectDao.updateCsRecordSubject(csRecordSubject);
	}
	/**
	 * 更新消费科目非空字段
	 * @param csRecordSubject
	 */
	public void updateCsRecordSubject$NotNull(CsRecordSubject csRecordSubject)
	{
		csRecordSubjectDao.updateCsRecordSubject$NotNull(csRecordSubject);
	}
	
	/**
	 * 根据ID删除一个消费科目
	 * @param id
	 */
	public void deleteCsRecordSubjectById(Long id)
	{
		csRecordSubjectDao.deleteCsRecordSubjectById(id);
	}
	
	/**
	 * 根据条件更新消费科目
	 * @param values
	 * @param params
	 */
	public void updateCsRecordSubjectByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsRecordSubjectBy时无条件");
		csRecordSubjectDao.updateCsRecordSubjectByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除消费科目
	 * @param params
	 */
	public void deleteCsRecordSubjectByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsRecordSubjectBy时无条件");
		csRecordSubjectDao.deleteCsRecordSubjectByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsRecordSubjectDao getCsRecordSubjectDao()
	{
		return csRecordSubjectDao;
	}

	public void setCsRecordSubjectDao(ICsRecordSubjectDao csRecordSubjectDao)
	{
		this.csRecordSubjectDao = csRecordSubjectDao;
	}
}