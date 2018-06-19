package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsContent;

/**
 * 静态内容的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsContentDao
{
	/**
	 * 获取所有静态内容
	 * @return
	 */
	public List<CsContent> getCsContentList(Map params,Integer size);
	
	/**
	 * 获取静态内容统计
	 * @return
	 */
	public List getCsContentStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取静态内容总数
	 * @return
	 */	
	public Long getCsContentCount(Map params);
	
	/**
	 * 获取静态内容自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsContentEval(String eval,Map params);
	
	/**
	 * 获取静态内容分页
	 * @return
	 */	
	public Page getCsContentPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取静态内容
	 * @param params
	 * @return
	 */
	public CsContent getCsContent(Map params);	

	/**
	 * 根据ID取静态内容
	 * @param id
	 * @return
	 */
	public CsContent getCsContentById(Long id);


	
	

	/**
	 * 保存静态内容
	 * @param csContent
	 */
	public CsContent saveCsContent(CsContent csContent);

	/**
	 * 更新静态内容
	 * @param csContent
	 */
	public void updateCsContent(CsContent csContent);
	/**
	 * 更新静态内容非空字段
	 * @param csContent
	 */
	public void updateCsContent$NotNull(CsContent csContent);

	/**
	 * 根据ID删除一个静态内容
	 * @param id
	 */
	public void deleteCsContentById(Long id);
	/**
	 * 根据ID逻辑删除一个静态内容
	 * @param id
	 */
	public void removeCsContentById(Long id);
	/**
	 * 根据条件更新静态内容
	 * @param values
	 * @param params
	 */
	public void updateCsContentByConfirm(Map values, Map params);
	/**
	 * 根据条件删除静态内容
	 * @param params
	 */
	public void deleteCsContentByConfirm(Map params);

}