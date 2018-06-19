package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsQuestAnswer;

/**
 * 问题及选项的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsQuestAnswerService
{
	/**
	 * 获取所有问题及选项
	 * @return
	 */
	public List<CsQuestAnswer> getCsQuestAnswerList(Map params,Integer size);
	
	/**
	 * 获取问题及选项统计
	 * @return
	 */
	public List getCsQuestAnswerStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取问题及选项总数
	 * @return
	 */	
	public Long getCsQuestAnswerCount(Map params);
	
	
	/**
	 * 获取问题及选项自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsQuestAnswerEval(String eval,Map params);

	/**
	 * 获取问题及选项分页
	 * @return
	 */	
	public Page getCsQuestAnswerPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取问题及选项
	 * @param params
	 * @return
	 */
	public CsQuestAnswer getCsQuestAnswer(Map params);

	/**
	 * 根据ID取问题及选项
	 * @param id
	 * @return
	 */
	public CsQuestAnswer getCsQuestAnswerById(Long id);


	/**
	 * 保存问题及选项
	 * @param csQuestAnswer
	 */
	public CsQuestAnswer saveCsQuestAnswer(CsQuestAnswer csQuestAnswer);

	/**
	 * 更新问题及选项
	 * @param csQuestAnswer
	 */
	public void updateCsQuestAnswer(CsQuestAnswer csQuestAnswer);
	/**
	 * 更新问题及选项非空字段
	 * @param csQuestAnswer
	 */
	public void updateCsQuestAnswer$NotNull(CsQuestAnswer csQuestAnswer);

	/**
	 * 根据ID删除一个问题及选项
	 * @param id
	 */
	public void deleteCsQuestAnswerById(Long id);
	/**
	 * 根据条件更新问题及选项
	 * @param values
	 * @param params
	 */
	public void updateCsQuestAnswerByConfirm(Map values, Map params);
	/**
	 * 根据条件删除问题及选项
	 * @param params
	 */
	public void deleteCsQuestAnswerByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}