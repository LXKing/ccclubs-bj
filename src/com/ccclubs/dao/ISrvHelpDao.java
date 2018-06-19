package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvHelp;

/**
 * 系统帮助的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ISrvHelpDao
{
	/**
	 * 获取所有系统帮助
	 * @return
	 */
	public List<SrvHelp> getSrvHelpList(Map params,Integer size);
	
	/**
	 * 获取系统帮助统计
	 * @return
	 */
	public List getSrvHelpStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取系统帮助总数
	 * @return
	 */	
	public Long getSrvHelpCount(Map params);
	
	/**
	 * 获取系统帮助自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getSrvHelpEval(String eval,Map params);
	
	/**
	 * 获取系统帮助分页
	 * @return
	 */	
	public Page getSrvHelpPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取系统帮助
	 * @param params
	 * @return
	 */
	public SrvHelp getSrvHelp(Map params);	

	/**
	 * 根据ID取系统帮助
	 * @param id
	 * @return
	 */
	public SrvHelp getSrvHelpById(Long id);


	
	

	/**
	 * 保存系统帮助
	 * @param srvHelp
	 */
	public SrvHelp saveSrvHelp(SrvHelp srvHelp);

	/**
	 * 更新系统帮助
	 * @param srvHelp
	 */
	public void updateSrvHelp(SrvHelp srvHelp);
	/**
	 * 更新系统帮助非空字段
	 * @param srvHelp
	 */
	public void updateSrvHelp$NotNull(SrvHelp srvHelp);

	/**
	 * 根据ID删除一个系统帮助
	 * @param id
	 */
	public void deleteSrvHelpById(Long id);
	/**
	 * 根据条件更新系统帮助
	 * @param values
	 * @param params
	 */
	public void updateSrvHelpByConfirm(Map values, Map params);
	/**
	 * 根据条件删除系统帮助
	 * @param params
	 */
	public void deleteSrvHelpByConfirm(Map params);

}