package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsCoinSourceDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCoinSource;
import com.lazy3q.web.helper.$;

/**
 * 红包来源的Dao实现
 * @author Joel
 */
@SuppressWarnings("unchecked")
public class CsCoinSourceDao extends SqlMapClientDaoSupport implements ICsCoinSourceDao
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
	private CsCoinSource custom(CsCoinSource params){
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
		String[] likes={"cscsName","cscsFlag","cscsRemark"};
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
	 * 获取所有红包来源
	 * @return
	 */	
	public List<CsCoinSource> getCsCoinSourceList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsCoinSource> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsCoinSourceList", params);
		
		return list;
	}
	
	
	/**
	 * 获取红包来源统计
	 * @return
	 */
	public List getCsCoinSourceStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsCoinSourceStats", params);
	}

	/**
	 * 获取红包来源分页
	 * @return
	 */
	public Page getCsCoinSourcePage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsCoinSourceCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsCoinSourcePage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取红包来源数量
	 * @return
	 */	
	public Long getCsCoinSourceCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCoinSourceEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取红包来源自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsCoinSourceEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCoinSourceEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取红包来源
	 * @param params
	 * @return
	 */
	public CsCoinSource getCsCoinSource(Map params)
	{		
		params = custom(params);
			
		List<CsCoinSource> list=this.getCsCoinSourceList(params,1);
		CsCoinSource csCoinSource = list.isEmpty()?null:list.get(0);
		
		return csCoinSource;
	}
		
	/**
	 * 根据ID取红包来源
	 * @param id
	 * @return
	 */
	public CsCoinSource getCsCoinSourceById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsCoinSource csCoinSource = null;
		if(csCoinSource!=null)
			return csCoinSource;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csCoinSource = (CsCoinSource) this.getSqlMapClientTemplate().queryForObject("getCsCoinSourceById",params);
		if(csCoinSource!=null)
			MemCache.setValue(CsCoinSource.class,id, csCoinSource.getKeyValue());
		return csCoinSource;
	}
	
	/**
	 * 根据ID取红包来源(不缓存)
	 * @param id
	 * @return
	 */
	public CsCoinSource GetCsCoinSourceById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsCoinSource) this.getSqlMapClientTemplate().queryForObject("getCsCoinSourceById",params);
	}
	

	/**
	 * 保存红包来源
	 * @param csCoinSource
	 */
	public CsCoinSource saveCsCoinSource(CsCoinSource csCoinSource)
	{
		csCoinSource = custom(csCoinSource);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsCoinSource", csCoinSource));
		csCoinSource = this.GetCsCoinSourceById(id);
		MemCache.setValue(CsCoinSource.class,id, csCoinSource.getKeyValue());
		return Trigger.on(CsCoinSource.class,Trigger.SAVE,csCoinSource);	
	}

	

	/**
	 * 更新红包来源
	 * @param csCoinSource
	 */
	public void updateCsCoinSource(CsCoinSource csCoinSource)
	{
		csCoinSource = custom(csCoinSource);
		this.getSqlMapClientTemplate().update("updateCsCoinSource", csCoinSource);
		csCoinSource = this.GetCsCoinSourceById(csCoinSource.getCscsId());
		if(csCoinSource!=null){
			MemCache.setValue(CsCoinSource.class,csCoinSource.getCscsId(), csCoinSource.getKeyValue());
		}
		Trigger.on(CsCoinSource.class,Trigger.UPDATE,csCoinSource);
	}
	/**
	 * 更新红包来源非空字段
	 * @param csCoinSource
	 */
	public void updateCsCoinSource$NotNull(CsCoinSource csCoinSource)
	{
		csCoinSource = custom(csCoinSource);
		this.getSqlMapClientTemplate().update("updateCsCoinSource$NotNull", csCoinSource);
		csCoinSource = this.GetCsCoinSourceById(csCoinSource.getCscsId());
		if(csCoinSource!=null){
			MemCache.setValue(CsCoinSource.class,csCoinSource.getCscsId(), csCoinSource.getKeyValue());
		}
		Trigger.on(CsCoinSource.class,Trigger.UPDATE,csCoinSource);
	}

	/**
	 * 根据ID删除一个红包来源
	 * @param id
	 */
	public void deleteCsCoinSourceById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsCoinSource.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsCoinSourceById", params);
		MemCache.deleteValue(CsCoinSource.class, id);
	}
	/**
	 * 根据ID逻辑删除红包来源 
	 * @param id
	 * @return
	 */
	public void removeCsCoinSourceById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsCoinSource.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsCoinSourceById", params);
		MemCache.deleteValue(CsCoinSource.class, id);
	}
	/**
	 * 根据条件更新红包来源
	 * @param values
	 * @param params
	 */
	public void updateCsCoinSourceByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsCoinSourceCount(params)>255){
			MemCache.clearValue(CsCoinSource.class);
		}else{
			params.put("eval","cscs_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCoinSourceEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCoinSource.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsCoinSourceBy", allParams);
		Trigger.on(CsCoinSource.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除红包来源
	 * @param params
	 */
	public void deleteCsCoinSourceByConfirm(Map params){
		Trigger.on(CsCoinSource.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsCoinSourceCount(params)>255){
			MemCache.clearValue(CsCoinSource.class);
		}else{
			params.put("eval","cscs_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCoinSourceEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCoinSource.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsCoinSourceBy", params);
	}
	

}