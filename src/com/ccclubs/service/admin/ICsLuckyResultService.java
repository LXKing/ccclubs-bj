package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsLuckyResult;

/**
 * 中奖结果的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsLuckyResultService
{
	/**
	 * 获取所有中奖结果
	 * @return
	 */
	public List<CsLuckyResult> getCsLuckyResultList(Map params,Integer size);
	
	/**
	 * 获取中奖结果统计
	 * @return
	 */
	public List getCsLuckyResultStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取中奖结果总数
	 * @return
	 */	
	public Long getCsLuckyResultCount(Map params);
	
	
	/**
	 * 获取中奖结果自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsLuckyResultEval(String eval,Map params);

	/**
	 * 获取中奖结果分页
	 * @return
	 */	
	public Page getCsLuckyResultPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取中奖结果
	 * @param params
	 * @return
	 */
	public CsLuckyResult getCsLuckyResult(Map params);

	/**
	 * 根据ID取中奖结果
	 * @param id
	 * @return
	 */
	public CsLuckyResult getCsLuckyResultById(Long id);


	/**
	 * 保存中奖结果
	 * @param csLuckyResult
	 */
	public CsLuckyResult saveCsLuckyResult(CsLuckyResult csLuckyResult);

	/**
	 * 更新中奖结果
	 * @param csLuckyResult
	 */
	public void updateCsLuckyResult(CsLuckyResult csLuckyResult);
	/**
	 * 更新中奖结果非空字段
	 * @param csLuckyResult
	 */
	public void updateCsLuckyResult$NotNull(CsLuckyResult csLuckyResult);

	/**
	 * 根据ID删除一个中奖结果
	 * @param id
	 */
	public void deleteCsLuckyResultById(Long id);
	/**
	 * 根据ID逻辑删除一个中奖结果
	 * @param id
	 */
	public void removeCsLuckyResultById(Long id);
	/**
	 * 根据条件更新中奖结果
	 * @param values
	 * @param params
	 */
	public void updateCsLuckyResultByConfirm(Map values, Map params);
	/**
	 * 根据条件删除中奖结果
	 * @param params
	 */
	public void deleteCsLuckyResultByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}