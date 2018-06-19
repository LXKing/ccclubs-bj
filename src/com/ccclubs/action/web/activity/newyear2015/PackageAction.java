package com.ccclubs.action.web.activity.newyear2015;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.helper.redis.DefaultJRedisClient;
import com.ccclubs.model.CsChjSign;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsPack;
import com.ccclubs.model.CsUserPack;
import com.ccclubs.service.admin.ICsChjSignService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsPackInfoService;
import com.ccclubs.service.admin.ICsPackService;
import com.ccclubs.service.admin.ICsUserPackService;
import com.ccclubs.service.common.IPackageService;
import com.ccclubs.util.DateUtil;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.Lazy;

public class PackageAction extends ActivityBase{
	
	IPackageService packageService;
	ICsPackService csPackService;
	ICsPackInfoService csPackInfoService;
	ICsUserPackService csUserPackService;
	ICsMemberService csMemberService;
	ICsChjSignService csChjSignService;
	
	DefaultJRedisClient jRedisClient;
	
	/**
	 * 缓存改读数据库开关
	 */
	protected static volatile boolean read4DB = false;
	
	/**
	 * 减库存操作类型
	 */
	public enum DecrCountOpType{
		cancelbuy, payment, expired
	}
	
	/**
	 * 缓存标签
	 */
	public enum CacheTag{
		book("meal_book_"), payment("meal_payment_");
		
		String prefix;
		
		CacheTag(String prefix){
			this.prefix = prefix;
		}
	}
	
	/**
	 * 套餐购买页
	 * @return
	 */
	public String index(){
		
		getChannel();
		
		if (SystemHelper.isLogin()){
			if (!isSign()) {
				$.setRequest("nosign", 1);
			}else{
				//判断会员是否已完成认证
				CsMember csMember = csMemberService.getCsMemberById(SystemHelper.getLoginId());		
				if(csMember!=null){
					if(csMember.getCsmVDrive()!=null && csMember.getCsmVReal()!=null && csMember.getCsmVDrive()==1 && csMember.getCsmVReal()==1){
						$.setRequest("certification", 1);
					}
				}
			}
		
			Boolean bl = $.getBoolean("onTest");
			if(bl !=null){
				isSwitch = bl;
			}
			$.setRequest("time", $.getRequest("time"));
			Date now = $.getDate("time");
			if(now == null){
				now = getNow();
			}
			
			Long[] step = getCurrentStep(now);
			if(step[0] > 0){
				UserBook userBook = getUserBook(getUserBookTag());
				if(userBook!=null){
					Date date = userBook.getDate();
					if(isExpire(userBook)){
						delUserBookAndDecrCount(DecrCountOpType.cancelbuy);
					}else{
						long time_end = date.getTime() + 1000 * 60 *30;
						$.setRequest("remainPayTime", time_end - new Date().getTime());
						$.setRequest("userBook", userBook);
					}
				}else{
					userBook = getUserBook(getUserBoughtTag());
					if(userBook !=null){
						return packDetail();
					}
				}
				
				//TODO
	//			jRedisClient.del("meal_book_10014169");
	//			Long v = jRedisClient.decr("NEW_YEAR_PACK_2015_BKKY-1");
	//			v = jRedisClient.decr("NEW_YEAR_PACK_2015_MG-1");
	//			v = jRedisClient.decr("NEW_YEAR_PACK_2015_SMART-1");
	//			jRedisClient.set("NEW_YEAR_PACK_2015_KY-1","0");
	//			jRedisClient.set("NEW_YEAR_PACK_2015_MG-1","0");
	//			jRedisClient.set("NEW_YEAR_PACK_2015_SMART-1","0");
				
				if(step[0] ==0){
					$.setRequest("activity_state", 1);
				}else if(step[0] >0 && step[0] <4){
					$.setRequest("activity_state", 2);
				}else if(step[0] == 5){
					$.setRequest("activity_state", 3);
				}else if(step[0] == 4){
					$.setRequest("activity_state", 4);
				}else if(step[0]>5){
					$.setRequest("activity_state", 5);
				}
			}
		}else{
			$.setRequest("nosign", 1);
		}
		
		$.setRequest("tmpv", new Date().getTime());
		return "index";
	}
	
