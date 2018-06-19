package com.ccclubs.action.app.official;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ccclubs.action.app.official.model.JsonFormat;
import com.lazy3q.web.helper.$;

public class AddressList {
	
	public static List<Map<String, Object>> addressList = new ArrayList<Map<String,Object>>();
	
	public static String json = null;
	
	static{
		//
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		list.add($.Map("id", "1").add("name", "安定门"));
		list.add($.Map("id", "2").add("name", "东四"));
		list.add($.Map("id", "3").add("name", "东直门"));
		list.add($.Map("id", "4").add("name", "前门"));
		list.add($.Map("id", "5").add("name", "建国门"));
		list.add($.Map("id", "6").add("name", "崇文门"));
		list.add($.Map("id", "7").add("name", "天坛"));
		list.add($.Map("id", "8").add("name", "龙潭湖"));
		list.add($.Map("id", "9").add("name", "永定门"));
		addressList.add($.Map("id", "1").add("name", "东城区").add("address", list));
		
		list = new ArrayList<Map<String,String>>();
		list.add($.Map("id", "1").add("name", "五里坨"));
		list.add($.Map("id", "2").add("name", "广宁"));
		list.add($.Map("id", "3").add("name", "金顶"));
		list.add($.Map("id", "4").add("name", "苹果园"));
		list.add($.Map("id", "5").add("name", "古城"));
		list.add($.Map("id", "6").add("name", "八角"));
		list.add($.Map("id", "7").add("name", "鲁谷"));
		list.add($.Map("id", "8").add("name", "老山"));
		list.add($.Map("id", "9").add("name", "八宝山"));
		addressList.add($.Map("id", "2").add("name", "石景山区").add("address", list));
		
		list = new ArrayList<Map<String,String>>();
		list.add($.Map("id", "1").add("name", "展览路"));
		list.add($.Map("id", "2").add("name", "新街口"));
		list.add($.Map("id", "3").add("name", "德胜门"));
		list.add($.Map("id", "4").add("name", "月坛"));
		list.add($.Map("id", "5").add("name", "金融街"));
		list.add($.Map("id", "6").add("name", "西长安街"));
		list.add($.Map("id", "7").add("name", "广安门"));
		list.add($.Map("id", "8").add("name", "陶然亭"));
		list.add($.Map("id", "9").add("name", "天桥"));
		addressList.add($.Map("id", "3").add("name", "西城区").add("address", list));
		
		list = new ArrayList<Map<String,String>>();
		list.add($.Map("id", "1").add("name", "云岗"));
		list.add($.Map("id", "2").add("name", "长辛店"));
		list.add($.Map("id", "3").add("name", "卢沟桥"));
		list.add($.Map("id", "4").add("name", "六里桥"));
		list.add($.Map("id", "5").add("name", "新村"));
		list.add($.Map("id", "6").add("name", "太平桥"));
		list.add($.Map("id", "7").add("name", "马家堡"));
		list.add($.Map("id", "8").add("name", "东铁匠营"));
		list.add($.Map("id", "9").add("name", "大红门"));
		addressList.add($.Map("id", "4").add("name", "丰台区").add("address", list));
		
		list = new ArrayList<Map<String,String>>();
		list.add($.Map("id", "1").add("name", "苏家坨"));
		list.add($.Map("id", "2").add("name", "上庄"));
		list.add($.Map("id", "3").add("name", "颐和园"));
		list.add($.Map("id", "4").add("name", "中关村"));
		list.add($.Map("id", "5").add("name", "四季青"));
		list.add($.Map("id", "6").add("name", "紫竹院"));
		list.add($.Map("id", "7").add("name", "北太平庄"));
		list.add($.Map("id", "8").add("name", "甘家口"));
		list.add($.Map("id", "9").add("name", "万寿路"));
		addressList.add($.Map("id", "5").add("name", "海淀区").add("address", list));
		
		list = new ArrayList<Map<String,String>>();
		list.add($.Map("id", "1").add("name", "崔各庄"));
		list.add($.Map("id", "2").add("name", "奥运村"));
		list.add($.Map("id", "3").add("name", "东坝"));
		list.add($.Map("id", "4").add("name", "国贸"));
		list.add($.Map("id", "5").add("name", "朝阳公园"));
		list.add($.Map("id", "6").add("name", "管庄"));
		list.add($.Map("id", "7").add("name", "小红门"));
		list.add($.Map("id", "8").add("name", "十八里店"));
		list.add($.Map("id", "9").add("name", "黑庄户"));
		addressList.add($.Map("id", "6").add("name", "朝阳区").add("address", list));
		
		list = new ArrayList<Map<String,String>>();
		list.add($.Map("id", "1").add("name", "延庆镇"));
		list.add($.Map("id", "2").add("name", "张山营镇"));
		list.add($.Map("id", "3").add("name", "八达岭镇"));
		list.add($.Map("id", "4").add("name", "刘斌堡乡"));
		list.add($.Map("id", "5").add("name", "千家店镇"));
		list.add($.Map("id", "6").add("name", "四海镇"));
		addressList.add($.Map("id", "7").add("name", "延庆").add("address", list));
		
		list = new ArrayList<Map<String,String>>();
		list.add($.Map("id", "1").add("name", "怀柔城区"));
		list.add($.Map("id", "2").add("name", "庙城镇"));
		list.add($.Map("id", "3").add("name", "雁西镇"));
		list.add($.Map("id", "4").add("name", "长哨营满族乡"));
		list.add($.Map("id", "5").add("name", "宝山镇"));
		list.add($.Map("id", "6").add("name", "九渡河镇"));
		addressList.add($.Map("id", "8").add("name", "怀柔").add("address", list));
		
		list = new ArrayList<Map<String,String>>();
		list.add($.Map("id", "1").add("name", "城北"));
		list.add($.Map("id", "2").add("name", "流村镇"));
		list.add($.Map("id", "3").add("name", "北七家镇"));
		list.add($.Map("id", "4").add("name", "马池口镇"));
		list.add($.Map("id", "5").add("name", "长陵镇"));
		list.add($.Map("id", "6").add("name", "小汤山镇"));
		addressList.add($.Map("id", "9").add("name", "昌平").add("address", list));
		
		list = new ArrayList<Map<String,String>>();
		list.add($.Map("id", "1").add("name", "密云镇"));
		list.add($.Map("id", "2").add("name", "东邵渠镇"));
		list.add($.Map("id", "3").add("name", "石城镇"));
		list.add($.Map("id", "4").add("name", "北庄镇"));
		list.add($.Map("id", "5").add("name", "不老屯镇"));
		list.add($.Map("id", "6").add("name", "新城子镇"));
		addressList.add($.Map("id", "10").add("name", "密云").add("address", list));
		
		list = new ArrayList<Map<String,String>>();
		list.add($.Map("id", "1").add("name", "滨河街道"));
		list.add($.Map("id", "2").add("name", "镇罗营镇"));
		list.add($.Map("id", "3").add("name", "王辛庄镇"));
		list.add($.Map("id", "4").add("name", "海湖地区办事处"));
		list.add($.Map("id", "5").add("name", "夏各庄镇"));
		list.add($.Map("id", "6").add("name", "马坊地区办事处"));
		addressList.add($.Map("id", "11").add("name", "平谷").add("address", list));
		
		list = new ArrayList<Map<String,String>>();
		list.add($.Map("id", "1").add("name", "胜利街道"));
		list.add($.Map("id", "2").add("name", "赵全营镇"));
		list.add($.Map("id", "3").add("name", "天竺地区办事处"));
		list.add($.Map("id", "4").add("name", "李遂镇"));
		list.add($.Map("id", "5").add("name", "张镇"));
		list.add($.Map("id", "6").add("name", "木林镇"));
		addressList.add($.Map("id", "12").add("name", "顺义").add("address", list));
		
		list = new ArrayList<Map<String,String>>();
		list.add($.Map("id", "1").add("name", "龙泉镇"));
		list.add($.Map("id", "2").add("name", "潭柘寺镇"));
		list.add($.Map("id", "3").add("name", "军庄镇"));
		list.add($.Map("id", "4").add("name", "雁翅镇"));
		list.add($.Map("id", "5").add("name", "斋堂镇"));
		list.add($.Map("id", "6").add("name", "清水镇"));
		addressList.add($.Map("id", "13").add("name", "门头沟").add("address", list));
		
		list = new ArrayList<Map<String,String>>();
		list.add($.Map("id", "1").add("name", "良乡镇"));
		list.add($.Map("id", "2").add("name", "十渡镇"));
		list.add($.Map("id", "3").add("name", "琉璃河镇"));
		list.add($.Map("id", "4").add("name", "史家营镇"));
		list.add($.Map("id", "5").add("name", "青龙湖镇"));
		list.add($.Map("id", "6").add("name", "周口店镇"));
		addressList.add($.Map("id", "14").add("name", "房山").add("address", list));
		
		list = new ArrayList<Map<String,String>>();
		list.add($.Map("id", "1").add("name", "黄村镇"));
		list.add($.Map("id", "2").add("name", "亦庄镇"));
		list.add($.Map("id", "3").add("name", "北臧村镇"));
		list.add($.Map("id", "4").add("name", "榆垡镇"));
		list.add($.Map("id", "5").add("name", "采育镇"));
		list.add($.Map("id", "6").add("name", "青云店镇"));
		addressList.add($.Map("id", "15").add("name", "大兴").add("address", list));
		
		list = new ArrayList<Map<String,String>>();
		list.add($.Map("id", "1").add("name", "北苑街道"));
		list.add($.Map("id", "2").add("name", "宋庄镇"));
		list.add($.Map("id", "3").add("name", "永乐店镇"));
		list.add($.Map("id", "4").add("name", "马驹桥镇"));
		list.add($.Map("id", "5").add("name", "西集镇"));
		list.add($.Map("id", "6").add("name", "张家湾镇"));
		addressList.add($.Map("id", "16").add("name", "通州").add("address", list));
		
		json = $.json(JsonFormat.success().setData($.$("list", addressList)));
	}
}
