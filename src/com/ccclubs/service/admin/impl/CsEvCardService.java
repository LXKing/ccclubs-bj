package com.ccclubs.service.admin.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsEvCardDao;
import com.ccclubs.helper.LoggerHelper;
import com.ccclubs.helper.SystemHelper;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsEvCard;
import com.ccclubs.model.CsMember;
import com.ccclubs.service.admin.ICsEvCardService;
import com.lazy3q.web.helper.$;

/**
 * 会员卡的Service实现
 * @author 飞啊飘啊
 */
public class CsEvCardService implements ICsEvCardService
{
	ICsEvCardDao csEvCardDao;
	

	/**
	 * 获取所有会员卡
	 * @return
	 */
	public List<CsEvCard> getCsEvCardList(Map params,Integer size)
	{
		return csEvCardDao.getCsEvCardList(params,size);
	}
	
	/**
	 * 获取会员卡统计
	 * @return
	 */
	public List getCsEvCardStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csEvCardDao.getCsEvCardStats(params,groups,sums);
	}
	
	/**
	 * 获取会员卡总数
	 * @return
	 */
	public Long getCsEvCardCount(Map params)
	{
		return csEvCardDao.getCsEvCardCount(params);
	}
	
	/**
	 * 获取会员卡自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsEvCardEval(String eval,Map params)
	{
		return csEvCardDao.getCsEvCardEval(eval,params);
	}

	/**
	 * 获取会员卡分页
	 * @return
	 */
	public Page getCsEvCardPage(int page,int size,Map params)
	{
		return csEvCardDao.getCsEvCardPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取会员卡
	 * @param params
	 * @return
	 */
	public CsEvCard getCsEvCard(Map params)
	{
		return csEvCardDao.getCsEvCard(params);
	}

	/**
	 * 根据ID取会员卡
	 * @param id
	 * @return
	 */
	public CsEvCard getCsEvCardById(Long id)
	{
		return csEvCardDao.getCsEvCardById(id);
	}

	/**
	 * 保存会员卡
	 * @param csEvCard
	 */
	public CsEvCard saveCsEvCard(CsEvCard csEvCard)
	{
		return csEvCardDao.saveCsEvCard(csEvCard);
	}
	
	/**
	 * 更新会员卡
	 * @param csEvCard
	 */
	public void updateCsEvCard(CsEvCard csEvCard)
	{
		csEvCardDao.updateCsEvCard(csEvCard);
	}
	/**
	 * 更新会员卡非空字段
	 * @param csEvCard
	 */
	public void updateCsEvCard$NotNull(CsEvCard csEvCard)
	{
		csEvCardDao.updateCsEvCard$NotNull(csEvCard);
	}
	
	/**
	 * 根据ID删除一个会员卡
	 * @param id
	 */
	public void deleteCsEvCardById(Long id)
	{
		csEvCardDao.deleteCsEvCardById(id);
	}
	/**
	 * 根据ID逻辑删除一个会员卡
	 * @param id
	 */
	public void removeCsEvCardById(Long id)
	{
		csEvCardDao.removeCsEvCardById(id);
	}
	
	/**
	 * 根据条件更新会员卡
	 * @param values
	 * @param params
	 */
	public void updateCsEvCardByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsEvCardBy时无条件");
		csEvCardDao.updateCsEvCardByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除会员卡
	 * @param params
	 */
	public void deleteCsEvCardByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsEvCardBy时无条件");
		csEvCardDao.deleteCsEvCardByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsEvCardDao getCsEvCardDao()
	{
		return csEvCardDao;
	}

	public void setCsEvCardDao(ICsEvCardDao csEvCardDao)
	{
		this.csEvCardDao = csEvCardDao;
	}
	
	/**
	 * 后台自动为会员绑定ev卡；注意添加分布式锁（暂不实现）
	 * @param member
	 */
	@Transactional
	public void autoBindEvCard(CsMember member) {
	    //会员为空这不做绑定ev卡流程
	    if (member == null) {
            return;
        }
	    //初始化ev卡
	    CsEvCard csEvCard = new CsEvCard();
	    
	    long count = 1;
	    while (count > 0) {
	        String number = RandomStringUtils.random(8);
	        String rfid = RandomStringUtils.random(12);
	        //检索ev卡编号是否已存在，存在则重新生成编号，否则使用当前编号生成ev卡信息
	        count = this.getCsEvCardCount($.add("csec_number", number).add("csec_rfid", "rfid"));
	        csEvCard.setCsecNumber(number);
	        csEvCard.setCsecRfid(rfid);
	    }
	    csEvCard.setCsecHost(member.getCsmHost());
	    csEvCard.setCsecRemark("自动绑定");
	    csEvCard.setCsecAddTime(new Date());
	    csEvCard.setCsecFlag((short) 1);//已绑定
	    csEvCard.setCsecStatus((short) 1);//正常
	    //保存ev卡
	    csEvCard = this.saveCsEvCard(csEvCard);
	    //输入会员绑定ev卡日志
	    LoggerHelper.writeLog(CsEvCard.class,"add","自动添加[会员卡]["+csEvCard.getCsecNumber()+"]",(Long)$.getSession("ccclubs_login_id"), csEvCard,csEvCard.getCsecId());
	    
	    //会员绑定ev卡
	    member.setCsmEvcard(csEvCard.getCsecId());
	    CsMemberService memberService = $.getBean("csMemberService");
	    memberService.updateCsMember$NotNull(member);
	}
}