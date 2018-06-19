package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsServeDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsServe;
import com.lazy3q.web.helper.$;

/**
 * 客户服务的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsServeDao extends SqlMapClientDaoSupport implements ICsServeDao
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
	private CsServe custom(CsServe params){
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
			"cssTitle"			
			,"cssDesc"			
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
	 * 获取所有客户服务
	 * @return
	 */	
	public List<CsServe> getCsServeList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsServe> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsServeList", params);
		
		return list;
	}
	
	
	/**
	 * 获取客户服务统计
	 * @return
	 */
	public List getCsServeStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsServeStats", params);
	}

	/**
	 * 获取客户服务分页
	 * @return
	 */
	public Page getCsServePage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsServeCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsServePage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取客户服务数量
	 * @return
	 */	
	public Long getCsServeCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsServeEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取客户服务自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsServeEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsServeEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取客户服务
	 * @param params
	 * @return
	 */
	public CsServe getCsServe(Map params)
	{		
		params = custom(params);
			
		List<CsServe> list=this.getCsServeList(params,1);
		CsServe csServe = list.isEmpty()?null:list.get(0);
		
		return csServe;
	}
		
	/**
	 * 根据ID取客户服务
	 * @param id
	 * @return
	 */
	public CsServe getCsServeById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsServe csServe = null;
		if(csServe!=null)
			return csServe;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csServe = (CsServe) this.getSqlMapClientTemplate().queryForObject("getCsServeById",params);
		if(csServe!=null)
			MemCache.setValue(CsServe.class,id, csServe.getKeyValue());
		return csServe;
	}
	
	/**
	 * 根据ID取客户服务(不缓存)
	 * @param id
	 * @return
	 */
	public CsServe GetCsServeById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsServe) this.getSqlMapClientTemplate().queryForObject("getCsServeById",params);
	}
	

	/**
	 * 保存客户服务
	 * @param csServe
	 */
	public CsServe saveCsServe(CsServe csServe)
	{
		csServe = custom(csServe);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsServe", csServe));
		csServe = this.GetCsServeById(id);
		MemCache.setValue(CsServe.class,id, csServe.getKeyValue());
		return Trigger.on(CsServe.class,Trigger.SAVE,csServe);	
	}

	

	/**
	 * 更新客户服务
	 * @param csServe
	 */
	public void updateCsServe(CsServe csServe)
	{
		csServe = custom(csServe);
		this.getSqlMapClientTemplate().update("updateCsServe", csServe);
		csServe = this.GetCsServeById(csServe.getCssId());
		if(csServe!=null){
			MemCache.setValue(CsServe.class,csServe.getCssId(), csServe.getKeyValue());
		}
		Trigger.on(CsServe.class,Trigger.UPDATE,csServe);
	}
	/**
	 * 更新客户服务非空字段
	 * @param csServe
	 */
	public void updateCsServe$NotNull(CsServe csServe)
	{
		csServe = custom(csServe);
		this.getSqlMapClientTemplate().update("updateCsServe$NotNull", csServe);
		csServe = this.GetCsServeById(csServe.getCssId());
		if(csServe!=null){
			MemCache.setValue(CsServe.class,csServe.getCssId(), csServe.getKeyValue());
		}
		Trigger.on(CsServe.class,Trigger.UPDATE,csServe);
	}

	/**
	 * 根据ID删除一个客户服务
	 * @param id
	 */
	public void deleteCsServeById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsServe.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsServeById", params);
		MemCache.deleteValue(CsServe.class, id);
	}
	/**
	 * 根据ID逻辑删除客户服务 
	 * @param id
	 * @return
	 */
	public void removeCsServeById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsServe.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsServeById", params);
		MemCache.deleteValue(CsServe.class, id);
	}
	/**
	 * 根据条件更新客户服务
	 * @param values
	 * @param params
	 */
	public void updateCsServeByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsServeCount(params)>255){
			MemCache.clearValue(CsServe.class);
		}else{
			params.put("eval","css_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsServeEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsServe.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsServeBy", allParams);
		Trigger.on(CsServe.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除客户服务
	 * @param params
	 */
	public void deleteCsServeByConfirm(Map params){
		Trigger.on(CsServe.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsServeCount(params)>255){
			MemCache.clearValue(CsServe.class);
		}else{
			params.put("eval","css_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsServeEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsServe.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsServeBy", params);
	}
	

}