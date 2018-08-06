package com.ccclubs.service.admin.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.lazy3q.util.Function;
import com.ccclubs.dao.ICsMemberShipDao;
import com.ccclubs.dao.ICsUnitInfoDao;
import com.ccclubs.dao.ICsUnitPersonDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsMemberShip;
import com.ccclubs.model.CsUnitInfo;
import com.ccclubs.model.CsUnitPerson;
import com.ccclubs.service.admin.ICsUnitPersonService;
import com.lazy3q.web.helper.$;

/**
 * 用车人员的Service实现
 * 
 * @author Joel
 */
public class CsUnitPersonService implements ICsUnitPersonService {
    ICsUnitPersonDao csUnitPersonDao;

    ICsMemberShipDao csMemberShipDao;

    ICsUnitInfoDao csUnitInfoDao;


    /**
     * 获取所有用车人员
     * 
     * @return
     */
    public List<CsUnitPerson> getCsUnitPersonList(Map params, Integer size) {
        return csUnitPersonDao.getCsUnitPersonList(params, size);
    }

    /**
     * 获取用车人员统计
     * 
     * @return
     */
    public List getCsUnitPersonStats(Map params, Map<String, Object> groups,
            Map<String, Object> sums) {
        return csUnitPersonDao.getCsUnitPersonStats(params, groups, sums);
    }

    /**
     * 获取用车人员总数
     * 
     * @return
     */
    public Long getCsUnitPersonCount(Map params) {
        return csUnitPersonDao.getCsUnitPersonCount(params);
    }

    /**
     * 获取用车人员自定义求和表达式,比如求和:eval="sum(id)"
     * 
     * @return
     */
    public <T> T getCsUnitPersonEval(String eval, Map params) {
        return csUnitPersonDao.getCsUnitPersonEval(eval, params);
    }

    /**
     * 获取用车人员分页
     * 
     * @return
     */
    public Page getCsUnitPersonPage(int page, int size, Map params) {
        return csUnitPersonDao.getCsUnitPersonPage(page, size, params);
    }

    /**
     * 根据查询条件取用车人员
     * 
     * @param params
     * @return
     */
    public CsUnitPerson getCsUnitPerson(Map params) {
        return csUnitPersonDao.getCsUnitPerson(params);
    }

    /**
     * 根据ID取用车人员
     * 
     * @param id
     * @return
     */
    public CsUnitPerson getCsUnitPersonById(Long id) {
        return csUnitPersonDao.getCsUnitPersonById(id);
    }

    /**
     * 保存用车人员
     * 
     * @param csUnitPerson
     */
    public CsUnitPerson saveCsUnitPerson(CsUnitPerson csUnitPerson) {
        return csUnitPersonDao.saveCsUnitPerson(csUnitPerson);
    }

    /**
     * 更新用车人员
     * 
     * @param csUnitPerson
     */
    public void updateCsUnitPerson(CsUnitPerson csUnitPerson) {
        csUnitPersonDao.updateCsUnitPerson(csUnitPerson);
    }

    /**
     * 更新用车人员非空字段
     * 
     * @param csUnitPerson
     */
    public void updateCsUnitPerson$NotNull(CsUnitPerson csUnitPerson) {
        csUnitPersonDao.updateCsUnitPerson$NotNull(csUnitPerson);
    }

    /**
     * 根据ID删除一个用车人员
     * 
     * @param id
     */
    public void deleteCsUnitPersonById(Long id) {
        csUnitPersonDao.deleteCsUnitPersonById(id);
    }

    /**
     * 根据ID逻辑删除一个用车人员
     * 
     * @param id
     */
    public void removeCsUnitPersonById(Long id) {
        csUnitPersonDao.removeCsUnitPersonById(id);
    }

    /**
     * 根据条件更新用车人员
     * 
     * @param values
     * @param params
     */
    public void updateCsUnitPersonByConfirm(Map values, Map params) {
        if (params.isEmpty())
            throw new RuntimeException("updateCsUnitPersonBy时无条件");
        csUnitPersonDao.updateCsUnitPersonByConfirm(values, params);
    }

    /**
     * 根据条件删除用车人员
     * 
     * @param params
     */
    public void deleteCsUnitPersonByConfirm(Map params) {
        if (params.isEmpty())
            throw new RuntimeException("deleteCsUnitPersonBy时无条件");
        csUnitPersonDao.deleteCsUnitPersonByConfirm(params);
    }

    /**
     * 事务处理
     */
    public <T> T executeTransaction(Function function) {
        return function.execute();
    }

    public ICsUnitPersonDao getCsUnitPersonDao() {
        return csUnitPersonDao;
    }

    public void setCsUnitPersonDao(ICsUnitPersonDao csUnitPersonDao) {
        this.csUnitPersonDao = csUnitPersonDao;
    }

    public ICsMemberShipDao getCsMemberShipDao() {
        return csMemberShipDao;
    }

    public void setCsMemberShipDao(ICsMemberShipDao csMemberShipDao) {
        this.csMemberShipDao = csMemberShipDao;
    }

    public ICsUnitInfoDao getCsUnitInfoDao() {
        return csUnitInfoDao;
    }

