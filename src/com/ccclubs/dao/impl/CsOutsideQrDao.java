package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsOutsideQrDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsOutsideQr;
import com.lazy3q.web.helper.$;

/**
 * 巡检作业的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsOutsideQrDao extends SqlMapClientDaoSupport implements ICsOutsideQrDao
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
	private CsOutsideQr custom(CsOutsideQr params){
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
			"csoqRemark"			
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
	 * 获取所有巡检作业
	 * @return
	 */	
	public List<CsOutsideQr> getCsOutsideQrList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsOutsideQr> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsOutsideQrList", params);
		
		return list;
	}
	
	
	/**
	 * 获取巡检作业统计
	 * @return
	 */
	public List getCsOutsideQrStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsOutsideQrStats", params);
	}

	/**
	 * 获取巡检作业分页
	 * @return
	 */
	public Page getCsOutsideQrPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsOutsideQrCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsOutsideQrPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取巡检作业数量
	 * @return
	 */	
	public Long getCsOutsideQrCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsOutsideQrEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取巡检作业自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsOutsideQrEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsOutsideQrEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取巡检作业
	 * @param params
	 * @return
	 */
	public CsOutsideQr getCsOutsideQr(Map params)
	{		
		params = custom(params);
			
		List<CsOutsideQr> list=this.getCsOutsideQrList(params,1);
		CsOutsideQr csOutsideQr = list.isEmpty()?null:list.get(0);
		
		return csOutsideQr;
	}
		
	/**
	 * 根据ID取巡检作业
	 * @param id
	 * @return
	 */
	public CsOutsideQr getCsOutsideQrById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsOutsideQr csOutsideQr = null;
		if(csOutsideQr!=null)
			return csOutsideQr;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csOutsideQr = (CsOutsideQr) this.getSqlMapClientTemplate().queryForObject("getCsOutsideQrById",params);
		if(csOutsideQr!=null)
			MemCache.setValue(CsOutsideQr.class,id, csOutsideQr.getKeyValue());
		return csOutsideQr;
	}
	
	/**
	 * 根据ID取巡检作业(不缓存)
	 * @param id
	 * @return
	 */
	public CsOutsideQr GetCsOutsideQrById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsOutsideQr) this.getSqlMapClientTemplate().queryForObject("getCsOutsideQrById",params);
	}
	

	/**
	 * 保存巡检作业
	 * @param csOutsideQr
	 */
	public CsOutsideQr saveCsOutsideQr(CsOutsideQr csOutsideQr)
	{
		csOutsideQr = custom(csOutsideQr);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsOutsideQr", csOutsideQr));
		csOutsideQr = this.GetCsOutsideQrById(id);
		MemCache.setValue(CsOutsideQr.class,id, csOutsideQr.getKeyValue());
		return Trigger.on(CsOutsideQr.class,Trigger.SAVE,csOutsideQr);	
	}

	

	/**
	 * 更新巡检作业
	 * @param csOutsideQr
	 */
	public void updateCsOutsideQr(CsOutsideQr csOutsideQr)
	{
		csOutsideQr = custom(csOutsideQr);
		this.getSqlMapClientTemplate().update("updateCsOutsideQr", csOutsideQr);
		csOutsideQr = this.GetCsOutsideQrById(csOutsideQr.getCsoqId());
		if(csOutsideQr!=null){
			MemCache.setValue(CsOutsideQr.class,csOutsideQr.getCsoqId(), csOutsideQr.getKeyValue());
		}
		Trigger.on(CsOutsideQr.class,Trigger.UPDATE,csOutsideQr);
	}
	/**
	 * 更新巡检作业非空字段
	 * @param csOutsideQr
	 */
	public void updateCsOutsideQr$NotNull(CsOutsideQr csOutsideQr)
	{
		csOutsideQr = custom(csOutsideQr);
		this.getSqlMapClientTemplate().update("updateCsOutsideQr$NotNull", csOutsideQr);
		csOutsideQr = this.GetCsOutsideQrById(csOutsideQr.getCsoqId());
		if(csOutsideQr!=null){
			MemCache.setValue(CsOutsideQr.class,csOutsideQr.getCsoqId(), csOutsideQr.getKeyValue());
		}
		Trigger.on(CsOutsideQr.class,Trigger.UPDATE,csOutsideQr);
	}

	/**
	 * 根据ID删除一个巡检作业
	 * @param id
	 */
	public void deleteCsOutsideQrById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsOutsideQr.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsOutsideQrById", params);
		MemCache.deleteValue(CsOutsideQr.class, id);
	}
	/**
	 * 根据ID逻辑删除巡检作业 
	 * @param id
	 * @return
	 */
	public void removeCsOutsideQrById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsOutsideQr.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsOutsideQrById", params);
		MemCache.deleteValue(CsOutsideQr.class, id);
	}
	/**
	 * 根据条件更新巡检作业
	 * @param values
	 * @param params
	 */
	public void updateCsOutsideQrByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsOutsideQrCount(params)>255){
			MemCache.clearValue(CsOutsideQr.class);
		}else{
			params.put("eval","csoq_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsOutsideQrEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsOutsideQr.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsOutsideQrBy", allParams);
		Trigger.on(CsOutsideQr.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除巡检作业
	 * @param params
	 */
	public void deleteCsOutsideQrByConfirm(Map params){
		Trigger.on(CsOutsideQr.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsOutsideQrCount(params)>255){
			MemCache.clearValue(CsOutsideQr.class);
		}else{
			params.put("eval","csoq_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsOutsideQrEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsOutsideQr.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsOutsideQrBy", params);
	}
	

}