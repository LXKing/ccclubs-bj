package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsHolidayDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsHoliday;
import com.lazy3q.web.helper.$;

/**
 * 节假日的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsHolidayDao extends SqlMapClientDaoSupport implements ICsHolidayDao
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
	private CsHoliday custom(CsHoliday params){
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
	 * 获取所有节假日
	 * @return
	 */	
	public List<CsHoliday> getCsHolidayList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsHoliday> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject","cshDate","cshDateStart","cshDateEnd"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(CsHoliday.M.class, md5);
			List caches = MemCache.getObject(CsHoliday.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						CsHoliday cacheObject = MemCache.getObject(CsHoliday.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(CsHoliday.M.class, md5);
							MemCache.deleteValue(CsHoliday.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(CsHoliday.M.class, md5);
					MemCache.deleteValue(CsHoliday.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsHolidayList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(CsHoliday csHoliday:list){
				caches.add(csHoliday.getCshId());
				MemCache.setObject(CsHoliday.class,csHoliday.getCshId(), csHoliday);
			}
			MemCache.setObject(CsHoliday.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(CsHoliday.M.class, md5, json, MemCache.defaultSeconds());
		}		
		
		return list;
	}
	
	
	/**
	 * 获取节假日统计
	 * @return
	 */
	public List getCsHolidayStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsHolidayStats", params);
	}

	/**
	 * 获取节假日分页
	 * @return
	 */
	public Page getCsHolidayPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsHolidayCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsHolidayPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取节假日数量
	 * @return
	 */	
	public Long getCsHolidayCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsHolidayEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取节假日自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsHolidayEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsHolidayEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取节假日
	 * @param params
	 * @return
	 */
	public CsHoliday getCsHoliday(Map params)
	{		
		params = custom(params);
			
		List<CsHoliday> list=this.getCsHolidayList(params,1);
		CsHoliday csHoliday = list.isEmpty()?null:list.get(0);
		
		return csHoliday;
	}
		
	/**
	 * 根据ID取节假日
	 * @param id
	 * @return
	 */
	public CsHoliday getCsHolidayById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsHoliday csHoliday = MemCache.getObject(CsHoliday.class, id);
		if(csHoliday!=null)
			return csHoliday;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csHoliday = (CsHoliday) this.getSqlMapClientTemplate().queryForObject("getCsHolidayById",params);
		if(csHoliday!=null)
			MemCache.setValue(CsHoliday.class,id, csHoliday.getKeyValue());
		if(csHoliday!=null)
			MemCache.setObject(CsHoliday.class,id, csHoliday);
		return csHoliday;
	}
	
	/**
	 * 根据ID取节假日(不缓存)
	 * @param id
	 * @return
	 */
	public CsHoliday GetCsHolidayById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsHoliday) this.getSqlMapClientTemplate().queryForObject("getCsHolidayById",params);
	}
	

	/**
	 * 保存节假日
	 * @param csHoliday
	 */
	public CsHoliday saveCsHoliday(CsHoliday csHoliday)
	{
		csHoliday = custom(csHoliday);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsHoliday", csHoliday));
		csHoliday = this.GetCsHolidayById(id);
		MemCache.setValue(CsHoliday.class,id, csHoliday.getKeyValue());
		MemCache.setObject(CsHoliday.class,id,csHoliday);
		MemCache.clearObject(CsHoliday.M.class);
		MemCache.clearValue(CsHoliday.M.class);
		return Trigger.on(CsHoliday.class,Trigger.SAVE,csHoliday);	
	}

	

	/**
	 * 更新节假日
	 * @param csHoliday
	 */
	public void updateCsHoliday(CsHoliday csHoliday)
	{
		csHoliday = custom(csHoliday);
		this.getSqlMapClientTemplate().update("updateCsHoliday", csHoliday);
		csHoliday = this.GetCsHolidayById(csHoliday.getCshId());
		if(csHoliday!=null){
			MemCache.setValue(CsHoliday.class,csHoliday.getCshId(), csHoliday.getKeyValue());
			MemCache.setObject(CsHoliday.class,csHoliday.getCshId(),csHoliday);
		}
		MemCache.clearObject(CsHoliday.M.class);
		MemCache.clearValue(CsHoliday.M.class);
		Trigger.on(CsHoliday.class,Trigger.UPDATE,csHoliday);
	}
	/**
	 * 更新节假日非空字段
	 * @param csHoliday
	 */
	public void updateCsHoliday$NotNull(CsHoliday csHoliday)
	{
		csHoliday = custom(csHoliday);
		this.getSqlMapClientTemplate().update("updateCsHoliday$NotNull", csHoliday);
		csHoliday = this.GetCsHolidayById(csHoliday.getCshId());
		if(csHoliday!=null){
			MemCache.setValue(CsHoliday.class,csHoliday.getCshId(), csHoliday.getKeyValue());
			MemCache.setObject(CsHoliday.class,csHoliday.getCshId(),csHoliday);
		}
		MemCache.clearObject(CsHoliday.M.class);
		MemCache.clearValue(CsHoliday.M.class);
		Trigger.on(CsHoliday.class,Trigger.UPDATE,csHoliday);
	}

	/**
	 * 根据ID删除一个节假日
	 * @param id
	 */
	public void deleteCsHolidayById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsHoliday.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsHolidayById", params);
		MemCache.deleteValue(CsHoliday.class, id);
		MemCache.deleteObject(CsHoliday.class, id);
		MemCache.clearObject(CsHoliday.M.class);
		MemCache.clearValue(CsHoliday.M.class);
	}
	/**
	 * 根据条件更新节假日
	 * @param values
	 * @param params
	 */
	public void updateCsHolidayByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsHolidayCount(params)>255){
			MemCache.clearValue(CsHoliday.class);
			MemCache.clearObject(CsHoliday.class);
		}else{
			params.put("eval","csh_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsHolidayEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsHoliday.class, map.get("eval"));
				MemCache.deleteObject(CsHoliday.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsHoliday.M.class);
		MemCache.clearValue(CsHoliday.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateCsHolidayBy", allParams);
		Trigger.on(CsHoliday.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除节假日
	 * @param params
	 */
	public void deleteCsHolidayByConfirm(Map params){
		Trigger.on(CsHoliday.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsHolidayCount(params)>255){
			MemCache.clearValue(CsHoliday.class);
			MemCache.clearObject(CsHoliday.class);
		}else{
			params.put("eval","csh_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsHolidayEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsHoliday.class, map.get("eval"));
				MemCache.deleteObject(CsHoliday.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsHoliday.M.class);
		MemCache.clearValue(CsHoliday.M.class);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsHolidayBy", params);
	}
	

}