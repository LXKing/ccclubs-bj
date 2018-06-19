package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsCarPartsDao;
import com.ccclubs.model.CsCarParts;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.util.Page;

/**
 * 车辆部件的Dao实现
 * @author Joel
 */
@SuppressWarnings("unchecked")
public class CsCarPartsDao extends SqlMapClientDaoSupport implements ICsCarPartsDao
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
	private CsCarParts custom(CsCarParts params){
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
		"cscpByname"
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
	 * 获取所有车辆部件
	 * @return
	 */	
	public List<CsCarParts> getCsCarPartsList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsCarParts> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsCarPartsList", params);
		
		return list;
	}
	
	
	/**
	 * 获取车辆部件统计
	 * @return
	 */
	public List getCsCarPartsStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsCarPartsStats", params);
	}

	/**
	 * 获取车辆部件分页
	 * @return
	 */
	public Page getCsCarPartsPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsCarPartsCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsCarPartsPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取车辆部件数量
	 * @return
	 */	
	public Long getCsCarPartsCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCarPartsEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取车辆部件自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsCarPartsEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCarPartsEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取车辆部件
	 * @param params
	 * @return
	 */
	public CsCarParts getCsCarParts(Map params)
	{		
		params = custom(params);
			
		List<CsCarParts> list=this.getCsCarPartsList(params,1);
		CsCarParts csCarParts = list.isEmpty()?null:list.get(0);
		
		return csCarParts;
	}
		
	/**
	 * 根据ID取车辆部件
	 * @param id
	 * @return
	 */
	public CsCarParts getCsCarPartsById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsCarParts csCarParts = null;
		if(csCarParts!=null)
			return csCarParts;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csCarParts = (CsCarParts) this.getSqlMapClientTemplate().queryForObject("getCsCarPartsById",params);
		if(csCarParts!=null)
			MemCache.setValue(CsCarParts.class,id, csCarParts.getKeyValue());
		return csCarParts;
	}
	
	/**
	 * 根据ID取车辆部件(不缓存)
	 * @param id
	 * @return
	 */
	public CsCarParts GetCsCarPartsById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsCarParts) this.getSqlMapClientTemplate().queryForObject("getCsCarPartsById",params);
	}
	

	/**
	 * 保存车辆部件
	 * @param csCarParts
	 */
	public CsCarParts saveCsCarParts(CsCarParts csCarParts)
	{
		csCarParts = custom(csCarParts);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsCarParts", csCarParts));
		csCarParts = this.GetCsCarPartsById(id);
		MemCache.setValue(CsCarParts.class,id, csCarParts.getKeyValue());
		return Trigger.on(CsCarParts.class,Trigger.SAVE,csCarParts);	
	}

	

	/**
	 * 更新车辆部件
	 * @param csCarParts
	 */
	public void updateCsCarParts(CsCarParts csCarParts)
	{
		csCarParts = custom(csCarParts);
		this.getSqlMapClientTemplate().update("updateCsCarParts", csCarParts);
		csCarParts = this.GetCsCarPartsById(csCarParts.getCscpId());
		if(csCarParts!=null){
			MemCache.setValue(CsCarParts.class,csCarParts.getCscpId(), csCarParts.getKeyValue());
		}
		Trigger.on(CsCarParts.class,Trigger.UPDATE,csCarParts);
	}
	/**
	 * 更新车辆部件非空字段
	 * @param csCarParts
	 */
	public void updateCsCarParts$NotNull(CsCarParts csCarParts)
	{
		csCarParts = custom(csCarParts);
		this.getSqlMapClientTemplate().update("updateCsCarParts$NotNull", csCarParts);
		csCarParts = this.GetCsCarPartsById(csCarParts.getCscpId());
		if(csCarParts!=null){
			MemCache.setValue(CsCarParts.class,csCarParts.getCscpId(), csCarParts.getKeyValue());
		}
		Trigger.on(CsCarParts.class,Trigger.UPDATE,csCarParts);
	}

	/**
	 * 根据ID删除一个车辆部件
	 * @param id
	 */
	public void deleteCsCarPartsById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsCarParts.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsCarPartsById", params);
		MemCache.deleteValue(CsCarParts.class, id);
	}
	/**
	 * 根据ID逻辑删除车辆部件 
	 * @param id
	 * @return
	 */
	public void removeCsCarPartsById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsCarParts.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsCarPartsById", params);
		MemCache.deleteValue(CsCarParts.class, id);
	}
	/**
	 * 根据条件更新车辆部件
	 * @param values
	 * @param params
	 */
	public void updateCsCarPartsByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsCarPartsCount(params)>255){
			MemCache.clearValue(CsCarParts.class);
		}else{
			params.put("eval","cscp_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCarPartsEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCarParts.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsCarPartsBy", allParams);
		Trigger.on(CsCarParts.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除车辆部件
	 * @param params
	 */
	public void deleteCsCarPartsByConfirm(Map params){
		Trigger.on(CsCarParts.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsCarPartsCount(params)>255){
			MemCache.clearValue(CsCarParts.class);
		}else{
			params.put("eval","cscp_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCarPartsEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCarParts.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsCarPartsBy", params);
	}
	

}