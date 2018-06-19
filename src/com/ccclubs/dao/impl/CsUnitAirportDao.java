package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsUnitAirportDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsUnitAirport;
import com.lazy3q.web.helper.$;

/**
 * 机场服务的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsUnitAirportDao extends SqlMapClientDaoSupport implements ICsUnitAirportDao
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
	private CsUnitAirport custom(CsUnitAirport params){
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
			"csuaFlight"			
			,"csuaContact"			
			,"csuaMobile"			
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
	 * 获取所有机场服务
	 * @return
	 */	
	public List<CsUnitAirport> getCsUnitAirportList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsUnitAirport> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsUnitAirportList", params);
		
		return list;
	}
	
	
	/**
	 * 获取机场服务统计
	 * @return
	 */
	public List getCsUnitAirportStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsUnitAirportStats", params);
	}

	/**
	 * 获取机场服务分页
	 * @return
	 */
	public Page getCsUnitAirportPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsUnitAirportCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsUnitAirportPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取机场服务数量
	 * @return
	 */	
	public Long getCsUnitAirportCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsUnitAirportEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取机场服务自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsUnitAirportEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsUnitAirportEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取机场服务
	 * @param params
	 * @return
	 */
	public CsUnitAirport getCsUnitAirport(Map params)
	{		
		params = custom(params);
			
		List<CsUnitAirport> list=this.getCsUnitAirportList(params,1);
		CsUnitAirport csUnitAirport = list.isEmpty()?null:list.get(0);
		
		return csUnitAirport;
	}
		
	/**
	 * 根据ID取机场服务
	 * @param id
	 * @return
	 */
	public CsUnitAirport getCsUnitAirportById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsUnitAirport csUnitAirport = null;
		if(csUnitAirport!=null)
			return csUnitAirport;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csUnitAirport = (CsUnitAirport) this.getSqlMapClientTemplate().queryForObject("getCsUnitAirportById",params);
		if(csUnitAirport!=null)
			MemCache.setValue(CsUnitAirport.class,id, csUnitAirport.getKeyValue());
		return csUnitAirport;
	}
	
	/**
	 * 根据ID取机场服务(不缓存)
	 * @param id
	 * @return
	 */
	public CsUnitAirport GetCsUnitAirportById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsUnitAirport) this.getSqlMapClientTemplate().queryForObject("getCsUnitAirportById",params);
	}
	

	/**
	 * 保存机场服务
	 * @param csUnitAirport
	 */
	public CsUnitAirport saveCsUnitAirport(CsUnitAirport csUnitAirport)
	{
		csUnitAirport = custom(csUnitAirport);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsUnitAirport", csUnitAirport));
		csUnitAirport = this.GetCsUnitAirportById(id);
		MemCache.setValue(CsUnitAirport.class,id, csUnitAirport.getKeyValue());
		return Trigger.on(CsUnitAirport.class,Trigger.SAVE,csUnitAirport);	
	}

	

	/**
	 * 更新机场服务
	 * @param csUnitAirport
	 */
	public void updateCsUnitAirport(CsUnitAirport csUnitAirport)
	{
		csUnitAirport = custom(csUnitAirport);
		this.getSqlMapClientTemplate().update("updateCsUnitAirport", csUnitAirport);
		csUnitAirport = this.GetCsUnitAirportById(csUnitAirport.getCsuaId());
		if(csUnitAirport!=null){
			MemCache.setValue(CsUnitAirport.class,csUnitAirport.getCsuaId(), csUnitAirport.getKeyValue());
		}
		Trigger.on(CsUnitAirport.class,Trigger.UPDATE,csUnitAirport);
	}
	/**
	 * 更新机场服务非空字段
	 * @param csUnitAirport
	 */
	public void updateCsUnitAirport$NotNull(CsUnitAirport csUnitAirport)
	{
		csUnitAirport = custom(csUnitAirport);
		this.getSqlMapClientTemplate().update("updateCsUnitAirport$NotNull", csUnitAirport);
		csUnitAirport = this.GetCsUnitAirportById(csUnitAirport.getCsuaId());
		if(csUnitAirport!=null){
			MemCache.setValue(CsUnitAirport.class,csUnitAirport.getCsuaId(), csUnitAirport.getKeyValue());
		}
		Trigger.on(CsUnitAirport.class,Trigger.UPDATE,csUnitAirport);
	}

	/**
	 * 根据ID删除一个机场服务
	 * @param id
	 */
	public void deleteCsUnitAirportById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsUnitAirport.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsUnitAirportById", params);
		MemCache.deleteValue(CsUnitAirport.class, id);
	}
	/**
	 * 根据ID逻辑删除机场服务 
	 * @param id
	 * @return
	 */
	public void removeCsUnitAirportById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsUnitAirport.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsUnitAirportById", params);
		MemCache.deleteValue(CsUnitAirport.class, id);
	}
	/**
	 * 根据条件更新机场服务
	 * @param values
	 * @param params
	 */
	public void updateCsUnitAirportByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsUnitAirportCount(params)>255){
			MemCache.clearValue(CsUnitAirport.class);
		}else{
			params.put("eval","csua_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsUnitAirportEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsUnitAirport.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsUnitAirportBy", allParams);
		Trigger.on(CsUnitAirport.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除机场服务
	 * @param params
	 */
	public void deleteCsUnitAirportByConfirm(Map params){
		Trigger.on(CsUnitAirport.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsUnitAirportCount(params)>255){
			MemCache.clearValue(CsUnitAirport.class);
		}else{
			params.put("eval","csua_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsUnitAirportEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsUnitAirport.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsUnitAirportBy", params);
	}
	

}