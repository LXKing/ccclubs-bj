package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsSpecialCarDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsSpecialCar;
import com.lazy3q.web.helper.$;

/**
 * 专车订单的Dao实现
 * @author Joel
 */
@SuppressWarnings("unchecked")
public class CsSpecialCarDao extends SqlMapClientDaoSupport implements ICsSpecialCarDao
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
	private CsSpecialCar custom(CsSpecialCar params){
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
//		//为模糊匹配的varchar类型字段加上%号
//		String[] likes={
//		"csscUnitOrder","csscName","csscMobile","csscDriverName","csscDriverPhone","csscDriverImage","csscDriverColor","csscDriverCarInfo","csscStartPoint","csscStartTime","csscFinishPoint","csscFinishTime","csscDuration","csscRealDur","csscKilo","csscScope","csscThird","csscThirdNo","csscThirdFee","csscThirdOtherFee","csscUpdateTime","csscAddTime","csscStatus","csscPreditMileage","csscEstimateFee","csscDemand","csscIsCarpool","csscDriverArrivaltime","csscDrivercar"
//		};
//		for(String key:likes){
//			String value=(String) params.get(key);
//			if(value==null || value.trim().equals(""))
//				params.remove(key);
//			else
//				params.put(key, "%"+value.replaceAll("%", "")+"%");
//		}
		return params;
	}
	

	/**
	 * 获取所有专车订单
	 * @return
	 */	
	public List<CsSpecialCar> getCsSpecialCarList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsSpecialCar> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsSpecialCarList", params);
		
		return list;
	}
	
	
	/**
	 * 获取专车订单统计
	 * @return
	 */
	public List getCsSpecialCarStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsSpecialCarStats", params);
	}

	/**
	 * 获取专车订单分页
	 * @return
	 */
	public Page getCsSpecialCarPage(int page,int size,Map params)
	{
		params = custom(params);
		//params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsSpecialCarCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsSpecialCarPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取专车订单数量
	 * @return
	 */	
	public Long getCsSpecialCarCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsSpecialCarEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取专车订单自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsSpecialCarEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsSpecialCarEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取专车订单
	 * @param params
	 * @return
	 */
	public CsSpecialCar getCsSpecialCar(Map params)
	{		
		params = custom(params);
			
		List<CsSpecialCar> list=this.getCsSpecialCarList(params,1);
		CsSpecialCar csSpecialCar = list.isEmpty()?null:list.get(0);
		
		return csSpecialCar;
	}
		
	/**
	 * 根据ID取专车订单
	 * @param id
	 * @return
	 */
	public CsSpecialCar getCsSpecialCarById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsSpecialCar csSpecialCar = null;
		if(csSpecialCar!=null)
			return csSpecialCar;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csSpecialCar = (CsSpecialCar) this.getSqlMapClientTemplate().queryForObject("getCsSpecialCarById",params);
		if(csSpecialCar!=null)
			MemCache.setValue(CsSpecialCar.class,id, csSpecialCar.getKeyValue());
		return csSpecialCar;
	}
	
	/**
	 * 根据ID取专车订单(不缓存)
	 * @param id
	 * @return
	 */
	public CsSpecialCar GetCsSpecialCarById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsSpecialCar) this.getSqlMapClientTemplate().queryForObject("getCsSpecialCarById",params);
	}
	

	/**
	 * 保存专车订单
	 * @param csSpecialCar
	 */
	public CsSpecialCar saveCsSpecialCar(CsSpecialCar csSpecialCar)
	{
		csSpecialCar = custom(csSpecialCar);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsSpecialCar", csSpecialCar));
		csSpecialCar = this.GetCsSpecialCarById(id);
		MemCache.setValue(CsSpecialCar.class,id, csSpecialCar.getKeyValue());
		return Trigger.on(CsSpecialCar.class,Trigger.SAVE,csSpecialCar);	
	}

	

	/**
	 * 更新专车订单
	 * @param csSpecialCar
	 */
	public void updateCsSpecialCar(CsSpecialCar csSpecialCar)
	{
		csSpecialCar = custom(csSpecialCar);
		this.getSqlMapClientTemplate().update("updateCsSpecialCar", csSpecialCar);
		csSpecialCar = this.GetCsSpecialCarById(csSpecialCar.getCsscId());
		if(csSpecialCar!=null){
			MemCache.setValue(CsSpecialCar.class,csSpecialCar.getCsscId(), csSpecialCar.getKeyValue());
		}
		Trigger.on(CsSpecialCar.class,Trigger.UPDATE,csSpecialCar);
	}
	/**
	 * 更新专车订单非空字段
	 * @param csSpecialCar
	 */
	public void updateCsSpecialCar$NotNull(CsSpecialCar csSpecialCar)
	{
		csSpecialCar = custom(csSpecialCar);
		this.getSqlMapClientTemplate().update("updateCsSpecialCar$NotNull", csSpecialCar);
		csSpecialCar = this.GetCsSpecialCarById(csSpecialCar.getCsscId());
		if(csSpecialCar!=null){
			MemCache.setValue(CsSpecialCar.class,csSpecialCar.getCsscId(), csSpecialCar.getKeyValue());
		}
		Trigger.on(CsSpecialCar.class,Trigger.UPDATE,csSpecialCar);
	}

	/**
	 * 根据ID删除一个专车订单
	 * @param id
	 */
	public void deleteCsSpecialCarById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsSpecialCar.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsSpecialCarById", params);
		MemCache.deleteValue(CsSpecialCar.class, id);
	}
	/**
	 * 根据ID逻辑删除专车订单 
	 * @param id
	 * @return
	 */
	public void removeCsSpecialCarById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsSpecialCar.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsSpecialCarById", params);
		MemCache.deleteValue(CsSpecialCar.class, id);
	}
	/**
	 * 根据条件更新专车订单
	 * @param values
	 * @param params
	 */
	public void updateCsSpecialCarByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsSpecialCarCount(params)>255){
			MemCache.clearValue(CsSpecialCar.class);
		}else{
			params.put("eval","cssc_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsSpecialCarEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsSpecialCar.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsSpecialCarBy", allParams);
		Trigger.on(CsSpecialCar.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除专车订单
	 * @param params
	 */
	public void deleteCsSpecialCarByConfirm(Map params){
		Trigger.on(CsSpecialCar.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsSpecialCarCount(params)>255){
			MemCache.clearValue(CsSpecialCar.class);
		}else{
			params.put("eval","cssc_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsSpecialCarEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsSpecialCar.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsSpecialCarBy", params);
	}
	

}