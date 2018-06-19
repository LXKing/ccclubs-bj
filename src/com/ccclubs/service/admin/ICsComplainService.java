package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsComplain;

/**
 * 意见反馈的Service接口
 * @author Joel
 */
@SuppressWarnings("unchecked")
public interface ICsComplainService
{
	/**
	 * 获取所有意见反馈
	 * @return
	 */
	public List<CsComplain> getCsComplainList(Map params,Integer size);
	
	/**
	 * 获取意见反馈统计
	 * @return
	 */
	public List getCsComplainStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取意见反馈总数
	 * @return
	 */	
	public Long getCsComplainCount(Map params);
	
	
	/**
	 * 获取意见反馈自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsComplainEval(String eval,Map params);

	/**
	 * 获取意见反馈分页
	 * @return
	 */	
	public Page getCsComplainPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取意见反馈
	 * @param params
	 * @return
	 */
	public CsComplain getCsComplain(Map params);

	/**
	 * 根据ID取意见反馈
	 * @param id
	 * @return
	 */
	public CsComplain getCsComplainById(Long id);


	/**
	 * 保存意见反馈
	 * @param csComplain
	 */
	public CsComplain saveCsComplain(CsComplain csComplain);

	/**
	 * 更新意见反馈
	 * @param csComplain
	 */
	public void updateCsComplain(CsComplain csComplain);
	/**
	 * 更新意见反馈非空字段
	 * @param csComplain
	 */
	public void updateCsComplain$NotNull(CsComplain csComplain);

	/**
	 * 根据ID删除一个意见反馈
	 * @param id
	 */
	public void deleteCsComplainById(Long id);
	/**
	 * 根据ID逻辑删除一个意见反馈
	 * @param id
	 */
	public void removeCsComplainById(Long id);
	/**
	 * 根据条件更新意见反馈
	 * @param values
	 * @param params
	 */
	public void updateCsComplainByConfirm(Map values, Map params);
	/**
	 * 根据条件删除意见反馈
	 * @param params
	 */
	public void deleteCsComplainByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}