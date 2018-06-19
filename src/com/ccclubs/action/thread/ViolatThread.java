package com.ccclubs.action.thread;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPMessage;

import com.ccclubs.helper.DateHelper;
import com.ccclubs.helper.LoggerHelper;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsViolat;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.admin.ICsViolatService;
import com.ccclubs.util.DateUtil;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;
import com.lazy3q.xml.Item;
import com.lazy3q.xml.Xml;

/**
 * 杭州违章车辆处理线程
 * 
 * @author qsxiaogang
 * 
 */
public class ViolatThread extends Thread {

	ICsViolatService				csViolatService;
	ICsOrderService					csOrderService;
	ICsCarService					csCarService;
	private static String			logPath			= null;
	private static String			errorLogPath	= null;
	private static Lazy3qDaoSupport	dao				= $.getDao("ccclubs_system");
	private static int				MAX_COUNT		= 6;

	@Override
	public void run() {
		while (true) {
			try {
				// $.trace("##########################ViolatThread###############################");
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				int hour = cal.get(Calendar.HOUR_OF_DAY);
				int min = cal.get(Calendar.MINUTE);
				// 每天的8点，15点进行查询
				if (min == 0 && (hour == 7 || hour == 16)) {
					// 遍历出杭州车辆
					String strSql = "select csc_car_no,csc_vin,csc_id from cs_car where csc_number like 'ZJA%' AND csc_vin <>''  order by csc_id desc";

					List<Map> list = dao.executeQuery(strSql);

					for (Map map : list) {
						if (map != null) {
							// 查询车辆违章信息
							List<CsViolat> policeList = policeQuery(map.get(CsCar.C.cscCarNo).toString(), map.get(CsCar.C.cscVin).toString(), (Long) (map.get(CsCar.C.cscId)));

							List<CsViolat> chasedList = chasedQuery(map.get(CsCar.C.cscCarNo).toString(), (Long) (map.get(CsCar.C.cscId)));

							// 有交警违章
							saveViolat(policeList, (short) 0);
							// 有城管违章
							saveViolat(chasedList, (short) 1);

							// 先拿浙A077FX做参考
							// if
							// (map.get(CsCar.C.cscId).toString().equals("1312"))
							// {
							// 2014-11-20 后添加的，状态不等于2的 对应的车辆 违章
							List<CsViolat> currentList = csViolatService.getCsViolatList($.add(CsViolat.F.csvCar, map.get(CsCar.C.cscId)).add("definex", CsViolat.C.csvStatus + " in (0,1) and " + CsViolat.C.csvAddTime + " > '2014-11-20'"), -1);
							if (policeList != null && chasedList != null) {
								if (currentList.size() > 0) {
									for (CsViolat csViolat : currentList) {
										boolean flag = false;
										for (CsViolat csViolatPolice : policeList) {
											if (csViolat.getCsvHappenTime().equals(csViolatPolice.getCsvHappenTime())) {
												flag = true;
												break;
											}
										}
										if (!flag) {
											for (CsViolat csViolatChased : chasedList) {
												if (csViolat.getCsvHappenTime().equals(csViolatChased.getCsvHappenTime())) {
													flag = true;
													break;
												}
											}
										}
										// 未匹配到
										if (!flag) {
											if (csViolat.getCsv22() == null) {
												csViolat.setCsv22(0);
											}
											if (csViolat.getCsv22() > MAX_COUNT) {
												csViolat.setCsvStatus((short) 3);
												csViolat.setCsv22(0);
												csViolat.update();
												// 记录操作日志
												LoggerHelper.writeLog(CsViolat.class, "edit", "更新了违章状态[" + csViolat.getCsvStatus() + "]", 0l, csViolat, csViolat.getCsvId());
											} else {
												csViolat.setCsv22(csViolat.getCsv22() + 1);
												csViolat.update();
											}
										} else {
											// 匹配到，重置查询次数
											if (csViolat.getCsv22() == null || csViolat.getCsv22() > 0) {
												csViolat.setCsv22(0);
												csViolat.update();
												// 记录操作日志
												// LoggerHelper.writeLog(CsViolat.class,
												// "edit", "更新了违章状态[" +
												// csViolat.getCsvStatus() +
												// "]", 0l, csViolat,
												// csViolat.getCsvId());
											}
										}
									}
								}
							}
							// }
						} else {
							writeLog("未查询到符合条件的车辆数据", 1);
						}
						Thread.sleep(20 * 1000);
					}
					Thread.sleep(1 * 60 * 60 * 1000);
				}
				Thread.sleep(10 * 1000);
			} catch (Exception e) {
				writeLog("main -- " + e.getMessage(), 1);
			}
		}
	}

