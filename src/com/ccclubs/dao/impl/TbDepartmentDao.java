package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ITbDepartmentDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.TbDepartment;
import com.lazy3q.web.helper.$;

/**
 * 公司部门的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class TbDepartmentDao extends SqlMapClientDaoSupport implements ITbDepartmentDao
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
	private TbDepartment custom(TbDepartment params){
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
	 * 获取所有公司部门
	 * @return
	 */	
	public List<TbDepartment> getTbDepartmentList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<TbDepartment> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getTbDepartmentList", params);
		
		return list;
	}
	
	
	/**
	 * 获取公司部门统计
	 * @return
	 */
	public List getTbDepartmentStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getTbDepartmentStats", params);
	}

	/**
	 * 获取公司部门分页
	 * @return
	 */
	public Page getTbDepartmentPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getTbDepartmentCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getTbDepartmentPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取公司部门数量
	 * @return
	 */	
	public Long getTbDepartmentCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getTbDepartmentEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取公司部门自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getTbDepartmentEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getTbDepartmentEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取公司部门
	 * @param params
	 * @return
	 */
	public TbDepartment getTbDepartment(Map params)
	{		
		params = custom(params);
			
		List<TbDepartment> list=this.getTbDepartmentList(params,1);
		TbDepartment tbDepartment = list.isEmpty()?null:list.get(0);
		
		return tbDepartment;
	}
		
	/**
	 * 根据ID取公司部门
	 * @param id
	 * @return
	 */
	public TbDepartment getTbDepartmentById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		TbDepartment tbDepartment = null;
		if(tbDepartment!=null)
			return tbDepartment;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		tbDepartment = (TbDepartment) this.getSqlMapClientTemplate().queryForObject("getTbDepartmentById",params);
		if(tbDepartment!=null)
			MemCache.setValue(TbDepartment.class,id, tbDepartment.getKeyValue());
		return tbDepartment;
	}
	
	/**
	 * 根据ID取公司部门(不缓存)
	 * @param id
	 * @return
	 */
	public TbDepartment GetTbDepartmentById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (TbDepartment) this.getSqlMapClientTemplate().queryForObject("getTbDepartmentById",params);
	}
	

	/**
	 * 保存公司部门
	 * @param tbDepartment
	 */
	public TbDepartment saveTbDepartment(TbDepartment tbDepartment)
	{
		tbDepartment = custom(tbDepartment);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveTbDepartment", tbDepartment));
		tbDepartment = this.GetTbDepartmentById(id);
		MemCache.setValue(TbDepartment.class,id, tbDepartment.getKeyValue());
		return Trigger.on(TbDepartment.class,Trigger.SAVE,tbDepartment);	
	}

	

	/**
	 * 更新公司部门
	 * @param tbDepartment
	 */
	public void updateTbDepartment(TbDepartment tbDepartment)
	{
		tbDepartment = custom(tbDepartment);
		this.getSqlMapClientTemplate().update("updateTbDepartment", tbDepartment);
		tbDepartment = this.GetTbDepartmentById(tbDepartment.getTbdId());
		if(tbDepartment!=null){
			MemCache.setValue(TbDepartment.class,tbDepartment.getTbdId(), tbDepartment.getKeyValue());
		}
		Trigger.on(TbDepartment.class,Trigger.UPDATE,tbDepartment);
	}
	/**
	 * 更新公司部门非空字段
	 * @param tbDepartment
	 */
	public void updateTbDepartment$NotNull(TbDepartment tbDepartment)
	{
		tbDepartment = custom(tbDepartment);
		this.getSqlMapClientTemplate().update("updateTbDepartment$NotNull", tbDepartment);
		tbDepartment = this.GetTbDepartmentById(tbDepartment.getTbdId());
		if(tbDepartment!=null){
			MemCache.setValue(TbDepartment.class,tbDepartment.getTbdId(), tbDepartment.getKeyValue());
		}
		Trigger.on(TbDepartment.class,Trigger.UPDATE,tbDepartment);
	}

	/**
	 * 根据ID删除一个公司部门
	 * @param id
	 */
	public void deleteTbDepartmentById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(TbDepartment.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteTbDepartmentById", params);
		MemCache.deleteValue(TbDepartment.class, id);
	}
	/**
	 * 根据ID逻辑删除公司部门 
	 * @param id
	 * @return
	 */
	public void removeTbDepartmentById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(TbDepartment.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeTbDepartmentById", params);
		MemCache.deleteValue(TbDepartment.class, id);
	}
	/**
	 * 根据条件更新公司部门
	 * @param values
	 * @param params
	 */
	public void updateTbDepartmentByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getTbDepartmentCount(params)>255){
			MemCache.clearValue(TbDepartment.class);
		}else{
			params.put("eval","tbd_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getTbDepartmentEval", params);
			for(Map map:result){
				MemCache.deleteValue(TbDepartment.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateTbDepartmentBy", allParams);
		Trigger.on(TbDepartment.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除公司部门
	 * @param params
	 */
	public void deleteTbDepartmentByConfirm(Map params){
		Trigger.on(TbDepartment.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getTbDepartmentCount(params)>255){
			MemCache.clearValue(TbDepartment.class);
		}else{
			params.put("eval","tbd_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getTbDepartmentEval", params);
			for(Map map:result){
				MemCache.deleteValue(TbDepartment.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteTbDepartmentBy", params);
	}
	

}