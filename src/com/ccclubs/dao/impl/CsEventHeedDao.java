package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsEventHeedDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsEventHeed;
import com.lazy3q.web.helper.$;

/**
 * 事件关注的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsEventHeedDao extends SqlMapClientDaoSupport implements ICsEventHeedDao
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
	private CsEventHeed custom(CsEventHeed params){
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
			"csehHeedType"			
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
	 * 获取所有事件关注
	 * @return
	 */	
	public List<CsEventHeed> getCsEventHeedList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsEventHeed> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsEventHeedList", params);
		
		return list;
	}
	
	
	/**
	 * 获取事件关注统计
	 * @return
	 */
	public List getCsEventHeedStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsEventHeedStats", params);
	}

	/**
	 * 获取事件关注分页
	 * @return
	 */
	public Page getCsEventHeedPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsEventHeedCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsEventHeedPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取事件关注数量
	 * @return
	 */	
	public Long getCsEventHeedCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsEventHeedEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取事件关注自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsEventHeedEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsEventHeedEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取事件关注
	 * @param params
	 * @return
	 */
	public CsEventHeed getCsEventHeed(Map params)
	{		
		params = custom(params);
			
		List<CsEventHeed> list=this.getCsEventHeedList(params,1);
		CsEventHeed csEventHeed = list.isEmpty()?null:list.get(0);
		
		return csEventHeed;
	}
		
	/**
	 * 根据ID取事件关注
	 * @param id
	 * @return
	 */
	public CsEventHeed getCsEventHeedById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsEventHeed csEventHeed = null;
		if(csEventHeed!=null)
			return csEventHeed;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csEventHeed = (CsEventHeed) this.getSqlMapClientTemplate().queryForObject("getCsEventHeedById",params);
		if(csEventHeed!=null)
			MemCache.setValue(CsEventHeed.class,id, csEventHeed.getKeyValue());
		return csEventHeed;
	}
	
	/**
	 * 根据ID取事件关注(不缓存)
	 * @param id
	 * @return
	 */
	public CsEventHeed GetCsEventHeedById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsEventHeed) this.getSqlMapClientTemplate().queryForObject("getCsEventHeedById",params);
	}
	

	/**
	 * 保存事件关注
	 * @param csEventHeed
	 */
	public CsEventHeed saveCsEventHeed(CsEventHeed csEventHeed)
	{
		csEventHeed = custom(csEventHeed);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsEventHeed", csEventHeed));
		csEventHeed = this.GetCsEventHeedById(id);
		MemCache.setValue(CsEventHeed.class,id, csEventHeed.getKeyValue());
		return Trigger.on(CsEventHeed.class,Trigger.SAVE,csEventHeed);	
	}

	

	/**
	 * 更新事件关注
	 * @param csEventHeed
	 */
	public void updateCsEventHeed(CsEventHeed csEventHeed)
	{
		csEventHeed = custom(csEventHeed);
		this.getSqlMapClientTemplate().update("updateCsEventHeed", csEventHeed);
		csEventHeed = this.GetCsEventHeedById(csEventHeed.getCsehId());
		if(csEventHeed!=null){
			MemCache.setValue(CsEventHeed.class,csEventHeed.getCsehId(), csEventHeed.getKeyValue());
		}
		Trigger.on(CsEventHeed.class,Trigger.UPDATE,csEventHeed);
	}
	/**
	 * 更新事件关注非空字段
	 * @param csEventHeed
	 */
	public void updateCsEventHeed$NotNull(CsEventHeed csEventHeed)
	{
		csEventHeed = custom(csEventHeed);
		this.getSqlMapClientTemplate().update("updateCsEventHeed$NotNull", csEventHeed);
		csEventHeed = this.GetCsEventHeedById(csEventHeed.getCsehId());
		if(csEventHeed!=null){
			MemCache.setValue(CsEventHeed.class,csEventHeed.getCsehId(), csEventHeed.getKeyValue());
		}
		Trigger.on(CsEventHeed.class,Trigger.UPDATE,csEventHeed);
	}

	/**
	 * 根据ID删除一个事件关注
	 * @param id
	 */
	public void deleteCsEventHeedById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsEventHeed.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsEventHeedById", params);
		MemCache.deleteValue(CsEventHeed.class, id);
	}
	/**
	 * 根据ID逻辑删除事件关注 
	 * @param id
	 * @return
	 */
	public void removeCsEventHeedById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsEventHeed.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsEventHeedById", params);
		MemCache.deleteValue(CsEventHeed.class, id);
	}
	/**
	 * 根据条件更新事件关注
	 * @param values
	 * @param params
	 */
	public void updateCsEventHeedByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsEventHeedCount(params)>255){
			MemCache.clearValue(CsEventHeed.class);
		}else{
			params.put("eval","cseh_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsEventHeedEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsEventHeed.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsEventHeedBy", allParams);
		Trigger.on(CsEventHeed.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除事件关注
	 * @param params
	 */
	public void deleteCsEventHeedByConfirm(Map params){
		Trigger.on(CsEventHeed.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsEventHeedCount(params)>255){
			MemCache.clearValue(CsEventHeed.class);
		}else{
			params.put("eval","cseh_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsEventHeedEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsEventHeed.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsEventHeedBy", params);
	}
	

}