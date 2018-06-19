package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsPackInfo;

/**
 * 套餐内容的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsPackInfoService
{
	/**
	 * 获取所有套餐内容
	 * @return
	 */
	public List<CsPackInfo> getCsPackInfoList(Map params,Integer size);
	
	/**
	 * 获取套餐内容统计
	 * @return
	 */
	public List getCsPackInfoStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取套餐内容总数
	 * @return
	 */	
	public Long getCsPackInfoCount(Map params);
	
	
	/**
	 * 获取套餐内容自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsPackInfoEval(String eval,Map params);

	/**
	 * 获取套餐内容分页
	 * @return
	 */	
	public Page getCsPackInfoPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取套餐内容
	 * @param params
	 * @return
	 */
	public CsPackInfo getCsPackInfo(Map params);

	/**
	 * 根据ID取套餐内容
	 * @param id
	 * @return
	 */
	public CsPackInfo getCsPackInfoById(Long id);


	/**
	 * 保存套餐内容
	 * @param csPackInfo
	 */
	public CsPackInfo saveCsPackInfo(CsPackInfo csPackInfo);

	/**
	 * 更新套餐内容
	 * @param csPackInfo
	 */
	public void updateCsPackInfo(CsPackInfo csPackInfo);
	/**
	 * 更新套餐内容非空字段
	 * @param csPackInfo
	 */
	public void updateCsPackInfo$NotNull(CsPackInfo csPackInfo);

	/**
	 * 根据ID删除一个套餐内容
	 * @param id
	 */
	public void deleteCsPackInfoById(Long id);
	/**
	 * 根据条件更新套餐内容
	 * @param values
	 * @param params
	 */
	public void updateCsPackInfoByConfirm(Map values, Map params);
	/**
	 * 根据条件删除套餐内容
	 * @param params
	 */
	public void deleteCsPackInfoByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}