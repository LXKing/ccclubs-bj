package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsErrorDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsError;
import com.lazy3q.web.helper.$;

/**
 * 错误信息的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsErrorDao extends SqlMapClientDaoSupport implements ICsErrorDao
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
	private CsError custom(CsError params){
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
			"cseTitle"			
			,"cseContent"			
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
	 * 获取所有错误信息
	 * @return
	 */	
	public List<CsError> getCsErrorList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsError> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsErrorList", params);
		
		return list;
	}
	
	
	/**
	 * 获取错误信息统计
	 * @return
	 */
	public List getCsErrorStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsErrorStats", params);
	}

	/**
	 * 获取错误信息分页
	 * @return
	 */
	public Page getCsErrorPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsErrorCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsErrorPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取错误信息数量
	 * @return
	 */	
	public Long getCsErrorCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsErrorEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取错误信息自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsErrorEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsErrorEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取错误信息
	 * @param params
	 * @return
	 */
	public CsError getCsError(Map params)
	{		
		params = custom(params);
			
		List<CsError> list=this.getCsErrorList(params,1);
		CsError csError = list.isEmpty()?null:list.get(0);
		
		return csError;
	}
		
	/**
	 * 根据ID取错误信息
	 * @param id
	 * @return
	 */
	public CsError getCsErrorById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsError csError = null;
		if(csError!=null)
			return csError;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csError = (CsError) this.getSqlMapClientTemplate().queryForObject("getCsErrorById",params);
		return csError;
	}
	
	/**
	 * 根据ID取错误信息(不缓存)
	 * @param id
	 * @return
	 */
	public CsError GetCsErrorById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsError) this.getSqlMapClientTemplate().queryForObject("getCsErrorById",params);
	}
	

	/**
	 * 保存错误信息
	 * @param csError
	 */
	public CsError saveCsError(CsError csError)
	{
		csError = custom(csError);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsError", csError));
		csError = this.GetCsErrorById(id);
		return Trigger.on(CsError.class,Trigger.SAVE,csError);	
	}

	

	/**
	 * 更新错误信息
	 * @param csError
	 */
	public void updateCsError(CsError csError)
	{
		csError = custom(csError);
		this.getSqlMapClientTemplate().update("updateCsError", csError);
		csError = this.GetCsErrorById(csError.getCseId());
		Trigger.on(CsError.class,Trigger.UPDATE,csError);
	}
	/**
	 * 更新错误信息非空字段
	 * @param csError
	 */
	public void updateCsError$NotNull(CsError csError)
	{
		csError = custom(csError);
		this.getSqlMapClientTemplate().update("updateCsError$NotNull", csError);
		csError = this.GetCsErrorById(csError.getCseId());
		Trigger.on(CsError.class,Trigger.UPDATE,csError);
	}

	/**
	 * 根据ID删除一个错误信息
	 * @param id
	 */
	public void deleteCsErrorById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsError.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsErrorById", params);
	}
	/**
	 * 根据条件更新错误信息
	 * @param values
	 * @param params
	 */
	public void updateCsErrorByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
			
		int rows = this.getSqlMapClientTemplate().update("updateCsErrorBy", allParams);
		Trigger.on(CsError.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除错误信息
	 * @param params
	 */
	public void deleteCsErrorByConfirm(Map params){
		Trigger.on(CsError.class,Trigger.DELETE,params);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsErrorBy", params);
	}
	

}