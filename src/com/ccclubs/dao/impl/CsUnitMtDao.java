package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsUnitMtDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsUnitMt;
import com.lazy3q.web.helper.$;

/**
 * 推送消息的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsUnitMtDao extends SqlMapClientDaoSupport implements ICsUnitMtDao
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
	private CsUnitMt custom(CsUnitMt params){
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
			"csumTitle"			
			,"csumContent"			
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
	 * 获取所有推送消息
	 * @return
	 */	
	public List<CsUnitMt> getCsUnitMtList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsUnitMt> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsUnitMtList", params);
		
		return list;
	}
	
	
	/**
	 * 获取推送消息统计
	 * @return
	 */
	public List getCsUnitMtStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsUnitMtStats", params);
	}

	/**
	 * 获取推送消息分页
	 * @return
	 */
	public Page getCsUnitMtPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsUnitMtCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsUnitMtPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取推送消息数量
	 * @return
	 */	
	public Long getCsUnitMtCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsUnitMtEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取推送消息自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsUnitMtEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsUnitMtEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取推送消息
	 * @param params
	 * @return
	 */
	public CsUnitMt getCsUnitMt(Map params)
	{		
		params = custom(params);
			
		List<CsUnitMt> list=this.getCsUnitMtList(params,1);
		CsUnitMt csUnitMt = list.isEmpty()?null:list.get(0);
		
		return csUnitMt;
	}
		
	/**
	 * 根据ID取推送消息
	 * @param id
	 * @return
	 */
	public CsUnitMt getCsUnitMtById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsUnitMt csUnitMt = null;
		if(csUnitMt!=null)
			return csUnitMt;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csUnitMt = (CsUnitMt) this.getSqlMapClientTemplate().queryForObject("getCsUnitMtById",params);
		if(csUnitMt!=null)
			MemCache.setValue(CsUnitMt.class,id, csUnitMt.getKeyValue());
		return csUnitMt;
	}
	
	/**
	 * 根据ID取推送消息(不缓存)
	 * @param id
	 * @return
	 */
	public CsUnitMt GetCsUnitMtById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsUnitMt) this.getSqlMapClientTemplate().queryForObject("getCsUnitMtById",params);
	}
	

	/**
	 * 保存推送消息
	 * @param csUnitMt
	 */
	public CsUnitMt saveCsUnitMt(CsUnitMt csUnitMt)
	{
		csUnitMt = custom(csUnitMt);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsUnitMt", csUnitMt));
		csUnitMt = this.GetCsUnitMtById(id);
		MemCache.setValue(CsUnitMt.class,id, csUnitMt.getKeyValue());
		return Trigger.on(CsUnitMt.class,Trigger.SAVE,csUnitMt);	
	}

	

	/**
	 * 更新推送消息
	 * @param csUnitMt
	 */
	public void updateCsUnitMt(CsUnitMt csUnitMt)
	{
		csUnitMt = custom(csUnitMt);
		this.getSqlMapClientTemplate().update("updateCsUnitMt", csUnitMt);
		csUnitMt = this.GetCsUnitMtById(csUnitMt.getCsumId());
		if(csUnitMt!=null){
			MemCache.setValue(CsUnitMt.class,csUnitMt.getCsumId(), csUnitMt.getKeyValue());
		}
		Trigger.on(CsUnitMt.class,Trigger.UPDATE,csUnitMt);
	}
	/**
	 * 更新推送消息非空字段
	 * @param csUnitMt
	 */
	public void updateCsUnitMt$NotNull(CsUnitMt csUnitMt)
	{
		csUnitMt = custom(csUnitMt);
		this.getSqlMapClientTemplate().update("updateCsUnitMt$NotNull", csUnitMt);
		csUnitMt = this.GetCsUnitMtById(csUnitMt.getCsumId());
		if(csUnitMt!=null){
			MemCache.setValue(CsUnitMt.class,csUnitMt.getCsumId(), csUnitMt.getKeyValue());
		}
		Trigger.on(CsUnitMt.class,Trigger.UPDATE,csUnitMt);
	}

	/**
	 * 根据ID删除一个推送消息
	 * @param id
	 */
	public void deleteCsUnitMtById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsUnitMt.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsUnitMtById", params);
		MemCache.deleteValue(CsUnitMt.class, id);
	}
	/**
	 * 根据ID逻辑删除推送消息 
	 * @param id
	 * @return
	 */
	public void removeCsUnitMtById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsUnitMt.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsUnitMtById", params);
		MemCache.deleteValue(CsUnitMt.class, id);
	}
	/**
	 * 根据条件更新推送消息
	 * @param values
	 * @param params
	 */
	public void updateCsUnitMtByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsUnitMtCount(params)>255){
			MemCache.clearValue(CsUnitMt.class);
		}else{
			params.put("eval","csum_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsUnitMtEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsUnitMt.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsUnitMtBy", allParams);
		Trigger.on(CsUnitMt.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除推送消息
	 * @param params
	 */
	public void deleteCsUnitMtByConfirm(Map params){
		Trigger.on(CsUnitMt.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsUnitMtCount(params)>255){
			MemCache.clearValue(CsUnitMt.class);
		}else{
			params.put("eval","csum_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsUnitMtEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsUnitMt.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsUnitMtBy", params);
	}
	

}