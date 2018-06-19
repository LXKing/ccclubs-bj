package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsInvoiceDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsInvoice;
import com.lazy3q.web.helper.$;

/**
 * 发票记录的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsInvoiceDao extends SqlMapClientDaoSupport implements ICsInvoiceDao
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
	private CsInvoice custom(CsInvoice params){
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
			"csiTitle"			
			,"csiTariffNo"			
			,"csiAddress"			
			,"csiRemark"			
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
	 * 获取所有发票记录
	 * @return
	 */	
	public List<CsInvoice> getCsInvoiceList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsInvoice> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsInvoiceList", params);
		
		return list;
	}
	
	
	/**
	 * 获取发票记录统计
	 * @return
	 */
	public List getCsInvoiceStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsInvoiceStats", params);
	}

	/**
	 * 获取发票记录分页
	 * @return
	 */
	public Page getCsInvoicePage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsInvoiceCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsInvoicePage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取发票记录数量
	 * @return
	 */	
	public Long getCsInvoiceCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsInvoiceEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取发票记录自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsInvoiceEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsInvoiceEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取发票记录
	 * @param params
	 * @return
	 */
	public CsInvoice getCsInvoice(Map params)
	{		
		params = custom(params);
			
		List<CsInvoice> list=this.getCsInvoiceList(params,1);
		CsInvoice csInvoice = list.isEmpty()?null:list.get(0);
		
		return csInvoice;
	}
		
	/**
	 * 根据ID取发票记录
	 * @param id
	 * @return
	 */
	public CsInvoice getCsInvoiceById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsInvoice csInvoice = null;
		if(csInvoice!=null)
			return csInvoice;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csInvoice = (CsInvoice) this.getSqlMapClientTemplate().queryForObject("getCsInvoiceById",params);
		if(csInvoice!=null)
			MemCache.setValue(CsInvoice.class,id, csInvoice.getKeyValue());
		return csInvoice;
	}
	
	/**
	 * 根据ID取发票记录(不缓存)
	 * @param id
	 * @return
	 */
	public CsInvoice GetCsInvoiceById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsInvoice) this.getSqlMapClientTemplate().queryForObject("getCsInvoiceById",params);
	}
	

	/**
	 * 保存发票记录
	 * @param csInvoice
	 */
	public CsInvoice saveCsInvoice(CsInvoice csInvoice)
	{
		csInvoice = custom(csInvoice);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsInvoice", csInvoice));
		csInvoice = this.GetCsInvoiceById(id);
		MemCache.setValue(CsInvoice.class,id, csInvoice.getKeyValue());
		return Trigger.on(CsInvoice.class,Trigger.SAVE,csInvoice);	
	}

	

	/**
	 * 更新发票记录
	 * @param csInvoice
	 */
	public void updateCsInvoice(CsInvoice csInvoice)
	{
		csInvoice = custom(csInvoice);
		this.getSqlMapClientTemplate().update("updateCsInvoice", csInvoice);
		csInvoice = this.GetCsInvoiceById(csInvoice.getCsiId());
		if(csInvoice!=null){
			MemCache.setValue(CsInvoice.class,csInvoice.getCsiId(), csInvoice.getKeyValue());
		}
		Trigger.on(CsInvoice.class,Trigger.UPDATE,csInvoice);
	}
	/**
	 * 更新发票记录非空字段
	 * @param csInvoice
	 */
	public void updateCsInvoice$NotNull(CsInvoice csInvoice)
	{
		csInvoice = custom(csInvoice);
		this.getSqlMapClientTemplate().update("updateCsInvoice$NotNull", csInvoice);
		csInvoice = this.GetCsInvoiceById(csInvoice.getCsiId());
		if(csInvoice!=null){
			MemCache.setValue(CsInvoice.class,csInvoice.getCsiId(), csInvoice.getKeyValue());
		}
		Trigger.on(CsInvoice.class,Trigger.UPDATE,csInvoice);
	}

	/**
	 * 根据ID删除一个发票记录
	 * @param id
	 */
	public void deleteCsInvoiceById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsInvoice.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsInvoiceById", params);
		MemCache.deleteValue(CsInvoice.class, id);
	}
	/**
	 * 根据ID逻辑删除发票记录 
	 * @param id
	 * @return
	 */
	public void removeCsInvoiceById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsInvoice.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsInvoiceById", params);
		MemCache.deleteValue(CsInvoice.class, id);
	}
	/**
	 * 根据条件更新发票记录
	 * @param values
	 * @param params
	 */
	public void updateCsInvoiceByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsInvoiceCount(params)>255){
			MemCache.clearValue(CsInvoice.class);
		}else{
			params.put("eval","csi_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsInvoiceEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsInvoice.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsInvoiceBy", allParams);
		Trigger.on(CsInvoice.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除发票记录
	 * @param params
	 */
	public void deleteCsInvoiceByConfirm(Map params){
		Trigger.on(CsInvoice.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsInvoiceCount(params)>255){
			MemCache.clearValue(CsInvoice.class);
		}else{
			params.put("eval","csi_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsInvoiceEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsInvoice.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsInvoiceBy", params);
	}
	

}