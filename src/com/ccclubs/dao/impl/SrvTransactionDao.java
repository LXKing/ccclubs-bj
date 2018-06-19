package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ISrvTransactionDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvTransaction;
import com.lazy3q.web.helper.$;

/**
 * 数据事务的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class SrvTransactionDao extends SqlMapClientDaoSupport implements ISrvTransactionDao
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
	private SrvTransaction custom(SrvTransaction params){
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
			"stEntry"			
			,"stDetails"			
			,"stModels"			
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
	 * 获取所有数据事务
	 * @return
	 */	
	public List<SrvTransaction> getSrvTransactionList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<SrvTransaction> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getSrvTransactionList", params);
		
		return list;
	}
	
	
	/**
	 * 获取数据事务统计
	 * @return
	 */
	public List getSrvTransactionStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getSrvTransactionStats", params);
	}

	/**
	 * 获取数据事务分页
	 * @return
	 */
	public Page getSrvTransactionPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getSrvTransactionCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getSrvTransactionPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取数据事务数量
	 * @return
	 */	
	public Long getSrvTransactionCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getSrvTransactionEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取数据事务自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getSrvTransactionEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getSrvTransactionEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取数据事务
	 * @param params
	 * @return
	 */
	public SrvTransaction getSrvTransaction(Map params)
	{		
		params = custom(params);
			
		List<SrvTransaction> list=this.getSrvTransactionList(params,1);
		SrvTransaction srvTransaction = list.isEmpty()?null:list.get(0);
		
		return srvTransaction;
	}
		
	/**
	 * 根据ID取数据事务
	 * @param id
	 * @return
	 */
	public SrvTransaction getSrvTransactionById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		SrvTransaction srvTransaction = null;
		if(srvTransaction!=null)
			return srvTransaction;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		srvTransaction = (SrvTransaction) this.getSqlMapClientTemplate().queryForObject("getSrvTransactionById",params);
		if(srvTransaction!=null)
			MemCache.setValue(SrvTransaction.class,id, srvTransaction.getKeyValue());
		return srvTransaction;
	}
	
	/**
	 * 根据ID取数据事务(不缓存)
	 * @param id
	 * @return
	 */
	public SrvTransaction GetSrvTransactionById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (SrvTransaction) this.getSqlMapClientTemplate().queryForObject("getSrvTransactionById",params);
	}
	

	/**
	 * 保存数据事务
	 * @param srvTransaction
	 */
	public SrvTransaction saveSrvTransaction(SrvTransaction srvTransaction)
	{
		srvTransaction = custom(srvTransaction);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveSrvTransaction", srvTransaction));
		srvTransaction = this.GetSrvTransactionById(id);
		MemCache.setValue(SrvTransaction.class,id, srvTransaction.getKeyValue());
		return Trigger.on(SrvTransaction.class,Trigger.SAVE,srvTransaction);	
	}

	

	/**
	 * 更新数据事务
	 * @param srvTransaction
	 */
	public void updateSrvTransaction(SrvTransaction srvTransaction)
	{
		srvTransaction = custom(srvTransaction);
		this.getSqlMapClientTemplate().update("updateSrvTransaction", srvTransaction);
		srvTransaction = this.GetSrvTransactionById(srvTransaction.getStId());
		if(srvTransaction!=null){
			MemCache.setValue(SrvTransaction.class,srvTransaction.getStId(), srvTransaction.getKeyValue());
		}
		Trigger.on(SrvTransaction.class,Trigger.UPDATE,srvTransaction);
	}
	/**
	 * 更新数据事务非空字段
	 * @param srvTransaction
	 */
	public void updateSrvTransaction$NotNull(SrvTransaction srvTransaction)
	{
		srvTransaction = custom(srvTransaction);
		this.getSqlMapClientTemplate().update("updateSrvTransaction$NotNull", srvTransaction);
		srvTransaction = this.GetSrvTransactionById(srvTransaction.getStId());
		if(srvTransaction!=null){
			MemCache.setValue(SrvTransaction.class,srvTransaction.getStId(), srvTransaction.getKeyValue());
		}
		Trigger.on(SrvTransaction.class,Trigger.UPDATE,srvTransaction);
	}

	/**
	 * 根据ID删除一个数据事务
	 * @param id
	 */
	public void deleteSrvTransactionById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(SrvTransaction.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteSrvTransactionById", params);
		MemCache.deleteValue(SrvTransaction.class, id);
	}
	/**
	 * 根据条件更新数据事务
	 * @param values
	 * @param params
	 */
	public void updateSrvTransactionByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getSrvTransactionCount(params)>255){
			MemCache.clearValue(SrvTransaction.class);
		}else{
			params.put("eval","st_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getSrvTransactionEval", params);
			for(Map map:result){
				MemCache.deleteValue(SrvTransaction.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateSrvTransactionBy", allParams);
		Trigger.on(SrvTransaction.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除数据事务
	 * @param params
	 */
	public void deleteSrvTransactionByConfirm(Map params){
		Trigger.on(SrvTransaction.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getSrvTransactionCount(params)>255){
			MemCache.clearValue(SrvTransaction.class);
		}else{
			params.put("eval","st_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getSrvTransactionEval", params);
			for(Map map:result){
				MemCache.deleteValue(SrvTransaction.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteSrvTransactionBy", params);
	}
	

}