	/**
	 * 交警违章查询 掌上车管app 下载地址：http://www.hzcgs.gov.cn/
	 */
	private List<CsViolat> policeQuery(String carNo, String carVin, Long carId) {
		List<CsViolat> list = new ArrayList<CsViolat>();
		try {
			// 目标URL地址

			String URL = "http://service.zscg.hzcdt.com/api/httpapi";
			// 车架号后6位
			String CLSBDH = carVin.substring(carVin.length() - 6, carVin.length());
			Map<String, String> params = new HashMap<String, String>();
			params.put("xlh", "0C2B3243AFCB169B0E0C07533816A4D3");
			params.put("jkid", "A001674");
			params.put("xmlDoc", URLEncoder.encode("<?xml version='1.0' encoding='gb2312' ?><root><QueryCondition><clsbdh>" + CLSBDH + "</clsbdh><hpzl>02</hpzl><hphm>" + URLEncoder.encode(carNo.toUpperCase(), "utf-8") + "</hphm></QueryCondition></root>", "utf-8"));

			String content = http(URL + "?" + getParams(params), "utf-8", 30 * 1000);

			writeLog(carNo + " -- " + content, 0);
			if (content != null && !content.equals("")) {
				list = parseViolat(content, (short) 0, carId);
			}
			return list;
		} catch (Exception e) {
			writeLog("交警违章查询" + e.getMessage(), 1);
			return null;
		}
	}

	/**
	 * 城管违章查询 贴心城管 app 下载地址 http://www.hzcgw.gov.cn/app/
	 */
	private List<CsViolat> chasedQuery(String carNo, Long carId) {
		List<CsViolat> list = new ArrayList<CsViolat>();
		try {
			// 目标URL地址
			String URL = "http://www.hzcgw.gov.cn:8081/smhdpt/WebService/RemoteSvc";
			String CONTENT = "<v:Envelope xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:d=\"http://www.w3.org/2001/XMLSchema\" xmlns:c=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:v=\"http://schemas.xmlsoap.org/soap/envelope/\"><v:Header /><v:Body><n0:doQueryCarparking id=\"o0\" c:root=\"1\" xmlns:n0=\"http://zfj.bingding.com/\"><request i:type=\"d:anyType\"><carNo i:type=\"d:string\">{0}</carNo><carType i:type=\"d:string\">{1}</carType><pageNo i:type=\"d:int\">0</pageNo><pageSize i:type=\"d:int\">200</pageSize></request></n0:doQueryCarparking></v:Body></v:Envelope>";
			String CAR_TYPE = "小型汽车";

			String content = $.post(URL, CONTENT.replace("{0}", StringEscapeUtils.escapeHtml(carNo.toUpperCase())).replace("{1}", StringEscapeUtils.escapeHtml(CAR_TYPE)), "utf-8");

			writeLog(carNo + " -- " + StringEscapeUtils.unescapeHtml(content), 0);

			if (content != null && !content.equals("")) {
				list = parseViolat(StringEscapeUtils.unescapeHtml(content), (short) 1, carId);
			}
			return list;
		} catch (Exception e) {
			writeLog("城管违章查询" + e.getMessage(), 1);
			return null;
		}
	}

	/**
	 * 格式化成违章信息列表
	 * 
	 * @param content
	 * @param csvBranch
	 * @param carId
	 * @return
	 */
	private List<CsViolat> parseViolat(String content, Short csvBranch, Long carId) {
		// TODO:写日志
		List<CsViolat> list = new ArrayList<CsViolat>();
		switch (csvBranch) {
			case 0:
				// 交警违法
				list = pasePoliceXml(content, csvBranch, carId);
				break;

			case 1:
				// 城管违法
				list = paseChasedXml(content, csvBranch, carId);
				break;
		}
		return list;
	}

