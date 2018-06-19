package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsRepairAdr;

/**
 * 维修地点的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsRepairAdrDao
{
	/**
	 * 获取所有维修地点
	 * @return
	 */
	public List<CsRepairAdr> getCsRepairAdrList(Map params,Integer size);
	
	/**
	 * 获取维修地点统计
	 * @return
	 */
	public List getCsRepairAdrStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取维修地点总数
	 * @return
	 */	
	public Long getCsRepairAdrCount(Map params);
	
	/**
	 * 获取维修地点自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsRepairAdrEval(String eval,Map params);
	
	/**
	 * 获取维修地点分页
	 * @return
	 */	
	public Page getCsRepairAdrPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取维修地点
	 * @param params
	 * @return
	 */
	public CsRepairAdr getCsRepairAdr(Map params);	

	/**
	 * 根据ID取维修地点
	 * @param id
	 * @return
	 */
	public CsRepairAdr getCsRepairAdrById(Long id);


	
	

	/**
	 * 保存维修地点
	 * @param csRepairAdr
	 */
	public CsRepairAdr saveCsRepairAdr(CsRepairAdr csRepairAdr);

	/**
	 * 更新维修地点
	 * @param csRepairAdr
	 */
	public void updateCsRepairAdr(CsRepairAdr csRepairAdr);
	/**
	 * 更新维修地点非空字段
	 * @param csRepairAdr
	 */
	public void updateCsRepairAdr$NotNull(CsRepairAdr csRepairAdr);

	/**
	 * 根据ID删除一个维修地点
	 * @param id
	 */
	public void deleteCsRepairAdrById(Long id);
	/**
	 * 根据条件更新维修地点
	 * @param values
	 * @param params
	 */
	public void updateCsRepairAdrByConfirm(Map values, Map params);
	/**
	 * 根据条件删除维修地点
	 * @param params
	 */
	public void deleteCsRepairAdrByConfirm(Map params);

}