package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsChatMessage;

/**
 * 客服消息的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsChatMessageDao
{
	/**
	 * 获取所有客服消息
	 * @return
	 */
	public List<CsChatMessage> getCsChatMessageList(Map params,Integer size);
	
	/**
	 * 获取客服消息统计
	 * @return
	 */
	public List getCsChatMessageStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取客服消息总数
	 * @return
	 */	
	public Long getCsChatMessageCount(Map params);
	
	/**
	 * 获取客服消息自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsChatMessageEval(String eval,Map params);
	
	/**
	 * 获取客服消息分页
	 * @return
	 */	
	public Page getCsChatMessagePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取客服消息
	 * @param params
	 * @return
	 */
	public CsChatMessage getCsChatMessage(Map params);	

	/**
	 * 根据ID取客服消息
	 * @param id
	 * @return
	 */
	public CsChatMessage getCsChatMessageById(Long id);


	
	

	/**
	 * 保存客服消息
	 * @param csChatMessage
	 */
	public CsChatMessage saveCsChatMessage(CsChatMessage csChatMessage);

	/**
	 * 更新客服消息
	 * @param csChatMessage
	 */
	public void updateCsChatMessage(CsChatMessage csChatMessage);
	/**
	 * 更新客服消息非空字段
	 * @param csChatMessage
	 */
	public void updateCsChatMessage$NotNull(CsChatMessage csChatMessage);

	/**
	 * 根据ID删除一个客服消息
	 * @param id
	 */
	public void deleteCsChatMessageById(Long id);
	/**
	 * 根据ID逻辑删除一个客服消息
	 * @param id
	 */
	public void removeCsChatMessageById(Long id);
	/**
	 * 根据条件更新客服消息
	 * @param values
	 * @param params
	 */
	public void updateCsChatMessageByConfirm(Map values, Map params);
	/**
	 * 根据条件删除客服消息
	 * @param params
	 */
	public void deleteCsChatMessageByConfirm(Map params);

}