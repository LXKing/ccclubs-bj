package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsArgument;

/**
 * 系统参数的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsArgumentDao
{
	/**
	 * 获取所有系统参数
	 * @return
	 */
	public List<CsArgument> getCsArgumentList(Map params,Integer size);
	
	/**
	 * 获取系统参数统计
	 * @return
	 */
	public List getCsArgumentStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取系统参数总数
	 * @return
	 */	
	public Long getCsArgumentCount(Map params);
	
	/**
	 * 获取系统参数自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsArgumentEval(String eval,Map params);
	
	/**
	 * 获取系统参数分页
	 * @return
	 */	
	public Page getCsArgumentPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取系统参数
	 * @param params
	 * @return
	 */
	public CsArgument getCsArgument(Map params);	

	/**
	 * 根据ID取系统参数
	 * @param id
	 * @return
	 */
	public CsArgument getCsArgumentById(Long id);


	
	

	/**
	 * 保存系统参数
	 * @param csArgument
	 */
	public CsArgument saveCsArgument(CsArgument csArgument);

	/**
	 * 更新系统参数
	 * @param csArgument
	 */
	public void updateCsArgument(CsArgument csArgument);
	/**
	 * 更新系统参数非空字段
	 * @param csArgument
	 */
	public void updateCsArgument$NotNull(CsArgument csArgument);

	/**
	 * 根据ID删除一个系统参数
	 * @param id
	 */
	public void deleteCsArgumentById(Long id);
	/**
	 * 根据条件更新系统参数
	 * @param values
	 * @param params
	 */
	public void updateCsArgumentByConfirm(Map values, Map params);
	/**
	 * 根据条件删除系统参数
	 * @param params
	 */
	public void deleteCsArgumentByConfirm(Map params);

}