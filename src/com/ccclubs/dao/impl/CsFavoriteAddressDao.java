package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsFavoriteAddressDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsFavoriteAddress;
import com.lazy3q.web.helper.$;

/**
 * 会员常用地址的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsFavoriteAddressDao extends SqlMapClientDaoSupport implements ICsFavoriteAddressDao
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
	private CsFavoriteAddress custom(CsFavoriteAddress params){
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
			"csmMarket"			
			,"csmTag"			
			,"csmMark"			
			,"csmVisitFlag"			
			,"csmRemark"			
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
	 * 获取所有会员常用地址
	 * @return
	 */	
	public List<CsFavoriteAddress> getCsFavoriteAddressList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsFavoriteAddress> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsFavoriteAddressList", params);
		
		return list;
	}
	
	
	/**
	 * 获取会员常用地址统计
	 * @return
	 */
	public List getCsFavoriteAddressStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsFavoriteAddressStats", params);
	}

	/**
	 * 获取会员常用地址分页
	 * @return
	 */
	public Page getCsFavoriteAddressPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsFavoriteAddressCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsFavoriteAddressPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取会员常用地址数量
	 * @return
	 */	
	public Long getCsFavoriteAddressCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsFavoriteAddressEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取会员常用地址自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsFavoriteAddressEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsFavoriteAddressEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取会员常用地址
	 * @param params
	 * @return
	 */
	public CsFavoriteAddress getCsFavoriteAddress(Map params)
	{		
		params = custom(params);
			
		List<CsFavoriteAddress> list=this.getCsFavoriteAddressList(params,1);
		CsFavoriteAddress csFavoriteAddress = list.isEmpty()?null:list.get(0);
		
		return csFavoriteAddress;
	}
		
	/**
	 * 根据ID取会员常用地址
	 * @param id
	 * @return
	 */
	public CsFavoriteAddress getCsFavoriteAddressById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsFavoriteAddress csFavoriteAddress = null;
		if(csFavoriteAddress!=null)
			return csFavoriteAddress;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csFavoriteAddress = (CsFavoriteAddress) this.getSqlMapClientTemplate().queryForObject("getCsFavoriteAddressById",params);
		if(csFavoriteAddress!=null)
			MemCache.setValue(CsFavoriteAddress.class,id, csFavoriteAddress.getKeyValue());
		return csFavoriteAddress;
	}
	
	/**
	 * 根据ID取会员常用地址(不缓存)
	 * @param id
	 * @return
	 */
	public CsFavoriteAddress GetCsFavoriteAddressById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsFavoriteAddress) this.getSqlMapClientTemplate().queryForObject("getCsFavoriteAddressById",params);
	}
	

	/**
	 * 保存会员常用地址
	 * @param csFavoriteAddress
	 */
	public CsFavoriteAddress saveCsFavoriteAddress(CsFavoriteAddress csFavoriteAddress)
	{
		csFavoriteAddress = custom(csFavoriteAddress);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsFavoriteAddress", csFavoriteAddress));
		csFavoriteAddress = this.GetCsFavoriteAddressById(id);
		MemCache.setValue(CsFavoriteAddress.class,id, csFavoriteAddress.getKeyValue());
		return Trigger.on(CsFavoriteAddress.class,Trigger.SAVE,csFavoriteAddress);	
	}

	

	/**
	 * 更新会员常用地址
	 * @param csFavoriteAddress
	 */
	public void updateCsFavoriteAddress(CsFavoriteAddress csFavoriteAddress)
	{
		csFavoriteAddress = custom(csFavoriteAddress);
		this.getSqlMapClientTemplate().update("updateCsFavoriteAddress", csFavoriteAddress);
		csFavoriteAddress = this.GetCsFavoriteAddressById(csFavoriteAddress.getCsfaId());
		if(csFavoriteAddress!=null){
			MemCache.setValue(CsFavoriteAddress.class,csFavoriteAddress.getCsfaId(), csFavoriteAddress.getKeyValue());
		}
		Trigger.on(CsFavoriteAddress.class,Trigger.UPDATE,csFavoriteAddress);
	}
	/**
	 * 更新会员常用地址非空字段
	 * @param csFavoriteAddress
	 */
	public void updateCsFavoriteAddress$NotNull(CsFavoriteAddress csFavoriteAddress)
	{
		csFavoriteAddress = custom(csFavoriteAddress);
		this.getSqlMapClientTemplate().update("updateCsFavoriteAddress$NotNull", csFavoriteAddress);
		csFavoriteAddress = this.GetCsFavoriteAddressById(csFavoriteAddress.getCsfaId());
		if(csFavoriteAddress!=null){
			MemCache.setValue(CsFavoriteAddress.class,csFavoriteAddress.getCsfaId(), csFavoriteAddress.getKeyValue());
		}
		Trigger.on(CsFavoriteAddress.class,Trigger.UPDATE,csFavoriteAddress);
	}

	/**
	 * 根据ID删除一个会员常用地址
	 * @param id
	 */
	public void deleteCsFavoriteAddressById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsFavoriteAddress.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsFavoriteAddressById", params);
		MemCache.deleteValue(CsFavoriteAddress.class, id);
	}
	/**
	 * 根据ID逻辑删除会员常用地址 
	 * @param id
	 * @return
	 */
	public void removeCsFavoriteAddressById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsFavoriteAddress.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsFavoriteAddressById", params);
		MemCache.deleteValue(CsFavoriteAddress.class, id);
	}
	/**
	 * 根据条件更新会员常用地址
	 * @param values
	 * @param params
	 */
	public void updateCsFavoriteAddressByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsFavoriteAddressCount(params)>255){
			MemCache.clearValue(CsFavoriteAddress.class);
		}else{
			params.put("eval","csm_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsFavoriteAddressEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsFavoriteAddress.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsFavoriteAddressBy", allParams);
		Trigger.on(CsFavoriteAddress.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除会员常用地址
	 * @param params
	 */
	public void deleteCsFavoriteAddressByConfirm(Map params){
		Trigger.on(CsFavoriteAddress.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsFavoriteAddressCount(params)>255){
			MemCache.clearValue(CsFavoriteAddress.class);
		}else{
			params.put("eval","csm_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsFavoriteAddressEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsFavoriteAddress.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsFavoriteAddressBy", params);
	}
	

}