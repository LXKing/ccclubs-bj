package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsDriverCarDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsDriverCar;
import com.lazy3q.web.helper.$;

/**
 * 司机车辆的Dao实现
 * @author Joel
 */
@SuppressWarnings("unchecked")
public class CsDriverCarDao extends SqlMapClientDaoSupport implements ICsDriverCarDao
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
	private CsDriverCar custom(CsDriverCar params){
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
		//"csdcId","csdcHost","csdcOutlets","csdcDriver","csdcCar","csdcState","csdcStatus","csdcAddTime","csdcUpdateTime"
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
	 * 获取所有司机车辆
	 * @return
	 */	
	public List<CsDriverCar> getCsDriverCarList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsDriverCar> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsDriverCarList", params);
		
		return list;
	}
	
	
	/**
	 * 获取司机车辆统计
	 * @return
	 */
	public List getCsDriverCarStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsDriverCarStats", params);
	}

	/**
	 * 获取司机车辆分页
	 * @return
	 */
	public Page getCsDriverCarPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsDriverCarCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsDriverCarPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取司机车辆数量
	 * @return
	 */	
	public Long getCsDriverCarCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsDriverCarEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取司机车辆自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsDriverCarEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsDriverCarEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取司机车辆
	 * @param params
	 * @return
	 */
	public CsDriverCar getCsDriverCar(Map params)
	{		
		params = custom(params);
			
		List<CsDriverCar> list=this.getCsDriverCarList(params,1);
		CsDriverCar csDriverCar = list.isEmpty()?null:list.get(0);
		
		return csDriverCar;
	}
		
	/**
	 * 根据ID取司机车辆
	 * @param id
	 * @return
	 */
	public CsDriverCar getCsDriverCarById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsDriverCar csDriverCar = null;
		if(csDriverCar!=null)
			return csDriverCar;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csDriverCar = (CsDriverCar) this.getSqlMapClientTemplate().queryForObject("getCsDriverCarById",params);
		if(csDriverCar!=null)
			MemCache.setValue(CsDriverCar.class,id, csDriverCar.getKeyValue());
		return csDriverCar;
	}
	
	/**
	 * 根据ID取司机车辆(不缓存)
	 * @param id
	 * @return
	 */
	public CsDriverCar GetCsDriverCarById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsDriverCar) this.getSqlMapClientTemplate().queryForObject("getCsDriverCarById",params);
	}
	

	/**
	 * 保存司机车辆
	 * @param csDriverCar
	 */
	public CsDriverCar saveCsDriverCar(CsDriverCar csDriverCar)
	{
		csDriverCar = custom(csDriverCar);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsDriverCar", csDriverCar));
		csDriverCar = this.GetCsDriverCarById(id);
		MemCache.setValue(CsDriverCar.class,id, csDriverCar.getKeyValue());
		return Trigger.on(CsDriverCar.class,Trigger.SAVE,csDriverCar);	
	}

	

	/**
	 * 更新司机车辆
	 * @param csDriverCar
	 */
	public void updateCsDriverCar(CsDriverCar csDriverCar)
	{
		csDriverCar = custom(csDriverCar);
		this.getSqlMapClientTemplate().update("updateCsDriverCar", csDriverCar);
		csDriverCar = this.GetCsDriverCarById(csDriverCar.getCsdcId());
		if(csDriverCar!=null){
			MemCache.setValue(CsDriverCar.class,csDriverCar.getCsdcId(), csDriverCar.getKeyValue());
		}
		Trigger.on(CsDriverCar.class,Trigger.UPDATE,csDriverCar);
	}
	/**
	 * 更新司机车辆非空字段
	 * @param csDriverCar
	 */
	public void updateCsDriverCar$NotNull(CsDriverCar csDriverCar)
	{
		csDriverCar = custom(csDriverCar);
		this.getSqlMapClientTemplate().update("updateCsDriverCar$NotNull", csDriverCar);
		csDriverCar = this.GetCsDriverCarById(csDriverCar.getCsdcId());
		if(csDriverCar!=null){
			MemCache.setValue(CsDriverCar.class,csDriverCar.getCsdcId(), csDriverCar.getKeyValue());
		}
		Trigger.on(CsDriverCar.class,Trigger.UPDATE,csDriverCar);
	}

	/**
	 * 根据ID删除一个司机车辆
	 * @param id
	 */
	public void deleteCsDriverCarById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsDriverCar.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsDriverCarById", params);
		MemCache.deleteValue(CsDriverCar.class, id);
	}
	/**
	 * 根据ID逻辑删除司机车辆 
	 * @param id
	 * @return
	 */
	public void removeCsDriverCarById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsDriverCar.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsDriverCarById", params);
		MemCache.deleteValue(CsDriverCar.class, id);
	}
	/**
	 * 根据条件更新司机车辆
	 * @param values
	 * @param params
	 */
	public void updateCsDriverCarByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsDriverCarCount(params)>255){
			MemCache.clearValue(CsDriverCar.class);
		}else{
			params.put("eval","csdc_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsDriverCarEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsDriverCar.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsDriverCarBy", allParams);
		Trigger.on(CsDriverCar.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除司机车辆
	 * @param params
	 */
	public void deleteCsDriverCarByConfirm(Map params){
		Trigger.on(CsDriverCar.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsDriverCarCount(params)>255){
			MemCache.clearValue(CsDriverCar.class);
		}else{
			params.put("eval","csdc_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsDriverCarEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsDriverCar.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsDriverCarBy", params);
	}
	

}