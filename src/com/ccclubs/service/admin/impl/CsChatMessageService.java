package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsChatMessageDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsChatMessage;
import com.ccclubs.service.admin.ICsChatMessageService;
import com.lazy3q.web.helper.$;

/**
 * 客服消息的Service实现
 * @author 飞啊飘啊
 */
public class CsChatMessageService implements ICsChatMessageService
{
	ICsChatMessageDao csChatMessageDao;
	

	/**
	 * 获取所有客服消息
	 * @return
	 */
	public List<CsChatMessage> getCsChatMessageList(Map params,Integer size)
	{
		return csChatMessageDao.getCsChatMessageList(params,size);
	}
	
	/**
	 * 获取客服消息统计
	 * @return
	 */
	public List getCsChatMessageStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csChatMessageDao.getCsChatMessageStats(params,groups,sums);
	}
	
	/**
	 * 获取客服消息总数
	 * @return
	 */
	public Long getCsChatMessageCount(Map params)
	{
		return csChatMessageDao.getCsChatMessageCount(params);
	}
	
	/**
	 * 获取客服消息自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsChatMessageEval(String eval,Map params)
	{
		return csChatMessageDao.getCsChatMessageEval(eval,params);
	}

	/**
	 * 获取客服消息分页
	 * @return
	 */
	public Page getCsChatMessagePage(int page,int size,Map params)
	{
		return csChatMessageDao.getCsChatMessagePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取客服消息
	 * @param params
	 * @return
	 */
	public CsChatMessage getCsChatMessage(Map params)
	{
		return csChatMessageDao.getCsChatMessage(params);
	}

	/**
	 * 根据ID取客服消息
	 * @param id
	 * @return
	 */
	public CsChatMessage getCsChatMessageById(Long id)
	{
		return csChatMessageDao.getCsChatMessageById(id);
	}

	/**
	 * 保存客服消息
	 * @param csChatMessage
	 */
	public CsChatMessage saveCsChatMessage(CsChatMessage csChatMessage)
	{
		return csChatMessageDao.saveCsChatMessage(csChatMessage);
	}
	
	/**
	 * 更新客服消息
	 * @param csChatMessage
	 */
	public void updateCsChatMessage(CsChatMessage csChatMessage)
	{
		csChatMessageDao.updateCsChatMessage(csChatMessage);
	}
	/**
	 * 更新客服消息非空字段
	 * @param csChatMessage
	 */
	public void updateCsChatMessage$NotNull(CsChatMessage csChatMessage)
	{
		csChatMessageDao.updateCsChatMessage$NotNull(csChatMessage);
	}
	
	/**
	 * 根据ID删除一个客服消息
	 * @param id
	 */
	public void deleteCsChatMessageById(Long id)
	{
		csChatMessageDao.deleteCsChatMessageById(id);
	}
	/**
	 * 根据ID逻辑删除一个客服消息
	 * @param id
	 */
	public void removeCsChatMessageById(Long id)
	{
		csChatMessageDao.removeCsChatMessageById(id);
	}
	
	/**
	 * 根据条件更新客服消息
	 * @param values
	 * @param params
	 */
	public void updateCsChatMessageByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsChatMessageBy时无条件");
		csChatMessageDao.updateCsChatMessageByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除客服消息
	 * @param params
	 */
	public void deleteCsChatMessageByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsChatMessageBy时无条件");
		csChatMessageDao.deleteCsChatMessageByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsChatMessageDao getCsChatMessageDao()
	{
		return csChatMessageDao;
	}

	public void setCsChatMessageDao(ICsChatMessageDao csChatMessageDao)
	{
		this.csChatMessageDao = csChatMessageDao;
	}
}