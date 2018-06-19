package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsPropagateDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsPropagate;
import com.lazy3q.web.helper.$;

/**
 * 传播内容的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsPropagateDao extends SqlMapClientDaoSupport implements ICsPropagateDao
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
	private CsPropagate custom(CsPropagate params){
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
			"cspTitle"			
			,"cspContent"			
			,"cspHtml"			
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
	 * 获取所有传播内容
	 * @return
	 */	
	public List<CsPropagate> getCsPropagateList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsPropagate> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsPropagateList", params);
		
		return list;
	}
	
	
	/**
	 * 获取传播内容统计
	 * @return
	 */
	public List getCsPropagateStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsPropagateStats", params);
	}

	/**
	 * 获取传播内容分页
	 * @return
	 */
	public Page getCsPropagatePage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsPropagateCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsPropagatePage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取传播内容数量
	 * @return
	 */	
	public Long getCsPropagateCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsPropagateEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取传播内容自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsPropagateEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsPropagateEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取传播内容
	 * @param params
	 * @return
	 */
	public CsPropagate getCsPropagate(Map params)
	{		
		params = custom(params);
			
		List<CsPropagate> list=this.getCsPropagateList(params,1);
		CsPropagate csPropagate = list.isEmpty()?null:list.get(0);
		
		return csPropagate;
	}
		
	/**
	 * 根据ID取传播内容
	 * @param id
	 * @return
	 */
	public CsPropagate getCsPropagateById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsPropagate csPropagate = null;
		if(csPropagate!=null)
			return csPropagate;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csPropagate = (CsPropagate) this.getSqlMapClientTemplate().queryForObject("getCsPropagateById",params);
		if(csPropagate!=null)
			MemCache.setValue(CsPropagate.class,id, csPropagate.getKeyValue());
		return csPropagate;
	}
	
	/**
	 * 根据ID取传播内容(不缓存)
	 * @param id
	 * @return
	 */
	public CsPropagate GetCsPropagateById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsPropagate) this.getSqlMapClientTemplate().queryForObject("getCsPropagateById",params);
	}
	

	/**
	 * 保存传播内容
	 * @param csPropagate
	 */
	public CsPropagate saveCsPropagate(CsPropagate csPropagate)
	{
		csPropagate = custom(csPropagate);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsPropagate", csPropagate));
		csPropagate = this.GetCsPropagateById(id);
		MemCache.setValue(CsPropagate.class,id, csPropagate.getKeyValue());
		return Trigger.on(CsPropagate.class,Trigger.SAVE,csPropagate);	
	}

	

	/**
	 * 更新传播内容
	 * @param csPropagate
	 */
	public void updateCsPropagate(CsPropagate csPropagate)
	{
		csPropagate = custom(csPropagate);
		this.getSqlMapClientTemplate().update("updateCsPropagate", csPropagate);
		csPropagate = this.GetCsPropagateById(csPropagate.getCspId());
		if(csPropagate!=null){
			MemCache.setValue(CsPropagate.class,csPropagate.getCspId(), csPropagate.getKeyValue());
		}
		Trigger.on(CsPropagate.class,Trigger.UPDATE,csPropagate);
	}
	/**
	 * 更新传播内容非空字段
	 * @param csPropagate
	 */
	public void updateCsPropagate$NotNull(CsPropagate csPropagate)
	{
		csPropagate = custom(csPropagate);
		this.getSqlMapClientTemplate().update("updateCsPropagate$NotNull", csPropagate);
		csPropagate = this.GetCsPropagateById(csPropagate.getCspId());
		if(csPropagate!=null){
			MemCache.setValue(CsPropagate.class,csPropagate.getCspId(), csPropagate.getKeyValue());
		}
		Trigger.on(CsPropagate.class,Trigger.UPDATE,csPropagate);
	}

	/**
	 * 根据ID删除一个传播内容
	 * @param id
	 */
	public void deleteCsPropagateById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsPropagate.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsPropagateById", params);
		MemCache.deleteValue(CsPropagate.class, id);
	}
	/**
	 * 根据ID逻辑删除传播内容 
	 * @param id
	 * @return
	 */
	public void removeCsPropagateById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsPropagate.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsPropagateById", params);
		MemCache.deleteValue(CsPropagate.class, id);
	}
	/**
	 * 根据条件更新传播内容
	 * @param values
	 * @param params
	 */
	public void updateCsPropagateByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsPropagateCount(params)>255){
			MemCache.clearValue(CsPropagate.class);
		}else{
			params.put("eval","csp_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsPropagateEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsPropagate.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsPropagateBy", allParams);
		Trigger.on(CsPropagate.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除传播内容
	 * @param params
	 */
	public void deleteCsPropagateByConfirm(Map params){
		Trigger.on(CsPropagate.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsPropagateCount(params)>255){
			MemCache.clearValue(CsPropagate.class);
		}else{
			params.put("eval","csp_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsPropagateEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsPropagate.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsPropagateBy", params);
	}
	

}