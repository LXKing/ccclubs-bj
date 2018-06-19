package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsNotice;

/**
 * 系统通知的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsNoticeService
{
	/**
	 * 获取所有系统通知
	 * @return
	 */
	public List<CsNotice> getCsNoticeList(Map params,Integer size);
	
	/**
	 * 获取系统通知统计
	 * @return
	 */
	public List getCsNoticeStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取系统通知总数
	 * @return
	 */	
	public Long getCsNoticeCount(Map params);
	
	
	/**
	 * 获取系统通知自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsNoticeEval(String eval,Map params);

	/**
	 * 获取系统通知分页
	 * @return
	 */	
	public Page getCsNoticePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取系统通知
	 * @param params
	 * @return
	 */
	public CsNotice getCsNotice(Map params);

	/**
	 * 根据ID取系统通知
	 * @param id
	 * @return
	 */
	public CsNotice getCsNoticeById(Long id);


	/**
	 * 保存系统通知
	 * @param csNotice
	 */
	public CsNotice saveCsNotice(CsNotice csNotice);

	/**
	 * 更新系统通知
	 * @param csNotice
	 */
	public void updateCsNotice(CsNotice csNotice);
	/**
	 * 更新系统通知非空字段
	 * @param csNotice
	 */
	public void updateCsNotice$NotNull(CsNotice csNotice);

	/**
	 * 根据ID删除一个系统通知
	 * @param id
	 */
	public void deleteCsNoticeById(Long id);
	/**
	 * 根据ID逻辑删除一个系统通知
	 * @param id
	 */
	public void removeCsNoticeById(Long id);
	/**
	 * 根据条件更新系统通知
	 * @param values
	 * @param params
	 */
	public void updateCsNoticeByConfirm(Map values, Map params);
	/**
	 * 根据条件删除系统通知
	 * @param params
	 */
	public void deleteCsNoticeByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}