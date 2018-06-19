package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsHistoryStateDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsHistoryState;
import com.lazy3q.web.helper.$;

/**
 * 车辆历史状态的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsHistoryStateDao extends SqlMapClientDaoSupport implements ICsHistoryStateDao
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
		String sNumber = $.getLocal("number");
		if($.empty(sNumber))
			sNumber = com.lazy3q.web.helper.$.getString("cshsNumber");
		if(sNumber!=null && !sNumber.trim().equals("")){
			sNumber = "_"+sNumber.replace("%", "").toLowerCase();
			params.put("suffix",sNumber);
			Boolean bool = setDynamicDataSource("cs_history_state"+sNumber+"");
			if(created.get(bool+"."+sNumber)==null){				
				new com.lazy3q.sql.Lazy3qDaoSupport(this.getSqlMapClientTemplate().getDataSource()).execute("CREATE table if not exists cs_history_state"+sNumber+" LIKE cs_history_state");			
				created.put(bool+"."+sNumber, true);
			}
		}
		params.remove("cshsNumber");
		/************LAZY3Q_DAO_CUSTOM************/

		return params;
	}
	
	/**
	 * 自定义加工处理params,可以添加表别名、联表查询等操作
	 * 可在此动态设置数据源，以达到分库分表的目的
	 * @param params
	 * @return
	 */
	private CsHistoryState custom(CsHistoryState params){
		/************LAZY3Q_CUSTOM_MODEL************/
		String sNumber = $.getLocal("number");
		if($.empty(sNumber))
			sNumber = com.lazy3q.web.helper.$.getString("cshsNumber");
		if(sNumber!=null && !sNumber.trim().equals("")){
			sNumber = "_"+sNumber.replace("%", "").toLowerCase();
			params.setSuffix(sNumber);
			Boolean bool = setDynamicDataSource("cs_history_state"+sNumber+"");
			if(created.get(bool+"."+sNumber)==null){
				new com.lazy3q.sql.Lazy3qDaoSupport(this.getSqlMapClientTemplate().getDataSource()).execute("CREATE table if not exists cs_history_state"+sNumber+" LIKE cs_history_state");			
				created.put(bool+"."+sNumber, true);
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
		this.getSqlMapClientTemplate().setDataSource((javax.sql.DataSource) $.getBean("ccclubs_system_historyDataSource"));
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
			"cshsMoData"			
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
	 * 获取所有车辆历史状态
	 * @return
	 */	
	public List<CsHistoryState> getCsHistoryStateList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsHistoryState> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsHistoryStateList", params);
		
		return list;
	}
	
	
	/**
	 * 获取车辆历史状态统计
	 * @return
	 */
	public List getCsHistoryStateStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsHistoryStateStats", params);
	}

	/**
	 * 获取车辆历史状态分页
	 * @return
	 */
	public Page getCsHistoryStatePage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsHistoryStateCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsHistoryStatePage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取车辆历史状态数量
	 * @return
	 */	
	public Long getCsHistoryStateCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsHistoryStateEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取车辆历史状态自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsHistoryStateEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsHistoryStateEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取车辆历史状态
	 * @param params
	 * @return
	 */
	public CsHistoryState getCsHistoryState(Map params)
	{		
		params = custom(params);
			
		List<CsHistoryState> list=this.getCsHistoryStateList(params,1);
		CsHistoryState csHistoryState = list.isEmpty()?null:list.get(0);
		
		return csHistoryState;
	}
		
	/**
	 * 根据ID取车辆历史状态
	 * @param id
	 * @return
	 */
	public CsHistoryState getCsHistoryStateById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsHistoryState csHistoryState = null;
		if(csHistoryState!=null)
			return csHistoryState;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csHistoryState = (CsHistoryState) this.getSqlMapClientTemplate().queryForObject("getCsHistoryStateById",params);
		return csHistoryState;
	}
	
	/**
	 * 根据ID取车辆历史状态(不缓存)
	 * @param id
	 * @return
	 */
	public CsHistoryState GetCsHistoryStateById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsHistoryState) this.getSqlMapClientTemplate().queryForObject("getCsHistoryStateById",params);
	}
	

	/**
	 * 保存车辆历史状态
	 * @param csHistoryState
	 */
	public CsHistoryState saveCsHistoryState(CsHistoryState csHistoryState)
	{
		csHistoryState = custom(csHistoryState);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsHistoryState", csHistoryState));
		csHistoryState = this.GetCsHistoryStateById(id);
		return Trigger.on(CsHistoryState.class,Trigger.SAVE,csHistoryState);	
	}

	

	/**
	 * 更新车辆历史状态
	 * @param csHistoryState
	 */
	public void updateCsHistoryState(CsHistoryState csHistoryState)
	{
		csHistoryState = custom(csHistoryState);
		this.getSqlMapClientTemplate().update("updateCsHistoryState", csHistoryState);
		csHistoryState = this.GetCsHistoryStateById(csHistoryState.getCshsId());
		Trigger.on(CsHistoryState.class,Trigger.UPDATE,csHistoryState);
	}
	/**
	 * 更新车辆历史状态非空字段
	 * @param csHistoryState
	 */
	public void updateCsHistoryState$NotNull(CsHistoryState csHistoryState)
	{
		csHistoryState = custom(csHistoryState);
		this.getSqlMapClientTemplate().update("updateCsHistoryState$NotNull", csHistoryState);
		csHistoryState = this.GetCsHistoryStateById(csHistoryState.getCshsId());
		Trigger.on(CsHistoryState.class,Trigger.UPDATE,csHistoryState);
	}

	/**
	 * 根据ID删除一个车辆历史状态
	 * @param id
	 */
	public void deleteCsHistoryStateById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsHistoryState.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsHistoryStateById", params);
	}
	/**
	 * 根据条件更新车辆历史状态
	 * @param values
	 * @param params
	 */
	public void updateCsHistoryStateByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
			
		int rows = this.getSqlMapClientTemplate().update("updateCsHistoryStateBy", allParams);
		Trigger.on(CsHistoryState.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除车辆历史状态
	 * @param params
	 */
	public void deleteCsHistoryStateByConfirm(Map params){
		Trigger.on(CsHistoryState.class,Trigger.DELETE,params);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsHistoryStateBy", params);
	}
	

}