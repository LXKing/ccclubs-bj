package com.ccclubs.action.app.official.util;

import org.apache.commons.lang.StringUtils;

import com.ccclubs.model.CsMember;
import com.ccclubs.model.SrvUser;

/**
 * 授权
 * 
 * @author joel
 *
 */
public class OauthUtils {
	
	static final String  memberid = "plapp_uid";
	
	public static boolean validLogin(String sessionToken){
		return getSession(sessionToken)!=null;
	}
	
	/**
	 * 登录校验
	 * @param sessionToken
	 * @return
	 */
	public static Long getSession(String sessionToken){
		String memberID = SessionMgr.get(sessionToken, memberid);
		if(StringUtils.isNotEmpty(memberID)){
			return Long.valueOf(memberID);
		}
		return null;
	}
	
	/**
	 * 获取权限用户
	 * @param sessionToken
	 * @return
	 */
	public static CsMember getOauth(String sessionToken){
		Long memberId = getSession(sessionToken);
		if(memberId!=null){
			CsMember member = CsMember.get(memberId);
			return member;
		}
		return null;
	}
	
	public static SrvUser getSrvOauth(String sessionToken){
		Long srvId = getSession(sessionToken);
		if(srvId!=null){
			SrvUser srv = SrvUser.get(srvId);
			return srv;
		}
		return null;
	}
	
	
	/**
	 * 保存用户token
	 * @param memberID
	 * @param sessionToken
	 */
	public static void saveToken(String memberID, String sessionToken){
		SessionMgr.put(sessionToken, memberid, memberID);
		SessionMgr.set(memberID, sessionToken, SessionMgr.SESSION_LIFE_CYCLE);
	}
	
	/**
	 * 删除用户token
	 * @param memberID
	 * @param sessionToken
	 */
	public static void removeToken(String sessionToken, String memberId){
		SessionMgr.clearSession(sessionToken);
		SessionMgr.del(memberId);
	}
	
}
