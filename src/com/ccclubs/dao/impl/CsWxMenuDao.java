package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsWxMenuDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsWxMenu;
import com.lazy3q.web.helper.$;

/**
 * 微信菜单的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsWxMenuDao extends SqlMapClientDaoSupport implements ICsWxMenuDao
{
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/


	/**
	 * 自定义加工处理params,可以添加表别名、联表查询等操作
	 * 可在此动态设置数据源，以达到分库分表的目的
	 * @param params
	 * @return
	 */
	private Map custom(Map params){
		/************LAZY3Q_DAO_CUSTOM************/
		/************LAZY3Q_DAO_CUSTOM************/

		return params;
	}
	
	/**
	 * 自定义加工处理params,可以添加表别名、联表查询等操作
	 * 可在此动态设置数据源，以达到分库分表的目的
	 * @param params
	 * @return
	 */
	private CsWxMenu custom(CsWxMenu params){
		/************LAZY3Q_CUSTOM_MODEL************/
		/************LAZY3Q_CUSTOM_MODEL************/

		return params;
	}
	/************LAZY3Q_DEFINE_FUNCTION************/
	/************LAZY3Q_DEFINE_FUNCTION************/

	
	/**
	 * 为某些需要加like的key加%
	 * @param params
	 * @return
	 */
	private Map likeKey(Map params){
		//为模糊匹配的varchar类型字段加上%号
		String[] likes={
			"cswmSupport"			
		};
		for(String key:likes){
			String value=(String) params.get(key);
			if(value==null || value.trim().equals(""))
				params.remove(key);
			else
				params.put(key, "%"+value.replaceAll("%", "")+"%");
		}
		return params;
	}
	

	/**
	 * 获取所有微信菜单
	 * @return
	 */	
	public List<CsWxMenu> getCsWxMenuList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsWxMenu> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject","cswmTime","cswmTimeStart","cswmTimeEnd"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(CsWxMenu.M.class, md5);
			List caches = MemCache.getObject(CsWxMenu.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						CsWxMenu cacheObject = MemCache.getObject(CsWxMenu.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(CsWxMenu.M.class, md5);
							MemCache.deleteValue(CsWxMenu.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(CsWxMenu.M.class, md5);
					MemCache.deleteValue(CsWxMenu.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsWxMenuList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(CsWxMenu csWxMenu:list){
				caches.add(csWxMenu.getCswmId());
				MemCache.setObject(CsWxMenu.class,csWxMenu.getCswmId(), csWxMenu);
			}
			MemCache.setObject(CsWxMenu.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(CsWxMenu.M.class, md5, json, MemCache.defaultSeconds());
		}		
		
		return list;
	}
	
	
	/**
	 * 获取微信菜单统计
	 * @return
	 */
	public List getCsWxMenuStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		params = custom(params);
		params=likeKey(params);
		
		String groupSql="";
		for(Map.Entry<String, Object> item : groups.entrySet())
			if(!$.empty(item.getKey()))
				groupSql+=(groupSql.equals("")?"":",")+item.getValue().toString().replace("{key}",item.getKey());
		if(groups.get("definex")!=null)
			groupSql+=(groupSql.equals("")?"":",")+groups.get("definex");
		if(!groupSql.equals(""))
			groupSql = "group by "+groupSql;
		if(params.get("desc")!=null)
			groupSql+=" order by {key} desc ".replace("{key}",params.get("desc").toString());
		else if(params.get("asc")!=null)
			groupSql+=" order by {key} asc ".replace("{key}",params.get("asc").toString());
		if(!groupSql.equals(""))
			groupSql+=" limit 512 ";
			
		String selectSql="count(*) as 'count'";
		for(Map.Entry<String, Object> item : sums.entrySet())
			if(!$.empty(item.getKey()))
				selectSql+=(selectSql.equals("")?"":",")+item.getValue().toString().replace("{key}",item.getKey())+(item.getValue().toString().indexOf(" as ")==-1 ? (" as "+item.getKey()) : "");
		for(Map.Entry<String, Object> item : groups.entrySet())
			if(!$.empty(item.getKey()))
				selectSql+=(selectSql.equals("")?"":",")+item.getKey()+" as '"+item.getKey()+"'";
		if(sums.get("definex")!=null)
			selectSql+=(selectSql.equals("")?"":",")+sums.get("definex");
		
		params.put("select",selectSql);
		params.put("group",groupSql);
		return this.getSqlMapClientTemplate().queryForList("getCsWxMenuStats", params);
	}

	/**
	 * 获取微信菜单分页
	 * @return
	 */
	public Page getCsWxMenuPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsWxMenuCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsWxMenuPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取微信菜单数量
	 * @return
	 */	
	public Long getCsWxMenuCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsWxMenuEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取微信菜单自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsWxMenuEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsWxMenuEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取微信菜单
	 * @param params
	 * @return
	 */
	public CsWxMenu getCsWxMenu(Map params)
	{		
		params = custom(params);
			
		List<CsWxMenu> list=this.getCsWxMenuList(params,1);
		CsWxMenu csWxMenu = list.isEmpty()?null:list.get(0);
		
		return csWxMenu;
	}
		
	/**
	 * 根据ID取微信菜单
	 * @param id
	 * @return
	 */
	public CsWxMenu getCsWxMenuById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsWxMenu csWxMenu = MemCache.getObject(CsWxMenu.class, id);
		if(csWxMenu!=null)
			return csWxMenu;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csWxMenu = (CsWxMenu) this.getSqlMapClientTemplate().queryForObject("getCsWxMenuById",params);
		if(csWxMenu!=null)
			MemCache.setValue(CsWxMenu.class,id, csWxMenu.getKeyValue());
		if(csWxMenu!=null)
			MemCache.setObject(CsWxMenu.class,id, csWxMenu);
		return csWxMenu;
	}
	
	/**
	 * 根据ID取微信菜单(不缓存)
	 * @param id
	 * @return
	 */
	public CsWxMenu GetCsWxMenuById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsWxMenu) this.getSqlMapClientTemplate().queryForObject("getCsWxMenuById",params);
	}
	

	/**
	 * 保存微信菜单
	 * @param csWxMenu
	 */
	public CsWxMenu saveCsWxMenu(CsWxMenu csWxMenu)
	{
		csWxMenu = custom(csWxMenu);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsWxMenu", csWxMenu));
		csWxMenu = this.GetCsWxMenuById(id);
		MemCache.setValue(CsWxMenu.class,id, csWxMenu.getKeyValue());
		MemCache.setObject(CsWxMenu.class,id,csWxMenu);
		MemCache.clearObject(CsWxMenu.M.class);
		MemCache.clearValue(CsWxMenu.M.class);
		return Trigger.on(CsWxMenu.class,Trigger.SAVE,csWxMenu);	
	}

	

	/**
	 * 更新微信菜单
	 * @param csWxMenu
	 */
	public void updateCsWxMenu(CsWxMenu csWxMenu)
	{
		csWxMenu = custom(csWxMenu);
		this.getSqlMapClientTemplate().update("updateCsWxMenu", csWxMenu);
		csWxMenu = this.GetCsWxMenuById(csWxMenu.getCswmId());
		if(csWxMenu!=null){
			MemCache.setValue(CsWxMenu.class,csWxMenu.getCswmId(), csWxMenu.getKeyValue());
			MemCache.setObject(CsWxMenu.class,csWxMenu.getCswmId(),csWxMenu);
		}
		MemCache.clearObject(CsWxMenu.M.class);
		MemCache.clearValue(CsWxMenu.M.class);
		Trigger.on(CsWxMenu.class,Trigger.UPDATE,csWxMenu);
	}
	/**
	 * 更新微信菜单非空字段
	 * @param csWxMenu
	 */
	public void updateCsWxMenu$NotNull(CsWxMenu csWxMenu)
	{
		csWxMenu = custom(csWxMenu);
		this.getSqlMapClientTemplate().update("updateCsWxMenu$NotNull", csWxMenu);
		csWxMenu = this.GetCsWxMenuById(csWxMenu.getCswmId());
		if(csWxMenu!=null){
			MemCache.setValue(CsWxMenu.class,csWxMenu.getCswmId(), csWxMenu.getKeyValue());
			MemCache.setObject(CsWxMenu.class,csWxMenu.getCswmId(),csWxMenu);
		}
		MemCache.clearObject(CsWxMenu.M.class);
		MemCache.clearValue(CsWxMenu.M.class);
		Trigger.on(CsWxMenu.class,Trigger.UPDATE,csWxMenu);
	}

	/**
	 * 根据ID删除一个微信菜单
	 * @param id
	 */
	public void deleteCsWxMenuById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsWxMenu.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsWxMenuById", params);
		MemCache.deleteValue(CsWxMenu.class, id);
		MemCache.deleteObject(CsWxMenu.class, id);
		MemCache.clearObject(CsWxMenu.M.class);
		MemCache.clearValue(CsWxMenu.M.class);
	}
	/**
	 * 根据ID逻辑删除微信菜单 
	 * @param id
	 * @return
	 */
	public void removeCsWxMenuById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsWxMenu.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsWxMenuById", params);
		MemCache.deleteValue(CsWxMenu.class, id);
		MemCache.deleteObject(CsWxMenu.class, id);
		MemCache.clearObject(CsWxMenu.M.class);
		MemCache.clearValue(CsWxMenu.M.class);
	}
	/**
	 * 根据条件更新微信菜单
	 * @param values
	 * @param params
	 */
	public void updateCsWxMenuByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsWxMenuCount(params)>255){
			MemCache.clearValue(CsWxMenu.class);
			MemCache.clearObject(CsWxMenu.class);
		}else{
			params.put("eval","cswm_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsWxMenuEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsWxMenu.class, map.get("eval"));
				MemCache.deleteObject(CsWxMenu.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsWxMenu.M.class);
		MemCache.clearValue(CsWxMenu.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateCsWxMenuBy", allParams);
		Trigger.on(CsWxMenu.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除微信菜单
	 * @param params
	 */
	public void deleteCsWxMenuByConfirm(Map params){
		Trigger.on(CsWxMenu.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsWxMenuCount(params)>255){
			MemCache.clearValue(CsWxMenu.class);
			MemCache.clearObject(CsWxMenu.class);
		}else{
			params.put("eval","cswm_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsWxMenuEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsWxMenu.class, map.get("eval"));
				MemCache.deleteObject(CsWxMenu.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsWxMenu.M.class);
		MemCache.clearValue(CsWxMenu.M.class);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsWxMenuBy", params);
	}
	

}