package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsLimitTimeDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsLimitTime;
import com.lazy3q.web.helper.$;

/**
 * 交通限行的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsLimitTimeDao extends SqlMapClientDaoSupport implements ICsLimitTimeDao
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
	private CsLimitTime custom(CsLimitTime params){
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
			"csltNum"			
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
	 * 获取所有交通限行
	 * @return
	 */	
	public List<CsLimitTime> getCsLimitTimeList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsLimitTime> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject","csltUpdateTime","csltUpdateTimeStart","csltUpdateTimeEnd"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(CsLimitTime.M.class, md5);
			List caches = MemCache.getObject(CsLimitTime.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						CsLimitTime cacheObject = MemCache.getObject(CsLimitTime.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(CsLimitTime.M.class, md5);
							MemCache.deleteValue(CsLimitTime.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(CsLimitTime.M.class, md5);
					MemCache.deleteValue(CsLimitTime.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsLimitTimeList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(CsLimitTime csLimitTime:list){
				caches.add(csLimitTime.getCsltId());
				MemCache.setObject(CsLimitTime.class,csLimitTime.getCsltId(), csLimitTime);
			}
			MemCache.setObject(CsLimitTime.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(CsLimitTime.M.class, md5, json, MemCache.defaultSeconds());
		}		
		
		return list;
	}
	
	
	/**
	 * 获取交通限行统计
	 * @return
	 */
	public List getCsLimitTimeStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsLimitTimeStats", params);
	}

	/**
	 * 获取交通限行分页
	 * @return
	 */
	public Page getCsLimitTimePage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsLimitTimeCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsLimitTimePage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取交通限行数量
	 * @return
	 */	
	public Long getCsLimitTimeCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsLimitTimeEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取交通限行自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsLimitTimeEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsLimitTimeEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取交通限行
	 * @param params
	 * @return
	 */
	public CsLimitTime getCsLimitTime(Map params)
	{		
		params = custom(params);
			
		List<CsLimitTime> list=this.getCsLimitTimeList(params,1);
		CsLimitTime csLimitTime = list.isEmpty()?null:list.get(0);
		
		return csLimitTime;
	}
		
	/**
	 * 根据ID取交通限行
	 * @param id
	 * @return
	 */
	public CsLimitTime getCsLimitTimeById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsLimitTime csLimitTime = MemCache.getObject(CsLimitTime.class, id);
		if(csLimitTime!=null)
			return csLimitTime;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csLimitTime = (CsLimitTime) this.getSqlMapClientTemplate().queryForObject("getCsLimitTimeById",params);
		if(csLimitTime!=null)
			MemCache.setValue(CsLimitTime.class,id, csLimitTime.getKeyValue());
		if(csLimitTime!=null)
			MemCache.setObject(CsLimitTime.class,id, csLimitTime);
		return csLimitTime;
	}
	
	/**
	 * 根据ID取交通限行(不缓存)
	 * @param id
	 * @return
	 */
	public CsLimitTime GetCsLimitTimeById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsLimitTime) this.getSqlMapClientTemplate().queryForObject("getCsLimitTimeById",params);
	}
	

	/**
	 * 保存交通限行
	 * @param csLimitTime
	 */
	public CsLimitTime saveCsLimitTime(CsLimitTime csLimitTime)
	{
		csLimitTime = custom(csLimitTime);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsLimitTime", csLimitTime));
		csLimitTime = this.GetCsLimitTimeById(id);
		MemCache.setValue(CsLimitTime.class,id, csLimitTime.getKeyValue());
		MemCache.setObject(CsLimitTime.class,id,csLimitTime);
		MemCache.clearObject(CsLimitTime.M.class);
		MemCache.clearValue(CsLimitTime.M.class);
		return Trigger.on(CsLimitTime.class,Trigger.SAVE,csLimitTime);	
	}

	

	/**
	 * 更新交通限行
	 * @param csLimitTime
	 */
	public void updateCsLimitTime(CsLimitTime csLimitTime)
	{
		csLimitTime = custom(csLimitTime);
		this.getSqlMapClientTemplate().update("updateCsLimitTime", csLimitTime);
		csLimitTime = this.GetCsLimitTimeById(csLimitTime.getCsltId());
		if(csLimitTime!=null){
			MemCache.setValue(CsLimitTime.class,csLimitTime.getCsltId(), csLimitTime.getKeyValue());
			MemCache.setObject(CsLimitTime.class,csLimitTime.getCsltId(),csLimitTime);
		}
		MemCache.clearObject(CsLimitTime.M.class);
		MemCache.clearValue(CsLimitTime.M.class);
		Trigger.on(CsLimitTime.class,Trigger.UPDATE,csLimitTime);
	}
	/**
	 * 更新交通限行非空字段
	 * @param csLimitTime
	 */
	public void updateCsLimitTime$NotNull(CsLimitTime csLimitTime)
	{
		csLimitTime = custom(csLimitTime);
		this.getSqlMapClientTemplate().update("updateCsLimitTime$NotNull", csLimitTime);
		csLimitTime = this.GetCsLimitTimeById(csLimitTime.getCsltId());
		if(csLimitTime!=null){
			MemCache.setValue(CsLimitTime.class,csLimitTime.getCsltId(), csLimitTime.getKeyValue());
			MemCache.setObject(CsLimitTime.class,csLimitTime.getCsltId(),csLimitTime);
		}
		MemCache.clearObject(CsLimitTime.M.class);
		MemCache.clearValue(CsLimitTime.M.class);
		Trigger.on(CsLimitTime.class,Trigger.UPDATE,csLimitTime);
	}

	/**
	 * 根据ID删除一个交通限行
	 * @param id
	 */
	public void deleteCsLimitTimeById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsLimitTime.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsLimitTimeById", params);
		MemCache.deleteValue(CsLimitTime.class, id);
		MemCache.deleteObject(CsLimitTime.class, id);
		MemCache.clearObject(CsLimitTime.M.class);
		MemCache.clearValue(CsLimitTime.M.class);
	}
	/**
	 * 根据条件更新交通限行
	 * @param values
	 * @param params
	 */
	public void updateCsLimitTimeByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsLimitTimeCount(params)>255){
			MemCache.clearValue(CsLimitTime.class);
			MemCache.clearObject(CsLimitTime.class);
		}else{
			params.put("eval","cslt_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsLimitTimeEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsLimitTime.class, map.get("eval"));
				MemCache.deleteObject(CsLimitTime.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsLimitTime.M.class);
		MemCache.clearValue(CsLimitTime.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateCsLimitTimeBy", allParams);
		Trigger.on(CsLimitTime.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除交通限行
	 * @param params
	 */
	public void deleteCsLimitTimeByConfirm(Map params){
		Trigger.on(CsLimitTime.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsLimitTimeCount(params)>255){
			MemCache.clearValue(CsLimitTime.class);
			MemCache.clearObject(CsLimitTime.class);
		}else{
			params.put("eval","cslt_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsLimitTimeEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsLimitTime.class, map.get("eval"));
				MemCache.deleteObject(CsLimitTime.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsLimitTime.M.class);
		MemCache.clearValue(CsLimitTime.M.class);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsLimitTimeBy", params);
	}
	

}