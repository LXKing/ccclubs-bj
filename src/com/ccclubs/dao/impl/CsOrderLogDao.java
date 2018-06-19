package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsOrderLogDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsOrderLog;
import com.lazy3q.web.helper.$;

/**
 * 订单操作记录的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsOrderLogDao extends SqlMapClientDaoSupport implements ICsOrderLogDao
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
	private CsOrderLog custom(CsOrderLog params){
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
			"csolTitle"			
			,"csolRemark"			
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
	 * 获取所有订单操作记录
	 * @return
	 */	
	public List<CsOrderLog> getCsOrderLogList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsOrderLog> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsOrderLogList", params);
		
		return list;
	}
	
	
	/**
	 * 获取订单操作记录统计
	 * @return
	 */
	public List getCsOrderLogStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsOrderLogStats", params);
	}

	/**
	 * 获取订单操作记录分页
	 * @return
	 */
	public Page getCsOrderLogPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsOrderLogCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsOrderLogPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取订单操作记录数量
	 * @return
	 */	
	public Long getCsOrderLogCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsOrderLogEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取订单操作记录自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsOrderLogEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsOrderLogEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取订单操作记录
	 * @param params
	 * @return
	 */
	public CsOrderLog getCsOrderLog(Map params)
	{		
		params = custom(params);
			
		List<CsOrderLog> list=this.getCsOrderLogList(params,1);
		CsOrderLog csOrderLog = list.isEmpty()?null:list.get(0);
		
		return csOrderLog;
	}
		
	/**
	 * 根据ID取订单操作记录
	 * @param id
	 * @return
	 */
	public CsOrderLog getCsOrderLogById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsOrderLog csOrderLog = null;
		if(csOrderLog!=null)
			return csOrderLog;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csOrderLog = (CsOrderLog) this.getSqlMapClientTemplate().queryForObject("getCsOrderLogById",params);
		return csOrderLog;
	}
	
	/**
	 * 根据ID取订单操作记录(不缓存)
	 * @param id
	 * @return
	 */
	public CsOrderLog GetCsOrderLogById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsOrderLog) this.getSqlMapClientTemplate().queryForObject("getCsOrderLogById",params);
	}
	

	/**
	 * 保存订单操作记录
	 * @param csOrderLog
	 */
	public CsOrderLog saveCsOrderLog(CsOrderLog csOrderLog)
	{
		csOrderLog = custom(csOrderLog);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsOrderLog", csOrderLog));
		csOrderLog = this.GetCsOrderLogById(id);
		return Trigger.on(CsOrderLog.class,Trigger.SAVE,csOrderLog);	
	}

	

	/**
	 * 更新订单操作记录
	 * @param csOrderLog
	 */
	public void updateCsOrderLog(CsOrderLog csOrderLog)
	{
		csOrderLog = custom(csOrderLog);
		this.getSqlMapClientTemplate().update("updateCsOrderLog", csOrderLog);
		csOrderLog = this.GetCsOrderLogById(csOrderLog.getCsolId());
		Trigger.on(CsOrderLog.class,Trigger.UPDATE,csOrderLog);
	}
	/**
	 * 更新订单操作记录非空字段
	 * @param csOrderLog
	 */
	public void updateCsOrderLog$NotNull(CsOrderLog csOrderLog)
	{
		csOrderLog = custom(csOrderLog);
		this.getSqlMapClientTemplate().update("updateCsOrderLog$NotNull", csOrderLog);
		csOrderLog = this.GetCsOrderLogById(csOrderLog.getCsolId());
		Trigger.on(CsOrderLog.class,Trigger.UPDATE,csOrderLog);
	}

	/**
	 * 根据ID删除一个订单操作记录
	 * @param id
	 */
	public void deleteCsOrderLogById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsOrderLog.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsOrderLogById", params);
	}
	/**
	 * 根据条件更新订单操作记录
	 * @param values
	 * @param params
	 */
	public void updateCsOrderLogByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
			
		int rows = this.getSqlMapClientTemplate().update("updateCsOrderLogBy", allParams);
		Trigger.on(CsOrderLog.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除订单操作记录
	 * @param params
	 */
	public void deleteCsOrderLogByConfirm(Map params){
		Trigger.on(CsOrderLog.class,Trigger.DELETE,params);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsOrderLogBy", params);
	}
	

}