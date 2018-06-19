package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ISrvOauthDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvOauth;
import com.lazy3q.web.helper.$;

/**
 * 用户授权的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class SrvOauthDao extends SqlMapClientDaoSupport implements ISrvOauthDao
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
	private SrvOauth custom(SrvOauth params){
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
	 * 获取所有用户授权
	 * @return
	 */	
	public List<SrvOauth> getSrvOauthList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<SrvOauth> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getSrvOauthList", params);
		
		return list;
	}
	
	
	/**
	 * 获取用户授权统计
	 * @return
	 */
	public List getSrvOauthStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getSrvOauthStats", params);
	}

	/**
	 * 获取用户授权分页
	 * @return
	 */
	public Page getSrvOauthPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getSrvOauthCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getSrvOauthPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取用户授权数量
	 * @return
	 */	
	public Long getSrvOauthCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getSrvOauthEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取用户授权自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getSrvOauthEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getSrvOauthEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取用户授权
	 * @param params
	 * @return
	 */
	public SrvOauth getSrvOauth(Map params)
	{		
		params = custom(params);
			
		List<SrvOauth> list=this.getSrvOauthList(params,1);
		SrvOauth srvOauth = list.isEmpty()?null:list.get(0);
		
		return srvOauth;
	}
		
	/**
	 * 根据ID取用户授权
	 * @param id
	 * @return
	 */
	public SrvOauth getSrvOauthById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		SrvOauth srvOauth = null;
		if(srvOauth!=null)
			return srvOauth;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		srvOauth = (SrvOauth) this.getSqlMapClientTemplate().queryForObject("getSrvOauthById",params);
		if(srvOauth!=null)
			MemCache.setValue(SrvOauth.class,id, srvOauth.getKeyValue());
		return srvOauth;
	}
	
	/**
	 * 根据ID取用户授权(不缓存)
	 * @param id
	 * @return
	 */
	public SrvOauth GetSrvOauthById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (SrvOauth) this.getSqlMapClientTemplate().queryForObject("getSrvOauthById",params);
	}
	

	/**
	 * 保存用户授权
	 * @param srvOauth
	 */
	public SrvOauth saveSrvOauth(SrvOauth srvOauth)
	{
		srvOauth = custom(srvOauth);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveSrvOauth", srvOauth));
		srvOauth = this.GetSrvOauthById(id);
		MemCache.setValue(SrvOauth.class,id, srvOauth.getKeyValue());
		return Trigger.on(SrvOauth.class,Trigger.SAVE,srvOauth);	
	}

	

	/**
	 * 更新用户授权
	 * @param srvOauth
	 */
	public void updateSrvOauth(SrvOauth srvOauth)
	{
		srvOauth = custom(srvOauth);
		this.getSqlMapClientTemplate().update("updateSrvOauth", srvOauth);
		srvOauth = this.GetSrvOauthById(srvOauth.getSoId());
		if(srvOauth!=null){
			MemCache.setValue(SrvOauth.class,srvOauth.getSoId(), srvOauth.getKeyValue());
		}
		Trigger.on(SrvOauth.class,Trigger.UPDATE,srvOauth);
	}
	/**
	 * 更新用户授权非空字段
	 * @param srvOauth
	 */
	public void updateSrvOauth$NotNull(SrvOauth srvOauth)
	{
		srvOauth = custom(srvOauth);
		this.getSqlMapClientTemplate().update("updateSrvOauth$NotNull", srvOauth);
		srvOauth = this.GetSrvOauthById(srvOauth.getSoId());
		if(srvOauth!=null){
			MemCache.setValue(SrvOauth.class,srvOauth.getSoId(), srvOauth.getKeyValue());
		}
		Trigger.on(SrvOauth.class,Trigger.UPDATE,srvOauth);
	}

	/**
	 * 根据ID删除一个用户授权
	 * @param id
	 */
	public void deleteSrvOauthById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(SrvOauth.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteSrvOauthById", params);
		MemCache.deleteValue(SrvOauth.class, id);
	}
	/**
	 * 根据条件更新用户授权
	 * @param values
	 * @param params
	 */
	public void updateSrvOauthByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getSrvOauthCount(params)>255){
			MemCache.clearValue(SrvOauth.class);
		}else{
			params.put("eval","so_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getSrvOauthEval", params);
			for(Map map:result){
				MemCache.deleteValue(SrvOauth.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateSrvOauthBy", allParams);
		Trigger.on(SrvOauth.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除用户授权
	 * @param params
	 */
	public void deleteSrvOauthByConfirm(Map params){
		Trigger.on(SrvOauth.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getSrvOauthCount(params)>255){
			MemCache.clearValue(SrvOauth.class);
		}else{
			params.put("eval","so_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getSrvOauthEval", params);
			for(Map map:result){
				MemCache.deleteValue(SrvOauth.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteSrvOauthBy", params);
	}
	

}