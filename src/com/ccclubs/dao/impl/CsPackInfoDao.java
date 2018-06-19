package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsPackInfoDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsPackInfo;
import com.lazy3q.web.helper.$;

/**
 * 套餐内容的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsPackInfoDao extends SqlMapClientDaoSupport implements ICsPackInfoDao
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
	private CsPackInfo custom(CsPackInfo params){
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
			"cspiModel"			
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
	 * 获取所有套餐内容
	 * @return
	 */	
	public List<CsPackInfo> getCsPackInfoList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsPackInfo> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject","cspiAddTime","cspiAddTimeStart","cspiAddTimeEnd"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(CsPackInfo.M.class, md5);
			List caches = MemCache.getObject(CsPackInfo.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						CsPackInfo cacheObject = MemCache.getObject(CsPackInfo.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(CsPackInfo.M.class, md5);
							MemCache.deleteValue(CsPackInfo.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(CsPackInfo.M.class, md5);
					MemCache.deleteValue(CsPackInfo.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsPackInfoList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(CsPackInfo csPackInfo:list){
				caches.add(csPackInfo.getCspiId());
				MemCache.setObject(CsPackInfo.class,csPackInfo.getCspiId(), csPackInfo);
			}
			MemCache.setObject(CsPackInfo.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(CsPackInfo.M.class, md5, json, MemCache.defaultSeconds());
		}		
		
		return list;
	}
	
	
	/**
	 * 获取套餐内容统计
	 * @return
	 */
	public List getCsPackInfoStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsPackInfoStats", params);
	}

	/**
	 * 获取套餐内容分页
	 * @return
	 */
	public Page getCsPackInfoPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsPackInfoCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsPackInfoPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取套餐内容数量
	 * @return
	 */	
	public Long getCsPackInfoCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsPackInfoEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取套餐内容自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsPackInfoEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsPackInfoEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取套餐内容
	 * @param params
	 * @return
	 */
	public CsPackInfo getCsPackInfo(Map params)
	{		
		params = custom(params);
			
		List<CsPackInfo> list=this.getCsPackInfoList(params,1);
		CsPackInfo csPackInfo = list.isEmpty()?null:list.get(0);
		
		return csPackInfo;
	}
		
	/**
	 * 根据ID取套餐内容
	 * @param id
	 * @return
	 */
	public CsPackInfo getCsPackInfoById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsPackInfo csPackInfo = MemCache.getObject(CsPackInfo.class, id);
		if(csPackInfo!=null)
			return csPackInfo;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csPackInfo = (CsPackInfo) this.getSqlMapClientTemplate().queryForObject("getCsPackInfoById",params);
		if(csPackInfo!=null)
			MemCache.setValue(CsPackInfo.class,id, csPackInfo.getKeyValue());
		if(csPackInfo!=null)
			MemCache.setObject(CsPackInfo.class,id, csPackInfo);
		return csPackInfo;
	}
	
	/**
	 * 根据ID取套餐内容(不缓存)
	 * @param id
	 * @return
	 */
	public CsPackInfo GetCsPackInfoById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsPackInfo) this.getSqlMapClientTemplate().queryForObject("getCsPackInfoById",params);
	}
	

	/**
	 * 保存套餐内容
	 * @param csPackInfo
	 */
	public CsPackInfo saveCsPackInfo(CsPackInfo csPackInfo)
	{
		csPackInfo = custom(csPackInfo);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsPackInfo", csPackInfo));
		csPackInfo = this.GetCsPackInfoById(id);
		MemCache.setValue(CsPackInfo.class,id, csPackInfo.getKeyValue());
		MemCache.setObject(CsPackInfo.class,id,csPackInfo);
		MemCache.clearObject(CsPackInfo.M.class);
		MemCache.clearValue(CsPackInfo.M.class);
		return Trigger.on(CsPackInfo.class,Trigger.SAVE,csPackInfo);	
	}

	

	/**
	 * 更新套餐内容
	 * @param csPackInfo
	 */
	public void updateCsPackInfo(CsPackInfo csPackInfo)
	{
		csPackInfo = custom(csPackInfo);
		this.getSqlMapClientTemplate().update("updateCsPackInfo", csPackInfo);
		csPackInfo = this.GetCsPackInfoById(csPackInfo.getCspiId());
		if(csPackInfo!=null){
			MemCache.setValue(CsPackInfo.class,csPackInfo.getCspiId(), csPackInfo.getKeyValue());
			MemCache.setObject(CsPackInfo.class,csPackInfo.getCspiId(),csPackInfo);
		}
		MemCache.clearObject(CsPackInfo.M.class);
		MemCache.clearValue(CsPackInfo.M.class);
		Trigger.on(CsPackInfo.class,Trigger.UPDATE,csPackInfo);
	}
	/**
	 * 更新套餐内容非空字段
	 * @param csPackInfo
	 */
	public void updateCsPackInfo$NotNull(CsPackInfo csPackInfo)
	{
		csPackInfo = custom(csPackInfo);
		this.getSqlMapClientTemplate().update("updateCsPackInfo$NotNull", csPackInfo);
		csPackInfo = this.GetCsPackInfoById(csPackInfo.getCspiId());
		if(csPackInfo!=null){
			MemCache.setValue(CsPackInfo.class,csPackInfo.getCspiId(), csPackInfo.getKeyValue());
			MemCache.setObject(CsPackInfo.class,csPackInfo.getCspiId(),csPackInfo);
		}
		MemCache.clearObject(CsPackInfo.M.class);
		MemCache.clearValue(CsPackInfo.M.class);
		Trigger.on(CsPackInfo.class,Trigger.UPDATE,csPackInfo);
	}

	/**
	 * 根据ID删除一个套餐内容
	 * @param id
	 */
	public void deleteCsPackInfoById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsPackInfo.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsPackInfoById", params);
		MemCache.deleteValue(CsPackInfo.class, id);
		MemCache.deleteObject(CsPackInfo.class, id);
		MemCache.clearObject(CsPackInfo.M.class);
		MemCache.clearValue(CsPackInfo.M.class);
	}
	/**
	 * 根据条件更新套餐内容
	 * @param values
	 * @param params
	 */
	public void updateCsPackInfoByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsPackInfoCount(params)>255){
			MemCache.clearValue(CsPackInfo.class);
			MemCache.clearObject(CsPackInfo.class);
		}else{
			params.put("eval","cspi_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsPackInfoEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsPackInfo.class, map.get("eval"));
				MemCache.deleteObject(CsPackInfo.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsPackInfo.M.class);
		MemCache.clearValue(CsPackInfo.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateCsPackInfoBy", allParams);
		Trigger.on(CsPackInfo.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除套餐内容
	 * @param params
	 */
	public void deleteCsPackInfoByConfirm(Map params){
		Trigger.on(CsPackInfo.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsPackInfoCount(params)>255){
			MemCache.clearValue(CsPackInfo.class);
			MemCache.clearObject(CsPackInfo.class);
		}else{
			params.put("eval","cspi_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsPackInfoEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsPackInfo.class, map.get("eval"));
				MemCache.deleteObject(CsPackInfo.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsPackInfo.M.class);
		MemCache.clearValue(CsPackInfo.M.class);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsPackInfoBy", params);
	}
	

}