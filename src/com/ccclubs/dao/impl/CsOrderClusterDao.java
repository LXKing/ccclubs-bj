package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsOrderClusterDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsOrderCluster;
import com.lazy3q.web.helper.$;

/**
 * 订单簇的Dao实现
 * @author Joel
 */
@SuppressWarnings("unchecked")
public class CsOrderClusterDao extends SqlMapClientDaoSupport implements ICsOrderClusterDao
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
	private CsOrderCluster custom(CsOrderCluster params){
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
		String[] likes={"csocMobile"
		//"csocId","csocHost","csocSubOrders","csocPayMember","csocMobile","csocPrice","csocMarginNeed","csocTotalDuration","csocPayMoney","csocPayCoupon","csocPayCoin","csocPayNeed","csocPayReal","csocPayRent","csocPayInsure","csocPayTimeout","csocPayDetails","csocOutType","csocOutId","csocStartTime","csocFinishTime","csocStatus","csocAddTime","csocUpdateTime"
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
	 * 获取所有订单簇
	 * @return
	 */	
	public List<CsOrderCluster> getCsOrderClusterList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsOrderCluster> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsOrderClusterList", params);
		
		return list;
	}
	
	
	/**
	 * 获取订单簇统计
	 * @return
	 */
	public List getCsOrderClusterStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsOrderClusterStats", params);
	}

	/**
	 * 获取订单簇分页
	 * @return
	 */
	public Page getCsOrderClusterPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsOrderClusterCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsOrderClusterPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取订单簇数量
	 * @return
	 */	
	public Long getCsOrderClusterCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsOrderClusterEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取订单簇自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsOrderClusterEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsOrderClusterEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取订单簇
	 * @param params
	 * @return
	 */
	public CsOrderCluster getCsOrderCluster(Map params)
	{		
		params = custom(params);
			
		List<CsOrderCluster> list=this.getCsOrderClusterList(params,1);
		CsOrderCluster csOrderCluster = list.isEmpty()?null:list.get(0);
		
		return csOrderCluster;
	}
		
	/**
	 * 根据ID取订单簇
	 * @param id
	 * @return
	 */
	public CsOrderCluster getCsOrderClusterById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsOrderCluster csOrderCluster = null;
		if(csOrderCluster!=null)
			return csOrderCluster;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csOrderCluster = (CsOrderCluster) this.getSqlMapClientTemplate().queryForObject("getCsOrderClusterById",params);
		if(csOrderCluster!=null)
			MemCache.setValue(CsOrderCluster.class,id, csOrderCluster.getKeyValue());
		return csOrderCluster;
	}
	
	/**
	 * 根据ID取订单簇(不缓存)
	 * @param id
	 * @return
	 */
	public CsOrderCluster GetCsOrderClusterById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsOrderCluster) this.getSqlMapClientTemplate().queryForObject("getCsOrderClusterById",params);
	}
	

	/**
	 * 保存订单簇
	 * @param csOrderCluster
	 */
	public CsOrderCluster saveCsOrderCluster(CsOrderCluster csOrderCluster)
	{
		csOrderCluster = custom(csOrderCluster);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsOrderCluster", csOrderCluster));
		csOrderCluster = this.GetCsOrderClusterById(id);
		MemCache.setValue(CsOrderCluster.class,id, csOrderCluster.getKeyValue());
		return Trigger.on(CsOrderCluster.class,Trigger.SAVE,csOrderCluster);	
	}

	

	/**
	 * 更新订单簇
	 * @param csOrderCluster
	 */
	public void updateCsOrderCluster(CsOrderCluster csOrderCluster)
	{
		csOrderCluster = custom(csOrderCluster);
		this.getSqlMapClientTemplate().update("updateCsOrderCluster", csOrderCluster);
		csOrderCluster = this.GetCsOrderClusterById(csOrderCluster.getCsocId());
		if(csOrderCluster!=null){
			MemCache.setValue(CsOrderCluster.class,csOrderCluster.getCsocId(), csOrderCluster.getKeyValue());
		}
		Trigger.on(CsOrderCluster.class,Trigger.UPDATE,csOrderCluster);
	}
	/**
	 * 更新订单簇非空字段
	 * @param csOrderCluster
	 */
	public void updateCsOrderCluster$NotNull(CsOrderCluster csOrderCluster)
	{
		csOrderCluster = custom(csOrderCluster);
		this.getSqlMapClientTemplate().update("updateCsOrderCluster$NotNull", csOrderCluster);
		csOrderCluster = this.GetCsOrderClusterById(csOrderCluster.getCsocId());
		if(csOrderCluster!=null){
			MemCache.setValue(CsOrderCluster.class,csOrderCluster.getCsocId(), csOrderCluster.getKeyValue());
		}
		Trigger.on(CsOrderCluster.class,Trigger.UPDATE,csOrderCluster);
	}

	/**
	 * 根据ID删除一个订单簇
	 * @param id
	 */
	public void deleteCsOrderClusterById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsOrderCluster.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsOrderClusterById", params);
		MemCache.deleteValue(CsOrderCluster.class, id);
	}
	/**
	 * 根据ID逻辑删除订单簇 
	 * @param id
	 * @return
	 */
	public void removeCsOrderClusterById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsOrderCluster.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsOrderClusterById", params);
		MemCache.deleteValue(CsOrderCluster.class, id);
	}
	/**
	 * 根据条件更新订单簇
	 * @param values
	 * @param params
	 */
	public void updateCsOrderClusterByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsOrderClusterCount(params)>255){
			MemCache.clearValue(CsOrderCluster.class);
		}else{
			params.put("eval","csoc_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsOrderClusterEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsOrderCluster.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsOrderClusterBy", allParams);
		Trigger.on(CsOrderCluster.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除订单簇
	 * @param params
	 */
	public void deleteCsOrderClusterByConfirm(Map params){
		Trigger.on(CsOrderCluster.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsOrderClusterCount(params)>255){
			MemCache.clearValue(CsOrderCluster.class);
		}else{
			params.put("eval","csoc_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsOrderClusterEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsOrderCluster.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsOrderClusterBy", params);
	}
	

}