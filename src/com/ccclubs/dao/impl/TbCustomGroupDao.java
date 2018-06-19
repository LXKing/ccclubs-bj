package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ITbCustomGroupDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.TbCustomGroup;
import com.lazy3q.web.helper.$;

/**
 * 客户组织的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class TbCustomGroupDao extends SqlMapClientDaoSupport implements ITbCustomGroupDao
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
	private TbCustomGroup custom(TbCustomGroup params){
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
			"tbcgName"			
			,"tbcgFullName"			
			,"tbcgAddress"			
			,"tbcgDetail"			
			,"tbcgRemark"			
			,"tbcgMemo"			
			,"tbcgData"			
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
	 * 获取所有客户组织
	 * @return
	 */	
	public List<TbCustomGroup> getTbCustomGroupList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<TbCustomGroup> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getTbCustomGroupList", params);
		
		return list;
	}
	
	
	/**
	 * 获取客户组织统计
	 * @return
	 */
	public List getTbCustomGroupStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getTbCustomGroupStats", params);
	}

	/**
	 * 获取客户组织分页
	 * @return
	 */
	public Page getTbCustomGroupPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getTbCustomGroupCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getTbCustomGroupPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取客户组织数量
	 * @return
	 */	
	public Long getTbCustomGroupCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getTbCustomGroupEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取客户组织自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getTbCustomGroupEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getTbCustomGroupEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取客户组织
	 * @param params
	 * @return
	 */
	public TbCustomGroup getTbCustomGroup(Map params)
	{		
		params = custom(params);
			
		List<TbCustomGroup> list=this.getTbCustomGroupList(params,1);
		TbCustomGroup tbCustomGroup = list.isEmpty()?null:list.get(0);
		
		return tbCustomGroup;
	}
		
	/**
	 * 根据ID取客户组织
	 * @param id
	 * @return
	 */
	public TbCustomGroup getTbCustomGroupById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		TbCustomGroup tbCustomGroup = null;
		if(tbCustomGroup!=null)
			return tbCustomGroup;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		tbCustomGroup = (TbCustomGroup) this.getSqlMapClientTemplate().queryForObject("getTbCustomGroupById",params);
		if(tbCustomGroup!=null)
			MemCache.setValue(TbCustomGroup.class,id, tbCustomGroup.getKeyValue());
		return tbCustomGroup;
	}
	
	/**
	 * 根据ID取客户组织(不缓存)
	 * @param id
	 * @return
	 */
	public TbCustomGroup GetTbCustomGroupById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (TbCustomGroup) this.getSqlMapClientTemplate().queryForObject("getTbCustomGroupById",params);
	}
	

	/**
	 * 保存客户组织
	 * @param tbCustomGroup
	 */
	public TbCustomGroup saveTbCustomGroup(TbCustomGroup tbCustomGroup)
	{
		tbCustomGroup = custom(tbCustomGroup);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveTbCustomGroup", tbCustomGroup));
		tbCustomGroup = this.GetTbCustomGroupById(id);
		MemCache.setValue(TbCustomGroup.class,id, tbCustomGroup.getKeyValue());
		return Trigger.on(TbCustomGroup.class,Trigger.SAVE,tbCustomGroup);	
	}

	

	/**
	 * 更新客户组织
	 * @param tbCustomGroup
	 */
	public void updateTbCustomGroup(TbCustomGroup tbCustomGroup)
	{
		tbCustomGroup = custom(tbCustomGroup);
		this.getSqlMapClientTemplate().update("updateTbCustomGroup", tbCustomGroup);
		tbCustomGroup = this.GetTbCustomGroupById(tbCustomGroup.getTbcgId());
		if(tbCustomGroup!=null){
			MemCache.setValue(TbCustomGroup.class,tbCustomGroup.getTbcgId(), tbCustomGroup.getKeyValue());
		}
		Trigger.on(TbCustomGroup.class,Trigger.UPDATE,tbCustomGroup);
	}
	/**
	 * 更新客户组织非空字段
	 * @param tbCustomGroup
	 */
	public void updateTbCustomGroup$NotNull(TbCustomGroup tbCustomGroup)
	{
		tbCustomGroup = custom(tbCustomGroup);
		this.getSqlMapClientTemplate().update("updateTbCustomGroup$NotNull", tbCustomGroup);
		tbCustomGroup = this.GetTbCustomGroupById(tbCustomGroup.getTbcgId());
		if(tbCustomGroup!=null){
			MemCache.setValue(TbCustomGroup.class,tbCustomGroup.getTbcgId(), tbCustomGroup.getKeyValue());
		}
		Trigger.on(TbCustomGroup.class,Trigger.UPDATE,tbCustomGroup);
	}

	/**
	 * 根据ID删除一个客户组织
	 * @param id
	 */
	public void deleteTbCustomGroupById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(TbCustomGroup.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteTbCustomGroupById", params);
		MemCache.deleteValue(TbCustomGroup.class, id);
	}
	/**
	 * 根据ID逻辑删除客户组织 
	 * @param id
	 * @return
	 */
	public void removeTbCustomGroupById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(TbCustomGroup.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeTbCustomGroupById", params);
		MemCache.deleteValue(TbCustomGroup.class, id);
	}
	/**
	 * 根据条件更新客户组织
	 * @param values
	 * @param params
	 */
	public void updateTbCustomGroupByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getTbCustomGroupCount(params)>255){
			MemCache.clearValue(TbCustomGroup.class);
		}else{
			params.put("eval","tbcg_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getTbCustomGroupEval", params);
			for(Map map:result){
				MemCache.deleteValue(TbCustomGroup.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateTbCustomGroupBy", allParams);
		Trigger.on(TbCustomGroup.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除客户组织
	 * @param params
	 */
	public void deleteTbCustomGroupByConfirm(Map params){
		Trigger.on(TbCustomGroup.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getTbCustomGroupCount(params)>255){
			MemCache.clearValue(TbCustomGroup.class);
		}else{
			params.put("eval","tbcg_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getTbCustomGroupEval", params);
			for(Map map:result){
				MemCache.deleteValue(TbCustomGroup.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteTbCustomGroupBy", params);
	}
	

}