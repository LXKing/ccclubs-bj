package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsElecHistoryDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsElecHistory;
import com.lazy3q.web.helper.$;

/**
 * 电桩历史数据的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsElecHistoryDao extends SqlMapClientDaoSupport implements ICsElecHistoryDao
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
		Long csehPowerPile = (Long) params.get("csehPowerPile");
		if(csehPowerPile!=null){
			String suffix = "_"+csehPowerPile;
			params.put("suffix",suffix);
			Boolean bool = setDynamicDataSource("cs_elec_history"+suffix+"");
			if(created.get(bool+"."+csehPowerPile)==null){
				new com.lazy3q.sql.Lazy3qDaoSupport(this.getSqlMapClientTemplate().getDataSource()).execute("CREATE table if not exists cs_elec_history"+suffix+" LIKE cs_elec_history");
				created.put(bool+"."+csehPowerPile, true);
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
	private CsElecHistory custom(CsElecHistory params){
		/************LAZY3Q_CUSTOM_MODEL************/
		Long csehPowerPile = params.getCsehPowerPile();
		if(csehPowerPile!=null){
			String suffix = "_"+csehPowerPile;
			params.setSuffix(suffix);
			Boolean bool = setDynamicDataSource("cs_elec_history"+suffix+"");
			if(created.get(csehPowerPile)==null){
				new com.lazy3q.sql.Lazy3qDaoSupport(this.getSqlMapClientTemplate().getDataSource()).execute("CREATE table if not exists cs_elec_history"+suffix+" LIKE cs_elec_history");
				created.put(bool+"."+csehPowerPile, true);
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
		this.getSqlMapClientTemplate().setDataSource((javax.sql.DataSource) $.getBean("ccclubs_elec_historyDataSource"));
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
			"csehStatusText"			
			,"csehData"			
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
	 * 获取所有电桩历史数据
	 * @return
	 */	
	public List<CsElecHistory> getCsElecHistoryList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsElecHistory> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsElecHistoryList", params);
		
		return list;
	}
	
	
	/**
	 * 获取电桩历史数据统计
	 * @return
	 */
	public List getCsElecHistoryStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsElecHistoryStats", params);
	}

	/**
	 * 获取电桩历史数据分页
	 * @return
	 */
	public Page getCsElecHistoryPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsElecHistoryCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsElecHistoryPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取电桩历史数据数量
	 * @return
	 */	
	public Long getCsElecHistoryCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsElecHistoryEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取电桩历史数据自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsElecHistoryEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsElecHistoryEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取电桩历史数据
	 * @param params
	 * @return
	 */
	public CsElecHistory getCsElecHistory(Map params)
	{		
		params = custom(params);
			
		List<CsElecHistory> list=this.getCsElecHistoryList(params,1);
		CsElecHistory csElecHistory = list.isEmpty()?null:list.get(0);
		
		return csElecHistory;
	}
		
	/**
	 * 根据ID取电桩历史数据
	 * @param id
	 * @return
	 */
	public CsElecHistory getCsElecHistoryById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsElecHistory csElecHistory = null;
		if(csElecHistory!=null)
			return csElecHistory;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csElecHistory = (CsElecHistory) this.getSqlMapClientTemplate().queryForObject("getCsElecHistoryById",params);
		return csElecHistory;
	}
	
	/**
	 * 根据ID取电桩历史数据(不缓存)
	 * @param id
	 * @return
	 */
	public CsElecHistory GetCsElecHistoryById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsElecHistory) this.getSqlMapClientTemplate().queryForObject("getCsElecHistoryById",params);
	}
	

	/**
	 * 保存电桩历史数据
	 * @param csElecHistory
	 */
	public CsElecHistory saveCsElecHistory(CsElecHistory csElecHistory)
	{
		csElecHistory = custom(csElecHistory);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsElecHistory", csElecHistory));
		csElecHistory = this.GetCsElecHistoryById(id);
		return Trigger.on(CsElecHistory.class,Trigger.SAVE,csElecHistory);	
	}

	

	/**
	 * 更新电桩历史数据
	 * @param csElecHistory
	 */
	public void updateCsElecHistory(CsElecHistory csElecHistory)
	{
		csElecHistory = custom(csElecHistory);
		this.getSqlMapClientTemplate().update("updateCsElecHistory", csElecHistory);
		csElecHistory = this.GetCsElecHistoryById(csElecHistory.getCsehId());
		Trigger.on(CsElecHistory.class,Trigger.UPDATE,csElecHistory);
	}
	/**
	 * 更新电桩历史数据非空字段
	 * @param csElecHistory
	 */
	public void updateCsElecHistory$NotNull(CsElecHistory csElecHistory)
	{
		csElecHistory = custom(csElecHistory);
		this.getSqlMapClientTemplate().update("updateCsElecHistory$NotNull", csElecHistory);
		csElecHistory = this.GetCsElecHistoryById(csElecHistory.getCsehId());
		Trigger.on(CsElecHistory.class,Trigger.UPDATE,csElecHistory);
	}

	/**
	 * 根据ID删除一个电桩历史数据
	 * @param id
	 */
	public void deleteCsElecHistoryById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsElecHistory.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsElecHistoryById", params);
	}
	/**
	 * 根据条件更新电桩历史数据
	 * @param values
	 * @param params
	 */
	public void updateCsElecHistoryByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
			
		int rows = this.getSqlMapClientTemplate().update("updateCsElecHistoryBy", allParams);
		Trigger.on(CsElecHistory.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除电桩历史数据
	 * @param params
	 */
	public void deleteCsElecHistoryByConfirm(Map params){
		Trigger.on(CsElecHistory.class,Trigger.DELETE,params);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsElecHistoryBy", params);
	}
	

}