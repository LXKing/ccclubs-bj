package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsTodoDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsTodo;
import com.lazy3q.web.helper.$;

/**
 * 待办事项的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsTodoDao extends SqlMapClientDaoSupport implements ICsTodoDao
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
	private CsTodo custom(CsTodo params){
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
			"cstTitle"			
			,"cstContent"			
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
	 * 获取所有待办事项
	 * @return
	 */	
	public List<CsTodo> getCsTodoList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsTodo> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsTodoList", params);
		
		return list;
	}
	
	
	/**
	 * 获取待办事项统计
	 * @return
	 */
	public List getCsTodoStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsTodoStats", params);
	}

	/**
	 * 获取待办事项分页
	 * @return
	 */
	public Page getCsTodoPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsTodoCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsTodoPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取待办事项数量
	 * @return
	 */	
	public Long getCsTodoCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsTodoEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取待办事项自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsTodoEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsTodoEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取待办事项
	 * @param params
	 * @return
	 */
	public CsTodo getCsTodo(Map params)
	{		
		params = custom(params);
			
		List<CsTodo> list=this.getCsTodoList(params,1);
		CsTodo csTodo = list.isEmpty()?null:list.get(0);
		
		return csTodo;
	}
		
	/**
	 * 根据ID取待办事项
	 * @param id
	 * @return
	 */
	public CsTodo getCsTodoById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsTodo csTodo = null;
		if(csTodo!=null)
			return csTodo;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csTodo = (CsTodo) this.getSqlMapClientTemplate().queryForObject("getCsTodoById",params);
		if(csTodo!=null)
			MemCache.setValue(CsTodo.class,id, csTodo.getKeyValue());
		return csTodo;
	}
	
	/**
	 * 根据ID取待办事项(不缓存)
	 * @param id
	 * @return
	 */
	public CsTodo GetCsTodoById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsTodo) this.getSqlMapClientTemplate().queryForObject("getCsTodoById",params);
	}
	

	/**
	 * 保存待办事项
	 * @param csTodo
	 */
	public CsTodo saveCsTodo(CsTodo csTodo)
	{
		csTodo = custom(csTodo);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsTodo", csTodo));
		csTodo = this.GetCsTodoById(id);
		MemCache.setValue(CsTodo.class,id, csTodo.getKeyValue());
		return Trigger.on(CsTodo.class,Trigger.SAVE,csTodo);	
	}

	

	/**
	 * 更新待办事项
	 * @param csTodo
	 */
	public void updateCsTodo(CsTodo csTodo)
	{
		csTodo = custom(csTodo);
		this.getSqlMapClientTemplate().update("updateCsTodo", csTodo);
		csTodo = this.GetCsTodoById(csTodo.getCstId());
		if(csTodo!=null){
			MemCache.setValue(CsTodo.class,csTodo.getCstId(), csTodo.getKeyValue());
		}
		Trigger.on(CsTodo.class,Trigger.UPDATE,csTodo);
	}
	/**
	 * 更新待办事项非空字段
	 * @param csTodo
	 */
	public void updateCsTodo$NotNull(CsTodo csTodo)
	{
		csTodo = custom(csTodo);
		this.getSqlMapClientTemplate().update("updateCsTodo$NotNull", csTodo);
		csTodo = this.GetCsTodoById(csTodo.getCstId());
		if(csTodo!=null){
			MemCache.setValue(CsTodo.class,csTodo.getCstId(), csTodo.getKeyValue());
		}
		Trigger.on(CsTodo.class,Trigger.UPDATE,csTodo);
	}

	/**
	 * 根据ID删除一个待办事项
	 * @param id
	 */
	public void deleteCsTodoById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsTodo.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsTodoById", params);
		MemCache.deleteValue(CsTodo.class, id);
	}
	/**
	 * 根据ID逻辑删除待办事项 
	 * @param id
	 * @return
	 */
	public void removeCsTodoById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsTodo.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsTodoById", params);
		MemCache.deleteValue(CsTodo.class, id);
	}
	/**
	 * 根据条件更新待办事项
	 * @param values
	 * @param params
	 */
	public void updateCsTodoByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsTodoCount(params)>255){
			MemCache.clearValue(CsTodo.class);
		}else{
			params.put("eval","cst_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsTodoEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsTodo.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsTodoBy", allParams);
		Trigger.on(CsTodo.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除待办事项
	 * @param params
	 */
	public void deleteCsTodoByConfirm(Map params){
		Trigger.on(CsTodo.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsTodoCount(params)>255){
			MemCache.clearValue(CsTodo.class);
		}else{
			params.put("eval","cst_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsTodoEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsTodo.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsTodoBy", params);
	}
	

}