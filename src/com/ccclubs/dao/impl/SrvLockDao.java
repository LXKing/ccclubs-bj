package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ISrvLockDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvLock;
import com.lazy3q.web.helper.$;

/**
 * 事务锁的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class SrvLockDao extends SqlMapClientDaoSupport implements ISrvLockDao
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
	private SrvLock custom(SrvLock params){
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
			"slProfile"			
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
	 * 获取所有事务锁
	 * @return
	 */	
	public List<SrvLock> getSrvLockList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<SrvLock> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getSrvLockList", params);
		
		return list;
	}
	
	
	/**
	 * 获取事务锁统计
	 * @return
	 */
	public List getSrvLockStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getSrvLockStats", params);
	}

	/**
	 * 获取事务锁分页
	 * @return
	 */
	public Page getSrvLockPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getSrvLockCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getSrvLockPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取事务锁数量
	 * @return
	 */	
	public Long getSrvLockCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getSrvLockEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取事务锁自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getSrvLockEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getSrvLockEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取事务锁
	 * @param params
	 * @return
	 */
	public SrvLock getSrvLock(Map params)
	{		
		params = custom(params);
			
		List<SrvLock> list=this.getSrvLockList(params,1);
		SrvLock srvLock = list.isEmpty()?null:list.get(0);
		
		return srvLock;
	}
		
	/**
	 * 根据ID取事务锁
	 * @param id
	 * @return
	 */
	public SrvLock getSrvLockById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		SrvLock srvLock = null;
		if(srvLock!=null)
			return srvLock;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		srvLock = (SrvLock) this.getSqlMapClientTemplate().queryForObject("getSrvLockById",params);
		if(srvLock!=null)
			MemCache.setValue(SrvLock.class,id, srvLock.getKeyValue());
		return srvLock;
	}
	
	/**
	 * 根据ID取事务锁(不缓存)
	 * @param id
	 * @return
	 */
	public SrvLock GetSrvLockById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (SrvLock) this.getSqlMapClientTemplate().queryForObject("getSrvLockById",params);
	}
	

	/**
	 * 保存事务锁
	 * @param srvLock
	 */
	public SrvLock saveSrvLock(SrvLock srvLock)
	{
		srvLock = custom(srvLock);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveSrvLock", srvLock));
		srvLock = this.GetSrvLockById(id);
		MemCache.setValue(SrvLock.class,id, srvLock.getKeyValue());
		return Trigger.on(SrvLock.class,Trigger.SAVE,srvLock);	
	}

	

	/**
	 * 更新事务锁
	 * @param srvLock
	 */
	public void updateSrvLock(SrvLock srvLock)
	{
		srvLock = custom(srvLock);
		this.getSqlMapClientTemplate().update("updateSrvLock", srvLock);
		srvLock = this.GetSrvLockById(srvLock.getSlId());
		if(srvLock!=null){
			MemCache.setValue(SrvLock.class,srvLock.getSlId(), srvLock.getKeyValue());
		}
		Trigger.on(SrvLock.class,Trigger.UPDATE,srvLock);
	}
	/**
	 * 更新事务锁非空字段
	 * @param srvLock
	 */
	public void updateSrvLock$NotNull(SrvLock srvLock)
	{
		srvLock = custom(srvLock);
		this.getSqlMapClientTemplate().update("updateSrvLock$NotNull", srvLock);
		srvLock = this.GetSrvLockById(srvLock.getSlId());
		if(srvLock!=null){
			MemCache.setValue(SrvLock.class,srvLock.getSlId(), srvLock.getKeyValue());
		}
		Trigger.on(SrvLock.class,Trigger.UPDATE,srvLock);
	}

	/**
	 * 根据ID删除一个事务锁
	 * @param id
	 */
	public void deleteSrvLockById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(SrvLock.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteSrvLockById", params);
		MemCache.deleteValue(SrvLock.class, id);
	}
	/**
	 * 根据条件更新事务锁
	 * @param values
	 * @param params
	 */
	public void updateSrvLockByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getSrvLockCount(params)>255){
			MemCache.clearValue(SrvLock.class);
		}else{
			params.put("eval","sl_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getSrvLockEval", params);
			for(Map map:result){
				MemCache.deleteValue(SrvLock.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateSrvLockBy", allParams);
		Trigger.on(SrvLock.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除事务锁
	 * @param params
	 */
	public void deleteSrvLockByConfirm(Map params){
		Trigger.on(SrvLock.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getSrvLockCount(params)>255){
			MemCache.clearValue(SrvLock.class);
		}else{
			params.put("eval","sl_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getSrvLockEval", params);
			for(Map map:result){
				MemCache.deleteValue(SrvLock.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteSrvLockBy", params);
	}
	

}