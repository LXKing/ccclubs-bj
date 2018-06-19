package com.ccclubs.action.api.webservice.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.ccclubs.action.api.webservice.ICarStateService;
import com.ccclubs.action.api.webservice.struct.CsCarRentRecord;
import com.ccclubs.action.api.webservice.struct.CsCarType;
import com.ccclubs.action.api.webservice.struct.CsOutletsRecord;
import com.ccclubs.action.api.webservice.struct.DataFlag;
import com.ccclubs.action.api.webservice.struct.EVTime;
import com.ccclubs.action.api.webservice.struct.MOData;
import com.ccclubs.action.api.webservice.struct.MOData.定位数据;
import com.ccclubs.action.api.webservice.struct.MOData.整车数据;
import com.ccclubs.action.api.webservice.struct.MOData.极值数据;
import com.ccclubs.action.api.webservice.struct.MOData.续航数据;
import com.ccclubs.dao.ICsInfoDistributeDao;
import com.ccclubs.dao.ICsOrderDao;
import com.ccclubs.dao.ICsOutletsDao;
import com.ccclubs.model.SrvApiClient;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsHistoryState;
import com.ccclubs.model.CsState;
import com.ccclubs.model.SrvHost;
import com.ccclubs.util.struct.Struct;
import com.lazy3q.web.helper.$;

public class CarStateService implements ICarStateService {
	
	
	public String uploadPacketData(String userName,String pwd,String packetData){
		try{
			SrvApiClient srvApiClient = SrvApiClient.Get($.add(SrvApiClient.F.sacClient, userName).add(SrvApiClient.F.sacSecret, pwd));
			if(srvApiClient==null)
				return "用户名密码输入错误";
			if(!Pattern.matches("[a-fA-F0-9]+", packetData))
				return "发送的数据不是HEX字符";
			$.trace("接收到数据包",packetData.length());
			byte[] bytes = getBytesFromString(packetData);
			List<MOData> datas = CarStateService.parseMOData(packetData);
			saveMOData(datas);
			return "发送成功";
		}catch(EOFException ex){
			ex.printStackTrace();
			return "数据字节长度不对";
		}catch(Exception ex){
			ex.printStackTrace();
			return "系统繁忙，请稍候再试";
		}
	}
	
	
	static Map<String,String> vinmap = new HashMap();
	public static String getNumberByVin(String vin){
		String number = vinmap.get(vin.toUpperCase());
		if(number!=null)
			return number;
		CsCar csCar = CsCar.Get($.add(CsCar.F.cscVin, vin));
		if(csCar!=null){
			number = csCar.getCscNumber();
			vinmap.put(vin.toUpperCase(), number);
		}
		return number;
	}
	
