package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsMemberShipDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsMemberShip;
import com.lazy3q.web.helper.$;

/**
 * 会员关系的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsMemberShipDao extends SqlMapClientDaoSupport implements ICsMemberShipDao
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
	private CsMemberShip custom(CsMemberShip params){
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
			"csmsRemark"			
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
	 * 获取所有会员关系
	 * @return
	 */	
	public List<CsMemberShip> getCsMemberShipList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsMemberShip> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsMemberShipList", params);
		
		return list;
	}
	
	
	/**
	 * 获取会员关系统计
	 * @return
	 */
	public List getCsMemberShipStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsMemberShipStats", params);
	}

	/**
	 * 获取会员关系分页
	 * @return
	 */
	public Page getCsMemberShipPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsMemberShipCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsMemberShipPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取会员关系数量
	 * @return
	 */	
	public Long getCsMemberShipCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsMemberShipEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取会员关系自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsMemberShipEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsMemberShipEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取会员关系
	 * @param params
	 * @return
	 */
	public CsMemberShip getCsMemberShip(Map params)
	{		
		params = custom(params);
			
		List<CsMemberShip> list=this.getCsMemberShipList(params,1);
		CsMemberShip csMemberShip = list.isEmpty()?null:list.get(0);
		
		return csMemberShip;
	}
		
	/**
	 * 根据ID取会员关系
	 * @param id
	 * @return
	 */
	public CsMemberShip getCsMemberShipById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsMemberShip csMemberShip = null;
		if(csMemberShip!=null)
			return csMemberShip;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csMemberShip = (CsMemberShip) this.getSqlMapClientTemplate().queryForObject("getCsMemberShipById",params);
		if(csMemberShip!=null)
			MemCache.setValue(CsMemberShip.class,id, csMemberShip.getKeyValue());
		return csMemberShip;
	}
	
	/**
	 * 根据ID取会员关系(不缓存)
	 * @param id
	 * @return
	 */
	public CsMemberShip GetCsMemberShipById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsMemberShip) this.getSqlMapClientTemplate().queryForObject("getCsMemberShipById",params);
	}
	

	/**
	 * 保存会员关系
	 * @param csMemberShip
	 */
	public CsMemberShip saveCsMemberShip(CsMemberShip csMemberShip)
	{
		csMemberShip = custom(csMemberShip);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsMemberShip", csMemberShip));
		csMemberShip = this.GetCsMemberShipById(id);
		MemCache.setValue(CsMemberShip.class,id, csMemberShip.getKeyValue());
		return Trigger.on(CsMemberShip.class,Trigger.SAVE,csMemberShip);	
	}

	

	/**
	 * 更新会员关系
	 * @param csMemberShip
	 */
	public void updateCsMemberShip(CsMemberShip csMemberShip)
	{
		csMemberShip = custom(csMemberShip);
		this.getSqlMapClientTemplate().update("updateCsMemberShip", csMemberShip);
		csMemberShip = this.GetCsMemberShipById(csMemberShip.getCsmsId());
		if(csMemberShip!=null){
			MemCache.setValue(CsMemberShip.class,csMemberShip.getCsmsId(), csMemberShip.getKeyValue());
		}
		Trigger.on(CsMemberShip.class,Trigger.UPDATE,csMemberShip);
	}
	/**
	 * 更新会员关系非空字段
	 * @param csMemberShip
	 */
	public void updateCsMemberShip$NotNull(CsMemberShip csMemberShip)
	{
		csMemberShip = custom(csMemberShip);
		this.getSqlMapClientTemplate().update("updateCsMemberShip$NotNull", csMemberShip);
		csMemberShip = this.GetCsMemberShipById(csMemberShip.getCsmsId());
		if(csMemberShip!=null){
			MemCache.setValue(CsMemberShip.class,csMemberShip.getCsmsId(), csMemberShip.getKeyValue());
		}
		Trigger.on(CsMemberShip.class,Trigger.UPDATE,csMemberShip);
	}

	/**
	 * 根据ID删除一个会员关系
	 * @param id
	 */
	public void deleteCsMemberShipById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsMemberShip.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsMemberShipById", params);
		MemCache.deleteValue(CsMemberShip.class, id);
	}
	/**
	 * 根据ID逻辑删除会员关系 
	 * @param id
	 * @return
	 */
	public void removeCsMemberShipById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsMemberShip.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsMemberShipById", params);
		MemCache.deleteValue(CsMemberShip.class, id);
	}
	/**
	 * 根据条件更新会员关系
	 * @param values
	 * @param params
	 */
	public void updateCsMemberShipByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsMemberShipCount(params)>255){
			MemCache.clearValue(CsMemberShip.class);
		}else{
			params.put("eval","csms_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsMemberShipEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsMemberShip.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsMemberShipBy", allParams);
		Trigger.on(CsMemberShip.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除会员关系
	 * @param params
	 */
	public void deleteCsMemberShipByConfirm(Map params){
		Trigger.on(CsMemberShip.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsMemberShipCount(params)>255){
			MemCache.clearValue(CsMemberShip.class);
		}else{
			params.put("eval","csms_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsMemberShipEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsMemberShip.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsMemberShipBy", params);
	}
	

}