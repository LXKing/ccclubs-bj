package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsOutsideQrDao;
import com.ccclubs.dao.ICsOutsideStatisticsDao;
import com.ccclubs.model.CsChannel;
import com.ccclubs.model.CsChannelIndex;
import com.ccclubs.model.CsGoods;
import com.ccclubs.model.CsOutsideStatistics;
import com.ccclubs.model.TbAbAffirm;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.util.Page;

@SuppressWarnings("unchecked")
public class CsOutsideStatisticsDao extends SqlMapClientDaoSupport implements ICsOutsideStatisticsDao{

	@Override
	public Page getCsOutsideStatisticsPage(int page, int size, Map params) {
		
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsOutsideStatisticsCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsOutsideStatisticsPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}

	
	@Override
	public CsOutsideStatistics getCsOutsideStatisticsById(Long id) {

		if(id==null || id.longValue()==0)
			return null;
	
		CsOutsideStatistics outsideStatistics = null;
		if(outsideStatistics!=null)
			return outsideStatistics;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		outsideStatistics = (CsOutsideStatistics) this.getSqlMapClientTemplate().queryForObject("getCsOutsideStatisticsById",params);
		if(outsideStatistics!=null)
			MemCache.setValue(CsOutsideStatistics.class,id, outsideStatistics.getKeyValue());
		return outsideStatistics;
	}


	@Override
	public void removeCsOutsideStatisticsById(Long id) {
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsOutsideStatistics.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsOutsideStatisticsById", params);
		MemCache.deleteValue(CsOutsideStatistics.class, id);
		MemCache.deleteObject(CsOutsideStatistics.class, id);
		MemCache.clearObject(CsOutsideStatistics.M.class);
		MemCache.clearValue(CsOutsideStatistics.M.class);
	}




	@Override
	public void deleteCsOutsideStatisticsById(Long id) {
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsOutsideStatistics.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsOutsideStatisticsById", params);
		MemCache.deleteValue(CsOutsideStatistics.class, id);
		MemCache.deleteObject(CsOutsideStatistics.class, id);
		MemCache.clearObject(CsOutsideStatistics.M.class);
		MemCache.clearValue(CsOutsideStatistics.M.class);
		
	}


