package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsStateDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsState;
import com.lazy3q.web.helper.$;

/**
 * 车辆当前状态的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsStateDao extends SqlMapClientDaoSupport implements ICsStateDao
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
	private CsState custom(CsState params){
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
			"cssNumber"			
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
	 * 获取所有车辆当前状态
	 * @return
	 */	
	public List<CsState> getCsStateList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsState> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsStateList", params);
		
		return list;
	}
	
	
	/**
	 * 获取车辆当前状态统计
	 * @return
	 */
	public List getCsStateStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsStateStats", params);
	}

	/**
	 * 获取车辆当前状态分页
	 * @return
	 */
	public Page getCsStatePage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsStateCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsStatePage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取车辆当前状态数量
	 * @return
	 */	
	public Long getCsStateCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsStateEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取车辆当前状态自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsStateEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsStateEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取车辆当前状态
	 * @param params
	 * @return
	 */
	public CsState getCsState(Map params)
	{		
		params = custom(params);
			
		List<CsState> list=this.getCsStateList(params,1);
		CsState csState = list.isEmpty()?null:list.get(0);
		
		return csState;
	}
		
	/**
	 * 根据ID取车辆当前状态
	 * @param id
	 * @return
	 */
	public CsState getCsStateById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsState csState = null;
		if(csState!=null)
			return csState;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csState = (CsState) this.getSqlMapClientTemplate().queryForObject("getCsStateById",params);
		return csState;
	}
	
	/**
	 * 根据ID取车辆当前状态(不缓存)
	 * @param id
	 * @return
	 */
	public CsState GetCsStateById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsState) this.getSqlMapClientTemplate().queryForObject("getCsStateById",params);
	}
	

	/**
	 * 保存车辆当前状态
	 * @param csState
	 */
	public CsState saveCsState(CsState csState)
	{
		csState = custom(csState);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsState", csState));
		csState = this.GetCsStateById(id);
		return Trigger.on(CsState.class,Trigger.SAVE,csState);	
	}

	

	/**
	 * 更新车辆当前状态
	 * @param csState
	 */
	public void updateCsState(CsState csState)
	{
		csState = custom(csState);
		this.getSqlMapClientTemplate().update("updateCsState", csState);
		csState = this.GetCsStateById(csState.getCssId());
		Trigger.on(CsState.class,Trigger.UPDATE,csState);
	}
	/**
	 * 更新车辆当前状态非空字段
	 * @param csState
	 */
	public void updateCsState$NotNull(CsState csState)
	{
		csState = custom(csState);
		this.getSqlMapClientTemplate().update("updateCsState$NotNull", csState);
		csState = this.GetCsStateById(csState.getCssId());
		Trigger.on(CsState.class,Trigger.UPDATE,csState);
	}

	/**
	 * 根据ID删除一个车辆当前状态
	 * @param id
	 */
	public void deleteCsStateById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsState.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsStateById", params);
	}
	/**
	 * 根据条件更新车辆当前状态
	 * @param values
	 * @param params
	 */
	public void updateCsStateByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
			
		int rows = this.getSqlMapClientTemplate().update("updateCsStateBy", allParams);
		Trigger.on(CsState.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除车辆当前状态
	 * @param params
	 */
	public void deleteCsStateByConfirm(Map params){
		Trigger.on(CsState.class,Trigger.DELETE,params);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsStateBy", params);
	}
	

}