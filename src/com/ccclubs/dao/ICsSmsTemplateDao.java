package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsSmsTemplate;

/**
 * 短信模版的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsSmsTemplateDao
{
	/**
	 * 获取所有短信模版
	 * @return
	 */
	public List<CsSmsTemplate> getCsSmsTemplateList(Map params,Integer size);
	
	/**
	 * 获取短信模版统计
	 * @return
	 */
	public List getCsSmsTemplateStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取短信模版总数
	 * @return
	 */	
	public Long getCsSmsTemplateCount(Map params);
	
	/**
	 * 获取短信模版自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsSmsTemplateEval(String eval,Map params);
	
	/**
	 * 获取短信模版分页
	 * @return
	 */	
	public Page getCsSmsTemplatePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取短信模版
	 * @param params
	 * @return
	 */
	public CsSmsTemplate getCsSmsTemplate(Map params);	

	/**
	 * 根据ID取短信模版
	 * @param id
	 * @return
	 */
	public CsSmsTemplate getCsSmsTemplateById(Long id);


	
	

	/**
	 * 保存短信模版
	 * @param csSmsTemplate
	 */
	public CsSmsTemplate saveCsSmsTemplate(CsSmsTemplate csSmsTemplate);

	/**
	 * 更新短信模版
	 * @param csSmsTemplate
	 */
	public void updateCsSmsTemplate(CsSmsTemplate csSmsTemplate);
	/**
	 * 更新短信模版非空字段
	 * @param csSmsTemplate
	 */
	public void updateCsSmsTemplate$NotNull(CsSmsTemplate csSmsTemplate);

	/**
	 * 根据ID删除一个短信模版
	 * @param id
	 */
	public void deleteCsSmsTemplateById(Long id);
	/**
	 * 根据ID逻辑删除一个短信模版
	 * @param id
	 */
	public void removeCsSmsTemplateById(Long id);
	/**
	 * 根据条件更新短信模版
	 * @param values
	 * @param params
	 */
	public void updateCsSmsTemplateByConfirm(Map values, Map params);
	/**
	 * 根据条件删除短信模版
	 * @param params
	 */
	public void deleteCsSmsTemplateByConfirm(Map params);

}