package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsChargeStationDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsChargeStation;
import com.lazy3q.web.helper.$;

/**
 * 充电站的Dao实现
 * @author Joel
 */
@SuppressWarnings("unchecked")
public class CsChargeStationDao extends SqlMapClientDaoSupport implements ICsChargeStationDao
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
	private CsChargeStation custom(CsChargeStation params){
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
		"cscsId","cscsStaCode","cscsStaName","cscsType","cscsOpState","cscsProvince","cscsCity","cscsArea","cscsAddress","cscsLng","cscsLat","cscsPrice","cscsProvider","cscsData","cscsAddTime","cscsUpdateTime"
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
	 * 获取所有充电站
	 * @return
	 */	
	public List<CsChargeStation> getCsChargeStationList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsChargeStation> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsChargeStationList", params);
		
		return list;
	}
	
	
	/**
	 * 获取充电站统计
	 * @return
	 */
	public List getCsChargeStationStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsChargeStationStats", params);
	}

	/**
	 * 获取充电站分页
	 * @return
	 */
	public Page getCsChargeStationPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsChargeStationCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsChargeStationPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取充电站数量
	 * @return
	 */	
	public Long getCsChargeStationCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsChargeStationEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取充电站自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsChargeStationEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsChargeStationEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取充电站
	 * @param params
	 * @return
	 */
	public CsChargeStation getCsChargeStation(Map params)
	{		
		params = custom(params);
			
		List<CsChargeStation> list=this.getCsChargeStationList(params,1);
		CsChargeStation csChargeStation = list.isEmpty()?null:list.get(0);
		
		return csChargeStation;
	}
		
	/**
	 * 根据ID取充电站
	 * @param id
	 * @return
	 */
	public CsChargeStation getCsChargeStationById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsChargeStation csChargeStation = null;
		if(csChargeStation!=null)
			return csChargeStation;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csChargeStation = (CsChargeStation) this.getSqlMapClientTemplate().queryForObject("getCsChargeStationById",params);
		if(csChargeStation!=null)
			MemCache.setValue(CsChargeStation.class,id, csChargeStation.getKeyValue());
		return csChargeStation;
	}
	
	/**
	 * 根据ID取充电站(不缓存)
	 * @param id
	 * @return
	 */
	public CsChargeStation GetCsChargeStationById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsChargeStation) this.getSqlMapClientTemplate().queryForObject("getCsChargeStationById",params);
	}
	

	/**
	 * 保存充电站
	 * @param csChargeStation
	 */
	public CsChargeStation saveCsChargeStation(CsChargeStation csChargeStation)
	{
		csChargeStation = custom(csChargeStation);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsChargeStation", csChargeStation));
		csChargeStation = this.GetCsChargeStationById(id);
		MemCache.setValue(CsChargeStation.class,id, csChargeStation.getKeyValue());
		return Trigger.on(CsChargeStation.class,Trigger.SAVE,csChargeStation);	
	}

	

	/**
	 * 更新充电站
	 * @param csChargeStation
	 */
	public void updateCsChargeStation(CsChargeStation csChargeStation)
	{
		csChargeStation = custom(csChargeStation);
		this.getSqlMapClientTemplate().update("updateCsChargeStation", csChargeStation);
		csChargeStation = this.GetCsChargeStationById(csChargeStation.getCscsId());
		if(csChargeStation!=null){
			MemCache.setValue(CsChargeStation.class,csChargeStation.getCscsId(), csChargeStation.getKeyValue());
		}
		Trigger.on(CsChargeStation.class,Trigger.UPDATE,csChargeStation);
	}
	/**
	 * 更新充电站非空字段
	 * @param csChargeStation
	 */
	public void updateCsChargeStation$NotNull(CsChargeStation csChargeStation)
	{
		csChargeStation = custom(csChargeStation);
		this.getSqlMapClientTemplate().update("updateCsChargeStation$NotNull", csChargeStation);
		csChargeStation = this.GetCsChargeStationById(csChargeStation.getCscsId());
		if(csChargeStation!=null){
			MemCache.setValue(CsChargeStation.class,csChargeStation.getCscsId(), csChargeStation.getKeyValue());
		}
		Trigger.on(CsChargeStation.class,Trigger.UPDATE,csChargeStation);
	}

	/**
	 * 根据ID删除一个充电站
	 * @param id
	 */
	public void deleteCsChargeStationById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsChargeStation.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsChargeStationById", params);
		MemCache.deleteValue(CsChargeStation.class, id);
	}
	/**
	 * 根据ID逻辑删除充电站 
	 * @param id
	 * @return
	 */
	public void removeCsChargeStationById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsChargeStation.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsChargeStationById", params);
		MemCache.deleteValue(CsChargeStation.class, id);
	}
	/**
	 * 根据条件更新充电站
	 * @param values
	 * @param params
	 */
	public void updateCsChargeStationByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsChargeStationCount(params)>255){
			MemCache.clearValue(CsChargeStation.class);
		}else{
			params.put("eval","cscs_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsChargeStationEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsChargeStation.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsChargeStationBy", allParams);
		Trigger.on(CsChargeStation.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除充电站
	 * @param params
	 */
	public void deleteCsChargeStationByConfirm(Map params){
		Trigger.on(CsChargeStation.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsChargeStationCount(params)>255){
			MemCache.clearValue(CsChargeStation.class);
		}else{
			params.put("eval","cscs_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsChargeStationEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsChargeStation.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsChargeStationBy", params);
	}
	

}