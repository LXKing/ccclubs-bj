package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsOutletsDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.service.admin.ICsOutletsService;
import com.lazy3q.web.helper.$;

/**
 * 网点的Service实现
 * 
 * @author 飞啊飘啊
 */
public class CsOutletsService implements ICsOutletsService {
	ICsOutletsDao csOutletsDao;

	/**
	 * 获取所有网点
	 * 
	 * @return
	 */
	public List<CsOutlets> getCsOutletsList(Map params, Integer size) {
		return csOutletsDao.getCsOutletsList(params, size);
	}

	/**
	 * 获取网点统计
	 * 
	 * @return
	 */
	public List getCsOutletsStats(Map params, Map<String, Object> groups,
			Map<String, Object> sums) {
		return csOutletsDao.getCsOutletsStats(params, groups, sums);
	}

	/**
	 * 获取网点总数
	 * 
	 * @return
	 */
	public Long getCsOutletsCount(Map params) {
		return csOutletsDao.getCsOutletsCount(params);
	}

	/**
	 * 获取网点自定义求和表达式,比如求和:eval="sum(id)"
	 * 
	 * @return
	 */
	public <T> T getCsOutletsEval(String eval, Map params) {
		return csOutletsDao.getCsOutletsEval(eval, params);
	}

	/**
	 * 获取网点自定义求和表达式,比如求和:eval="sum(id),avg(id)"
	 * 
	 * @return
	 */
	@Override
	public <T> T getCsOutletsEvalList(String eval, Map params) {
		// TODO Auto-generated method stub
		return csOutletsDao.getCsOutletsEvalList(eval, params);
	}

	/**
	 * 获取网点分页
	 * 
	 * @return
	 */
	public Page getCsOutletsPage(int page, int size, Map params) {
		return csOutletsDao.getCsOutletsPage(page, size, params);
	}

	/**
	 * 根据查询条件取网点
	 * 
	 * @param params
	 * @return
	 */
	public CsOutlets getCsOutlets(Map params) {
		return csOutletsDao.getCsOutlets(params);
	}

	/**
	 * 根据ID取网点
	 * 
	 * @param id
	 * @return
	 */
	public CsOutlets getCsOutletsById(Long id) {
		return csOutletsDao.getCsOutletsById(id);
	}

	/**
	 * 保存网点
	 * 
	 * @param csOutlets
	 */
	public CsOutlets saveCsOutlets(CsOutlets csOutlets) {
		return csOutletsDao.saveCsOutlets(csOutlets);
	}

	/**
	 * 更新网点
	 * 
	 * @param csOutlets
	 */
	public void updateCsOutlets(CsOutlets csOutlets) {
		csOutletsDao.updateCsOutlets(csOutlets);
	}

	/**
	 * 更新网点非空字段
	 * 
	 * @param csOutlets
	 */
	public void updateCsOutlets$NotNull(CsOutlets csOutlets) {
		csOutletsDao.updateCsOutlets$NotNull(csOutlets);
	}

	/**
	 * 根据ID删除一个网点
	 * 
	 * @param id
	 */
	public void deleteCsOutletsById(Long id) {
		csOutletsDao.deleteCsOutletsById(id);
	}

	/**
	 * 根据ID逻辑删除一个网点
	 * 
	 * @param id
	 */
	public void removeCsOutletsById(Long id) {
		csOutletsDao.removeCsOutletsById(id);
	}

	/**
	 * 根据条件更新网点
	 * 
	 * @param values
	 * @param params
	 */
	public void updateCsOutletsByConfirm(Map values, Map params) {
		if (params.isEmpty())
			throw new RuntimeException("updateCsOutletsBy时无条件");
		csOutletsDao.updateCsOutletsByConfirm(values, params);
	}

	/**
	 * 根据条件删除网点
	 * 
	 * @param params
	 */
	public void deleteCsOutletsByConfirm(Map params) {
		if (params.isEmpty())
			throw new RuntimeException("deleteCsOutletsBy时无条件");
		csOutletsDao.deleteCsOutletsByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();
	}

	public ICsOutletsDao getCsOutletsDao() {
		return csOutletsDao;
	}

	public void setCsOutletsDao(ICsOutletsDao csOutletsDao) {
		this.csOutletsDao = csOutletsDao;
	}

}