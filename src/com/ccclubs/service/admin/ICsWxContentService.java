package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsWxContent;

/**
 * 微信素材的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsWxContentService
{
	/**
	 * 获取所有微信素材
	 * @return
	 */
	public List<CsWxContent> getCsWxContentList(Map params,Integer size);
	
	/**
	 * 获取微信素材统计
	 * @return
	 */
	public List getCsWxContentStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取微信素材总数
	 * @return
	 */	
	public Long getCsWxContentCount(Map params);
	
	
	/**
	 * 获取微信素材自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsWxContentEval(String eval,Map params);

	/**
	 * 获取微信素材分页
	 * @return
	 */	
	public Page getCsWxContentPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取微信素材
	 * @param params
	 * @return
	 */
	public CsWxContent getCsWxContent(Map params);

	/**
	 * 根据ID取微信素材
	 * @param id
	 * @return
	 */
	public CsWxContent getCsWxContentById(Long id);


	/**
	 * 保存微信素材
	 * @param csWxContent
	 */
	public CsWxContent saveCsWxContent(CsWxContent csWxContent);

	/**
	 * 更新微信素材
	 * @param csWxContent
	 */
	public void updateCsWxContent(CsWxContent csWxContent);
	/**
	 * 更新微信素材非空字段
	 * @param csWxContent
	 */
	public void updateCsWxContent$NotNull(CsWxContent csWxContent);

	/**
	 * 根据ID删除一个微信素材
	 * @param id
	 */
	public void deleteCsWxContentById(Long id);
	/**
	 * 根据条件更新微信素材
	 * @param values
	 * @param params
	 */
	public void updateCsWxContentByConfirm(Map values, Map params);
	/**
	 * 根据条件删除微信素材
	 * @param params
	 */
	public void deleteCsWxContentByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}