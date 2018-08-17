package com.ccclubs.service.admin.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import com.ccclubs.config.SYSTEM.IntegralType;
import com.ccclubs.constants.MemberRecStatus;
import com.ccclubs.constants.SmsFlagEnum;
import com.ccclubs.dao.ICsMemberDao;
import com.ccclubs.helper.UtilHelper;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsMemberInfo;
import com.ccclubs.model.CsUnitGroup;
import com.ccclubs.model.CsUnitInfo;
import com.ccclubs.model.CsUnitPerson;
import com.ccclubs.service.admin.ICsEvCardService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsUnitPersonService;
import com.ccclubs.service.common.ICommonUtilService.SMSType;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.util.Page;
import edu.emory.mathcs.backport.java.util.Collections;

/**
 * 会员帐号的Service实现
 * @author 飞啊飘啊
 */
public class CsMemberService implements ICsMemberService
{
	ICsMemberDao csMemberDao;
	

	/**
	 * 获取所有会员帐号
	 * @return
	 */
	public List<CsMember> getCsMemberList(Map params,Integer size)
	{
		return csMemberDao.getCsMemberList(params,size);
	}
	
	/**
	 * 获取会员帐号统计
	 * @return
	 */
	public List getCsMemberStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csMemberDao.getCsMemberStats(params,groups,sums);
	}
	
	/**
	 * 获取会员帐号总数
	 * @return
	 */
	public Long getCsMemberCount(Map params)
	{
		return csMemberDao.getCsMemberCount(params);
	}
	
	/**
	 * 获取会员帐号自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsMemberEval(String eval,Map params)
	{
		return csMemberDao.getCsMemberEval(eval,params);
	}

	/**
	 * 获取会员帐号分页
	 * @return
	 */
	public Page getCsMemberPage(int page,int size,Map params)
	{
		return csMemberDao.getCsMemberPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取会员帐号
	 * @param params
	 * @return
	 */
	public CsMember getCsMember(Map params)
	{
		return csMemberDao.getCsMember(params);
	}

	/**
	 * 根据ID取会员帐号
	 * @param id
	 * @return
	 */
	public CsMember getCsMemberById(Long id)
	{
		return csMemberDao.getCsMemberById(id);
	}

	/**
	 * 保存会员帐号
	 * @param csMember
	 */
	public CsMember saveCsMember(CsMember csMember)
	{
		return csMemberDao.saveCsMember(csMember);
	}
	
	/**
	 * 更新会员帐号
	 * @param csMember
	 */
	public void updateCsMember(CsMember csMember)
	{
		csMemberDao.updateCsMember(csMember);
	}
	/**
	 * 更新会员帐号非空字段
	 * @param csMember
	 */
	public void updateCsMember$NotNull(CsMember csMember)
	{
		csMemberDao.updateCsMember$NotNull(csMember);
	}
	
	/**
	 * 根据ID删除一个会员帐号
	 * @param id
	 */
	public void deleteCsMemberById(Long id)
	{
		csMemberDao.deleteCsMemberById(id);
	}
	/**
	 * 根据ID逻辑删除一个会员帐号
	 * @param id
	 */
	public void removeCsMemberById(Long id)
	{
		csMemberDao.removeCsMemberById(id);
	}
	
	/**
	 * 根据条件更新会员帐号
	 * @param values
	 * @param params
	 */
	public void updateCsMemberByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsMemberBy时无条件");
		csMemberDao.updateCsMemberByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除会员帐号
	 * @param params
	 */
	public void deleteCsMemberByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsMemberBy时无条件");
		csMemberDao.deleteCsMemberByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsMemberDao getCsMemberDao()
	{
		return csMemberDao;
	}

	public void setCsMemberDao(ICsMemberDao csMemberDao)
	{
		this.csMemberDao = csMemberDao;
	}
	
	/**
	 * 审核用户认证信息
     * @param old 数据库数据
     * @param fresh 用户请求数据
	 */
	@Override
	public void verify(CsMember old, CsMember fresh) {
	    //用户认证通过，赠送积分
	    authSuccessGiveIntegral(fresh);
	    
	    //认证进度提醒短信
	    authProgressNotice(old, fresh);
	    
	    //身份证、驾驶证、工作证认证状态变更失败发送短信提示
	    authFailNotice(old, fresh);
	    
	    //自动绑定ev卡逻辑处理开始
	    ICsEvCardService csEvCardService = $.getBean("csEvCardService");
	    //自动绑定ev卡操作
	    csEvCardService.autoBindEvCard(fresh.getCsmId(), old.getCsmHost(), old.getCsmEvcard(), fresh.getVWork());
	}
	
	/**
     * 用户认证通过，赠送积分
     * @param member 用户请求数据
     */
	public void authSuccessGiveIntegral(CsMember member) {
        //两项认证成功，送积分
        if (member.getVDrive().shortValue() == 1
                && member.getVReal().shortValue() == 1) {
            com.ccclubs.service.common.ICommonMoneyService commonMoneyService =
                    $.getBean("commonMoneyService");
            if (com.ccclubs.model.CsIntegralRecord.where()
                    .csrMember(member.getCsmId()).csrType(IntegralType.注册送积分.name())
                    .get() == null)
                commonMoneyService.updateIntegralByRule(member.getCsmId(), 1d,
                        IntegralType.注册送积分, "资料审核通过得积分", null);
        }
    }
	
	/**
     * 认证进度提醒短信
     * @param old 数据库数据
     * @param fresh 用户请求数据
     */
	public void authProgressNotice(CsMember old, CsMember fresh) {
	    // 审核通过发送短信
        String password = StringUtils.isEmpty(old.getCsmPassword()) ? $.zerofill($.rand(999999), 6) : null;// 自动生成6位随机密码
        fresh.setCsmRemark("");

        Short from = $.or(old.getCsmFrom(), (short) 5);

        if (fresh.getVReal() == 1 && fresh.getVDrive() == 1 && from == 5
                && StringUtils.isNotEmpty(password)) {
            fresh.setCsmPassword($.md5(password));
        }
        fresh.update();

        // 只有审核通过才发送短信
        short vprogress = 0;// 初始化原认证进度
        if (old.getVReal() == 1 && old.getVDrive() == 1
                && old.getVWork() == 1
                && old.getVOffline() == 1) {
            vprogress = 4;// 四项认证完毕
        } else if (old.getVReal() == 1 && old.getVDrive() == 1
                && old.getVWork() == 1) {
            vprogress = 3;// 三项认证完毕
        }
        // 满足认证进度的发送短信
        if (vprogress < 4 && !$.empty(old.getCsmMobile()) && from == 5) {
            CsUnitPerson csUnitPerson = CsUnitPerson.getCsUnitPerson(
                    $.add(CsUnitPerson.F.csupMember, old.getCsmId()));
            if (csUnitPerson != null) {
                String append = StringUtils.isNotEmpty(password)? "您的密码为："+password+"。" : null;
                if (fresh.getVReal() == 1 && fresh.getVDrive() == 1
                        && fresh.getVWork() == 1
                        && fresh.getVOffline() == 1 && old.getVOffline() != 1) {
                    UtilHelper.sendTemplateSMS(csUnitPerson.getCsupHost(),
                            SmsFlagEnum.实名认证四证通过.getFlag(), old.getCsmMobile$(),
                            null, SMSType.通知类短信,
                            Collections.emptyMap(), append);
                }else if (fresh.getVReal() == 1 && fresh.getVDrive() == 1
                         && fresh.getVWork() == 1 && vprogress < 3 && old.getVOffline() != 1) {
                     UtilHelper.sendTemplateSMS(csUnitPerson.getCsupHost(),
                             SmsFlagEnum.实名认证三证通过.getFlag(), old.getCsmMobile$(),
                             null, SMSType.通知类短信,
                             Collections.emptyMap(), append);
                 }
            }
        }
    }
	
	/**
	 * 身份证、驾驶证、工作证认证状态变更失败发送短信提示
	 * @param old 数据库数据
	 * @param fresh 用户请求数据
	 */
	public void authFailNotice(CsMember old, CsMember fresh) {
	    //发身份证提醒短信
	    if(old.getVReal()!=MemberRecStatus.REC_FAIL && fresh.getVReal()==MemberRecStatus.REC_FAIL) {
	        UtilHelper.sendTemplateSMS(old.getCsmHost(),
                    SmsFlagEnum.身份证认证失败.getFlag(), old.getCsmMobile$(),
                    null, SMSType.通知类短信,
                    Collections.emptyMap());
	    }
	    //发驾驶证提醒短信
	    if(old.getVDrive()!=MemberRecStatus.REC_FAIL && fresh.getVDrive()==MemberRecStatus.REC_FAIL) {
	        UtilHelper.sendTemplateSMS(old.getCsmHost(),
                    SmsFlagEnum.驾驶证认证失败.getFlag(), old.getCsmMobile$(),
                    null, SMSType.通知类短信,
                    Collections.emptyMap());
	    }
	    //发工作证提醒短信
	    if(old.getVWork()!=MemberRecStatus.REC_FAIL && fresh.getVWork()==MemberRecStatus.REC_FAIL) {
	        UtilHelper.sendTemplateSMS(old.getCsmHost(),
                    SmsFlagEnum.工作证认证失败.getFlag(), old.getCsmMobile$(),
                    null, SMSType.通知类短信,
                    Collections.emptyMap());
	    }
    }

}