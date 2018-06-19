package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsMessageDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsMessage;
import com.ccclubs.service.admin.ICsMessageService;
import com.lazy3q.web.helper.$;

/**
 * 会员消息的Service实现
 * @author 飞啊飘啊
 */
public class CsMessageService implements ICsMessageService
{
	ICsMessageDao csMessageDao;
	

	/**
	 * 获取所有会员消息
	 * @return
	 */
	public List<CsMessage> getCsMessageList(Map params,Integer size)
	{
		return csMessageDao.getCsMessageList(params,size);
	}
	
	/**
	 * 获取会员消息统计
	 * @return
	 */
	public List getCsMessageStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csMessageDao.getCsMessageStats(params,groups,sums);
	}
	
	/**
	 * 获取会员消息总数
	 * @return
	 */
	public Long getCsMessageCount(Map params)
	{
		return csMessageDao.getCsMessageCount(params);
	}
	
	/**
	 * 获取会员消息自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsMessageEval(String eval,Map params)
	{
		return csMessageDao.getCsMessageEval(eval,params);
	}

	/**
	 * 获取会员消息分页
	 * @return
	 */
	public Page getCsMessagePage(int page,int size,Map params)
	{
		return csMessageDao.getCsMessagePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取会员消息
	 * @param params
	 * @return
	 */
	public CsMessage getCsMessage(Map params)
	{
		return csMessageDao.getCsMessage(params);
	}

	/**
	 * 根据ID取会员消息
	 * @param id
	 * @return
	 */
	public CsMessage getCsMessageById(Long id)
	{
		return csMessageDao.getCsMessageById(id);
	}

	/**
	 * 保存会员消息
	 * @param csMessage
	 */
	public CsMessage saveCsMessage(CsMessage csMessage)
	{
		return csMessageDao.saveCsMessage(csMessage);
	}
	
	/**
	 * 更新会员消息
	 * @param csMessage
	 */
	public void updateCsMessage(CsMessage csMessage)
	{
		csMessageDao.updateCsMessage(csMessage);
	}
	/**
	 * 更新会员消息非空字段
	 * @param csMessage
	 */
	public void updateCsMessage$NotNull(CsMessage csMessage)
	{
		csMessageDao.updateCsMessage$NotNull(csMessage);
	}
	
	/**
	 * 根据ID删除一个会员消息
	 * @param id
	 */
	public void deleteCsMessageById(Long id)
	{
		csMessageDao.deleteCsMessageById(id);
	}
	/**
	 * 根据ID逻辑删除一个会员消息
	 * @param id
	 */
	public void removeCsMessageById(Long id)
	{
		csMessageDao.removeCsMessageById(id);
	}
	
	/**
	 * 根据条件更新会员消息
	 * @param values
	 * @param params
	 */
	public void updateCsMessageByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsMessageBy时无条件");
		csMessageDao.updateCsMessageByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除会员消息
	 * @param params
	 */
	public void deleteCsMessageByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsMessageBy时无条件");
		csMessageDao.deleteCsMessageByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsMessageDao getCsMessageDao()
	{
		return csMessageDao;
	}

	public void setCsMessageDao(ICsMessageDao csMessageDao)
	{
		this.csMessageDao = csMessageDao;
	}
}