package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ISrvHostDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvHost;
import com.lazy3q.web.helper.$;

/**
 * 运营城市的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class SrvHostDao extends SqlMapClientDaoSupport implements ISrvHostDao
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
	private SrvHost custom(SrvHost params){
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
	 * 获取所有运营城市
	 * @return
	 */	
	public List<SrvHost> getSrvHostList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<SrvHost> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(SrvHost.M.class, md5);
			List caches = MemCache.getObject(SrvHost.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						SrvHost cacheObject = MemCache.getObject(SrvHost.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(SrvHost.M.class, md5);
							MemCache.deleteValue(SrvHost.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(SrvHost.M.class, md5);
					MemCache.deleteValue(SrvHost.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getSrvHostList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(SrvHost srvHost:list){
				caches.add(srvHost.getShId());
				MemCache.setObject(SrvHost.class,srvHost.getShId(), srvHost);
			}
			MemCache.setObject(SrvHost.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(SrvHost.M.class, md5, json, MemCache.defaultSeconds());
		}		
		
		return list;
	}
	
	
	/**
	 * 获取运营城市统计
	 * @return
	 */
	public List getSrvHostStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getSrvHostStats", params);
	}

	/**
	 * 获取运营城市分页
	 * @return
	 */
	public Page getSrvHostPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getSrvHostCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getSrvHostPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取运营城市数量
	 * @return
	 */	
	public Long getSrvHostCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getSrvHostEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取运营城市自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getSrvHostEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getSrvHostEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取运营城市
	 * @param params
	 * @return
	 */
	public SrvHost getSrvHost(Map params)
	{		
		params = custom(params);
			
		List<SrvHost> list=this.getSrvHostList(params,1);
		SrvHost srvHost = list.isEmpty()?null:list.get(0);
		
		return srvHost;
	}
		
	/**
	 * 根据ID取运营城市
	 * @param id
	 * @return
	 */
	public SrvHost getSrvHostById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		SrvHost srvHost = MemCache.getObject(SrvHost.class, id);
		if(srvHost!=null)
			return srvHost;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		srvHost = (SrvHost) this.getSqlMapClientTemplate().queryForObject("getSrvHostById",params);
		if(srvHost!=null)
			MemCache.setValue(SrvHost.class,id, srvHost.getKeyValue());
		if(srvHost!=null)
			MemCache.setObject(SrvHost.class,id, srvHost);
		return srvHost;
	}
	
	/**
	 * 根据ID取运营城市(不缓存)
	 * @param id
	 * @return
	 */
	public SrvHost GetSrvHostById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (SrvHost) this.getSqlMapClientTemplate().queryForObject("getSrvHostById",params);
	}
	

	/**
	 * 保存运营城市
	 * @param srvHost
	 */
	public SrvHost saveSrvHost(SrvHost srvHost)
	{
		srvHost = custom(srvHost);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveSrvHost", srvHost));
		srvHost = this.GetSrvHostById(id);
		MemCache.setValue(SrvHost.class,id, srvHost.getKeyValue());
		MemCache.setObject(SrvHost.class,id,srvHost);
		MemCache.clearObject(SrvHost.M.class);
		MemCache.clearValue(SrvHost.M.class);
		return Trigger.on(SrvHost.class,Trigger.SAVE,srvHost);	
	}

	

	/**
	 * 更新运营城市
	 * @param srvHost
	 */
	public void updateSrvHost(SrvHost srvHost)
	{
		srvHost = custom(srvHost);
		this.getSqlMapClientTemplate().update("updateSrvHost", srvHost);
		srvHost = this.GetSrvHostById(srvHost.getShId());
		if(srvHost!=null){
			MemCache.setValue(SrvHost.class,srvHost.getShId(), srvHost.getKeyValue());
			MemCache.setObject(SrvHost.class,srvHost.getShId(),srvHost);
		}
		MemCache.clearObject(SrvHost.M.class);
		MemCache.clearValue(SrvHost.M.class);
		Trigger.on(SrvHost.class,Trigger.UPDATE,srvHost);
	}
	/**
	 * 更新运营城市非空字段
	 * @param srvHost
	 */
	public void updateSrvHost$NotNull(SrvHost srvHost)
	{
		srvHost = custom(srvHost);
		this.getSqlMapClientTemplate().update("updateSrvHost$NotNull", srvHost);
		srvHost = this.GetSrvHostById(srvHost.getShId());
		if(srvHost!=null){
			MemCache.setValue(SrvHost.class,srvHost.getShId(), srvHost.getKeyValue());
			MemCache.setObject(SrvHost.class,srvHost.getShId(),srvHost);
		}
		MemCache.clearObject(SrvHost.M.class);
		MemCache.clearValue(SrvHost.M.class);
		Trigger.on(SrvHost.class,Trigger.UPDATE,srvHost);
	}

	/**
	 * 根据ID删除一个运营城市
	 * @param id
	 */
	public void deleteSrvHostById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(SrvHost.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteSrvHostById", params);
		MemCache.deleteValue(SrvHost.class, id);
		MemCache.deleteObject(SrvHost.class, id);
		MemCache.clearObject(SrvHost.M.class);
		MemCache.clearValue(SrvHost.M.class);
	}
	/**
	 * 根据ID逻辑删除运营城市 
	 * @param id
	 * @return
	 */
	public void removeSrvHostById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(SrvHost.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeSrvHostById", params);
		MemCache.deleteValue(SrvHost.class, id);
		MemCache.deleteObject(SrvHost.class, id);
		MemCache.clearObject(SrvHost.M.class);
		MemCache.clearValue(SrvHost.M.class);
	}
	/**
	 * 根据条件更新运营城市
	 * @param values
	 * @param params
	 */
	public void updateSrvHostByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getSrvHostCount(params)>255){
			MemCache.clearValue(SrvHost.class);
			MemCache.clearObject(SrvHost.class);
		}else{
			params.put("eval","sh_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getSrvHostEval", params);
			for(Map map:result){
				MemCache.deleteValue(SrvHost.class, map.get("eval"));
				MemCache.deleteObject(SrvHost.class, map.get("eval"));
			}
		}
		MemCache.clearObject(SrvHost.M.class);
		MemCache.clearValue(SrvHost.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateSrvHostBy", allParams);
		Trigger.on(SrvHost.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除运营城市
	 * @param params
	 */
	public void deleteSrvHostByConfirm(Map params){
		Trigger.on(SrvHost.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getSrvHostCount(params)>255){
			MemCache.clearValue(SrvHost.class);
			MemCache.clearObject(SrvHost.class);
		}else{
			params.put("eval","sh_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getSrvHostEval", params);
			for(Map map:result){
				MemCache.deleteValue(SrvHost.class, map.get("eval"));
				MemCache.deleteObject(SrvHost.class, map.get("eval"));
			}
		}
		MemCache.clearObject(SrvHost.M.class);
		MemCache.clearValue(SrvHost.M.class);
		
		int rows = this.getSqlMapClientTemplate().update("deleteSrvHostBy", params);
	}
	

}