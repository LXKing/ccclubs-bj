package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsCoinDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCoin;
import com.lazy3q.web.helper.$;

/**
 * 红包的Dao实现
 * @author Joel
 */
@SuppressWarnings("unchecked")
public class CsCoinDao extends SqlMapClientDaoSupport implements ICsCoinDao
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
	private CsCoin custom(CsCoin params){
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
				"cscRemark"			
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
	 * 获取所有红包
	 * @return
	 */	
	public List<CsCoin> getCsCoinList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsCoin> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsCoinList", params);
		
		return list;
	}
	
	
	/**
	 * 获取红包统计
	 * @return
	 */
	public List getCsCoinStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsCoinStats", params);
	}

	/**
	 * 获取红包分页
	 * @return
	 */
	public Page getCsCoinPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsCoinCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsCoinPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取红包数量
	 * @return
	 */	
	public Long getCsCoinCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCoinEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取红包自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsCoinEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCoinEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取红包
	 * @param params
	 * @return
	 */
	public CsCoin getCsCoin(Map params)
	{		
		params = custom(params);
			
		List<CsCoin> list=this.getCsCoinList(params,1);
		CsCoin csCoin = list.isEmpty()?null:list.get(0);
		
		return csCoin;
	}
		
	/**
	 * 根据ID取红包
	 * @param id
	 * @return
	 */
	public CsCoin getCsCoinById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsCoin csCoin = null;
		if(csCoin!=null)
			return csCoin;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csCoin = (CsCoin) this.getSqlMapClientTemplate().queryForObject("getCsCoinById",params);
		if(csCoin!=null)
			MemCache.setValue(CsCoin.class,id, csCoin.getKeyValue());
		return csCoin;
	}
	
	/**
	 * 根据ID取红包(不缓存)
	 * @param id
	 * @return
	 */
	public CsCoin GetCsCoinById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsCoin) this.getSqlMapClientTemplate().queryForObject("getCsCoinById",params);
	}
	

	/**
	 * 保存红包
	 * @param csCoin
	 */
	public CsCoin saveCsCoin(CsCoin csCoin)
	{
		csCoin = custom(csCoin);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsCoin", csCoin));
		csCoin = this.GetCsCoinById(id);
		MemCache.setValue(CsCoin.class,id, csCoin.getKeyValue());
		return Trigger.on(CsCoin.class,Trigger.SAVE,csCoin);	
	}

	

	/**
	 * 更新红包
	 * @param csCoin
	 */
	public void updateCsCoin(CsCoin csCoin)
	{
		csCoin = custom(csCoin);
		this.getSqlMapClientTemplate().update("updateCsCoin", csCoin);
		csCoin = this.GetCsCoinById(csCoin.getCscId());
		if(csCoin!=null){
			MemCache.setValue(CsCoin.class,csCoin.getCscId(), csCoin.getKeyValue());
		}
		Trigger.on(CsCoin.class,Trigger.UPDATE,csCoin);
	}
	/**
	 * 更新红包非空字段
	 * @param csCoin
	 */
	public void updateCsCoin$NotNull(CsCoin csCoin)
	{
		csCoin = custom(csCoin);
		this.getSqlMapClientTemplate().update("updateCsCoin$NotNull", csCoin);
		csCoin = this.GetCsCoinById(csCoin.getCscId());
		if(csCoin!=null){
			MemCache.setValue(CsCoin.class,csCoin.getCscId(), csCoin.getKeyValue());
		}
		Trigger.on(CsCoin.class,Trigger.UPDATE,csCoin);
	}

	/**
	 * 根据ID删除一个红包
	 * @param id
	 */
	public void deleteCsCoinById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsCoin.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsCoinById", params);
		MemCache.deleteValue(CsCoin.class, id);
	}
	/**
	 * 根据ID逻辑删除红包 
	 * @param id
	 * @return
	 */
	public void removeCsCoinById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsCoin.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsCoinById", params);
		MemCache.deleteValue(CsCoin.class, id);
	}
	/**
	 * 根据条件更新红包
	 * @param values
	 * @param params
	 */
	public void updateCsCoinByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsCoinCount(params)>255){
			MemCache.clearValue(CsCoin.class);
		}else{
			params.put("eval","csc_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCoinEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCoin.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsCoinBy", allParams);
		Trigger.on(CsCoin.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除红包
	 * @param params
	 */
	public void deleteCsCoinByConfirm(Map params){
		Trigger.on(CsCoin.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsCoinCount(params)>255){
			MemCache.clearValue(CsCoin.class);
		}else{
			params.put("eval","csc_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCoinEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCoin.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsCoinBy", params);
	}
	

}