package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsGasStationDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsGasStation;
import com.lazy3q.web.helper.$;

/**
 * 加油站的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsGasStationDao extends SqlMapClientDaoSupport implements ICsGasStationDao
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
	private CsGasStation custom(CsGasStation params){
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
			"csgsAddress"			
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
	 * 获取所有加油站
	 * @return
	 */	
	public List<CsGasStation> getCsGasStationList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsGasStation> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsGasStationList", params);
		
		return list;
	}
	
	
	/**
	 * 获取加油站统计
	 * @return
	 */
	public List getCsGasStationStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsGasStationStats", params);
	}

	/**
	 * 获取加油站分页
	 * @return
	 */
	public Page getCsGasStationPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsGasStationCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsGasStationPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取加油站数量
	 * @return
	 */	
	public Long getCsGasStationCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsGasStationEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取加油站自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsGasStationEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsGasStationEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取加油站
	 * @param params
	 * @return
	 */
	public CsGasStation getCsGasStation(Map params)
	{		
		params = custom(params);
			
		List<CsGasStation> list=this.getCsGasStationList(params,1);
		CsGasStation csGasStation = list.isEmpty()?null:list.get(0);
		
		return csGasStation;
	}
		
	/**
	 * 根据ID取加油站
	 * @param id
	 * @return
	 */
	public CsGasStation getCsGasStationById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsGasStation csGasStation = null;
		if(csGasStation!=null)
			return csGasStation;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csGasStation = (CsGasStation) this.getSqlMapClientTemplate().queryForObject("getCsGasStationById",params);
		if(csGasStation!=null)
			MemCache.setValue(CsGasStation.class,id, csGasStation.getKeyValue());
		return csGasStation;
	}
	
	/**
	 * 根据ID取加油站(不缓存)
	 * @param id
	 * @return
	 */
	public CsGasStation GetCsGasStationById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsGasStation) this.getSqlMapClientTemplate().queryForObject("getCsGasStationById",params);
	}
	

	/**
	 * 保存加油站
	 * @param csGasStation
	 */
	public CsGasStation saveCsGasStation(CsGasStation csGasStation)
	{
		csGasStation = custom(csGasStation);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsGasStation", csGasStation));
		csGasStation = this.GetCsGasStationById(id);
		MemCache.setValue(CsGasStation.class,id, csGasStation.getKeyValue());
		return Trigger.on(CsGasStation.class,Trigger.SAVE,csGasStation);	
	}

	

	/**
	 * 更新加油站
	 * @param csGasStation
	 */
	public void updateCsGasStation(CsGasStation csGasStation)
	{
		csGasStation = custom(csGasStation);
		this.getSqlMapClientTemplate().update("updateCsGasStation", csGasStation);
		csGasStation = this.GetCsGasStationById(csGasStation.getCsgsId());
		if(csGasStation!=null){
			MemCache.setValue(CsGasStation.class,csGasStation.getCsgsId(), csGasStation.getKeyValue());
		}
		Trigger.on(CsGasStation.class,Trigger.UPDATE,csGasStation);
	}
	/**
	 * 更新加油站非空字段
	 * @param csGasStation
	 */
	public void updateCsGasStation$NotNull(CsGasStation csGasStation)
	{
		csGasStation = custom(csGasStation);
		this.getSqlMapClientTemplate().update("updateCsGasStation$NotNull", csGasStation);
		csGasStation = this.GetCsGasStationById(csGasStation.getCsgsId());
		if(csGasStation!=null){
			MemCache.setValue(CsGasStation.class,csGasStation.getCsgsId(), csGasStation.getKeyValue());
		}
		Trigger.on(CsGasStation.class,Trigger.UPDATE,csGasStation);
	}

	/**
	 * 根据ID删除一个加油站
	 * @param id
	 */
	public void deleteCsGasStationById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsGasStation.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsGasStationById", params);
		MemCache.deleteValue(CsGasStation.class, id);
	}
	/**
	 * 根据ID逻辑删除加油站 
	 * @param id
	 * @return
	 */
	public void removeCsGasStationById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsGasStation.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsGasStationById", params);
		MemCache.deleteValue(CsGasStation.class, id);
	}
	/**
	 * 根据条件更新加油站
	 * @param values
	 * @param params
	 */
	public void updateCsGasStationByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsGasStationCount(params)>255){
			MemCache.clearValue(CsGasStation.class);
		}else{
			params.put("eval","csgs_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsGasStationEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsGasStation.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsGasStationBy", allParams);
		Trigger.on(CsGasStation.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除加油站
	 * @param params
	 */
	public void deleteCsGasStationByConfirm(Map params){
		Trigger.on(CsGasStation.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsGasStationCount(params)>255){
			MemCache.clearValue(CsGasStation.class);
		}else{
			params.put("eval","csgs_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsGasStationEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsGasStation.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsGasStationBy", params);
	}
	

}