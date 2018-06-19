package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsChjSignDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsChjSign;
import com.lazy3q.web.helper.$;

/**
 * 春节套餐报名的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsChjSignDao extends SqlMapClientDaoSupport implements ICsChjSignDao
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
	private CsChjSign custom(CsChjSign params){
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
			"cscsRemark"			
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
	 * 获取所有春节套餐报名
	 * @return
	 */	
	public List<CsChjSign> getCsChjSignList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsChjSign> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsChjSignList", params);
		
		return list;
	}
	
	
	/**
	 * 获取春节套餐报名统计
	 * @return
	 */
	public List getCsChjSignStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsChjSignStats", params);
	}

	/**
	 * 获取春节套餐报名分页
	 * @return
	 */
	public Page getCsChjSignPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsChjSignCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsChjSignPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取春节套餐报名数量
	 * @return
	 */	
	public Long getCsChjSignCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsChjSignEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取春节套餐报名自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsChjSignEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsChjSignEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取春节套餐报名
	 * @param params
	 * @return
	 */
	public CsChjSign getCsChjSign(Map params)
	{		
		params = custom(params);
			
		List<CsChjSign> list=this.getCsChjSignList(params,1);
		CsChjSign csChjSign = list.isEmpty()?null:list.get(0);
		
		return csChjSign;
	}
		
	/**
	 * 根据ID取春节套餐报名
	 * @param id
	 * @return
	 */
	public CsChjSign getCsChjSignById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsChjSign csChjSign = null;
		if(csChjSign!=null)
			return csChjSign;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csChjSign = (CsChjSign) this.getSqlMapClientTemplate().queryForObject("getCsChjSignById",params);
		if(csChjSign!=null)
			MemCache.setValue(CsChjSign.class,id, csChjSign.getKeyValue());
		return csChjSign;
	}
	
	/**
	 * 根据ID取春节套餐报名(不缓存)
	 * @param id
	 * @return
	 */
	public CsChjSign GetCsChjSignById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsChjSign) this.getSqlMapClientTemplate().queryForObject("getCsChjSignById",params);
	}
	

	/**
	 * 保存春节套餐报名
	 * @param csChjSign
	 */
	public CsChjSign saveCsChjSign(CsChjSign csChjSign)
	{
		csChjSign = custom(csChjSign);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsChjSign", csChjSign));
		csChjSign = this.GetCsChjSignById(id);
		MemCache.setValue(CsChjSign.class,id, csChjSign.getKeyValue());
		return Trigger.on(CsChjSign.class,Trigger.SAVE,csChjSign);	
	}

	

	/**
	 * 更新春节套餐报名
	 * @param csChjSign
	 */
	public void updateCsChjSign(CsChjSign csChjSign)
	{
		csChjSign = custom(csChjSign);
		this.getSqlMapClientTemplate().update("updateCsChjSign", csChjSign);
		csChjSign = this.GetCsChjSignById(csChjSign.getCscsId());
		if(csChjSign!=null){
			MemCache.setValue(CsChjSign.class,csChjSign.getCscsId(), csChjSign.getKeyValue());
		}
		Trigger.on(CsChjSign.class,Trigger.UPDATE,csChjSign);
	}
	/**
	 * 更新春节套餐报名非空字段
	 * @param csChjSign
	 */
	public void updateCsChjSign$NotNull(CsChjSign csChjSign)
	{
		csChjSign = custom(csChjSign);
		this.getSqlMapClientTemplate().update("updateCsChjSign$NotNull", csChjSign);
		csChjSign = this.GetCsChjSignById(csChjSign.getCscsId());
		if(csChjSign!=null){
			MemCache.setValue(CsChjSign.class,csChjSign.getCscsId(), csChjSign.getKeyValue());
		}
		Trigger.on(CsChjSign.class,Trigger.UPDATE,csChjSign);
	}

	/**
	 * 根据ID删除一个春节套餐报名
	 * @param id
	 */
	public void deleteCsChjSignById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsChjSign.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsChjSignById", params);
		MemCache.deleteValue(CsChjSign.class, id);
	}
	/**
	 * 根据ID逻辑删除春节套餐报名 
	 * @param id
	 * @return
	 */
	public void removeCsChjSignById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsChjSign.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsChjSignById", params);
		MemCache.deleteValue(CsChjSign.class, id);
	}
	/**
	 * 根据条件更新春节套餐报名
	 * @param values
	 * @param params
	 */
	public void updateCsChjSignByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsChjSignCount(params)>255){
			MemCache.clearValue(CsChjSign.class);
		}else{
			params.put("eval","cscs_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsChjSignEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsChjSign.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsChjSignBy", allParams);
		Trigger.on(CsChjSign.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除春节套餐报名
	 * @param params
	 */
	public void deleteCsChjSignByConfirm(Map params){
		Trigger.on(CsChjSign.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsChjSignCount(params)>255){
			MemCache.clearValue(CsChjSign.class);
		}else{
			params.put("eval","cscs_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsChjSignEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsChjSign.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsChjSignBy", params);
	}
	

}