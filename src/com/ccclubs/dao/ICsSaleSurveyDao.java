package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsSaleSurvey;

/**
 * 市场调查的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsSaleSurveyDao
{
	/**
	 * 获取所有市场调查
	 * @return
	 */
	public List<CsSaleSurvey> getCsSaleSurveyList(Map params,Integer size);
	
	/**
	 * 获取市场调查统计
	 * @return
	 */
	public List getCsSaleSurveyStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取市场调查总数
	 * @return
	 */	
	public Long getCsSaleSurveyCount(Map params);
	
	/**
	 * 获取市场调查自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsSaleSurveyEval(String eval,Map params);
	
	/**
	 * 获取市场调查分页
	 * @return
	 */	
	public Page getCsSaleSurveyPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取市场调查
	 * @param params
	 * @return
	 */
	public CsSaleSurvey getCsSaleSurvey(Map params);	

	/**
	 * 根据ID取市场调查
	 * @param id
	 * @return
	 */
	public CsSaleSurvey getCsSaleSurveyById(Long id);


	
	

	/**
	 * 保存市场调查
	 * @param csSaleSurvey
	 */
	public CsSaleSurvey saveCsSaleSurvey(CsSaleSurvey csSaleSurvey);

	/**
	 * 更新市场调查
	 * @param csSaleSurvey
	 */
	public void updateCsSaleSurvey(CsSaleSurvey csSaleSurvey);
	/**
	 * 更新市场调查非空字段
	 * @param csSaleSurvey
	 */
	public void updateCsSaleSurvey$NotNull(CsSaleSurvey csSaleSurvey);

	/**
	 * 根据ID删除一个市场调查
	 * @param id
	 */
	public void deleteCsSaleSurveyById(Long id);
	/**
	 * 根据ID逻辑删除一个市场调查
	 * @param id
	 */
	public void removeCsSaleSurveyById(Long id);
	/**
	 * 根据条件更新市场调查
	 * @param values
	 * @param params
	 */
	public void updateCsSaleSurveyByConfirm(Map values, Map params);
	/**
	 * 根据条件删除市场调查
	 * @param params
	 */
	public void deleteCsSaleSurveyByConfirm(Map params);

}