package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsContentDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsContent;
import com.ccclubs.service.admin.ICsContentService;
import com.lazy3q.web.helper.$;

/**
 * 静态内容的Service实现
 * @author 飞啊飘啊
 */
public class CsContentService implements ICsContentService
{
	ICsContentDao csContentDao;
	

	/**
	 * 获取所有静态内容
	 * @return
	 */
	public List<CsContent> getCsContentList(Map params,Integer size)
	{
		return csContentDao.getCsContentList(params,size);
	}
	
	/**
	 * 获取静态内容统计
	 * @return
	 */
	public List getCsContentStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csContentDao.getCsContentStats(params,groups,sums);
	}
	
	/**
	 * 获取静态内容总数
	 * @return
	 */
	public Long getCsContentCount(Map params)
	{
		return csContentDao.getCsContentCount(params);
	}
	
	/**
	 * 获取静态内容自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsContentEval(String eval,Map params)
	{
		return csContentDao.getCsContentEval(eval,params);
	}

	/**
	 * 获取静态内容分页
	 * @return
	 */
	public Page getCsContentPage(int page,int size,Map params)
	{
		return csContentDao.getCsContentPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取静态内容
	 * @param params
	 * @return
	 */
	public CsContent getCsContent(Map params)
	{
		return csContentDao.getCsContent(params);
	}

	/**
	 * 根据ID取静态内容
	 * @param id
	 * @return
	 */
	public CsContent getCsContentById(Long id)
	{
		return csContentDao.getCsContentById(id);
	}

	/**
	 * 保存静态内容
	 * @param csContent
	 */
	public CsContent saveCsContent(CsContent csContent)
	{
		return csContentDao.saveCsContent(csContent);
	}
	
	/**
	 * 更新静态内容
	 * @param csContent
	 */
	public void updateCsContent(CsContent csContent)
	{
		csContentDao.updateCsContent(csContent);
	}
	/**
	 * 更新静态内容非空字段
	 * @param csContent
	 */
	public void updateCsContent$NotNull(CsContent csContent)
	{
		csContentDao.updateCsContent$NotNull(csContent);
	}
	
	/**
	 * 根据ID删除一个静态内容
	 * @param id
	 */
	public void deleteCsContentById(Long id)
	{
		csContentDao.deleteCsContentById(id);
		List<CsContent> list = csContentDao.getCsContentList($.Map().add("cscParent", id),-1);
		while(!list.isEmpty()){
			String strIds="";
			for(CsContent csContent:list){
				if(!strIds.equals(""))
					strIds+=",";
				strIds+=csContent.getCscId();
			}
			csContentDao.deleteCsContentByConfirm($.Map().add("definex", "csc_id in ("+strIds+")").add("confirm",1));
			list=csContentDao.getCsContentList($.Map().add("definex", "csc_parent in ("+strIds+")"),-1);
		}
	}
	/**
	 * 根据ID逻辑删除一个静态内容
	 * @param id
	 */
	public void removeCsContentById(Long id)
	{
		csContentDao.removeCsContentById(id);
		List<CsContent> list = csContentDao.getCsContentList($.Map().add("cscParent", id),-1);
		while(!list.isEmpty()){
			String strIds="";
			for(CsContent csContent:list){
				if(!strIds.equals(""))
					strIds+=",";
				strIds+=csContent.getCscId();
			}
			csContentDao.updateCsContentByConfirm($.Map().add("cscStatus", "-1"),$.Map().add("definex", "csc_id in ("+strIds+")").add("confirm",1));
			list=csContentDao.getCsContentList($.Map().add("definex", "csc_parent in ("+strIds+")"),-1);
		}
	}
	
	/**
	 * 根据条件更新静态内容
	 * @param values
	 * @param params
	 */
	public void updateCsContentByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsContentBy时无条件");
		csContentDao.updateCsContentByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除静态内容
	 * @param params
	 */
	public void deleteCsContentByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsContentBy时无条件");
		csContentDao.deleteCsContentByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsContentDao getCsContentDao()
	{
		return csContentDao;
	}

	public void setCsContentDao(ICsContentDao csContentDao)
	{
		this.csContentDao = csContentDao;
	}
}