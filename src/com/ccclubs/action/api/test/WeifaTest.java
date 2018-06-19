package com.ccclubs.action.api.test;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.ccclubs.model.CsViolat;
import com.lazy3q.web.helper.$;
import com.lazy3q.xml.Item;
import com.lazy3q.xml.Xml;

/**
 * 杭州交警违法查询demo
 * 
 * @author qsxiaogang
 * 
 * <pre>
 * x-www-form-urlencoded
 * 
 * URL:http://service.zscg.hzcdt.com/api/httpapi
 * 
 * parmars:
 * xlh			0C2B3243AFCB169B0E0C07533816A4D3
 * jkid			A001674
 * xmlDoc		&lt;?xml version='1.0' encoding='gb2312' ?&gt;&lt;root&gt;&lt;QueryCondition&gt;&lt;clsbdh&gt;082685&lt;/clsbdh&gt;&lt;hpzl&gt;02&lt;/hpzl&gt;&lt;hphm&gt;%E6%B5%99AF286X&lt;/hphm&gt;&lt;/				QueryCondition&gt;&lt;/root&gt;
 * 
 * - 违章时间
 * wfsj 2014-09-16 13:47:39.0 
 * 
 * - 违章地点
 * wfdz 兴业路与三棵树路交叉口 
 * 
 * - 违章说明
 * wfxwmc 不按所需行进方向驶入导向车道的 
 * 
 * - 车牌号
 * hphm 浙AF286X
 * 
 * - 违章扣分
 * wfjfs 2 
 * 
 * - 违章罚款
 * fkje 100 
 * 
 * - 采集部门
 * cjjgmc 宣城市公安局交警支队二大队采处中心 
 * 
 * - 车架号
 * clsbdh LSGJA52H4DS082685 
 * 
 * - 车辆类型，（小型车辆）
 * hpzl 02 
 * 
 * 
 * - 违章去重，依据：车牌号，违章时间
 * - 多线程处理
 * - 防止频繁访问策略，a、在特定时间可自动后台执行，b、通过网络代理，c、执行频率限制
 * </pre>
 */
public class WeifaTest {
	// 目标URL地址
	private static String	URL		= "http://service.zscg.hzcdt.com/api/httpapi";
	// 车架号后6位
	private static String	CLSBDH	= "LSJZ14C30DS111111";
	// 车牌号，最好转化成大写
	private static String	CAR_NUM	= "浙A1NN15";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("xlh", "0C2B3243AFCB169B0E0C07533816A4D3");
			params.put("jkid", "A001674");
			params.put("xmlDoc", URLEncoder.encode("<?xml version='1.0' encoding='gb2312' ?><root><QueryCondition><clsbdh>" + CLSBDH.substring(CLSBDH.length() - 6, CLSBDH.length()) + "</clsbdh><hpzl>02</hpzl><hphm>" + URLEncoder.encode(CAR_NUM.toUpperCase(), "utf-8")
					+ "</hphm></QueryCondition></root>", "utf-8"));
			// String result = getParams(params);

			// String result = $.http(URL + "?" + getParams(params));