    public void setCsUnitInfoDao(ICsUnitInfoDao csUnitInfoDao) {
        this.csUnitInfoDao = csUnitInfoDao;
    }

    
    @Override
    public CsUnitPerson changeUnit(CsUnitPerson newData, CsUnitPerson oldData) {
        CsMember payMember = null;// 企业支付账号关联会员初始化
        if (newData.getCsupInfo() != null && oldData != null && (oldData.getCsupInfo() == null
                || newData.getCsupInfo() != oldData.getCsupInfo())) {
            CsUnitInfo csUnitInfo = csUnitInfoDao.getCsUnitInfoById(newData.getCsupInfo());
            if (csUnitInfo == null) {
                newData.setCsupInfo(null);// 关联单位信息未找到则不更新单位id
            } else {
                List<CsMember> members = csUnitInfo.get$csuiMember();
                if (members.size() > 0) {
                    payMember = members.get(0);
                    CsMemberShip ship = csMemberShipDao.getCsMemberShip(
                            $.add("csmsTargeter", newData.getCsupMember()).add("csmsStatus", 1));
                    if (ship == null) {
                        new CsMemberShip(payMember.getCsmHost()// 城市 [非空]
                                , payMember.getCsmId()// 付款帐号 [非空]
                                , newData.getCsupMember()// 使用帐号 [非空]
                                , new Date()// 添加时间 [非空]
                                , "修改-更换单位创建支付关系"// 备注
                                , (short) 1// 状态 [非空]
                        ).save();
                    } else {
                        ship.setCsmsPayer(payMember.getCsmId());
                        ship.setCsmsRemark("修改-更换单位更新支付关系");
                        csMemberShipDao.updateCsMemberShip$NotNull(ship);
                    }
                } else {
                    newData.setCsupInfo(null);// 单位未配置支付账号则不更新单位id
                }
            }
        } else {
            newData.setCsupInfo(null);// 不更新单位id
        }
        //更新企业用户信息
        this.updateCsUnitPerson(newData);
        return newData;
    }
    
    /**
     * 会员绑定企业和支付账号
     * @param memberId
     * @param unitId
     * @param unitGroupId
     */
    @Override
    @Transactional
    public void setUnitAndPayMember(Long memberId, Long unitId, Long unitGroupId) {
        if(unitId!=null && memberId!=null) {
            CsMember member = CsMember.get(memberId);
            
            //当前要绑定的企业
            CsUnitInfo csUnitInfo = csUnitInfoDao.getCsUnitInfoById(unitId);
            //会员关联企业用户信息
            CsUnitPerson unitPerson = CsUnitPerson.where().csupMember(memberId).get();
            if(null==unitPerson) {
                //企业用户不存在，执行新增操作
                unitPerson = new CsUnitPerson();
                unitPerson.setCsupGroup(unitGroupId);
                unitPerson.setCsupInfo(unitId);
                unitPerson.setCsupAddTime(new Date());
                unitPerson.setCsupFlag(null);
                unitPerson.setCsupHost(member.getCsmHost());
                unitPerson.setCsupMember(member.getCsmId());
                unitPerson.setCsupMemo(null);
                if(StringUtils.isEmpty(member.getCsmName())) {
                    unitPerson.setCsupName(member.getCsmMobile());
                }else {
                    unitPerson.setCsupName(member.getCsmName());    
                }
                
                unitPerson.setCsupRemark(null);
                unitPerson.setCsupStatus((short)1);
                unitPerson.setCsupUpdateTime(new Date());
                this.saveCsUnitPerson(unitPerson);
                //绑定会员支付账号关系
                saveMemberShip(memberId, csUnitInfo);
            }else {
                //企业用户已存在，执行更新操作
                CsUnitPerson.where().csupMember(memberId).set()
                .csupInfo(unitId).csupGroup(unitGroupId).update();
                
                if (unitPerson.getCsupInfo()!=null && unitPerson.getCsupInfo()!=unitId.longValue()) {
                    //绑定会员支付账号关系
                    saveMemberShip(memberId, csUnitInfo);
                }
            }  
        }
    }
    
    /**
     * 保存会员支付关系 
     * @param memberId 会员id
     * @param csUnitInfo 会员绑定的企业
     */
    public void saveMemberShip(Long memberId, CsUnitInfo csUnitInfo) {
        List<CsMember> members = csUnitInfo.get$csuiMember();
        if (members.size() > 0) {
            CsMember payMember = members.get(0);
            CsMemberShip ship = csMemberShipDao.getCsMemberShip(
                    $.add("csmsTargeter", memberId).add("csmsStatus", 1));
            if (ship == null) {
                new CsMemberShip(payMember.getCsmHost()// 城市 [非空]
                        , payMember.getCsmId()// 付款帐号 [非空]
                        , memberId// 使用帐号 [非空]
                        , new Date()// 添加时间 [非空]
                        , "创建支付关系"// 备注
                        , (short) 1// 状态 [非空]
                ).save();
            } else {
                ship.setCsmsPayer(payMember.getCsmId());
                ship.setCsmsRemark("更换单位更新支付关系");
                csMemberShipDao.updateCsMemberShip$NotNull(ship);
            }
        }
    }
    
}
