package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsLongOrderFeeDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsLongOrderFee;
import com.lazy3q.web.helper.$;

/**
 * 长单缴费的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsLongOrderFeeDao extends SqlMapClientDaoSupport implements ICsLongOrderFeeDao
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
	private CsLongOrderFee custom(CsLongOrderFee params){
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
			"cslofRemark"			
			,"cslofStateInfo"			
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
	 * 获取所有长单缴费
	 * @return
	 */	
	public List<CsLongOrderFee> getCsLongOrderFeeList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsLongOrderFee> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsLongOrderFeeList", params);
		
		return list;
	}
	
	
	/**
	 * 获取长单缴费统计
	 * @return
	 */
	public List getCsLongOrderFeeStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsLongOrderFeeStats", params);
	}

	/**
	 * 获取长单缴费分页
	 * @return
	 */
	public Page getCsLongOrderFeePage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsLongOrderFeeCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsLongOrderFeePage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取长单缴费数量
	 * @return
	 */	
	public Long getCsLongOrderFeeCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsLongOrderFeeEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取长单缴费自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsLongOrderFeeEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsLongOrderFeeEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取长单缴费
	 * @param params
	 * @return
	 */
	public CsLongOrderFee getCsLongOrderFee(Map params)
	{		
		params = custom(params);
			
		List<CsLongOrderFee> list=this.getCsLongOrderFeeList(params,1);
		CsLongOrderFee csLongOrderFee = list.isEmpty()?null:list.get(0);
		
		return csLongOrderFee;
	}
		
	/**
	 * 根据ID取长单缴费
	 * @param id
	 * @return
	 */
	public CsLongOrderFee getCsLongOrderFeeById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsLongOrderFee csLongOrderFee = null;
		if(csLongOrderFee!=null)
			return csLongOrderFee;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csLongOrderFee = (CsLongOrderFee) this.getSqlMapClientTemplate().queryForObject("getCsLongOrderFeeById",params);
		if(csLongOrderFee!=null)
			MemCache.setValue(CsLongOrderFee.class,id, csLongOrderFee.getKeyValue());
		return csLongOrderFee;
	}
	
	/**
	 * 根据ID取长单缴费(不缓存)
	 * @param id
	 * @return
	 */
	public CsLongOrderFee GetCsLongOrderFeeById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsLongOrderFee) this.getSqlMapClientTemplate().queryForObject("getCsLongOrderFeeById",params);
	}
	

	/**
	 * 保存长单缴费
	 * @param csLongOrderFee
	 */
	public CsLongOrderFee saveCsLongOrderFee(CsLongOrderFee csLongOrderFee)
	{
		csLongOrderFee = custom(csLongOrderFee);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsLongOrderFee", csLongOrderFee));
		csLongOrderFee = this.GetCsLongOrderFeeById(id);
		MemCache.setValue(CsLongOrderFee.class,id, csLongOrderFee.getKeyValue());
		return Trigger.on(CsLongOrderFee.class,Trigger.SAVE,csLongOrderFee);	
	}

	

	/**
	 * 更新长单缴费
	 * @param csLongOrderFee
	 */
	public void updateCsLongOrderFee(CsLongOrderFee csLongOrderFee)
	{
		csLongOrderFee = custom(csLongOrderFee);
		this.getSqlMapClientTemplate().update("updateCsLongOrderFee", csLongOrderFee);
		csLongOrderFee = this.GetCsLongOrderFeeById(csLongOrderFee.getCslofId());
		if(csLongOrderFee!=null){
			MemCache.setValue(CsLongOrderFee.class,csLongOrderFee.getCslofId(), csLongOrderFee.getKeyValue());
		}
		Trigger.on(CsLongOrderFee.class,Trigger.UPDATE,csLongOrderFee);
	}
	/**
	 * 更新长单缴费非空字段
	 * @param csLongOrderFee
	 */
	public void updateCsLongOrderFee$NotNull(CsLongOrderFee csLongOrderFee)
	{
		csLongOrderFee = custom(csLongOrderFee);
		this.getSqlMapClientTemplate().update("updateCsLongOrderFee$NotNull", csLongOrderFee);
		csLongOrderFee = this.GetCsLongOrderFeeById(csLongOrderFee.getCslofId());
		if(csLongOrderFee!=null){
			MemCache.setValue(CsLongOrderFee.class,csLongOrderFee.getCslofId(), csLongOrderFee.getKeyValue());
		}
		Trigger.on(CsLongOrderFee.class,Trigger.UPDATE,csLongOrderFee);
	}

	/**
	 * 根据ID删除一个长单缴费
	 * @param id
	 */
	public void deleteCsLongOrderFeeById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsLongOrderFee.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsLongOrderFeeById", params);
		MemCache.deleteValue(CsLongOrderFee.class, id);
	}
	/**
	 * 根据ID逻辑删除长单缴费 
	 * @param id
	 * @return
	 */
	public void removeCsLongOrderFeeById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsLongOrderFee.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsLongOrderFeeById", params);
		MemCache.deleteValue(CsLongOrderFee.class, id);
	}
	/**
	 * 根据条件更新长单缴费
	 * @param values
	 * @param params
	 */
	public void updateCsLongOrderFeeByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsLongOrderFeeCount(params)>255){
			MemCache.clearValue(CsLongOrderFee.class);
		}else{
			params.put("eval","cslof_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsLongOrderFeeEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsLongOrderFee.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsLongOrderFeeBy", allParams);
		Trigger.on(CsLongOrderFee.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除长单缴费
	 * @param params
	 */
	public void deleteCsLongOrderFeeByConfirm(Map params){
		Trigger.on(CsLongOrderFee.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsLongOrderFeeCount(params)>255){
			MemCache.clearValue(CsLongOrderFee.class);
		}else{
			params.put("eval","cslof_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsLongOrderFeeEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsLongOrderFee.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsLongOrderFeeBy", params);
	}
	

}