			String result = "<?xml version=\"1.0\" encoding=\"GBK\"?><root><head><code>1</code><message>查询成功</message><rownum>4</rownum></head><body><item id=\"0\"><cyry></cyry><jyhgbzbh>153301125740</jyhgbzbh><xh>3410021000137551</xh><yzbm2></yzbm2><yzbm1>310000</yzbm1><xgzl>01,02,03,04,05,06,10</xgzl><zzz></zzz><rlzl>A</rlzl><clly>1</clly><cllx>K33</cllx><hxnbcd></hxnbcd><zzl>1500</zzl><bdjcs>0</bdjcs><llpz2></llpz2><qzbfqz>2099-12-31 00:00:00.0</qzbfqz><glbm>330100000400</glbm><zzg>156</zzg><zdjzshs></zdjzshs><fzjg>浙A</fzjg><llpz1></llpz1><zdyzt></zdyzt><fkje>150</fkje><jszh></jszh><bxzzrq>2014-03-11 00:00:00.0</bxzzrq><hxnbkd></hxnbkd><sjhm>13357153855</sjhm><hmbh></hmbh><fzrq>2013-03-12 15:01:11.0</fzrq><hdzzl></hdzzl><wfxwmc>驾驶机动车违反道路交通信号灯通行的</wfxwmc><jkbj>0</jkbj><nszm></nszm><qmbh></qmbh><wfsj>2014-10-06 14:39:54.0</wfsj><gl>68</gl><syxz>A</syxz><pl>1343</pl><dphgzbh></dphgzbh><syq>1</syq><sfzmhm>58028352-8</sfzmhm><cwkg>1517</cwkg><hbdbqk>GB18352.3-2005国Ⅳ</hbdbqk><clsj></clsj><xszbh>3360009792853</xszbh><zzxxdz>浙江省杭州市西湖区文一路123号银马公寓1-1-301</zzxxdz><dwbh></dwbh><ltgg>185/70 R14 88H</ltgg><zbzl>1125</zbzl><cwkk>1728</cwkk><syr>杭州车厘子智能科技有限公司</syr><hphm>浙A1NN15</hphm><dabh>040035E50242</dabh><sqdm></sqdm><fdjh>B2GD1150515</fdjh><djzsbh>330013900296</djzsbh><pzbh2></pzbh2><jkpz></jkpz><pzbh1></pzbh1><hpzk></hpzk><hpzl>02</hpzl><cwkc>3999</cwkc><hdzk>5</hdzk><bpcs>0</bpcs><zsxxdz>浙江省杭州市西湖区西溪路525号C楼225室</zsxxdz><hgzbh>YF1442000380948</hgzbh><wfxw>1625</wfxw><xsdw></xsdw><zzcmc>上海汽车集团股份有限公司</zzcmc><ccrq>2013-01-21 00:00:00.0</ccrq><fdjrq>2013-03-12 15:01:25.0</fdjrq><yxqz>2015-03-31 00:00:00.0</yxqz><hxnbgd></hxnbgd><qlj>1505</qlj><bz></bz><nszmbh></nszmbh><lts>4</lts><jdsbh></jdsbh><jkpzhm></jkpzhm><dsr></dsr><wfdz>轩辕大道与新三路交叉路口</wfdz><fprq>2013-03-12 14:54:05.0</fprq><dzyx></dzyx><clyt>P1</clyt><fdjxh>13S4F</fdjxh><lsh>1130312008658</lsh><xzqh>330106</xzqh><gxrq>2014-10-07 11:09:53.0</gxrq><djrq>2013-03-12 00:00:00.0</djrq><ccdjrq>2013-03-12 14:54:05.0</ccdjrq><cjjg>341002000000</cjjg><jbr>汤水明</jbr><lxdh>88190338</lxdh><gbthps></gbthps><dybj>0</dybj><gcjk>A</gcjk><clxh>CSA7130ACS</clxh><xsjg></xsjg><hdfs>A</hdfs><qpzk></qpzk><yxh></yxh><fhgzrq>2013-03-12 15:01:28.0</fhgzrq><csys>A</csys><clbj>0</clbj><kcl>1</kcl><wfjfs>6</wfjfs><zs>2</zs><zt>G</zt><ytsx>9</ytsx><wfgd>《中华人民共和国道路交通安全法》第三十八条、《中华人民共和国道路交通安全法实施条例》第三十八条、四十条、四十一、四十二、四十三条</wfgd><zj>2520</zj><sfzmmc>B</sfzmmc><fltw>《中华人民共和国道路交通安全法》第九十条</fltw><hlj>1493</hlj><clpp1>名爵牌</clpp1><clpp2></clpp2><zqyzl></zqyzl><bzcs>0</bzcs><zzxzqh>330106</zzxzqh><xsrq></xsrq><cjjgmc>黄山公安分局交通警察大队 </cjjgmc><zxxs>1</zxxs><zsxzqh>330106</zsxzqh><jyw>321F7E7B0601F3CA8CC0F1FE7066770F010206797A6026210A2E5C462A1D532A201E010002030573007370090200060D01047D0B0A722D4070455E425A1F515C303135303333314135</jyw><clsbdh>LSJZ14C30DS015575</clsbdh></item><item id=\"1\"><cyry></cyry><jyhgbzbh>153301125740</jyhgbzbh><xh>3499391000186472</xh><yzbm2></yzbm2><yzbm1>310000</yzbm1><xgzl>01,02,03,04,05,06,10</xgzl><zzz></zzz><rlzl>A</rlzl><clly>1</clly><cllx>K33</cllx><hxnbcd></hxnbcd><zzl>1500</zzl><bdjcs>0</bdjcs><llpz2></llpz2><qzbfqz>2099-12-31 00:00:00.0</qzbfqz><glbm>330100000400</glbm><zzg>156</zzg><zdjzshs></zdjzshs><fzjg>浙A</fzjg><llpz1></llpz1><zdyzt></zdyzt><fkje>50</fkje><jszh></jszh><bxzzrq>2014-03-11 00:00:00.0</bxzzrq><hxnbkd></hxnbkd><sjhm>13357153855</sjhm><hmbh></hmbh><fzrq>2013-03-12 15:01:11.0</fzrq><hdzzl></hdzzl><wfxwmc>在高速公路上行驶时,驾驶人未按规定使用安全带的</wfxwmc><jkbj>0</jkbj><nszm></nszm><qmbh></qmbh><wfsj>2014-10-06 08:23:31.0</wfsj><gl>68</gl><syxz>A</syxz><pl>1343</pl><dphgzbh></dphgzbh><syq>1</syq><sfzmhm>58028352-8</sfzmhm><cwkg>1517</cwkg><hbdbqk>GB18352.3-2005国Ⅳ</hbdbqk><clsj></clsj><xszbh>3360009792853</xszbh><zzxxdz>浙江省杭州市西湖区文一路123号银马公寓1-1-301</zzxxdz><dwbh></dwbh><ltgg>185/70 R14 88H</ltgg><zbzl>1125</zbzl><cwkk>1728</cwkk><syr>杭州车厘子智能科技有限公司</syr><hphm>浙A1NN15</hphm><dabh>040035E50242</dabh><sqdm></sqdm><fdjh>B2GD1150515</fdjh><djzsbh>330013900296</djzsbh><pzbh2></pzbh2><jkpz></jkpz><pzbh1></pzbh1><hpzk></hpzk><hpzl>02</hpzl><cwkc>3999</cwkc><hdzk>5</hdzk><bpcs>0</bpcs><zsxxdz>浙江省杭州市西湖区西溪路525号C楼225室</zsxxdz><hgzbh>YF1442000380948</hgzbh><wfxw>4204</wfxw><xsdw></xsdw><zzcmc>上海汽车集团股份有限公司</zzcmc><ccrq>2013-01-21 00:00:00.0</ccrq><fdjrq>2013-03-12 15:01:25.0</fdjrq><yxqz>2015-03-31 00:00:00.0</yxqz><hxnbgd></hxnbgd><qlj>1505</qlj><bz></bz><nszmbh></nszmbh><lts>4</lts><jdsbh></jdsbh><jkpzhm></jkpzhm><dsr>季鹤峰</dsr><wfdz>京台高速1267公里</wfdz><fprq>2013-03-12 14:54:05.0</fprq><dzyx></dzyx><clyt>P1</clyt><fdjxh>13S4F</fdjxh><lsh>1130312008658</lsh><xzqh>330106</xzqh><gxrq>2014-10-07 11:09:53.0</gxrq><djrq>2013-03-12 00:00:00.0</djrq><ccdjrq>2013-03-12 14:54:05.0</ccdjrq><cjjg>349939000010</cjjg><jbr>汤水明</jbr><lxdh>88190338</lxdh><gbthps></gbthps><dybj>0</dybj><gcjk>A</gcjk><clxh>CSA7130ACS</clxh><xsjg></xsjg><hdfs>A</hdfs><qpzk></qpzk><yxh></yxh><fhgzrq>2013-03-12 15:01:28.0</fhgzrq><csys>A</csys><clbj>0</clbj><kcl>1</kcl><wfjfs>2</wfjfs><zs>2</zs><zt>G</zt><ytsx>9</ytsx><wfgd>《中华人民共和国道路交通安全法》第五十一条</wfgd><zj>2520</zj><sfzmmc>B</sfzmmc><fltw>《浙江省实施〈中华人民共和国道路交通安全法〉办法》第七十四条第一款第二项</fltw><hlj>1493</hlj><clpp1>名爵牌</clpp1><clpp2></clpp2><zqyzl></zqyzl><bzcs>0</bzcs><zzxzqh>330106</zzxzqh><xsrq></xsrq><cjjgmc>黄山市高速二大队</cjjgmc><zxxs>1</zxxs><zsxzqh>330106</zsxzqh><jyw>321F7E7B0601F3CA8CC0F1FE7066770F010206797A6026210A2E5C462A1D532A201E010002030573007370090200060D01047D0B0A722D4070455E425A1F515C303135303333314135</jyw><clsbdh>LSJZ14C30DS015575</clsbdh></item><item id=\"2\"><cyry></cyry><jyhgbzbh>153301125740</jyhgbzbh><xh>3417230000230890</xh><yzbm2></yzbm2><yzbm1>310000</yzbm1><xgzl>01,02,03,04,05,06,10</xgzl><zzz></zzz><rlzl>A</rlzl><clly>1</clly><cllx>K33</cllx><hxnbcd></hxnbcd><zzl>1500</zzl><bdjcs>0</bdjcs><llpz2></llpz2><qzbfqz>2099-12-31 00:00:00.0</qzbfqz><glbm>330100000400</glbm><zzg>156</zzg><zdjzshs></zdjzshs><fzjg>浙A</fzjg><llpz1></llpz1><zdyzt></zdyzt><fkje>200</fkje><jszh></jszh><bxzzrq>2014-03-11 00:00:00.0</bxzzrq><hxnbkd></hxnbkd><sjhm>13357153855</sjhm><hmbh></hmbh><fzrq>2013-03-12 15:01:11.0</fzrq><hdzzl></hdzzl><wfxwmc>驾驶中型以上载客载货汽车、危险物品运输车辆以外的其他机动车行驶超过规定时速10%未达20%的</wfxwmc><jkbj>0</jkbj><nszm></nszm><qmbh></qmbh><wfsj>2014-10-02 14:11:00.0</wfsj><gl>68</gl><syxz>A</syxz><pl>1343</pl><dphgzbh></dphgzbh><syq>1</syq><sfzmhm>58028352-8</sfzmhm><cwkg>1517</cwkg><hbdbqk>GB18352.3-2005国Ⅳ</hbdbqk><clsj></clsj><xszbh>3360009792853</xszbh><zzxxdz>浙江省杭州市西湖区文一路123号银马公寓1-1-301</zzxxdz><dwbh></dwbh><ltgg>185/70 R14 88H</ltgg><zbzl>1125</zbzl><cwkk>1728</cwkk><syr>杭州车厘子智能科技有限公司</syr><hphm>浙A1NN15</hphm><dabh>040035E50242</dabh><sqdm></sqdm><fdjh>B2GD1150515</fdjh><djzsbh>330013900296</djzsbh><pzbh2></pzbh2><jkpz></jkpz><pzbh1></pzbh1><hpzk></hpzk><hpzl>02</hpzl><cwkc>3999</cwkc><hdzk>5</hdzk><bpcs>0</bpcs><zsxxdz>浙江省杭州市西湖区西溪路525号C楼225室</zsxxdz><hgzbh>YF1442000380948</hgzbh><wfxw>13522</wfxw><xsdw></xsdw><zzcmc>上海汽车集团股份有限公司</zzcmc><ccrq>2013-01-21 00:00:00.0</ccrq><fdjrq>2013-03-12 15:01:25.0</fdjrq><yxqz>2015-03-31 00:00:00.0</yxqz><hxnbgd></hxnbgd><qlj>1505</qlj><bz></bz><nszmbh></nszmbh><lts>4</lts><jdsbh></jdsbh><jkpzhm></jkpzhm><dsr>季鹤峰</dsr><wfdz>沪聂线417公里820米</wfdz><fprq>2013-03-12 14:54:05.0</fprq><dzyx></dzyx><clyt>P1</clyt><fdjxh>13S4F</fdjxh><lsh>1130312008658</lsh><xzqh>330106</xzqh><gxrq>2014-10-07 11:09:53.0</gxrq><djrq>2013-03-12 00:00:00.0</djrq><ccdjrq>2013-03-12 14:54:05.0</ccdjrq><cjjg>341723000000</cjjg><jbr>汤水明</jbr><lxdh>88190338</lxdh><gbthps></gbthps><dybj>0</dybj><gcjk>A</gcjk><clxh>CSA7130ACS</clxh><xsjg></xsjg><hdfs>A</hdfs><qpzk></qpzk><yxh></yxh><fhgzrq>2013-03-12 15:01:28.0</fhgzrq><csys>A</csys><clbj>0</clbj><kcl>1</kcl><wfjfs>3</wfjfs><zs>2</zs><zt>G</zt><ytsx>9</ytsx><wfgd>《中华人民共和国道路交通安全法》第四十二条、《中华人民共和国道路交通安全法实施条例》第四十五条、?谒氖?</wfgd><zj>2520</zj><sfzmmc>B</sfzmmc><fltw>《中华人民共和国道路交通安全法》第九十条、《浙江省实施〈中华人民共和国道路交通安全法〉办法》第八十三条第一款第一项</fltw><hlj>1493</hlj><clpp1>名爵牌</clpp1><clpp2></clpp2><zqyzl></zqyzl><bzcs>0</bzcs><zzxzqh>330106</zzxzqh><xsrq></xsrq><cjjgmc>青阳大队</cjjgmc><zxxs>1</zxxs><zsxzqh>330106</zsxzqh><jyw>321F7E7B0601F3CA8CC0F1FE7066770F010206797A6026210A2E5C462A1D532A201E010002030573007370090200060D01047D0B0A722D4070455E425A1F515C303135303333314135</jyw><clsbdh>LSJZ14C30DS015575</clsbdh></item><item id=\"3\"><cyry></cyry><jyhgbzbh>153301125740</jyhgbzbh><xh></xh><yzbm2></yzbm2><yzbm1>310000</yzbm1><xgzl>01,02,03,04,05,06,10</xgzl><zzz></zzz><rlzl>A</rlzl><clly>1</clly><cllx>K33</cllx><hxnbcd></hxnbcd><zzl>1500</zzl><bdjcs>0</bdjcs><llpz2></llpz2><qzbfqz>2099-12-31 00:00:00.0</qzbfqz><glbm>330100000400</glbm><zzg>156</zzg><zdjzshs></zdjzshs><fzjg>浙A</fzjg><llpz1></llpz1><zdyzt></zdyzt><fkje>100</fkje><jszh>33010619890205331X</jszh><bxzzrq>2014-03-11 00:00:00.0</bxzzrq><hxnbkd></hxnbkd><sjhm>13357153855</sjhm><hmbh></hmbh><fzrq>2013-03-12 15:01:11.0</fzrq><hdzzl></hdzzl><wfxwmc>机动车未按规定在限制、禁止的区域通行</wfxwmc><jkbj>0</jkbj><nszm></nszm><qmbh></qmbh><wfsj>2014-10-17 08:05:30.0</wfsj><gl>68</gl><syxz>A</syxz><pl>1343</pl><dphgzbh></dphgzbh><syq>1</syq><sfzmhm>58028352-8</sfzmhm><cwkg>1517</cwkg><hbdbqk>GB18352.3-2005国Ⅳ</hbdbqk><clsj>2014-10-17 08:05:30.0</clsj><xszbh>3360009792853</xszbh><zzxxdz>浙江省杭州市西湖区文一路123号银马公寓1-1-301</zzxxdz><dwbh></dwbh><ltgg>185/70 R14 88H</ltgg><zbzl>1125</zbzl><cwkk>1728</cwkk><syr>杭州车厘子智能科技有限公司</syr><hphm>浙A1NN15</hphm><dabh>040035E50242</dabh><sqdm></sqdm><fdjh>B2GD1150515</fdjh><djzsbh>330013900296</djzsbh><pzbh2></pzbh2><jkpz></jkpz><pzbh1></pzbh1><hpzk></hpzk><hpzl>02</hpzl><cwkc>3999</cwkc><hdzk>5</hdzk><bpcs>0</bpcs><zsxxdz>浙江省杭州市西湖区西溪路525号C楼225室</zsxxdz><hgzbh>YF1442000380948</hgzbh><wfxw>7045</wfxw><xsdw></xsdw><zzcmc>上海汽车集团股份有限公司</zzcmc><ccrq>2013-01-21 00:00:00.0</ccrq><fdjrq>2013-03-12 15:01:25.0</fdjrq><yxqz>2015-03-31 00:00:00.0</yxqz><hxnbgd></hxnbgd><qlj>1505</qlj><bz></bz><nszmbh></nszmbh><lts>4</lts><jdsbh>330197141352110</jdsbh><jkpzhm></jkpzhm><dsr>葛海滨</dsr><wfdz>虎跑路莲花峰路口</wfdz><fprq>2013-03-12 14:54:05.0</fprq><dzyx></dzyx><clyt>P1</clyt><fdjxh>13S4F</fdjxh><lsh>1130312008658</lsh><xzqh>330106</xzqh><gxrq>2014-10-07 11:09:53.0</gxrq><djrq>2013-03-12 00:00:00.0</djrq><ccdjrq>2013-03-12 14:54:05.0</ccdjrq><cjjg>330197005400</cjjg><jbr>汤水明</jbr><lxdh>88190338</lxdh><gbthps></gbthps><dybj>0</dybj><gcjk>A</gcjk><clxh>CSA7130ACS</clxh><xsjg></xsjg><hdfs>A</hdfs><qpzk></qpzk><yxh></yxh><fhgzrq>2013-03-12 15:01:28.0</fhgzrq><csys>A</csys><clbj>1</clbj><kcl>0</kcl><wfjfs>0</wfjfs><zs>2</zs><zt>G</zt><ytsx>9</ytsx><wfgd>《浙江省实施〈中华人民共和国道路交通安全法〉办法》第四十六条</wfgd><zj>2520</zj><sfzmmc>B</sfzmmc><fltw>《浙江省实施〈中华人民共和国道路交通安全法〉办法》第七十五条第一款第八项</fltw><hlj>1493</hlj><clpp1>名爵牌</clpp1><clpp2></clpp2><zqyzl></zqyzl><bzcs>0</bzcs><zzxzqh>330106</zzxzqh><xsrq></xsrq><cjjgmc>景区大队四中队</cjjgmc><zxxs>1</zxxs><zsxzqh>330106</zsxzqh><jyw>321F7E7B0601F3CA8CC0F1FE7066770F010206797A6026210A2E5C462A1D532A201E010002030573007370090200060D01047D0B0A722D4070455E425A1F515C303135303333314135</jyw><clsbdh>LSJZ14C30DS015575</clsbdh></item></body></root>";
			//String result = "<?xml version=\"1.0\" encoding=\"GBK\"?><root><head><code>0</code><message>车辆识别代号验证失败，请确认您输入的车辆识别代号是否正确。</message><rownum>0</rownum></head></root>";
			System.out.print(result);

