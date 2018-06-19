package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ISrvApiDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvApi;
import com.lazy3q.web.helper.$;

/**
 * API接口的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class SrvApiDao extends SqlMapClientDaoSupport implements ISrvApiDao
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
	private SrvApi custom(SrvApi params){
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
			"saParams"			
			,"saReturnStruct"			
			,"saJavaCode"			
			,"saReturnFormat"			
			,"saError"			
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
	 * 获取所有API接口
	 * @return
	 */	
	public List<SrvApi> getSrvApiList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<SrvApi> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject","saUpdateTime","saUpdateTimeStart","saUpdateTimeEnd","saAddTime","saAddTimeStart","saAddTimeEnd"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(SrvApi.M.class, md5);
			List caches = MemCache.getObject(SrvApi.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						SrvApi cacheObject = MemCache.getObject(SrvApi.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(SrvApi.M.class, md5);
							MemCache.deleteValue(SrvApi.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(SrvApi.M.class, md5);
					MemCache.deleteValue(SrvApi.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getSrvApiList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(SrvApi srvApi:list){
				caches.add(srvApi.getSaId());
				MemCache.setObject(SrvApi.class,srvApi.getSaId(), srvApi);
			}
			MemCache.setObject(SrvApi.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(SrvApi.M.class, md5, json, MemCache.defaultSeconds());
		}		
		
		return list;
	}
	
	
	/**
	 * 获取API接口统计
	 * @return
	 */
	public List getSrvApiStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getSrvApiStats", params);
	}

	/**
	 * 获取API接口分页
	 * @return
	 */
	public Page getSrvApiPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getSrvApiCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getSrvApiPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取API接口数量
	 * @return
	 */	
	public Long getSrvApiCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getSrvApiEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取API接口自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getSrvApiEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getSrvApiEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取API接口
	 * @param params
	 * @return
	 */
	public SrvApi getSrvApi(Map params)
	{		
		params = custom(params);
			
		List<SrvApi> list=this.getSrvApiList(params,1);
		SrvApi srvApi = list.isEmpty()?null:list.get(0);
		
		return srvApi;
	}
		
	/**
	 * 根据ID取API接口
	 * @param id
	 * @return
	 */
	public SrvApi getSrvApiById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		SrvApi srvApi = MemCache.getObject(SrvApi.class, id);
		if(srvApi!=null)
			return srvApi;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		srvApi = (SrvApi) this.getSqlMapClientTemplate().queryForObject("getSrvApiById",params);
		if(srvApi!=null)
			MemCache.setValue(SrvApi.class,id, srvApi.getKeyValue());
		if(srvApi!=null)
			MemCache.setObject(SrvApi.class,id, srvApi);
		return srvApi;
	}
	
	/**
	 * 根据ID取API接口(不缓存)
	 * @param id
	 * @return
	 */
	public SrvApi GetSrvApiById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (SrvApi) this.getSqlMapClientTemplate().queryForObject("getSrvApiById",params);
	}
	

	/**
	 * 保存API接口
	 * @param srvApi
	 */
	public SrvApi saveSrvApi(SrvApi srvApi)
	{
		srvApi = custom(srvApi);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveSrvApi", srvApi));
		srvApi = this.GetSrvApiById(id);
		MemCache.setValue(SrvApi.class,id, srvApi.getKeyValue());
		MemCache.setObject(SrvApi.class,id,srvApi);
		MemCache.clearObject(SrvApi.M.class);
		MemCache.clearValue(SrvApi.M.class);
		return Trigger.on(SrvApi.class,Trigger.SAVE,srvApi);	
	}

	

	/**
	 * 更新API接口
	 * @param srvApi
	 */
	public void updateSrvApi(SrvApi srvApi)
	{
		srvApi = custom(srvApi);
		this.getSqlMapClientTemplate().update("updateSrvApi", srvApi);
		srvApi = this.GetSrvApiById(srvApi.getSaId());
		if(srvApi!=null){
			MemCache.setValue(SrvApi.class,srvApi.getSaId(), srvApi.getKeyValue());
			MemCache.setObject(SrvApi.class,srvApi.getSaId(),srvApi);
		}
		MemCache.clearObject(SrvApi.M.class);
		MemCache.clearValue(SrvApi.M.class);
		Trigger.on(SrvApi.class,Trigger.UPDATE,srvApi);
	}
	/**
	 * 更新API接口非空字段
	 * @param srvApi
	 */
	public void updateSrvApi$NotNull(SrvApi srvApi)
	{
		srvApi = custom(srvApi);
		this.getSqlMapClientTemplate().update("updateSrvApi$NotNull", srvApi);
		srvApi = this.GetSrvApiById(srvApi.getSaId());
		if(srvApi!=null){
			MemCache.setValue(SrvApi.class,srvApi.getSaId(), srvApi.getKeyValue());
			MemCache.setObject(SrvApi.class,srvApi.getSaId(),srvApi);
		}
		MemCache.clearObject(SrvApi.M.class);
		MemCache.clearValue(SrvApi.M.class);
		Trigger.on(SrvApi.class,Trigger.UPDATE,srvApi);
	}

	/**
	 * 根据ID删除一个API接口
	 * @param id
	 */
	public void deleteSrvApiById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(SrvApi.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteSrvApiById", params);
		MemCache.deleteValue(SrvApi.class, id);
		MemCache.deleteObject(SrvApi.class, id);
		MemCache.clearObject(SrvApi.M.class);
		MemCache.clearValue(SrvApi.M.class);
	}
	/**
	 * 根据ID逻辑删除API接口 
	 * @param id
	 * @return
	 */
	public void removeSrvApiById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(SrvApi.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeSrvApiById", params);
		MemCache.deleteValue(SrvApi.class, id);
		MemCache.deleteObject(SrvApi.class, id);
		MemCache.clearObject(SrvApi.M.class);
		MemCache.clearValue(SrvApi.M.class);
	}
	/**
	 * 根据条件更新API接口
	 * @param values
	 * @param params
	 */
	public void updateSrvApiByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getSrvApiCount(params)>255){
			MemCache.clearValue(SrvApi.class);
			MemCache.clearObject(SrvApi.class);
		}else{
			params.put("eval","sa_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getSrvApiEval", params);
			for(Map map:result){
				MemCache.deleteValue(SrvApi.class, map.get("eval"));
				MemCache.deleteObject(SrvApi.class, map.get("eval"));
			}
		}
		MemCache.clearObject(SrvApi.M.class);
		MemCache.clearValue(SrvApi.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateSrvApiBy", allParams);
		Trigger.on(SrvApi.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除API接口
	 * @param params
	 */
	public void deleteSrvApiByConfirm(Map params){
		Trigger.on(SrvApi.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getSrvApiCount(params)>255){
			MemCache.clearValue(SrvApi.class);
			MemCache.clearObject(SrvApi.class);
		}else{
			params.put("eval","sa_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getSrvApiEval", params);
			for(Map map:result){
				MemCache.deleteValue(SrvApi.class, map.get("eval"));
				MemCache.deleteObject(SrvApi.class, map.get("eval"));
			}
		}
		MemCache.clearObject(SrvApi.M.class);
		MemCache.clearValue(SrvApi.M.class);
		
		int rows = this.getSqlMapClientTemplate().update("deleteSrvApiBy", params);
	}
	

}