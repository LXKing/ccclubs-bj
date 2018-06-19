package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsDriverDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsDriver;
import com.lazy3q.web.helper.$;

/**
 * 专车司机的Dao实现
 * @author Joel
 */
@SuppressWarnings("unchecked")
public class CsDriverDao extends SqlMapClientDaoSupport implements ICsDriverDao
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
	private CsDriver custom(CsDriver params){
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
		"csdName","csdMobile","csdPhoto","csdCertifyNum","csdDriverNum"};
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
	 * 获取所有专车司机
	 * @return
	 */	
	public List<CsDriver> getCsDriverList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsDriver> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsDriverList", params);
		
		return list;
	}
	
	
	/**
	 * 获取专车司机统计
	 * @return
	 */
	public List getCsDriverStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsDriverStats", params);
	}

	/**
	 * 获取专车司机分页
	 * @return
	 */
	public Page getCsDriverPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsDriverCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsDriverPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取专车司机数量
	 * @return
	 */	
	public Long getCsDriverCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsDriverEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取专车司机自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsDriverEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsDriverEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取专车司机
	 * @param params
	 * @return
	 */
	public CsDriver getCsDriver(Map params)
	{		
		params = custom(params);
			
		List<CsDriver> list=this.getCsDriverList(params,1);
		CsDriver csDriver = list.isEmpty()?null:list.get(0);
		
		return csDriver;
	}
		
	/**
	 * 根据ID取专车司机
	 * @param id
	 * @return
	 */
	public CsDriver getCsDriverById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsDriver csDriver = null;
		if(csDriver!=null)
			return csDriver;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csDriver = (CsDriver) this.getSqlMapClientTemplate().queryForObject("getCsDriverById",params);
		if(csDriver!=null)
			MemCache.setValue(CsDriver.class,id, csDriver.getKeyValue());
		return csDriver;
	}
	
	/**
	 * 根据ID取专车司机(不缓存)
	 * @param id
	 * @return
	 */
	public CsDriver GetCsDriverById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsDriver) this.getSqlMapClientTemplate().queryForObject("getCsDriverById",params);
	}
	

	/**
	 * 保存专车司机
	 * @param csDriver
	 */
	public CsDriver saveCsDriver(CsDriver csDriver)
	{
		csDriver = custom(csDriver);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsDriver", csDriver));
		csDriver = this.GetCsDriverById(id);
		MemCache.setValue(CsDriver.class,id, csDriver.getKeyValue());
		return Trigger.on(CsDriver.class,Trigger.SAVE,csDriver);	
	}

	

	/**
	 * 更新专车司机
	 * @param csDriver
	 */
	public void updateCsDriver(CsDriver csDriver)
	{
		csDriver = custom(csDriver);
		this.getSqlMapClientTemplate().update("updateCsDriver", csDriver);
		csDriver = this.GetCsDriverById(csDriver.getCsdId());
		if(csDriver!=null){
			MemCache.setValue(CsDriver.class,csDriver.getCsdId(), csDriver.getKeyValue());
		}
		Trigger.on(CsDriver.class,Trigger.UPDATE,csDriver);
	}
	/**
	 * 更新专车司机非空字段
	 * @param csDriver
	 */
	public void updateCsDriver$NotNull(CsDriver csDriver)
	{
		csDriver = custom(csDriver);
		this.getSqlMapClientTemplate().update("updateCsDriver$NotNull", csDriver);
		csDriver = this.GetCsDriverById(csDriver.getCsdId());
		if(csDriver!=null){
			MemCache.setValue(CsDriver.class,csDriver.getCsdId(), csDriver.getKeyValue());
		}
		Trigger.on(CsDriver.class,Trigger.UPDATE,csDriver);
	}

	/**
	 * 根据ID删除一个专车司机
	 * @param id
	 */
	public void deleteCsDriverById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsDriver.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsDriverById", params);
		MemCache.deleteValue(CsDriver.class, id);
	}
	/**
	 * 根据ID逻辑删除专车司机 
	 * @param id
	 * @return
	 */
	public void removeCsDriverById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsDriver.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsDriverById", params);
		MemCache.deleteValue(CsDriver.class, id);
	}
	/**
	 * 根据条件更新专车司机
	 * @param values
	 * @param params
	 */
	public void updateCsDriverByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsDriverCount(params)>255){
			MemCache.clearValue(CsDriver.class);
		}else{
			params.put("eval","csd_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsDriverEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsDriver.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsDriverBy", allParams);
		Trigger.on(CsDriver.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除专车司机
	 * @param params
	 */
	public void deleteCsDriverByConfirm(Map params){
		Trigger.on(CsDriver.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsDriverCount(params)>255){
			MemCache.clearValue(CsDriver.class);
		}else{
			params.put("eval","csd_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsDriverEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsDriver.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsDriverBy", params);
	}
	

}