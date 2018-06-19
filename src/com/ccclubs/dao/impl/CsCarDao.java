package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsCarDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCar;
import com.lazy3q.web.helper.$;

/**
 * 车辆的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsCarDao extends SqlMapClientDaoSupport implements ICsCarDao
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
	private CsCar custom(CsCar params){
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
			"cscNumber"			
			,"cscCarNo"			
			,"cscVin"			
			,"cscEngineNo"			
			,"cscAnnualInfo"			
			,"cscRemark"			
			,"cscMemo"			
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
	 * 获取所有车辆
	 * @return
	 */	
	public List<CsCar> getCsCarList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsCar> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject","cscBuyDate","cscBuyDateStart","cscBuyDateEnd","cscCheckIn","cscCheckInStart","cscCheckInEnd","cscAnnualPre","cscAnnualPreStart","cscAnnualPreEnd","cscAnnual","cscAnnualStart","cscAnnualEnd","cscAnnualNext","cscAnnualNextStart","cscAnnualNextEnd","cscTiDate","cscTiDateStart","cscTiDateEnd","cscTiUnit","cscTiUnitStart","cscTiUnitEnd","cscBiDate","cscBiDateStart","cscBiDateEnd","cscBiUnit","cscBiUnitStart","cscBiUnitEnd","cscUpdateTime","cscUpdateTimeStart","cscUpdateTimeEnd","cscAddTime","cscAddTimeStart","cscAddTimeEnd"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(CsCar.M.class, md5);
			List caches = MemCache.getObject(CsCar.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						CsCar cacheObject = MemCache.getObject(CsCar.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(CsCar.M.class, md5);
							MemCache.deleteValue(CsCar.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(CsCar.M.class, md5);
					MemCache.deleteValue(CsCar.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsCarList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(CsCar csCar:list){
				caches.add(csCar.getCscId());
				MemCache.setObject(CsCar.class,csCar.getCscId(), csCar);
			}
			MemCache.setObject(CsCar.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(CsCar.M.class, md5, json, MemCache.defaultSeconds());
		}		
		
		return list;
	}
	
	
	/**
	 * 获取车辆统计
	 * @return
	 */
	public List getCsCarStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsCarStats", params);
	}

	/**
	 * 获取车辆分页
	 * @return
	 */
	public Page getCsCarPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsCarCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsCarPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取车辆数量
	 * @return
	 */	
	public Long getCsCarCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCarEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取车辆自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsCarEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCarEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取车辆
	 * @param params
	 * @return
	 */
	public CsCar getCsCar(Map params)
	{		
		params = custom(params);
			
		List<CsCar> list=this.getCsCarList(params,1);
		CsCar csCar = list.isEmpty()?null:list.get(0);
		
		return csCar;
	}
		
	/**
	 * 根据ID取车辆
	 * @param id
	 * @return
	 */
	public CsCar getCsCarById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsCar csCar = MemCache.getObject(CsCar.class, id);
		if(csCar!=null)
			return csCar;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csCar = (CsCar) this.getSqlMapClientTemplate().queryForObject("getCsCarById",params);
		if(csCar!=null)
			MemCache.setValue(CsCar.class,id, csCar.getKeyValue());
		if(csCar!=null)
			MemCache.setObject(CsCar.class,id, csCar);
		return csCar;
	}
	
	/**
	 * 根据ID取车辆(不缓存)
	 * @param id
	 * @return
	 */
	public CsCar GetCsCarById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsCar) this.getSqlMapClientTemplate().queryForObject("getCsCarById",params);
	}
	

	/**
	 * 保存车辆
	 * @param csCar
	 */
	public CsCar saveCsCar(CsCar csCar)
	{
		csCar = custom(csCar);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsCar", csCar));
		csCar = this.GetCsCarById(id);
		MemCache.setValue(CsCar.class,id, csCar.getKeyValue());
		MemCache.setObject(CsCar.class,id,csCar);
		MemCache.clearObject(CsCar.M.class);
		MemCache.clearValue(CsCar.M.class);
		return Trigger.on(CsCar.class,Trigger.SAVE,csCar);	
	}

	

	/**
	 * 更新车辆
	 * @param csCar
	 */
	public void updateCsCar(CsCar csCar)
	{
		csCar = custom(csCar);
		this.getSqlMapClientTemplate().update("updateCsCar", csCar);
		csCar = this.GetCsCarById(csCar.getCscId());
		if(csCar!=null){
			MemCache.setValue(CsCar.class,csCar.getCscId(), csCar.getKeyValue());
			MemCache.setObject(CsCar.class,csCar.getCscId(),csCar);
		}
		MemCache.clearObject(CsCar.M.class);
		MemCache.clearValue(CsCar.M.class);
		Trigger.on(CsCar.class,Trigger.UPDATE,csCar);
	}
	/**
	 * 更新车辆非空字段
	 * @param csCar
	 */
	public void updateCsCar$NotNull(CsCar csCar)
	{
		csCar = custom(csCar);
		this.getSqlMapClientTemplate().update("updateCsCar$NotNull", csCar);
		csCar = this.GetCsCarById(csCar.getCscId());
		if(csCar!=null){
			MemCache.setValue(CsCar.class,csCar.getCscId(), csCar.getKeyValue());
			MemCache.setObject(CsCar.class,csCar.getCscId(),csCar);
		}
		MemCache.clearObject(CsCar.M.class);
		MemCache.clearValue(CsCar.M.class);
		Trigger.on(CsCar.class,Trigger.UPDATE,csCar);
	}

	/**
	 * 根据ID删除一个车辆
	 * @param id
	 */
	public void deleteCsCarById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsCar.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsCarById", params);
		MemCache.deleteValue(CsCar.class, id);
		MemCache.deleteObject(CsCar.class, id);
		MemCache.clearObject(CsCar.M.class);
		MemCache.clearValue(CsCar.M.class);
	}
	/**
	 * 根据ID逻辑删除车辆 
	 * @param id
	 * @return
	 */
	public void removeCsCarById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsCar.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsCarById", params);
		MemCache.deleteValue(CsCar.class, id);
		MemCache.deleteObject(CsCar.class, id);
		MemCache.clearObject(CsCar.M.class);
		MemCache.clearValue(CsCar.M.class);
	}
	/**
	 * 根据条件更新车辆
	 * @param values
	 * @param params
	 */
	public void updateCsCarByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsCarCount(params)>255){
			MemCache.clearValue(CsCar.class);
			MemCache.clearObject(CsCar.class);
		}else{
			params.put("eval","csc_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCarEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCar.class, map.get("eval"));
				MemCache.deleteObject(CsCar.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsCar.M.class);
		MemCache.clearValue(CsCar.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateCsCarBy", allParams);
		Trigger.on(CsCar.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除车辆
	 * @param params
	 */
	public void deleteCsCarByConfirm(Map params){
		Trigger.on(CsCar.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsCarCount(params)>255){
			MemCache.clearValue(CsCar.class);
			MemCache.clearObject(CsCar.class);
		}else{
			params.put("eval","csc_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCarEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCar.class, map.get("eval"));
				MemCache.deleteObject(CsCar.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsCar.M.class);
		MemCache.clearValue(CsCar.M.class);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsCarBy", params);
	}
	

}