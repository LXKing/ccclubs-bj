package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ISrvUserExpDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvUserExp;
import com.lazy3q.web.helper.$;

/**
 * 用户扩展信息的Dao实现
 * @author Joel
 */
@SuppressWarnings("unchecked")
public class SrvUserExpDao extends SqlMapClientDaoSupport implements ISrvUserExpDao
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
	private SrvUserExp custom(SrvUserExp params){
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
		"sueId","sueUser","sueWxOpenid","sueIsQrOp","sueIsServicer","sueChatNick","sueChatHeader","sueChatSign","sueOutlets"
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
	 * 获取所有用户扩展信息
	 * @return
	 */	
	public List<SrvUserExp> getSrvUserExpList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<SrvUserExp> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(SrvUserExp.M.class, md5);
			List caches = MemCache.getObject(SrvUserExp.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						SrvUserExp cacheObject = MemCache.getObject(SrvUserExp.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(SrvUserExp.M.class, md5);
							MemCache.deleteValue(SrvUserExp.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(SrvUserExp.M.class, md5);
					MemCache.deleteValue(SrvUserExp.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getSrvUserExpList", params);
		
		return list;
	}
	
	
	/**
	 * 获取用户扩展信息统计
	 * @return
	 */
	public List getSrvUserExpStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getSrvUserExpStats", params);
	}

	/**
	 * 获取用户扩展信息分页
	 * @return
	 */
	public Page getSrvUserExpPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getSrvUserExpCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getSrvUserExpPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取用户扩展信息数量
	 * @return
	 */	
	public Long getSrvUserExpCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getSrvUserExpEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取用户扩展信息自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getSrvUserExpEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getSrvUserExpEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取用户扩展信息
	 * @param params
	 * @return
	 */
	public SrvUserExp getSrvUserExp(Map params)
	{		
		params = custom(params);
			
		List<SrvUserExp> list=this.getSrvUserExpList(params,1);
		SrvUserExp srvUserExp = list.isEmpty()?null:list.get(0);
		
		return srvUserExp;
	}
		
	/**
	 * 根据ID取用户扩展信息
	 * @param id
	 * @return
	 */
	public SrvUserExp getSrvUserExpById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		SrvUserExp srvUserExp = null;
		if(srvUserExp!=null)
			return srvUserExp;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		srvUserExp = (SrvUserExp) this.getSqlMapClientTemplate().queryForObject("getSrvUserExpById",params);
		if(srvUserExp!=null)
			MemCache.setValue(SrvUserExp.class,id, srvUserExp.getKeyValue());
		return srvUserExp;
	}
	
	/**
	 * 根据ID取用户扩展信息(不缓存)
	 * @param id
	 * @return
	 */
	public SrvUserExp GetSrvUserExpById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (SrvUserExp) this.getSqlMapClientTemplate().queryForObject("getSrvUserExpById",params);
	}
	

	/**
	 * 保存用户扩展信息
	 * @param srvUserExp
	 */
	public SrvUserExp saveSrvUserExp(SrvUserExp srvUserExp)
	{
		srvUserExp = custom(srvUserExp);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveSrvUserExp", srvUserExp));
		srvUserExp = this.GetSrvUserExpById(id);
		MemCache.setValue(SrvUserExp.class,id, srvUserExp.getKeyValue());
		return Trigger.on(SrvUserExp.class,Trigger.SAVE,srvUserExp);	
	}

	

	/**
	 * 更新用户扩展信息
	 * @param srvUserExp
	 */
	public void updateSrvUserExp(SrvUserExp srvUserExp)
	{
		srvUserExp = custom(srvUserExp);
		this.getSqlMapClientTemplate().update("updateSrvUserExp", srvUserExp);
		srvUserExp = this.GetSrvUserExpById(srvUserExp.getSueId());
		if(srvUserExp!=null){
			MemCache.setValue(SrvUserExp.class,srvUserExp.getSueId(), srvUserExp.getKeyValue());
		}
		Trigger.on(SrvUserExp.class,Trigger.UPDATE,srvUserExp);
	}
	/**
	 * 更新用户扩展信息非空字段
	 * @param srvUserExp
	 */
	public void updateSrvUserExp$NotNull(SrvUserExp srvUserExp)
	{
		srvUserExp = custom(srvUserExp);
		this.getSqlMapClientTemplate().update("updateSrvUserExp$NotNull", srvUserExp);
		srvUserExp = this.GetSrvUserExpById(srvUserExp.getSueId());
		if(srvUserExp!=null){
			MemCache.setValue(SrvUserExp.class,srvUserExp.getSueId(), srvUserExp.getKeyValue());
		}
		Trigger.on(SrvUserExp.class,Trigger.UPDATE,srvUserExp);
	}

	/**
	 * 根据ID删除一个用户扩展信息
	 * @param id
	 */
	public void deleteSrvUserExpById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(SrvUserExp.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteSrvUserExpById", params);
		MemCache.deleteValue(SrvUserExp.class, id);
	}
	/**
	 * 根据ID逻辑删除用户扩展信息 
	 * @param id
	 * @return
	 */
	public void removeSrvUserExpById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(SrvUserExp.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeSrvUserExpById", params);
		MemCache.deleteValue(SrvUserExp.class, id);
	}
	/**
	 * 根据条件更新用户扩展信息
	 * @param values
	 * @param params
	 */
	public void updateSrvUserExpByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getSrvUserExpCount(params)>255){
			MemCache.clearValue(SrvUserExp.class);
		}else{
			params.put("eval","sue_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getSrvUserExpEval", params);
			for(Map map:result){
				MemCache.deleteValue(SrvUserExp.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateSrvUserExpBy", allParams);
		Trigger.on(SrvUserExp.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除用户扩展信息
	 * @param params
	 */
	public void deleteSrvUserExpByConfirm(Map params){
		Trigger.on(SrvUserExp.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getSrvUserExpCount(params)>255){
			MemCache.clearValue(SrvUserExp.class);
		}else{
			params.put("eval","sue_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getSrvUserExpEval", params);
			for(Map map:result){
				MemCache.deleteValue(SrvUserExp.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteSrvUserExpBy", params);
	}
	

}