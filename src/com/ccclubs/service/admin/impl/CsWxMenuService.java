package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsWxMenuDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsWxMenu;
import com.ccclubs.service.admin.ICsWxMenuService;
import com.lazy3q.web.helper.$;

/**
 * 微信菜单的Service实现
 * @author 飞啊飘啊
 */
public class CsWxMenuService implements ICsWxMenuService
{
	ICsWxMenuDao csWxMenuDao;
	

	/**
	 * 获取所有微信菜单
	 * @return
	 */
	public List<CsWxMenu> getCsWxMenuList(Map params,Integer size)
	{
		return csWxMenuDao.getCsWxMenuList(params,size);
	}
	
	/**
	 * 获取微信菜单统计
	 * @return
	 */
	public List getCsWxMenuStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csWxMenuDao.getCsWxMenuStats(params,groups,sums);
	}
	
	/**
	 * 获取微信菜单总数
	 * @return
	 */
	public Long getCsWxMenuCount(Map params)
	{
		return csWxMenuDao.getCsWxMenuCount(params);
	}
	
	/**
	 * 获取微信菜单自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsWxMenuEval(String eval,Map params)
	{
		return csWxMenuDao.getCsWxMenuEval(eval,params);
	}

	/**
	 * 获取微信菜单分页
	 * @return
	 */
	public Page getCsWxMenuPage(int page,int size,Map params)
	{
		return csWxMenuDao.getCsWxMenuPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取微信菜单
	 * @param params
	 * @return
	 */
	public CsWxMenu getCsWxMenu(Map params)
	{
		return csWxMenuDao.getCsWxMenu(params);
	}

	/**
	 * 根据ID取微信菜单
	 * @param id
	 * @return
	 */
	public CsWxMenu getCsWxMenuById(Long id)
	{
		return csWxMenuDao.getCsWxMenuById(id);
	}

	/**
	 * 保存微信菜单
	 * @param csWxMenu
	 */
	public CsWxMenu saveCsWxMenu(CsWxMenu csWxMenu)
	{
		return csWxMenuDao.saveCsWxMenu(csWxMenu);
	}
	
	/**
	 * 更新微信菜单
	 * @param csWxMenu
	 */
	public void updateCsWxMenu(CsWxMenu csWxMenu)
	{
		csWxMenuDao.updateCsWxMenu(csWxMenu);
	}
	/**
	 * 更新微信菜单非空字段
	 * @param csWxMenu
	 */
	public void updateCsWxMenu$NotNull(CsWxMenu csWxMenu)
	{
		csWxMenuDao.updateCsWxMenu$NotNull(csWxMenu);
	}
	
	/**
	 * 根据ID删除一个微信菜单
	 * @param id
	 */
	public void deleteCsWxMenuById(Long id)
	{
		csWxMenuDao.deleteCsWxMenuById(id);
		List<CsWxMenu> list = csWxMenuDao.getCsWxMenuList($.Map().add("cswmParent", id),-1);
		while(!list.isEmpty()){
			String strIds="";
			for(CsWxMenu csWxMenu:list){
				if(!strIds.equals(""))
					strIds+=",";
				strIds+=csWxMenu.getCswmId();
			}
			csWxMenuDao.deleteCsWxMenuByConfirm($.Map().add("definex", "cswm_id in ("+strIds+")").add("confirm",1));
			list=csWxMenuDao.getCsWxMenuList($.Map().add("definex", "cswm_parent in ("+strIds+")"),-1);
		}
	}
	/**
	 * 根据ID逻辑删除一个微信菜单
	 * @param id
	 */
	public void removeCsWxMenuById(Long id)
	{
		csWxMenuDao.removeCsWxMenuById(id);
		List<CsWxMenu> list = csWxMenuDao.getCsWxMenuList($.Map().add("cswmParent", id),-1);
		while(!list.isEmpty()){
			String strIds="";
			for(CsWxMenu csWxMenu:list){
				if(!strIds.equals(""))
					strIds+=",";
				strIds+=csWxMenu.getCswmId();
			}
			csWxMenuDao.updateCsWxMenuByConfirm($.Map().add("cswmStatus", "-1"),$.Map().add("definex", "cswm_id in ("+strIds+")").add("confirm",1));
			list=csWxMenuDao.getCsWxMenuList($.Map().add("definex", "cswm_parent in ("+strIds+")"),-1);
		}
	}
	
	/**
	 * 根据条件更新微信菜单
	 * @param values
	 * @param params
	 */
	public void updateCsWxMenuByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsWxMenuBy时无条件");
		csWxMenuDao.updateCsWxMenuByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除微信菜单
	 * @param params
	 */
	public void deleteCsWxMenuByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsWxMenuBy时无条件");
		csWxMenuDao.deleteCsWxMenuByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsWxMenuDao getCsWxMenuDao()
	{
		return csWxMenuDao;
	}

	public void setCsWxMenuDao(ICsWxMenuDao csWxMenuDao)
	{
		this.csWxMenuDao = csWxMenuDao;
	}
}