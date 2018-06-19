package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ISrvApiLogDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvApiLog;
import com.lazy3q.web.helper.$;

/**
 * API日志的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class SrvApiLogDao extends SqlMapClientDaoSupport implements ISrvApiLogDao
{
	/************LAZY3Q_DEFINE_CODE************/
	static Map<String,Boolean> created = new HashMap();
	/************LAZY3Q_DEFINE_CODE************/


	/**
	 * 自定义加工处理params,可以添加表别名、联表查询等操作
	 * 可在此动态设置数据源，以达到分库分表的目的
	 * @param params
	 * @return
	 */
	private Map custom(Map params){
		/************LAZY3Q_DAO_CUSTOM************/
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMM");
		String salClient = formatter.format(java.util.Calendar.getInstance().getTime());
		if(salClient!=null){
			String suffix = "_"+salClient;
			params.put("suffix",suffix);
			Boolean bool = setDynamicDataSource("srv_api_log"+suffix+"");
			if(created.get(bool+"."+salClient)==null){
				new com.lazy3q.sql.Lazy3qDaoSupport(this.getSqlMapClientTemplate().getDataSource()).execute("CREATE table if not exists srv_api_log"+suffix+" LIKE srv_api_log");
				created.put(bool+"."+salClient, true);
			}
		}
		/************LAZY3Q_DAO_CUSTOM************/

		return params;
	}
	
	/**
	 * 自定义加工处理params,可以添加表别名、联表查询等操作
	 * 可在此动态设置数据源，以达到分库分表的目的
	 * @param params
	 * @return
	 */
	private SrvApiLog custom(SrvApiLog params){
		/************LAZY3Q_CUSTOM_MODEL************/
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMM");
		String salClient = formatter.format(java.util.Calendar.getInstance().getTime());
		if(salClient!=null){
			String suffix = "_"+salClient;
			params.setSuffix(suffix);
			Boolean bool = setDynamicDataSource("srv_api_log"+suffix+"");
			if(created.get(salClient)==null){
				new com.lazy3q.sql.Lazy3qDaoSupport(this.getSqlMapClientTemplate().getDataSource()).execute("CREATE table if not exists srv_api_log"+suffix+" LIKE srv_api_log");
				created.put(bool+"."+salClient, true);
			}
		}
		/************LAZY3Q_CUSTOM_MODEL************/

		return params;
	}
	/************LAZY3Q_DEFINE_FUNCTION************/
	/**
	 * 根据表名动态设置数据源
	 */
	public Boolean setDynamicDataSource(String table_name){
		this.getSqlMapClientTemplate().setDataSource((javax.sql.DataSource) $.getBean("ccclubs_dataDataSource"));
		return false;
	}
	/************LAZY3Q_DEFINE_FUNCTION************/

	
	/**
	 * 为某些需要加like的key加%
	 * @param params
	 * @return
	 */
	private Map likeKey(Map params){
		//为模糊匹配的varchar类型字段加上%号
		String[] likes={
			"salParams"			
			,"salReturn"			
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
	 * 获取所有API日志
	 * @return
	 */	
	public List<SrvApiLog> getSrvApiLogList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<SrvApiLog> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getSrvApiLogList", params);
		
		return list;
	}
	
	
	/**
	 * 获取API日志统计
	 * @return
	 */
	public List getSrvApiLogStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getSrvApiLogStats", params);
	}

	/**
	 * 获取API日志分页
	 * @return
	 */
	public Page getSrvApiLogPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getSrvApiLogCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getSrvApiLogPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取API日志数量
	 * @return
	 */	
	public Long getSrvApiLogCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getSrvApiLogEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取API日志自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getSrvApiLogEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getSrvApiLogEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取API日志
	 * @param params
	 * @return
	 */
	public SrvApiLog getSrvApiLog(Map params)
	{		
		params = custom(params);
			
		List<SrvApiLog> list=this.getSrvApiLogList(params,1);
		SrvApiLog srvApiLog = list.isEmpty()?null:list.get(0);
		
		return srvApiLog;
	}
		
	/**
	 * 根据ID取API日志
	 * @param id
	 * @return
	 */
	public SrvApiLog getSrvApiLogById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		SrvApiLog srvApiLog = null;
		if(srvApiLog!=null)
			return srvApiLog;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		srvApiLog = (SrvApiLog) this.getSqlMapClientTemplate().queryForObject("getSrvApiLogById",params);
		return srvApiLog;
	}
	
	/**
	 * 根据ID取API日志(不缓存)
	 * @param id
	 * @return
	 */
	public SrvApiLog GetSrvApiLogById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (SrvApiLog) this.getSqlMapClientTemplate().queryForObject("getSrvApiLogById",params);
	}
	

	/**
	 * 保存API日志
	 * @param srvApiLog
	 */
	public SrvApiLog saveSrvApiLog(SrvApiLog srvApiLog)
	{
		srvApiLog = custom(srvApiLog);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveSrvApiLog", srvApiLog));
		srvApiLog = this.GetSrvApiLogById(id);
		return Trigger.on(SrvApiLog.class,Trigger.SAVE,srvApiLog);	
	}

	

	/**
	 * 更新API日志
	 * @param srvApiLog
	 */
	public void updateSrvApiLog(SrvApiLog srvApiLog)
	{
		srvApiLog = custom(srvApiLog);
		this.getSqlMapClientTemplate().update("updateSrvApiLog", srvApiLog);
		srvApiLog = this.GetSrvApiLogById(srvApiLog.getSalId());
		Trigger.on(SrvApiLog.class,Trigger.UPDATE,srvApiLog);
	}
	/**
	 * 更新API日志非空字段
	 * @param srvApiLog
	 */
	public void updateSrvApiLog$NotNull(SrvApiLog srvApiLog)
	{
		srvApiLog = custom(srvApiLog);
		this.getSqlMapClientTemplate().update("updateSrvApiLog$NotNull", srvApiLog);
		srvApiLog = this.GetSrvApiLogById(srvApiLog.getSalId());
		Trigger.on(SrvApiLog.class,Trigger.UPDATE,srvApiLog);
	}

	/**
	 * 根据ID删除一个API日志
	 * @param id
	 */
	public void deleteSrvApiLogById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(SrvApiLog.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteSrvApiLogById", params);
	}
	/**
	 * 根据ID逻辑删除API日志 
	 * @param id
	 * @return
	 */
	public void removeSrvApiLogById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(SrvApiLog.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeSrvApiLogById", params);
	}
	/**
	 * 根据条件更新API日志
	 * @param values
	 * @param params
	 */
	public void updateSrvApiLogByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
			
		int rows = this.getSqlMapClientTemplate().update("updateSrvApiLogBy", allParams);
		Trigger.on(SrvApiLog.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除API日志
	 * @param params
	 */
	public void deleteSrvApiLogByConfirm(Map params){
		Trigger.on(SrvApiLog.class,Trigger.DELETE,params);
		
		int rows = this.getSqlMapClientTemplate().update("deleteSrvApiLogBy", params);
	}
	

}