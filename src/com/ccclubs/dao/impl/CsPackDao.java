package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsPackDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsPack;
import com.lazy3q.web.helper.$;

/**
 * 系统套餐的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsPackDao extends SqlMapClientDaoSupport implements ICsPackDao
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
	private CsPack custom(CsPack params){
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
			"cspProfile"			
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
	 * 获取所有系统套餐
	 * @return
	 */	
	public List<CsPack> getCsPackList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsPack> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject","cspUpdateTime","cspUpdateTimeStart","cspUpdateTimeEnd","cspAddTime","cspAddTimeStart","cspAddTimeEnd"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(CsPack.M.class, md5);
			List caches = MemCache.getObject(CsPack.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						CsPack cacheObject = MemCache.getObject(CsPack.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(CsPack.M.class, md5);
							MemCache.deleteValue(CsPack.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(CsPack.M.class, md5);
					MemCache.deleteValue(CsPack.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsPackList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(CsPack csPack:list){
				caches.add(csPack.getCspId());
				MemCache.setObject(CsPack.class,csPack.getCspId(), csPack);
			}
			MemCache.setObject(CsPack.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(CsPack.M.class, md5, json, MemCache.defaultSeconds());
		}		
		
		return list;
	}
	
	
	/**
	 * 获取系统套餐统计
	 * @return
	 */
	public List getCsPackStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsPackStats", params);
	}

	/**
	 * 获取系统套餐分页
	 * @return
	 */
	public Page getCsPackPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsPackCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsPackPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取系统套餐数量
	 * @return
	 */	
	public Long getCsPackCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsPackEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取系统套餐自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsPackEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsPackEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取系统套餐
	 * @param params
	 * @return
	 */
	public CsPack getCsPack(Map params)
	{		
		params = custom(params);
			
		List<CsPack> list=this.getCsPackList(params,1);
		CsPack csPack = list.isEmpty()?null:list.get(0);
		
		return csPack;
	}
		
	/**
	 * 根据ID取系统套餐
	 * @param id
	 * @return
	 */
	public CsPack getCsPackById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsPack csPack = MemCache.getObject(CsPack.class, id);
		if(csPack!=null)
			return csPack;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csPack = (CsPack) this.getSqlMapClientTemplate().queryForObject("getCsPackById",params);
		if(csPack!=null)
			MemCache.setValue(CsPack.class,id, csPack.getKeyValue());
		if(csPack!=null)
			MemCache.setObject(CsPack.class,id, csPack);
		return csPack;
	}
	
	/**
	 * 根据ID取系统套餐(不缓存)
	 * @param id
	 * @return
	 */
	public CsPack GetCsPackById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsPack) this.getSqlMapClientTemplate().queryForObject("getCsPackById",params);
	}
	

	/**
	 * 保存系统套餐
	 * @param csPack
	 */
	public CsPack saveCsPack(CsPack csPack)
	{
		csPack = custom(csPack);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsPack", csPack));
		csPack = this.GetCsPackById(id);
		MemCache.setValue(CsPack.class,id, csPack.getKeyValue());
		MemCache.setObject(CsPack.class,id,csPack);
		MemCache.clearObject(CsPack.M.class);
		MemCache.clearValue(CsPack.M.class);
		return Trigger.on(CsPack.class,Trigger.SAVE,csPack);	
	}

	

	/**
	 * 更新系统套餐
	 * @param csPack
	 */
	public void updateCsPack(CsPack csPack)
	{
		csPack = custom(csPack);
		this.getSqlMapClientTemplate().update("updateCsPack", csPack);
		csPack = this.GetCsPackById(csPack.getCspId());
		if(csPack!=null){
			MemCache.setValue(CsPack.class,csPack.getCspId(), csPack.getKeyValue());
			MemCache.setObject(CsPack.class,csPack.getCspId(),csPack);
		}
		MemCache.clearObject(CsPack.M.class);
		MemCache.clearValue(CsPack.M.class);
		Trigger.on(CsPack.class,Trigger.UPDATE,csPack);
	}
	/**
	 * 更新系统套餐非空字段
	 * @param csPack
	 */
	public void updateCsPack$NotNull(CsPack csPack)
	{
		csPack = custom(csPack);
		this.getSqlMapClientTemplate().update("updateCsPack$NotNull", csPack);
		csPack = this.GetCsPackById(csPack.getCspId());
		if(csPack!=null){
			MemCache.setValue(CsPack.class,csPack.getCspId(), csPack.getKeyValue());
			MemCache.setObject(CsPack.class,csPack.getCspId(),csPack);
		}
		MemCache.clearObject(CsPack.M.class);
		MemCache.clearValue(CsPack.M.class);
		Trigger.on(CsPack.class,Trigger.UPDATE,csPack);
	}

	/**
	 * 根据ID删除一个系统套餐
	 * @param id
	 */
	public void deleteCsPackById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsPack.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsPackById", params);
		MemCache.deleteValue(CsPack.class, id);
		MemCache.deleteObject(CsPack.class, id);
		MemCache.clearObject(CsPack.M.class);
		MemCache.clearValue(CsPack.M.class);
	}
	/**
	 * 根据ID逻辑删除系统套餐 
	 * @param id
	 * @return
	 */
	public void removeCsPackById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsPack.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsPackById", params);
		MemCache.deleteValue(CsPack.class, id);
		MemCache.deleteObject(CsPack.class, id);
		MemCache.clearObject(CsPack.M.class);
		MemCache.clearValue(CsPack.M.class);
	}
	/**
	 * 根据条件更新系统套餐
	 * @param values
	 * @param params
	 */
	public void updateCsPackByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsPackCount(params)>255){
			MemCache.clearValue(CsPack.class);
			MemCache.clearObject(CsPack.class);
		}else{
			params.put("eval","csp_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsPackEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsPack.class, map.get("eval"));
				MemCache.deleteObject(CsPack.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsPack.M.class);
		MemCache.clearValue(CsPack.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateCsPackBy", allParams);
		Trigger.on(CsPack.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除系统套餐
	 * @param params
	 */
	public void deleteCsPackByConfirm(Map params){
		Trigger.on(CsPack.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsPackCount(params)>255){
			MemCache.clearValue(CsPack.class);
			MemCache.clearObject(CsPack.class);
		}else{
			params.put("eval","csp_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsPackEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsPack.class, map.get("eval"));
				MemCache.deleteObject(CsPack.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsPack.M.class);
		MemCache.clearValue(CsPack.M.class);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsPackBy", params);
	}
	

}