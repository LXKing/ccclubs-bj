package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsLuckyDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsLucky;
import com.lazy3q.web.helper.$;

/**
 * 抽奖活动的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsLuckyDao extends SqlMapClientDaoSupport implements ICsLuckyDao
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
	private CsLucky custom(CsLucky params){
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
			"cslName"			
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
	 * 获取所有抽奖活动
	 * @return
	 */	
	public List<CsLucky> getCsLuckyList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsLucky> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsLuckyList", params);
		
		return list;
	}
	
	
	/**
	 * 获取抽奖活动统计
	 * @return
	 */
	public List getCsLuckyStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsLuckyStats", params);
	}

	/**
	 * 获取抽奖活动分页
	 * @return
	 */
	public Page getCsLuckyPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsLuckyCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsLuckyPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取抽奖活动数量
	 * @return
	 */	
	public Long getCsLuckyCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsLuckyEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取抽奖活动自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsLuckyEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsLuckyEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取抽奖活动
	 * @param params
	 * @return
	 */
	public CsLucky getCsLucky(Map params)
	{		
		params = custom(params);
			
		List<CsLucky> list=this.getCsLuckyList(params,1);
		CsLucky csLucky = list.isEmpty()?null:list.get(0);
		
		return csLucky;
	}
		
	/**
	 * 根据ID取抽奖活动
	 * @param id
	 * @return
	 */
	public CsLucky getCsLuckyById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsLucky csLucky = null;
		if(csLucky!=null)
			return csLucky;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csLucky = (CsLucky) this.getSqlMapClientTemplate().queryForObject("getCsLuckyById",params);
		if(csLucky!=null)
			MemCache.setValue(CsLucky.class,id, csLucky.getKeyValue());
		return csLucky;
	}
	
	/**
	 * 根据ID取抽奖活动(不缓存)
	 * @param id
	 * @return
	 */
	public CsLucky GetCsLuckyById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsLucky) this.getSqlMapClientTemplate().queryForObject("getCsLuckyById",params);
	}
	

	/**
	 * 保存抽奖活动
	 * @param csLucky
	 */
	public CsLucky saveCsLucky(CsLucky csLucky)
	{
		csLucky = custom(csLucky);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsLucky", csLucky));
		csLucky = this.GetCsLuckyById(id);
		MemCache.setValue(CsLucky.class,id, csLucky.getKeyValue());
		return Trigger.on(CsLucky.class,Trigger.SAVE,csLucky);	
	}

	

	/**
	 * 更新抽奖活动
	 * @param csLucky
	 */
	public void updateCsLucky(CsLucky csLucky)
	{
		csLucky = custom(csLucky);
		this.getSqlMapClientTemplate().update("updateCsLucky", csLucky);
		csLucky = this.GetCsLuckyById(csLucky.getCslId());
		if(csLucky!=null){
			MemCache.setValue(CsLucky.class,csLucky.getCslId(), csLucky.getKeyValue());
		}
		Trigger.on(CsLucky.class,Trigger.UPDATE,csLucky);
	}
	/**
	 * 更新抽奖活动非空字段
	 * @param csLucky
	 */
	public void updateCsLucky$NotNull(CsLucky csLucky)
	{
		csLucky = custom(csLucky);
		this.getSqlMapClientTemplate().update("updateCsLucky$NotNull", csLucky);
		csLucky = this.GetCsLuckyById(csLucky.getCslId());
		if(csLucky!=null){
			MemCache.setValue(CsLucky.class,csLucky.getCslId(), csLucky.getKeyValue());
		}
		Trigger.on(CsLucky.class,Trigger.UPDATE,csLucky);
	}

	/**
	 * 根据ID删除一个抽奖活动
	 * @param id
	 */
	public void deleteCsLuckyById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsLucky.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsLuckyById", params);
		MemCache.deleteValue(CsLucky.class, id);
	}
	/**
	 * 根据ID逻辑删除抽奖活动 
	 * @param id
	 * @return
	 */
	public void removeCsLuckyById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsLucky.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsLuckyById", params);
		MemCache.deleteValue(CsLucky.class, id);
	}
	/**
	 * 根据条件更新抽奖活动
	 * @param values
	 * @param params
	 */
	public void updateCsLuckyByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsLuckyCount(params)>255){
			MemCache.clearValue(CsLucky.class);
		}else{
			params.put("eval","csl_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsLuckyEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsLucky.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsLuckyBy", allParams);
		Trigger.on(CsLucky.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除抽奖活动
	 * @param params
	 */
	public void deleteCsLuckyByConfirm(Map params){
		Trigger.on(CsLucky.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsLuckyCount(params)>255){
			MemCache.clearValue(CsLucky.class);
		}else{
			params.put("eval","csl_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsLuckyEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsLucky.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsLuckyBy", params);
	}
	

}