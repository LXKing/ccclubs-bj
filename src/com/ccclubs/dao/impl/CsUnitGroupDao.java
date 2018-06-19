package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsUnitGroupDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsUnitGroup;
import com.lazy3q.web.helper.$;

/**
 * 分组信息的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsUnitGroupDao extends SqlMapClientDaoSupport implements ICsUnitGroupDao
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
	private CsUnitGroup custom(CsUnitGroup params){
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
			"csugName"			
			,"csugRemark"			
			,"csugMemo"			
			,"csugData"			
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
	 * 获取所有分组信息
	 * @return
	 */	
	public List<CsUnitGroup> getCsUnitGroupList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsUnitGroup> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsUnitGroupList", params);
		
		return list;
	}
	
	
	/**
	 * 获取分组信息统计
	 * @return
	 */
	public List getCsUnitGroupStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsUnitGroupStats", params);
	}

	/**
	 * 获取分组信息分页
	 * @return
	 */
	public Page getCsUnitGroupPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsUnitGroupCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsUnitGroupPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取分组信息数量
	 * @return
	 */	
	public Long getCsUnitGroupCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsUnitGroupEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取分组信息自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsUnitGroupEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsUnitGroupEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取分组信息
	 * @param params
	 * @return
	 */
	public CsUnitGroup getCsUnitGroup(Map params)
	{		
		params = custom(params);
			
		List<CsUnitGroup> list=this.getCsUnitGroupList(params,1);
		CsUnitGroup csUnitGroup = list.isEmpty()?null:list.get(0);
		
		return csUnitGroup;
	}
		
	/**
	 * 根据ID取分组信息
	 * @param id
	 * @return
	 */
	public CsUnitGroup getCsUnitGroupById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsUnitGroup csUnitGroup = null;
		if(csUnitGroup!=null)
			return csUnitGroup;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csUnitGroup = (CsUnitGroup) this.getSqlMapClientTemplate().queryForObject("getCsUnitGroupById",params);
		if(csUnitGroup!=null)
			MemCache.setValue(CsUnitGroup.class,id, csUnitGroup.getKeyValue());
		return csUnitGroup;
	}
	
	/**
	 * 根据ID取分组信息(不缓存)
	 * @param id
	 * @return
	 */
	public CsUnitGroup GetCsUnitGroupById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsUnitGroup) this.getSqlMapClientTemplate().queryForObject("getCsUnitGroupById",params);
	}
	

	/**
	 * 保存分组信息
	 * @param csUnitGroup
	 */
	public CsUnitGroup saveCsUnitGroup(CsUnitGroup csUnitGroup)
	{
		csUnitGroup = custom(csUnitGroup);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsUnitGroup", csUnitGroup));
		csUnitGroup = this.GetCsUnitGroupById(id);
		MemCache.setValue(CsUnitGroup.class,id, csUnitGroup.getKeyValue());
		return Trigger.on(CsUnitGroup.class,Trigger.SAVE,csUnitGroup);	
	}

	

	/**
	 * 更新分组信息
	 * @param csUnitGroup
	 */
	public void updateCsUnitGroup(CsUnitGroup csUnitGroup)
	{
		csUnitGroup = custom(csUnitGroup);
		this.getSqlMapClientTemplate().update("updateCsUnitGroup", csUnitGroup);
		csUnitGroup = this.GetCsUnitGroupById(csUnitGroup.getCsugId());
		if(csUnitGroup!=null){
			MemCache.setValue(CsUnitGroup.class,csUnitGroup.getCsugId(), csUnitGroup.getKeyValue());
		}
		Trigger.on(CsUnitGroup.class,Trigger.UPDATE,csUnitGroup);
	}
	/**
	 * 更新分组信息非空字段
	 * @param csUnitGroup
	 */
	public void updateCsUnitGroup$NotNull(CsUnitGroup csUnitGroup)
	{
		csUnitGroup = custom(csUnitGroup);
		this.getSqlMapClientTemplate().update("updateCsUnitGroup$NotNull", csUnitGroup);
		csUnitGroup = this.GetCsUnitGroupById(csUnitGroup.getCsugId());
		if(csUnitGroup!=null){
			MemCache.setValue(CsUnitGroup.class,csUnitGroup.getCsugId(), csUnitGroup.getKeyValue());
		}
		Trigger.on(CsUnitGroup.class,Trigger.UPDATE,csUnitGroup);
	}

	/**
	 * 根据ID删除一个分组信息
	 * @param id
	 */
	public void deleteCsUnitGroupById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsUnitGroup.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsUnitGroupById", params);
		MemCache.deleteValue(CsUnitGroup.class, id);
	}
	/**
	 * 根据ID逻辑删除分组信息 
	 * @param id
	 * @return
	 */
	public void removeCsUnitGroupById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsUnitGroup.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsUnitGroupById", params);
		MemCache.deleteValue(CsUnitGroup.class, id);
	}
	/**
	 * 根据条件更新分组信息
	 * @param values
	 * @param params
	 */
	public void updateCsUnitGroupByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsUnitGroupCount(params)>255){
			MemCache.clearValue(CsUnitGroup.class);
		}else{
			params.put("eval","csug_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsUnitGroupEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsUnitGroup.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsUnitGroupBy", allParams);
		Trigger.on(CsUnitGroup.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除分组信息
	 * @param params
	 */
	public void deleteCsUnitGroupByConfirm(Map params){
		Trigger.on(CsUnitGroup.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsUnitGroupCount(params)>255){
			MemCache.clearValue(CsUnitGroup.class);
		}else{
			params.put("eval","csug_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsUnitGroupEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsUnitGroup.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsUnitGroupBy", params);
	}
	

}