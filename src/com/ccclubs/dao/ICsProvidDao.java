package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsProvid;

/**
 * 供应商的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsProvidDao
{
	/**
	 * 获取所有供应商
	 * @return
	 */
	public List<CsProvid> getCsProvidList(Map params,Integer size);
	
	/**
	 * 获取供应商统计
	 * @return
	 */
	public List getCsProvidStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取供应商总数
	 * @return
	 */	
	public Long getCsProvidCount(Map params);
	
	/**
	 * 获取供应商自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsProvidEval(String eval,Map params);
	
	/**
	 * 获取供应商分页
	 * @return
	 */	
	public Page getCsProvidPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取供应商
	 * @param params
	 * @return
	 */
	public CsProvid getCsProvid(Map params);	

	/**
	 * 根据ID取供应商
	 * @param id
	 * @return
	 */
	public CsProvid getCsProvidById(Long id);


	
	

	/**
	 * 保存供应商
	 * @param csProvid
	 */
	public CsProvid saveCsProvid(CsProvid csProvid);

	/**
	 * 更新供应商
	 * @param csProvid
	 */
	public void updateCsProvid(CsProvid csProvid);
	/**
	 * 更新供应商非空字段
	 * @param csProvid
	 */
	public void updateCsProvid$NotNull(CsProvid csProvid);

	/**
	 * 根据ID删除一个供应商
	 * @param id
	 */
	public void deleteCsProvidById(Long id);
	/**
	 * 根据ID逻辑删除一个供应商
	 * @param id
	 */
	public void removeCsProvidById(Long id);
	/**
	 * 根据条件更新供应商
	 * @param values
	 * @param params
	 */
	public void updateCsProvidByConfirm(Map values, Map params);
	/**
	 * 根据条件删除供应商
	 * @param params
	 */
	public void deleteCsProvidByConfirm(Map params);

}