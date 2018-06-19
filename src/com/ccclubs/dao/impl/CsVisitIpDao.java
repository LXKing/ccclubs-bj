package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsVisitIpDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsVisitIp;
import com.lazy3q.web.helper.$;

/**
 * 独立IP访问的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsVisitIpDao extends SqlMapClientDaoSupport implements ICsVisitIpDao
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
	private CsVisitIp custom(CsVisitIp params){
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
			"csviUrlFrom"			
			,"csviUrlTo"			
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
	 * 获取所有独立IP访问
	 * @return
	 */	
	public List<CsVisitIp> getCsVisitIpList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsVisitIp> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsVisitIpList", params);
		
		return list;
	}
	
	
	/**
	 * 获取独立IP访问统计
	 * @return
	 */
	public List getCsVisitIpStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsVisitIpStats", params);
	}

	/**
	 * 获取独立IP访问分页
	 * @return
	 */
	public Page getCsVisitIpPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsVisitIpCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsVisitIpPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取独立IP访问数量
	 * @return
	 */	
	public Long getCsVisitIpCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsVisitIpEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取独立IP访问自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsVisitIpEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsVisitIpEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取独立IP访问
	 * @param params
	 * @return
	 */
	public CsVisitIp getCsVisitIp(Map params)
	{		
		params = custom(params);
			
		List<CsVisitIp> list=this.getCsVisitIpList(params,1);
		CsVisitIp csVisitIp = list.isEmpty()?null:list.get(0);
		
		return csVisitIp;
	}
		
	/**
	 * 根据ID取独立IP访问
	 * @param id
	 * @return
	 */
	public CsVisitIp getCsVisitIpById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsVisitIp csVisitIp = null;
		if(csVisitIp!=null)
			return csVisitIp;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csVisitIp = (CsVisitIp) this.getSqlMapClientTemplate().queryForObject("getCsVisitIpById",params);
		if(csVisitIp!=null)
			MemCache.setValue(CsVisitIp.class,id, csVisitIp.getKeyValue());
		return csVisitIp;
	}
	
	/**
	 * 根据ID取独立IP访问(不缓存)
	 * @param id
	 * @return
	 */
	public CsVisitIp GetCsVisitIpById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsVisitIp) this.getSqlMapClientTemplate().queryForObject("getCsVisitIpById",params);
	}
	

	/**
	 * 保存独立IP访问
	 * @param csVisitIp
	 */
	public CsVisitIp saveCsVisitIp(CsVisitIp csVisitIp)
	{
		csVisitIp = custom(csVisitIp);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsVisitIp", csVisitIp));
		csVisitIp = this.GetCsVisitIpById(id);
		MemCache.setValue(CsVisitIp.class,id, csVisitIp.getKeyValue());
		return Trigger.on(CsVisitIp.class,Trigger.SAVE,csVisitIp);	
	}

	

	/**
	 * 更新独立IP访问
	 * @param csVisitIp
	 */
	public void updateCsVisitIp(CsVisitIp csVisitIp)
	{
		csVisitIp = custom(csVisitIp);
		this.getSqlMapClientTemplate().update("updateCsVisitIp", csVisitIp);
		csVisitIp = this.GetCsVisitIpById(csVisitIp.getCsviId());
		if(csVisitIp!=null){
			MemCache.setValue(CsVisitIp.class,csVisitIp.getCsviId(), csVisitIp.getKeyValue());
		}
		Trigger.on(CsVisitIp.class,Trigger.UPDATE,csVisitIp);
	}
	/**
	 * 更新独立IP访问非空字段
	 * @param csVisitIp
	 */
	public void updateCsVisitIp$NotNull(CsVisitIp csVisitIp)
	{
		csVisitIp = custom(csVisitIp);
		this.getSqlMapClientTemplate().update("updateCsVisitIp$NotNull", csVisitIp);
		csVisitIp = this.GetCsVisitIpById(csVisitIp.getCsviId());
		if(csVisitIp!=null){
			MemCache.setValue(CsVisitIp.class,csVisitIp.getCsviId(), csVisitIp.getKeyValue());
		}
		Trigger.on(CsVisitIp.class,Trigger.UPDATE,csVisitIp);
	}

	/**
	 * 根据ID删除一个独立IP访问
	 * @param id
	 */
	public void deleteCsVisitIpById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsVisitIp.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsVisitIpById", params);
		MemCache.deleteValue(CsVisitIp.class, id);
	}
	/**
	 * 根据条件更新独立IP访问
	 * @param values
	 * @param params
	 */
	public void updateCsVisitIpByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsVisitIpCount(params)>255){
			MemCache.clearValue(CsVisitIp.class);
		}else{
			params.put("eval","csvi_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsVisitIpEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsVisitIp.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsVisitIpBy", allParams);
		Trigger.on(CsVisitIp.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除独立IP访问
	 * @param params
	 */
	public void deleteCsVisitIpByConfirm(Map params){
		Trigger.on(CsVisitIp.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsVisitIpCount(params)>255){
			MemCache.clearValue(CsVisitIp.class);
		}else{
			params.put("eval","csvi_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsVisitIpEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsVisitIp.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsVisitIpBy", params);
	}
	

}