package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsChargeBillDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsChargeBill;
import com.lazy3q.web.helper.$;

/**
 * 充电订单的Dao实现
 * @author Joel
 */
@SuppressWarnings("unchecked")
public class CsChargeBillDao extends SqlMapClientDaoSupport implements ICsChargeBillDao
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
	private CsChargeBill custom(CsChargeBill params){
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
		"cscbId","cscbSerialnumber","cscbStation","cscbPileCode","cscbCarno","cscbCustId","cscbCustName","cscbCustCardno","cscbStartEnergy","cscbFinishEnergy","cscbStartTime","cscbFinishTime","cscbTotalPower","cscbTotalElecfee","cscbStatus","cscbProvider","cscbData","cscbAddTime","cscbUpdateTime"
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
	 * 获取所有充电订单
	 * @return
	 */	
	public List<CsChargeBill> getCsChargeBillList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsChargeBill> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsChargeBillList", params);
		
		return list;
	}
	
	
	/**
	 * 获取充电订单统计
	 * @return
	 */
	public List getCsChargeBillStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsChargeBillStats", params);
	}

	/**
	 * 获取充电订单分页
	 * @return
	 */
	public Page getCsChargeBillPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsChargeBillCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsChargeBillPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取充电订单数量
	 * @return
	 */	
	public Long getCsChargeBillCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsChargeBillEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取充电订单自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsChargeBillEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsChargeBillEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取充电订单
	 * @param params
	 * @return
	 */
	public CsChargeBill getCsChargeBill(Map params)
	{		
		params = custom(params);
			
		List<CsChargeBill> list=this.getCsChargeBillList(params,1);
		CsChargeBill csChargeBill = list.isEmpty()?null:list.get(0);
		
		return csChargeBill;
	}
		
	/**
	 * 根据ID取充电订单
	 * @param id
	 * @return
	 */
	public CsChargeBill getCsChargeBillById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsChargeBill csChargeBill = null;
		if(csChargeBill!=null)
			return csChargeBill;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csChargeBill = (CsChargeBill) this.getSqlMapClientTemplate().queryForObject("getCsChargeBillById",params);
		if(csChargeBill!=null)
			MemCache.setValue(CsChargeBill.class,id, csChargeBill.getKeyValue());
		return csChargeBill;
	}
	
	/**
	 * 根据ID取充电订单(不缓存)
	 * @param id
	 * @return
	 */
	public CsChargeBill GetCsChargeBillById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsChargeBill) this.getSqlMapClientTemplate().queryForObject("getCsChargeBillById",params);
	}
	

	/**
	 * 保存充电订单
	 * @param csChargeBill
	 */
	public CsChargeBill saveCsChargeBill(CsChargeBill csChargeBill)
	{
		csChargeBill = custom(csChargeBill);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsChargeBill", csChargeBill));
		csChargeBill = this.GetCsChargeBillById(id);
		MemCache.setValue(CsChargeBill.class,id, csChargeBill.getKeyValue());
		return Trigger.on(CsChargeBill.class,Trigger.SAVE,csChargeBill);	
	}

	

	/**
	 * 更新充电订单
	 * @param csChargeBill
	 */
	public void updateCsChargeBill(CsChargeBill csChargeBill)
	{
		csChargeBill = custom(csChargeBill);
		this.getSqlMapClientTemplate().update("updateCsChargeBill", csChargeBill);
		csChargeBill = this.GetCsChargeBillById(csChargeBill.getCscbId());
		if(csChargeBill!=null){
			MemCache.setValue(CsChargeBill.class,csChargeBill.getCscbId(), csChargeBill.getKeyValue());
		}
		Trigger.on(CsChargeBill.class,Trigger.UPDATE,csChargeBill);
	}
	/**
	 * 更新充电订单非空字段
	 * @param csChargeBill
	 */
	public void updateCsChargeBill$NotNull(CsChargeBill csChargeBill)
	{
		csChargeBill = custom(csChargeBill);
		this.getSqlMapClientTemplate().update("updateCsChargeBill$NotNull", csChargeBill);
		csChargeBill = this.GetCsChargeBillById(csChargeBill.getCscbId());
		if(csChargeBill!=null){
			MemCache.setValue(CsChargeBill.class,csChargeBill.getCscbId(), csChargeBill.getKeyValue());
		}
		Trigger.on(CsChargeBill.class,Trigger.UPDATE,csChargeBill);
	}

	/**
	 * 根据ID删除一个充电订单
	 * @param id
	 */
	public void deleteCsChargeBillById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsChargeBill.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsChargeBillById", params);
		MemCache.deleteValue(CsChargeBill.class, id);
	}
	/**
	 * 根据ID逻辑删除充电订单 
	 * @param id
	 * @return
	 */
	public void removeCsChargeBillById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsChargeBill.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsChargeBillById", params);
		MemCache.deleteValue(CsChargeBill.class, id);
	}
	/**
	 * 根据条件更新充电订单
	 * @param values
	 * @param params
	 */
	public void updateCsChargeBillByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsChargeBillCount(params)>255){
			MemCache.clearValue(CsChargeBill.class);
		}else{
			params.put("eval","cscb_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsChargeBillEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsChargeBill.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsChargeBillBy", allParams);
		Trigger.on(CsChargeBill.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除充电订单
	 * @param params
	 */
	public void deleteCsChargeBillByConfirm(Map params){
		Trigger.on(CsChargeBill.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsChargeBillCount(params)>255){
			MemCache.clearValue(CsChargeBill.class);
		}else{
			params.put("eval","cscb_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsChargeBillEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsChargeBill.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsChargeBillBy", params);
	}
	

}