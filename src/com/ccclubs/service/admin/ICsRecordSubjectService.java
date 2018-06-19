package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsRecordSubject;

/**
 * 消费科目的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsRecordSubjectService
{
	/**
	 * 获取所有消费科目
	 * @return
	 */
	public List<CsRecordSubject> getCsRecordSubjectList(Map params,Integer size);
	
	/**
	 * 获取消费科目统计
	 * @return
	 */
	public List getCsRecordSubjectStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取消费科目总数
	 * @return
	 */	
	public Long getCsRecordSubjectCount(Map params);
	
	
	/**
	 * 获取消费科目自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsRecordSubjectEval(String eval,Map params);

	/**
	 * 获取消费科目分页
	 * @return
	 */	
	public Page getCsRecordSubjectPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取消费科目
	 * @param params
	 * @return
	 */
	public CsRecordSubject getCsRecordSubject(Map params);

	/**
	 * 根据ID取消费科目
	 * @param id
	 * @return
	 */
	public CsRecordSubject getCsRecordSubjectById(Long id);


	/**
	 * 保存消费科目
	 * @param csRecordSubject
	 */
	public CsRecordSubject saveCsRecordSubject(CsRecordSubject csRecordSubject);

	/**
	 * 更新消费科目
	 * @param csRecordSubject
	 */
	public void updateCsRecordSubject(CsRecordSubject csRecordSubject);
	/**
	 * 更新消费科目非空字段
	 * @param csRecordSubject
	 */
	public void updateCsRecordSubject$NotNull(CsRecordSubject csRecordSubject);

	/**
	 * 根据ID删除一个消费科目
	 * @param id
	 */
	public void deleteCsRecordSubjectById(Long id);
	/**
	 * 根据条件更新消费科目
	 * @param values
	 * @param params
	 */
	public void updateCsRecordSubjectByConfirm(Map values, Map params);
	/**
	 * 根据条件删除消费科目
	 * @param params
	 */
	public void deleteCsRecordSubjectByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}