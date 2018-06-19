package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsVisitDetail;

/**
 * 访问详情的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsVisitDetailService
{
	/**
	 * 获取所有访问详情
	 * @return
	 */
	public List<CsVisitDetail> getCsVisitDetailList(Map params,Integer size);
	
	/**
	 * 获取访问详情统计
	 * @return
	 */
	public List getCsVisitDetailStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取访问详情总数
	 * @return
	 */	
	public Long getCsVisitDetailCount(Map params);
	
	
	/**
	 * 获取访问详情自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsVisitDetailEval(String eval,Map params);

	/**
	 * 获取访问详情分页
	 * @return
	 */	
	public Page getCsVisitDetailPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取访问详情
	 * @param params
	 * @return
	 */
	public CsVisitDetail getCsVisitDetail(Map params);

	/**
	 * 根据ID取访问详情
	 * @param id
	 * @return
	 */
	public CsVisitDetail getCsVisitDetailById(Long id);


	/**
	 * 保存访问详情
	 * @param csVisitDetail
	 */
	public CsVisitDetail saveCsVisitDetail(CsVisitDetail csVisitDetail);

	/**
	 * 更新访问详情
	 * @param csVisitDetail
	 */
	public void updateCsVisitDetail(CsVisitDetail csVisitDetail);
	/**
	 * 更新访问详情非空字段
	 * @param csVisitDetail
	 */
	public void updateCsVisitDetail$NotNull(CsVisitDetail csVisitDetail);

	/**
	 * 根据ID删除一个访问详情
	 * @param id
	 */
	public void deleteCsVisitDetailById(Long id);
	/**
	 * 根据ID逻辑删除一个访问详情
	 * @param id
	 */
	public void removeCsVisitDetailById(Long id);
	/**
	 * 根据条件更新访问详情
	 * @param values
	 * @param params
	 */
	public void updateCsVisitDetailByConfirm(Map values, Map params);
	/**
	 * 根据条件删除访问详情
	 * @param params
	 */
	public void deleteCsVisitDetailByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}