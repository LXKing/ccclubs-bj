package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsGoodsDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsGoods;
import com.lazy3q.web.helper.$;

/**
 * 商品的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsGoodsDao extends SqlMapClientDaoSupport implements ICsGoodsDao
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
	private CsGoods custom(CsGoods params){
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
			"csgProfile"			
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
	 * 获取所有商品
	 * @return
	 */	
	public List<CsGoods> getCsGoodsList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsGoods> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject","csgUpdateTime","csgUpdateTimeStart","csgUpdateTimeEnd","csgAddTime","csgAddTimeStart","csgAddTimeEnd"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(CsGoods.M.class, md5);
			List caches = MemCache.getObject(CsGoods.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						CsGoods cacheObject = MemCache.getObject(CsGoods.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(CsGoods.M.class, md5);
							MemCache.deleteValue(CsGoods.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(CsGoods.M.class, md5);
					MemCache.deleteValue(CsGoods.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsGoodsList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(CsGoods csGoods:list){
				caches.add(csGoods.getCsgId());
				MemCache.setObject(CsGoods.class,csGoods.getCsgId(), csGoods);
			}
			MemCache.setObject(CsGoods.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(CsGoods.M.class, md5, json, MemCache.defaultSeconds());
		}		
		
		return list;
	}
	
	
	/**
	 * 获取商品统计
	 * @return
	 */
	public List getCsGoodsStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsGoodsStats", params);
	}

	/**
	 * 获取商品分页
	 * @return
	 */
	public Page getCsGoodsPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsGoodsCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsGoodsPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取商品数量
	 * @return
	 */	
	public Long getCsGoodsCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsGoodsEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取商品自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsGoodsEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsGoodsEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取商品
	 * @param params
	 * @return
	 */
	public CsGoods getCsGoods(Map params)
	{		
		params = custom(params);
			
		List<CsGoods> list=this.getCsGoodsList(params,1);
		CsGoods csGoods = list.isEmpty()?null:list.get(0);
		
		return csGoods;
	}
		
	/**
	 * 根据ID取商品
	 * @param id
	 * @return
	 */
	public CsGoods getCsGoodsById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsGoods csGoods = MemCache.getObject(CsGoods.class, id);
		if(csGoods!=null)
			return csGoods;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csGoods = (CsGoods) this.getSqlMapClientTemplate().queryForObject("getCsGoodsById",params);
		if(csGoods!=null)
			MemCache.setValue(CsGoods.class,id, csGoods.getKeyValue());
		if(csGoods!=null)
			MemCache.setObject(CsGoods.class,id, csGoods);
		return csGoods;
	}
	
	/**
	 * 根据ID取商品(不缓存)
	 * @param id
	 * @return
	 */
	public CsGoods GetCsGoodsById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsGoods) this.getSqlMapClientTemplate().queryForObject("getCsGoodsById",params);
	}
	

	/**
	 * 保存商品
	 * @param csGoods
	 */
	public CsGoods saveCsGoods(CsGoods csGoods)
	{
		csGoods = custom(csGoods);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsGoods", csGoods));
		csGoods = this.GetCsGoodsById(id);
		MemCache.setValue(CsGoods.class,id, csGoods.getKeyValue());
		MemCache.setObject(CsGoods.class,id,csGoods);
		MemCache.clearObject(CsGoods.M.class);
		MemCache.clearValue(CsGoods.M.class);
		return Trigger.on(CsGoods.class,Trigger.SAVE,csGoods);	
	}

	

	/**
	 * 更新商品
	 * @param csGoods
	 */
	public void updateCsGoods(CsGoods csGoods)
	{
		csGoods = custom(csGoods);
		this.getSqlMapClientTemplate().update("updateCsGoods", csGoods);
		csGoods = this.GetCsGoodsById(csGoods.getCsgId());
		if(csGoods!=null){
			MemCache.setValue(CsGoods.class,csGoods.getCsgId(), csGoods.getKeyValue());
			MemCache.setObject(CsGoods.class,csGoods.getCsgId(),csGoods);
		}
		MemCache.clearObject(CsGoods.M.class);
		MemCache.clearValue(CsGoods.M.class);
		Trigger.on(CsGoods.class,Trigger.UPDATE,csGoods);
	}
	/**
	 * 更新商品非空字段
	 * @param csGoods
	 */
	public void updateCsGoods$NotNull(CsGoods csGoods)
	{
		csGoods = custom(csGoods);
		this.getSqlMapClientTemplate().update("updateCsGoods$NotNull", csGoods);
		csGoods = this.GetCsGoodsById(csGoods.getCsgId());
		if(csGoods!=null){
			MemCache.setValue(CsGoods.class,csGoods.getCsgId(), csGoods.getKeyValue());
			MemCache.setObject(CsGoods.class,csGoods.getCsgId(),csGoods);
		}
		MemCache.clearObject(CsGoods.M.class);
		MemCache.clearValue(CsGoods.M.class);
		Trigger.on(CsGoods.class,Trigger.UPDATE,csGoods);
	}

	/**
	 * 根据ID删除一个商品
	 * @param id
	 */
	public void deleteCsGoodsById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsGoods.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsGoodsById", params);
		MemCache.deleteValue(CsGoods.class, id);
		MemCache.deleteObject(CsGoods.class, id);
		MemCache.clearObject(CsGoods.M.class);
		MemCache.clearValue(CsGoods.M.class);
	}
	/**
	 * 根据ID逻辑删除商品 
	 * @param id
	 * @return
	 */
	public void removeCsGoodsById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsGoods.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsGoodsById", params);
		MemCache.deleteValue(CsGoods.class, id);
		MemCache.deleteObject(CsGoods.class, id);
		MemCache.clearObject(CsGoods.M.class);
		MemCache.clearValue(CsGoods.M.class);
	}
	/**
	 * 根据条件更新商品
	 * @param values
	 * @param params
	 */
	public void updateCsGoodsByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsGoodsCount(params)>255){
			MemCache.clearValue(CsGoods.class);
			MemCache.clearObject(CsGoods.class);
		}else{
			params.put("eval","csg_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsGoodsEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsGoods.class, map.get("eval"));
				MemCache.deleteObject(CsGoods.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsGoods.M.class);
		MemCache.clearValue(CsGoods.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateCsGoodsBy", allParams);
		Trigger.on(CsGoods.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除商品
	 * @param params
	 */
	public void deleteCsGoodsByConfirm(Map params){
		Trigger.on(CsGoods.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsGoodsCount(params)>255){
			MemCache.clearValue(CsGoods.class);
			MemCache.clearObject(CsGoods.class);
		}else{
			params.put("eval","csg_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsGoodsEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsGoods.class, map.get("eval"));
				MemCache.deleteObject(CsGoods.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsGoods.M.class);
		MemCache.clearValue(CsGoods.M.class);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsGoodsBy", params);
	}
	

}