	/**
	 * 交警违法xml处理 (状态：clbj，0或者1，表示未交款未处理，未交款已处理)
	 * 
	 * @param content
	 * @param csvBranch
	 * @param carId
	 * @return
	 */
	private List<CsViolat> pasePoliceXml(String content, Short csvBranch, Long carId) {

		List<CsViolat> list = new ArrayList<CsViolat>();
		try {
			Xml requestXml = new Xml();

			requestXml.Parse(content);
			Item requestRoot = requestXml.getRoot();

			Item requestBody = requestRoot.getChild("body");
			// 有违章记录
			if (requestBody != null) {
				List<Item> itemList = requestBody.getItemList();
				for (Item item : itemList) {
					// 只记录非现场违法 dsr
					// if (item.getValue("dsr") == null ||
					// $.empty(item.getValue("dsr"))) {
					CsViolat csViolat = new CsViolat();
					// 设置
					// 违章车辆，处理部门，添加人员，添加时间，违章时间，违章说明，违章扣分，违章罚款，处理方式，未缴款未处理
					csViolat.setCsvCar(carId);
					csViolat.setCsvBranch(csvBranch);
					csViolat.setCsvAdder(0l);
					csViolat.setCsv22(0);
					csViolat.setCsvAddTime(new Date());
					csViolat.setCsvHappenTime($.date(item.getValue("wfsj"), "yyyy-MM-dd HH:mm:ss"));
					csViolat.setCsvDecipt(item.getValue("wfdz") + " - " + item.getValue("wfxwmc"));
					csViolat.setCsvScore(Integer.parseInt(item.getValue("wfjfs")));
					csViolat.setCsvMoney(Double.valueOf(item.getValue("fkje")));
					csViolat.setCsvPrice(0d);
					csViolat.setCsvType((short) 0);
					// edit at 2015-03-30 14:25 by xiaogang
					csViolat.setCsvStatus(Short.parseShort(item.getValue("clbj")));

					list.add(csViolat);
					// } else {
					// 现场执法，不记录
					// writeLog("现场执法，不记录" + item.getValue("wfdz") + " - " +
					// item.getValue("wfxwmc"), 1);
					// }
				}
			}
		} catch (Exception e) {
			writeLog("交警违法xml处理" + e.getMessage(), 1);
		}
		return list;
	}

