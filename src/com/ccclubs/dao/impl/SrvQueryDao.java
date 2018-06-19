package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ISrvQueryDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvQuery;
import com.lazy3q.web.helper.$;

/**
 * 表单查询的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class SrvQueryDao extends SqlMapClientDaoSupport implements ISrvQueryDao
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
	private SrvQuery custom(SrvQuery params){
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
			"sqName"			
			,"sqParams"			
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
	 * 获取所有表单查询
	 * @return
	 */	
	public List<SrvQuery> getSrvQueryList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<SrvQuery> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject","sqUpdateTime","sqUpdateTimeStart","sqUpdateTimeEnd","sqAddTime","sqAddTimeStart","sqAddTimeEnd"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(SrvQuery.M.class, md5);
			List caches = MemCache.getObject(SrvQuery.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						SrvQuery cacheObject = MemCache.getObject(SrvQuery.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(SrvQuery.M.class, md5);
							MemCache.deleteValue(SrvQuery.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(SrvQuery.M.class, md5);
					MemCache.deleteValue(SrvQuery.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getSrvQueryList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(SrvQuery srvQuery:list){
				caches.add(srvQuery.getSqId());
				MemCache.setObject(SrvQuery.class,srvQuery.getSqId(), srvQuery);
			}
			MemCache.setObject(SrvQuery.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(SrvQuery.M.class, md5, json, MemCache.defaultSeconds());
		}		
		
		return list;
	}
	
	
	/**
	 * 获取表单查询统计
	 * @return
	 */
	public List getSrvQueryStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getSrvQueryStats", params);
	}

	/**
	 * 获取表单查询分页
	 * @return
	 */
	public Page getSrvQueryPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getSrvQueryCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getSrvQueryPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取表单查询数量
	 * @return
	 */	
	public Long getSrvQueryCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getSrvQueryEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取表单查询自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getSrvQueryEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getSrvQueryEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取表单查询
	 * @param params
	 * @return
	 */
	public SrvQuery getSrvQuery(Map params)
	{		
		params = custom(params);
			
		List<SrvQuery> list=this.getSrvQueryList(params,1);
		SrvQuery srvQuery = list.isEmpty()?null:list.get(0);
		
		return srvQuery;
	}
		
	/**
	 * 根据ID取表单查询
	 * @param id
	 * @return
	 */
	public SrvQuery getSrvQueryById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		SrvQuery srvQuery = MemCache.getObject(SrvQuery.class, id);
		if(srvQuery!=null)
			return srvQuery;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		srvQuery = (SrvQuery) this.getSqlMapClientTemplate().queryForObject("getSrvQueryById",params);
		if(srvQuery!=null)
			MemCache.setValue(SrvQuery.class,id, srvQuery.getKeyValue());
		if(srvQuery!=null)
			MemCache.setObject(SrvQuery.class,id, srvQuery);
		return srvQuery;
	}
	
	/**
	 * 根据ID取表单查询(不缓存)
	 * @param id
	 * @return
	 */
	public SrvQuery GetSrvQueryById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (SrvQuery) this.getSqlMapClientTemplate().queryForObject("getSrvQueryById",params);
	}
	

	/**
	 * 保存表单查询
	 * @param srvQuery
	 */
	public SrvQuery saveSrvQuery(SrvQuery srvQuery)
	{
		srvQuery = custom(srvQuery);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveSrvQuery", srvQuery));
		srvQuery = this.GetSrvQueryById(id);
		MemCache.setValue(SrvQuery.class,id, srvQuery.getKeyValue());
		MemCache.setObject(SrvQuery.class,id,srvQuery);
		MemCache.clearObject(SrvQuery.M.class);
		MemCache.clearValue(SrvQuery.M.class);
		return Trigger.on(SrvQuery.class,Trigger.SAVE,srvQuery);	
	}

	

	/**
	 * 更新表单查询
	 * @param srvQuery
	 */
	public void updateSrvQuery(SrvQuery srvQuery)
	{
		srvQuery = custom(srvQuery);
		this.getSqlMapClientTemplate().update("updateSrvQuery", srvQuery);
		srvQuery = this.GetSrvQueryById(srvQuery.getSqId());
		if(srvQuery!=null){
			MemCache.setValue(SrvQuery.class,srvQuery.getSqId(), srvQuery.getKeyValue());
			MemCache.setObject(SrvQuery.class,srvQuery.getSqId(),srvQuery);
		}
		MemCache.clearObject(SrvQuery.M.class);
		MemCache.clearValue(SrvQuery.M.class);
		Trigger.on(SrvQuery.class,Trigger.UPDATE,srvQuery);
	}
	/**
	 * 更新表单查询非空字段
	 * @param srvQuery
	 */
	public void updateSrvQuery$NotNull(SrvQuery srvQuery)
	{
		srvQuery = custom(srvQuery);
		this.getSqlMapClientTemplate().update("updateSrvQuery$NotNull", srvQuery);
		srvQuery = this.GetSrvQueryById(srvQuery.getSqId());
		if(srvQuery!=null){
			MemCache.setValue(SrvQuery.class,srvQuery.getSqId(), srvQuery.getKeyValue());
			MemCache.setObject(SrvQuery.class,srvQuery.getSqId(),srvQuery);
		}
		MemCache.clearObject(SrvQuery.M.class);
		MemCache.clearValue(SrvQuery.M.class);
		Trigger.on(SrvQuery.class,Trigger.UPDATE,srvQuery);
	}

	/**
	 * 根据ID删除一个表单查询
	 * @param id
	 */
	public void deleteSrvQueryById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(SrvQuery.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteSrvQueryById", params);
		MemCache.deleteValue(SrvQuery.class, id);
		MemCache.deleteObject(SrvQuery.class, id);
		MemCache.clearObject(SrvQuery.M.class);
		MemCache.clearValue(SrvQuery.M.class);
	}
	/**
	 * 根据ID逻辑删除表单查询 
	 * @param id
	 * @return
	 */
	public void removeSrvQueryById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(SrvQuery.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeSrvQueryById", params);
		MemCache.deleteValue(SrvQuery.class, id);
		MemCache.deleteObject(SrvQuery.class, id);
		MemCache.clearObject(SrvQuery.M.class);
		MemCache.clearValue(SrvQuery.M.class);
	}
	/**
	 * 根据条件更新表单查询
	 * @param values
	 * @param params
	 */
	public void updateSrvQueryByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getSrvQueryCount(params)>255){
			MemCache.clearValue(SrvQuery.class);
			MemCache.clearObject(SrvQuery.class);
		}else{
			params.put("eval","sq_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getSrvQueryEval", params);
			for(Map map:result){
				MemCache.deleteValue(SrvQuery.class, map.get("eval"));
				MemCache.deleteObject(SrvQuery.class, map.get("eval"));
			}
		}
		MemCache.clearObject(SrvQuery.M.class);
		MemCache.clearValue(SrvQuery.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateSrvQueryBy", allParams);
		Trigger.on(SrvQuery.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除表单查询
	 * @param params
	 */
	public void deleteSrvQueryByConfirm(Map params){
		Trigger.on(SrvQuery.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getSrvQueryCount(params)>255){
			MemCache.clearValue(SrvQuery.class);
			MemCache.clearObject(SrvQuery.class);
		}else{
			params.put("eval","sq_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getSrvQueryEval", params);
			for(Map map:result){
				MemCache.deleteValue(SrvQuery.class, map.get("eval"));
				MemCache.deleteObject(SrvQuery.class, map.get("eval"));
			}
		}
		MemCache.clearObject(SrvQuery.M.class);
		MemCache.clearValue(SrvQuery.M.class);
		
		int rows = this.getSqlMapClientTemplate().update("deleteSrvQueryBy", params);
	}
	

}