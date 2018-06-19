package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsPlaceDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsPlace;
import com.lazy3q.web.helper.$;

/**
 * 省市地区的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsPlaceDao extends SqlMapClientDaoSupport implements ICsPlaceDao
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
	private CsPlace custom(CsPlace params){
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
	 * 获取所有省市地区
	 * @return
	 */	
	public List<CsPlace> getCsPlaceList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsPlace> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsPlaceList", params);
		
		return list;
	}
	
	
	/**
	 * 获取省市地区统计
	 * @return
	 */
	public List getCsPlaceStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsPlaceStats", params);
	}

	/**
	 * 获取省市地区分页
	 * @return
	 */
	public Page getCsPlacePage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsPlaceCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsPlacePage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取省市地区数量
	 * @return
	 */	
	public Long getCsPlaceCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsPlaceEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取省市地区自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsPlaceEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsPlaceEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取省市地区
	 * @param params
	 * @return
	 */
	public CsPlace getCsPlace(Map params)
	{		
		params = custom(params);
			
		List<CsPlace> list=this.getCsPlaceList(params,1);
		CsPlace csPlace = list.isEmpty()?null:list.get(0);
		
		return csPlace;
	}
		
	/**
	 * 根据ID取省市地区
	 * @param id
	 * @return
	 */
	public CsPlace getCsPlaceById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsPlace csPlace = null;
		if(csPlace!=null)
			return csPlace;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csPlace = (CsPlace) this.getSqlMapClientTemplate().queryForObject("getCsPlaceById",params);
		if(csPlace!=null)
			MemCache.setValue(CsPlace.class,id, csPlace.getKeyValue());
		return csPlace;
	}
	
	/**
	 * 根据ID取省市地区(不缓存)
	 * @param id
	 * @return
	 */
	public CsPlace GetCsPlaceById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsPlace) this.getSqlMapClientTemplate().queryForObject("getCsPlaceById",params);
	}
	

	/**
	 * 保存省市地区
	 * @param csPlace
	 */
	public CsPlace saveCsPlace(CsPlace csPlace)
	{
		csPlace = custom(csPlace);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsPlace", csPlace));
		csPlace = this.GetCsPlaceById(id);
		MemCache.setValue(CsPlace.class,id, csPlace.getKeyValue());
		return Trigger.on(CsPlace.class,Trigger.SAVE,csPlace);	
	}

	

	/**
	 * 更新省市地区
	 * @param csPlace
	 */
	public void updateCsPlace(CsPlace csPlace)
	{
		csPlace = custom(csPlace);
		this.getSqlMapClientTemplate().update("updateCsPlace", csPlace);
		csPlace = this.GetCsPlaceById(csPlace.getCspId());
		if(csPlace!=null){
			MemCache.setValue(CsPlace.class,csPlace.getCspId(), csPlace.getKeyValue());
		}
		Trigger.on(CsPlace.class,Trigger.UPDATE,csPlace);
	}
	/**
	 * 更新省市地区非空字段
	 * @param csPlace
	 */
	public void updateCsPlace$NotNull(CsPlace csPlace)
	{
		csPlace = custom(csPlace);
		this.getSqlMapClientTemplate().update("updateCsPlace$NotNull", csPlace);
		csPlace = this.GetCsPlaceById(csPlace.getCspId());
		if(csPlace!=null){
			MemCache.setValue(CsPlace.class,csPlace.getCspId(), csPlace.getKeyValue());
		}
		Trigger.on(CsPlace.class,Trigger.UPDATE,csPlace);
	}

	/**
	 * 根据ID删除一个省市地区
	 * @param id
	 */
	public void deleteCsPlaceById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsPlace.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsPlaceById", params);
		MemCache.deleteValue(CsPlace.class, id);
	}
	/**
	 * 根据条件更新省市地区
	 * @param values
	 * @param params
	 */
	public void updateCsPlaceByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsPlaceCount(params)>255){
			MemCache.clearValue(CsPlace.class);
		}else{
			params.put("eval","csp_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsPlaceEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsPlace.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsPlaceBy", allParams);
		Trigger.on(CsPlace.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除省市地区
	 * @param params
	 */
	public void deleteCsPlaceByConfirm(Map params){
		Trigger.on(CsPlace.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsPlaceCount(params)>255){
			MemCache.clearValue(CsPlace.class);
		}else{
			params.put("eval","csp_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsPlaceEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsPlace.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsPlaceBy", params);
	}
	

}