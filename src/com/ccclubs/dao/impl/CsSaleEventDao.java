package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsSaleEventDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsSaleEvent;
import com.lazy3q.web.helper.$;

/**
 * 销售活动的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsSaleEventDao extends SqlMapClientDaoSupport implements ICsSaleEventDao
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
	private CsSaleEvent custom(CsSaleEvent params){
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
			"csseSubject"			
			,"csseAddress"			
			,"csseDesc"			
			,"csseRemark"			
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
	 * 获取所有销售活动
	 * @return
	 */	
	public List<CsSaleEvent> getCsSaleEventList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsSaleEvent> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsSaleEventList", params);
		
		return list;
	}
	
	
	/**
	 * 获取销售活动统计
	 * @return
	 */
	public List getCsSaleEventStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsSaleEventStats", params);
	}

	/**
	 * 获取销售活动分页
	 * @return
	 */
	public Page getCsSaleEventPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsSaleEventCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsSaleEventPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取销售活动数量
	 * @return
	 */	
	public Long getCsSaleEventCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsSaleEventEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取销售活动自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsSaleEventEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsSaleEventEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取销售活动
	 * @param params
	 * @return
	 */
	public CsSaleEvent getCsSaleEvent(Map params)
	{		
		params = custom(params);
			
		List<CsSaleEvent> list=this.getCsSaleEventList(params,1);
		CsSaleEvent csSaleEvent = list.isEmpty()?null:list.get(0);
		
		return csSaleEvent;
	}
		
	/**
	 * 根据ID取销售活动
	 * @param id
	 * @return
	 */
	public CsSaleEvent getCsSaleEventById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsSaleEvent csSaleEvent = null;
		if(csSaleEvent!=null)
			return csSaleEvent;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csSaleEvent = (CsSaleEvent) this.getSqlMapClientTemplate().queryForObject("getCsSaleEventById",params);
		if(csSaleEvent!=null)
			MemCache.setValue(CsSaleEvent.class,id, csSaleEvent.getKeyValue());
		return csSaleEvent;
	}
	
	/**
	 * 根据ID取销售活动(不缓存)
	 * @param id
	 * @return
	 */
	public CsSaleEvent GetCsSaleEventById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsSaleEvent) this.getSqlMapClientTemplate().queryForObject("getCsSaleEventById",params);
	}
	

	/**
	 * 保存销售活动
	 * @param csSaleEvent
	 */
	public CsSaleEvent saveCsSaleEvent(CsSaleEvent csSaleEvent)
	{
		csSaleEvent = custom(csSaleEvent);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsSaleEvent", csSaleEvent));
		csSaleEvent = this.GetCsSaleEventById(id);
		MemCache.setValue(CsSaleEvent.class,id, csSaleEvent.getKeyValue());
		return Trigger.on(CsSaleEvent.class,Trigger.SAVE,csSaleEvent);	
	}

	

	/**
	 * 更新销售活动
	 * @param csSaleEvent
	 */
	public void updateCsSaleEvent(CsSaleEvent csSaleEvent)
	{
		csSaleEvent = custom(csSaleEvent);
		this.getSqlMapClientTemplate().update("updateCsSaleEvent", csSaleEvent);
		csSaleEvent = this.GetCsSaleEventById(csSaleEvent.getCsseId());
		if(csSaleEvent!=null){
			MemCache.setValue(CsSaleEvent.class,csSaleEvent.getCsseId(), csSaleEvent.getKeyValue());
		}
		Trigger.on(CsSaleEvent.class,Trigger.UPDATE,csSaleEvent);
	}
	/**
	 * 更新销售活动非空字段
	 * @param csSaleEvent
	 */
	public void updateCsSaleEvent$NotNull(CsSaleEvent csSaleEvent)
	{
		csSaleEvent = custom(csSaleEvent);
		this.getSqlMapClientTemplate().update("updateCsSaleEvent$NotNull", csSaleEvent);
		csSaleEvent = this.GetCsSaleEventById(csSaleEvent.getCsseId());
		if(csSaleEvent!=null){
			MemCache.setValue(CsSaleEvent.class,csSaleEvent.getCsseId(), csSaleEvent.getKeyValue());
		}
		Trigger.on(CsSaleEvent.class,Trigger.UPDATE,csSaleEvent);
	}

	/**
	 * 根据ID删除一个销售活动
	 * @param id
	 */
	public void deleteCsSaleEventById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsSaleEvent.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsSaleEventById", params);
		MemCache.deleteValue(CsSaleEvent.class, id);
	}
	/**
	 * 根据ID逻辑删除销售活动 
	 * @param id
	 * @return
	 */
	public void removeCsSaleEventById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsSaleEvent.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsSaleEventById", params);
		MemCache.deleteValue(CsSaleEvent.class, id);
	}
	/**
	 * 根据条件更新销售活动
	 * @param values
	 * @param params
	 */
	public void updateCsSaleEventByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsSaleEventCount(params)>255){
			MemCache.clearValue(CsSaleEvent.class);
		}else{
			params.put("eval","csse_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsSaleEventEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsSaleEvent.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsSaleEventBy", allParams);
		Trigger.on(CsSaleEvent.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除销售活动
	 * @param params
	 */
	public void deleteCsSaleEventByConfirm(Map params){
		Trigger.on(CsSaleEvent.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsSaleEventCount(params)>255){
			MemCache.clearValue(CsSaleEvent.class);
		}else{
			params.put("eval","csse_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsSaleEventEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsSaleEvent.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsSaleEventBy", params);
	}
	

}