package com.ccclubs.action.weixin.scripts;

import java.util.List;
import java.util.Map;

import com.ccclubs.action.weixin.IWXScript;
import com.ccclubs.helper.redis.DBIndex;
import com.ccclubs.helper.redis.DefaultJRedisClient;
import com.ccclubs.model.CsWxContent;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;

/**
 * 同步openId缓存
 * 
 * @author joel
 * 
 */
public class CacheSyncWXScript implements IWXScript {
	
	final DefaultJRedisClient<String, Object> jr = new DefaultJRedisClient<String, Object>(DBIndex.APP_WEIXIN);

	@Override
	public ReturnType getReturnType() {
		return ReturnType.TEXT;
	}

	@Override
	public List<CsWxContent> getContents(String openId, String content) {
		return null;
	}

	@Override
	public String getContent(String openId, String content) {
		String sql = "select cm.csm_id,cm.csm_weixin_flag,cm.csm_alipay_flag from cs_member cm where cm.csm_weixin_flag is not null or cm.csm_alipay_flag is not null";
		Lazy3qDaoSupport dao = $.getDao("ccclubs_system");
		List<Map> list = dao.executeQuery(sql);
		int wx_count = 0;
		int ali_count = 0;
		Long startTime = System.currentTimeMillis();
		if(list!=null){
			for(Map mp : list){
				Object member = mp.get("csm_id");
				Object weixin_flag = mp.get("csm_weixin_flag");
				Object alipay_flag = mp.get("csm_alipay_flag");
				if(weixin_flag!=null && !weixin_flag.toString().equals("")){
					if(!jr.isExists(weixin_flag.toString())){
						wx_count ++;
						jr.set(weixin_flag.toString(), member.toString());
					}
				}
				if(alipay_flag!=null && !alipay_flag.toString().equals("")){
					if(!jr.isExists(alipay_flag.toString())){
						ali_count ++;
						jr.set(alipay_flag.toString(), member.toString());
					}
				}
			}
		}
		long endTime = System.currentTimeMillis();    //获取结束时间

		return "已完成同步微信OPENID:"+wx_count+"，支付宝OPENID："+ali_count+"，执行时间："+(endTime - startTime)+"！";
	}

	
}
