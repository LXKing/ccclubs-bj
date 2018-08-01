package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsMemberInfo;

/**
 * 会员信息的Service接口
 * @author Joel
 */
@SuppressWarnings("unchecked")
public interface ICsMemberInfoService
{
	/**
	 * 获取所有会员信息
	 * @return
	 */
	public List<CsMemberInfo> getCsMemberInfoList(Map params,Integer size);
	
	/**
	 * 获取会员信息统计
	 * @return
	 */
	public List getCsMemberInfoStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取会员信息总数
	 * @return
	 */	
	public Long getCsMemberInfoCount(Map params);
	
	
	/**
	 * 获取会员信息自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsMemberInfoEval(String eval,Map params);

	/**
	 * 获取会员信息分页
	 * @return
	 */	
	public Page getCsMemberInfoPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取会员信息
	 * @param params
	 * @return
	 */
	public CsMemberInfo getCsMemberInfo(Map params);

	/**
	 * 根据ID取会员信息
	 * @param id
	 * @return
	 */
	public CsMemberInfo getCsMemberInfoById(Long id);


	/**
	 * 保存会员信息
	 * @param csMemberInfo
	 */
	public CsMemberInfo saveCsMemberInfo(CsMemberInfo csMemberInfo);

	/**
	 * 更新会员信息
	 * @param csMemberInfo
	 */
	public void updateCsMemberInfo(CsMemberInfo csMemberInfo);
	/**
	 * 更新会员信息非空字段
	 * @param csMemberInfo
	 */
	public void updateCsMemberInfo$NotNull(CsMemberInfo csMemberInfo);

	/**
	 * 根据ID删除一个会员信息
	 * @param id
	 */
	public void deleteCsMemberInfoById(Long id);
	/**
	 * 根据ID逻辑删除一个会员信息
	 * @param id
	 */
	public void removeCsMemberInfoById(Long id);
	/**
	 * 根据条件更新会员信息
	 * @param values
	 * @param params
	 */
	public void updateCsMemberInfoByConfirm(Map values, Map params);
	/**
	 * 根据条件删除会员信息
	 * @param params
	 */
	public void deleteCsMemberInfoByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);
	
	/**
	 * 创建初始化的会员详细信息
	 * @param member
	 * @return
	 */
	public CsMemberInfo createMemberInfoIfAbsent(CsMember member);
	
	/**
	 * 更新身份证认证信息
	 * @param member
	 * @param certifyImg
	 * @param certifyNum
	 * @param realName
	 * @param onCertifyImg
	 * @return
	 */
	public CsMemberInfo updateMemberInfoCertifyImage(CsMember member, String certifyImg,
            String certifyNum, String realName, String onCertifyImg);
	/**
	 * 更新驾照信息
	 * @param member
	 * @param driverImage
	 * @return
	 */
	public CsMemberInfo updateMemberInfoDriverImage(CsMember member, String driverImage);
	
	/**
	 * 更新工作证信息
	 * @param member
	 * @param proofOfEmployment
	 * @param company
	 * @param department
	 * @return
	 */
	public CsMemberInfo updateMemberInfoWorkImage(CsMember member, String proofOfEmployment,
	            String company, String department);
	
	/**
     * 实名认证
     * @param member
     * @param certifyImg
     * @param certifyNum
     * @param driverImage
     * @param onCertifyImg
     * @return
     */
    public CsMemberInfo updateMemberInfo(CsMember member, String certifyImg, String certifyNum,
            String driverImage, String onCertifyImg);

}