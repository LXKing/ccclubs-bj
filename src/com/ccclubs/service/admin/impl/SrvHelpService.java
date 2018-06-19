package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ISrvHelpDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvHelp;
import com.ccclubs.service.admin.ISrvHelpService;
import com.lazy3q.web.helper.$;

/**
 * 系统帮助的Service实现
 * @author 飞啊飘啊
 */
public class SrvHelpService implements ISrvHelpService
{
	ISrvHelpDao srvHelpDao;
	

	/**
	 * 获取所有系统帮助
	 * @return
	 */
	public List<SrvHelp> getSrvHelpList(Map params,Integer size)
	{
		return srvHelpDao.getSrvHelpList(params,size);
	}
	
	/**
	 * 获取系统帮助统计
	 * @return
	 */
	public List getSrvHelpStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return srvHelpDao.getSrvHelpStats(params,groups,sums);
	}
	
	/**
	 * 获取系统帮助总数
	 * @return
	 */
	public Long getSrvHelpCount(Map params)
	{
		return srvHelpDao.getSrvHelpCount(params);
	}
	
	/**
	 * 获取系统帮助自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getSrvHelpEval(String eval,Map params)
	{
		return srvHelpDao.getSrvHelpEval(eval,params);
	}

	/**
	 * 获取系统帮助分页
	 * @return
	 */
	public Page getSrvHelpPage(int page,int size,Map params)
	{
		return srvHelpDao.getSrvHelpPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取系统帮助
	 * @param params
	 * @return
	 */
	public SrvHelp getSrvHelp(Map params)
	{
		return srvHelpDao.getSrvHelp(params);
	}

	/**
	 * 根据ID取系统帮助
	 * @param id
	 * @return
	 */
	public SrvHelp getSrvHelpById(Long id)
	{
		return srvHelpDao.getSrvHelpById(id);
	}

	/**
	 * 保存系统帮助
	 * @param srvHelp
	 */
	public SrvHelp saveSrvHelp(SrvHelp srvHelp)
	{
		return srvHelpDao.saveSrvHelp(srvHelp);
	}
	
	/**
	 * 更新系统帮助
	 * @param srvHelp
	 */
	public void updateSrvHelp(SrvHelp srvHelp)
	{
		srvHelpDao.updateSrvHelp(srvHelp);
	}
	/**
	 * 更新系统帮助非空字段
	 * @param srvHelp
	 */
	public void updateSrvHelp$NotNull(SrvHelp srvHelp)
	{
		srvHelpDao.updateSrvHelp$NotNull(srvHelp);
	}
	
	/**
	 * 根据ID删除一个系统帮助
	 * @param id
	 */
	public void deleteSrvHelpById(Long id)
	{
		srvHelpDao.deleteSrvHelpById(id);
		List<SrvHelp> list = srvHelpDao.getSrvHelpList($.Map().add("shParent", id),-1);
		while(!list.isEmpty()){
			String strIds="";
			for(SrvHelp srvHelp:list){
				if(!strIds.equals(""))
					strIds+=",";
				strIds+=srvHelp.getShId();
			}
			srvHelpDao.deleteSrvHelpByConfirm($.Map().add("definex", "sh_id in ("+strIds+")").add("confirm",1));
			list=srvHelpDao.getSrvHelpList($.Map().add("definex", "sh_parent in ("+strIds+")"),-1);
		}
	}
	
	/**
	 * 根据条件更新系统帮助
	 * @param values
	 * @param params
	 */
	public void updateSrvHelpByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateSrvHelpBy时无条件");
		srvHelpDao.updateSrvHelpByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除系统帮助
	 * @param params
	 */
	public void deleteSrvHelpByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteSrvHelpBy时无条件");
		srvHelpDao.deleteSrvHelpByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ISrvHelpDao getSrvHelpDao()
	{
		return srvHelpDao;
	}

	public void setSrvHelpDao(ISrvHelpDao srvHelpDao)
	{
		this.srvHelpDao = srvHelpDao;
	}
}