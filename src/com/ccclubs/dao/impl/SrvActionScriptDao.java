package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ISrvActionScriptDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvActionScript;
import com.lazy3q.web.helper.$;

/**
 * 后台事务脚本的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class SrvActionScriptDao extends SqlMapClientDaoSupport implements ISrvActionScriptDao
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
	private SrvActionScript custom(SrvActionScript params){
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
			"ssCode"			
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
	 * 获取所有后台事务脚本
	 * @return
	 */	
	public List<SrvActionScript> getSrvActionScriptList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<SrvActionScript> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getSrvActionScriptList", params);
		
		return list;
	}
	
	
	/**
	 * 获取后台事务脚本统计
	 * @return
	 */
	public List getSrvActionScriptStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getSrvActionScriptStats", params);
	}

	/**
	 * 获取后台事务脚本分页
	 * @return
	 */
	public Page getSrvActionScriptPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getSrvActionScriptCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getSrvActionScriptPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取后台事务脚本数量
	 * @return
	 */	
	public Long getSrvActionScriptCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getSrvActionScriptEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取后台事务脚本自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getSrvActionScriptEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getSrvActionScriptEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取后台事务脚本
	 * @param params
	 * @return
	 */
	public SrvActionScript getSrvActionScript(Map params)
	{		
		params = custom(params);
			
		List<SrvActionScript> list=this.getSrvActionScriptList(params,1);
		SrvActionScript srvActionScript = list.isEmpty()?null:list.get(0);
		
		return srvActionScript;
	}
		
	/**
	 * 根据ID取后台事务脚本
	 * @param id
	 * @return
	 */
	public SrvActionScript getSrvActionScriptById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		SrvActionScript srvActionScript = null;
		if(srvActionScript!=null)
			return srvActionScript;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		srvActionScript = (SrvActionScript) this.getSqlMapClientTemplate().queryForObject("getSrvActionScriptById",params);
		if(srvActionScript!=null)
			MemCache.setValue(SrvActionScript.class,id, srvActionScript.getKeyValue());
		return srvActionScript;
	}
	
	/**
	 * 根据ID取后台事务脚本(不缓存)
	 * @param id
	 * @return
	 */
	public SrvActionScript GetSrvActionScriptById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (SrvActionScript) this.getSqlMapClientTemplate().queryForObject("getSrvActionScriptById",params);
	}
	

	/**
	 * 保存后台事务脚本
	 * @param srvActionScript
	 */
	public SrvActionScript saveSrvActionScript(SrvActionScript srvActionScript)
	{
		srvActionScript = custom(srvActionScript);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveSrvActionScript", srvActionScript));
		srvActionScript = this.GetSrvActionScriptById(id);
		MemCache.setValue(SrvActionScript.class,id, srvActionScript.getKeyValue());
		return Trigger.on(SrvActionScript.class,Trigger.SAVE,srvActionScript);	
	}

	

	/**
	 * 更新后台事务脚本
	 * @param srvActionScript
	 */
	public void updateSrvActionScript(SrvActionScript srvActionScript)
	{
		srvActionScript = custom(srvActionScript);
		this.getSqlMapClientTemplate().update("updateSrvActionScript", srvActionScript);
		srvActionScript = this.GetSrvActionScriptById(srvActionScript.getSsId());
		if(srvActionScript!=null){
			MemCache.setValue(SrvActionScript.class,srvActionScript.getSsId(), srvActionScript.getKeyValue());
		}
		Trigger.on(SrvActionScript.class,Trigger.UPDATE,srvActionScript);
	}
	/**
	 * 更新后台事务脚本非空字段
	 * @param srvActionScript
	 */
	public void updateSrvActionScript$NotNull(SrvActionScript srvActionScript)
	{
		srvActionScript = custom(srvActionScript);
		this.getSqlMapClientTemplate().update("updateSrvActionScript$NotNull", srvActionScript);
		srvActionScript = this.GetSrvActionScriptById(srvActionScript.getSsId());
		if(srvActionScript!=null){
			MemCache.setValue(SrvActionScript.class,srvActionScript.getSsId(), srvActionScript.getKeyValue());
		}
		Trigger.on(SrvActionScript.class,Trigger.UPDATE,srvActionScript);
	}

	/**
	 * 根据ID删除一个后台事务脚本
	 * @param id
	 */
	public void deleteSrvActionScriptById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(SrvActionScript.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteSrvActionScriptById", params);
		MemCache.deleteValue(SrvActionScript.class, id);
	}
	/**
	 * 根据ID逻辑删除后台事务脚本 
	 * @param id
	 * @return
	 */
	public void removeSrvActionScriptById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(SrvActionScript.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeSrvActionScriptById", params);
		MemCache.deleteValue(SrvActionScript.class, id);
	}
	/**
	 * 根据条件更新后台事务脚本
	 * @param values
	 * @param params
	 */
	public void updateSrvActionScriptByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getSrvActionScriptCount(params)>255){
			MemCache.clearValue(SrvActionScript.class);
		}else{
			params.put("eval","ss_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getSrvActionScriptEval", params);
			for(Map map:result){
				MemCache.deleteValue(SrvActionScript.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateSrvActionScriptBy", allParams);
		Trigger.on(SrvActionScript.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除后台事务脚本
	 * @param params
	 */
	public void deleteSrvActionScriptByConfirm(Map params){
		Trigger.on(SrvActionScript.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getSrvActionScriptCount(params)>255){
			MemCache.clearValue(SrvActionScript.class);
		}else{
			params.put("eval","ss_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getSrvActionScriptEval", params);
			for(Map map:result){
				MemCache.deleteValue(SrvActionScript.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteSrvActionScriptBy", params);
	}
	

}