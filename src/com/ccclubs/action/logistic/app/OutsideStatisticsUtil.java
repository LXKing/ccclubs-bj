package com.ccclubs.action.logistic.app;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSON;

import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsOutsideStatistics;
import com.ccclubs.model.SrvUser;
import com.ccclubs.service.admin.ICsAreaService;
import com.ccclubs.service.admin.ICsOutletsService;
import com.ccclubs.service.admin.ICsOutsideStatisticsService;
import com.lazy3q.web.helper.$;


public class OutsideStatisticsUtil {
	
	ICsOutsideStatisticsService csOutsideStatisticsService ;
	
	ICsOutletsService csOutletsService;
	
	/**
	 * 整备提交
	 */
	public static void  finish(SrvUser user,Long carId,Long caseId){
		//
		Map<String, Object>map=new HashMap<>();
		map.put("csosStaff", user.getSuId());//员工
		map.put("csosCar", carId);//车辆id
		map.put("csosProperty", caseId);//整备项
		map.put("csosStatus", 1);
		
		ICsOutsideStatisticsService csOutsideStatisticsService = $.GetSpringBean("csOutsideStatisticsService");
		CsOutsideStatistics outsideStatistics=csOutsideStatisticsService.getOutsideStatistics(map);
		if(outsideStatistics!=null&&outsideStatistics.getCsosFinishTime()==null){//无创建
			Date nowDate=new Date();
			long workTime=nowDate.getTime()-outsideStatistics.getCsosAddTime().getTime();
			outsideStatistics.setCsosWorkTime(workTime);//作业时长
			outsideStatistics.setCsosFinishTime(nowDate);//整备完成时间
			outsideStatistics.setCsosStatus((short)2);
			csOutsideStatisticsService.updateCsOutsideStatistics$NotNull(outsideStatistics);//更新
		}
	}
	
	/**
	 * 远程控制
	 * @param type  0:远程开门 1:远程关门 2:允许启动 17:远程鸣笛
	 * @param carId 
	 * @param type
	 * @param caseId //整备项
	 */
	public boolean updateRemoteControlCount(SrvUser user,CsCar csCar,String type,Long caseId ,String memberLocation){
		//
		Map<String, Object>map=new HashMap<>();
		map.put("csosStaff", user.getSuId());//员工
		map.put("csosCar", csCar.getCscId());//车辆id
		map.put("csosProperty", caseId);//整备项
		map.put("csosStatus", 1);
		//
		ICsOutsideStatisticsService csOutsideStatisticsService = $.GetSpringBean("csOutsideStatisticsService");
		CsOutsideStatistics outsideStatistics=csOutsideStatisticsService.getOutsideStatistics(map);
		/**
		 * 重新开始一段流程
		 * 插入一条记录
		 */
		if(outsideStatistics==null){//无创建
			outsideStatistics=new CsOutsideStatistics();
			getRemoteType(outsideStatistics,type);//远程控制类别
			outsideStatistics.setCsosUser(user.getSuId());//员工
			outsideStatistics.setCsosProperty(caseId);//整备项
			outsideStatistics.setCsosCar(csCar.getCscId());//车辆id
			outsideStatistics.setCsosHost(csCar.getCscHost());//城市
			outsideStatistics.setCsosOutlets(csCar.getCscOutlets());//网点
			//车辆经纬度
			String str=csCar.getCscLatitude()+","+csCar.getCscLongitude();
			outsideStatistics.setCsosCarLocation(str);
			//人员位置
			outsideStatistics.setCsosMemberLocation(memberLocation);
			//人车距离计算
			Double distance=null;
			if(memberLocation!=null){
				String[] locationStr=memberLocation.split(",");
				String str1="0";
				String str2="0";
				if(locationStr.length>1){
					str1=locationStr[0];//纬度
					str2=locationStr[1];//经度
					distance=getDistance(str1,str2,csCar.getCscLatitude()+"",csCar.getCscLongitude()+"");
					outsideStatistics.setCsosDistance(distance);
				}
			}
			//
			ICsOutletsService csOutletsService= $.GetSpringBean("csOutletsService");
			CsOutlets	csOutlet=csOutletsService.getCsOutletsById(csCar.getCscOutlets());
			outsideStatistics.setCsosArea(csOutlet.getCsoArea());//设置运营区域
			//
			outsideStatistics.setCsosAddTime(new Date());
			outsideStatistics.setCsosUpdateTime(new Date());
			//状态：1=未完成  2=已完成
			outsideStatistics.setCsosStatus((short)1);
			csOutsideStatisticsService.saveCsOutsideStatistics(outsideStatistics);
		}else if(outsideStatistics!=null&&outsideStatistics.getCsosFinishTime()==null){////这一次的操作流程没有结束
			//
			getRemoteType(outsideStatistics,type);
			csOutsideStatisticsService.updateCsOutsideStatistics$NotNull(outsideStatistics);//更新次数
		}
		return true;
	}
	
