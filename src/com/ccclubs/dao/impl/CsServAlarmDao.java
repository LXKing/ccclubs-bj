package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsServAlarmDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsServAlarm;
import com.lazy3q.web.helper.$;

/**
 * 服务监控报警的Dao实现
 * @author Joel
 */
@SuppressWarnings("unchecked")
public class CsServAlarmDao extends SqlMapClientDaoSupport implements ICsServAlarmDao
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
	private CsServAlarm custom(CsServAlarm params){
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
		"cssaId","cssaHost","cssaType","cssaTitle","cssaContent","cssaRelate","cssaSrouce","cssaStatus","cssaData","cssaAddTime","cssaUpdateTime"
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
	 * 获取所有服务监控报警
	 * @return
	 */	
	public List<CsServAlarm> getCsServAlarmList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsServAlarm> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsServAlarmList", params);
		
		return list;
	}
	
	
	/**
	 * 获取服务监控报警统计
	 * @return
	 */
	public List getCsServAlarmStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsServAlarmStats", params);
	}

	/**
	 * 获取服务监控报警分页
	 * @return
	 */
	public Page getCsServAlarmPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsServAlarmCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsServAlarmPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取服务监控报警数量
	 * @return
	 */	
	public Long getCsServAlarmCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsServAlarmEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取服务监控报警自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsServAlarmEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsServAlarmEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取服务监控报警
	 * @param params
	 * @return
	 */
	public CsServAlarm getCsServAlarm(Map params)
	{		
		params = custom(params);
			
		List<CsServAlarm> list=this.getCsServAlarmList(params,1);
		CsServAlarm csServAlarm = list.isEmpty()?null:list.get(0);
		
		return csServAlarm;
	}
		
	/**
	 * 根据ID取服务监控报警
	 * @param id
	 * @return
	 */
	public CsServAlarm getCsServAlarmById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsServAlarm csServAlarm = null;
		if(csServAlarm!=null)
			return csServAlarm;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csServAlarm = (CsServAlarm) this.getSqlMapClientTemplate().queryForObject("getCsServAlarmById",params);
		if(csServAlarm!=null)
			MemCache.setValue(CsServAlarm.class,id, csServAlarm.getKeyValue());
		return csServAlarm;
	}
	
	/**
	 * 根据ID取服务监控报警(不缓存)
	 * @param id
	 * @return
	 */
	public CsServAlarm GetCsServAlarmById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsServAlarm) this.getSqlMapClientTemplate().queryForObject("getCsServAlarmById",params);
	}
	

	/**
	 * 保存服务监控报警
	 * @param csServAlarm
	 */
	public CsServAlarm saveCsServAlarm(CsServAlarm csServAlarm)
	{
		csServAlarm = custom(csServAlarm);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsServAlarm", csServAlarm));
		csServAlarm = this.GetCsServAlarmById(id);
		MemCache.setValue(CsServAlarm.class,id, csServAlarm.getKeyValue());
		return Trigger.on(CsServAlarm.class,Trigger.SAVE,csServAlarm);	
	}

	

	/**
	 * 更新服务监控报警
	 * @param csServAlarm
	 */
	public void updateCsServAlarm(CsServAlarm csServAlarm)
	{
		csServAlarm = custom(csServAlarm);
		this.getSqlMapClientTemplate().update("updateCsServAlarm", csServAlarm);
		csServAlarm = this.GetCsServAlarmById(csServAlarm.getCssaId());
		if(csServAlarm!=null){
			MemCache.setValue(CsServAlarm.class,csServAlarm.getCssaId(), csServAlarm.getKeyValue());
		}
		Trigger.on(CsServAlarm.class,Trigger.UPDATE,csServAlarm);
	}
	/**
	 * 更新服务监控报警非空字段
	 * @param csServAlarm
	 */
	public void updateCsServAlarm$NotNull(CsServAlarm csServAlarm)
	{
		csServAlarm = custom(csServAlarm);
		this.getSqlMapClientTemplate().update("updateCsServAlarm$NotNull", csServAlarm);
		csServAlarm = this.GetCsServAlarmById(csServAlarm.getCssaId());
		if(csServAlarm!=null){
			MemCache.setValue(CsServAlarm.class,csServAlarm.getCssaId(), csServAlarm.getKeyValue());
		}
		Trigger.on(CsServAlarm.class,Trigger.UPDATE,csServAlarm);
	}

	/**
	 * 根据ID删除一个服务监控报警
	 * @param id
	 */
	public void deleteCsServAlarmById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsServAlarm.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsServAlarmById", params);
		MemCache.deleteValue(CsServAlarm.class, id);
	}
	/**
	 * 根据ID逻辑删除服务监控报警 
	 * @param id
	 * @return
	 */
	public void removeCsServAlarmById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsServAlarm.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsServAlarmById", params);
		MemCache.deleteValue(CsServAlarm.class, id);
	}
	/**
	 * 根据条件更新服务监控报警
	 * @param values
	 * @param params
	 */
	public void updateCsServAlarmByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsServAlarmCount(params)>255){
			MemCache.clearValue(CsServAlarm.class);
		}else{
			params.put("eval","cssa_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsServAlarmEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsServAlarm.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsServAlarmBy", allParams);
		Trigger.on(CsServAlarm.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除服务监控报警
	 * @param params
	 */
	public void deleteCsServAlarmByConfirm(Map params){
		Trigger.on(CsServAlarm.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsServAlarmCount(params)>255){
			MemCache.clearValue(CsServAlarm.class);
		}else{
			params.put("eval","cssa_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsServAlarmEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsServAlarm.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsServAlarmBy", params);
	}
	

}