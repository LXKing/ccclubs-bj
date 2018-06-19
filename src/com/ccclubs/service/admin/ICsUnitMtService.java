package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsUnitMt;

/**
 * 推送消息的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsUnitMtService
{
	/**
	 * 获取所有推送消息
	 * @return
	 */
	public List<CsUnitMt> getCsUnitMtList(Map params,Integer size);
	
	/**
	 * 获取推送消息统计
	 * @return
	 */
	public List getCsUnitMtStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取推送消息总数
	 * @return
	 */	
	public Long getCsUnitMtCount(Map params);
	
	
	/**
	 * 获取推送消息自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsUnitMtEval(String eval,Map params);

	/**
	 * 获取推送消息分页
	 * @return
	 */	
	public Page getCsUnitMtPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取推送消息
	 * @param params
	 * @return
	 */
	public CsUnitMt getCsUnitMt(Map params);

	/**
	 * 根据ID取推送消息
	 * @param id
	 * @return
	 */
	public CsUnitMt getCsUnitMtById(Long id);


	/**
	 * 保存推送消息
	 * @param csUnitMt
	 */
	public CsUnitMt saveCsUnitMt(CsUnitMt csUnitMt);

	/**
	 * 更新推送消息
	 * @param csUnitMt
	 */
	public void updateCsUnitMt(CsUnitMt csUnitMt);
	/**
	 * 更新推送消息非空字段
	 * @param csUnitMt
	 */
	public void updateCsUnitMt$NotNull(CsUnitMt csUnitMt);

	/**
	 * 根据ID删除一个推送消息
	 * @param id
	 */
	public void deleteCsUnitMtById(Long id);
	/**
	 * 根据ID逻辑删除一个推送消息
	 * @param id
	 */
	public void removeCsUnitMtById(Long id);
	/**
	 * 根据条件更新推送消息
	 * @param values
	 * @param params
	 */
	public void updateCsUnitMtByConfirm(Map values, Map params);
	/**
	 * 根据条件删除推送消息
	 * @param params
	 */
	public void deleteCsUnitMtByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}