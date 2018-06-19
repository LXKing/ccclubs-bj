package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ITbAbAffirmDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.TbAbAffirm;
import com.lazy3q.web.helper.$;

/**
 * 异借确认的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class TbAbAffirmDao extends SqlMapClientDaoSupport implements ITbAbAffirmDao
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
	private TbAbAffirm custom(TbAbAffirm params){
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
			"tbaaRemark"			
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
	 * 获取所有异借确认
	 * @return
	 */	
	public List<TbAbAffirm> getTbAbAffirmList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<TbAbAffirm> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getTbAbAffirmList", params);
		
		return list;
	}
	
	
	/**
	 * 获取异借确认统计
	 * @return
	 */
	public List getTbAbAffirmStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getTbAbAffirmStats", params);
	}

	/**
	 * 获取异借确认分页
	 * @return
	 */
	public Page getTbAbAffirmPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getTbAbAffirmCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getTbAbAffirmPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取异借确认数量
	 * @return
	 */	
	public Long getTbAbAffirmCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getTbAbAffirmEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取异借确认自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getTbAbAffirmEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getTbAbAffirmEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取异借确认
	 * @param params
	 * @return
	 */
	public TbAbAffirm getTbAbAffirm(Map params)
	{		
		params = custom(params);
			
		List<TbAbAffirm> list=this.getTbAbAffirmList(params,1);
		TbAbAffirm tbAbAffirm = list.isEmpty()?null:list.get(0);
		
		return tbAbAffirm;
	}
		
	/**
	 * 根据ID取异借确认
	 * @param id
	 * @return
	 */
	public TbAbAffirm getTbAbAffirmById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		TbAbAffirm tbAbAffirm = null;
		if(tbAbAffirm!=null)
			return tbAbAffirm;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		tbAbAffirm = (TbAbAffirm) this.getSqlMapClientTemplate().queryForObject("getTbAbAffirmById",params);
		if(tbAbAffirm!=null)
			MemCache.setValue(TbAbAffirm.class,id, tbAbAffirm.getKeyValue());
		return tbAbAffirm;
	}
	
	/**
	 * 根据ID取异借确认(不缓存)
	 * @param id
	 * @return
	 */
	public TbAbAffirm GetTbAbAffirmById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (TbAbAffirm) this.getSqlMapClientTemplate().queryForObject("getTbAbAffirmById",params);
	}
	

	/**
	 * 保存异借确认
	 * @param tbAbAffirm
	 */
	public TbAbAffirm saveTbAbAffirm(TbAbAffirm tbAbAffirm)
	{
		tbAbAffirm = custom(tbAbAffirm);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveTbAbAffirm", tbAbAffirm));
		tbAbAffirm = this.GetTbAbAffirmById(id);
		MemCache.setValue(TbAbAffirm.class,id, tbAbAffirm.getKeyValue());
		return Trigger.on(TbAbAffirm.class,Trigger.SAVE,tbAbAffirm);	
	}

	

	/**
	 * 更新异借确认
	 * @param tbAbAffirm
	 */
	public void updateTbAbAffirm(TbAbAffirm tbAbAffirm)
	{
		tbAbAffirm = custom(tbAbAffirm);
		this.getSqlMapClientTemplate().update("updateTbAbAffirm", tbAbAffirm);
		tbAbAffirm = this.GetTbAbAffirmById(tbAbAffirm.getTbaaId());
		if(tbAbAffirm!=null){
			MemCache.setValue(TbAbAffirm.class,tbAbAffirm.getTbaaId(), tbAbAffirm.getKeyValue());
		}
		Trigger.on(TbAbAffirm.class,Trigger.UPDATE,tbAbAffirm);
	}
	/**
	 * 更新异借确认非空字段
	 * @param tbAbAffirm
	 */
	public void updateTbAbAffirm$NotNull(TbAbAffirm tbAbAffirm)
	{
		tbAbAffirm = custom(tbAbAffirm);
		this.getSqlMapClientTemplate().update("updateTbAbAffirm$NotNull", tbAbAffirm);
		tbAbAffirm = this.GetTbAbAffirmById(tbAbAffirm.getTbaaId());
		if(tbAbAffirm!=null){
			MemCache.setValue(TbAbAffirm.class,tbAbAffirm.getTbaaId(), tbAbAffirm.getKeyValue());
		}
		Trigger.on(TbAbAffirm.class,Trigger.UPDATE,tbAbAffirm);
	}

	/**
	 * 根据ID删除一个异借确认
	 * @param id
	 */
	public void deleteTbAbAffirmById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(TbAbAffirm.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteTbAbAffirmById", params);
		MemCache.deleteValue(TbAbAffirm.class, id);
	}
	/**
	 * 根据ID逻辑删除异借确认 
	 * @param id
	 * @return
	 */
	public void removeTbAbAffirmById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(TbAbAffirm.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeTbAbAffirmById", params);
		MemCache.deleteValue(TbAbAffirm.class, id);
	}
	/**
	 * 根据条件更新异借确认
	 * @param values
	 * @param params
	 */
	public void updateTbAbAffirmByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getTbAbAffirmCount(params)>255){
			MemCache.clearValue(TbAbAffirm.class);
		}else{
			params.put("eval","tbaa_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getTbAbAffirmEval", params);
			for(Map map:result){
				MemCache.deleteValue(TbAbAffirm.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateTbAbAffirmBy", allParams);
		Trigger.on(TbAbAffirm.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除异借确认
	 * @param params
	 */
	public void deleteTbAbAffirmByConfirm(Map params){
		Trigger.on(TbAbAffirm.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getTbAbAffirmCount(params)>255){
			MemCache.clearValue(TbAbAffirm.class);
		}else{
			params.put("eval","tbaa_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getTbAbAffirmEval", params);
			for(Map map:result){
				MemCache.deleteValue(TbAbAffirm.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteTbAbAffirmBy", params);
	}
	

}