			Xml requestXml = new Xml();

			requestXml.Parse(result);
			Item requestRoot = requestXml.getRoot();

			Item requestBody = requestRoot.getChild("body");
			// 有违章记录
			if (requestBody != null) {
				List<Item> itemList = requestBody.getItemList();
				for (Item item : itemList) {
					CsViolat csViolat = new CsViolat();
					// 设置
					// 违章车辆，处理部门，添加人员，添加时间，违章时间，违章说明，违章扣分，违章罚款，处理方式，未缴款未处理
					csViolat.setCsvCar(1l);
					csViolat.setCsvBranch((short) 0);
					csViolat.setCsvAdder(0l);
					csViolat.setCsvAddTime(new Date());
					csViolat.setCsvHappenTime($.date(item.getValue("wfsj"), "yyyy-MM-dd HH:mm:ss"));
					csViolat.setCsvDecipt(item.getValue("wfdz") + " - " + item.getValue("wfxwmc"));
					csViolat.setCsvScore(Integer.parseInt(item.getValue("wfjfs")));
					csViolat.setCsvMoney(Double.valueOf(item.getValue("fkje")));
					csViolat.setCsvType((short) 0);
					csViolat.setCsvStatus((short) 0);
				}
			}

			System.out.print(requestRoot.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private static String getParams(Map<String, String> params) {
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
}
