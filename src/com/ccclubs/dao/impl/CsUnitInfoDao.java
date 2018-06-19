package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsUnitInfoDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsUnitInfo;
import com.lazy3q.web.helper.$;

/**
 * 企业信息的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsUnitInfoDao extends SqlMapClientDaoSupport implements ICsUnitInfoDao
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
	private CsUnitInfo custom(CsUnitInfo params){
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
			"csuiName"			
			,"csuiPerson"			
			,"csuiAddress"			
			,"csuiContact"			
			,"csuiRemark"			
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
	 * 获取所有企业信息
	 * @return
	 */	
	public List<CsUnitInfo> getCsUnitInfoList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsUnitInfo> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject","csuiUpdateTime","csuiUpdateTimeStart","csuiUpdateTimeEnd","csuiAddTime","csuiAddTimeStart","csuiAddTimeEnd"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(CsUnitInfo.M.class, md5);
			List caches = MemCache.getObject(CsUnitInfo.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						CsUnitInfo cacheObject = MemCache.getObject(CsUnitInfo.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(CsUnitInfo.M.class, md5);
							MemCache.deleteValue(CsUnitInfo.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(CsUnitInfo.M.class, md5);
					MemCache.deleteValue(CsUnitInfo.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsUnitInfoList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(CsUnitInfo csUnitInfo:list){
				caches.add(csUnitInfo.getCsuiId());
				MemCache.setObject(CsUnitInfo.class,csUnitInfo.getCsuiId(), csUnitInfo);
			}
			MemCache.setObject(CsUnitInfo.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(CsUnitInfo.M.class, md5, json, MemCache.defaultSeconds());
		}		
		
		return list;
	}
	
	
	/**
	 * 获取企业信息统计
	 * @return
	 */
	public List getCsUnitInfoStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsUnitInfoStats", params);
	}

	/**
	 * 获取企业信息分页
	 * @return
	 */
	public Page getCsUnitInfoPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsUnitInfoCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsUnitInfoPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取企业信息数量
	 * @return
	 */	
	public Long getCsUnitInfoCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsUnitInfoEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取企业信息自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsUnitInfoEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsUnitInfoEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取企业信息
	 * @param params
	 * @return
	 */
	public CsUnitInfo getCsUnitInfo(Map params)
	{		
		params = custom(params);
			
		List<CsUnitInfo> list=this.getCsUnitInfoList(params,1);
		CsUnitInfo csUnitInfo = list.isEmpty()?null:list.get(0);
		
		return csUnitInfo;
	}
		
	/**
	 * 根据ID取企业信息
	 * @param id
	 * @return
	 */
	public CsUnitInfo getCsUnitInfoById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsUnitInfo csUnitInfo = MemCache.getObject(CsUnitInfo.class, id);
		if(csUnitInfo!=null)
			return csUnitInfo;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csUnitInfo = (CsUnitInfo) this.getSqlMapClientTemplate().queryForObject("getCsUnitInfoById",params);
		if(csUnitInfo!=null)
			MemCache.setValue(CsUnitInfo.class,id, csUnitInfo.getKeyValue());
		if(csUnitInfo!=null)
			MemCache.setObject(CsUnitInfo.class,id, csUnitInfo);
		return csUnitInfo;
	}
	
	/**
	 * 根据ID取企业信息(不缓存)
	 * @param id
	 * @return
	 */
	public CsUnitInfo GetCsUnitInfoById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsUnitInfo) this.getSqlMapClientTemplate().queryForObject("getCsUnitInfoById",params);
	}
	

	/**
	 * 保存企业信息
	 * @param csUnitInfo
	 */
	public CsUnitInfo saveCsUnitInfo(CsUnitInfo csUnitInfo)
	{
		csUnitInfo = custom(csUnitInfo);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsUnitInfo", csUnitInfo));
		csUnitInfo = this.GetCsUnitInfoById(id);
		MemCache.setValue(CsUnitInfo.class,id, csUnitInfo.getKeyValue());
		MemCache.setObject(CsUnitInfo.class,id,csUnitInfo);
		MemCache.clearObject(CsUnitInfo.M.class);
		MemCache.clearValue(CsUnitInfo.M.class);
		return Trigger.on(CsUnitInfo.class,Trigger.SAVE,csUnitInfo);	
	}

	

	/**
	 * 更新企业信息
	 * @param csUnitInfo
	 */
	public void updateCsUnitInfo(CsUnitInfo csUnitInfo)
	{
		csUnitInfo = custom(csUnitInfo);
		this.getSqlMapClientTemplate().update("updateCsUnitInfo", csUnitInfo);
		csUnitInfo = this.GetCsUnitInfoById(csUnitInfo.getCsuiId());
		if(csUnitInfo!=null){
			MemCache.setValue(CsUnitInfo.class,csUnitInfo.getCsuiId(), csUnitInfo.getKeyValue());
			MemCache.setObject(CsUnitInfo.class,csUnitInfo.getCsuiId(),csUnitInfo);
		}
		MemCache.clearObject(CsUnitInfo.M.class);
		MemCache.clearValue(CsUnitInfo.M.class);
		Trigger.on(CsUnitInfo.class,Trigger.UPDATE,csUnitInfo);
	}
	/**
	 * 更新企业信息非空字段
	 * @param csUnitInfo
	 */
	public void updateCsUnitInfo$NotNull(CsUnitInfo csUnitInfo)
	{
		csUnitInfo = custom(csUnitInfo);
		this.getSqlMapClientTemplate().update("updateCsUnitInfo$NotNull", csUnitInfo);
		csUnitInfo = this.GetCsUnitInfoById(csUnitInfo.getCsuiId());
		if(csUnitInfo!=null){
			MemCache.setValue(CsUnitInfo.class,csUnitInfo.getCsuiId(), csUnitInfo.getKeyValue());
			MemCache.setObject(CsUnitInfo.class,csUnitInfo.getCsuiId(),csUnitInfo);
		}
		MemCache.clearObject(CsUnitInfo.M.class);
		MemCache.clearValue(CsUnitInfo.M.class);
		Trigger.on(CsUnitInfo.class,Trigger.UPDATE,csUnitInfo);
	}

	/**
	 * 根据ID删除一个企业信息
	 * @param id
	 */
	public void deleteCsUnitInfoById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsUnitInfo.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsUnitInfoById", params);
		MemCache.deleteValue(CsUnitInfo.class, id);
		MemCache.deleteObject(CsUnitInfo.class, id);
		MemCache.clearObject(CsUnitInfo.M.class);
		MemCache.clearValue(CsUnitInfo.M.class);
	}
	/**
	 * 根据ID逻辑删除企业信息 
	 * @param id
	 * @return
	 */
	public void removeCsUnitInfoById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsUnitInfo.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsUnitInfoById", params);
		MemCache.deleteValue(CsUnitInfo.class, id);
		MemCache.deleteObject(CsUnitInfo.class, id);
		MemCache.clearObject(CsUnitInfo.M.class);
		MemCache.clearValue(CsUnitInfo.M.class);
	}
	/**
	 * 根据条件更新企业信息
	 * @param values
	 * @param params
	 */
	public void updateCsUnitInfoByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsUnitInfoCount(params)>255){
			MemCache.clearValue(CsUnitInfo.class);
			MemCache.clearObject(CsUnitInfo.class);
		}else{
			params.put("eval","csui_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsUnitInfoEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsUnitInfo.class, map.get("eval"));
				MemCache.deleteObject(CsUnitInfo.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsUnitInfo.M.class);
		MemCache.clearValue(CsUnitInfo.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateCsUnitInfoBy", allParams);
		Trigger.on(CsUnitInfo.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除企业信息
	 * @param params
	 */
	public void deleteCsUnitInfoByConfirm(Map params){
		Trigger.on(CsUnitInfo.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsUnitInfoCount(params)>255){
			MemCache.clearValue(CsUnitInfo.class);
			MemCache.clearObject(CsUnitInfo.class);
		}else{
			params.put("eval","csui_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsUnitInfoEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsUnitInfo.class, map.get("eval"));
				MemCache.deleteObject(CsUnitInfo.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsUnitInfo.M.class);
		MemCache.clearValue(CsUnitInfo.M.class);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsUnitInfoBy", params);
	}
	

}