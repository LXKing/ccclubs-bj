package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsWxMenu;

/**
 * 微信菜单的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsWxMenuDao
{
	/**
	 * 获取所有微信菜单
	 * @return
	 */
	public List<CsWxMenu> getCsWxMenuList(Map params,Integer size);
	
	/**
	 * 获取微信菜单统计
	 * @return
	 */
	public List getCsWxMenuStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取微信菜单总数
	 * @return
	 */	
	public Long getCsWxMenuCount(Map params);
	
	/**
	 * 获取微信菜单自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsWxMenuEval(String eval,Map params);
	
	/**
	 * 获取微信菜单分页
	 * @return
	 */	
	public Page getCsWxMenuPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取微信菜单
	 * @param params
	 * @return
	 */
	public CsWxMenu getCsWxMenu(Map params);	

	/**
	 * 根据ID取微信菜单
	 * @param id
	 * @return
	 */
	public CsWxMenu getCsWxMenuById(Long id);


	
	

	/**
	 * 保存微信菜单
	 * @param csWxMenu
	 */
	public CsWxMenu saveCsWxMenu(CsWxMenu csWxMenu);

	/**
	 * 更新微信菜单
	 * @param csWxMenu
	 */
	public void updateCsWxMenu(CsWxMenu csWxMenu);
	/**
	 * 更新微信菜单非空字段
	 * @param csWxMenu
	 */
	public void updateCsWxMenu$NotNull(CsWxMenu csWxMenu);

	/**
	 * 根据ID删除一个微信菜单
	 * @param id
	 */
	public void deleteCsWxMenuById(Long id);
	/**
	 * 根据ID逻辑删除一个微信菜单
	 * @param id
	 */
	public void removeCsWxMenuById(Long id);
	/**
	 * 根据条件更新微信菜单
	 * @param values
	 * @param params
	 */
	public void updateCsWxMenuByConfirm(Map values, Map params);
	/**
	 * 根据条件删除微信菜单
	 * @param params
	 */
	public void deleteCsWxMenuByConfirm(Map params);

}