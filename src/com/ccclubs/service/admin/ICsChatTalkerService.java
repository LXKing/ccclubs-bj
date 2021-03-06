package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsChatTalker;

/**
 * 在线访客的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsChatTalkerService
{
	/**
	 * 获取所有在线访客
	 * @return
	 */
	public List<CsChatTalker> getCsChatTalkerList(Map params,Integer size);
	
	/**
	 * 获取在线访客统计
	 * @return
	 */
	public List getCsChatTalkerStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取在线访客总数
	 * @return
	 */	
	public Long getCsChatTalkerCount(Map params);
	
	
	/**
	 * 获取在线访客自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsChatTalkerEval(String eval,Map params);

	/**
	 * 获取在线访客分页
	 * @return
	 */	
	public Page getCsChatTalkerPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取在线访客
	 * @param params
	 * @return
	 */
	public CsChatTalker getCsChatTalker(Map params);

	/**
	 * 根据ID取在线访客
	 * @param id
	 * @return
	 */
	public CsChatTalker getCsChatTalkerById(Long id);


	/**
	 * 保存在线访客
	 * @param csChatTalker
	 */
	public CsChatTalker saveCsChatTalker(CsChatTalker csChatTalker);

	/**
	 * 更新在线访客
	 * @param csChatTalker
	 */
	public void updateCsChatTalker(CsChatTalker csChatTalker);
	/**
	 * 更新在线访客非空字段
	 * @param csChatTalker
	 */
	public void updateCsChatTalker$NotNull(CsChatTalker csChatTalker);

	/**
	 * 根据ID删除一个在线访客
	 * @param id
	 */
	public void deleteCsChatTalkerById(Long id);
	/**
	 * 根据ID逻辑删除一个在线访客
	 * @param id
	 */
	public void removeCsChatTalkerById(Long id);
	/**
	 * 根据条件更新在线访客
	 * @param values
	 * @param params
	 */
	public void updateCsChatTalkerByConfirm(Map values, Map params);
	/**
	 * 根据条件删除在线访客
	 * @param params
	 */
	public void deleteCsChatTalkerByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}