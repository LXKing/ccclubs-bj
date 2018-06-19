package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.TbPassCheck;

/**
 * 上下班签到的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ITbPassCheckService
{
	/**
	 * 获取所有上下班签到
	 * @return
	 */
	public List<TbPassCheck> getTbPassCheckList(Map params,Integer size);
	
	/**
	 * 获取上下班签到统计
	 * @return
	 */
	public List getTbPassCheckStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取上下班签到总数
	 * @return
	 */	
	public Long getTbPassCheckCount(Map params);
	
	
	/**
	 * 获取上下班签到自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getTbPassCheckEval(String eval,Map params);

	/**
	 * 获取上下班签到分页
	 * @return
	 */	
	public Page getTbPassCheckPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取上下班签到
	 * @param params
	 * @return
	 */
	public TbPassCheck getTbPassCheck(Map params);

	/**
	 * 根据ID取上下班签到
	 * @param id
	 * @return
	 */
	public TbPassCheck getTbPassCheckById(Long id);


	/**
	 * 保存上下班签到
	 * @param tbPassCheck
	 */
	public TbPassCheck saveTbPassCheck(TbPassCheck tbPassCheck);

	/**
	 * 更新上下班签到
	 * @param tbPassCheck
	 */
	public void updateTbPassCheck(TbPassCheck tbPassCheck);
	/**
	 * 更新上下班签到非空字段
	 * @param tbPassCheck
	 */
	public void updateTbPassCheck$NotNull(TbPassCheck tbPassCheck);

	/**
	 * 根据ID删除一个上下班签到
	 * @param id
	 */
	public void deleteTbPassCheckById(Long id);
	/**
	 * 根据ID逻辑删除一个上下班签到
	 * @param id
	 */
	public void removeTbPassCheckById(Long id);
	/**
	 * 根据条件更新上下班签到
	 * @param values
	 * @param params
	 */
	public void updateTbPassCheckByConfirm(Map values, Map params);
	/**
	 * 根据条件删除上下班签到
	 * @param params
	 */
	public void deleteTbPassCheckByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}