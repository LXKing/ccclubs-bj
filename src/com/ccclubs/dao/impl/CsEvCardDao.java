package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsEvCardDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsEvCard;
import com.lazy3q.web.helper.$;

/**
 * 会员卡的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsEvCardDao extends SqlMapClientDaoSupport implements ICsEvCardDao
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
	private CsEvCard custom(CsEvCard params){
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
			"csecNumber"			
			,"csecRemark"			
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
	 * 获取所有会员卡
	 * @return
	 */	
	public List<CsEvCard> getCsEvCardList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsEvCard> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsEvCardList", params);
		
		return list;
	}
	
	
	/**
	 * 获取会员卡统计
	 * @return
	 */
	public List getCsEvCardStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsEvCardStats", params);
	}

	/**
	 * 获取会员卡分页
	 * @return
	 */
	public Page getCsEvCardPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsEvCardCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsEvCardPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取会员卡数量
	 * @return
	 */	
	public Long getCsEvCardCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsEvCardEval", params);
		return (Long)result.get("eval");
	}
	
	public Long getCount(Map params)
    {
        params = custom(params);
        params.put("eval","count(*)");
        Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsEvCardEval", params);
        return (Long)result.get("eval");
    }
	
	/**
	 * 获取会员卡自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsEvCardEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsEvCardEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取会员卡
	 * @param params
	 * @return
	 */
	public CsEvCard getCsEvCard(Map params)
	{		
		params = custom(params);
			
		List<CsEvCard> list=this.getCsEvCardList(params,1);
		CsEvCard csEvCard = list.isEmpty()?null:list.get(0);
		
		return csEvCard;
	}
		
	/**
	 * 根据ID取会员卡
	 * @param id
	 * @return
	 */
	public CsEvCard getCsEvCardById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsEvCard csEvCard = null;
		if(csEvCard!=null)
			return csEvCard;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csEvCard = (CsEvCard) this.getSqlMapClientTemplate().queryForObject("getCsEvCardById",params);
		if(csEvCard!=null)
			MemCache.setValue(CsEvCard.class,id, csEvCard.getKeyValue());
		return csEvCard;
	}
	
	/**
	 * 根据ID取会员卡(不缓存)
	 * @param id
	 * @return
	 */
	public CsEvCard GetCsEvCardById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsEvCard) this.getSqlMapClientTemplate().queryForObject("getCsEvCardById",params);
	}
	

	/**
	 * 保存会员卡
	 * @param csEvCard
	 */
	public CsEvCard saveCsEvCard(CsEvCard csEvCard)
	{
		csEvCard = custom(csEvCard);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsEvCard", csEvCard));
		csEvCard = this.GetCsEvCardById(id);
		MemCache.setValue(CsEvCard.class,id, csEvCard.getKeyValue());
		return Trigger.on(CsEvCard.class,Trigger.SAVE,csEvCard);	
	}

	

	/**
	 * 更新会员卡
	 * @param csEvCard
	 */
	public void updateCsEvCard(CsEvCard csEvCard)
	{
		csEvCard = custom(csEvCard);
		this.getSqlMapClientTemplate().update("updateCsEvCard", csEvCard);
		csEvCard = this.GetCsEvCardById(csEvCard.getCsecId());
		if(csEvCard!=null){
			MemCache.setValue(CsEvCard.class,csEvCard.getCsecId(), csEvCard.getKeyValue());
		}
		Trigger.on(CsEvCard.class,Trigger.UPDATE,csEvCard);
	}
	/**
	 * 更新会员卡非空字段
	 * @param csEvCard
	 */
	public void updateCsEvCard$NotNull(CsEvCard csEvCard)
	{
		csEvCard = custom(csEvCard);
		this.getSqlMapClientTemplate().update("updateCsEvCard$NotNull", csEvCard);
		csEvCard = this.GetCsEvCardById(csEvCard.getCsecId());
		if(csEvCard!=null){
			MemCache.setValue(CsEvCard.class,csEvCard.getCsecId(), csEvCard.getKeyValue());
		}
		Trigger.on(CsEvCard.class,Trigger.UPDATE,csEvCard);
	}

	/**
	 * 根据ID删除一个会员卡
	 * @param id
	 */
	public void deleteCsEvCardById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsEvCard.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsEvCardById", params);
		MemCache.deleteValue(CsEvCard.class, id);
	}
	/**
	 * 根据ID逻辑删除会员卡 
	 * @param id
	 * @return
	 */
	public void removeCsEvCardById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsEvCard.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsEvCardById", params);
		MemCache.deleteValue(CsEvCard.class, id);
	}
	/**
	 * 根据条件更新会员卡
	 * @param values
	 * @param params
	 */
	public void updateCsEvCardByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsEvCardCount(params)>255){
			MemCache.clearValue(CsEvCard.class);
		}else{
			params.put("eval","csec_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsEvCardEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsEvCard.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsEvCardBy", allParams);
		Trigger.on(CsEvCard.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除会员卡
	 * @param params
	 */
	public void deleteCsEvCardByConfirm(Map params){
		Trigger.on(CsEvCard.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsEvCardCount(params)>255){
			MemCache.clearValue(CsEvCard.class);
		}else{
			params.put("eval","csec_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsEvCardEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsEvCard.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsEvCardBy", params);
	}
	

}