	/**
	 * 城管违法xml处理 (状态：待处罚，待执行)
	 * 
	 * @param content
	 * @param csvBranch
	 * @param carId
	 * @return
	 */
	private List<CsViolat> paseChasedXml(String content, Short csvBranch, Long carId) {

		List<CsViolat> list = new ArrayList<CsViolat>();
		try {
			SOAPMessage msg = formatSoapString(content);
			SOAPBody body = msg.getSOAPBody();
			// 获得 doQueryCarparkingResponse 节点
			SOAPElement doQueryCarparkingResponse = (SOAPElement) ((SOAPElement) body.getChildElements().next()).getChildElements().next();

			if (doQueryCarparkingResponse != null) {

				Iterator<SOAPElement> iterator = doQueryCarparkingResponse.getChildElements();
				while (iterator.hasNext()) {
					SOAPElement element = (SOAPElement) iterator.next();
					if (element.getNodeName().equals("disobeyStops")) {
						Iterator<Name> iteratorName = element.getAllAttributes();

						CsViolat csViolat = new CsViolat();
						// 设置
						// 违章车辆，处理部门，添加人员，添加时间，违章时间，违章说明，违章扣分，违章罚款，处理方式，未缴款未处理
						csViolat.setCsvCar(carId);
						csViolat.setCsvBranch(csvBranch);
						csViolat.setCsvAdder(0l);
						csViolat.setCsv22(0);
						csViolat.setCsvAddTime(new Date());
						csViolat.setCsvHappenTime(new DateUtil().StringtoDate(element.getAttribute("dsDate"), "yyyy-MM-dd'T'HH:mm:ss"));
						csViolat.setCsvDecipt(element.getAttribute("currentSite") + " - " + element.getAttribute("stopSite"));
						csViolat.setCsvScore(0);
						csViolat.setCsvMoney(150.0);
						csViolat.setCsvPrice(0d);
						csViolat.setCsvType((short) 0);
						csViolat.setCsvStatus((short) 0);
						// edit at 2015-03-30 14:25 by xiaogang
						if (element.getAttribute("status").equals("待执行")) {
							csViolat.setCsvStatus((short) 1);
						}

						list.add(csViolat);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			writeLog("城管违法xml处理" + e.getMessage(), 1);
		}
		return list;
	}

	/**
	 * 根据违章时间和违章车辆，查询对应的定单信息
	 * 
	 * @param time
	 * @param car
	 * @return
	 */
	private CsOrder getOrder(Date time, Long car) {
		if (time == null || car == null)
			return null;
		CsOrder csOrder = csOrderService.getCsOrder($.add("csoCar", car).add("csoStartTimeEnd", time).add("csoFinishTimeStart", time).add("definex", CsOrder.C.csoStatus + " in (0,1,4)"));// csoStatus
		return csOrder;
	}

	/**
	 * 保存违章信息
	 * 
	 * @param violatList
	 */
	private void saveViolat(List<CsViolat> violatList, Short csvBranch) {
		try {
			if (violatList == null) {
				return;
			}
			if (violatList.size() > 0) {
				for (CsViolat csViolat : violatList) {
					// 系统没有录入
					CsViolat csViolatExist = csViolatService.getCsViolat($.add(CsViolat.F.csvCar, csViolat.getCsvCar()).add(CsViolat.F.csvHappenTime, csViolat.getCsvHappenTime()));
					if (csViolatExist == null) {

						// 违章时间在车辆入库之前的，不需要入库
						CsCar car = csCarService.getCsCarById(csViolat.getCsvCar());
						
						if (car != null && car.getCscAddTime().before(csViolat.getCsvHappenTime())) {

							CsOrder csOrder = getOrder(csViolat.getCsvHappenTime(), csViolat.getCsvCar());
							if (csOrder != null) {
								// 设置违章定单违章定单号，违章人员
								csViolat.setCsvOrder(csOrder.getCsoId());
								csViolat.setCsvMember(csOrder.getCsoUseMember());
								csViolat.setCsvHost(csOrder.getCsoHost());

								// 保存违章信息
								csViolat = csViolatService.saveCsViolat(csViolat);
								LoggerHelper.writeLog(CsViolat.class, "添加", "[添加了违章]", 0l, csViolat, csViolat.getCsvId());
							} else {
								// 设置违章定单违章定单号，违章人员
								csViolat.setCsvOrder(0l);
								csViolat.setCsvMember(0l);
								csViolat.setCsvHost(csViolat.get$csvCar().getCscHost());

								// 保存违章信息
								csViolat = csViolatService.saveCsViolat(csViolat);
								LoggerHelper.writeLog(CsViolat.class, "添加", "[添加了违章]", 0l, csViolat, csViolat.getCsvId());

								writeLog(" 违章信息定单不存在：" + csViolat.getCsvDecipt(), 1);
							}
						}else {
							writeLog(" 违章信息在车辆入库前：" + csViolat.getCsvDecipt(), 1);
						}
					} else {
						// 如果已存在，并且状态不等于0或者不等于2
						if (csViolatExist.getCsvStatus() != (short) 0 || csViolatExist.getCsvStatus() != (short) 2) {
							csViolatExist.setCsvStatus(csViolat.getCsvStatus());
							csViolatExist.update();
							// 记录操作日志
							LoggerHelper.writeLog(CsViolat.class, "edit", "更新了违章状态[" + csViolatExist.getCsvStatus() + "]", 0l, csViolatExist, csViolatExist.getCsvId());
							writeLog(" 更新违章信息：" + csViolat.get$csvCar().getCscCarNo() + " - " + csViolat.getCsvDecipt(), 1);
						}
					}
				}
			}
		} catch (Exception e) {
			writeLog("保存违章信息" + e.getMessage(), 1);
		}
	}

	/**
	 * 把soap字符串格式化为SOAPMessage
	 * 
	 * @param soapString
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private SOAPMessage formatSoapString(String soapString) {
		MessageFactory msgFactory;
		try {
			msgFactory = MessageFactory.newInstance();
			SOAPMessage reqMsg = msgFactory.createMessage(new MimeHeaders(), new ByteArrayInputStream(soapString.getBytes("UTF-8")));
			reqMsg.saveChanges();
			return reqMsg;
		} catch (Exception e) {
			writeLog("formatSoapString" + e.getMessage(), 1);
			return null;
		}
	}

	/**
	 * 拼接参数，key=value&的方式
	 * 
	 * @param params
	 * @return
	 */
	private String getParams(Map<String, String> params) {
		String result = "";
		for (String key : params.keySet()) {
			String value = (String) params.get(key);
			result = result + key + "=" + value + "&";
		}
		if (result.length() > 0) {
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}

	public String http(String sUrl, String encode, int timeout) throws Exception {
		String strContent = "";
		HttpURLConnection urlConnection = null;
		URL url = new URL(sUrl);
		urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setRequestMethod("GET");
		urlConnection.setConnectTimeout(timeout);
		urlConnection.setReadTimeout(timeout);
		// urlConnection.setDoOutput(true);
		if (urlConnection.getResponseCode() >= 200 && urlConnection.getResponseCode() < 300) {
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), encode));
			String strLine = in.readLine();
			// 得到的结论是文件如果是带bom的utf-8格式的，即使用UTF-8的流读入，也得自己去掉bom才可以
			if (strLine != null && !strLine.equals("")) {
				if (strLine.charAt(0) == (char) 65279)// BOM
					strLine = strLine.substring(1);
			}
			while (strLine != null) {
				strContent += strLine + "\r\n";
				strLine = in.readLine();
			}
			in.close();
		}
		urlConnection.disconnect();
		return strContent;
	}

	/**
	 * 写日志
	 * 
	 * @param content
	 */
	public void writeLog(String content, int type) {
		// if (logPath == null) {
		getLogPath();
		if (logPath != null && errorLogPath != null) {
			String timeString = DateHelper.formatDate(new Date(), "HH:mm:ss");
			// $.trace(timeString + " - " + content);
			switch (type) {
				case 0:
					writeAppendToFile(logPath, timeString + "   -   " + content, "UTF-8");
					break;
				case 1:
					writeAppendToFile(errorLogPath, timeString + "   -   " + content, "UTF-8");
					break;
			}
		}
	}

	/**
	 * 获取日志路径
	 */
	public void getLogPath() {
		try {
			logPath = ViolatThread.class.getResource("/").toURI().getPath() + "ViolatInfoReceiverTemp/" + DateHelper.formatDate(new Date(), "yyyy-MM-dd") + ".txt";
			errorLogPath = ViolatThread.class.getResource("/").toURI().getPath() + "ErrorLogTemp/" + DateHelper.formatDate(new Date(), "yyyy-MM-dd") + ".txt";
		} catch (Exception ex) {
			System.out.println("获取文件路径时出错:" + ex.getMessage());
		}
	}

	/**
	 * 追加到文件末尾，并且换行
	 * 
	 * @param fileName
	 * @param content
	 * @param encode
	 */
	public static void writeAppendToFile(String fileName, String content, String encode) {
		if (encode == null)
			encode = "UTF-8";
		FileWriter writer = null;
		try {
			fileName = fileName.replaceAll("\\\\", "/");
			fileName = fileName.replaceAll("//", "/");

			String filePath = fileName.substring(0, fileName.lastIndexOf("/"));

			if (!new File(filePath).exists())
				new File(filePath).mkdirs();

			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			writer = new FileWriter(fileName, true);
			writer.write(content + "\r\n");
		} catch (IOException ex) {
			System.out.println("写文件[" + fileName + "]到[" + fileName + "]时出错:" + ex.getMessage());
			ex.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public ICsViolatService getCsViolatService() {
		return csViolatService;
	}

	public void setCsViolatService(ICsViolatService csViolatService) {
		this.csViolatService = csViolatService;
	}

	public ICsOrderService getCsOrderService() {
		return csOrderService;
	}

	public void setCsOrderService(ICsOrderService csOrderService) {
		this.csOrderService = csOrderService;
	}

	public ICsCarService getCsCarService() {
		return csCarService;
	}

	public void setCsCarService(ICsCarService csCarService) {
		this.csCarService = csCarService;
	}

}
