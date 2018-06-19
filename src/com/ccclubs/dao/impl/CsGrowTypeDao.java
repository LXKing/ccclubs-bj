package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsGrowTypeDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsGrowType;
import com.lazy3q.web.helper.$;

/**
 * 成长分类型的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsGrowTypeDao extends SqlMapClientDaoSupport implements ICsGrowTypeDao
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
	private CsGrowType custom(CsGrowType params){
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
			"csgtProfile"			
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
	 * 获取所有成长分类型
	 * @return
	 */	
	public List<CsGrowType> getCsGrowTypeList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsGrowType> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(CsGrowType.M.class, md5);
			List caches = MemCache.getObject(CsGrowType.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						CsGrowType cacheObject = MemCache.getObject(CsGrowType.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(CsGrowType.M.class, md5);
							MemCache.deleteValue(CsGrowType.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(CsGrowType.M.class, md5);
					MemCache.deleteValue(CsGrowType.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsGrowTypeList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(CsGrowType csGrowType:list){
				caches.add(csGrowType.getCsgtId());
				MemCache.setObject(CsGrowType.class,csGrowType.getCsgtId(), csGrowType);
			}
			MemCache.setObject(CsGrowType.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(CsGrowType.M.class, md5, json, MemCache.defaultSeconds());
		}		
		
		return list;
	}
	
	
	/**
	 * 获取成长分类型统计
	 * @return
	 */
	public List getCsGrowTypeStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsGrowTypeStats", params);
	}

	/**
	 * 获取成长分类型分页
	 * @return
	 */
	public Page getCsGrowTypePage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsGrowTypeCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsGrowTypePage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取成长分类型数量
	 * @return
	 */	
	public Long getCsGrowTypeCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsGrowTypeEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取成长分类型自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsGrowTypeEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsGrowTypeEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取成长分类型
	 * @param params
	 * @return
	 */
	public CsGrowType getCsGrowType(Map params)
	{		
		params = custom(params);
			
		List<CsGrowType> list=this.getCsGrowTypeList(params,1);
		CsGrowType csGrowType = list.isEmpty()?null:list.get(0);
		
		return csGrowType;
	}
		
	/**
	 * 根据ID取成长分类型
	 * @param id
	 * @return
	 */
	public CsGrowType getCsGrowTypeById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsGrowType csGrowType = MemCache.getObject(CsGrowType.class, id);
		if(csGrowType!=null)
			return csGrowType;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csGrowType = (CsGrowType) this.getSqlMapClientTemplate().queryForObject("getCsGrowTypeById",params);
		if(csGrowType!=null)
			MemCache.setValue(CsGrowType.class,id, csGrowType.getKeyValue());
		if(csGrowType!=null)
			MemCache.setObject(CsGrowType.class,id, csGrowType);
		return csGrowType;
	}
	
	/**
	 * 根据ID取成长分类型(不缓存)
	 * @param id
	 * @return
	 */
	public CsGrowType GetCsGrowTypeById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsGrowType) this.getSqlMapClientTemplate().queryForObject("getCsGrowTypeById",params);
	}
	

	/**
	 * 保存成长分类型
	 * @param csGrowType
	 */
	public CsGrowType saveCsGrowType(CsGrowType csGrowType)
	{
		csGrowType = custom(csGrowType);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsGrowType", csGrowType));
		csGrowType = this.GetCsGrowTypeById(id);
		MemCache.setValue(CsGrowType.class,id, csGrowType.getKeyValue());
		MemCache.setObject(CsGrowType.class,id,csGrowType);
		MemCache.clearObject(CsGrowType.M.class);
		MemCache.clearValue(CsGrowType.M.class);
		return Trigger.on(CsGrowType.class,Trigger.SAVE,csGrowType);	
	}

	

	/**
	 * 更新成长分类型
	 * @param csGrowType
	 */
	public void updateCsGrowType(CsGrowType csGrowType)
	{
		csGrowType = custom(csGrowType);
		this.getSqlMapClientTemplate().update("updateCsGrowType", csGrowType);
		csGrowType = this.GetCsGrowTypeById(csGrowType.getCsgtId());
		if(csGrowType!=null){
			MemCache.setValue(CsGrowType.class,csGrowType.getCsgtId(), csGrowType.getKeyValue());
			MemCache.setObject(CsGrowType.class,csGrowType.getCsgtId(),csGrowType);
		}
		MemCache.clearObject(CsGrowType.M.class);
		MemCache.clearValue(CsGrowType.M.class);
		Trigger.on(CsGrowType.class,Trigger.UPDATE,csGrowType);
	}
	/**
	 * 更新成长分类型非空字段
	 * @param csGrowType
	 */
	public void updateCsGrowType$NotNull(CsGrowType csGrowType)
	{
		csGrowType = custom(csGrowType);
		this.getSqlMapClientTemplate().update("updateCsGrowType$NotNull", csGrowType);
		csGrowType = this.GetCsGrowTypeById(csGrowType.getCsgtId());
		if(csGrowType!=null){
			MemCache.setValue(CsGrowType.class,csGrowType.getCsgtId(), csGrowType.getKeyValue());
			MemCache.setObject(CsGrowType.class,csGrowType.getCsgtId(),csGrowType);
		}
		MemCache.clearObject(CsGrowType.M.class);
		MemCache.clearValue(CsGrowType.M.class);
		Trigger.on(CsGrowType.class,Trigger.UPDATE,csGrowType);
	}

	/**
	 * 根据ID删除一个成长分类型
	 * @param id
	 */
	public void deleteCsGrowTypeById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsGrowType.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsGrowTypeById", params);
		MemCache.deleteValue(CsGrowType.class, id);
		MemCache.deleteObject(CsGrowType.class, id);
		MemCache.clearObject(CsGrowType.M.class);
		MemCache.clearValue(CsGrowType.M.class);
	}
	/**
	 * 根据ID逻辑删除成长分类型 
	 * @param id
	 * @return
	 */
	public void removeCsGrowTypeById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsGrowType.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsGrowTypeById", params);
		MemCache.deleteValue(CsGrowType.class, id);
		MemCache.deleteObject(CsGrowType.class, id);
		MemCache.clearObject(CsGrowType.M.class);
		MemCache.clearValue(CsGrowType.M.class);
	}
	/**
	 * 根据条件更新成长分类型
	 * @param values
	 * @param params
	 */
	public void updateCsGrowTypeByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsGrowTypeCount(params)>255){
			MemCache.clearValue(CsGrowType.class);
			MemCache.clearObject(CsGrowType.class);
		}else{
			params.put("eval","csgt_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsGrowTypeEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsGrowType.class, map.get("eval"));
				MemCache.deleteObject(CsGrowType.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsGrowType.M.class);
		MemCache.clearValue(CsGrowType.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateCsGrowTypeBy", allParams);
		Trigger.on(CsGrowType.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除成长分类型
	 * @param params
	 */
	public void deleteCsGrowTypeByConfirm(Map params){
		Trigger.on(CsGrowType.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsGrowTypeCount(params)>255){
			MemCache.clearValue(CsGrowType.class);
			MemCache.clearObject(CsGrowType.class);
		}else{
			params.put("eval","csgt_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsGrowTypeEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsGrowType.class, map.get("eval"));
				MemCache.deleteObject(CsGrowType.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsGrowType.M.class);
		MemCache.clearValue(CsGrowType.M.class);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsGrowTypeBy", params);
	}
	

}