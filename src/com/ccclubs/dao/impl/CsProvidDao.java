package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsProvidDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsProvid;
import com.lazy3q.web.helper.$;

/**
 * 供应商的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsProvidDao extends SqlMapClientDaoSupport implements ICsProvidDao
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
	private CsProvid custom(CsProvid params){
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
			"cspConcat"			
			,"cspRemark"			
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
	 * 获取所有供应商
	 * @return
	 */	
	public List<CsProvid> getCsProvidList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsProvid> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject","cspUpdateTime","cspUpdateTimeStart","cspUpdateTimeEnd","cspAddTime","cspAddTimeStart","cspAddTimeEnd"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(CsProvid.M.class, md5);
			List caches = MemCache.getObject(CsProvid.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						CsProvid cacheObject = MemCache.getObject(CsProvid.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(CsProvid.M.class, md5);
							MemCache.deleteValue(CsProvid.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(CsProvid.M.class, md5);
					MemCache.deleteValue(CsProvid.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsProvidList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(CsProvid csProvid:list){
				caches.add(csProvid.getCspId());
				MemCache.setObject(CsProvid.class,csProvid.getCspId(), csProvid);
			}
			MemCache.setObject(CsProvid.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(CsProvid.M.class, md5, json, MemCache.defaultSeconds());
		}		
		
		return list;
	}
	
	
	/**
	 * 获取供应商统计
	 * @return
	 */
	public List getCsProvidStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsProvidStats", params);
	}

	/**
	 * 获取供应商分页
	 * @return
	 */
	public Page getCsProvidPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsProvidCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsProvidPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取供应商数量
	 * @return
	 */	
	public Long getCsProvidCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsProvidEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取供应商自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsProvidEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsProvidEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取供应商
	 * @param params
	 * @return
	 */
	public CsProvid getCsProvid(Map params)
	{		
		params = custom(params);
			
		List<CsProvid> list=this.getCsProvidList(params,1);
		CsProvid csProvid = list.isEmpty()?null:list.get(0);
		
		return csProvid;
	}
		
	/**
	 * 根据ID取供应商
	 * @param id
	 * @return
	 */
	public CsProvid getCsProvidById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsProvid csProvid = MemCache.getObject(CsProvid.class, id);
		if(csProvid!=null)
			return csProvid;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csProvid = (CsProvid) this.getSqlMapClientTemplate().queryForObject("getCsProvidById",params);
		if(csProvid!=null)
			MemCache.setValue(CsProvid.class,id, csProvid.getKeyValue());
		if(csProvid!=null)
			MemCache.setObject(CsProvid.class,id, csProvid);
		return csProvid;
	}
	
	/**
	 * 根据ID取供应商(不缓存)
	 * @param id
	 * @return
	 */
	public CsProvid GetCsProvidById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsProvid) this.getSqlMapClientTemplate().queryForObject("getCsProvidById",params);
	}
	

	/**
	 * 保存供应商
	 * @param csProvid
	 */
	public CsProvid saveCsProvid(CsProvid csProvid)
	{
		csProvid = custom(csProvid);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsProvid", csProvid));
		csProvid = this.GetCsProvidById(id);
		MemCache.setValue(CsProvid.class,id, csProvid.getKeyValue());
		MemCache.setObject(CsProvid.class,id,csProvid);
		MemCache.clearObject(CsProvid.M.class);
		MemCache.clearValue(CsProvid.M.class);
		return Trigger.on(CsProvid.class,Trigger.SAVE,csProvid);	
	}

	

	/**
	 * 更新供应商
	 * @param csProvid
	 */
	public void updateCsProvid(CsProvid csProvid)
	{
		csProvid = custom(csProvid);
		this.getSqlMapClientTemplate().update("updateCsProvid", csProvid);
		csProvid = this.GetCsProvidById(csProvid.getCspId());
		if(csProvid!=null){
			MemCache.setValue(CsProvid.class,csProvid.getCspId(), csProvid.getKeyValue());
			MemCache.setObject(CsProvid.class,csProvid.getCspId(),csProvid);
		}
		MemCache.clearObject(CsProvid.M.class);
		MemCache.clearValue(CsProvid.M.class);
		Trigger.on(CsProvid.class,Trigger.UPDATE,csProvid);
	}
	/**
	 * 更新供应商非空字段
	 * @param csProvid
	 */
	public void updateCsProvid$NotNull(CsProvid csProvid)
	{
		csProvid = custom(csProvid);
		this.getSqlMapClientTemplate().update("updateCsProvid$NotNull", csProvid);
		csProvid = this.GetCsProvidById(csProvid.getCspId());
		if(csProvid!=null){
			MemCache.setValue(CsProvid.class,csProvid.getCspId(), csProvid.getKeyValue());
			MemCache.setObject(CsProvid.class,csProvid.getCspId(),csProvid);
		}
		MemCache.clearObject(CsProvid.M.class);
		MemCache.clearValue(CsProvid.M.class);
		Trigger.on(CsProvid.class,Trigger.UPDATE,csProvid);
	}

	/**
	 * 根据ID删除一个供应商
	 * @param id
	 */
	public void deleteCsProvidById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsProvid.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsProvidById", params);
		MemCache.deleteValue(CsProvid.class, id);
		MemCache.deleteObject(CsProvid.class, id);
		MemCache.clearObject(CsProvid.M.class);
		MemCache.clearValue(CsProvid.M.class);
	}
	/**
	 * 根据ID逻辑删除供应商 
	 * @param id
	 * @return
	 */
	public void removeCsProvidById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsProvid.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsProvidById", params);
		MemCache.deleteValue(CsProvid.class, id);
		MemCache.deleteObject(CsProvid.class, id);
		MemCache.clearObject(CsProvid.M.class);
		MemCache.clearValue(CsProvid.M.class);
	}
	/**
	 * 根据条件更新供应商
	 * @param values
	 * @param params
	 */
	public void updateCsProvidByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsProvidCount(params)>255){
			MemCache.clearValue(CsProvid.class);
			MemCache.clearObject(CsProvid.class);
		}else{
			params.put("eval","csp_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsProvidEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsProvid.class, map.get("eval"));
				MemCache.deleteObject(CsProvid.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsProvid.M.class);
		MemCache.clearValue(CsProvid.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateCsProvidBy", allParams);
		Trigger.on(CsProvid.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除供应商
	 * @param params
	 */
	public void deleteCsProvidByConfirm(Map params){
		Trigger.on(CsProvid.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsProvidCount(params)>255){
			MemCache.clearValue(CsProvid.class);
			MemCache.clearObject(CsProvid.class);
		}else{
			params.put("eval","csp_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsProvidEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsProvid.class, map.get("eval"));
				MemCache.deleteObject(CsProvid.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsProvid.M.class);
		MemCache.clearValue(CsProvid.M.class);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsProvidBy", params);
	}
	

}