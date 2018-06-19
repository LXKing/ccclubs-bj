package com.ccclubs.util;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.ccclubs.config.SYSTEM.RecordType;
import com.ccclubs.model.CsOrder;
import com.ccclubs.service.common.ICommonMoneyService.MoneyType;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class CarInfoImportor {
	
	
	public static void main(String[] args) {
		
		
		Lazy3qDaoSupport dao = $.getDao("ccclubs_system");
		
		List<Map> list = dao.executeQuery("select * from cs_repair");
		
		for(Map map:list)
			System.out.println("update cs_repair set csr_money="+map.get("csr_money")+" where csr_id="+map.get("csr_id")+";");
		
	}
	

	/**
	 * @param args
	 */
	public static void main1(String[] args) {
		
		Workbook book=null;
		try {
			File batch=new File("D:\\carinfo.xls");
			book = Workbook.getWorkbook(batch);
		} catch (Exception e) {						
			e.printStackTrace();
			return;
		}
		
		
		String[][] infoes={
				{"csc_mobile","手机号码","b"},
				{"csc_oil_card","加油卡号","a"},
				{"csc_vin","车架号","I"},
				{"csc_buy_date","购车日日期","D"},
				{"csc_bargain_no","合同号","E"},
				{"csc_tax_price","购置税","F"},
				{"csc_buy_price","车价","G"},
				{"csc_certific","合格证号","J"},
				{"csc_factory_no","厂牌型号","L"},
				{"csc_color","颜色","M"},
				{"csc_maint_km","保养公里","N"},
				{"csc_check_in","初次登记日期","P"},
				{"csc_annual","年检日期","Q"},
				{"csc_ti_date","交强险投保日期","R"},
				{"csc_ti_unit","交强险到期日期","S"},
				{"csc_ti_no","交强险保单","T"},
				{"csc_bi_date","商业险投保日期","U"},
				{"csc_bi_unit","商业险到期日期","V"},
				{"csc_bi_no","商业险保单","W"},
				{"csc_bi_type","商业险类型","X"},
				{"csc_bi_limit","商业险金额","Y"},
				{"csc_bi_company","保险公司","Z"}
		};
		
		String strColor = "黑色白色红色绿色黄色橙色银色蓝色深蓝深灰深红";
		
		String indexes="ABCDEFGHIJKLMNOPQRSTUVWXYZab";
		Sheet sheet = book.getSheet(0);
        for(int i=3;i<sheet.getRows();i++){
        	String strSql = "update cs_car set ";
        	
        	for(int j=0;j<infoes.length;j++){
        		String[] infos=infoes[j];         		
        		String content = sheet.getCell(indexes.indexOf(infos[2]),i).getContents();
        		if(!$.empty(content)){
        			if(infos[0].equals("csc_color")){
        				strSql+=infos[0]+"="+strColor.indexOf($.trim(content))/2+",";
        			}else if(infos[1].indexOf("日期")>-1){
        				strSql+=infos[0]+"='"+date($.trim(content))+"',";
        			}else 
        				strSql+=infos[0]+"='"+$.trim(content)+"',";
        		}
        	} 
        	
        	String number=sheet.getCell(1,i).getContents();
        	if($.empty(number))continue;
        	
        	strSql+="csc_number=csc_number where csc_number='"+number.replace("浙", "ZJ")+"';";
        	System.out.println(strSql);
        }
        
        book.close();
	}
	
	public static String date(String strDate){		
		String[] array = strDate.split("\\.");
		String strRet=array[0];
		strRet+="-";
		if(array[1].length()==1)
			strRet+="0";
		strRet+=array[1];
		strRet+="-";
		if(array.length==2)
			strRet+="01";
		else{
			if(array[2].length()==1)
				strRet+="0";
			strRet+=array[2];
		}
				
		return strRet;
	}

}
