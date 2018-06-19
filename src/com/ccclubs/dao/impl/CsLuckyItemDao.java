package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsLuckyItemDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsLuckyItem;
import com.lazy3q.web.helper.$;

/**
 * 抽奖奖项的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsLuckyItemDao extends SqlMapClientDaoSupport implements ICsLuckyItemDao
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
	private CsLuckyItem custom(CsLuckyItem params){
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
			"csliName"			
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
	 * 获取所有抽奖奖项
	 * @return
	 */	
	public List<CsLuckyItem> getCsLuckyItemList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsLuckyItem> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsLuckyItemList", params);
		
		return list;
	}
	
	
	/**
	 * 获取抽奖奖项统计
	 * @return
	 */
	public List getCsLuckyItemStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsLuckyItemStats", params);
	}

	/**
	 * 获取抽奖奖项分页
	 * @return
	 */
	public Page getCsLuckyItemPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsLuckyItemCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsLuckyItemPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取抽奖奖项数量
	 * @return
	 */	
	public Long getCsLuckyItemCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsLuckyItemEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取抽奖奖项自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsLuckyItemEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsLuckyItemEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取抽奖奖项
	 * @param params
	 * @return
	 */
	public CsLuckyItem getCsLuckyItem(Map params)
	{		
		params = custom(params);
			
		List<CsLuckyItem> list=this.getCsLuckyItemList(params,1);
		CsLuckyItem csLuckyItem = list.isEmpty()?null:list.get(0);
		
		return csLuckyItem;
	}
		
	/**
	 * 根据ID取抽奖奖项
	 * @param id
	 * @return
	 */
	public CsLuckyItem getCsLuckyItemById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsLuckyItem csLuckyItem = null;
		if(csLuckyItem!=null)
			return csLuckyItem;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csLuckyItem = (CsLuckyItem) this.getSqlMapClientTemplate().queryForObject("getCsLuckyItemById",params);
		if(csLuckyItem!=null)
			MemCache.setValue(CsLuckyItem.class,id, csLuckyItem.getKeyValue());
		return csLuckyItem;
	}
	
	/**
	 * 根据ID取抽奖奖项(不缓存)
	 * @param id
	 * @return
	 */
	public CsLuckyItem GetCsLuckyItemById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsLuckyItem) this.getSqlMapClientTemplate().queryForObject("getCsLuckyItemById",params);
	}
	

	/**
	 * 保存抽奖奖项
	 * @param csLuckyItem
	 */
	public CsLuckyItem saveCsLuckyItem(CsLuckyItem csLuckyItem)
	{
		csLuckyItem = custom(csLuckyItem);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsLuckyItem", csLuckyItem));
		csLuckyItem = this.GetCsLuckyItemById(id);
		MemCache.setValue(CsLuckyItem.class,id, csLuckyItem.getKeyValue());
		return Trigger.on(CsLuckyItem.class,Trigger.SAVE,csLuckyItem);	
	}

	

	/**
	 * 更新抽奖奖项
	 * @param csLuckyItem
	 */
	public void updateCsLuckyItem(CsLuckyItem csLuckyItem)
	{
		csLuckyItem = custom(csLuckyItem);
		this.getSqlMapClientTemplate().update("updateCsLuckyItem", csLuckyItem);
		csLuckyItem = this.GetCsLuckyItemById(csLuckyItem.getCsliId());
		if(csLuckyItem!=null){
			MemCache.setValue(CsLuckyItem.class,csLuckyItem.getCsliId(), csLuckyItem.getKeyValue());
		}
		Trigger.on(CsLuckyItem.class,Trigger.UPDATE,csLuckyItem);
	}
	/**
	 * 更新抽奖奖项非空字段
	 * @param csLuckyItem
	 */
	public void updateCsLuckyItem$NotNull(CsLuckyItem csLuckyItem)
	{
		csLuckyItem = custom(csLuckyItem);
		this.getSqlMapClientTemplate().update("updateCsLuckyItem$NotNull", csLuckyItem);
		csLuckyItem = this.GetCsLuckyItemById(csLuckyItem.getCsliId());
		if(csLuckyItem!=null){
			MemCache.setValue(CsLuckyItem.class,csLuckyItem.getCsliId(), csLuckyItem.getKeyValue());
		}
		Trigger.on(CsLuckyItem.class,Trigger.UPDATE,csLuckyItem);
	}

	/**
	 * 根据ID删除一个抽奖奖项
	 * @param id
	 */
	public void deleteCsLuckyItemById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsLuckyItem.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsLuckyItemById", params);
		MemCache.deleteValue(CsLuckyItem.class, id);
	}
	/**
	 * 根据ID逻辑删除抽奖奖项 
	 * @param id
	 * @return
	 */
	public void removeCsLuckyItemById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsLuckyItem.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsLuckyItemById", params);
		MemCache.deleteValue(CsLuckyItem.class, id);
	}
	/**
	 * 根据条件更新抽奖奖项
	 * @param values
	 * @param params
	 */
	public void updateCsLuckyItemByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsLuckyItemCount(params)>255){
			MemCache.clearValue(CsLuckyItem.class);
		}else{
			params.put("eval","csli_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsLuckyItemEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsLuckyItem.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsLuckyItemBy", allParams);
		Trigger.on(CsLuckyItem.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除抽奖奖项
	 * @param params
	 */
	public void deleteCsLuckyItemByConfirm(Map params){
		Trigger.on(CsLuckyItem.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsLuckyItemCount(params)>255){
			MemCache.clearValue(CsLuckyItem.class);
		}else{
			params.put("eval","csli_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsLuckyItemEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsLuckyItem.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsLuckyItemBy", params);
	}
	

}