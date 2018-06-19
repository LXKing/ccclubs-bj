package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsVisitIp;

/**
 * 独立IP访问的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsVisitIpDao
{
	/**
	 * 获取所有独立IP访问
	 * @return
	 */
	public List<CsVisitIp> getCsVisitIpList(Map params,Integer size);
	
	/**
	 * 获取独立IP访问统计
	 * @return
	 */
	public List getCsVisitIpStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取独立IP访问总数
	 * @return
	 */	
	public Long getCsVisitIpCount(Map params);
	
	/**
	 * 获取独立IP访问自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsVisitIpEval(String eval,Map params);
	
	/**
	 * 获取独立IP访问分页
	 * @return
	 */	
	public Page getCsVisitIpPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取独立IP访问
	 * @param params
	 * @return
	 */
	public CsVisitIp getCsVisitIp(Map params);	

	/**
	 * 根据ID取独立IP访问
	 * @param id
	 * @return
	 */
	public CsVisitIp getCsVisitIpById(Long id);


	
	

	/**
	 * 保存独立IP访问
	 * @param csVisitIp
	 */
	public CsVisitIp saveCsVisitIp(CsVisitIp csVisitIp);

	/**
	 * 更新独立IP访问
	 * @param csVisitIp
	 */
	public void updateCsVisitIp(CsVisitIp csVisitIp);
	/**
	 * 更新独立IP访问非空字段
	 * @param csVisitIp
	 */
	public void updateCsVisitIp$NotNull(CsVisitIp csVisitIp);

	/**
	 * 根据ID删除一个独立IP访问
	 * @param id
	 */
	public void deleteCsVisitIpById(Long id);
	/**
	 * 根据条件更新独立IP访问
	 * @param values
	 * @param params
	 */
	public void updateCsVisitIpByConfirm(Map values, Map params);
	/**
	 * 根据条件删除独立IP访问
	 * @param params
	 */
	public void deleteCsVisitIpByConfirm(Map params);

}