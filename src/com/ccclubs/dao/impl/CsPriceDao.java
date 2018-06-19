package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsPriceDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsPrice;
import com.lazy3q.web.helper.$;

/**
 * 价格的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsPriceDao extends SqlMapClientDaoSupport implements ICsPriceDao
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
	private CsPrice custom(CsPrice params){
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
	 * 获取所有价格
	 * @return
	 */	
	public List<CsPrice> getCsPriceList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsPrice> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(CsPrice.M.class, md5);
			List caches = MemCache.getObject(CsPrice.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						CsPrice cacheObject = MemCache.getObject(CsPrice.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(CsPrice.M.class, md5);
							MemCache.deleteValue(CsPrice.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(CsPrice.M.class, md5);
					MemCache.deleteValue(CsPrice.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsPriceList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(CsPrice csPrice:list){
				caches.add(csPrice.getCspId());
				MemCache.setObject(CsPrice.class,csPrice.getCspId(), csPrice);
			}
			MemCache.setObject(CsPrice.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(CsPrice.M.class, md5, json, MemCache.defaultSeconds());
		}		
		
		return list;
	}
	
	
	/**
	 * 获取价格统计
	 * @return
	 */
	public List getCsPriceStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsPriceStats", params);
	}

	/**
	 * 获取价格分页
	 * @return
	 */
	public Page getCsPricePage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsPriceCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsPricePage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取价格数量
	 * @return
	 */	
	public Long getCsPriceCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsPriceEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取价格自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsPriceEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsPriceEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取价格
	 * @param params
	 * @return
	 */
	public CsPrice getCsPrice(Map params)
	{		
		params = custom(params);
			
		List<CsPrice> list=this.getCsPriceList(params,1);
		CsPrice csPrice = list.isEmpty()?null:list.get(0);
		
		return csPrice;
	}
		
	/**
	 * 根据ID取价格
	 * @param id
	 * @return
	 */
	public CsPrice getCsPriceById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsPrice csPrice = MemCache.getObject(CsPrice.class, id);
		if(csPrice!=null)
			return csPrice;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csPrice = (CsPrice) this.getSqlMapClientTemplate().queryForObject("getCsPriceById",params);
		if(csPrice!=null)
			MemCache.setValue(CsPrice.class,id, csPrice.getKeyValue());
		if(csPrice!=null)
			MemCache.setObject(CsPrice.class,id, csPrice);
		return csPrice;
	}
	
	/**
	 * 根据ID取价格(不缓存)
	 * @param id
	 * @return
	 */
	public CsPrice GetCsPriceById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsPrice) this.getSqlMapClientTemplate().queryForObject("getCsPriceById",params);
	}
	

	/**
	 * 保存价格
	 * @param csPrice
	 */
	public CsPrice saveCsPrice(CsPrice csPrice)
	{
		csPrice = custom(csPrice);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsPrice", csPrice));
		csPrice = this.GetCsPriceById(id);
		MemCache.setValue(CsPrice.class,id, csPrice.getKeyValue());
		MemCache.setObject(CsPrice.class,id,csPrice);
		MemCache.clearObject(CsPrice.M.class);
		MemCache.clearValue(CsPrice.M.class);
		return Trigger.on(CsPrice.class,Trigger.SAVE,csPrice);	
	}

	

	/**
	 * 更新价格
	 * @param csPrice
	 */
	public void updateCsPrice(CsPrice csPrice)
	{
		csPrice = custom(csPrice);
		this.getSqlMapClientTemplate().update("updateCsPrice", csPrice);
		csPrice = this.GetCsPriceById(csPrice.getCspId());
		if(csPrice!=null){
			MemCache.setValue(CsPrice.class,csPrice.getCspId(), csPrice.getKeyValue());
			MemCache.setObject(CsPrice.class,csPrice.getCspId(),csPrice);
		}
		MemCache.clearObject(CsPrice.M.class);
		MemCache.clearValue(CsPrice.M.class);
		Trigger.on(CsPrice.class,Trigger.UPDATE,csPrice);
	}
	/**
	 * 更新价格非空字段
	 * @param csPrice
	 */
	public void updateCsPrice$NotNull(CsPrice csPrice)
	{
		csPrice = custom(csPrice);
		this.getSqlMapClientTemplate().update("updateCsPrice$NotNull", csPrice);
		csPrice = this.GetCsPriceById(csPrice.getCspId());
		if(csPrice!=null){
			MemCache.setValue(CsPrice.class,csPrice.getCspId(), csPrice.getKeyValue());
			MemCache.setObject(CsPrice.class,csPrice.getCspId(),csPrice);
		}
		MemCache.clearObject(CsPrice.M.class);
		MemCache.clearValue(CsPrice.M.class);
		Trigger.on(CsPrice.class,Trigger.UPDATE,csPrice);
	}

	/**
	 * 根据ID删除一个价格
	 * @param id
	 */
	public void deleteCsPriceById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsPrice.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsPriceById", params);
		MemCache.deleteValue(CsPrice.class, id);
		MemCache.deleteObject(CsPrice.class, id);
		MemCache.clearObject(CsPrice.M.class);
		MemCache.clearValue(CsPrice.M.class);
	}
	/**
	 * 根据条件更新价格
	 * @param values
	 * @param params
	 */
	public void updateCsPriceByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsPriceCount(params)>255){
			MemCache.clearValue(CsPrice.class);
			MemCache.clearObject(CsPrice.class);
		}else{
			params.put("eval","csp_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsPriceEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsPrice.class, map.get("eval"));
				MemCache.deleteObject(CsPrice.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsPrice.M.class);
		MemCache.clearValue(CsPrice.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateCsPriceBy", allParams);
		Trigger.on(CsPrice.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除价格
	 * @param params
	 */
	public void deleteCsPriceByConfirm(Map params){
		Trigger.on(CsPrice.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsPriceCount(params)>255){
			MemCache.clearValue(CsPrice.class);
			MemCache.clearObject(CsPrice.class);
		}else{
			params.put("eval","csp_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsPriceEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsPrice.class, map.get("eval"));
				MemCache.deleteObject(CsPrice.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsPrice.M.class);
		MemCache.clearValue(CsPrice.M.class);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsPriceBy", params);
	}
	

}