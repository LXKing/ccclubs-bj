package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsQuestAnswerDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsQuestAnswer;
import com.ccclubs.service.admin.ICsQuestAnswerService;
import com.lazy3q.web.helper.$;

/**
 * 问题及选项的Service实现
 * @author 飞啊飘啊
 */
public class CsQuestAnswerService implements ICsQuestAnswerService
{
	ICsQuestAnswerDao csQuestAnswerDao;
	

	/**
	 * 获取所有问题及选项
	 * @return
	 */
	public List<CsQuestAnswer> getCsQuestAnswerList(Map params,Integer size)
	{
		return csQuestAnswerDao.getCsQuestAnswerList(params,size);
	}
	
	/**
	 * 获取问题及选项统计
	 * @return
	 */
	public List getCsQuestAnswerStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csQuestAnswerDao.getCsQuestAnswerStats(params,groups,sums);
	}
	
	/**
	 * 获取问题及选项总数
	 * @return
	 */
	public Long getCsQuestAnswerCount(Map params)
	{
		return csQuestAnswerDao.getCsQuestAnswerCount(params);
	}
	
	/**
	 * 获取问题及选项自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsQuestAnswerEval(String eval,Map params)
	{
		return csQuestAnswerDao.getCsQuestAnswerEval(eval,params);
	}

	/**
	 * 获取问题及选项分页
	 * @return
	 */
	public Page getCsQuestAnswerPage(int page,int size,Map params)
	{
		return csQuestAnswerDao.getCsQuestAnswerPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取问题及选项
	 * @param params
	 * @return
	 */
	public CsQuestAnswer getCsQuestAnswer(Map params)
	{
		return csQuestAnswerDao.getCsQuestAnswer(params);
	}

	/**
	 * 根据ID取问题及选项
	 * @param id
	 * @return
	 */
	public CsQuestAnswer getCsQuestAnswerById(Long id)
	{
		return csQuestAnswerDao.getCsQuestAnswerById(id);
	}

	/**
	 * 保存问题及选项
	 * @param csQuestAnswer
	 */
	public CsQuestAnswer saveCsQuestAnswer(CsQuestAnswer csQuestAnswer)
	{
		return csQuestAnswerDao.saveCsQuestAnswer(csQuestAnswer);
	}
	
	/**
	 * 更新问题及选项
	 * @param csQuestAnswer
	 */
	public void updateCsQuestAnswer(CsQuestAnswer csQuestAnswer)
	{
		csQuestAnswerDao.updateCsQuestAnswer(csQuestAnswer);
	}
	/**
	 * 更新问题及选项非空字段
	 * @param csQuestAnswer
	 */
	public void updateCsQuestAnswer$NotNull(CsQuestAnswer csQuestAnswer)
	{
		csQuestAnswerDao.updateCsQuestAnswer$NotNull(csQuestAnswer);
	}
	
	/**
	 * 根据ID删除一个问题及选项
	 * @param id
	 */
	public void deleteCsQuestAnswerById(Long id)
	{
		csQuestAnswerDao.deleteCsQuestAnswerById(id);
		List<CsQuestAnswer> list = csQuestAnswerDao.getCsQuestAnswerList($.Map().add("csqaParent", id),-1);
		while(!list.isEmpty()){
			String strIds="";
			for(CsQuestAnswer csQuestAnswer:list){
				if(!strIds.equals(""))
					strIds+=",";
				strIds+=csQuestAnswer.getCsqaId();
			}
			csQuestAnswerDao.deleteCsQuestAnswerByConfirm($.Map().add("definex", "csqa_id in ("+strIds+")").add("confirm",1));
			list=csQuestAnswerDao.getCsQuestAnswerList($.Map().add("definex", "csqa_parent in ("+strIds+")"),-1);
		}
	}
	
	/**
	 * 根据条件更新问题及选项
	 * @param values
	 * @param params
	 */
	public void updateCsQuestAnswerByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsQuestAnswerBy时无条件");
		csQuestAnswerDao.updateCsQuestAnswerByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除问题及选项
	 * @param params
	 */
	public void deleteCsQuestAnswerByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsQuestAnswerBy时无条件");
		csQuestAnswerDao.deleteCsQuestAnswerByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsQuestAnswerDao getCsQuestAnswerDao()
	{
		return csQuestAnswerDao;
	}

	public void setCsQuestAnswerDao(ICsQuestAnswerDao csQuestAnswerDao)
	{
		this.csQuestAnswerDao = csQuestAnswerDao;
	}
}