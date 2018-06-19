package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsCreditCardDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCreditCard;
import com.lazy3q.web.helper.$;

/**
 * 信用卡的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsCreditCardDao extends SqlMapClientDaoSupport implements ICsCreditCardDao
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
	private CsCreditCard custom(CsCreditCard params){
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
			"csccRemark"			
			,"csccData"			
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
	 * 获取所有信用卡
	 * @return
	 */	
	public List<CsCreditCard> getCsCreditCardList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsCreditCard> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsCreditCardList", params);
		
		return list;
	}
	
	
	/**
	 * 获取信用卡统计
	 * @return
	 */
	public List getCsCreditCardStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsCreditCardStats", params);
	}

	/**
	 * 获取信用卡分页
	 * @return
	 */
	public Page getCsCreditCardPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsCreditCardCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsCreditCardPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取信用卡数量
	 * @return
	 */	
	public Long getCsCreditCardCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCreditCardEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取信用卡自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsCreditCardEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCreditCardEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取信用卡
	 * @param params
	 * @return
	 */
	public CsCreditCard getCsCreditCard(Map params)
	{		
		params = custom(params);
			
		List<CsCreditCard> list=this.getCsCreditCardList(params,1);
		CsCreditCard csCreditCard = list.isEmpty()?null:list.get(0);
		
		return csCreditCard;
	}
		
	/**
	 * 根据ID取信用卡
	 * @param id
	 * @return
	 */
	public CsCreditCard getCsCreditCardById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsCreditCard csCreditCard = null;
		if(csCreditCard!=null)
			return csCreditCard;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csCreditCard = (CsCreditCard) this.getSqlMapClientTemplate().queryForObject("getCsCreditCardById",params);
		if(csCreditCard!=null)
			MemCache.setValue(CsCreditCard.class,id, csCreditCard.getKeyValue());
		return csCreditCard;
	}
	
	/**
	 * 根据ID取信用卡(不缓存)
	 * @param id
	 * @return
	 */
	public CsCreditCard GetCsCreditCardById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsCreditCard) this.getSqlMapClientTemplate().queryForObject("getCsCreditCardById",params);
	}
	

	/**
	 * 保存信用卡
	 * @param csCreditCard
	 */
	public CsCreditCard saveCsCreditCard(CsCreditCard csCreditCard)
	{
		csCreditCard = custom(csCreditCard);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsCreditCard", csCreditCard));
		csCreditCard = this.GetCsCreditCardById(id);
		MemCache.setValue(CsCreditCard.class,id, csCreditCard.getKeyValue());
		return Trigger.on(CsCreditCard.class,Trigger.SAVE,csCreditCard);	
	}

	

	/**
	 * 更新信用卡
	 * @param csCreditCard
	 */
	public void updateCsCreditCard(CsCreditCard csCreditCard)
	{
		csCreditCard = custom(csCreditCard);
		this.getSqlMapClientTemplate().update("updateCsCreditCard", csCreditCard);
		csCreditCard = this.GetCsCreditCardById(csCreditCard.getCsccId());
		if(csCreditCard!=null){
			MemCache.setValue(CsCreditCard.class,csCreditCard.getCsccId(), csCreditCard.getKeyValue());
		}
		Trigger.on(CsCreditCard.class,Trigger.UPDATE,csCreditCard);
	}
	/**
	 * 更新信用卡非空字段
	 * @param csCreditCard
	 */
	public void updateCsCreditCard$NotNull(CsCreditCard csCreditCard)
	{
		csCreditCard = custom(csCreditCard);
		this.getSqlMapClientTemplate().update("updateCsCreditCard$NotNull", csCreditCard);
		csCreditCard = this.GetCsCreditCardById(csCreditCard.getCsccId());
		if(csCreditCard!=null){
			MemCache.setValue(CsCreditCard.class,csCreditCard.getCsccId(), csCreditCard.getKeyValue());
		}
		Trigger.on(CsCreditCard.class,Trigger.UPDATE,csCreditCard);
	}

	/**
	 * 根据ID删除一个信用卡
	 * @param id
	 */
	public void deleteCsCreditCardById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsCreditCard.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsCreditCardById", params);
		MemCache.deleteValue(CsCreditCard.class, id);
	}
	/**
	 * 根据ID逻辑删除信用卡 
	 * @param id
	 * @return
	 */
	public void removeCsCreditCardById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsCreditCard.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsCreditCardById", params);
		MemCache.deleteValue(CsCreditCard.class, id);
	}
	/**
	 * 根据条件更新信用卡
	 * @param values
	 * @param params
	 */
	public void updateCsCreditCardByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsCreditCardCount(params)>255){
			MemCache.clearValue(CsCreditCard.class);
		}else{
			params.put("eval","cscc_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCreditCardEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCreditCard.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsCreditCardBy", allParams);
		Trigger.on(CsCreditCard.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除信用卡
	 * @param params
	 */
	public void deleteCsCreditCardByConfirm(Map params){
		Trigger.on(CsCreditCard.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsCreditCardCount(params)>255){
			MemCache.clearValue(CsCreditCard.class);
		}else{
			params.put("eval","cscc_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCreditCardEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCreditCard.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsCreditCardBy", params);
	}
	

}