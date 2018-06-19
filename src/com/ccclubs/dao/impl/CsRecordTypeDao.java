package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsRecordTypeDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsRecordType;
import com.lazy3q.web.helper.$;

/**
 * 消费类型的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsRecordTypeDao extends SqlMapClientDaoSupport implements ICsRecordTypeDao
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
	private CsRecordType custom(CsRecordType params){
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
	 * 获取所有消费类型
	 * @return
	 */	
	public List<CsRecordType> getCsRecordTypeList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsRecordType> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(CsRecordType.M.class, md5);
			List caches = MemCache.getObject(CsRecordType.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						CsRecordType cacheObject = MemCache.getObject(CsRecordType.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(CsRecordType.M.class, md5);
							MemCache.deleteValue(CsRecordType.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(CsRecordType.M.class, md5);
					MemCache.deleteValue(CsRecordType.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsRecordTypeList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(CsRecordType csRecordType:list){
				caches.add(csRecordType.getCsrtId());
				MemCache.setObject(CsRecordType.class,csRecordType.getCsrtId(), csRecordType);
			}
			MemCache.setObject(CsRecordType.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(CsRecordType.M.class, md5, json, MemCache.defaultSeconds());
		}		
		
		return list;
	}
	
	
	/**
	 * 获取消费类型统计
	 * @return
	 */
	public List getCsRecordTypeStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsRecordTypeStats", params);
	}

	/**
	 * 获取消费类型分页
	 * @return
	 */
	public Page getCsRecordTypePage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsRecordTypeCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsRecordTypePage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取消费类型数量
	 * @return
	 */	
	public Long getCsRecordTypeCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsRecordTypeEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取消费类型自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsRecordTypeEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsRecordTypeEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取消费类型
	 * @param params
	 * @return
	 */
	public CsRecordType getCsRecordType(Map params)
	{		
		params = custom(params);
			
		List<CsRecordType> list=this.getCsRecordTypeList(params,1);
		CsRecordType csRecordType = list.isEmpty()?null:list.get(0);
		
		return csRecordType;
	}
		
	/**
	 * 根据ID取消费类型
	 * @param id
	 * @return
	 */
	public CsRecordType getCsRecordTypeById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsRecordType csRecordType = MemCache.getObject(CsRecordType.class, id);
		if(csRecordType!=null)
			return csRecordType;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csRecordType = (CsRecordType) this.getSqlMapClientTemplate().queryForObject("getCsRecordTypeById",params);
		if(csRecordType!=null)
			MemCache.setValue(CsRecordType.class,id, csRecordType.getKeyValue());
		if(csRecordType!=null)
			MemCache.setObject(CsRecordType.class,id, csRecordType);
		return csRecordType;
	}
	
	/**
	 * 根据ID取消费类型(不缓存)
	 * @param id
	 * @return
	 */
	public CsRecordType GetCsRecordTypeById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsRecordType) this.getSqlMapClientTemplate().queryForObject("getCsRecordTypeById",params);
	}
	

	/**
	 * 保存消费类型
	 * @param csRecordType
	 */
	public CsRecordType saveCsRecordType(CsRecordType csRecordType)
	{
		csRecordType = custom(csRecordType);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsRecordType", csRecordType));
		csRecordType = this.GetCsRecordTypeById(id);
		MemCache.setValue(CsRecordType.class,id, csRecordType.getKeyValue());
		MemCache.setObject(CsRecordType.class,id,csRecordType);
		MemCache.clearObject(CsRecordType.M.class);
		MemCache.clearValue(CsRecordType.M.class);
		return Trigger.on(CsRecordType.class,Trigger.SAVE,csRecordType);	
	}

	

	/**
	 * 更新消费类型
	 * @param csRecordType
	 */
	public void updateCsRecordType(CsRecordType csRecordType)
	{
		csRecordType = custom(csRecordType);
		this.getSqlMapClientTemplate().update("updateCsRecordType", csRecordType);
		csRecordType = this.GetCsRecordTypeById(csRecordType.getCsrtId());
		if(csRecordType!=null){
			MemCache.setValue(CsRecordType.class,csRecordType.getCsrtId(), csRecordType.getKeyValue());
			MemCache.setObject(CsRecordType.class,csRecordType.getCsrtId(),csRecordType);
		}
		MemCache.clearObject(CsRecordType.M.class);
		MemCache.clearValue(CsRecordType.M.class);
		Trigger.on(CsRecordType.class,Trigger.UPDATE,csRecordType);
	}
	/**
	 * 更新消费类型非空字段
	 * @param csRecordType
	 */
	public void updateCsRecordType$NotNull(CsRecordType csRecordType)
	{
		csRecordType = custom(csRecordType);
		this.getSqlMapClientTemplate().update("updateCsRecordType$NotNull", csRecordType);
		csRecordType = this.GetCsRecordTypeById(csRecordType.getCsrtId());
		if(csRecordType!=null){
			MemCache.setValue(CsRecordType.class,csRecordType.getCsrtId(), csRecordType.getKeyValue());
			MemCache.setObject(CsRecordType.class,csRecordType.getCsrtId(),csRecordType);
		}
		MemCache.clearObject(CsRecordType.M.class);
		MemCache.clearValue(CsRecordType.M.class);
		Trigger.on(CsRecordType.class,Trigger.UPDATE,csRecordType);
	}

	/**
	 * 根据ID删除一个消费类型
	 * @param id
	 */
	public void deleteCsRecordTypeById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsRecordType.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsRecordTypeById", params);
		MemCache.deleteValue(CsRecordType.class, id);
		MemCache.deleteObject(CsRecordType.class, id);
		MemCache.clearObject(CsRecordType.M.class);
		MemCache.clearValue(CsRecordType.M.class);
	}
	/**
	 * 根据ID逻辑删除消费类型 
	 * @param id
	 * @return
	 */
	public void removeCsRecordTypeById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsRecordType.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsRecordTypeById", params);
		MemCache.deleteValue(CsRecordType.class, id);
		MemCache.deleteObject(CsRecordType.class, id);
		MemCache.clearObject(CsRecordType.M.class);
		MemCache.clearValue(CsRecordType.M.class);
	}
	/**
	 * 根据条件更新消费类型
	 * @param values
	 * @param params
	 */
	public void updateCsRecordTypeByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsRecordTypeCount(params)>255){
			MemCache.clearValue(CsRecordType.class);
			MemCache.clearObject(CsRecordType.class);
		}else{
			params.put("eval","csrt_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsRecordTypeEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsRecordType.class, map.get("eval"));
				MemCache.deleteObject(CsRecordType.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsRecordType.M.class);
		MemCache.clearValue(CsRecordType.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateCsRecordTypeBy", allParams);
		Trigger.on(CsRecordType.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除消费类型
	 * @param params
	 */
	public void deleteCsRecordTypeByConfirm(Map params){
		Trigger.on(CsRecordType.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsRecordTypeCount(params)>255){
			MemCache.clearValue(CsRecordType.class);
			MemCache.clearObject(CsRecordType.class);
		}else{
			params.put("eval","csrt_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsRecordTypeEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsRecordType.class, map.get("eval"));
				MemCache.deleteObject(CsRecordType.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsRecordType.M.class);
		MemCache.clearValue(CsRecordType.M.class);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsRecordTypeBy", params);
	}
	

}