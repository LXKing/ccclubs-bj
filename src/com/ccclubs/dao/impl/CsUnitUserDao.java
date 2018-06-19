package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsUnitUserDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsUnitUser;
import com.lazy3q.web.helper.$;

/**
 * 企业车管的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsUnitUserDao extends SqlMapClientDaoSupport implements ICsUnitUserDao
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
	private CsUnitUser custom(CsUnitUser params){
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
			"csuuLimit"			
			,"csuuRealname"			
			,"csuuMobile"			
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
	 * 获取所有企业车管
	 * @return
	 */	
	public List<CsUnitUser> getCsUnitUserList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsUnitUser> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsUnitUserList", params);
		
		return list;
	}
	
	
	/**
	 * 获取企业车管统计
	 * @return
	 */
	public List getCsUnitUserStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsUnitUserStats", params);
	}

	/**
	 * 获取企业车管分页
	 * @return
	 */
	public Page getCsUnitUserPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsUnitUserCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsUnitUserPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取企业车管数量
	 * @return
	 */	
	public Long getCsUnitUserCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsUnitUserEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取企业车管自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsUnitUserEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsUnitUserEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取企业车管
	 * @param params
	 * @return
	 */
	public CsUnitUser getCsUnitUser(Map params)
	{		
		params = custom(params);
			
		List<CsUnitUser> list=this.getCsUnitUserList(params,1);
		CsUnitUser csUnitUser = list.isEmpty()?null:list.get(0);
		
		return csUnitUser;
	}
		
	/**
	 * 根据ID取企业车管
	 * @param id
	 * @return
	 */
	public CsUnitUser getCsUnitUserById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsUnitUser csUnitUser = null;
		if(csUnitUser!=null)
			return csUnitUser;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csUnitUser = (CsUnitUser) this.getSqlMapClientTemplate().queryForObject("getCsUnitUserById",params);
		if(csUnitUser!=null)
			MemCache.setValue(CsUnitUser.class,id, csUnitUser.getKeyValue());
		return csUnitUser;
	}
	
	/**
	 * 根据ID取企业车管(不缓存)
	 * @param id
	 * @return
	 */
	public CsUnitUser GetCsUnitUserById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsUnitUser) this.getSqlMapClientTemplate().queryForObject("getCsUnitUserById",params);
	}
	

	/**
	 * 保存企业车管
	 * @param csUnitUser
	 */
	public CsUnitUser saveCsUnitUser(CsUnitUser csUnitUser)
	{
		csUnitUser = custom(csUnitUser);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsUnitUser", csUnitUser));
		csUnitUser = this.GetCsUnitUserById(id);
		MemCache.setValue(CsUnitUser.class,id, csUnitUser.getKeyValue());
		return Trigger.on(CsUnitUser.class,Trigger.SAVE,csUnitUser);	
	}

	

	/**
	 * 更新企业车管
	 * @param csUnitUser
	 */
	public void updateCsUnitUser(CsUnitUser csUnitUser)
	{
		csUnitUser = custom(csUnitUser);
		this.getSqlMapClientTemplate().update("updateCsUnitUser", csUnitUser);
		csUnitUser = this.GetCsUnitUserById(csUnitUser.getCsuuId());
		if(csUnitUser!=null){
			MemCache.setValue(CsUnitUser.class,csUnitUser.getCsuuId(), csUnitUser.getKeyValue());
		}
		Trigger.on(CsUnitUser.class,Trigger.UPDATE,csUnitUser);
	}
	/**
	 * 更新企业车管非空字段
	 * @param csUnitUser
	 */
	public void updateCsUnitUser$NotNull(CsUnitUser csUnitUser)
	{
		csUnitUser = custom(csUnitUser);
		this.getSqlMapClientTemplate().update("updateCsUnitUser$NotNull", csUnitUser);
		csUnitUser = this.GetCsUnitUserById(csUnitUser.getCsuuId());
		if(csUnitUser!=null){
			MemCache.setValue(CsUnitUser.class,csUnitUser.getCsuuId(), csUnitUser.getKeyValue());
		}
		Trigger.on(CsUnitUser.class,Trigger.UPDATE,csUnitUser);
	}

	/**
	 * 根据ID删除一个企业车管
	 * @param id
	 */
	public void deleteCsUnitUserById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsUnitUser.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsUnitUserById", params);
		MemCache.deleteValue(CsUnitUser.class, id);
	}
	/**
	 * 根据ID逻辑删除企业车管 
	 * @param id
	 * @return
	 */
	public void removeCsUnitUserById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsUnitUser.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsUnitUserById", params);
		MemCache.deleteValue(CsUnitUser.class, id);
	}
	/**
	 * 根据条件更新企业车管
	 * @param values
	 * @param params
	 */
	public void updateCsUnitUserByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsUnitUserCount(params)>255){
			MemCache.clearValue(CsUnitUser.class);
		}else{
			params.put("eval","csuu_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsUnitUserEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsUnitUser.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsUnitUserBy", allParams);
		Trigger.on(CsUnitUser.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除企业车管
	 * @param params
	 */
	public void deleteCsUnitUserByConfirm(Map params){
		Trigger.on(CsUnitUser.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsUnitUserCount(params)>255){
			MemCache.clearValue(CsUnitUser.class);
		}else{
			params.put("eval","csuu_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsUnitUserEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsUnitUser.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsUnitUserBy", params);
	}
	

}