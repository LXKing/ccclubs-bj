package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ITbSolveDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.TbSolve;
import com.lazy3q.web.helper.$;

/**
 * 问题处理记录的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class TbSolveDao extends SqlMapClientDaoSupport implements ITbSolveDao
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
	private TbSolve custom(TbSolve params){
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
			"tbsDesc"			
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
	 * 获取所有问题处理记录
	 * @return
	 */	
	public List<TbSolve> getTbSolveList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<TbSolve> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getTbSolveList", params);
		
		return list;
	}
	
	
	/**
	 * 获取问题处理记录统计
	 * @return
	 */
	public List getTbSolveStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getTbSolveStats", params);
	}

	/**
	 * 获取问题处理记录分页
	 * @return
	 */
	public Page getTbSolvePage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getTbSolveCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getTbSolvePage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取问题处理记录数量
	 * @return
	 */	
	public Long getTbSolveCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getTbSolveEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取问题处理记录自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getTbSolveEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getTbSolveEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取问题处理记录
	 * @param params
	 * @return
	 */
	public TbSolve getTbSolve(Map params)
	{		
		params = custom(params);
			
		List<TbSolve> list=this.getTbSolveList(params,1);
		TbSolve tbSolve = list.isEmpty()?null:list.get(0);
		
		return tbSolve;
	}
		
	/**
	 * 根据ID取问题处理记录
	 * @param id
	 * @return
	 */
	public TbSolve getTbSolveById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		TbSolve tbSolve = null;
		if(tbSolve!=null)
			return tbSolve;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		tbSolve = (TbSolve) this.getSqlMapClientTemplate().queryForObject("getTbSolveById",params);
		if(tbSolve!=null)
			MemCache.setValue(TbSolve.class,id, tbSolve.getKeyValue());
		return tbSolve;
	}
	
	/**
	 * 根据ID取问题处理记录(不缓存)
	 * @param id
	 * @return
	 */
	public TbSolve GetTbSolveById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (TbSolve) this.getSqlMapClientTemplate().queryForObject("getTbSolveById",params);
	}
	

	/**
	 * 保存问题处理记录
	 * @param tbSolve
	 */
	public TbSolve saveTbSolve(TbSolve tbSolve)
	{
		tbSolve = custom(tbSolve);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveTbSolve", tbSolve));
		tbSolve = this.GetTbSolveById(id);
		MemCache.setValue(TbSolve.class,id, tbSolve.getKeyValue());
		return Trigger.on(TbSolve.class,Trigger.SAVE,tbSolve);	
	}

	

	/**
	 * 更新问题处理记录
	 * @param tbSolve
	 */
	public void updateTbSolve(TbSolve tbSolve)
	{
		tbSolve = custom(tbSolve);
		this.getSqlMapClientTemplate().update("updateTbSolve", tbSolve);
		tbSolve = this.GetTbSolveById(tbSolve.getTbsId());
		if(tbSolve!=null){
			MemCache.setValue(TbSolve.class,tbSolve.getTbsId(), tbSolve.getKeyValue());
		}
		Trigger.on(TbSolve.class,Trigger.UPDATE,tbSolve);
	}
	/**
	 * 更新问题处理记录非空字段
	 * @param tbSolve
	 */
	public void updateTbSolve$NotNull(TbSolve tbSolve)
	{
		tbSolve = custom(tbSolve);
		this.getSqlMapClientTemplate().update("updateTbSolve$NotNull", tbSolve);
		tbSolve = this.GetTbSolveById(tbSolve.getTbsId());
		if(tbSolve!=null){
			MemCache.setValue(TbSolve.class,tbSolve.getTbsId(), tbSolve.getKeyValue());
		}
		Trigger.on(TbSolve.class,Trigger.UPDATE,tbSolve);
	}

	/**
	 * 根据ID删除一个问题处理记录
	 * @param id
	 */
	public void deleteTbSolveById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(TbSolve.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteTbSolveById", params);
		MemCache.deleteValue(TbSolve.class, id);
	}
	/**
	 * 根据ID逻辑删除问题处理记录 
	 * @param id
	 * @return
	 */
	public void removeTbSolveById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(TbSolve.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeTbSolveById", params);
		MemCache.deleteValue(TbSolve.class, id);
	}
	/**
	 * 根据条件更新问题处理记录
	 * @param values
	 * @param params
	 */
	public void updateTbSolveByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getTbSolveCount(params)>255){
			MemCache.clearValue(TbSolve.class);
		}else{
			params.put("eval","tbs_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getTbSolveEval", params);
			for(Map map:result){
				MemCache.deleteValue(TbSolve.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateTbSolveBy", allParams);
		Trigger.on(TbSolve.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除问题处理记录
	 * @param params
	 */
	public void deleteTbSolveByConfirm(Map params){
		Trigger.on(TbSolve.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getTbSolveCount(params)>255){
			MemCache.clearValue(TbSolve.class);
		}else{
			params.put("eval","tbs_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getTbSolveEval", params);
			for(Map map:result){
				MemCache.deleteValue(TbSolve.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteTbSolveBy", params);
	}
	

}