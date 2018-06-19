package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsSmsMtDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsSmsMt;
import com.lazy3q.web.helper.$;

/**
 * 短信发送的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsSmsMtDao extends SqlMapClientDaoSupport implements ICsSmsMtDao
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
	private CsSmsMt custom(CsSmsMt params){
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
			"cssmContent"			
			,"cssmRemark"			
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
	 * 获取所有短信发送
	 * @return
	 */	
	public List<CsSmsMt> getCsSmsMtList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsSmsMt> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsSmsMtList", params);
		
		return list;
	}
	
	
	/**
	 * 获取短信发送统计
	 * @return
	 */
	public List getCsSmsMtStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsSmsMtStats", params);
	}

	/**
	 * 获取短信发送分页
	 * @return
	 */
	public Page getCsSmsMtPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsSmsMtCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsSmsMtPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取短信发送数量
	 * @return
	 */	
	public Long getCsSmsMtCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsSmsMtEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取短信发送自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsSmsMtEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsSmsMtEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取短信发送
	 * @param params
	 * @return
	 */
	public CsSmsMt getCsSmsMt(Map params)
	{		
		params = custom(params);
			
		List<CsSmsMt> list=this.getCsSmsMtList(params,1);
		CsSmsMt csSmsMt = list.isEmpty()?null:list.get(0);
		
		return csSmsMt;
	}
		
	/**
	 * 根据ID取短信发送
	 * @param id
	 * @return
	 */
	public CsSmsMt getCsSmsMtById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsSmsMt csSmsMt = null;
		if(csSmsMt!=null)
			return csSmsMt;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csSmsMt = (CsSmsMt) this.getSqlMapClientTemplate().queryForObject("getCsSmsMtById",params);
		return csSmsMt;
	}
	
	/**
	 * 根据ID取短信发送(不缓存)
	 * @param id
	 * @return
	 */
	public CsSmsMt GetCsSmsMtById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsSmsMt) this.getSqlMapClientTemplate().queryForObject("getCsSmsMtById",params);
	}
	

	/**
	 * 保存短信发送
	 * @param csSmsMt
	 */
	public CsSmsMt saveCsSmsMt(CsSmsMt csSmsMt)
	{
		csSmsMt = custom(csSmsMt);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsSmsMt", csSmsMt));
		csSmsMt = this.GetCsSmsMtById(id);
		return Trigger.on(CsSmsMt.class,Trigger.SAVE,csSmsMt);	
	}

	

	/**
	 * 更新短信发送
	 * @param csSmsMt
	 */
	public void updateCsSmsMt(CsSmsMt csSmsMt)
	{
		csSmsMt = custom(csSmsMt);
		this.getSqlMapClientTemplate().update("updateCsSmsMt", csSmsMt);
		csSmsMt = this.GetCsSmsMtById(csSmsMt.getCssmId());
		Trigger.on(CsSmsMt.class,Trigger.UPDATE,csSmsMt);
	}
	/**
	 * 更新短信发送非空字段
	 * @param csSmsMt
	 */
	public void updateCsSmsMt$NotNull(CsSmsMt csSmsMt)
	{
		csSmsMt = custom(csSmsMt);
		this.getSqlMapClientTemplate().update("updateCsSmsMt$NotNull", csSmsMt);
		csSmsMt = this.GetCsSmsMtById(csSmsMt.getCssmId());
		Trigger.on(CsSmsMt.class,Trigger.UPDATE,csSmsMt);
	}

	/**
	 * 根据ID删除一个短信发送
	 * @param id
	 */
	public void deleteCsSmsMtById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsSmsMt.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsSmsMtById", params);
	}
	/**
	 * 根据ID逻辑删除短信发送 
	 * @param id
	 * @return
	 */
	public void removeCsSmsMtById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsSmsMt.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsSmsMtById", params);
	}
	/**
	 * 根据条件更新短信发送
	 * @param values
	 * @param params
	 */
	public void updateCsSmsMtByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
			
		int rows = this.getSqlMapClientTemplate().update("updateCsSmsMtBy", allParams);
		Trigger.on(CsSmsMt.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除短信发送
	 * @param params
	 */
	public void deleteCsSmsMtByConfirm(Map params){
		Trigger.on(CsSmsMt.class,Trigger.DELETE,params);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsSmsMtBy", params);
	}
	

}