package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsAreaDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsArea;
import com.lazy3q.web.helper.$;

/**
 * 网点区域的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsAreaDao extends SqlMapClientDaoSupport implements ICsAreaDao
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
	private CsArea custom(CsArea params){
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
	 * 获取所有网点区域
	 * @return
	 */	
	public List<CsArea> getCsAreaList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsArea> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(CsArea.M.class, md5);
			List caches = MemCache.getObject(CsArea.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						CsArea cacheObject = MemCache.getObject(CsArea.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(CsArea.M.class, md5);
							MemCache.deleteValue(CsArea.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(CsArea.M.class, md5);
					MemCache.deleteValue(CsArea.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsAreaList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(CsArea csArea:list){
				caches.add(csArea.getCsaId());
				MemCache.setObject(CsArea.class,csArea.getCsaId(), csArea);
			}
			MemCache.setObject(CsArea.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(CsArea.M.class, md5, json, MemCache.defaultSeconds());
		}		
		
		return list;
	}
	
	
	/**
	 * 获取网点区域统计
	 * @return
	 */
	public List getCsAreaStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsAreaStats", params);
	}

	/**
	 * 获取网点区域分页
	 * @return
	 */
	public Page getCsAreaPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsAreaCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsAreaPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取网点区域数量
	 * @return
	 */	
	public Long getCsAreaCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsAreaEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取网点区域自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsAreaEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsAreaEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取网点区域
	 * @param params
	 * @return
	 */
	public CsArea getCsArea(Map params)
	{		
		params = custom(params);
			
		List<CsArea> list=this.getCsAreaList(params,1);
		CsArea csArea = list.isEmpty()?null:list.get(0);
		
		return csArea;
	}
		
	/**
	 * 根据ID取网点区域
	 * @param id
	 * @return
	 */
	public CsArea getCsAreaById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsArea csArea = MemCache.getObject(CsArea.class, id);
		if(csArea!=null)
			return csArea;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csArea = (CsArea) this.getSqlMapClientTemplate().queryForObject("getCsAreaById",params);
		if(csArea!=null)
			MemCache.setValue(CsArea.class,id, csArea.getKeyValue());
		if(csArea!=null)
			MemCache.setObject(CsArea.class,id, csArea);
		return csArea;
	}
	
	/**
	 * 根据ID取网点区域(不缓存)
	 * @param id
	 * @return
	 */
	public CsArea GetCsAreaById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsArea) this.getSqlMapClientTemplate().queryForObject("getCsAreaById",params);
	}
	

	/**
	 * 保存网点区域
	 * @param csArea
	 */
	public CsArea saveCsArea(CsArea csArea)
	{
		csArea = custom(csArea);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsArea", csArea));
		csArea = this.GetCsAreaById(id);
		MemCache.setValue(CsArea.class,id, csArea.getKeyValue());
		MemCache.setObject(CsArea.class,id,csArea);
		MemCache.clearObject(CsArea.M.class);
		MemCache.clearValue(CsArea.M.class);
		return Trigger.on(CsArea.class,Trigger.SAVE,csArea);	
	}

	

	/**
	 * 更新网点区域
	 * @param csArea
	 */
	public void updateCsArea(CsArea csArea)
	{
		csArea = custom(csArea);
		this.getSqlMapClientTemplate().update("updateCsArea", csArea);
		csArea = this.GetCsAreaById(csArea.getCsaId());
		if(csArea!=null){
			MemCache.setValue(CsArea.class,csArea.getCsaId(), csArea.getKeyValue());
			MemCache.setObject(CsArea.class,csArea.getCsaId(),csArea);
		}
		MemCache.clearObject(CsArea.M.class);
		MemCache.clearValue(CsArea.M.class);
		Trigger.on(CsArea.class,Trigger.UPDATE,csArea);
	}
	/**
	 * 更新网点区域非空字段
	 * @param csArea
	 */
	public void updateCsArea$NotNull(CsArea csArea)
	{
		csArea = custom(csArea);
		this.getSqlMapClientTemplate().update("updateCsArea$NotNull", csArea);
		csArea = this.GetCsAreaById(csArea.getCsaId());
		if(csArea!=null){
			MemCache.setValue(CsArea.class,csArea.getCsaId(), csArea.getKeyValue());
			MemCache.setObject(CsArea.class,csArea.getCsaId(),csArea);
		}
		MemCache.clearObject(CsArea.M.class);
		MemCache.clearValue(CsArea.M.class);
		Trigger.on(CsArea.class,Trigger.UPDATE,csArea);
	}

	/**
	 * 根据ID删除一个网点区域
	 * @param id
	 */
	public void deleteCsAreaById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsArea.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsAreaById", params);
		MemCache.deleteValue(CsArea.class, id);
		MemCache.deleteObject(CsArea.class, id);
		MemCache.clearObject(CsArea.M.class);
		MemCache.clearValue(CsArea.M.class);
	}
	/**
	 * 根据ID逻辑删除网点区域 
	 * @param id
	 * @return
	 */
	public void removeCsAreaById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsArea.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsAreaById", params);
		MemCache.deleteValue(CsArea.class, id);
		MemCache.deleteObject(CsArea.class, id);
		MemCache.clearObject(CsArea.M.class);
		MemCache.clearValue(CsArea.M.class);
	}
	/**
	 * 根据条件更新网点区域
	 * @param values
	 * @param params
	 */
	public void updateCsAreaByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsAreaCount(params)>255){
			MemCache.clearValue(CsArea.class);
			MemCache.clearObject(CsArea.class);
		}else{
			params.put("eval","csa_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsAreaEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsArea.class, map.get("eval"));
				MemCache.deleteObject(CsArea.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsArea.M.class);
		MemCache.clearValue(CsArea.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateCsAreaBy", allParams);
		Trigger.on(CsArea.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除网点区域
	 * @param params
	 */
	public void deleteCsAreaByConfirm(Map params){
		Trigger.on(CsArea.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsAreaCount(params)>255){
			MemCache.clearValue(CsArea.class);
			MemCache.clearObject(CsArea.class);
		}else{
			params.put("eval","csa_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsAreaEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsArea.class, map.get("eval"));
				MemCache.deleteObject(CsArea.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsArea.M.class);
		MemCache.clearValue(CsArea.M.class);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsAreaBy", params);
	}
	

}