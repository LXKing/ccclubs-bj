package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsItemDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsItem;
import com.lazy3q.web.helper.$;

/**
 * 商品信息的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsItemDao extends SqlMapClientDaoSupport implements ICsItemDao
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
	private CsItem custom(CsItem params){
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
			"csiTitle"			
			,"csiDepict"			
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
	 * 获取所有商品信息
	 * @return
	 */	
	public List<CsItem> getCsItemList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsItem> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsItemList", params);
		
		return list;
	}
	
	
	/**
	 * 获取商品信息统计
	 * @return
	 */
	public List getCsItemStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsItemStats", params);
	}

	/**
	 * 获取商品信息分页
	 * @return
	 */
	public Page getCsItemPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsItemCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsItemPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取商品信息数量
	 * @return
	 */	
	public Long getCsItemCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsItemEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取商品信息自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsItemEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsItemEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取商品信息
	 * @param params
	 * @return
	 */
	public CsItem getCsItem(Map params)
	{		
		params = custom(params);
			
		List<CsItem> list=this.getCsItemList(params,1);
		CsItem csItem = list.isEmpty()?null:list.get(0);
		
		return csItem;
	}
		
	/**
	 * 根据ID取商品信息
	 * @param id
	 * @return
	 */
	public CsItem getCsItemById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsItem csItem = null;
		if(csItem!=null)
			return csItem;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csItem = (CsItem) this.getSqlMapClientTemplate().queryForObject("getCsItemById",params);
		if(csItem!=null)
			MemCache.setValue(CsItem.class,id, csItem.getKeyValue());
		return csItem;
	}
	
	/**
	 * 根据ID取商品信息(不缓存)
	 * @param id
	 * @return
	 */
	public CsItem GetCsItemById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsItem) this.getSqlMapClientTemplate().queryForObject("getCsItemById",params);
	}
	

	/**
	 * 保存商品信息
	 * @param csItem
	 */
	public CsItem saveCsItem(CsItem csItem)
	{
		csItem = custom(csItem);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsItem", csItem));
		csItem = this.GetCsItemById(id);
		MemCache.setValue(CsItem.class,id, csItem.getKeyValue());
		return Trigger.on(CsItem.class,Trigger.SAVE,csItem);	
	}

	

	/**
	 * 更新商品信息
	 * @param csItem
	 */
	public void updateCsItem(CsItem csItem)
	{
		csItem = custom(csItem);
		this.getSqlMapClientTemplate().update("updateCsItem", csItem);
		csItem = this.GetCsItemById(csItem.getCsiId());
		if(csItem!=null){
			MemCache.setValue(CsItem.class,csItem.getCsiId(), csItem.getKeyValue());
		}
		Trigger.on(CsItem.class,Trigger.UPDATE,csItem);
	}
	/**
	 * 更新商品信息非空字段
	 * @param csItem
	 */
	public void updateCsItem$NotNull(CsItem csItem)
	{
		csItem = custom(csItem);
		this.getSqlMapClientTemplate().update("updateCsItem$NotNull", csItem);
		csItem = this.GetCsItemById(csItem.getCsiId());
		if(csItem!=null){
			MemCache.setValue(CsItem.class,csItem.getCsiId(), csItem.getKeyValue());
		}
		Trigger.on(CsItem.class,Trigger.UPDATE,csItem);
	}

	/**
	 * 根据ID删除一个商品信息
	 * @param id
	 */
	public void deleteCsItemById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsItem.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsItemById", params);
		MemCache.deleteValue(CsItem.class, id);
	}
	/**
	 * 根据ID逻辑删除商品信息 
	 * @param id
	 * @return
	 */
	public void removeCsItemById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsItem.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsItemById", params);
		MemCache.deleteValue(CsItem.class, id);
	}
	/**
	 * 根据条件更新商品信息
	 * @param values
	 * @param params
	 */
	public void updateCsItemByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsItemCount(params)>255){
			MemCache.clearValue(CsItem.class);
		}else{
			params.put("eval","csi_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsItemEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsItem.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsItemBy", allParams);
		Trigger.on(CsItem.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除商品信息
	 * @param params
	 */
	public void deleteCsItemByConfirm(Map params){
		Trigger.on(CsItem.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsItemCount(params)>255){
			MemCache.clearValue(CsItem.class);
		}else{
			params.put("eval","csi_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsItemEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsItem.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsItemBy", params);
	}
	

}