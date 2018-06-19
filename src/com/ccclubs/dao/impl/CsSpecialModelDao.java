package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsSpecialModelDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsSpecialModel;
import com.lazy3q.web.helper.$;

/**
 * 专车车型的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsSpecialModelDao extends SqlMapClientDaoSupport implements ICsSpecialModelDao
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
	private CsSpecialModel custom(CsSpecialModel params){
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
			"cssmName"			
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
	 * 获取所有专车车型
	 * @return
	 */	
	public List<CsSpecialModel> getCsSpecialModelList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsSpecialModel> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsSpecialModelList", params);
		
		return list;
	}
	
	
	/**
	 * 获取专车车型统计
	 * @return
	 */
	public List getCsSpecialModelStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsSpecialModelStats", params);
	}

	/**
	 * 获取专车车型分页
	 * @return
	 */
	public Page getCsSpecialModelPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsSpecialModelCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsSpecialModelPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取专车车型数量
	 * @return
	 */	
	public Long getCsSpecialModelCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsSpecialModelEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取专车车型自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsSpecialModelEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsSpecialModelEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取专车车型
	 * @param params
	 * @return
	 */
	public CsSpecialModel getCsSpecialModel(Map params)
	{		
		params = custom(params);
			
		List<CsSpecialModel> list=this.getCsSpecialModelList(params,1);
		CsSpecialModel csSpecialModel = list.isEmpty()?null:list.get(0);
		
		return csSpecialModel;
	}
		
	/**
	 * 根据ID取专车车型
	 * @param id
	 * @return
	 */
	public CsSpecialModel getCsSpecialModelById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsSpecialModel csSpecialModel = null;
		if(csSpecialModel!=null)
			return csSpecialModel;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csSpecialModel = (CsSpecialModel) this.getSqlMapClientTemplate().queryForObject("getCsSpecialModelById",params);
		if(csSpecialModel!=null)
			MemCache.setValue(CsSpecialModel.class,id, csSpecialModel.getKeyValue());
		return csSpecialModel;
	}
	
	/**
	 * 根据ID取专车车型(不缓存)
	 * @param id
	 * @return
	 */
	public CsSpecialModel GetCsSpecialModelById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsSpecialModel) this.getSqlMapClientTemplate().queryForObject("getCsSpecialModelById",params);
	}
	

	/**
	 * 保存专车车型
	 * @param csSpecialModel
	 */
	public CsSpecialModel saveCsSpecialModel(CsSpecialModel csSpecialModel)
	{
		csSpecialModel = custom(csSpecialModel);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsSpecialModel", csSpecialModel));
		csSpecialModel = this.GetCsSpecialModelById(id);
		MemCache.setValue(CsSpecialModel.class,id, csSpecialModel.getKeyValue());
		return Trigger.on(CsSpecialModel.class,Trigger.SAVE,csSpecialModel);	
	}

	

	/**
	 * 更新专车车型
	 * @param csSpecialModel
	 */
	public void updateCsSpecialModel(CsSpecialModel csSpecialModel)
	{
		csSpecialModel = custom(csSpecialModel);
		this.getSqlMapClientTemplate().update("updateCsSpecialModel", csSpecialModel);
		csSpecialModel = this.GetCsSpecialModelById(csSpecialModel.getCssmId());
		if(csSpecialModel!=null){
			MemCache.setValue(CsSpecialModel.class,csSpecialModel.getCssmId(), csSpecialModel.getKeyValue());
		}
		Trigger.on(CsSpecialModel.class,Trigger.UPDATE,csSpecialModel);
	}
	/**
	 * 更新专车车型非空字段
	 * @param csSpecialModel
	 */
	public void updateCsSpecialModel$NotNull(CsSpecialModel csSpecialModel)
	{
		csSpecialModel = custom(csSpecialModel);
		this.getSqlMapClientTemplate().update("updateCsSpecialModel$NotNull", csSpecialModel);
		csSpecialModel = this.GetCsSpecialModelById(csSpecialModel.getCssmId());
		if(csSpecialModel!=null){
			MemCache.setValue(CsSpecialModel.class,csSpecialModel.getCssmId(), csSpecialModel.getKeyValue());
		}
		Trigger.on(CsSpecialModel.class,Trigger.UPDATE,csSpecialModel);
	}

	/**
	 * 根据ID删除一个专车车型
	 * @param id
	 */
	public void deleteCsSpecialModelById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsSpecialModel.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsSpecialModelById", params);
		MemCache.deleteValue(CsSpecialModel.class, id);
	}
	/**
	 * 根据ID逻辑删除专车车型 
	 * @param id
	 * @return
	 */
	public void removeCsSpecialModelById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsSpecialModel.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsSpecialModelById", params);
		MemCache.deleteValue(CsSpecialModel.class, id);
	}
	/**
	 * 根据条件更新专车车型
	 * @param values
	 * @param params
	 */
	public void updateCsSpecialModelByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsSpecialModelCount(params)>255){
			MemCache.clearValue(CsSpecialModel.class);
		}else{
			params.put("eval","cssm_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsSpecialModelEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsSpecialModel.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsSpecialModelBy", allParams);
		Trigger.on(CsSpecialModel.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除专车车型
	 * @param params
	 */
	public void deleteCsSpecialModelByConfirm(Map params){
		Trigger.on(CsSpecialModel.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsSpecialModelCount(params)>255){
			MemCache.clearValue(CsSpecialModel.class);
		}else{
			params.put("eval","cssm_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsSpecialModelEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsSpecialModel.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsSpecialModelBy", params);
	}
	

}