package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsBug;

/**
 * 系统BUG的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsBugService
{
	/**
	 * 获取所有系统BUG
	 * @return
	 */
	public List<CsBug> getCsBugList(Map params,Integer size);
	
	/**
	 * 获取系统BUG统计
	 * @return
	 */
	public List getCsBugStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取系统BUG总数
	 * @return
	 */	
	public Long getCsBugCount(Map params);
	
	
	/**
	 * 获取系统BUG自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsBugEval(String eval,Map params);

	/**
	 * 获取系统BUG分页
	 * @return
	 */	
	public Page getCsBugPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取系统BUG
	 * @param params
	 * @return
	 */
	public CsBug getCsBug(Map params);

	/**
	 * 根据ID取系统BUG
	 * @param id
	 * @return
	 */
	public CsBug getCsBugById(Long id);


	/**
	 * 保存系统BUG
	 * @param csBug
	 */
	public CsBug saveCsBug(CsBug csBug);

	/**
	 * 更新系统BUG
	 * @param csBug
	 */
	public void updateCsBug(CsBug csBug);
	/**
	 * 更新系统BUG非空字段
	 * @param csBug
	 */
	public void updateCsBug$NotNull(CsBug csBug);

	/**
	 * 根据ID删除一个系统BUG
	 * @param id
	 */
	public void deleteCsBugById(Long id);
	/**
	 * 根据ID逻辑删除一个系统BUG
	 * @param id
	 */
	public void removeCsBugById(Long id);
	/**
	 * 根据条件更新系统BUG
	 * @param values
	 * @param params
	 */
	public void updateCsBugByConfirm(Map values, Map params);
	/**
	 * 根据条件删除系统BUG
	 * @param params
	 */
	public void deleteCsBugByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}