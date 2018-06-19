package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ITbSaleQuoteDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.TbSaleQuote;
import com.lazy3q.web.helper.$;

/**
 * 跟单报价的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class TbSaleQuoteDao extends SqlMapClientDaoSupport implements ITbSaleQuoteDao
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
	private TbSaleQuote custom(TbSaleQuote params){
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
			"tbsqTitle"			
			,"tbsqDetail"			
			,"tbsqFile"			
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
	 * 获取所有跟单报价
	 * @return
	 */	
	public List<TbSaleQuote> getTbSaleQuoteList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<TbSaleQuote> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getTbSaleQuoteList", params);
		
		return list;
	}
	
	
	/**
	 * 获取跟单报价统计
	 * @return
	 */
	public List getTbSaleQuoteStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getTbSaleQuoteStats", params);
	}

	/**
	 * 获取跟单报价分页
	 * @return
	 */
	public Page getTbSaleQuotePage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getTbSaleQuoteCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getTbSaleQuotePage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取跟单报价数量
	 * @return
	 */	
	public Long getTbSaleQuoteCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getTbSaleQuoteEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取跟单报价自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getTbSaleQuoteEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getTbSaleQuoteEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取跟单报价
	 * @param params
	 * @return
	 */
	public TbSaleQuote getTbSaleQuote(Map params)
	{		
		params = custom(params);
			
		List<TbSaleQuote> list=this.getTbSaleQuoteList(params,1);
		TbSaleQuote tbSaleQuote = list.isEmpty()?null:list.get(0);
		
		return tbSaleQuote;
	}
		
	/**
	 * 根据ID取跟单报价
	 * @param id
	 * @return
	 */
	public TbSaleQuote getTbSaleQuoteById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		TbSaleQuote tbSaleQuote = null;
		if(tbSaleQuote!=null)
			return tbSaleQuote;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		tbSaleQuote = (TbSaleQuote) this.getSqlMapClientTemplate().queryForObject("getTbSaleQuoteById",params);
		if(tbSaleQuote!=null)
			MemCache.setValue(TbSaleQuote.class,id, tbSaleQuote.getKeyValue());
		return tbSaleQuote;
	}
	
	/**
	 * 根据ID取跟单报价(不缓存)
	 * @param id
	 * @return
	 */
	public TbSaleQuote GetTbSaleQuoteById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (TbSaleQuote) this.getSqlMapClientTemplate().queryForObject("getTbSaleQuoteById",params);
	}
	

	/**
	 * 保存跟单报价
	 * @param tbSaleQuote
	 */
	public TbSaleQuote saveTbSaleQuote(TbSaleQuote tbSaleQuote)
	{
		tbSaleQuote = custom(tbSaleQuote);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveTbSaleQuote", tbSaleQuote));
		tbSaleQuote = this.GetTbSaleQuoteById(id);
		MemCache.setValue(TbSaleQuote.class,id, tbSaleQuote.getKeyValue());
		return Trigger.on(TbSaleQuote.class,Trigger.SAVE,tbSaleQuote);	
	}

	

	/**
	 * 更新跟单报价
	 * @param tbSaleQuote
	 */
	public void updateTbSaleQuote(TbSaleQuote tbSaleQuote)
	{
		tbSaleQuote = custom(tbSaleQuote);
		this.getSqlMapClientTemplate().update("updateTbSaleQuote", tbSaleQuote);
		tbSaleQuote = this.GetTbSaleQuoteById(tbSaleQuote.getTbsqId());
		if(tbSaleQuote!=null){
			MemCache.setValue(TbSaleQuote.class,tbSaleQuote.getTbsqId(), tbSaleQuote.getKeyValue());
		}
		Trigger.on(TbSaleQuote.class,Trigger.UPDATE,tbSaleQuote);
	}
	/**
	 * 更新跟单报价非空字段
	 * @param tbSaleQuote
	 */
	public void updateTbSaleQuote$NotNull(TbSaleQuote tbSaleQuote)
	{
		tbSaleQuote = custom(tbSaleQuote);
		this.getSqlMapClientTemplate().update("updateTbSaleQuote$NotNull", tbSaleQuote);
		tbSaleQuote = this.GetTbSaleQuoteById(tbSaleQuote.getTbsqId());
		if(tbSaleQuote!=null){
			MemCache.setValue(TbSaleQuote.class,tbSaleQuote.getTbsqId(), tbSaleQuote.getKeyValue());
		}
		Trigger.on(TbSaleQuote.class,Trigger.UPDATE,tbSaleQuote);
	}

	/**
	 * 根据ID删除一个跟单报价
	 * @param id
	 */
	public void deleteTbSaleQuoteById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(TbSaleQuote.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteTbSaleQuoteById", params);
		MemCache.deleteValue(TbSaleQuote.class, id);
	}
	/**
	 * 根据ID逻辑删除跟单报价 
	 * @param id
	 * @return
	 */
	public void removeTbSaleQuoteById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(TbSaleQuote.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeTbSaleQuoteById", params);
		MemCache.deleteValue(TbSaleQuote.class, id);
	}
	/**
	 * 根据条件更新跟单报价
	 * @param values
	 * @param params
	 */
	public void updateTbSaleQuoteByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getTbSaleQuoteCount(params)>255){
			MemCache.clearValue(TbSaleQuote.class);
		}else{
			params.put("eval","tbsq_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getTbSaleQuoteEval", params);
			for(Map map:result){
				MemCache.deleteValue(TbSaleQuote.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateTbSaleQuoteBy", allParams);
		Trigger.on(TbSaleQuote.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除跟单报价
	 * @param params
	 */
	public void deleteTbSaleQuoteByConfirm(Map params){
		Trigger.on(TbSaleQuote.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getTbSaleQuoteCount(params)>255){
			MemCache.clearValue(TbSaleQuote.class);
		}else{
			params.put("eval","tbsq_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getTbSaleQuoteEval", params);
			for(Map map:result){
				MemCache.deleteValue(TbSaleQuote.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteTbSaleQuoteBy", params);
	}
	

}