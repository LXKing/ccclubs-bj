package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsCallInDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCallIn;
import com.lazy3q.web.helper.$;

/**
 * 客户来电的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsCallInDao extends SqlMapClientDaoSupport implements ICsCallInDao
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
	private CsCallIn custom(CsCallIn params){
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
	 * 获取所有客户来电
	 * @return
	 */	
	public List<CsCallIn> getCsCallInList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsCallIn> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsCallInList", params);
		
		return list;
	}
	
	
	/**
	 * 获取客户来电统计
	 * @return
	 */
	public List getCsCallInStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsCallInStats", params);
	}

	/**
	 * 获取客户来电分页
	 * @return
	 */
	public Page getCsCallInPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsCallInCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsCallInPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取客户来电数量
	 * @return
	 */	
	public Long getCsCallInCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCallInEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取客户来电自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsCallInEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCallInEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取客户来电
	 * @param params
	 * @return
	 */
	public CsCallIn getCsCallIn(Map params)
	{		
		params = custom(params);
			
		List<CsCallIn> list=this.getCsCallInList(params,1);
		CsCallIn csCallIn = list.isEmpty()?null:list.get(0);
		
		return csCallIn;
	}
		
	/**
	 * 根据ID取客户来电
	 * @param id
	 * @return
	 */
	public CsCallIn getCsCallInById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsCallIn csCallIn = null;
		if(csCallIn!=null)
			return csCallIn;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csCallIn = (CsCallIn) this.getSqlMapClientTemplate().queryForObject("getCsCallInById",params);
		if(csCallIn!=null)
			MemCache.setValue(CsCallIn.class,id, csCallIn.getKeyValue());
		return csCallIn;
	}
	
	/**
	 * 根据ID取客户来电(不缓存)
	 * @param id
	 * @return
	 */
	public CsCallIn GetCsCallInById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsCallIn) this.getSqlMapClientTemplate().queryForObject("getCsCallInById",params);
	}
	

	/**
	 * 保存客户来电
	 * @param csCallIn
	 */
	public CsCallIn saveCsCallIn(CsCallIn csCallIn)
	{
		csCallIn = custom(csCallIn);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsCallIn", csCallIn));
		csCallIn = this.GetCsCallInById(id);
		MemCache.setValue(CsCallIn.class,id, csCallIn.getKeyValue());
		return Trigger.on(CsCallIn.class,Trigger.SAVE,csCallIn);	
	}

	

	/**
	 * 更新客户来电
	 * @param csCallIn
	 */
	public void updateCsCallIn(CsCallIn csCallIn)
	{
		csCallIn = custom(csCallIn);
		this.getSqlMapClientTemplate().update("updateCsCallIn", csCallIn);
		csCallIn = this.GetCsCallInById(csCallIn.getCsciId());
		if(csCallIn!=null){
			MemCache.setValue(CsCallIn.class,csCallIn.getCsciId(), csCallIn.getKeyValue());
		}
		Trigger.on(CsCallIn.class,Trigger.UPDATE,csCallIn);
	}
	/**
	 * 更新客户来电非空字段
	 * @param csCallIn
	 */
	public void updateCsCallIn$NotNull(CsCallIn csCallIn)
	{
		csCallIn = custom(csCallIn);
		this.getSqlMapClientTemplate().update("updateCsCallIn$NotNull", csCallIn);
		csCallIn = this.GetCsCallInById(csCallIn.getCsciId());
		if(csCallIn!=null){
			MemCache.setValue(CsCallIn.class,csCallIn.getCsciId(), csCallIn.getKeyValue());
		}
		Trigger.on(CsCallIn.class,Trigger.UPDATE,csCallIn);
	}

	/**
	 * 根据ID删除一个客户来电
	 * @param id
	 */
	public void deleteCsCallInById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsCallIn.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsCallInById", params);
		MemCache.deleteValue(CsCallIn.class, id);
	}
	/**
	 * 根据ID逻辑删除客户来电 
	 * @param id
	 * @return
	 */
	public void removeCsCallInById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsCallIn.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsCallInById", params);
		MemCache.deleteValue(CsCallIn.class, id);
	}
	/**
	 * 根据条件更新客户来电
	 * @param values
	 * @param params
	 */
	public void updateCsCallInByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsCallInCount(params)>255){
			MemCache.clearValue(CsCallIn.class);
		}else{
			params.put("eval","csci_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCallInEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCallIn.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsCallInBy", allParams);
		Trigger.on(CsCallIn.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除客户来电
	 * @param params
	 */
	public void deleteCsCallInByConfirm(Map params){
		Trigger.on(CsCallIn.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsCallInCount(params)>255){
			MemCache.clearValue(CsCallIn.class);
		}else{
			params.put("eval","csci_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCallInEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCallIn.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsCallInBy", params);
	}
	

}