	//0:远程开门 1:远程关门 2:允许启动 3:远程鸣笛
	public CsOutsideStatistics getRemoteType(CsOutsideStatistics  outsideStatistics,String type){
		int count=1;
		if("0".equals(type)){//0:远程开门
			if(outsideStatistics.getCsosOpenDoorCount()!=null){
				count=outsideStatistics.getCsosOpenDoorCount()+1;
			}
			outsideStatistics.setCsosOpenDoorCount(count);
			if(outsideStatistics.getCsosOpenDoorTime()==null){//首次关门时间
				outsideStatistics.setCsosOpenDoorTime(new Date());
			}
			
		}else if("1".equals(type)){//1:远程关门
			if(outsideStatistics.getCsosCloseDoorCount()!=null){
				count=outsideStatistics.getCsosCloseDoorCount()+1;
			}
			outsideStatistics.setCsosCloseDoorCount(count);
			if(outsideStatistics.getCsosCloseDoorTime()==null){//首次关门时间
				outsideStatistics.setCsosCloseDoorTime(new Date());
			}
		}else if("2".equals(type)){//2:允许启动
			
			 if(outsideStatistics.getCsosStartCount()!=null){
				 count=outsideStatistics.getCsosStartCount()+1;
			}
			outsideStatistics.setCsosStartCount(count);
		}else if("3".equals(type)){//17:远程鸣笛
			 if(outsideStatistics.getCsosWhistleCount()!=null){
				 count=outsideStatistics.getCsosWhistleCount()+1;
			 }
			outsideStatistics.setCsosWhistleCount(count);
		}
		outsideStatistics.setCsosUpdateTime(new Date());
		return outsideStatistics;
	}

	
	 public static double getDistance(String latStr1,String lngStr2,String latStr11,String lngStr12){
	 	
		Double lat1 = Double.parseDouble(latStr1);// 传值给经度
	    Double lng1 = Double.parseDouble(lngStr2);// 传值给纬度
	    Double lat2 = Double.parseDouble(latStr11);// 传值给经度
	    Double lng2 = Double.parseDouble(lngStr12);// 传值给纬度
	    //
	    double EARTH_RADIUS = 6371.393; 
		double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
                Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
	    
	 }
	 private static double rad(double d){  
	       return d * Math.PI / 180.0;  
	 }  
	

	public ICsOutsideStatisticsService getCsOutsideStatisticsService() {
		return csOutsideStatisticsService;
	}


	public void setCsOutsideStatisticsService(
			ICsOutsideStatisticsService csOutsideStatisticsService) {
		this.csOutsideStatisticsService = csOutsideStatisticsService;
	}

	public ICsOutletsService getCsOutletsService() {
		return csOutletsService;
	}

	public void setCsOutletsService(ICsOutletsService csOutletsService) {
		this.csOutletsService = csOutletsService;
	}
	
	
	public static void main(String[] orgs){
		OutsideStatisticsUtil outsideStatisticsUtil=new OutsideStatisticsUtil();
		double ll=outsideStatisticsUtil.getDistance("117.11811","36.68484","117.00999000000002","36.66123");
		System.out.println(ll);
		
	}
}
