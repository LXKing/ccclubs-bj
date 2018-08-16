package com.ccclubs.service.admin.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ccclubs.dao.ICsEvCardDao;
import com.ccclubs.helper.LoggerHelper;
import com.ccclubs.model.CsEvCard;
import com.ccclubs.model.CsMember;
import com.ccclubs.service.admin.ICsEvCardService;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.util.Page;

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
	    
        CsEvCard csEvCard = this.getCsEvCardById(member.getCsmEvcard());
        if(csEvCard==null) {
            if(member.getVWork()==1) {
                String cardSuffix = "BJ";
                String macSuffix = "XN";
                //获取最大卡号
                CsEvCard card = CsEvCard.where().csecNumber(cardSuffix).get();
                String cardNo = "";
                if (card!=null) {
                    cardNo = card.getCsecNumber();
                }
                if (StringUtils.isEmpty(cardNo)) {
                    cardNo = "10000";//5位数起步
                }else {
                    cardNo = cardNo.replaceAll(cardSuffix, "");
                }
                Integer cno = Integer.parseInt(cardNo);
                
                //获取最大机器编号
                card = CsEvCard.where().csecRfid(macSuffix).get();
                String macNo = "";
                if (card!=null) {
                    macNo = card.getCsecRfid();
                }
                if(StringUtils.isEmpty(macNo)) {
                    macNo = "100000";//6位数起步
                }else {
                    macNo = macNo.replaceAll(macSuffix, "");
                }
                Integer mno = Integer.parseInt(macNo);
                
                //初始化ev卡
                csEvCard = new CsEvCard();
                
                long count = 1;
                while (count > 0) {
                    //ev卡编号数字位自加1
                    cno++;
                    cardNo = cno.toString() + cardSuffix;
                    //设置ev卡编号
                    csEvCard.setCsecNumber(cardNo);
                    //检索ev卡编号是否已存在，存在则重新生成编号，否则使用当前编号生成ev卡信息
                    count = this.getCsEvCardCount($.add(CsEvCard.F.csecNumber, cardNo));
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
                
                count = 1;
                while (count > 0) {
                    //机器编号数字位自加1
                    mno++;
                    
                    macNo = mno.toString() + macSuffix;
                    //设置机器编号
                    csEvCard.setCsecRfid(macNo);
                    //检索ev卡机器编号是否已存在，存在则重新生成机器编号，否则使用当前机器编号更新ev卡信息
                    count = this.getCsEvCardCount($.add(CsEvCard.F.csecRfid, cardNo));
                }
                this.updateCsEvCard$NotNull(csEvCard);
            }
        }else {
            if(member.getVWork()==1) {
                //工作证认证成功，ev卡状态有效
                csEvCard.setCsecStatus((short)1); 
            }else {
                //工作证认证不成功，ev卡状态无效
                csEvCard.setCsecStatus((short)0);
            }
            this.updateCsEvCard$NotNull(csEvCard);
        }
	}
}