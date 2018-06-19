package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsUnitInfo;

/**
 * 企业信息的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsUnitInfoDao
{
	/**
	 * 获取所有企业信息
	 * @return
	 */
	public List<CsUnitInfo> getCsUnitInfoList(Map params,Integer size);
	
	/**
	 * 获取企业信息统计
	 * @return
	 */
	public List getCsUnitInfoStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取企业信息总数
	 * @return
	 */	
	public Long getCsUnitInfoCount(Map params);
	
	/**
	 * 获取企业信息自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsUnitInfoEval(String eval,Map params);
	
	/**
	 * 获取企业信息分页
	 * @return
	 */	
	public Page getCsUnitInfoPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取企业信息
	 * @param params
	 * @return
	 */
	public CsUnitInfo getCsUnitInfo(Map params);	

	/**
	 * 根据ID取企业信息
	 * @param id
	 * @return
	 */
	public CsUnitInfo getCsUnitInfoById(Long id);


	
	

	/**
	 * 保存企业信息
	 * @param csUnitInfo
	 */
	public CsUnitInfo saveCsUnitInfo(CsUnitInfo csUnitInfo);

	/**
	 * 更新企业信息
	 * @param csUnitInfo
	 */
	public void updateCsUnitInfo(CsUnitInfo csUnitInfo);
	/**
	 * 更新企业信息非空字段
	 * @param csUnitInfo
	 */
	public void updateCsUnitInfo$NotNull(CsUnitInfo csUnitInfo);

	/**
	 * 根据ID删除一个企业信息
	 * @param id
	 */
	public void deleteCsUnitInfoById(Long id);
	/**
	 * 根据ID逻辑删除一个企业信息
	 * @param id
	 */
	public void removeCsUnitInfoById(Long id);
	/**
	 * 根据条件更新企业信息
	 * @param values
	 * @param params
	 */
	public void updateCsUnitInfoByConfirm(Map values, Map params);
	/**
	 * 根据条件删除企业信息
	 * @param params
	 */
	public void deleteCsUnitInfoByConfirm(Map params);

}