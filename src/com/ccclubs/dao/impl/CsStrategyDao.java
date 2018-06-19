package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsStrategyDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsStrategy;
import com.lazy3q.web.helper.$;

/**
 * 品牌战略的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsStrategyDao extends SqlMapClientDaoSupport implements ICsStrategyDao
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
	private CsStrategy custom(CsStrategy params){
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
			"cssTitle"			
			,"cssContent"			
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
	 * 获取所有品牌战略
	 * @return
	 */	
	public List<CsStrategy> getCsStrategyList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsStrategy> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsStrategyList", params);
		
		return list;
	}
	
	
	/**
	 * 获取品牌战略统计
	 * @return
	 */
	public List getCsStrategyStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsStrategyStats", params);
	}

	/**
	 * 获取品牌战略分页
	 * @return
	 */
	public Page getCsStrategyPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsStrategyCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsStrategyPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取品牌战略数量
	 * @return
	 */	
	public Long getCsStrategyCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsStrategyEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取品牌战略自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsStrategyEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsStrategyEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取品牌战略
	 * @param params
	 * @return
	 */
	public CsStrategy getCsStrategy(Map params)
	{		
		params = custom(params);
			
		List<CsStrategy> list=this.getCsStrategyList(params,1);
		CsStrategy csStrategy = list.isEmpty()?null:list.get(0);
		
		return csStrategy;
	}
		
	/**
	 * 根据ID取品牌战略
	 * @param id
	 * @return
	 */
	public CsStrategy getCsStrategyById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsStrategy csStrategy = null;
		if(csStrategy!=null)
			return csStrategy;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csStrategy = (CsStrategy) this.getSqlMapClientTemplate().queryForObject("getCsStrategyById",params);
		if(csStrategy!=null)
			MemCache.setValue(CsStrategy.class,id, csStrategy.getKeyValue());
		return csStrategy;
	}
	
	/**
	 * 根据ID取品牌战略(不缓存)
	 * @param id
	 * @return
	 */
	public CsStrategy GetCsStrategyById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsStrategy) this.getSqlMapClientTemplate().queryForObject("getCsStrategyById",params);
	}
	

	/**
	 * 保存品牌战略
	 * @param csStrategy
	 */
	public CsStrategy saveCsStrategy(CsStrategy csStrategy)
	{
		csStrategy = custom(csStrategy);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsStrategy", csStrategy));
		csStrategy = this.GetCsStrategyById(id);
		MemCache.setValue(CsStrategy.class,id, csStrategy.getKeyValue());
		return Trigger.on(CsStrategy.class,Trigger.SAVE,csStrategy);	
	}

	

	/**
	 * 更新品牌战略
	 * @param csStrategy
	 */
	public void updateCsStrategy(CsStrategy csStrategy)
	{
		csStrategy = custom(csStrategy);
		this.getSqlMapClientTemplate().update("updateCsStrategy", csStrategy);
		csStrategy = this.GetCsStrategyById(csStrategy.getCssId());
		if(csStrategy!=null){
			MemCache.setValue(CsStrategy.class,csStrategy.getCssId(), csStrategy.getKeyValue());
		}
		Trigger.on(CsStrategy.class,Trigger.UPDATE,csStrategy);
	}
	/**
	 * 更新品牌战略非空字段
	 * @param csStrategy
	 */
	public void updateCsStrategy$NotNull(CsStrategy csStrategy)
	{
		csStrategy = custom(csStrategy);
		this.getSqlMapClientTemplate().update("updateCsStrategy$NotNull", csStrategy);
		csStrategy = this.GetCsStrategyById(csStrategy.getCssId());
		if(csStrategy!=null){
			MemCache.setValue(CsStrategy.class,csStrategy.getCssId(), csStrategy.getKeyValue());
		}
		Trigger.on(CsStrategy.class,Trigger.UPDATE,csStrategy);
	}

	/**
	 * 根据ID删除一个品牌战略
	 * @param id
	 */
	public void deleteCsStrategyById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsStrategy.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsStrategyById", params);
		MemCache.deleteValue(CsStrategy.class, id);
	}
	/**
	 * 根据ID逻辑删除品牌战略 
	 * @param id
	 * @return
	 */
	public void removeCsStrategyById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsStrategy.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsStrategyById", params);
		MemCache.deleteValue(CsStrategy.class, id);
	}
	/**
	 * 根据条件更新品牌战略
	 * @param values
	 * @param params
	 */
	public void updateCsStrategyByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsStrategyCount(params)>255){
			MemCache.clearValue(CsStrategy.class);
		}else{
			params.put("eval","css_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsStrategyEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsStrategy.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsStrategyBy", allParams);
		Trigger.on(CsStrategy.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除品牌战略
	 * @param params
	 */
	public void deleteCsStrategyByConfirm(Map params){
		Trigger.on(CsStrategy.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsStrategyCount(params)>255){
			MemCache.clearValue(CsStrategy.class);
		}else{
			params.put("eval","css_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsStrategyEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsStrategy.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsStrategyBy", params);
	}
	

}