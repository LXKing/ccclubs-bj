package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ISrvApiClientDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvApiClient;
import com.lazy3q.web.helper.$;

/**
 * API客户端的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class SrvApiClientDao extends SqlMapClientDaoSupport implements ISrvApiClientDao
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
	private SrvApiClient custom(SrvApiClient params){
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
			"sacDesc"			
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
	 * 获取所有API客户端
	 * @return
	 */	
	public List<SrvApiClient> getSrvApiClientList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<SrvApiClient> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject","sacUpdateTime","sacUpdateTimeStart","sacUpdateTimeEnd","sacAddTime","sacAddTimeStart","sacAddTimeEnd"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(SrvApiClient.M.class, md5);
			List caches = MemCache.getObject(SrvApiClient.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						SrvApiClient cacheObject = MemCache.getObject(SrvApiClient.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(SrvApiClient.M.class, md5);
							MemCache.deleteValue(SrvApiClient.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(SrvApiClient.M.class, md5);
					MemCache.deleteValue(SrvApiClient.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getSrvApiClientList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(SrvApiClient srvApiClient:list){
				caches.add(srvApiClient.getSacId());
				MemCache.setObject(SrvApiClient.class,srvApiClient.getSacId(), srvApiClient);
			}
			MemCache.setObject(SrvApiClient.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(SrvApiClient.M.class, md5, json, MemCache.defaultSeconds());
		}		
		
		return list;
	}
	
	
	/**
	 * 获取API客户端统计
	 * @return
	 */
	public List getSrvApiClientStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getSrvApiClientStats", params);
	}

	/**
	 * 获取API客户端分页
	 * @return
	 */
	public Page getSrvApiClientPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getSrvApiClientCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getSrvApiClientPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取API客户端数量
	 * @return
	 */	
	public Long getSrvApiClientCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getSrvApiClientEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取API客户端自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getSrvApiClientEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getSrvApiClientEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取API客户端
	 * @param params
	 * @return
	 */
	public SrvApiClient getSrvApiClient(Map params)
	{		
		params = custom(params);
			
		List<SrvApiClient> list=this.getSrvApiClientList(params,1);
		SrvApiClient srvApiClient = list.isEmpty()?null:list.get(0);
		
		return srvApiClient;
	}
		
	/**
	 * 根据ID取API客户端
	 * @param id
	 * @return
	 */
	public SrvApiClient getSrvApiClientById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		SrvApiClient srvApiClient = MemCache.getObject(SrvApiClient.class, id);
		if(srvApiClient!=null)
			return srvApiClient;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		srvApiClient = (SrvApiClient) this.getSqlMapClientTemplate().queryForObject("getSrvApiClientById",params);
		if(srvApiClient!=null)
			MemCache.setValue(SrvApiClient.class,id, srvApiClient.getKeyValue());
		if(srvApiClient!=null)
			MemCache.setObject(SrvApiClient.class,id, srvApiClient);
		return srvApiClient;
	}
	
	/**
	 * 根据ID取API客户端(不缓存)
	 * @param id
	 * @return
	 */
	public SrvApiClient GetSrvApiClientById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (SrvApiClient) this.getSqlMapClientTemplate().queryForObject("getSrvApiClientById",params);
	}
	

	/**
	 * 保存API客户端
	 * @param srvApiClient
	 */
	public SrvApiClient saveSrvApiClient(SrvApiClient srvApiClient)
	{
		srvApiClient = custom(srvApiClient);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveSrvApiClient", srvApiClient));
		srvApiClient = this.GetSrvApiClientById(id);
		MemCache.setValue(SrvApiClient.class,id, srvApiClient.getKeyValue());
		MemCache.setObject(SrvApiClient.class,id,srvApiClient);
		MemCache.clearObject(SrvApiClient.M.class);
		MemCache.clearValue(SrvApiClient.M.class);
		return Trigger.on(SrvApiClient.class,Trigger.SAVE,srvApiClient);	
	}

	

	/**
	 * 更新API客户端
	 * @param srvApiClient
	 */
	public void updateSrvApiClient(SrvApiClient srvApiClient)
	{
		srvApiClient = custom(srvApiClient);
		this.getSqlMapClientTemplate().update("updateSrvApiClient", srvApiClient);
		srvApiClient = this.GetSrvApiClientById(srvApiClient.getSacId());
		if(srvApiClient!=null){
			MemCache.setValue(SrvApiClient.class,srvApiClient.getSacId(), srvApiClient.getKeyValue());
			MemCache.setObject(SrvApiClient.class,srvApiClient.getSacId(),srvApiClient);
		}
		MemCache.clearObject(SrvApiClient.M.class);
		MemCache.clearValue(SrvApiClient.M.class);
		Trigger.on(SrvApiClient.class,Trigger.UPDATE,srvApiClient);
	}
	/**
	 * 更新API客户端非空字段
	 * @param srvApiClient
	 */
	public void updateSrvApiClient$NotNull(SrvApiClient srvApiClient)
	{
		srvApiClient = custom(srvApiClient);
		this.getSqlMapClientTemplate().update("updateSrvApiClient$NotNull", srvApiClient);
		srvApiClient = this.GetSrvApiClientById(srvApiClient.getSacId());
		if(srvApiClient!=null){
			MemCache.setValue(SrvApiClient.class,srvApiClient.getSacId(), srvApiClient.getKeyValue());
			MemCache.setObject(SrvApiClient.class,srvApiClient.getSacId(),srvApiClient);
		}
		MemCache.clearObject(SrvApiClient.M.class);
		MemCache.clearValue(SrvApiClient.M.class);
		Trigger.on(SrvApiClient.class,Trigger.UPDATE,srvApiClient);
	}

	/**
	 * 根据ID删除一个API客户端
	 * @param id
	 */
	public void deleteSrvApiClientById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(SrvApiClient.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteSrvApiClientById", params);
		MemCache.deleteValue(SrvApiClient.class, id);
		MemCache.deleteObject(SrvApiClient.class, id);
		MemCache.clearObject(SrvApiClient.M.class);
		MemCache.clearValue(SrvApiClient.M.class);
	}
	/**
	 * 根据ID逻辑删除API客户端 
	 * @param id
	 * @return
	 */
	public void removeSrvApiClientById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(SrvApiClient.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeSrvApiClientById", params);
		MemCache.deleteValue(SrvApiClient.class, id);
		MemCache.deleteObject(SrvApiClient.class, id);
		MemCache.clearObject(SrvApiClient.M.class);
		MemCache.clearValue(SrvApiClient.M.class);
	}
	/**
	 * 根据条件更新API客户端
	 * @param values
	 * @param params
	 */
	public void updateSrvApiClientByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getSrvApiClientCount(params)>255){
			MemCache.clearValue(SrvApiClient.class);
			MemCache.clearObject(SrvApiClient.class);
		}else{
			params.put("eval","sac_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getSrvApiClientEval", params);
			for(Map map:result){
				MemCache.deleteValue(SrvApiClient.class, map.get("eval"));
				MemCache.deleteObject(SrvApiClient.class, map.get("eval"));
			}
		}
		MemCache.clearObject(SrvApiClient.M.class);
		MemCache.clearValue(SrvApiClient.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateSrvApiClientBy", allParams);
		Trigger.on(SrvApiClient.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除API客户端
	 * @param params
	 */
	public void deleteSrvApiClientByConfirm(Map params){
		Trigger.on(SrvApiClient.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getSrvApiClientCount(params)>255){
			MemCache.clearValue(SrvApiClient.class);
			MemCache.clearObject(SrvApiClient.class);
		}else{
			params.put("eval","sac_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getSrvApiClientEval", params);
			for(Map map:result){
				MemCache.deleteValue(SrvApiClient.class, map.get("eval"));
				MemCache.deleteObject(SrvApiClient.class, map.get("eval"));
			}
		}
		MemCache.clearObject(SrvApiClient.M.class);
		MemCache.clearValue(SrvApiClient.M.class);
		
		int rows = this.getSqlMapClientTemplate().update("deleteSrvApiClientBy", params);
	}
	

}