package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsCartItemDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCartItem;
import com.lazy3q.web.helper.$;

/**
 * 购物车项的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsCartItemDao extends SqlMapClientDaoSupport implements ICsCartItemDao
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
	private CsCartItem custom(CsCartItem params){
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
	 * 获取所有购物车项
	 * @return
	 */	
	public List<CsCartItem> getCsCartItemList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsCartItem> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsCartItemList", params);
		
		return list;
	}
	
	
	/**
	 * 获取购物车项统计
	 * @return
	 */
	public List getCsCartItemStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsCartItemStats", params);
	}

	/**
	 * 获取购物车项分页
	 * @return
	 */
	public Page getCsCartItemPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsCartItemCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsCartItemPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取购物车项数量
	 * @return
	 */	
	public Long getCsCartItemCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCartItemEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取购物车项自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsCartItemEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCartItemEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取购物车项
	 * @param params
	 * @return
	 */
	public CsCartItem getCsCartItem(Map params)
	{		
		params = custom(params);
			
		List<CsCartItem> list=this.getCsCartItemList(params,1);
		CsCartItem csCartItem = list.isEmpty()?null:list.get(0);
		
		return csCartItem;
	}
		
	/**
	 * 根据ID取购物车项
	 * @param id
	 * @return
	 */
	public CsCartItem getCsCartItemById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsCartItem csCartItem = null;
		if(csCartItem!=null)
			return csCartItem;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csCartItem = (CsCartItem) this.getSqlMapClientTemplate().queryForObject("getCsCartItemById",params);
		if(csCartItem!=null)
			MemCache.setValue(CsCartItem.class,id, csCartItem.getKeyValue());
		return csCartItem;
	}
	
	/**
	 * 根据ID取购物车项(不缓存)
	 * @param id
	 * @return
	 */
	public CsCartItem GetCsCartItemById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsCartItem) this.getSqlMapClientTemplate().queryForObject("getCsCartItemById",params);
	}
	

	/**
	 * 保存购物车项
	 * @param csCartItem
	 */
	public CsCartItem saveCsCartItem(CsCartItem csCartItem)
	{
		csCartItem = custom(csCartItem);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsCartItem", csCartItem));
		csCartItem = this.GetCsCartItemById(id);
		MemCache.setValue(CsCartItem.class,id, csCartItem.getKeyValue());
		return Trigger.on(CsCartItem.class,Trigger.SAVE,csCartItem);	
	}

	

	/**
	 * 更新购物车项
	 * @param csCartItem
	 */
	public void updateCsCartItem(CsCartItem csCartItem)
	{
		csCartItem = custom(csCartItem);
		this.getSqlMapClientTemplate().update("updateCsCartItem", csCartItem);
		csCartItem = this.GetCsCartItemById(csCartItem.getCsciId());
		if(csCartItem!=null){
			MemCache.setValue(CsCartItem.class,csCartItem.getCsciId(), csCartItem.getKeyValue());
		}
		Trigger.on(CsCartItem.class,Trigger.UPDATE,csCartItem);
	}
	/**
	 * 更新购物车项非空字段
	 * @param csCartItem
	 */
	public void updateCsCartItem$NotNull(CsCartItem csCartItem)
	{
		csCartItem = custom(csCartItem);
		this.getSqlMapClientTemplate().update("updateCsCartItem$NotNull", csCartItem);
		csCartItem = this.GetCsCartItemById(csCartItem.getCsciId());
		if(csCartItem!=null){
			MemCache.setValue(CsCartItem.class,csCartItem.getCsciId(), csCartItem.getKeyValue());
		}
		Trigger.on(CsCartItem.class,Trigger.UPDATE,csCartItem);
	}

	/**
	 * 根据ID删除一个购物车项
	 * @param id
	 */
	public void deleteCsCartItemById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsCartItem.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsCartItemById", params);
		MemCache.deleteValue(CsCartItem.class, id);
	}
	/**
	 * 根据条件更新购物车项
	 * @param values
	 * @param params
	 */
	public void updateCsCartItemByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsCartItemCount(params)>255){
			MemCache.clearValue(CsCartItem.class);
		}else{
			params.put("eval","csci_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCartItemEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCartItem.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsCartItemBy", allParams);
		Trigger.on(CsCartItem.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除购物车项
	 * @param params
	 */
	public void deleteCsCartItemByConfirm(Map params){
		Trigger.on(CsCartItem.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsCartItemCount(params)>255){
			MemCache.clearValue(CsCartItem.class);
		}else{
			params.put("eval","csci_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCartItemEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCartItem.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsCartItemBy", params);
	}
	

}