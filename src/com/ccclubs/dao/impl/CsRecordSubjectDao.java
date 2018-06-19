package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsRecordSubjectDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsRecordSubject;
import com.lazy3q.web.helper.$;

/**
 * 消费科目的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsRecordSubjectDao extends SqlMapClientDaoSupport implements ICsRecordSubjectDao
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
	private CsRecordSubject custom(CsRecordSubject params){
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
	 * 获取所有消费科目
	 * @return
	 */	
	public List<CsRecordSubject> getCsRecordSubjectList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsRecordSubject> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(CsRecordSubject.M.class, md5);
			List caches = MemCache.getObject(CsRecordSubject.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						CsRecordSubject cacheObject = MemCache.getObject(CsRecordSubject.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(CsRecordSubject.M.class, md5);
							MemCache.deleteValue(CsRecordSubject.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(CsRecordSubject.M.class, md5);
					MemCache.deleteValue(CsRecordSubject.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsRecordSubjectList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(CsRecordSubject csRecordSubject:list){
				caches.add(csRecordSubject.getCsrsId());
				MemCache.setObject(CsRecordSubject.class,csRecordSubject.getCsrsId(), csRecordSubject);
			}
			MemCache.setObject(CsRecordSubject.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(CsRecordSubject.M.class, md5, json, MemCache.defaultSeconds());
		}		
		
		return list;
	}
	
	
	/**
	 * 获取消费科目统计
	 * @return
	 */
	public List getCsRecordSubjectStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsRecordSubjectStats", params);
	}

	/**
	 * 获取消费科目分页
	 * @return
	 */
	public Page getCsRecordSubjectPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsRecordSubjectCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsRecordSubjectPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取消费科目数量
	 * @return
	 */	
	public Long getCsRecordSubjectCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsRecordSubjectEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取消费科目自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsRecordSubjectEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsRecordSubjectEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取消费科目
	 * @param params
	 * @return
	 */
	public CsRecordSubject getCsRecordSubject(Map params)
	{		
		params = custom(params);
			
		List<CsRecordSubject> list=this.getCsRecordSubjectList(params,1);
		CsRecordSubject csRecordSubject = list.isEmpty()?null:list.get(0);
		
		return csRecordSubject;
	}
		
	/**
	 * 根据ID取消费科目
	 * @param id
	 * @return
	 */
	public CsRecordSubject getCsRecordSubjectById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsRecordSubject csRecordSubject = MemCache.getObject(CsRecordSubject.class, id);
		if(csRecordSubject!=null)
			return csRecordSubject;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csRecordSubject = (CsRecordSubject) this.getSqlMapClientTemplate().queryForObject("getCsRecordSubjectById",params);
		if(csRecordSubject!=null)
			MemCache.setValue(CsRecordSubject.class,id, csRecordSubject.getKeyValue());
		if(csRecordSubject!=null)
			MemCache.setObject(CsRecordSubject.class,id, csRecordSubject);
		return csRecordSubject;
	}
	
	/**
	 * 根据ID取消费科目(不缓存)
	 * @param id
	 * @return
	 */
	public CsRecordSubject GetCsRecordSubjectById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsRecordSubject) this.getSqlMapClientTemplate().queryForObject("getCsRecordSubjectById",params);
	}
	

	/**
	 * 保存消费科目
	 * @param csRecordSubject
	 */
	public CsRecordSubject saveCsRecordSubject(CsRecordSubject csRecordSubject)
	{
		csRecordSubject = custom(csRecordSubject);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsRecordSubject", csRecordSubject));
		csRecordSubject = this.GetCsRecordSubjectById(id);
		MemCache.setValue(CsRecordSubject.class,id, csRecordSubject.getKeyValue());
		MemCache.setObject(CsRecordSubject.class,id,csRecordSubject);
		MemCache.clearObject(CsRecordSubject.M.class);
		MemCache.clearValue(CsRecordSubject.M.class);
		return Trigger.on(CsRecordSubject.class,Trigger.SAVE,csRecordSubject);	
	}

	

	/**
	 * 更新消费科目
	 * @param csRecordSubject
	 */
	public void updateCsRecordSubject(CsRecordSubject csRecordSubject)
	{
		csRecordSubject = custom(csRecordSubject);
		this.getSqlMapClientTemplate().update("updateCsRecordSubject", csRecordSubject);
		csRecordSubject = this.GetCsRecordSubjectById(csRecordSubject.getCsrsId());
		if(csRecordSubject!=null){
			MemCache.setValue(CsRecordSubject.class,csRecordSubject.getCsrsId(), csRecordSubject.getKeyValue());
			MemCache.setObject(CsRecordSubject.class,csRecordSubject.getCsrsId(),csRecordSubject);
		}
		MemCache.clearObject(CsRecordSubject.M.class);
		MemCache.clearValue(CsRecordSubject.M.class);
		Trigger.on(CsRecordSubject.class,Trigger.UPDATE,csRecordSubject);
	}
	/**
	 * 更新消费科目非空字段
	 * @param csRecordSubject
	 */
	public void updateCsRecordSubject$NotNull(CsRecordSubject csRecordSubject)
	{
		csRecordSubject = custom(csRecordSubject);
		this.getSqlMapClientTemplate().update("updateCsRecordSubject$NotNull", csRecordSubject);
		csRecordSubject = this.GetCsRecordSubjectById(csRecordSubject.getCsrsId());
		if(csRecordSubject!=null){
			MemCache.setValue(CsRecordSubject.class,csRecordSubject.getCsrsId(), csRecordSubject.getKeyValue());
			MemCache.setObject(CsRecordSubject.class,csRecordSubject.getCsrsId(),csRecordSubject);
		}
		MemCache.clearObject(CsRecordSubject.M.class);
		MemCache.clearValue(CsRecordSubject.M.class);
		Trigger.on(CsRecordSubject.class,Trigger.UPDATE,csRecordSubject);
	}

	/**
	 * 根据ID删除一个消费科目
	 * @param id
	 */
	public void deleteCsRecordSubjectById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsRecordSubject.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsRecordSubjectById", params);
		MemCache.deleteValue(CsRecordSubject.class, id);
		MemCache.deleteObject(CsRecordSubject.class, id);
		MemCache.clearObject(CsRecordSubject.M.class);
		MemCache.clearValue(CsRecordSubject.M.class);
	}
	/**
	 * 根据条件更新消费科目
	 * @param values
	 * @param params
	 */
	public void updateCsRecordSubjectByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsRecordSubjectCount(params)>255){
			MemCache.clearValue(CsRecordSubject.class);
			MemCache.clearObject(CsRecordSubject.class);
		}else{
			params.put("eval","csrs_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsRecordSubjectEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsRecordSubject.class, map.get("eval"));
				MemCache.deleteObject(CsRecordSubject.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsRecordSubject.M.class);
		MemCache.clearValue(CsRecordSubject.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateCsRecordSubjectBy", allParams);
		Trigger.on(CsRecordSubject.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除消费科目
	 * @param params
	 */
	public void deleteCsRecordSubjectByConfirm(Map params){
		Trigger.on(CsRecordSubject.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsRecordSubjectCount(params)>255){
			MemCache.clearValue(CsRecordSubject.class);
			MemCache.clearObject(CsRecordSubject.class);
		}else{
			params.put("eval","csrs_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsRecordSubjectEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsRecordSubject.class, map.get("eval"));
				MemCache.deleteObject(CsRecordSubject.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsRecordSubject.M.class);
		MemCache.clearValue(CsRecordSubject.M.class);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsRecordSubjectBy", params);
	}
	

}