package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsWxContentDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsWxContent;
import com.ccclubs.service.admin.ICsWxContentService;
import com.lazy3q.web.helper.$;

/**
 * 微信素材的Service实现
 * @author 飞啊飘啊
 */
public class CsWxContentService implements ICsWxContentService
{
	ICsWxContentDao csWxContentDao;
	

	/**
	 * 获取所有微信素材
	 * @return
	 */
	public List<CsWxContent> getCsWxContentList(Map params,Integer size)
	{
		return csWxContentDao.getCsWxContentList(params,size);
	}
	
	/**
	 * 获取微信素材统计
	 * @return
	 */
	public List getCsWxContentStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csWxContentDao.getCsWxContentStats(params,groups,sums);
	}
	
	/**
	 * 获取微信素材总数
	 * @return
	 */
	public Long getCsWxContentCount(Map params)
	{
		return csWxContentDao.getCsWxContentCount(params);
	}
	
	/**
	 * 获取微信素材自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsWxContentEval(String eval,Map params)
	{
		return csWxContentDao.getCsWxContentEval(eval,params);
	}

	/**
	 * 获取微信素材分页
	 * @return
	 */
	public Page getCsWxContentPage(int page,int size,Map params)
	{
		return csWxContentDao.getCsWxContentPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取微信素材
	 * @param params
	 * @return
	 */
	public CsWxContent getCsWxContent(Map params)
	{
		return csWxContentDao.getCsWxContent(params);
	}

	/**
	 * 根据ID取微信素材
	 * @param id
	 * @return
	 */
	public CsWxContent getCsWxContentById(Long id)
	{
		return csWxContentDao.getCsWxContentById(id);
	}

	/**
	 * 保存微信素材
	 * @param csWxContent
	 */
	public CsWxContent saveCsWxContent(CsWxContent csWxContent)
	{
		return csWxContentDao.saveCsWxContent(csWxContent);
	}
	
	/**
	 * 更新微信素材
	 * @param csWxContent
	 */
	public void updateCsWxContent(CsWxContent csWxContent)
	{
		csWxContentDao.updateCsWxContent(csWxContent);
	}
	/**
	 * 更新微信素材非空字段
	 * @param csWxContent
	 */
	public void updateCsWxContent$NotNull(CsWxContent csWxContent)
	{
		csWxContentDao.updateCsWxContent$NotNull(csWxContent);
	}
	
	/**
	 * 根据ID删除一个微信素材
	 * @param id
	 */
	public void deleteCsWxContentById(Long id)
	{
		csWxContentDao.deleteCsWxContentById(id);
	}
	
	/**
	 * 根据条件更新微信素材
	 * @param values
	 * @param params
	 */
	public void updateCsWxContentByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsWxContentBy时无条件");
		csWxContentDao.updateCsWxContentByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除微信素材
	 * @param params
	 */
	public void deleteCsWxContentByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsWxContentBy时无条件");
		csWxContentDao.deleteCsWxContentByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsWxContentDao getCsWxContentDao()
	{
		return csWxContentDao;
	}

	public void setCsWxContentDao(ICsWxContentDao csWxContentDao)
	{
		this.csWxContentDao = csWxContentDao;
	}
}