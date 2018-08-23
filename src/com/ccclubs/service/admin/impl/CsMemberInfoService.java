package com.ccclubs.service.admin.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import com.lazy3q.util.Function;
import com.ccclubs.dao.ICsMemberDao;
import com.ccclubs.dao.ICsMemberInfoDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsMemberInfo;
import com.ccclubs.service.admin.ICsMemberInfoService;
import com.lazy3q.web.helper.$;

/**
 * 会员信息的Service实现
 * @author Joel
 */
public class CsMemberInfoService implements ICsMemberInfoService
{
	ICsMemberInfoDao csMemberInfoDao;
	
	ICsMemberDao csMemberDao;
	
    /**
	 * 获取所有会员信息
	 * @return
	 */
	public List<CsMemberInfo> getCsMemberInfoList(Map params,Integer size)
	{
		return csMemberInfoDao.getCsMemberInfoList(params,size);
	}
	
	/**
	 * 获取会员信息统计
	 * @return
	 */
	public List getCsMemberInfoStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csMemberInfoDao.getCsMemberInfoStats(params,groups,sums);
	}
	
	/**
	 * 获取会员信息总数
	 * @return
	 */
	public Long getCsMemberInfoCount(Map params)
	{
		return csMemberInfoDao.getCsMemberInfoCount(params);
	}
	
	/**
	 * 获取会员信息自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsMemberInfoEval(String eval,Map params)
	{
		return csMemberInfoDao.getCsMemberInfoEval(eval,params);
	}

	/**
	 * 获取会员信息分页
	 * @return
	 */
	public Page getCsMemberInfoPage(int page,int size,Map params)
	{
		return csMemberInfoDao.getCsMemberInfoPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取会员信息
	 * @param params
	 * @return
	 */
	public CsMemberInfo getCsMemberInfo(Map params)
	{
		return csMemberInfoDao.getCsMemberInfo(params);
	}

	/**
	 * 根据ID取会员信息
	 * @param id
	 * @return
	 */
	public CsMemberInfo getCsMemberInfoById(Long id)
	{
		return csMemberInfoDao.getCsMemberInfoById(id);
	}

	/**
	 * 保存会员信息
	 * @param csMemberInfo
	 */
	public CsMemberInfo saveCsMemberInfo(CsMemberInfo csMemberInfo)
	{
		return csMemberInfoDao.saveCsMemberInfo(csMemberInfo);
	}
	
	/**
	 * 更新会员信息
	 * @param csMemberInfo
	 */
	public void updateCsMemberInfo(CsMemberInfo csMemberInfo)
	{
		csMemberInfoDao.updateCsMemberInfo(csMemberInfo);
	}
	/**
	 * 更新会员信息非空字段
	 * @param csMemberInfo
	 */
	public void updateCsMemberInfo$NotNull(CsMemberInfo csMemberInfo)
	{
		csMemberInfoDao.updateCsMemberInfo$NotNull(csMemberInfo);
	}
	
	/**
	 * 根据ID删除一个会员信息
	 * @param id
	 */
	public void deleteCsMemberInfoById(Long id)
	{
		csMemberInfoDao.deleteCsMemberInfoById(id);
	}
	
	/**
	 * 根据ID逻辑删除一个会员信息
	 * @param id
	 */
	public void removeCsMemberInfoById(Long id)
	{
		csMemberInfoDao.removeCsMemberInfoById(id);
	}
	
	/**
	 * 根据条件更新会员信息
	 * @param values
	 * @param params
	 */
	public void updateCsMemberInfoByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsMemberInfoBy时无条件");
		csMemberInfoDao.updateCsMemberInfoByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除会员信息
	 * @param params
	 */
	public void deleteCsMemberInfoByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsMemberInfoBy时无条件");
		csMemberInfoDao.deleteCsMemberInfoByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsMemberInfoDao getCsMemberInfoDao()
	{
		return csMemberInfoDao;
	}

	public void setCsMemberInfoDao(ICsMemberInfoDao csMemberInfoDao)
	{
		this.csMemberInfoDao = csMemberInfoDao;
	}

    public ICsMemberDao getCsMemberDao() {
        return csMemberDao;
    }

    public void setCsMemberDao(ICsMemberDao csMemberDao) {
        this.csMemberDao = csMemberDao;
    }

    @Override
    public CsMemberInfo updateMemberInfoCertifyImage(CsMember member, String certifyImg,
            String certifyNum, String realName, String onCertifyImg) {
        CsMemberInfo csMemberInfo = createMemberInfoIfAbsent(member);
        //设置身份证信息
        csMemberInfo.setCsmiCertifyType((short) 1);
        csMemberInfo.setCsmiCertifyNum(StringUtils.trimToEmpty(certifyNum));
        csMemberInfo.setCsmiCertifyImage(certifyImg);
        csMemberInfo.setCsmiName(realName);
        csMemberInfo.setCsmiOnCertifyImage(onCertifyImg);
        
        csMemberInfoDao.updateCsMemberInfo$NotNull(csMemberInfo);
        return csMemberInfo;
    }

    @Override
    public CsMemberInfo updateMemberInfoDriverImage(CsMember member, String driverImage) {
        CsMemberInfo csMemberInfo = createMemberInfoIfAbsent(member);
        //设置驾照信息
        csMemberInfo.setCsmiDriverImage(driverImage);
        csMemberInfoDao.updateCsMemberInfo$NotNull(csMemberInfo);
        return csMemberInfo;
    }

    @Override
    public CsMemberInfo updateMemberInfoWorkImage(CsMember member, String proofOfEmployment,
            String company, String department) {
        CsMemberInfo csMemberInfo = createMemberInfoIfAbsent(member);
        //设置工作证信息
        csMemberInfo.setCsmiProofOfEmployment(proofOfEmployment);
        csMemberInfo.setCsmiCompany(company);
        csMemberInfo.setCsmiDepartment(department);

        csMemberInfoDao.updateCsMemberInfo$NotNull(csMemberInfo);
        return csMemberInfo;
    }
    
    @Override
    public CsMemberInfo updateMemberInfo(CsMember member, String certifyImg, String certifyNum,
            String driverImage, String onCertifyImg) {
        CsMemberInfo csMemberInfo = createMemberInfoIfAbsent(member);
        
        csMemberInfo.setCsmiCertifyType((short) 1);
        csMemberInfo.setCsmiCertifyNum(StringUtils.trimToEmpty(certifyNum));
        csMemberInfo.setCsmiCertifyImage(certifyImg);
        csMemberInfo.setCsmiDriverImage(driverImage);
        csMemberInfo.setCsmiOnCertifyImage(onCertifyImg);
        
        csMemberInfoDao.updateCsMemberInfo$NotNull(csMemberInfo);
        return csMemberInfo;
    }
    
    public CsMemberInfo createMemberInfoIfAbsent(CsMember member) {
        //查会员详细信息
        CsMemberInfo csMemberInfo =
                CsMemberInfo.Get($.add(CsMemberInfo.F.csmiMemberId, member.getCsmId()));
        
        if(csMemberInfo == null) {
            // 保存会员信息相关信息到cs_member_info
            csMemberInfo = initMemberInfo(new CsMemberInfo(),
                    null != member.getCsmHost() ? member.getCsmHost() : 1, null, member, (short)0);
            csMemberInfo = this.saveCsMemberInfo(csMemberInfo);
            
            // 保存会员信息相关信息到cs_member_info成功后，生成对应的csmiId,生成完成后反写到cs_member表中的csm_Info字段中
            member.setCsmInfo(csMemberInfo.getCsmiId());
            csMemberDao.updateCsMember$NotNull(member);
        }
        
        return csMemberInfo;
    }
    
    public CsMemberInfo initMemberInfo(CsMemberInfo csMemberInfo, Long host, String realName,
            CsMember csMember, short sex) {
        csMemberInfo.setCsmiHost(host);
        csMemberInfo.setCsmiName(realName);
        csMemberInfo.setCsmiMemberId(csMember.getCsmId());
        csMemberInfo.setCsmiAddTime(new Date());
        csMemberInfo.setCsmiUpdateTime(new Date());
        csMemberInfo.setCsmiStatus((short) 1);
        csMemberInfo.setCsmiSex(sex);
        return csMemberInfo;
    }
}