	/**
	 * 获取异借确认数量
	 * @return
	 */	
	public Long getCsOutsideStatisticsCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsOutsideStatisticsEval", params);
		return (Long)result.get("eval");
	}


	/**
	 * 获取车辆违章自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsOutsideStatisticsEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsOutsideStatisticsEval", params);
		return (T)result.get("eval");
	}
	

	@Override
	public void updateCsOutsideStatisticsByConfirm(Map values, Map params) {
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsOutsideStatisticsCount(params)>255){
			MemCache.clearValue(CsOutsideStatistics.class);
			MemCache.clearObject(CsOutsideStatistics.class);
		}else{
			params.put("eval","csos_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsOutsideStatisticsEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsOutsideStatistics.class, map.get("eval"));
				MemCache.deleteObject(CsOutsideStatistics.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsOutsideStatistics.M.class);
		MemCache.clearValue(CsOutsideStatistics.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateCsOutsideStatisticsBy", allParams);//------------呆改
		Trigger.on(CsOutsideStatistics.class,Trigger.UPDATE,allParams);		
		
	}


	/**
	 * 获取所有访问渠道
	 * @return
	 */	
	public List<CsOutsideStatistics> getCsOutsideStatisticsList(Map params,Integer size){		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsOutsideStatistics> list = new ArrayList();
		Boolean bInReject = true;
		//如果查询参数中有下列字段中任意之一，则不作缓存
//		for(String reject:new String[]{"In-Reject","csosOpenDoorTime","csosCloseDoorTime","csosAddTime","csosUpdateTime","csosFinishTime"}){
//			if(params.get(reject)!=null){
//				bInReject = true;
//				break;
//			}
//		}
//		if(!bInReject){
//			String sParam = MemCache.getValue(CsOutsideStatistics.M.class, md5);
//			List caches = MemCache.getObject(CsOutsideStatistics.M.class, md5);
//			if(caches!=null){
//				if($.equals(sParam, json)){
//					for(Object item:caches){
//						CsOutsideStatistics cacheObject = MemCache.getObject(CsOutsideStatistics.class, Long.parseLong(""+item));
//						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
//							list = new ArrayList();
//							MemCache.deleteObject(CsOutsideStatistics.M.class, md5);
//							MemCache.deleteValue(CsOutsideStatistics.M.class, md5);
//							break;
//						}else{
//							list.add(cacheObject);
//						}
//					}
//					return list;
//				}else{
//					$.trace("缓存冲突",sParam,json);
//					MemCache.deleteObject(CsOutsideStatistics.M.class, md5);
//					MemCache.deleteValue(CsOutsideStatistics.M.class, md5);
//				}
//			}
//		}
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsOutsideStatisticsList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(CsOutsideStatistics csOutsideStatistics:list){
				caches.add(csOutsideStatistics.getCsosId());
				MemCache.setObject(CsOutsideStatistics.class,csOutsideStatistics.getCsosId(), csOutsideStatistics);
			}
			MemCache.setObject(CsOutsideStatistics.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(CsOutsideStatistics.M.class, md5, json, MemCache.defaultSeconds());
		}		
		return list;
	}
	
	@Override
	public CsOutsideStatistics getOutsideStatistics(Map params) {
		params = custom(params);
		List<CsOutsideStatistics> list=this.getCsOutsideStatisticsList(params,1);
		CsOutsideStatistics outsideStatistics = list.isEmpty()?null:list.get(0);
		return outsideStatistics;
	}

	
	
	/**
	 * 根据ID取数据(不缓存)
	 * @param id
	 * @return
	 */
	public CsOutsideStatistics GetCsOutsideStatisticsById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsOutsideStatistics) this.getSqlMapClientTemplate().queryForObject("getCsOutsideStatisticsById",params);
	}

	@Override
	public void updateCsOutsideStatistics$NotNull(CsOutsideStatistics csOutsideStatistics) {
		csOutsideStatistics = custom(csOutsideStatistics);
		this.getSqlMapClientTemplate().update("updateCsOutsideStatistics$NotNull", csOutsideStatistics);
		csOutsideStatistics = this.GetCsOutsideStatisticsById(csOutsideStatistics.getCsosId());
//		if(csOutsideStatistics!=null){
////			MemCache.setValue(CsOutsideStatistics.class,csOutsideStatistics.getCsosId(), csOutsideStatistics.getKeyValue());
//			MemCache.setObject(CsOutsideStatistics.class,csOutsideStatistics.getCsosId(),csOutsideStatistics);
//		}
//		MemCache.clearObject(CsOutsideStatistics.M.class);
//		MemCache.clearValue(CsOutsideStatistics.M.class);
//		Trigger.on(CsOutsideStatistics.class,Trigger.UPDATE,csOutsideStatistics);
	}


	/**
	 * 更新数据
	 */
	@Override
	public void updateCsOutsideStatistics(CsOutsideStatistics csOutsideStatistics) {
		csOutsideStatistics = custom(csOutsideStatistics);
		this.getSqlMapClientTemplate().update("updateCsOutsideStatistics", csOutsideStatistics);
		csOutsideStatistics = this.GetCsOutsideStatisticsById(csOutsideStatistics.getCsosId());
		if(csOutsideStatistics!=null){
			MemCache.setValue(CsOutsideStatistics.class,csOutsideStatistics.getCsosId(), csOutsideStatistics.getKeyValue());
			MemCache.setObject(CsOutsideStatistics.class,csOutsideStatistics.getCsosId(),csOutsideStatistics);
		}
		MemCache.clearObject(CsOutsideStatistics.M.class);
		MemCache.clearValue(CsOutsideStatistics.M.class);
		Trigger.on(CsOutsideStatistics.class,Trigger.UPDATE,csOutsideStatistics);
		
	}


	
	
	@Override
	public Page getCsOutsideStatisticsOnPage(int page, int size, Map params) {

		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsOutsideStatisticsCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsOutsideStatisticsOnPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}


	/**
	 * 保存数据
	 */
	@Override
	public CsOutsideStatistics saveCsOutsideStatistics(CsOutsideStatistics csOutsideStatistics) {
		csOutsideStatistics = custom(csOutsideStatistics);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsOutsideStatistics", csOutsideStatistics));
		csOutsideStatistics = this.GetCsOutsideStatisticsById(id);
		MemCache.setValue(CsOutsideStatistics.class,id, csOutsideStatistics.getKeyValue());
		MemCache.setObject(CsOutsideStatistics.class,id,csOutsideStatistics);
		MemCache.clearObject(CsOutsideStatistics.M.class);
		MemCache.clearValue(CsOutsideStatistics.M.class);
		return Trigger.on(CsOutsideStatistics.class,Trigger.SAVE,csOutsideStatistics);	
	}



	/**
	 * 车辆整备统计
	 */
	@Override
	public List getCsOutsideStatisticsStats(Map params, Map<String, Object> groups,Map<String, Object> sums) {
		
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
		return this.getSqlMapClientTemplate().queryForList("getCsOutsideStatisticsStats", params);
		
	}


	/**
	 * 自定义加工处理params,可以添加表别名、联表查询等操作
	 * 可在此动态设置数据源，以达到分库分表的目的
	 * @param params
	 * @return
	 */
	private CsOutsideStatistics custom(CsOutsideStatistics params){
		/************LAZY3Q_CUSTOM_MODEL************/
		/************LAZY3Q_CUSTOM_MODEL************/

		return params;
	}
	/**
	 * 为某些需要加like的key加%
	 * @param params
	 * @return
	 */
	private Map likeKey(Map params){
		//为模糊匹配的varchar类型字段加上%号
		String[] likes={
			"tbaaRemark"			
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
	
	
}
