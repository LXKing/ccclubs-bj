package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsProductDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsProduct;
import com.lazy3q.web.helper.$;

/**
 * 产品的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsProductDao extends SqlMapClientDaoSupport implements ICsProductDao
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
	private CsProduct custom(CsProduct params){
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
			"cspProfile"			
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
	 * 获取所有产品
	 * @return
	 */	
	public List<CsProduct> getCsProductList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsProduct> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject","cspUpdateTime","cspUpdateTimeStart","cspUpdateTimeEnd","cspAddTime","cspAddTimeStart","cspAddTimeEnd"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(CsProduct.M.class, md5);
			List caches = MemCache.getObject(CsProduct.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						CsProduct cacheObject = MemCache.getObject(CsProduct.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(CsProduct.M.class, md5);
							MemCache.deleteValue(CsProduct.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(CsProduct.M.class, md5);
					MemCache.deleteValue(CsProduct.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsProductList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(CsProduct csProduct:list){
				caches.add(csProduct.getCspId());
				MemCache.setObject(CsProduct.class,csProduct.getCspId(), csProduct);
			}
			MemCache.setObject(CsProduct.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(CsProduct.M.class, md5, json, MemCache.defaultSeconds());
		}		
		
		return list;
	}
	
	
	/**
	 * 获取产品统计
	 * @return
	 */
	public List getCsProductStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsProductStats", params);
	}

	/**
	 * 获取产品分页
	 * @return
	 */
	public Page getCsProductPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsProductCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsProductPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取产品数量
	 * @return
	 */	
	public Long getCsProductCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsProductEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取产品自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsProductEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsProductEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取产品
	 * @param params
	 * @return
	 */
	public CsProduct getCsProduct(Map params)
	{		
		params = custom(params);
			
		List<CsProduct> list=this.getCsProductList(params,1);
		CsProduct csProduct = list.isEmpty()?null:list.get(0);
		
		return csProduct;
	}
		
	/**
	 * 根据ID取产品
	 * @param id
	 * @return
	 */
	public CsProduct getCsProductById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsProduct csProduct = MemCache.getObject(CsProduct.class, id);
		if(csProduct!=null)
			return csProduct;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csProduct = (CsProduct) this.getSqlMapClientTemplate().queryForObject("getCsProductById",params);
		if(csProduct!=null)
			MemCache.setValue(CsProduct.class,id, csProduct.getKeyValue());
		if(csProduct!=null)
			MemCache.setObject(CsProduct.class,id, csProduct);
		return csProduct;
	}
	
	/**
	 * 根据ID取产品(不缓存)
	 * @param id
	 * @return
	 */
	public CsProduct GetCsProductById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsProduct) this.getSqlMapClientTemplate().queryForObject("getCsProductById",params);
	}
	

	/**
	 * 保存产品
	 * @param csProduct
	 */
	public CsProduct saveCsProduct(CsProduct csProduct)
	{
		csProduct = custom(csProduct);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsProduct", csProduct));
		csProduct = this.GetCsProductById(id);
		MemCache.setValue(CsProduct.class,id, csProduct.getKeyValue());
		MemCache.setObject(CsProduct.class,id,csProduct);
		MemCache.clearObject(CsProduct.M.class);
		MemCache.clearValue(CsProduct.M.class);
		return Trigger.on(CsProduct.class,Trigger.SAVE,csProduct);	
	}

	

	/**
	 * 更新产品
	 * @param csProduct
	 */
	public void updateCsProduct(CsProduct csProduct)
	{
		csProduct = custom(csProduct);
		this.getSqlMapClientTemplate().update("updateCsProduct", csProduct);
		csProduct = this.GetCsProductById(csProduct.getCspId());
		if(csProduct!=null){
			MemCache.setValue(CsProduct.class,csProduct.getCspId(), csProduct.getKeyValue());
			MemCache.setObject(CsProduct.class,csProduct.getCspId(),csProduct);
		}
		MemCache.clearObject(CsProduct.M.class);
		MemCache.clearValue(CsProduct.M.class);
		Trigger.on(CsProduct.class,Trigger.UPDATE,csProduct);
	}
	/**
	 * 更新产品非空字段
	 * @param csProduct
	 */
	public void updateCsProduct$NotNull(CsProduct csProduct)
	{
		csProduct = custom(csProduct);
		this.getSqlMapClientTemplate().update("updateCsProduct$NotNull", csProduct);
		csProduct = this.GetCsProductById(csProduct.getCspId());
		if(csProduct!=null){
			MemCache.setValue(CsProduct.class,csProduct.getCspId(), csProduct.getKeyValue());
			MemCache.setObject(CsProduct.class,csProduct.getCspId(),csProduct);
		}
		MemCache.clearObject(CsProduct.M.class);
		MemCache.clearValue(CsProduct.M.class);
		Trigger.on(CsProduct.class,Trigger.UPDATE,csProduct);
	}

	/**
	 * 根据ID删除一个产品
	 * @param id
	 */
	public void deleteCsProductById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsProduct.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsProductById", params);
		MemCache.deleteValue(CsProduct.class, id);
		MemCache.deleteObject(CsProduct.class, id);
		MemCache.clearObject(CsProduct.M.class);
		MemCache.clearValue(CsProduct.M.class);
	}
	/**
	 * 根据ID逻辑删除产品 
	 * @param id
	 * @return
	 */
	public void removeCsProductById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsProduct.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsProductById", params);
		MemCache.deleteValue(CsProduct.class, id);
		MemCache.deleteObject(CsProduct.class, id);
		MemCache.clearObject(CsProduct.M.class);
		MemCache.clearValue(CsProduct.M.class);
	}
	/**
	 * 根据条件更新产品
	 * @param values
	 * @param params
	 */
	public void updateCsProductByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsProductCount(params)>255){
			MemCache.clearValue(CsProduct.class);
			MemCache.clearObject(CsProduct.class);
		}else{
			params.put("eval","csp_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsProductEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsProduct.class, map.get("eval"));
				MemCache.deleteObject(CsProduct.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsProduct.M.class);
		MemCache.clearValue(CsProduct.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateCsProductBy", allParams);
		Trigger.on(CsProduct.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除产品
	 * @param params
	 */
	public void deleteCsProductByConfirm(Map params){
		Trigger.on(CsProduct.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsProductCount(params)>255){
			MemCache.clearValue(CsProduct.class);
			MemCache.clearObject(CsProduct.class);
		}else{
			params.put("eval","csp_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsProductEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsProduct.class, map.get("eval"));
				MemCache.deleteObject(CsProduct.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsProduct.M.class);
		MemCache.clearValue(CsProduct.M.class);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsProductBy", params);
	}
	

}