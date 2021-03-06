package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsViolatDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsViolat;
import com.lazy3q.web.helper.$;

/**
 * 车辆违章的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsViolatDao extends SqlMapClientDaoSupport implements ICsViolatDao
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
	private CsViolat custom(CsViolat params){
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
			"csvDecipt"			
			,"csvAddress"			
			,"csvRemark"			
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
	 * 获取所有车辆违章
	 * @return
	 */	
	public List<CsViolat> getCsViolatList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsViolat> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsViolatList", params);
		
		return list;
	}
	
	
	/**
	 * 获取车辆违章统计
	 * @return
	 */
	public List getCsViolatStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsViolatStats", params);
	}

	/**
	 * 获取车辆违章分页
	 * @return
	 */
	public Page getCsViolatPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsViolatCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsViolatPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取车辆违章数量
	 * @return
	 */	
	public Long getCsViolatCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsViolatEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取车辆违章自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsViolatEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsViolatEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取车辆违章
	 * @param params
	 * @return
	 */
	public CsViolat getCsViolat(Map params)
	{		
		params = custom(params);
			
		List<CsViolat> list=this.getCsViolatList(params,1);
		CsViolat csViolat = list.isEmpty()?null:list.get(0);
		
		return csViolat;
	}
		
	/**
	 * 根据ID取车辆违章
	 * @param id
	 * @return
	 */
	public CsViolat getCsViolatById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsViolat csViolat = null;
		if(csViolat!=null)
			return csViolat;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csViolat = (CsViolat) this.getSqlMapClientTemplate().queryForObject("getCsViolatById",params);
		if(csViolat!=null)
			MemCache.setValue(CsViolat.class,id, csViolat.getKeyValue());
		return csViolat;
	}
	
	/**
	 * 根据ID取车辆违章(不缓存)
	 * @param id
	 * @return
	 */
	public CsViolat GetCsViolatById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsViolat) this.getSqlMapClientTemplate().queryForObject("getCsViolatById",params);
	}
	

	/**
	 * 保存车辆违章
	 * @param csViolat
	 */
	public CsViolat saveCsViolat(CsViolat csViolat)
	{
		csViolat = custom(csViolat);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsViolat", csViolat));
		csViolat = this.GetCsViolatById(id);
		MemCache.setValue(CsViolat.class,id, csViolat.getKeyValue());
		return Trigger.on(CsViolat.class,Trigger.SAVE,csViolat);	
	}

	

	/**
	 * 更新车辆违章
	 * @param csViolat
	 */
	public void updateCsViolat(CsViolat csViolat)
	{
		csViolat = custom(csViolat);
		this.getSqlMapClientTemplate().update("updateCsViolat", csViolat);
		csViolat = this.GetCsViolatById(csViolat.getCsvId());
		if(csViolat!=null){
			MemCache.setValue(CsViolat.class,csViolat.getCsvId(), csViolat.getKeyValue());
		}
		Trigger.on(CsViolat.class,Trigger.UPDATE,csViolat);
	}
	/**
	 * 更新车辆违章非空字段
	 * @param csViolat
	 */
	public void updateCsViolat$NotNull(CsViolat csViolat)
	{
		csViolat = custom(csViolat);
		this.getSqlMapClientTemplate().update("updateCsViolat$NotNull", csViolat);
		csViolat = this.GetCsViolatById(csViolat.getCsvId());
		if(csViolat!=null){
			MemCache.setValue(CsViolat.class,csViolat.getCsvId(), csViolat.getKeyValue());
		}
		Trigger.on(CsViolat.class,Trigger.UPDATE,csViolat);
	}

	/**
	 * 根据ID删除一个车辆违章
	 * @param id
	 */
	public void deleteCsViolatById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsViolat.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsViolatById", params);
		MemCache.deleteValue(CsViolat.class, id);
	}
	/**
	 * 根据ID逻辑删除车辆违章 
	 * @param id
	 * @return
	 */
	public void removeCsViolatById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsViolat.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsViolatById", params);
		MemCache.deleteValue(CsViolat.class, id);
	}
	/**
	 * 根据条件更新车辆违章
	 * @param values
	 * @param params
	 */
	public void updateCsViolatByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsViolatCount(params)>255){
			MemCache.clearValue(CsViolat.class);
		}else{
			params.put("eval","csv_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsViolatEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsViolat.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsViolatBy", allParams);
		Trigger.on(CsViolat.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除车辆违章
	 * @param params
	 */
	public void deleteCsViolatByConfirm(Map params){
		Trigger.on(CsViolat.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsViolatCount(params)>255){
			MemCache.clearValue(CsViolat.class);
		}else{
			params.put("eval","csv_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsViolatEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsViolat.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsViolatBy", params);
	}
	

}