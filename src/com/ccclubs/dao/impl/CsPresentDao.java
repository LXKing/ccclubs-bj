package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsPresentDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsPresent;
import com.lazy3q.web.helper.$;

/**
 * 礼品赠品的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsPresentDao extends SqlMapClientDaoSupport implements ICsPresentDao
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
	private CsPresent custom(CsPresent params){
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
			"cspName"			
			,"cspProfile"			
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
	 * 获取所有礼品赠品
	 * @return
	 */	
	public List<CsPresent> getCsPresentList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsPresent> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsPresentList", params);
		
		return list;
	}
	
	
	/**
	 * 获取礼品赠品统计
	 * @return
	 */
	public List getCsPresentStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsPresentStats", params);
	}

	/**
	 * 获取礼品赠品分页
	 * @return
	 */
	public Page getCsPresentPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsPresentCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsPresentPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取礼品赠品数量
	 * @return
	 */	
	public Long getCsPresentCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsPresentEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取礼品赠品自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsPresentEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsPresentEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取礼品赠品
	 * @param params
	 * @return
	 */
	public CsPresent getCsPresent(Map params)
	{		
		params = custom(params);
			
		List<CsPresent> list=this.getCsPresentList(params,1);
		CsPresent csPresent = list.isEmpty()?null:list.get(0);
		
		return csPresent;
	}
		
	/**
	 * 根据ID取礼品赠品
	 * @param id
	 * @return
	 */
	public CsPresent getCsPresentById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsPresent csPresent = null;
		if(csPresent!=null)
			return csPresent;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csPresent = (CsPresent) this.getSqlMapClientTemplate().queryForObject("getCsPresentById",params);
		if(csPresent!=null)
			MemCache.setValue(CsPresent.class,id, csPresent.getKeyValue());
		return csPresent;
	}
	
	/**
	 * 根据ID取礼品赠品(不缓存)
	 * @param id
	 * @return
	 */
	public CsPresent GetCsPresentById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsPresent) this.getSqlMapClientTemplate().queryForObject("getCsPresentById",params);
	}
	

	/**
	 * 保存礼品赠品
	 * @param csPresent
	 */
	public CsPresent saveCsPresent(CsPresent csPresent)
	{
		csPresent = custom(csPresent);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsPresent", csPresent));
		csPresent = this.GetCsPresentById(id);
		MemCache.setValue(CsPresent.class,id, csPresent.getKeyValue());
		return Trigger.on(CsPresent.class,Trigger.SAVE,csPresent);	
	}

	

	/**
	 * 更新礼品赠品
	 * @param csPresent
	 */
	public void updateCsPresent(CsPresent csPresent)
	{
		csPresent = custom(csPresent);
		this.getSqlMapClientTemplate().update("updateCsPresent", csPresent);
		csPresent = this.GetCsPresentById(csPresent.getCspId());
		if(csPresent!=null){
			MemCache.setValue(CsPresent.class,csPresent.getCspId(), csPresent.getKeyValue());
		}
		Trigger.on(CsPresent.class,Trigger.UPDATE,csPresent);
	}
	/**
	 * 更新礼品赠品非空字段
	 * @param csPresent
	 */
	public void updateCsPresent$NotNull(CsPresent csPresent)
	{
		csPresent = custom(csPresent);
		this.getSqlMapClientTemplate().update("updateCsPresent$NotNull", csPresent);
		csPresent = this.GetCsPresentById(csPresent.getCspId());
		if(csPresent!=null){
			MemCache.setValue(CsPresent.class,csPresent.getCspId(), csPresent.getKeyValue());
		}
		Trigger.on(CsPresent.class,Trigger.UPDATE,csPresent);
	}

	/**
	 * 根据ID删除一个礼品赠品
	 * @param id
	 */
	public void deleteCsPresentById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsPresent.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsPresentById", params);
		MemCache.deleteValue(CsPresent.class, id);
	}
	/**
	 * 根据ID逻辑删除礼品赠品 
	 * @param id
	 * @return
	 */
	public void removeCsPresentById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsPresent.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsPresentById", params);
		MemCache.deleteValue(CsPresent.class, id);
	}
	/**
	 * 根据条件更新礼品赠品
	 * @param values
	 * @param params
	 */
	public void updateCsPresentByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsPresentCount(params)>255){
			MemCache.clearValue(CsPresent.class);
		}else{
			params.put("eval","csp_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsPresentEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsPresent.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsPresentBy", allParams);
		Trigger.on(CsPresent.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除礼品赠品
	 * @param params
	 */
	public void deleteCsPresentByConfirm(Map params){
		Trigger.on(CsPresent.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsPresentCount(params)>255){
			MemCache.clearValue(CsPresent.class);
		}else{
			params.put("eval","csp_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsPresentEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsPresent.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsPresentBy", params);
	}
	

}