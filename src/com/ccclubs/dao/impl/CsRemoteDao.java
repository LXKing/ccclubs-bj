package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsRemoteDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsRemote;
import com.lazy3q.web.helper.$;

/**
 * 远程控制的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsRemoteDao extends SqlMapClientDaoSupport implements ICsRemoteDao
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
	private CsRemote custom(CsRemote params){
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
			"csrNumber"			
			,"csrRemark"			
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
	 * 获取所有远程控制
	 * @return
	 */	
	public List<CsRemote> getCsRemoteList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsRemote> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsRemoteList", params);
		
		return list;
	}
	
	
	/**
	 * 获取远程控制统计
	 * @return
	 */
	public List getCsRemoteStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsRemoteStats", params);
	}

	/**
	 * 获取远程控制分页
	 * @return
	 */
	public Page getCsRemotePage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsRemoteCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsRemotePage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取远程控制数量
	 * @return
	 */	
	public Long getCsRemoteCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsRemoteEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取远程控制自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsRemoteEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsRemoteEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取远程控制
	 * @param params
	 * @return
	 */
	public CsRemote getCsRemote(Map params)
	{		
		params = custom(params);
			
		List<CsRemote> list=this.getCsRemoteList(params,1);
		CsRemote csRemote = list.isEmpty()?null:list.get(0);
		
		return csRemote;
	}
		
	/**
	 * 根据ID取远程控制
	 * @param id
	 * @return
	 */
	public CsRemote getCsRemoteById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsRemote csRemote = null;
		if(csRemote!=null)
			return csRemote;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csRemote = (CsRemote) this.getSqlMapClientTemplate().queryForObject("getCsRemoteById",params);
		return csRemote;
	}
	
	/**
	 * 根据ID取远程控制(不缓存)
	 * @param id
	 * @return
	 */
	public CsRemote GetCsRemoteById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsRemote) this.getSqlMapClientTemplate().queryForObject("getCsRemoteById",params);
	}
	

	/**
	 * 保存远程控制
	 * @param csRemote
	 */
	public CsRemote saveCsRemote(CsRemote csRemote)
	{
		csRemote = custom(csRemote);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsRemote", csRemote));
		csRemote = this.GetCsRemoteById(id);
		return Trigger.on(CsRemote.class,Trigger.SAVE,csRemote);	
	}

	

	/**
	 * 更新远程控制
	 * @param csRemote
	 */
	public void updateCsRemote(CsRemote csRemote)
	{
		csRemote = custom(csRemote);
		this.getSqlMapClientTemplate().update("updateCsRemote", csRemote);
		csRemote = this.GetCsRemoteById(csRemote.getCsrId());
		Trigger.on(CsRemote.class,Trigger.UPDATE,csRemote);
	}
	/**
	 * 更新远程控制非空字段
	 * @param csRemote
	 */
	public void updateCsRemote$NotNull(CsRemote csRemote)
	{
		csRemote = custom(csRemote);
		this.getSqlMapClientTemplate().update("updateCsRemote$NotNull", csRemote);
		csRemote = this.GetCsRemoteById(csRemote.getCsrId());
		Trigger.on(CsRemote.class,Trigger.UPDATE,csRemote);
	}

	/**
	 * 根据ID删除一个远程控制
	 * @param id
	 */
	public void deleteCsRemoteById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsRemote.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsRemoteById", params);
	}
	/**
	 * 根据条件更新远程控制
	 * @param values
	 * @param params
	 */
	public void updateCsRemoteByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
			
		int rows = this.getSqlMapClientTemplate().update("updateCsRemoteBy", allParams);
		Trigger.on(CsRemote.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除远程控制
	 * @param params
	 */
	public void deleteCsRemoteByConfirm(Map params){
		Trigger.on(CsRemote.class,Trigger.DELETE,params);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsRemoteBy", params);
	}
	

}