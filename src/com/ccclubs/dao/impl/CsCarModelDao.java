package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsCarModelDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCarModel;
import com.lazy3q.web.helper.$;

/**
 * 车型的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsCarModelDao extends SqlMapClientDaoSupport implements ICsCarModelDao
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
	private CsCarModel custom(CsCarModel params){
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
			"cscmRemark"			
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
	 * 获取所有车型
	 * @return
	 */	
	public List<CsCarModel> getCsCarModelList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsCarModel> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject","cscmUpdateTime","cscmUpdateTimeStart","cscmUpdateTimeEnd","cscmAddTime","cscmAddTimeStart","cscmAddTimeEnd"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(CsCarModel.M.class, md5);
			List caches = MemCache.getObject(CsCarModel.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						CsCarModel cacheObject = MemCache.getObject(CsCarModel.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(CsCarModel.M.class, md5);
							MemCache.deleteValue(CsCarModel.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(CsCarModel.M.class, md5);
					MemCache.deleteValue(CsCarModel.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsCarModelList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(CsCarModel csCarModel:list){
				caches.add(csCarModel.getCscmId());
				MemCache.setObject(CsCarModel.class,csCarModel.getCscmId(), csCarModel);
			}
			MemCache.setObject(CsCarModel.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(CsCarModel.M.class, md5, json, MemCache.defaultSeconds());
		}		
		
		return list;
	}
	
	
	/**
	 * 获取车型统计
	 * @return
	 */
	public List getCsCarModelStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsCarModelStats", params);
	}

	/**
	 * 获取车型分页
	 * @return
	 */
	public Page getCsCarModelPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsCarModelCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsCarModelPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取车型数量
	 * @return
	 */	
	public Long getCsCarModelCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCarModelEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取车型自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsCarModelEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCarModelEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取车型
	 * @param params
	 * @return
	 */
	public CsCarModel getCsCarModel(Map params)
	{		
		params = custom(params);
			
		List<CsCarModel> list=this.getCsCarModelList(params,1);
		CsCarModel csCarModel = list.isEmpty()?null:list.get(0);
		
		return csCarModel;
	}
		
	/**
	 * 根据ID取车型
	 * @param id
	 * @return
	 */
	public CsCarModel getCsCarModelById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsCarModel csCarModel = MemCache.getObject(CsCarModel.class, id);
		if(csCarModel!=null)
			return csCarModel;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csCarModel = (CsCarModel) this.getSqlMapClientTemplate().queryForObject("getCsCarModelById",params);
		if(csCarModel!=null)
			MemCache.setValue(CsCarModel.class,id, csCarModel.getKeyValue());
		if(csCarModel!=null)
			MemCache.setObject(CsCarModel.class,id, csCarModel);
		return csCarModel;
	}
	
	/**
	 * 根据ID取车型(不缓存)
	 * @param id
	 * @return
	 */
	public CsCarModel GetCsCarModelById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsCarModel) this.getSqlMapClientTemplate().queryForObject("getCsCarModelById",params);
	}
	

	/**
	 * 保存车型
	 * @param csCarModel
	 */
	public CsCarModel saveCsCarModel(CsCarModel csCarModel)
	{
		csCarModel = custom(csCarModel);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsCarModel", csCarModel));
		csCarModel = this.GetCsCarModelById(id);
		MemCache.setValue(CsCarModel.class,id, csCarModel.getKeyValue());
		MemCache.setObject(CsCarModel.class,id,csCarModel);
		MemCache.clearObject(CsCarModel.M.class);
		MemCache.clearValue(CsCarModel.M.class);
		return Trigger.on(CsCarModel.class,Trigger.SAVE,csCarModel);	
	}

	

	/**
	 * 更新车型
	 * @param csCarModel
	 */
	public void updateCsCarModel(CsCarModel csCarModel)
	{
		csCarModel = custom(csCarModel);
		this.getSqlMapClientTemplate().update("updateCsCarModel", csCarModel);
		csCarModel = this.GetCsCarModelById(csCarModel.getCscmId());
		if(csCarModel!=null){
			MemCache.setValue(CsCarModel.class,csCarModel.getCscmId(), csCarModel.getKeyValue());
			MemCache.setObject(CsCarModel.class,csCarModel.getCscmId(),csCarModel);
		}
		MemCache.clearObject(CsCarModel.M.class);
		MemCache.clearValue(CsCarModel.M.class);
		Trigger.on(CsCarModel.class,Trigger.UPDATE,csCarModel);
	}
	/**
	 * 更新车型非空字段
	 * @param csCarModel
	 */
	public void updateCsCarModel$NotNull(CsCarModel csCarModel)
	{
		csCarModel = custom(csCarModel);
		this.getSqlMapClientTemplate().update("updateCsCarModel$NotNull", csCarModel);
		csCarModel = this.GetCsCarModelById(csCarModel.getCscmId());
		if(csCarModel!=null){
			MemCache.setValue(CsCarModel.class,csCarModel.getCscmId(), csCarModel.getKeyValue());
			MemCache.setObject(CsCarModel.class,csCarModel.getCscmId(),csCarModel);
		}
		MemCache.clearObject(CsCarModel.M.class);
		MemCache.clearValue(CsCarModel.M.class);
		Trigger.on(CsCarModel.class,Trigger.UPDATE,csCarModel);
	}

	/**
	 * 根据ID删除一个车型
	 * @param id
	 */
	public void deleteCsCarModelById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsCarModel.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsCarModelById", params);
		MemCache.deleteValue(CsCarModel.class, id);
		MemCache.deleteObject(CsCarModel.class, id);
		MemCache.clearObject(CsCarModel.M.class);
		MemCache.clearValue(CsCarModel.M.class);
	}
	/**
	 * 根据ID逻辑删除车型 
	 * @param id
	 * @return
	 */
	public void removeCsCarModelById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsCarModel.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsCarModelById", params);
		MemCache.deleteValue(CsCarModel.class, id);
		MemCache.deleteObject(CsCarModel.class, id);
		MemCache.clearObject(CsCarModel.M.class);
		MemCache.clearValue(CsCarModel.M.class);
	}
	/**
	 * 根据条件更新车型
	 * @param values
	 * @param params
	 */
	public void updateCsCarModelByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsCarModelCount(params)>255){
			MemCache.clearValue(CsCarModel.class);
			MemCache.clearObject(CsCarModel.class);
		}else{
			params.put("eval","cscm_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCarModelEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCarModel.class, map.get("eval"));
				MemCache.deleteObject(CsCarModel.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsCarModel.M.class);
		MemCache.clearValue(CsCarModel.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateCsCarModelBy", allParams);
		Trigger.on(CsCarModel.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除车型
	 * @param params
	 */
	public void deleteCsCarModelByConfirm(Map params){
		Trigger.on(CsCarModel.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsCarModelCount(params)>255){
			MemCache.clearValue(CsCarModel.class);
			MemCache.clearObject(CsCarModel.class);
		}else{
			params.put("eval","cscm_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCarModelEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCarModel.class, map.get("eval"));
				MemCache.deleteObject(CsCarModel.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsCarModel.M.class);
		MemCache.clearValue(CsCarModel.M.class);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsCarModelBy", params);
	}
	

}