	/**
	 * 保存状态数据
	 */
	public void saveMOData(List<MOData> datas){
		for(MOData moData:datas){
			
			boolean saveEvFlag = true;	//是否最终保存当前状态
			
			//根据VIN码获取车牌号
			String number = getNumberByVin(moData.识别码vin);
			if($.empty(number))continue;
			//根据车牌号到当前状态表中取当前状态
			CsState state = CsState.Get($.add(CsState.F.cssNumber, number));//上位机写入的数据
			CsState evstate = CsState.Get($.add(CsState.F.cssNumber, "EV"+number));//接收到的数据
			CsCar csCar = CsCar.Get($.add(CsCar.F.cscNumber, number));
			if(csCar==null){
				$.trace("未识别的车牌号码",number);
				continue;
			}
			if(evstate==null){
				evstate = new CsState();
				evstate.setCssNumber("EV"+number);//车牌号码
				if(csCar!=null){
					evstate.setCssHost(csCar.getCscHost());//城市
					//如果是杭州，放弃保存
					if(SrvHost.isHangZhou(csCar.getCscHost())){
						saveEvFlag = false;
					}
				}else{
					continue;
				}
			}else{
				//如果是杭州，删除旧数据
				if(SrvHost.isHangZhou(evstate.getCssHost())){
					evstate.delete();
					saveEvFlag = false;
				}
			}
			
			evstate.setCssHost(csCar.getCscHost());
			
			for(Struct struct:moData.datas){
				if(struct.getClass()==EVTime.class){
					EVTime item = (EVTime) struct;
					evstate.setCssAddTime(item.getTime());
				}else if(struct.getClass()==MOData.整车数据.class){
					MOData.整车数据 item = (整车数据) struct;
					evstate.setCssSpeed(""+item.车速/10);//单位：0.1公里
					evstate.setCssMileage(""+item.里程/10);//单位：0.1公里
					evstate.setCssCharging(item.充放电状态==1?"0":"1");
					evstate.setCssMotor(""+item.电机转速);
				}else if(struct.getClass()==MOData.定位数据.class){
					MOData.定位数据 item = (定位数据) struct;
					evstate.setCssLatitude(new Double(item.纬度)/1000000d+"");
					evstate.setCssLongitude(new Double(item.经度)/1000000d+"");
				}else if(struct.getClass()==MOData.续航数据.class){
					MOData.续航数据 item = (续航数据) struct;
					evstate.setCssEndurance(""+item.续航里程);
				}else if(struct.getClass()==MOData.极值数据.class){
					MOData.极值数据 item = (极值数据) struct;
					int soc = item.SOC & 0xFF;//把无符号byte转成int					
					evstate.setCssEvBattery(soc*100/255+"%");
				}
			}
			//如果时间与当前时间相差太大(超过10分钟)，则放弃写入
			/*if(evstate.getCssAddTime()==null || Math.abs(evstate.getCssAddTime().getTime()-new Date().getTime())>(10*60*1000)){
				$.trace(evstate.getCssNumber(),"如果时间与当前时间相差太大(超过10分钟)，则放弃写入");
				if(evstate.getCssId()!=null)
					evstate.delete();
				continue;
			}
			*/
			if(evstate.getCssAddTime()==null || Math.abs(evstate.getCssAddTime().getTime()-new Date().getTime())>(10*60*1000)){
				$.trace(evstate.getCssNumber(),"提示。。。如果时间与当前时间相差太大(超过10分钟)");
			}
			
			//把本系统上位机的状态同步到接收到的状态中
			if(state!=null){
				evstate.setCssHost(state.getCssHost());
				evstate.setCssCurrentTime(state.getCssCurrentTime());
				evstate.setCssCsq(state.getCssCsq());
				evstate.setCssElectricMileage(state.getCssElectricMileage());
				evstate.setCssFuelMileage(state.getCssFuelMileage());
				evstate.setCssOil(state.getCssOil());
				evstate.setCssOrder(state.getCssOrder());
				evstate.setCssPower(state.getCssPower());
				evstate.setCssPowerConsumption(state.getCssPowerConsumption());
				evstate.setCssRented(state.getCssRented());
				evstate.setCssRfid(state.getCssRfid());
				evstate.setCssWarn(state.getCssWarn());
			}
			
			if(saveEvFlag){
				if(evstate.getCssId()==null)
					evstate.save();
				else
					evstate.update();
			}

			$.setLocal("number", csCar.getCscNumber());
			//同时保存一个历史状态
			CsHistoryState csHistoryState = new CsHistoryState(
			 	evstate.getCssHost()//城市 [非空]
			 	,csCar.getCscNumber()//车牌号 [非空]
			 	,evstate.getCssAddTime()//添加时间 [非空]
			 	,evstate.getCssCurrentTime()//下位机时间
			 	,evstate.getCssRented()//租赁状态
			 	,evstate.getCssWarn()//有报警
			 	,evstate.getCssRfid()//RFID卡号
			 	,evstate.getCssRfidDte()//用户RFID
			 	,evstate.getCssObdMile()//OBD里程
			 	,evstate.getCssEngineT()//发动机温度
			 	,evstate.getCssMileage()//总里程
			 	,evstate.getCssSpeed()//车速
			 	,evstate.getCssMotor()//转速
			 	,evstate.getCssOil()//燃油量
			 	,evstate.getCssPower()//蓄电量
			 	,evstate.getCssEvBattery()//动力电池电量
			 	,evstate.getCssCharging()//在充电
			 	,evstate.getCssFuelMileage()//油里程
			 	,evstate.getCssElectricMileage()//电量程
			 	,evstate.getCssEndurance()//续航里程
			 	,evstate.getCssTemperature()//温度
			 	,evstate.getCssCsq()//信号强度
			 	,evstate.getCssPowerConsumption()//功耗
			 	,evstate.getCssLongitude()//经度
			 	,evstate.getCssLatitude()//纬度
			 	,evstate.getCssOrder()//当前订单
			 	,$.json(moData)//接口数据保存
			 );		
			 csHistoryState.setSuffix("_"+csCar.getCscNumber());
			 csHistoryState.save();
		}
	}
	
	
	
