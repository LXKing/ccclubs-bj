package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsMessage;

/**
 * 会员消息的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsMessageService
{
	/**
	 * 获取所有会员消息
	 * @return
	 */
	public List<CsMessage> getCsMessageList(Map params,Integer size);
	
	/**
	 * 获取会员消息统计
	 * @return
	 */
	public List getCsMessageStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取会员消息总数
	 * @return
	 */	
	public Long getCsMessageCount(Map params);
	
	
	/**
	 * 获取会员消息自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsMessageEval(String eval,Map params);

	/**
	 * 获取会员消息分页
	 * @return
	 */	
	public Page getCsMessagePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取会员消息
	 * @param params
	 * @return
	 */
	public CsMessage getCsMessage(Map params);

	/**
	 * 根据ID取会员消息
	 * @param id
	 * @return
	 */
	public CsMessage getCsMessageById(Long id);


	/**
	 * 保存会员消息
	 * @param csMessage
	 */
	public CsMessage saveCsMessage(CsMessage csMessage);

	/**
	 * 更新会员消息
	 * @param csMessage
	 */
	public void updateCsMessage(CsMessage csMessage);
	/**
	 * 更新会员消息非空字段
	 * @param csMessage
	 */
	public void updateCsMessage$NotNull(CsMessage csMessage);

	/**
	 * 根据ID删除一个会员消息
	 * @param id
	 */
	public void deleteCsMessageById(Long id);
	/**
	 * 根据ID逻辑删除一个会员消息
	 * @param id
	 */
	public void removeCsMessageById(Long id);
	/**
	 * 根据条件更新会员消息
	 * @param values
	 * @param params
	 */
	public void updateCsMessageByConfirm(Map values, Map params);
	/**
	 * 根据条件删除会员消息
	 * @param params
	 */
	public void deleteCsMessageByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}