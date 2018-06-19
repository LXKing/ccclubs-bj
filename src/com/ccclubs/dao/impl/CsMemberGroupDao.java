package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsMemberGroupDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsMemberGroup;
import com.lazy3q.web.helper.$;

/**
 * 会员组织的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsMemberGroupDao extends SqlMapClientDaoSupport implements ICsMemberGroupDao
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
	private CsMemberGroup custom(CsMemberGroup params){
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
			"csmgName"			
			,"csmgRemark"			
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
	 * 获取所有会员组织
	 * @return
	 */	
	public List<CsMemberGroup> getCsMemberGroupList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsMemberGroup> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsMemberGroupList", params);
		
		return list;
	}
	
	
	/**
	 * 获取会员组织统计
	 * @return
	 */
	public List getCsMemberGroupStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsMemberGroupStats", params);
	}

	/**
	 * 获取会员组织分页
	 * @return
	 */
	public Page getCsMemberGroupPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsMemberGroupCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsMemberGroupPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取会员组织数量
	 * @return
	 */	
	public Long getCsMemberGroupCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsMemberGroupEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取会员组织自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsMemberGroupEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsMemberGroupEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取会员组织
	 * @param params
	 * @return
	 */
	public CsMemberGroup getCsMemberGroup(Map params)
	{		
		params = custom(params);
			
		List<CsMemberGroup> list=this.getCsMemberGroupList(params,1);
		CsMemberGroup csMemberGroup = list.isEmpty()?null:list.get(0);
		
		return csMemberGroup;
	}
		
	/**
	 * 根据ID取会员组织
	 * @param id
	 * @return
	 */
	public CsMemberGroup getCsMemberGroupById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsMemberGroup csMemberGroup = null;
		if(csMemberGroup!=null)
			return csMemberGroup;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csMemberGroup = (CsMemberGroup) this.getSqlMapClientTemplate().queryForObject("getCsMemberGroupById",params);
		if(csMemberGroup!=null)
			MemCache.setValue(CsMemberGroup.class,id, csMemberGroup.getKeyValue());
		return csMemberGroup;
	}
	
	/**
	 * 根据ID取会员组织(不缓存)
	 * @param id
	 * @return
	 */
	public CsMemberGroup GetCsMemberGroupById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsMemberGroup) this.getSqlMapClientTemplate().queryForObject("getCsMemberGroupById",params);
	}
	

	/**
	 * 保存会员组织
	 * @param csMemberGroup
	 */
	public CsMemberGroup saveCsMemberGroup(CsMemberGroup csMemberGroup)
	{
		csMemberGroup = custom(csMemberGroup);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsMemberGroup", csMemberGroup));
		csMemberGroup = this.GetCsMemberGroupById(id);
		MemCache.setValue(CsMemberGroup.class,id, csMemberGroup.getKeyValue());
		return Trigger.on(CsMemberGroup.class,Trigger.SAVE,csMemberGroup);	
	}

	

	/**
	 * 更新会员组织
	 * @param csMemberGroup
	 */
	public void updateCsMemberGroup(CsMemberGroup csMemberGroup)
	{
		csMemberGroup = custom(csMemberGroup);
		this.getSqlMapClientTemplate().update("updateCsMemberGroup", csMemberGroup);
		csMemberGroup = this.GetCsMemberGroupById(csMemberGroup.getCsmgId());
		if(csMemberGroup!=null){
			MemCache.setValue(CsMemberGroup.class,csMemberGroup.getCsmgId(), csMemberGroup.getKeyValue());
		}
		Trigger.on(CsMemberGroup.class,Trigger.UPDATE,csMemberGroup);
	}
	/**
	 * 更新会员组织非空字段
	 * @param csMemberGroup
	 */
	public void updateCsMemberGroup$NotNull(CsMemberGroup csMemberGroup)
	{
		csMemberGroup = custom(csMemberGroup);
		this.getSqlMapClientTemplate().update("updateCsMemberGroup$NotNull", csMemberGroup);
		csMemberGroup = this.GetCsMemberGroupById(csMemberGroup.getCsmgId());
		if(csMemberGroup!=null){
			MemCache.setValue(CsMemberGroup.class,csMemberGroup.getCsmgId(), csMemberGroup.getKeyValue());
		}
		Trigger.on(CsMemberGroup.class,Trigger.UPDATE,csMemberGroup);
	}

	/**
	 * 根据ID删除一个会员组织
	 * @param id
	 */
	public void deleteCsMemberGroupById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsMemberGroup.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsMemberGroupById", params);
		MemCache.deleteValue(CsMemberGroup.class, id);
	}
	/**
	 * 根据ID逻辑删除会员组织 
	 * @param id
	 * @return
	 */
	public void removeCsMemberGroupById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsMemberGroup.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsMemberGroupById", params);
		MemCache.deleteValue(CsMemberGroup.class, id);
	}
	/**
	 * 根据条件更新会员组织
	 * @param values
	 * @param params
	 */
	public void updateCsMemberGroupByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsMemberGroupCount(params)>255){
			MemCache.clearValue(CsMemberGroup.class);
		}else{
			params.put("eval","csmg_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsMemberGroupEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsMemberGroup.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsMemberGroupBy", allParams);
		Trigger.on(CsMemberGroup.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除会员组织
	 * @param params
	 */
	public void deleteCsMemberGroupByConfirm(Map params){
		Trigger.on(CsMemberGroup.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsMemberGroupCount(params)>255){
			MemCache.clearValue(CsMemberGroup.class);
		}else{
			params.put("eval","csmg_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsMemberGroupEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsMemberGroup.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsMemberGroupBy", params);
	}
	

}