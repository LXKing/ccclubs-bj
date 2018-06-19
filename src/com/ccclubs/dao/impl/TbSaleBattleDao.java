package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ITbSaleBattleDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.TbSaleBattle;
import com.lazy3q.web.helper.$;

/**
 * 销售战役的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class TbSaleBattleDao extends SqlMapClientDaoSupport implements ITbSaleBattleDao
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
	private TbSaleBattle custom(TbSaleBattle params){
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
			"tbsbTitle"			
			,"tbsbDetail"			
			,"tbsbRemark"			
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
	 * 获取所有销售战役
	 * @return
	 */	
	public List<TbSaleBattle> getTbSaleBattleList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<TbSaleBattle> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getTbSaleBattleList", params);
		
		return list;
	}
	
	
	/**
	 * 获取销售战役统计
	 * @return
	 */
	public List getTbSaleBattleStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getTbSaleBattleStats", params);
	}

	/**
	 * 获取销售战役分页
	 * @return
	 */
	public Page getTbSaleBattlePage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getTbSaleBattleCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getTbSaleBattlePage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取销售战役数量
	 * @return
	 */	
	public Long getTbSaleBattleCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getTbSaleBattleEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取销售战役自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getTbSaleBattleEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getTbSaleBattleEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取销售战役
	 * @param params
	 * @return
	 */
	public TbSaleBattle getTbSaleBattle(Map params)
	{		
		params = custom(params);
			
		List<TbSaleBattle> list=this.getTbSaleBattleList(params,1);
		TbSaleBattle tbSaleBattle = list.isEmpty()?null:list.get(0);
		
		return tbSaleBattle;
	}
		
	/**
	 * 根据ID取销售战役
	 * @param id
	 * @return
	 */
	public TbSaleBattle getTbSaleBattleById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		TbSaleBattle tbSaleBattle = null;
		if(tbSaleBattle!=null)
			return tbSaleBattle;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		tbSaleBattle = (TbSaleBattle) this.getSqlMapClientTemplate().queryForObject("getTbSaleBattleById",params);
		if(tbSaleBattle!=null)
			MemCache.setValue(TbSaleBattle.class,id, tbSaleBattle.getKeyValue());
		return tbSaleBattle;
	}
	
	/**
	 * 根据ID取销售战役(不缓存)
	 * @param id
	 * @return
	 */
	public TbSaleBattle GetTbSaleBattleById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (TbSaleBattle) this.getSqlMapClientTemplate().queryForObject("getTbSaleBattleById",params);
	}
	

	/**
	 * 保存销售战役
	 * @param tbSaleBattle
	 */
	public TbSaleBattle saveTbSaleBattle(TbSaleBattle tbSaleBattle)
	{
		tbSaleBattle = custom(tbSaleBattle);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveTbSaleBattle", tbSaleBattle));
		tbSaleBattle = this.GetTbSaleBattleById(id);
		MemCache.setValue(TbSaleBattle.class,id, tbSaleBattle.getKeyValue());
		return Trigger.on(TbSaleBattle.class,Trigger.SAVE,tbSaleBattle);	
	}

	

	/**
	 * 更新销售战役
	 * @param tbSaleBattle
	 */
	public void updateTbSaleBattle(TbSaleBattle tbSaleBattle)
	{
		tbSaleBattle = custom(tbSaleBattle);
		this.getSqlMapClientTemplate().update("updateTbSaleBattle", tbSaleBattle);
		tbSaleBattle = this.GetTbSaleBattleById(tbSaleBattle.getTbsbId());
		if(tbSaleBattle!=null){
			MemCache.setValue(TbSaleBattle.class,tbSaleBattle.getTbsbId(), tbSaleBattle.getKeyValue());
		}
		Trigger.on(TbSaleBattle.class,Trigger.UPDATE,tbSaleBattle);
	}
	/**
	 * 更新销售战役非空字段
	 * @param tbSaleBattle
	 */
	public void updateTbSaleBattle$NotNull(TbSaleBattle tbSaleBattle)
	{
		tbSaleBattle = custom(tbSaleBattle);
		this.getSqlMapClientTemplate().update("updateTbSaleBattle$NotNull", tbSaleBattle);
		tbSaleBattle = this.GetTbSaleBattleById(tbSaleBattle.getTbsbId());
		if(tbSaleBattle!=null){
			MemCache.setValue(TbSaleBattle.class,tbSaleBattle.getTbsbId(), tbSaleBattle.getKeyValue());
		}
		Trigger.on(TbSaleBattle.class,Trigger.UPDATE,tbSaleBattle);
	}

	/**
	 * 根据ID删除一个销售战役
	 * @param id
	 */
	public void deleteTbSaleBattleById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(TbSaleBattle.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteTbSaleBattleById", params);
		MemCache.deleteValue(TbSaleBattle.class, id);
	}
	/**
	 * 根据ID逻辑删除销售战役 
	 * @param id
	 * @return
	 */
	public void removeTbSaleBattleById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(TbSaleBattle.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeTbSaleBattleById", params);
		MemCache.deleteValue(TbSaleBattle.class, id);
	}
	/**
	 * 根据条件更新销售战役
	 * @param values
	 * @param params
	 */
	public void updateTbSaleBattleByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getTbSaleBattleCount(params)>255){
			MemCache.clearValue(TbSaleBattle.class);
		}else{
			params.put("eval","tbsb_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getTbSaleBattleEval", params);
			for(Map map:result){
				MemCache.deleteValue(TbSaleBattle.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateTbSaleBattleBy", allParams);
		Trigger.on(TbSaleBattle.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除销售战役
	 * @param params
	 */
	public void deleteTbSaleBattleByConfirm(Map params){
		Trigger.on(TbSaleBattle.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getTbSaleBattleCount(params)>255){
			MemCache.clearValue(TbSaleBattle.class);
		}else{
			params.put("eval","tbsb_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getTbSaleBattleEval", params);
			for(Map map:result){
				MemCache.deleteValue(TbSaleBattle.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteTbSaleBattleBy", params);
	}
	

}