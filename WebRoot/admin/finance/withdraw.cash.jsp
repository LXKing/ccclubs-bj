<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>

<style>
.cashinfo{margin:10px;font-size:12px;padding:10px;}
.cashinfo span{font-size:12px;}
.cashinfo .r{color:red;}
.cashinfo .g{color:green;}
.cashinfo .b{color:blue;}
.cashinfo .p{font-family:impact;font-size:14px;}
</style>
${lz:set("hostid",(lz:or(cscrHost,csorHost))) }
${lz:set("hostid",(lz:or(hostid,session_like_host))) }
${lz:set("operCashInfo",(get:OperCashInfo(hostid)))}
<lz:set name="after$form">
<div class="cashinfo highlight">
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	当前城市：<span>${operCashInfo.host.shName}</span>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	帐户资金：<span class="r p">${lz:digit(operCashInfo.comein-operCashInfo.comeout-operCashInfo.extracted,2)}</span>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	冻结额：<span class="b p">${lz:digit(operCashInfo.freezed,2)}</span>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	可用额：<span class="g p">${lz:digit(operCashInfo.comein-operCashInfo.comeout-operCashInfo.freezed,2)}</span>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	已提取：<span>${lz:digit(operCashInfo.extracted,2)}</span>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	总收入：<span>${lz:digit(operCashInfo.comein,2)}</span>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	总支出：<span>${lz:digit(operCashInfo.comeout,2)}</span>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<button onclick="withdraw()" class="button" type="button">
		<img align="absmiddle" src="${basePath}admin/images/icons/20088256422923477806.png"/>
		提款&nbsp;&nbsp;
	</button>
</div>
<script>
function withdraw(){
	var url = "${basePath}admin/finance/cashrecord_edit.do";
	var params = {entrypoint:"${entrypoint}",ctrl:{title:"提款"},"csCashRecord.cscrHost":"${hostid}"};
	href(url,params);
}
</script>
</lz:set>