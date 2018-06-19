package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsUnitPersonDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsUnitPerson;
import com.lazy3q.web.helper.$;

/**
 * 用车人员的Dao实现
 * @author Joel
 */
@SuppressWarnings("unchecked")
public class CsUnitPersonDao extends SqlMapClientDaoSupport implements ICsUnitPersonDao
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
	private CsUnitPerson custom(CsUnitPerson params){
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
			"csupName"			
			,"csupRemark"			
			,"csupMemo"			
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
	 * 获取所有用车人员
	 * @return
	 */	
	public List<CsUnitPerson> getCsUnitPersonList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsUnitPerson> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsUnitPersonList", params);
		
		return list;
	}
	
	
	/**
	 * 获取用车人员统计
	 * @return
	 */
	public List getCsUnitPersonStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsUnitPersonStats", params);
	}

	/**
	 * 获取用车人员分页
	 * @return
	 */
	public Page getCsUnitPersonPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsUnitPersonCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsUnitPersonPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取用车人员数量
	 * @return
	 */	
	public Long getCsUnitPersonCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsUnitPersonEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取用车人员自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsUnitPersonEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsUnitPersonEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取用车人员
	 * @param params
	 * @return
	 */
	public CsUnitPerson getCsUnitPerson(Map params)
	{		
		params = custom(params);
			
		List<CsUnitPerson> list=this.getCsUnitPersonList(params,1);
		CsUnitPerson csUnitPerson = list.isEmpty()?null:list.get(0);
		
		return csUnitPerson;
	}
		
	/**
	 * 根据ID取用车人员
	 * @param id
	 * @return
	 */
	public CsUnitPerson getCsUnitPersonById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsUnitPerson csUnitPerson = null;
		if(csUnitPerson!=null)
			return csUnitPerson;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csUnitPerson = (CsUnitPerson) this.getSqlMapClientTemplate().queryForObject("getCsUnitPersonById",params);
		if(csUnitPerson!=null)
			MemCache.setValue(CsUnitPerson.class,id, csUnitPerson.getKeyValue());
		return csUnitPerson;
	}
	
	/**
	 * 根据ID取用车人员(不缓存)
	 * @param id
	 * @return
	 */
	public CsUnitPerson GetCsUnitPersonById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsUnitPerson) this.getSqlMapClientTemplate().queryForObject("getCsUnitPersonById",params);
	}
	

	/**
	 * 保存用车人员
	 * @param csUnitPerson
	 */
	public CsUnitPerson saveCsUnitPerson(CsUnitPerson csUnitPerson)
	{
		csUnitPerson = custom(csUnitPerson);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsUnitPerson", csUnitPerson));
		csUnitPerson = this.GetCsUnitPersonById(id);
		MemCache.setValue(CsUnitPerson.class,id, csUnitPerson.getKeyValue());
		return Trigger.on(CsUnitPerson.class,Trigger.SAVE,csUnitPerson);	
	}

	

	/**
	 * 更新用车人员
	 * @param csUnitPerson
	 */
	public void updateCsUnitPerson(CsUnitPerson csUnitPerson)
	{
		csUnitPerson = custom(csUnitPerson);
		this.getSqlMapClientTemplate().update("updateCsUnitPerson", csUnitPerson);
		csUnitPerson = this.GetCsUnitPersonById(csUnitPerson.getCsupId());
		if(csUnitPerson!=null){
			MemCache.setValue(CsUnitPerson.class,csUnitPerson.getCsupId(), csUnitPerson.getKeyValue());
		}
		Trigger.on(CsUnitPerson.class,Trigger.UPDATE,csUnitPerson);
	}
	/**
	 * 更新用车人员非空字段
	 * @param csUnitPerson
	 */
	public void updateCsUnitPerson$NotNull(CsUnitPerson csUnitPerson)
	{
		csUnitPerson = custom(csUnitPerson);
		this.getSqlMapClientTemplate().update("updateCsUnitPerson$NotNull", csUnitPerson);
		csUnitPerson = this.GetCsUnitPersonById(csUnitPerson.getCsupId());
		if(csUnitPerson!=null){
			MemCache.setValue(CsUnitPerson.class,csUnitPerson.getCsupId(), csUnitPerson.getKeyValue());
		}
		Trigger.on(CsUnitPerson.class,Trigger.UPDATE,csUnitPerson);
	}

	/**
	 * 根据ID删除一个用车人员
	 * @param id
	 */
	public void deleteCsUnitPersonById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsUnitPerson.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsUnitPersonById", params);
		MemCache.deleteValue(CsUnitPerson.class, id);
	}
	/**
	 * 根据ID逻辑删除用车人员 
	 * @param id
	 * @return
	 */
	public void removeCsUnitPersonById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsUnitPerson.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsUnitPersonById", params);
		MemCache.deleteValue(CsUnitPerson.class, id);
	}
	/**
	 * 根据条件更新用车人员
	 * @param values
	 * @param params
	 */
	public void updateCsUnitPersonByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsUnitPersonCount(params)>255){
			MemCache.clearValue(CsUnitPerson.class);
		}else{
			params.put("eval","csup_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsUnitPersonEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsUnitPerson.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsUnitPersonBy", allParams);
		Trigger.on(CsUnitPerson.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除用车人员
	 * @param params
	 */
	public void deleteCsUnitPersonByConfirm(Map params){
		Trigger.on(CsUnitPerson.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsUnitPersonCount(params)>255){
			MemCache.clearValue(CsUnitPerson.class);
		}else{
			params.put("eval","csup_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsUnitPersonEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsUnitPerson.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsUnitPersonBy", params);
	}
	

}