	public static List<MOData> parseMOData(String packetData) throws Exception{
		List<MOData> array = new ArrayList();
		packetData=packetData.replace("|", "");
		byte[] bytes = CarStateService.getBytesFromString(packetData);
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
		while(dis.available()>0){
			MOData moData = Struct.readObject(dis, MOData.class);
			DataInputStream itemdis = new DataInputStream(new ByteArrayInputStream(moData.bytes));
			EVTime time = Struct.readObject(itemdis, EVTime.class);
			moData.datas.add(time);
			while(itemdis.available()>0){
				DataFlag dataFlag = Struct.readObject(itemdis, DataFlag.class);
				Struct item = null;
				switch(dataFlag.信息类型标识){
				case 1:
					item = Struct.readObject(itemdis, MOData.单体蓄电池电压.class);
					break;
				case 2:
					item = Struct.readObject(itemdis, MOData.动力蓄电池包温度数据.class);
					break;
				case 3:
					item = Struct.readObject(itemdis, MOData.整车数据.class);
					break;
				case 4:
					item = Struct.readObject(itemdis, MOData.定位数据.class);
					break;
				case 5:
					item = Struct.readObject(itemdis, MOData.极值数据.class);
					break;
				case 6:
					item = Struct.readObject(itemdis, MOData.报警数据.class);
					break;
				case (byte) 0xe0:
					item = Struct.readObject(itemdis, MOData.续航数据.class);
					break;
				}
				if(item!=null)
					moData.datas.add(item);
				else{
					$.trace("数据结构发现未识别的标识",itemdis.available());
					Struct.trace(moData.bytes);
					break;
				}
			}
			array.add(moData);
		}
		return array;
	}
	
	
	
	public static byte[] getBytesFromString(String packetData){
		int length = packetData.length() / 2;
        byte[] bytes = new byte[length];
        byte[] source = packetData.getBytes();
        for (int i = 0; i < bytes.length; ++i) {
            byte bh = Byte.decode("0x" + new String(new byte[]{source[i * 2]})).byteValue();
            bh = (byte)(bh << 4);
            byte bl = Byte.decode("0x" + new String(new byte[]{source[i * 2 + 1]})).byteValue();
            bytes[i] = (byte)(bh ^ bl);
        }
		return bytes;
	}
	
	/**
	 * 数据包装
	 * @param car_vin	车辆识别码/租赁点识别码
	 * @param data		byte数组的数据
	 * @param type		1、车辆租赁数据，2、租赁点数据
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String enwrap(String car_vin, byte[] data, int dtype) throws UnsupportedEncodingException{
			byte[]	start  =    {0x23, 0x23};							//起始符		2
			
			byte[] bcc_arr = 	new byte[data.length + 22];				//识别码(17)位+命令单元(2)位+start(2)位
			//命令单元	2
			bcc_arr[0]	=		(byte)(dtype == 1 ? 0x0A : 0x0C);
			bcc_arr[1]	=		(byte)0xFE;
			
			//车辆识别码	17
			byte[]	byte_vin =	StringUtils.leftPad(car_vin, 17, " ").getBytes("GBK");
			for(int i=0; i<byte_vin.length; i++){
				bcc_arr[2+i] = byte_vin[i];
			}
			bcc_arr[20]	= 		0x00;									//数据加密方式，不加密
			bcc_arr[21]	= 		(byte)data.length;						//数据长度	1
			
			for(int i =0; i<data.length; i++){
				bcc_arr[22+i] = data[i];
			}
			String valid_code_hexcode = Struct.getBCC(bcc_arr);
			
			StringBuffer sbf = new StringBuffer();
			sbf.append(Struct.bytesToHexString(start));
			sbf.append(Struct.bytesToHexString(bcc_arr));
			sbf.append(valid_code_hexcode);
			return sbf.toString().toUpperCase();
	}
	
	/**
	 *  生成车辆租赁发送数据 
	 */
	@Override
	public String getCarRentData(){
		List<CsCarRentRecord> rentRecordList = genCarRentRecordData();
		if(!CollectionUtils.isEmpty(rentRecordList)){
			StringBuffer sbf = new StringBuffer();
			for(int i = 0; i<rentRecordList.size(); i++){
				sbf.append(i==0?"":"|").append(convertRentData(rentRecordList.get(i)));
			}
			return sbf.toString();
		}
		return null;
	}
	
