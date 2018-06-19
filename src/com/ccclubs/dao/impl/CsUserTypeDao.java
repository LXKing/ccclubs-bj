package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsUserTypeDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsUserType;
import com.lazy3q.web.helper.$;

/**
 * 计费方式的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsUserTypeDao extends SqlMapClientDaoSupport implements ICsUserTypeDao
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
	private CsUserType custom(CsUserType params){
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
			"csutProfile"			
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
	 * 获取所有计费方式
	 * @return
	 */	
	public List<CsUserType> getCsUserTypeList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsUserType> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(CsUserType.M.class, md5);
			List caches = MemCache.getObject(CsUserType.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						CsUserType cacheObject = MemCache.getObject(CsUserType.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(CsUserType.M.class, md5);
							MemCache.deleteValue(CsUserType.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(CsUserType.M.class, md5);
					MemCache.deleteValue(CsUserType.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsUserTypeList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(CsUserType csUserType:list){
				caches.add(csUserType.getCsutId());
				MemCache.setObject(CsUserType.class,csUserType.getCsutId(), csUserType);
			}
			MemCache.setObject(CsUserType.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(CsUserType.M.class, md5, json, MemCache.defaultSeconds());
		}		
		
		return list;
	}
	
	
	/**
	 * 获取计费方式统计
	 * @return
	 */
	public List getCsUserTypeStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsUserTypeStats", params);
	}

	/**
	 * 获取计费方式分页
	 * @return
	 */
	public Page getCsUserTypePage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsUserTypeCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsUserTypePage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取计费方式数量
	 * @return
	 */	
	public Long getCsUserTypeCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsUserTypeEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取计费方式自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsUserTypeEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsUserTypeEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取计费方式
	 * @param params
	 * @return
	 */
	public CsUserType getCsUserType(Map params)
	{		
		params = custom(params);
			
		List<CsUserType> list=this.getCsUserTypeList(params,1);
		CsUserType csUserType = list.isEmpty()?null:list.get(0);
		
		return csUserType;
	}
		
	/**
	 * 根据ID取计费方式
	 * @param id
	 * @return
	 */
	public CsUserType getCsUserTypeById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsUserType csUserType = MemCache.getObject(CsUserType.class, id);
		if(csUserType!=null)
			return csUserType;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csUserType = (CsUserType) this.getSqlMapClientTemplate().queryForObject("getCsUserTypeById",params);
		if(csUserType!=null)
			MemCache.setValue(CsUserType.class,id, csUserType.getKeyValue());
		if(csUserType!=null)
			MemCache.setObject(CsUserType.class,id, csUserType);
		return csUserType;
	}
	
	/**
	 * 根据ID取计费方式(不缓存)
	 * @param id
	 * @return
	 */
	public CsUserType GetCsUserTypeById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsUserType) this.getSqlMapClientTemplate().queryForObject("getCsUserTypeById",params);
	}
	

	/**
	 * 保存计费方式
	 * @param csUserType
	 */
	public CsUserType saveCsUserType(CsUserType csUserType)
	{
		csUserType = custom(csUserType);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsUserType", csUserType));
		csUserType = this.GetCsUserTypeById(id);
		MemCache.setValue(CsUserType.class,id, csUserType.getKeyValue());
		MemCache.setObject(CsUserType.class,id,csUserType);
		MemCache.clearObject(CsUserType.M.class);
		MemCache.clearValue(CsUserType.M.class);
		return Trigger.on(CsUserType.class,Trigger.SAVE,csUserType);	
	}

	

	/**
	 * 更新计费方式
	 * @param csUserType
	 */
	public void updateCsUserType(CsUserType csUserType)
	{
		csUserType = custom(csUserType);
		this.getSqlMapClientTemplate().update("updateCsUserType", csUserType);
		csUserType = this.GetCsUserTypeById(csUserType.getCsutId());
		if(csUserType!=null){
			MemCache.setValue(CsUserType.class,csUserType.getCsutId(), csUserType.getKeyValue());
			MemCache.setObject(CsUserType.class,csUserType.getCsutId(),csUserType);
		}
		MemCache.clearObject(CsUserType.M.class);
		MemCache.clearValue(CsUserType.M.class);
		Trigger.on(CsUserType.class,Trigger.UPDATE,csUserType);
	}
	/**
	 * 更新计费方式非空字段
	 * @param csUserType
	 */
	public void updateCsUserType$NotNull(CsUserType csUserType)
	{
		csUserType = custom(csUserType);
		this.getSqlMapClientTemplate().update("updateCsUserType$NotNull", csUserType);
		csUserType = this.GetCsUserTypeById(csUserType.getCsutId());
		if(csUserType!=null){
			MemCache.setValue(CsUserType.class,csUserType.getCsutId(), csUserType.getKeyValue());
			MemCache.setObject(CsUserType.class,csUserType.getCsutId(),csUserType);
		}
		MemCache.clearObject(CsUserType.M.class);
		MemCache.clearValue(CsUserType.M.class);
		Trigger.on(CsUserType.class,Trigger.UPDATE,csUserType);
	}

	/**
	 * 根据ID删除一个计费方式
	 * @param id
	 */
	public void deleteCsUserTypeById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsUserType.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsUserTypeById", params);
		MemCache.deleteValue(CsUserType.class, id);
		MemCache.deleteObject(CsUserType.class, id);
		MemCache.clearObject(CsUserType.M.class);
		MemCache.clearValue(CsUserType.M.class);
	}
	/**
	 * 根据ID逻辑删除计费方式 
	 * @param id
	 * @return
	 */
	public void removeCsUserTypeById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsUserType.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsUserTypeById", params);
		MemCache.deleteValue(CsUserType.class, id);
		MemCache.deleteObject(CsUserType.class, id);
		MemCache.clearObject(CsUserType.M.class);
		MemCache.clearValue(CsUserType.M.class);
	}
	/**
	 * 根据条件更新计费方式
	 * @param values
	 * @param params
	 */
	public void updateCsUserTypeByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsUserTypeCount(params)>255){
			MemCache.clearValue(CsUserType.class);
			MemCache.clearObject(CsUserType.class);
		}else{
			params.put("eval","csut_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsUserTypeEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsUserType.class, map.get("eval"));
				MemCache.deleteObject(CsUserType.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsUserType.M.class);
		MemCache.clearValue(CsUserType.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateCsUserTypeBy", allParams);
		Trigger.on(CsUserType.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除计费方式
	 * @param params
	 */
	public void deleteCsUserTypeByConfirm(Map params){
		Trigger.on(CsUserType.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsUserTypeCount(params)>255){
			MemCache.clearValue(CsUserType.class);
			MemCache.clearObject(CsUserType.class);
		}else{
			params.put("eval","csut_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsUserTypeEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsUserType.class, map.get("eval"));
				MemCache.deleteObject(CsUserType.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsUserType.M.class);
		MemCache.clearValue(CsUserType.M.class);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsUserTypeBy", params);
	}
	

}