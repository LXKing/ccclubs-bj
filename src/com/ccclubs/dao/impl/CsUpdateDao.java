package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsUpdateDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsUpdate;
import com.lazy3q.web.helper.$;

/**
 * App更新的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsUpdateDao extends SqlMapClientDaoSupport implements ICsUpdateDao
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
	private CsUpdate custom(CsUpdate params){
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
			"csuDescript"			
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
	 * 获取所有App更新
	 * @return
	 */	
	public List<CsUpdate> getCsUpdateList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsUpdate> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject","csuUpdateTime","csuUpdateTimeStart","csuUpdateTimeEnd","csuAddTime","csuAddTimeStart","csuAddTimeEnd"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(CsUpdate.M.class, md5);
			List caches = MemCache.getObject(CsUpdate.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						CsUpdate cacheObject = MemCache.getObject(CsUpdate.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(CsUpdate.M.class, md5);
							MemCache.deleteValue(CsUpdate.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(CsUpdate.M.class, md5);
					MemCache.deleteValue(CsUpdate.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsUpdateList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(CsUpdate csUpdate:list){
				caches.add(csUpdate.getCsuId());
				MemCache.setObject(CsUpdate.class,csUpdate.getCsuId(), csUpdate);
			}
			MemCache.setObject(CsUpdate.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(CsUpdate.M.class, md5, json, MemCache.defaultSeconds());
		}		
		
		return list;
	}
	
	
	/**
	 * 获取App更新统计
	 * @return
	 */
	public List getCsUpdateStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsUpdateStats", params);
	}

	/**
	 * 获取App更新分页
	 * @return
	 */
	public Page getCsUpdatePage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsUpdateCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsUpdatePage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取App更新数量
	 * @return
	 */	
	public Long getCsUpdateCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsUpdateEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取App更新自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsUpdateEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsUpdateEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取App更新
	 * @param params
	 * @return
	 */
	public CsUpdate getCsUpdate(Map params)
	{		
		params = custom(params);
			
		List<CsUpdate> list=this.getCsUpdateList(params,1);
		CsUpdate csUpdate = list.isEmpty()?null:list.get(0);
		
		return csUpdate;
	}
		
	/**
	 * 根据ID取App更新
	 * @param id
	 * @return
	 */
	public CsUpdate getCsUpdateById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsUpdate csUpdate = MemCache.getObject(CsUpdate.class, id);
		if(csUpdate!=null)
			return csUpdate;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csUpdate = (CsUpdate) this.getSqlMapClientTemplate().queryForObject("getCsUpdateById",params);
		if(csUpdate!=null)
			MemCache.setValue(CsUpdate.class,id, csUpdate.getKeyValue());
		if(csUpdate!=null)
			MemCache.setObject(CsUpdate.class,id, csUpdate);
		return csUpdate;
	}
	
	/**
	 * 根据ID取App更新(不缓存)
	 * @param id
	 * @return
	 */
	public CsUpdate GetCsUpdateById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsUpdate) this.getSqlMapClientTemplate().queryForObject("getCsUpdateById",params);
	}
	

	/**
	 * 保存App更新
	 * @param csUpdate
	 */
	public CsUpdate saveCsUpdate(CsUpdate csUpdate)
	{
		csUpdate = custom(csUpdate);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsUpdate", csUpdate));
		csUpdate = this.GetCsUpdateById(id);
		MemCache.setValue(CsUpdate.class,id, csUpdate.getKeyValue());
		MemCache.setObject(CsUpdate.class,id,csUpdate);
		MemCache.clearObject(CsUpdate.M.class);
		MemCache.clearValue(CsUpdate.M.class);
		return Trigger.on(CsUpdate.class,Trigger.SAVE,csUpdate);	
	}

	

	/**
	 * 更新App更新
	 * @param csUpdate
	 */
	public void updateCsUpdate(CsUpdate csUpdate)
	{
		csUpdate = custom(csUpdate);
		this.getSqlMapClientTemplate().update("updateCsUpdate", csUpdate);
		csUpdate = this.GetCsUpdateById(csUpdate.getCsuId());
		if(csUpdate!=null){
			MemCache.setValue(CsUpdate.class,csUpdate.getCsuId(), csUpdate.getKeyValue());
			MemCache.setObject(CsUpdate.class,csUpdate.getCsuId(),csUpdate);
		}
		MemCache.clearObject(CsUpdate.M.class);
		MemCache.clearValue(CsUpdate.M.class);
		Trigger.on(CsUpdate.class,Trigger.UPDATE,csUpdate);
	}
	/**
	 * 更新App更新非空字段
	 * @param csUpdate
	 */
	public void updateCsUpdate$NotNull(CsUpdate csUpdate)
	{
		csUpdate = custom(csUpdate);
		this.getSqlMapClientTemplate().update("updateCsUpdate$NotNull", csUpdate);
		csUpdate = this.GetCsUpdateById(csUpdate.getCsuId());
		if(csUpdate!=null){
			MemCache.setValue(CsUpdate.class,csUpdate.getCsuId(), csUpdate.getKeyValue());
			MemCache.setObject(CsUpdate.class,csUpdate.getCsuId(),csUpdate);
		}
		MemCache.clearObject(CsUpdate.M.class);
		MemCache.clearValue(CsUpdate.M.class);
		Trigger.on(CsUpdate.class,Trigger.UPDATE,csUpdate);
	}

	/**
	 * 根据ID删除一个App更新
	 * @param id
	 */
	public void deleteCsUpdateById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsUpdate.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsUpdateById", params);
		MemCache.deleteValue(CsUpdate.class, id);
		MemCache.deleteObject(CsUpdate.class, id);
		MemCache.clearObject(CsUpdate.M.class);
		MemCache.clearValue(CsUpdate.M.class);
	}
	/**
	 * 根据ID逻辑删除App更新 
	 * @param id
	 * @return
	 */
	public void removeCsUpdateById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsUpdate.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsUpdateById", params);
		MemCache.deleteValue(CsUpdate.class, id);
		MemCache.deleteObject(CsUpdate.class, id);
		MemCache.clearObject(CsUpdate.M.class);
		MemCache.clearValue(CsUpdate.M.class);
	}
	/**
	 * 根据条件更新App更新
	 * @param values
	 * @param params
	 */
	public void updateCsUpdateByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsUpdateCount(params)>255){
			MemCache.clearValue(CsUpdate.class);
			MemCache.clearObject(CsUpdate.class);
		}else{
			params.put("eval","csu_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsUpdateEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsUpdate.class, map.get("eval"));
				MemCache.deleteObject(CsUpdate.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsUpdate.M.class);
		MemCache.clearValue(CsUpdate.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateCsUpdateBy", allParams);
		Trigger.on(CsUpdate.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除App更新
	 * @param params
	 */
	public void deleteCsUpdateByConfirm(Map params){
		Trigger.on(CsUpdate.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsUpdateCount(params)>255){
			MemCache.clearValue(CsUpdate.class);
			MemCache.clearObject(CsUpdate.class);
		}else{
			params.put("eval","csu_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsUpdateEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsUpdate.class, map.get("eval"));
				MemCache.deleteObject(CsUpdate.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsUpdate.M.class);
		MemCache.clearValue(CsUpdate.M.class);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsUpdateBy", params);
	}
	

}