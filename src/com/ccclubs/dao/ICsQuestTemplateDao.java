package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsQuestTemplate;

/**
 * 问卷模板的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsQuestTemplateDao
{
	/**
	 * 获取所有问卷模板
	 * @return
	 */
	public List<CsQuestTemplate> getCsQuestTemplateList(Map params,Integer size);
	
	/**
	 * 获取问卷模板统计
	 * @return
	 */
	public List getCsQuestTemplateStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取问卷模板总数
	 * @return
	 */	
	public Long getCsQuestTemplateCount(Map params);
	
	/**
	 * 获取问卷模板自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsQuestTemplateEval(String eval,Map params);
	
	/**
	 * 获取问卷模板分页
	 * @return
	 */	
	public Page getCsQuestTemplatePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取问卷模板
	 * @param params
	 * @return
	 */
	public CsQuestTemplate getCsQuestTemplate(Map params);	

	/**
	 * 根据ID取问卷模板
	 * @param id
	 * @return
	 */
	public CsQuestTemplate getCsQuestTemplateById(Long id);


	
	

	/**
	 * 保存问卷模板
	 * @param csQuestTemplate
	 */
	public CsQuestTemplate saveCsQuestTemplate(CsQuestTemplate csQuestTemplate);

	/**
	 * 更新问卷模板
	 * @param csQuestTemplate
	 */
	public void updateCsQuestTemplate(CsQuestTemplate csQuestTemplate);
	/**
	 * 更新问卷模板非空字段
	 * @param csQuestTemplate
	 */
	public void updateCsQuestTemplate$NotNull(CsQuestTemplate csQuestTemplate);

	/**
	 * 根据ID删除一个问卷模板
	 * @param id
	 */
	public void deleteCsQuestTemplateById(Long id);
	/**
	 * 根据ID逻辑删除一个问卷模板
	 * @param id
	 */
	public void removeCsQuestTemplateById(Long id);
	/**
	 * 根据条件更新问卷模板
	 * @param values
	 * @param params
	 */
	public void updateCsQuestTemplateByConfirm(Map values, Map params);
	/**
	 * 根据条件删除问卷模板
	 * @param params
	 */
	public void deleteCsQuestTemplateByConfirm(Map params);

}