	/**
	 * 是否已过期
	 * @param userBook
	 * @return
	 */
	public boolean isExpire(UserBook userBook){
		if(userBook!=null){
			Date date = userBook.getDate();
			if(date!=null){
				long time_end = date.getTime() + 1000 * 60 *30;
				if(time_end <= new Date().getTime()){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 获取活动数据
	 * @return
	 */
	public String getActivityData(){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Long[] step = getCurrentStep(getNow());
		
		Map<String, Meal> mealMap = new HashMap<String, Meal>();
		List<String> flags = new ArrayList<String>();
		if(step[0] == 1){
			flags.add(NEW_YEAR_PACK_2015_KY);
			flags.add(NEW_YEAR_PACK_2015_MG);
			mealMap.put(NEW_YEAR_PACK_2015_KY, meal_bkky.setCount(16));
			mealMap.put(NEW_YEAR_PACK_2015_MG, meal_mg3.setCount(4));
		}
		
		if(step[0] == 2){
			flags.add(NEW_YEAR_PACK_2015_KY);
			flags.add(NEW_YEAR_PACK_2015_MG);
			flags.add(NEW_YEAR_PACK_2015_SMART);
			mealMap.put(NEW_YEAR_PACK_2015_KY, meal_bkky.setCount(45));
			mealMap.put(NEW_YEAR_PACK_2015_MG, meal_mg3.setCount(13));
			mealMap.put(NEW_YEAR_PACK_2015_SMART, meal_smart.setCount(4));
		}
		
		if(step[0] == 3){
			flags.add(NEW_YEAR_PACK_2015_KY);
			flags.add(NEW_YEAR_PACK_2015_MG);
			flags.add(NEW_YEAR_PACK_2015_SMART);
			mealMap.put(NEW_YEAR_PACK_2015_KY, meal_bkky.setCount(19));
			mealMap.put(NEW_YEAR_PACK_2015_MG, meal_mg3.setCount(6));
			mealMap.put(NEW_YEAR_PACK_2015_SMART, meal_smart.setCount(7));
		}
		
		if(step[0] ==1 || step[0] ==2 || step[0] ==3){
			List<CsPack> packs = csPackService.getCsPackList(
					$.add("definex",""+CsPack.C.cspFlag+" like '"+NEW_YEAR_PACK_2015_+"%'")
					, -1);
			
			if(packs!=null){
				for(CsPack pack : packs){
					if(flags.contains(pack.getCspFlag())){
						Long saled = csUserPackService.getCsUserPackCount($.add(CsUserPack.F.csupPack, pack.getCspId()).add(CsUserPack.F.csupStatus, 1)
								.add("definex", CsUserPack.C.csupRemark + " ='" +pack.getCspName() + getCurrentStepActivityRemark(step[0].intValue()) + "'")
						);
						Meal meal = mealMap.get(pack.getCspFlag());
						if(meal!=null){
							int bookCount = getCurrentPackBookCount(pack.getCspFlag(), step[0].intValue(), pack.getCspId());
							meal.setRemain(meal.getCount() - (saled.intValue()+bookCount));
							meal.setPack(pack);
						}
					}
				}
			}
		}
		
		dataMap.put("step", step[0]);
		dataMap.put("remainTime", step[1]);
		dataMap.put("meals", mealMap);
		
		return Lazy.SendAjax(dataMap, "UTF-8");
	}
	
	/**
	 * 获取当前 步骤的套餐
	 * @param step
	 * @return
	 */
	public Map<String, Meal> getCurrentStepMeals(int step){
		Map<String, Meal> mealMap = new HashMap<String, Meal>();
		if(step == 1){
			mealMap.put(NEW_YEAR_PACK_2015_KY, meal_bkky.setCount(16));
			mealMap.put(NEW_YEAR_PACK_2015_MG, meal_mg3.setCount(4));
		}
		
		if(step == 2){
			mealMap.put(NEW_YEAR_PACK_2015_KY, meal_bkky.setCount(45));
			mealMap.put(NEW_YEAR_PACK_2015_MG, meal_mg3.setCount(13));
			mealMap.put(NEW_YEAR_PACK_2015_SMART, meal_smart.setCount(4));
		}
		
		if(step == 3){
			mealMap.put(NEW_YEAR_PACK_2015_KY, meal_bkky.setCount(19));
			mealMap.put(NEW_YEAR_PACK_2015_MG, meal_mg3.setCount(6));
			mealMap.put(NEW_YEAR_PACK_2015_SMART, meal_smart.setCount(7));
		}
		return mealMap;
	}
	
	/**
	 * 获取用户已买到的标
	 * @return
	 */
	public String getUserBoughtTag(){
		return "meal_payment_"+SystemHelper.getLoginId()+"";
	}
	
	/**
	 * 获取用户已预定的标
	 * @return
	 */
	public String getUserBookTag(){
		return "meal_book_"+SystemHelper.getLoginId()+"";
	}
	
	/**
	 * 获取用户订单
	 * @param key
	 * @return
	 */
	public UserBook getUserBook(String key){
		UserBook book = null;
		String userBook = jRedisClient.get(key);
		if(!StringUtils.isEmpty(userBook)){
			book = new UserBook();
			String[] str = userBook.split(",");
			book.setResId(Long.valueOf(str[0]));
			book.setUserId(Long.valueOf(str[1]));
			book.setDate(new DateUtil().StringtoDate(str[2], DATE_FORMAT));
		}
		return book;
	}
	
	/**
	 * 存储用户订单
	 * @param key
	 * @return
	 */
	public boolean saveUserBook(String key, UserBook book){
		String str = "";
		if(book!=null){
			str += book.getResId();
			str += "," + book.getUserId();
			str += "," + new DateUtil().dateToString(book.getDate(), DATE_FORMAT);
		}
		if(str.equals(""))return false;
		jRedisClient.set(key, str);
		return true;
	}
	
	public void delUserBookAndDecrCount(DecrCountOpType opType){
		UserBook userBook = getUserBook(getUserBookTag());
		if(userBook!=null){
			//删除预订缓存
			jRedisClient.del(getUserBookTag());
			Long[] step = getCurrentStep(getNow());
			CsPack pack = csPackService.getCsPack($.add(CsPack.F.cspId, userBook.getResId()));
			
			//回归库存
			String currentPackKey = pack.getCspFlag()+"-"+step[0];
			Long val = jRedisClient.decr(currentPackKey);
			if(val!=null && val <0){
				System.out.println("======================================缓存不一致===============================");
				jRedisClient.set(currentPackKey, "0");
			}
			
			try{
				ICsChjSignService csChjSignService = $.getBean("csChjSignService");
				CsChjSign csChjSign = csChjSignService.getCsChjSign($.add(CsChjSign.F.cscsMember, SystemHelper.getLoginId())
						.add(CsChjSign.F.cscsYear, 2015));
				
				String remark = "";
				switch(opType){
					case cancelbuy:
						remark = "取消订单"+currentPackKey;
						csChjSign.setCscsStatus((short)0);	//回到已预订状态
						break;
					case payment:
						remark = "支付订单"+currentPackKey;
						csChjSign.setCscsStatus((short)2);	//已购买
						break;
					case expired:
						remark = "失效订单"+currentPackKey;
						csChjSign.setCscsStatus((short)0);	//回到已预订状态
						break;
				}
				
				if(csChjSign != null){
					csChjSign.setCscsRemark(csChjSign.getCscsRemark()+"#"+new DateUtil().dateToString(new Date(), DATE_FORMAT)+remark);
					csChjSign.setCscsUpdateTime(new Date());
					csChjSign.update();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 取消预订
	 * @return
	 */
	public String cancelbuy(){
		
		delUserBookAndDecrCount(DecrCountOpType.cancelbuy);
		
		$.SendHtml("1001", "UTF-8");
		
		return null;
	}
	
	/**
	 * 获取套餐包当前预订人数
	 * @return
	 */
	public int getCurrentPackBookCount(String cspFlag, int step, Long packId){
		
		//读取缓存
		String currentPackKey = cspFlag+"-"+step;
		String val = jRedisClient.get(currentPackKey);
		Long bookCount = 0L;
		if(val !=null && !val.equals("")){
			bookCount = Long.valueOf(val);
			if(bookCount <0){
				bookCount = 0L;
				System.out.println("======================================缓存不一致===============================");
				jRedisClient.set(currentPackKey, "0");
			}
		}

		//读取数据库
		if(read4DB){
			ICsChjSignService csChjSignService = $.getBean("csChjSignService");
			//根据套餐包ID + 预订状态统计已预订的人数
			Long count = csChjSignService.getCsChjSignCount($.add(CsChjSign.F.cscsLocked, packId)
					.add(CsChjSign.F.cscsStatus, (short)1)
					.add(CsChjSign.F.cscsTarget, currentPackKey)
					.add(CsChjSign.F.cscsYear, 2015));
			return count == null ? 0 : count.intValue();
		}
		
		return bookCount.intValue();
	}
	
	/**
	 * 
	 * 购买确认
	 * 
	 * 1、校验是否登录
	 * 2、校验参数是否正确，套餐存在
	 * 3、判断用户是否已购买了套餐（已购买成功，已准许预定）
	 *    先读缓存是否已含购买标记
	 * 4、判断活动是否已过期
	 * 5、判断套餐是否已卖完
	 * 6、预定并扣减库存
	 * 		成功-》
	 * 		失败-》
	 * 			余额不足-》
	 * 			其它-》
	 * 
	 * @return
	 */
	public String buy(){		
		try{
			//判断用户是否登录	
			if(!SystemHelper.isLogin()){
				$.SendHtml("1001", "UTF-8");
				return null;
			}	
			
			//已购买
			UserBook bought_val = getUserBook(getUserBoughtTag());
			if(bought_val!=null){
				$.SendHtml("1003", "UTF-8");
				return null;
			}
			
			//已预定
			UserBook reserve_val = getUserBook(getUserBookTag());
			if(reserve_val!=null){
				//检查是否已超时
				if(!isExpire(reserve_val)){
					$.SendHtml("1004", "UTF-8");
					return null;
				}else{
					delUserBookAndDecrCount(DecrCountOpType.expired);
				}
			}
			
			CsMember csMember = csMemberService.getCsMemberById(SystemHelper.getLoginId());		
			if(csMember!=null){
				if(csMember.getCsmVDrive()==null || csMember.getCsmVReal()==null){
					$.SendHtml("1008", "UTF-8");
					return null;
				}
				if(csMember.getCsmVDrive()!=1 || csMember.getCsmVReal()!=1){
					$.SendHtml("1008", "UTF-8");
					return null;
				}
			}
			
			//参数异常
			String cspId = $.getString("cspId");
			if(StringUtils.isEmpty(cspId)){
				$.SendHtml("1002", "UTF-8");
				return null;
			}
			
			List<CsPack> packs = csPackService.getCsPackList($.add("definex",""+CsPack.C.cspFlag+" like '"+NEW_YEAR_PACK_2015_+"%'"), -1);
			CsPack pack = null;
			String sIds = "";
			for(CsPack csPack:packs){
				if(!$.empty(sIds))
					sIds+=",";
				sIds+=csPack.getCspId();
				if(cspId.equals(csPack.getCspId().toString())){
					pack = csPack;
				}
			}
			
			//参数异常
			if($.empty(sIds) || sIds.indexOf(cspId) == -1 || pack == null){
				$.SendHtml("1002", "UTF-8");
				return null;
			}
			
			//判断用户是否已经购买过套餐
			CsUserPack csUserPack = csUserPackService.getCsUserPack(
				$.add(CsUserPack.F.csupMember, SystemHelper.getLoginId())
				.add("definex","csup_pack in ("+sIds+")")
				.add(CsUserPack.F.csupStatus, 1)
			);
			if(csUserPack!=null){
				$.SendHtml("1003", "UTF-8");
				return null;
			}		
			
			//判断活动是否已经结束
			if(getNow().after(pack_end_time)){
				$.SendHtml("1005", "UTF-8");
				return null;
			}
			
			//判断活动是否已经开始
			if(getNow().before(pack_begin_time)){
				$.SendHtml("1006", "UTF-8");
				return null;
			}
			
			Long[] step = getCurrentStep(getNow());
			Map<String, Meal> meals = getCurrentStepMeals(step[0].intValue());
			int bookCount = getCurrentPackBookCount(pack.getCspFlag(), step[0].intValue(), pack.getCspId());
						
			//判断当前用户选择的类型套餐是否已经卖完
			Long saled = csUserPackService.getCsUserPackCount(
				$.add(CsUserPack.F.csupPack, pack.getCspId())
				.add(CsUserPack.F.csupStatus, 1)
				.add("definex", CsUserPack.C.csupRemark + " ='" +pack.getCspName() + getCurrentStepActivityRemark(step[0].intValue()) + "'")
			);
			System.out.println("=============================="+pack.getCspFlag()+"已买"+saled+"已抢购"+bookCount+"==============================");
			if((saled+bookCount) >= meals.get(pack.getCspFlag()).getCount()){
				$.SendHtml("1007", "UTF-8");
				return null;
			}
			
			//-----------------------------保存订单-》自增已定人数---------------
			UserBook book = new UserBook();
			book.setResId(pack.getCspId());
			book.setUserId(SystemHelper.getLoginId());
			book.setDate(new Date());
			saveUserBook(getUserBookTag(), book);
			String currentPackKey = pack.getCspFlag()+"-"+step[0];
			Long nowCount = jRedisClient.incr(currentPackKey);
			if(nowCount == null){
				$.SendHtml("1009", "UTF-8");
				return null;
			}
			//------------------------------------------------------------------
			
			try{
				ICsChjSignService csChjSignService = $.getBean("csChjSignService");
				CsChjSign csChjSign = csChjSignService.getCsChjSign($.add(CsChjSign.F.cscsMember, SystemHelper.getLoginId())
						.add(CsChjSign.F.cscsYear, 2015));
				
				//未报名
				if(csChjSign == null){
					$.SendHtml("1002", "UTF-8");
					return null;
				}
				//已预订或已购买
				if(csChjSign.getCscsStatus().intValue() >0){
					$.SendHtml("1003", "UTF-8");
				}
				
				csChjSign.setCscsRemark(csChjSign.getCscsRemark()+"#"+new DateUtil().dateToString(new Date(), DATE_FORMAT)+"预订订单"+currentPackKey);
				csChjSign.setCscsLocked(pack.getCspId());
				csChjSign.setCscsTarget(currentPackKey);	//套餐包（临时用下）
				csChjSign.setCscsLockTime(new Date());
				csChjSign.setCscsStatus((short)1);	//已预订
				csChjSign.update();
			}catch(Exception e){
				e.printStackTrace();
			}
			
			$.SendHtml("1010", "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			
			$.SendHtml("1009", "UTF-8");
			return null;
		}
		return null;
	}
	
	/**
	 * 获取当前批次活动数量
	 * @param currentStep
	 * @return
	 */
	public String getCurrentStepActivityRemark(int currentStep){
		String remark = "";
		if(currentStep == 1){
			remark = "";
		}else if(currentStep == 2){
			remark = "第二轮";
		}else if(currentStep == 3){
			remark = "第三轮";
		}
		return remark;
	}
	
	
	/**
	 * 支付
	 * @return
	 */
	public String doPay(){
		
		//判断用户是否登录	
		if(!SystemHelper.isLogin()){
			$.SendHtml("1001", "UTF-8");
			return null;
		}	
		
		//已购买成功
		UserBook bought_val = getUserBook(getUserBoughtTag());
		if(bought_val!=null){
			$.SendHtml("1003", "UTF-8");
			return null;
		}
		
		//已预定
		UserBook reserve_val = getUserBook(getUserBookTag());
		if(reserve_val!=null){
			//检查是否已超时
			if(isExpire(reserve_val)){
				cancelbuy();
				$.SendHtml("1004", "UTF-8");
				return null;
			}
		}else{
			//未预定
			$.SendHtml("1005", "UTF-8");
			return null;
		}
		
		try{
			CsPack pack = csPackService.getCsPack($.add(CsPack.F.cspId, reserve_val.getResId()));
			if(pack == null){
				//系统异常
				$.SendHtml("1006", "UTF-8");
				return null;
			}
			Long[] step = getCurrentStep(getNow());
			
			CsUserPack userPack = packageService.executeBuyPackage(SystemHelper.getLoginId(), pack.getCspId(), pack.getCspName() + getCurrentStepActivityRemark(step[0].intValue()), true);
			if(userPack != null){
				UserBook book = new UserBook();
				book.setResId(pack.getCspId());
				book.setUserId(SystemHelper.getLoginId());
				book.setDate(new Date());
				saveUserBook(getUserBoughtTag(), book);
				
				//减库存
				delUserBookAndDecrCount(DecrCountOpType.payment);
			}
			$.SendHtml("1010", "UTF-8");
			return null;
		}catch(Exception e){
			e.printStackTrace();
			
			if(e.getMessage() !=null){
				//系统异常
				if(e.getMessage().equals("会员可用余额不足")){
					$.SendHtml("1007", "UTF-8");
				}
				else if(e.getMessage().equals("您已经购买了该套餐")){
					$.SendHtml("1003", "UTF-8");
				}
				else if(e.getMessage().equals("当前套餐不能购买")){
					$.SendHtml("1006", "UTF-8");
				}
				else{
					$.SendHtml("1006", "UTF-8");
				}
			}
			else{
				$.SendHtml("1006", "UTF-8");
			}
			return null;
		}
	}
	
	/**
	 * 套餐详情
	 * @return
	 */
	public String packDetail(){
		Long[] step = getCurrentStep(getNow());
		
		Date now = getNow();
		if(now == null){
			now = new Date();
		}
		
		$.setRequest("currentStep", step[0]);
		$.setRequest("remainCarTime", car_begin_time.getTime() - now.getTime());
		
		//判断用户是否已经购买过套餐
		List<CsPack> packs = csPackService.getCsPackList($.add("definex",""+CsPack.C.cspFlag+" like '"+NEW_YEAR_PACK_2015_+"%'"), -1);
		String sIds = "";
		for(CsPack csPack:packs){
			if(!$.empty(sIds))
				sIds+=",";
			sIds+=csPack.getCspId();
		}
		
		//取一个套餐
		CsUserPack csUserPack = csUserPackService.getCsUserPack(
				$.add(CsUserPack.F.csupMember, SystemHelper.getLoginId())
				.add("definex","csup_pack in ("+sIds+")")
				.add(CsUserPack.F.csupStatus, 1));
		if(csUserPack !=null){
			UserBook userBook = getUserBook(getUserBoughtTag());
			if(userBook ==null){
				userBook = new UserBook();
				userBook.setResId(csUserPack.getCsupPack());
				userBook.setUserId(SystemHelper.getLoginId());
				userBook.setDate(new Date());
				saveUserBook(getUserBoughtTag(), userBook);
			}
			
			CsPack pack = csPackService.getCsPack($.add(CsPack.F.cspId, userBook.getResId()));
			if(pack != null){
				$.setRequest("carType", pack.getCspFlag());
			}
			
		}else{
			UserBook userBook = getUserBook(getUserBoughtTag());
			if(userBook !=null){
				jRedisClient.set(getUserBoughtTag()+"-", userBook.getUserId() +"-"+ userBook.getResId()+"-"+userBook.getDate());
				jRedisClient.del(getUserBoughtTag());
			}
			$.setRequest("noPack", 1);
		}
		
//		ICsAreaService csAreaService = Lazy.GetSpringBean("csAreaService");
//		List<CsArea> areas = csAreaService.getCsAreaList($.add(CsArea.F.csaHost, SrvHost.HangZhou).add("desc", CsArea.C.csaLevel), -1);
//		Lazy.SetRequest("areas", areas);
		
		if(step[0] ==0){
			$.setRequest("activity_state", 1);
		}else if(step[0] >0 && step[0] <4){
			$.setRequest("activity_state", 2);
		}else if(step[0] == 5){
			$.setRequest("activity_state", 3);
		}else if(step[0] == 4){
			$.setRequest("activity_state", 4);
		}else if(step[0]>5){
			$.setRequest("activity_state", 5);
		}
		
		$.setRequest("now", step[1]);
		
		return "detail";
	}
	
	public IPackageService getPackageService() {
		return packageService;
	}

	public void setPackageService(IPackageService packageService) {
		this.packageService = packageService;
	}

	public ICsPackService getCsPackService() {
		return csPackService;
	}

	public void setCsPackService(ICsPackService csPackService) {
		this.csPackService = csPackService;
	}

	public ICsPackInfoService getCsPackInfoService() {
		return csPackInfoService;
	}

	public void setCsPackInfoService(ICsPackInfoService csPackInfoService) {
		this.csPackInfoService = csPackInfoService;
	}

	public ICsUserPackService getCsUserPackService() {
		return csUserPackService;
	}

	public void setCsUserPackService(ICsUserPackService csUserPackService) {
		this.csUserPackService = csUserPackService;
	}

	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}

	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}

	public ICsChjSignService getCsChjSignService() {
		return csChjSignService;
	}

	public void setCsChjSignService(ICsChjSignService csChjSignService) {
		this.csChjSignService = csChjSignService;
	}

	public DefaultJRedisClient getjRedisClient() {
		return jRedisClient;
	}

	public void setjRedisClient(DefaultJRedisClient jRedisClient) {
		this.jRedisClient = jRedisClient;
	}

	public String check(){
		int opType = $.getInteger("optype");
		
		Map<String, Object> o = new HashMap<String, Object>();
		//检查当前状态
		if(opType == 1){
			Long[] step = getCurrentStep(getNow());
			getCurrentPackBookCount("NEW_YEAR_PACK_2015_KY", step[0].intValue(), 9L);
			getCurrentPackBookCount("NEW_YEAR_PACK_2015_MG", step[0].intValue(), 10L);
			getCurrentPackBookCount("NEW_YEAR_PACK_2015_SMART", step[0].intValue(), 11L);
		}
		return "";
	}
	
}
