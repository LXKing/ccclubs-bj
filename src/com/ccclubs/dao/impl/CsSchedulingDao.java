package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsSchedulingDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsScheduling;
import com.lazy3q.web.helper.$;

/**
 * 车辆调度的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsSchedulingDao extends SqlMapClientDaoSupport implements ICsSchedulingDao
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
	private CsScheduling custom(CsScheduling params){
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
			"cssExplain"			
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
	 * 获取所有车辆调度
	 * @return
	 */	
	public List<CsScheduling> getCsSchedulingList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsScheduling> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsSchedulingList", params);
		
		return list;
	}
	
	
	/**
	 * 获取车辆调度统计
	 * @return
	 */
	public List getCsSchedulingStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsSchedulingStats", params);
	}

	/**
	 * 获取车辆调度分页
	 * @return
	 */
	public Page getCsSchedulingPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsSchedulingCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsSchedulingPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取车辆调度数量
	 * @return
	 */	
	public Long getCsSchedulingCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsSchedulingEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取车辆调度自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsSchedulingEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsSchedulingEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取车辆调度
	 * @param params
	 * @return
	 */
	public CsScheduling getCsScheduling(Map params)
	{		
		params = custom(params);
			
		List<CsScheduling> list=this.getCsSchedulingList(params,1);
		CsScheduling csScheduling = list.isEmpty()?null:list.get(0);
		
		return csScheduling;
	}
		
	/**
	 * 根据ID取车辆调度
	 * @param id
	 * @return
	 */
	public CsScheduling getCsSchedulingById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsScheduling csScheduling = null;
		if(csScheduling!=null)
			return csScheduling;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csScheduling = (CsScheduling) this.getSqlMapClientTemplate().queryForObject("getCsSchedulingById",params);
		if(csScheduling!=null)
			MemCache.setValue(CsScheduling.class,id, csScheduling.getKeyValue());
		return csScheduling;
	}
	
	/**
	 * 根据ID取车辆调度(不缓存)
	 * @param id
	 * @return
	 */
	public CsScheduling GetCsSchedulingById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsScheduling) this.getSqlMapClientTemplate().queryForObject("getCsSchedulingById",params);
	}
	

	/**
	 * 保存车辆调度
	 * @param csScheduling
	 */
	public CsScheduling saveCsScheduling(CsScheduling csScheduling)
	{
		csScheduling = custom(csScheduling);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsScheduling", csScheduling));
		csScheduling = this.GetCsSchedulingById(id);
		MemCache.setValue(CsScheduling.class,id, csScheduling.getKeyValue());
		return Trigger.on(CsScheduling.class,Trigger.SAVE,csScheduling);	
	}

	

	/**
	 * 更新车辆调度
	 * @param csScheduling
	 */
	public void updateCsScheduling(CsScheduling csScheduling)
	{
		csScheduling = custom(csScheduling);
		this.getSqlMapClientTemplate().update("updateCsScheduling", csScheduling);
		csScheduling = this.GetCsSchedulingById(csScheduling.getCssId());
		if(csScheduling!=null){
			MemCache.setValue(CsScheduling.class,csScheduling.getCssId(), csScheduling.getKeyValue());
		}
		Trigger.on(CsScheduling.class,Trigger.UPDATE,csScheduling);
	}
	/**
	 * 更新车辆调度非空字段
	 * @param csScheduling
	 */
	public void updateCsScheduling$NotNull(CsScheduling csScheduling)
	{
		csScheduling = custom(csScheduling);
		this.getSqlMapClientTemplate().update("updateCsScheduling$NotNull", csScheduling);
		csScheduling = this.GetCsSchedulingById(csScheduling.getCssId());
		if(csScheduling!=null){
			MemCache.setValue(CsScheduling.class,csScheduling.getCssId(), csScheduling.getKeyValue());
		}
		Trigger.on(CsScheduling.class,Trigger.UPDATE,csScheduling);
	}

	/**
	 * 根据ID删除一个车辆调度
	 * @param id
	 */
	public void deleteCsSchedulingById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsScheduling.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsSchedulingById", params);
		MemCache.deleteValue(CsScheduling.class, id);
	}
	/**
	 * 根据ID逻辑删除车辆调度 
	 * @param id
	 * @return
	 */
	public void removeCsSchedulingById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsScheduling.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsSchedulingById", params);
		MemCache.deleteValue(CsScheduling.class, id);
	}
	/**
	 * 根据条件更新车辆调度
	 * @param values
	 * @param params
	 */
	public void updateCsSchedulingByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsSchedulingCount(params)>255){
			MemCache.clearValue(CsScheduling.class);
		}else{
			params.put("eval","css_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsSchedulingEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsScheduling.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsSchedulingBy", allParams);
		Trigger.on(CsScheduling.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除车辆调度
	 * @param params
	 */
	public void deleteCsSchedulingByConfirm(Map params){
		Trigger.on(CsScheduling.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsSchedulingCount(params)>255){
			MemCache.clearValue(CsScheduling.class);
		}else{
			params.put("eval","css_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsSchedulingEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsScheduling.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsSchedulingBy", params);
	}
	

}