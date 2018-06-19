package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsEventDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsEvent;
import com.lazy3q.web.helper.$;

/**
 * 系统事件的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsEventDao extends SqlMapClientDaoSupport implements ICsEventDao
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
	private CsEvent custom(CsEvent params){
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
			"cseContent"			
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
	 * 获取所有系统事件
	 * @return
	 */	
	public List<CsEvent> getCsEventList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsEvent> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsEventList", params);
		
		return list;
	}
	
	
	/**
	 * 获取系统事件统计
	 * @return
	 */
	public List getCsEventStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsEventStats", params);
	}

	/**
	 * 获取系统事件分页
	 * @return
	 */
	public Page getCsEventPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsEventCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsEventPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取系统事件数量
	 * @return
	 */	
	public Long getCsEventCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsEventEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取系统事件自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsEventEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsEventEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取系统事件
	 * @param params
	 * @return
	 */
	public CsEvent getCsEvent(Map params)
	{		
		params = custom(params);
			
		List<CsEvent> list=this.getCsEventList(params,1);
		CsEvent csEvent = list.isEmpty()?null:list.get(0);
		
		return csEvent;
	}
		
	/**
	 * 根据ID取系统事件
	 * @param id
	 * @return
	 */
	public CsEvent getCsEventById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsEvent csEvent = null;
		if(csEvent!=null)
			return csEvent;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csEvent = (CsEvent) this.getSqlMapClientTemplate().queryForObject("getCsEventById",params);
		return csEvent;
	}
	
	/**
	 * 根据ID取系统事件(不缓存)
	 * @param id
	 * @return
	 */
	public CsEvent GetCsEventById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsEvent) this.getSqlMapClientTemplate().queryForObject("getCsEventById",params);
	}
	

	/**
	 * 保存系统事件
	 * @param csEvent
	 */
	public CsEvent saveCsEvent(CsEvent csEvent)
	{
		csEvent = custom(csEvent);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsEvent", csEvent));
		csEvent = this.GetCsEventById(id);
		return Trigger.on(CsEvent.class,Trigger.SAVE,csEvent);	
	}

	

	/**
	 * 更新系统事件
	 * @param csEvent
	 */
	public void updateCsEvent(CsEvent csEvent)
	{
		csEvent = custom(csEvent);
		this.getSqlMapClientTemplate().update("updateCsEvent", csEvent);
		csEvent = this.GetCsEventById(csEvent.getCseId());
		Trigger.on(CsEvent.class,Trigger.UPDATE,csEvent);
	}
	/**
	 * 更新系统事件非空字段
	 * @param csEvent
	 */
	public void updateCsEvent$NotNull(CsEvent csEvent)
	{
		csEvent = custom(csEvent);
		this.getSqlMapClientTemplate().update("updateCsEvent$NotNull", csEvent);
		csEvent = this.GetCsEventById(csEvent.getCseId());
		Trigger.on(CsEvent.class,Trigger.UPDATE,csEvent);
	}

	/**
	 * 根据ID删除一个系统事件
	 * @param id
	 */
	public void deleteCsEventById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsEvent.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsEventById", params);
	}
	/**
	 * 根据ID逻辑删除系统事件 
	 * @param id
	 * @return
	 */
	public void removeCsEventById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsEvent.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsEventById", params);
	}
	/**
	 * 根据条件更新系统事件
	 * @param values
	 * @param params
	 */
	public void updateCsEventByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
			
		int rows = this.getSqlMapClientTemplate().update("updateCsEventBy", allParams);
		Trigger.on(CsEvent.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除系统事件
	 * @param params
	 */
	public void deleteCsEventByConfirm(Map params){
		Trigger.on(CsEvent.class,Trigger.DELETE,params);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsEventBy", params);
	}
	

}