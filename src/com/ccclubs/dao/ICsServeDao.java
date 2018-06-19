package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsServe;

/**
 * 客户服务的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsServeDao
{
	/**
	 * 获取所有客户服务
	 * @return
	 */
	public List<CsServe> getCsServeList(Map params,Integer size);
	
	/**
	 * 获取客户服务统计
	 * @return
	 */
	public List getCsServeStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取客户服务总数
	 * @return
	 */	
	public Long getCsServeCount(Map params);
	
	/**
	 * 获取客户服务自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsServeEval(String eval,Map params);
	
	/**
	 * 获取客户服务分页
	 * @return
	 */	
	public Page getCsServePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取客户服务
	 * @param params
	 * @return
	 */
	public CsServe getCsServe(Map params);	

	/**
	 * 根据ID取客户服务
	 * @param id
	 * @return
	 */
	public CsServe getCsServeById(Long id);


	
	

	/**
	 * 保存客户服务
	 * @param csServe
	 */
	public CsServe saveCsServe(CsServe csServe);

	/**
	 * 更新客户服务
	 * @param csServe
	 */
	public void updateCsServe(CsServe csServe);
	/**
	 * 更新客户服务非空字段
	 * @param csServe
	 */
	public void updateCsServe$NotNull(CsServe csServe);

	/**
	 * 根据ID删除一个客户服务
	 * @param id
	 */
	public void deleteCsServeById(Long id);
	/**
	 * 根据ID逻辑删除一个客户服务
	 * @param id
	 */
	public void removeCsServeById(Long id);
	/**
	 * 根据条件更新客户服务
	 * @param values
	 * @param params
	 */
	public void updateCsServeByConfirm(Map values, Map params);
	/**
	 * 根据条件删除客户服务
	 * @param params
	 */
	public void deleteCsServeByConfirm(Map params);

}