	/**
	 * 生成车辆网点发送数据
	 */
	@Override
	public String getOutletsData() {
		List<CsOutletsRecord> outletsList = genOutletsRecordData();
		if(!CollectionUtils.isEmpty(outletsList)){
			StringBuffer sbf = new StringBuffer();
			for(int i = 0; i<outletsList.size(); i++){
				sbf.append(i==0?"":"|").append(convertOutletsData(outletsList.get(i)));
			}
			return sbf.toString();
		}
		return null;
	}
	
	/**
	 * 转换车辆租赁状态数据
	 * @param rentRecord
	 * @return
	 */
	public static String convertRentData(CsCarRentRecord rentRecord){
		try {
			byte[] byteData = Struct.getObjectBytesExt(rentRecord);
			return enwrap(rentRecord.getCarvin(), byteData, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 转换租赁点数据
	 * @param outletsRecord
	 * @return
	 */
	public static String convertOutletsData(CsOutletsRecord outletsRecord){
		if(outletsRecord!=null){
			ByteArrayOutputStream baOs = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(baOs);
			try {
				//租赁点数据
				byte[] outlets_byte = Struct.getObjectBytesExt(outletsRecord);
				dos.write(outlets_byte);
				
				//车型编号1...N
				List<CsCarType> carTypeList = outletsRecord.getCarTypeList();
				if(!CollectionUtils.isEmpty(carTypeList)){
					for(CsCarType carType : carTypeList){
						
						byte[] cartype_byte = Struct.getObjectBytesExt(carType);
						dos.write(cartype_byte);
						
						//车型编号车牌1...N
						List<String> carNumList = carType.getTypeNumber();
						if(!CollectionUtils.isEmpty(carNumList)){
							for(String  carNum: carNumList){
								dos.write(Struct.lpadBytes(8, carNum.getBytes("GBK")));
							}
						}
					}
				}
				dos.write(Struct.lpadBytes(8, (outletsRecord.getRemain() == null ? "":outletsRecord.getRemain()).getBytes("GBK")));
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				return enwrap(outletsRecord.getOutletsId(), baOs.toByteArray(), 2);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 生成车辆状态数据
	 * @return
	 */
	List<CsCarRentRecord> genCarRentRecordData(){
		List<CsCarRentRecord> carRentRecordList = new ArrayList<CsCarRentRecord>();
		ICsInfoDistributeDao infoDistributeDao = $.GetSpringBean("infoDistributeDao");
		List<HashMap> dataList = infoDistributeDao.getCsCarAndOrder();
		if(!CollectionUtils.isEmpty(dataList)){
			for(HashMap<?,?> dataMap : dataList){
				CsCarRentRecord record = new CsCarRentRecord();
				byte rented = (byte) (dataMap.get("cso_id")==null ? 0x01 : 0x03);	//租出状态：0X01待租状态，0x02 预约状态，0x03 出租状态，0x04 不可租状态；
				byte useType = 0x03;												//默认因公状态：0X01闲置，0x02 因公出行，0x03 因私出行；
				record.setCollectDate(EVTime.convertTime2Byte(Calendar.getInstance().getTime()));
				Date start_date = (Date)dataMap.get("cso_start_time");
				Date finish_date = (Date)dataMap.get("cso_finish_time");
				record.setCarvin(getValue(dataMap.get("csc_vin")));
				record.setStartTime(EVTime.convertTime2Byte(start_date));
				record.setEndTime(EVTime.convertTime2Byte(finish_date));
				record.setCssRented(rented);
				record.setUseType(useType);
				record.setRemain(new byte[]{0x00, 0x00, 0x00, 0x00});				//预留，长度4		
				carRentRecordList.add(record);
			}
		}
		return carRentRecordList;
	}
	
	String getValue(Object obj){
		return obj == null? "": obj.toString();
	}
	
	/**
	 * 生成车辆网点数据
	 * @param outletsRecord
	 * @return
	 */
	List<CsOutletsRecord> genOutletsRecordData(){
		List<CsOutletsRecord> outletsRecordList = new ArrayList<CsOutletsRecord>();
		ICsInfoDistributeDao infoDistributeDao = $.GetSpringBean("infoDistributeDao");
		List<Map> dataList = infoDistributeDao.getCsOutletsAndCarModelData();
		if(!CollectionUtils.isEmpty(dataList)){
			
			Map<String/*outlets*/, List<Map>> outletsMap = new HashMap<String, List<Map>>();
			
			for(Map dataMap: dataList){
				List<Map> outletsList = outletsMap.get(dataMap.get("csc_outlets").toString());
				if(outletsList == null) outletsList = new ArrayList<Map>();
				outletsList.add(dataMap);
				outletsMap.put(dataMap.get("csc_outlets").toString(), outletsList);
			}
			
			for(String key : outletsMap.keySet()){
				CsOutletsRecord record = new CsOutletsRecord();
				List<Map> outletsList = outletsMap.get(key);
				Map datamap = outletsList.get(0);
				BigDecimal cso_latitude = BigDecimal.valueOf(Double.valueOf(getValue(datamap.get("cso_latitude")))).multiply(BigDecimal.valueOf(1000000));
				BigDecimal cso_longitude = BigDecimal.valueOf(Double.valueOf(getValue(datamap.get("cso_longitude"))) * 1000000);
				record.setOutletsId(getValue(datamap.get("csc_outlets")));
				record.setCsoLatitude(cso_latitude.intValue());
				record.setCsoLongitude(cso_longitude.intValue());
				record.setCollectDate(EVTime.convertTime2Byte(Calendar.getInstance().getTime()));
				try {
					record.setCompany(("BJHY  ").getBytes("GBK"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				record.setRemain(null);
				CsOutletsRecord tmp_rc = getCsCarType(outletsList);
				record.setCarTypeList(tmp_rc.getCarTypeList());
				record.setCarTypeCount((byte)tmp_rc.getCarTypeCount());
				outletsRecordList.add(record);
			}
		}
		return outletsRecordList;
	}
	
	/**
	 * 计算网点车型—车牌数据
	 * @param outletsList 一个网点下的所有车辆/车型/车牌数据
	 * @return
	 */
	public CsOutletsRecord getCsCarType(List<Map> outletsList){
		CsOutletsRecord rc = new CsOutletsRecord();
		List<CsCarType> carTypeList = new ArrayList<CsCarType>();
		if(!CollectionUtils.isEmpty(outletsList)){
			Map<String, Map> carModelMap = new HashMap<String, Map>();
			
			for(Map<?, ?> car : outletsList){
				String csc_model = getValue(car.get("csc_model"));
				Map<String, Map<?,?>> childrenMap = carModelMap.get(csc_model);
				if(childrenMap == null)childrenMap = new HashMap();
				childrenMap.put(getValue(car.get("csc_number")), car);
				carModelMap.put(csc_model, childrenMap);
			}
			
			for(String csc_model : carModelMap.keySet()){
				List<String> numberList = new ArrayList<String>();
				CsCarType ctype = new CsCarType();
				ctype.setTypeId(StringUtils.leftPad(csc_model, 10, " ").getBytes());
				
				for(Object csc_number : carModelMap.get(csc_model).keySet()){
					numberList.add(StringUtils.leftPad(getValue(csc_number), 8, ""));
				}
				ctype.setTypeCount(Struct.lpadBytes(2, new byte[]{(byte)numberList.size()}));
				ctype.setTypeNumber(numberList);
				carTypeList.add(ctype);
			}
			rc.setCarTypeCount((byte)carModelMap.size());
			rc.setCarTypeList(carTypeList);
		}
		return rc;
	}

}
