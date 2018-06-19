package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsConfigDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsConfig;
import com.lazy3q.web.helper.$;

/**
 * 系统配置(城市)的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsConfigDao extends SqlMapClientDaoSupport implements ICsConfigDao
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
	private CsConfig custom(CsConfig params){
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
			"cscFlags"			
			,"cscBanners"			
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
	 * 获取所有系统配置(城市)
	 * @return
	 */	
	public List<CsConfig> getCsConfigList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsConfig> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(CsConfig.M.class, md5);
			List caches = MemCache.getObject(CsConfig.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						CsConfig cacheObject = MemCache.getObject(CsConfig.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(CsConfig.M.class, md5);
							MemCache.deleteValue(CsConfig.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(CsConfig.M.class, md5);
					MemCache.deleteValue(CsConfig.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsConfigList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(CsConfig csConfig:list){
				caches.add(csConfig.getCscId());
				MemCache.setObject(CsConfig.class,csConfig.getCscId(), csConfig);
			}
			MemCache.setObject(CsConfig.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(CsConfig.M.class, md5, json, MemCache.defaultSeconds());
		}		
		
		return list;
	}
	
	
	/**
	 * 获取系统配置(城市)统计
	 * @return
	 */
	public List getCsConfigStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsConfigStats", params);
	}

	/**
	 * 获取系统配置(城市)分页
	 * @return
	 */
	public Page getCsConfigPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsConfigCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsConfigPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取系统配置(城市)数量
	 * @return
	 */	
	public Long getCsConfigCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsConfigEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取系统配置(城市)自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsConfigEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsConfigEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取系统配置(城市)
	 * @param params
	 * @return
	 */
	public CsConfig getCsConfig(Map params)
	{		
		params = custom(params);
			
		List<CsConfig> list=this.getCsConfigList(params,1);
		CsConfig csConfig = list.isEmpty()?null:list.get(0);
		
		return csConfig;
	}
		
	/**
	 * 根据ID取系统配置(城市)
	 * @param id
	 * @return
	 */
	public CsConfig getCsConfigById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsConfig csConfig = MemCache.getObject(CsConfig.class, id);
		if(csConfig!=null)
			return csConfig;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csConfig = (CsConfig) this.getSqlMapClientTemplate().queryForObject("getCsConfigById",params);
		if(csConfig!=null)
			MemCache.setValue(CsConfig.class,id, csConfig.getKeyValue());
		if(csConfig!=null)
			MemCache.setObject(CsConfig.class,id, csConfig);
		return csConfig;
	}
	
	/**
	 * 根据ID取系统配置(城市)(不缓存)
	 * @param id
	 * @return
	 */
	public CsConfig GetCsConfigById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsConfig) this.getSqlMapClientTemplate().queryForObject("getCsConfigById",params);
	}
	

	/**
	 * 保存系统配置(城市)
	 * @param csConfig
	 */
	public CsConfig saveCsConfig(CsConfig csConfig)
	{
		csConfig = custom(csConfig);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsConfig", csConfig));
		csConfig = this.GetCsConfigById(id);
		MemCache.setValue(CsConfig.class,id, csConfig.getKeyValue());
		MemCache.setObject(CsConfig.class,id,csConfig);
		MemCache.clearObject(CsConfig.M.class);
		MemCache.clearValue(CsConfig.M.class);
		return Trigger.on(CsConfig.class,Trigger.SAVE,csConfig);	
	}

	

	/**
	 * 更新系统配置(城市)
	 * @param csConfig
	 */
	public void updateCsConfig(CsConfig csConfig)
	{
		csConfig = custom(csConfig);
		this.getSqlMapClientTemplate().update("updateCsConfig", csConfig);
		csConfig = this.GetCsConfigById(csConfig.getCscId());
		if(csConfig!=null){
			MemCache.setValue(CsConfig.class,csConfig.getCscId(), csConfig.getKeyValue());
			MemCache.setObject(CsConfig.class,csConfig.getCscId(),csConfig);
		}
		MemCache.clearObject(CsConfig.M.class);
		MemCache.clearValue(CsConfig.M.class);
		Trigger.on(CsConfig.class,Trigger.UPDATE,csConfig);
	}
	/**
	 * 更新系统配置(城市)非空字段
	 * @param csConfig
	 */
	public void updateCsConfig$NotNull(CsConfig csConfig)
	{
		csConfig = custom(csConfig);
		this.getSqlMapClientTemplate().update("updateCsConfig$NotNull", csConfig);
		csConfig = this.GetCsConfigById(csConfig.getCscId());
		if(csConfig!=null){
			MemCache.setValue(CsConfig.class,csConfig.getCscId(), csConfig.getKeyValue());
			MemCache.setObject(CsConfig.class,csConfig.getCscId(),csConfig);
		}
		MemCache.clearObject(CsConfig.M.class);
		MemCache.clearValue(CsConfig.M.class);
		Trigger.on(CsConfig.class,Trigger.UPDATE,csConfig);
	}

	/**
	 * 根据ID删除一个系统配置(城市)
	 * @param id
	 */
	public void deleteCsConfigById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsConfig.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsConfigById", params);
		MemCache.deleteValue(CsConfig.class, id);
		MemCache.deleteObject(CsConfig.class, id);
		MemCache.clearObject(CsConfig.M.class);
		MemCache.clearValue(CsConfig.M.class);
	}
	/**
	 * 根据条件更新系统配置(城市)
	 * @param values
	 * @param params
	 */
	public void updateCsConfigByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsConfigCount(params)>255){
			MemCache.clearValue(CsConfig.class);
			MemCache.clearObject(CsConfig.class);
		}else{
			params.put("eval","csc_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsConfigEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsConfig.class, map.get("eval"));
				MemCache.deleteObject(CsConfig.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsConfig.M.class);
		MemCache.clearValue(CsConfig.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateCsConfigBy", allParams);
		Trigger.on(CsConfig.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除系统配置(城市)
	 * @param params
	 */
	public void deleteCsConfigByConfirm(Map params){
		Trigger.on(CsConfig.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsConfigCount(params)>255){
			MemCache.clearValue(CsConfig.class);
			MemCache.clearObject(CsConfig.class);
		}else{
			params.put("eval","csc_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsConfigEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsConfig.class, map.get("eval"));
				MemCache.deleteObject(CsConfig.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsConfig.M.class);
		MemCache.clearValue(CsConfig.M.class);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsConfigBy", params);
	}
	

}