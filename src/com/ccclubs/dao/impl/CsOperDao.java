package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsOperDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsOper;
import com.lazy3q.web.helper.$;

/**
 * 运营商的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsOperDao extends SqlMapClientDaoSupport implements ICsOperDao
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
	private CsOper custom(CsOper params){
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
			"csoFullName"			
			,"csoAddress"			
			,"csoContacts"			
			,"csoRemark"			
			,"csoMemo"			
			,"csoData"			
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
	 * 获取所有运营商
	 * @return
	 */	
	public List<CsOper> getCsOperList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsOper> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject","csoUpdateTime","csoUpdateTimeStart","csoUpdateTimeEnd","csoAddTime","csoAddTimeStart","csoAddTimeEnd"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(CsOper.M.class, md5);
			List caches = MemCache.getObject(CsOper.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						CsOper cacheObject = MemCache.getObject(CsOper.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(CsOper.M.class, md5);
							MemCache.deleteValue(CsOper.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(CsOper.M.class, md5);
					MemCache.deleteValue(CsOper.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsOperList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(CsOper csOper:list){
				caches.add(csOper.getCsoId());
				MemCache.setObject(CsOper.class,csOper.getCsoId(), csOper);
			}
			MemCache.setObject(CsOper.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(CsOper.M.class, md5, json, MemCache.defaultSeconds());
		}		
		
		return list;
	}
	
	
	/**
	 * 获取运营商统计
	 * @return
	 */
	public List getCsOperStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsOperStats", params);
	}

	/**
	 * 获取运营商分页
	 * @return
	 */
	public Page getCsOperPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsOperCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsOperPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取运营商数量
	 * @return
	 */	
	public Long getCsOperCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsOperEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取运营商自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsOperEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsOperEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取运营商
	 * @param params
	 * @return
	 */
	public CsOper getCsOper(Map params)
	{		
		params = custom(params);
			
		List<CsOper> list=this.getCsOperList(params,1);
		CsOper csOper = list.isEmpty()?null:list.get(0);
		
		return csOper;
	}
		
	/**
	 * 根据ID取运营商
	 * @param id
	 * @return
	 */
	public CsOper getCsOperById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsOper csOper = MemCache.getObject(CsOper.class, id);
		if(csOper!=null)
			return csOper;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csOper = (CsOper) this.getSqlMapClientTemplate().queryForObject("getCsOperById",params);
		if(csOper!=null)
			MemCache.setValue(CsOper.class,id, csOper.getKeyValue());
		if(csOper!=null)
			MemCache.setObject(CsOper.class,id, csOper);
		return csOper;
	}
	
	/**
	 * 根据ID取运营商(不缓存)
	 * @param id
	 * @return
	 */
	public CsOper GetCsOperById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsOper) this.getSqlMapClientTemplate().queryForObject("getCsOperById",params);
	}
	

	/**
	 * 保存运营商
	 * @param csOper
	 */
	public CsOper saveCsOper(CsOper csOper)
	{
		csOper = custom(csOper);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsOper", csOper));
		csOper = this.GetCsOperById(id);
		MemCache.setValue(CsOper.class,id, csOper.getKeyValue());
		MemCache.setObject(CsOper.class,id,csOper);
		MemCache.clearObject(CsOper.M.class);
		MemCache.clearValue(CsOper.M.class);
		return Trigger.on(CsOper.class,Trigger.SAVE,csOper);	
	}

	

	/**
	 * 更新运营商
	 * @param csOper
	 */
	public void updateCsOper(CsOper csOper)
	{
		csOper = custom(csOper);
		this.getSqlMapClientTemplate().update("updateCsOper", csOper);
		csOper = this.GetCsOperById(csOper.getCsoId());
		if(csOper!=null){
			MemCache.setValue(CsOper.class,csOper.getCsoId(), csOper.getKeyValue());
			MemCache.setObject(CsOper.class,csOper.getCsoId(),csOper);
		}
		MemCache.clearObject(CsOper.M.class);
		MemCache.clearValue(CsOper.M.class);
		Trigger.on(CsOper.class,Trigger.UPDATE,csOper);
	}
	/**
	 * 更新运营商非空字段
	 * @param csOper
	 */
	public void updateCsOper$NotNull(CsOper csOper)
	{
		csOper = custom(csOper);
		this.getSqlMapClientTemplate().update("updateCsOper$NotNull", csOper);
		csOper = this.GetCsOperById(csOper.getCsoId());
		if(csOper!=null){
			MemCache.setValue(CsOper.class,csOper.getCsoId(), csOper.getKeyValue());
			MemCache.setObject(CsOper.class,csOper.getCsoId(),csOper);
		}
		MemCache.clearObject(CsOper.M.class);
		MemCache.clearValue(CsOper.M.class);
		Trigger.on(CsOper.class,Trigger.UPDATE,csOper);
	}

	/**
	 * 根据ID删除一个运营商
	 * @param id
	 */
	public void deleteCsOperById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsOper.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsOperById", params);
		MemCache.deleteValue(CsOper.class, id);
		MemCache.deleteObject(CsOper.class, id);
		MemCache.clearObject(CsOper.M.class);
		MemCache.clearValue(CsOper.M.class);
	}
	/**
	 * 根据ID逻辑删除运营商 
	 * @param id
	 * @return
	 */
	public void removeCsOperById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsOper.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsOperById", params);
		MemCache.deleteValue(CsOper.class, id);
		MemCache.deleteObject(CsOper.class, id);
		MemCache.clearObject(CsOper.M.class);
		MemCache.clearValue(CsOper.M.class);
	}
	/**
	 * 根据条件更新运营商
	 * @param values
	 * @param params
	 */
	public void updateCsOperByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsOperCount(params)>255){
			MemCache.clearValue(CsOper.class);
			MemCache.clearObject(CsOper.class);
		}else{
			params.put("eval","cso_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsOperEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsOper.class, map.get("eval"));
				MemCache.deleteObject(CsOper.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsOper.M.class);
		MemCache.clearValue(CsOper.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateCsOperBy", allParams);
		Trigger.on(CsOper.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除运营商
	 * @param params
	 */
	public void deleteCsOperByConfirm(Map params){
		Trigger.on(CsOper.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsOperCount(params)>255){
			MemCache.clearValue(CsOper.class);
			MemCache.clearObject(CsOper.class);
		}else{
			params.put("eval","cso_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsOperEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsOper.class, map.get("eval"));
				MemCache.deleteObject(CsOper.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsOper.M.class);
		MemCache.clearValue(CsOper.M.class);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsOperBy", params);
	}
	

}