package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsOutletsDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsOutlets;
import com.lazy3q.web.helper.$;

/**
 * 网点的Dao实现
 * 
 * @author 飞啊飘啊 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsOutletsDao extends SqlMapClientDaoSupport implements
		ICsOutletsDao {
	/************ LAZY3Q_DEFINE_CODE ************/
	/************ LAZY3Q_DEFINE_CODE ************/

	/**
	 * 自定义加工处理params,可以添加表别名、联表查询等操作 可在此动态设置数据源，以达到分库分表的目的
	 * 
	 * @param params
	 * @return
	 */
	private Map custom(Map params) {
		/************ LAZY3Q_DAO_CUSTOM ************/
		/************ LAZY3Q_DAO_CUSTOM ************/

		return params;
	}

	/**
	 * 自定义加工处理params,可以添加表别名、联表查询等操作 可在此动态设置数据源，以达到分库分表的目的
	 * 
	 * @param params
	 * @return
	 */
	private CsOutlets custom(CsOutlets params) {
		/************ LAZY3Q_CUSTOM_MODEL ************/
		/************ LAZY3Q_CUSTOM_MODEL ************/

		return params;
	}

	/************ LAZY3Q_DEFINE_FUNCTION ************/
	/************ LAZY3Q_DEFINE_FUNCTION ************/

	/**
	 * 为某些需要加like的key加%
	 * 
	 * @param params
	 * @return
	 */
	private Map likeKey(Map params) {
		// 为模糊匹配的varchar类型字段加上%号
		String[] likes = {"csoName", "csoProfile" };
		for (String key : likes) {
			String value = (String) params.get(key);
			if (value == null || value.trim().equals(""))
				params.remove(key);
			else
				params.put(key, "%" + value.replaceAll("%", "") + "%");
		}
		return params;
	}

	/**
	 * 获取所有网点
	 * 
	 * @return
	 */
	public List<CsOutlets> getCsOutletsList(Map params, Integer size) {
		params = custom(params);
		params.put("SIZE", size);// 放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);

		List<CsOutlets> list = new ArrayList();
		Boolean bInReject = false;
		// 如果查询参数中有下列字段中任意之一，则不作缓存
		for (String reject : new String[] { "In-Reject", "csoUntil",
				"csoUntilStart", "csoUntilEnd", "csoExpire", "csoExpireStart",
				"csoExpireEnd", "csoPileTime", "csoPileTimeStart",
				"csoPileTimeEnd", "csoOnlineTime", "csoOnlineTimeStart",
				"csoOnlineTimeEnd", "csoUpdateTime", "csoUpdateTimeStart",
				"csoUpdateTimeEnd", "csoAddTime", "csoAddTimeStart",
				"csoAddTimeEnd" }) {
			if (params.get(reject) != null) {
				bInReject = true;
				break;
			}
		}
		if (!bInReject) {
			String sParam = MemCache.getValue(CsOutlets.M.class, md5);
			List caches = MemCache.getObject(CsOutlets.M.class, md5);
			if (caches != null) {
				if ($.equals(sParam, json)) {
					for (Object item : caches) {
						CsOutlets cacheObject = MemCache.getObject(
								CsOutlets.class, Long.parseLong("" + item));
						if (cacheObject == null) {// 如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(CsOutlets.M.class, md5);
							MemCache.deleteValue(CsOutlets.M.class, md5);
							break;
						} else {
							list.add(cacheObject);
						}
					}
					return list;
				} else {
					$.trace("缓存冲突", sParam, json);
					MemCache.deleteObject(CsOutlets.M.class, md5);
					MemCache.deleteValue(CsOutlets.M.class, md5);
				}
			}
		}

		if (size != null && size != -1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsOutletsList",
				params);
		if (!bInReject && size < 1024) {
			List caches = new ArrayList();
			for (CsOutlets csOutlets : list) {
				caches.add(csOutlets.getCsoId());
				MemCache.setObject(CsOutlets.class, csOutlets.getCsoId(),
						csOutlets);
			}
			MemCache.setObject(CsOutlets.M.class, md5, caches,
					MemCache.defaultSeconds());
			MemCache.setValue(CsOutlets.M.class, md5, json,
					MemCache.defaultSeconds());
		}

		return list;
	}

	/**
	 * 获取网点统计
	 * 
	 * @return
	 */
	public List getCsOutletsStats(Map params, Map<String, Object> groups,
			Map<String, Object> sums) {
		params = custom(params);
		params = likeKey(params);

		String groupSql = "";
		for (Map.Entry<String, Object> item : groups.entrySet())
			if (!$.empty(item.getKey()))
				groupSql += (groupSql.equals("") ? "" : ",")
						+ item.getValue().toString()
								.replace("{key}", item.getKey());
		if (groups.get("definex") != null)
			groupSql += (groupSql.equals("") ? "" : ",")
					+ groups.get("definex");
		if (!groupSql.equals(""))
			groupSql = "group by " + groupSql;
		if (params.get("desc") != null)
			groupSql += " order by {key} desc ".replace("{key}",
					params.get("desc").toString());
		else if (params.get("asc") != null)
			groupSql += " order by {key} asc ".replace("{key}",
					params.get("asc").toString());
		if (!groupSql.equals(""))
			groupSql += " limit 512 ";

		String selectSql = "count(*) as 'count'";
		for (Map.Entry<String, Object> item : sums.entrySet())
			if (!$.empty(item.getKey()))
				selectSql += (selectSql.equals("") ? "" : ",")
						+ item.getValue().toString()
								.replace("{key}", item.getKey())
						+ (item.getValue().toString().indexOf(" as ") == -1 ? (" as " + item
								.getKey()) : "");
		for (Map.Entry<String, Object> item : groups.entrySet())
			if (!$.empty(item.getKey()))
				selectSql += (selectSql.equals("") ? "" : ",") + item.getKey()
						+ " as '" + item.getKey() + "'";
		if (sums.get("definex") != null)
			selectSql += (selectSql.equals("") ? "" : ",")
					+ sums.get("definex");

		params.put("select", selectSql);
		params.put("group", groupSql);
		return this.getSqlMapClientTemplate().queryForList("getCsOutletsStats",
				params);
	}

	/**
	 * 获取网点分页
	 * 
	 * @return
	 */
	public Page getCsOutletsPage(int page, int size, Map params) {
		params = custom(params);
		params = likeKey(params);

		if (size < 1)
			size = 20;

		// 获取分页总数
		Long count = getCsOutletsCount(params);
		// 取分页的队列

		params.put("limit", size);
		params.put("offset", (int) Page.test(page, size, count));
		List list = this.getSqlMapClientTemplate().queryForList(
				"getCsOutletsPage", params);
		// 返回一个包装分页对象
		return new Page(page, size, count, list);
	}

	/**
	 * 获取网点数量
	 * 
	 * @return
	 */
	public Long getCsOutletsCount(Map params) {
		params = custom(params);
		params = likeKey(params);
		params.put("eval", "count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject(
				"getCsOutletsEval", params);
		return (Long) result.get("eval");
	}

	/**
	 * 获取网点自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * 
	 * @return
	 */
	public <T> T getCsOutletsEval(String eval, Map params) {
		params = custom(params);
		params = likeKey(params);
		params.put("eval", eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject(
				"getCsOutletsEval", params);
		return (T) result.get("eval");
	}

	public <T> T getCsOutletsEvalList(String eval, Map params) {
		params = custom(params);
		params = likeKey(params);
		params.put("eval", eval);
		return (T) this.getSqlMapClientTemplate().queryForList(
				"getCsOutletsEval", params);
	}

	/**
	 * 根据查询条件取网点
	 * 
	 * @param params
	 * @return
	 */
	public CsOutlets getCsOutlets(Map params) {
		params = custom(params);

		List<CsOutlets> list = this.getCsOutletsList(params, 1);
		CsOutlets csOutlets = list.isEmpty() ? null : list.get(0);

		return csOutlets;
	}

	/**
	 * 根据ID取网点
	 * 
	 * @param id
	 * @return
	 */
	public CsOutlets getCsOutletsById(Long id) {
		if (id == null || id.longValue() == 0)
			return null;

		CsOutlets csOutlets = MemCache.getObject(CsOutlets.class, id);
		if (csOutlets != null)
			return csOutlets;

		Map params = new HashMap();
		params.put("id", id);
		params = custom(params);
		csOutlets = (CsOutlets) this.getSqlMapClientTemplate().queryForObject(
				"getCsOutletsById", params);
		if (csOutlets != null)
			MemCache.setValue(CsOutlets.class, id, csOutlets.getKeyValue());
		if (csOutlets != null)
			MemCache.setObject(CsOutlets.class, id, csOutlets);
		return csOutlets;
	}

	/**
	 * 根据ID取网点(不缓存)
	 * 
	 * @param id
	 * @return
	 */
	public CsOutlets GetCsOutletsById(Long id) {
		Map params = new HashMap();
		params.put("id", id);
		params = custom(params);
		return (CsOutlets) this.getSqlMapClientTemplate().queryForObject(
				"getCsOutletsById", params);
	}

	/**
	 * 保存网点
	 * 
	 * @param csOutlets
	 */
	public CsOutlets saveCsOutlets(CsOutlets csOutlets) {
		csOutlets = custom(csOutlets);
		Long id = $.parseLong(this.getSqlMapClientTemplate().insert(
				"saveCsOutlets", csOutlets));
		csOutlets = this.GetCsOutletsById(id);
		MemCache.setValue(CsOutlets.class, id, csOutlets.getKeyValue());
		MemCache.setObject(CsOutlets.class, id, csOutlets);
		MemCache.clearObject(CsOutlets.M.class);
		MemCache.clearValue(CsOutlets.M.class);
		return Trigger.on(CsOutlets.class, Trigger.SAVE, csOutlets);
	}

	/**
	 * 更新网点
	 * 
	 * @param csOutlets
	 */
	public void updateCsOutlets(CsOutlets csOutlets) {
		csOutlets = custom(csOutlets);
		this.getSqlMapClientTemplate().update("updateCsOutlets", csOutlets);
		csOutlets = this.GetCsOutletsById(csOutlets.getCsoId());
		if (csOutlets != null) {
			MemCache.setValue(CsOutlets.class, csOutlets.getCsoId(),
					csOutlets.getKeyValue());
			MemCache.setObject(CsOutlets.class, csOutlets.getCsoId(), csOutlets);
		}
		MemCache.clearObject(CsOutlets.M.class);
		MemCache.clearValue(CsOutlets.M.class);
		Trigger.on(CsOutlets.class, Trigger.UPDATE, csOutlets);
	}

	/**
	 * 更新网点非空字段
	 * 
	 * @param csOutlets
	 */
	public void updateCsOutlets$NotNull(CsOutlets csOutlets) {
		csOutlets = custom(csOutlets);
		this.getSqlMapClientTemplate().update("updateCsOutlets$NotNull",
				csOutlets);
		csOutlets = this.GetCsOutletsById(csOutlets.getCsoId());
		if (csOutlets != null) {
			MemCache.setValue(CsOutlets.class, csOutlets.getCsoId(),
					csOutlets.getKeyValue());
			MemCache.setObject(CsOutlets.class, csOutlets.getCsoId(), csOutlets);
		}
		MemCache.clearObject(CsOutlets.M.class);
		MemCache.clearValue(CsOutlets.M.class);
		Trigger.on(CsOutlets.class, Trigger.UPDATE, csOutlets);
	}

	/**
	 * 根据ID删除一个网点
	 * 
	 * @param id
	 */
	public void deleteCsOutletsById(Long id) {
		Map params = new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsOutlets.class, Trigger.DELETE, params);
		this.getSqlMapClientTemplate().delete("deleteCsOutletsById", params);
		MemCache.deleteValue(CsOutlets.class, id);
		MemCache.deleteObject(CsOutlets.class, id);
		MemCache.clearObject(CsOutlets.M.class);
		MemCache.clearValue(CsOutlets.M.class);
	}

	/**
	 * 根据ID逻辑删除网点
	 * 
	 * @param id
	 * @return
	 */
	public void removeCsOutletsById(Long id) {
		Map params = new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsOutlets.class, Trigger.DELETE, params);
		this.getSqlMapClientTemplate().update("removeCsOutletsById", params);
		MemCache.deleteValue(CsOutlets.class, id);
		MemCache.deleteObject(CsOutlets.class, id);
		MemCache.clearObject(CsOutlets.M.class);
		MemCache.clearValue(CsOutlets.M.class);
	}

	/**
	 * 根据条件更新网点
	 * 
	 * @param values
	 * @param params
	 */
	public void updateCsOutletsByConfirm(Map values, Map params) {
		Map allParams = new HashMap();
		for (Map.Entry<String, Object> entry : ((Map<String, Object>) values)
				.entrySet())
			allParams.put("V_" + entry.getKey(), entry.getValue());
		for (Map.Entry<String, Object> entry : ((Map<String, Object>) params)
				.entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		// 如果更新条数大于255，则清空缓存
		if (this.getCsOutletsCount(params) > 255) {
			MemCache.clearValue(CsOutlets.class);
			MemCache.clearObject(CsOutlets.class);
		} else {
			params.put("eval", "cso_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate()
					.queryForList("getCsOutletsEval", params);
			for (Map map : result) {
				MemCache.deleteValue(CsOutlets.class, map.get("eval"));
				MemCache.deleteObject(CsOutlets.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsOutlets.M.class);
		MemCache.clearValue(CsOutlets.M.class);

		int rows = this.getSqlMapClientTemplate().update("updateCsOutletsBy",
				allParams);
		Trigger.on(CsOutlets.class, Trigger.UPDATE, allParams);
	}

	/**
	 * 根据条件删除网点
	 * 
	 * @param params
	 */
	public void deleteCsOutletsByConfirm(Map params) {
		Trigger.on(CsOutlets.class, Trigger.DELETE, params);
		// 如果更新条数大于255，则清空缓存
		if (this.getCsOutletsCount(params) > 255) {
			MemCache.clearValue(CsOutlets.class);
			MemCache.clearObject(CsOutlets.class);
		} else {
			params.put("eval", "cso_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate()
					.queryForList("getCsOutletsEval", params);
			for (Map map : result) {
				MemCache.deleteValue(CsOutlets.class, map.get("eval"));
				MemCache.deleteObject(CsOutlets.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsOutlets.M.class);
		MemCache.clearValue(CsOutlets.M.class);

		int rows = this.getSqlMapClientTemplate().update("deleteCsOutletsBy",
				params);
	}

}