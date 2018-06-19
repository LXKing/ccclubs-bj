<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<s:bean id="lz" name="com.lazy3q.web.helper.$"/> 
	
	
	<s:if test="#request.waiting==true">
		${lz:set("message","该会员已经有未完成的退款在处理，请等待完成后再处理该退款")}
		<lz:set name="jsonString">
			{
				title:"财务退款",
				editable:false,
				visible:false
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:if>
	<s:elseif test="csRefund.csrStatus!=1">
		${lz:set("message","只有状态为'等待退款'的退款才能进行财务退款")}
		<lz:set name="jsonString">
			{
				title:"财务退款",
				editable:false,
				visible:false
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:elseif>
	<s:elseif test="#request.money<0">
		${lz:set("message","对不起，当前会员无余额可退")}
		<lz:set name="jsonString">
			{
				title:"财务退款",
				editable:false,
				visible:false
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:elseif>
	<s:else>		
		<lz:set name="jsonString">
			{
				title:"财务退款",
				editable:false,
				visible:false,
				action:"refund_finish.do",
				fields:{
					csrMember:{editable:false,visible:true}
					,csrAmount:{editable:false,visible:true}
					,csrAccountNo:{editable:false,visible:true}
					,csrAccountName:{editable:false,visible:true}
					,csrMobile:{editable:false,visible:true}
					,refunds:{editable:false,visible:true}
					,csrMemo:{editable:true,visible:true}
					,csrRemark:{editable:false,visible:true}
				},
				submits:[
					{name:"完成退款",value="提交退款"}
				]
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
		
		<lz:set name="before$refunds">
		<dl style="width:98%;">
			<dt></dt>
			<dd class="wide" style="color:blue;">
				提交退款后，系统将自动从会员帐户中扣除所有余额(接口退款方式将会有延时),请关注该会员消费记录和余额<br/>
				当前会员可退款额<span style="color:red;font-weight:bold;font-size:16px;">${money}</span>元，有可能会以多种方式退款，请注意核对已退金额
				<button onclick="addRefund();" class="button" type="button">新增退款</button>
			</dd>
		</dl>
		</lz:set>
		
		
		<lz:set name="refundForm">
			<table width="100%" border="0" cellpadding="5" cellspacing="5">
			  <tr>
			    <td width="88" align="right">退款方式：</td>
			    <td width="280">
			    	<select class="narrow" id="returnType" name="returnType">
						<option value="">请选择</option>
						<option value="0" ${csRefund.csrReturnType==0?"selected":""}>支付宝退款</option>
						<option value="10" ${csRefund.csrReturnType==0?"selected":""}>招商银行退款</option>
						<option value="21">支付宝转帐</option>
						<option value="22">银行卡转帐</option>
						<option value="23">邮局汇款</option>
						<option value="24">现金退款</option>
					</select>
				</td>
			  </tr>
			  <tr>
			    <td align="right">退款金额：</td>
			    <td>
					<input class="input" type="text" id="returnMoney" name="returnMoney" style="width:88px;" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''"/>
					<span id="maxCanRet">请选择退款方式</span>
				</td>
			  </tr>
			  <tr>
			    <td align="right">退款备注：</td>
			    <td>
					<input class="narrow input" type="text" id="returnMemo" name="returnMemo" maxlength="255"/>
				</td>
			  </tr>
			</table>
		</lz:set>
		
		<script>
		var mapping=${lz:json(mapping)};
		function addRefund(){
			var dialog = $("${lz:js(refundForm)}").dialog({
				title:"新增退款",
				modal:true,
				onOk:function(){
					return submitRefund();
				}
			});
			dialog.find("#returnType").change(function(){
				if($(this).val()==""){
					$("#maxCanRet").val("请选择退款方式");
					dialog.find("#returnMoney").val("${money}");
					return;
				}
				var max = mapping[$(this).val()];
				if(typeof(max)=="undefined")
					max = ${money};
				else
					max = Math.min(max,${money});
				$("#maxCanRet").html("当前退款方式最多可退"+max);
				dialog.find("#returnMoney").val("");
			});
		}
		function submitRefund(){
			if($("#returnType").val()==""){
				top.$.toast("请选择退款方式");
				return false;
			}
			var max = mapping[$("#returnType").val()];
			if(typeof(money)=="undefined")
				max = ${money};
			else
				max = Math.min(max,${money});
			var money = $("#returnMoney").val();
			if($("#returnMoney").val()==""){
				top.$.toast("请输入退款金额");
				return false;
			}
			if(Number(money)>max){
				top.$.toast("输入的退款金额超过了最大限制");
				return false;
			}
			$.loading(true);
			$.submit("refund_refund.do",{
				id:${csRefund.csrId},
				type:$("#returnType").val(),
				money:money,
				memo:$("#returnMemo").val(),
				editorpoint:$("#editorpoint").val()
			});		
		}
		</script>
		
		
		
		<s:if test="#request.total<#request.money">
			<!-- 不可以以支付宝退款方式退款 -->
			<script>
			$(function(){
				$("#csrReturnType option[value=0]").remove();
			});
			</script>
		</s:if>
		<s:else>
			<!-- 可以以支付宝退款方式退款 -->
			<script>
			$(function(){
				$("#csrReturnType").val(0);
				if($("#csrReturnType").val()=="0")
					$(".form button[name=submiter]").attr("target","_self");
				$("#csrReturnType").change(function(){
					if($(this).val()=="0")
						$(".form button[name=submiter]").attr("target","_self");
					else
						$(".form button[name=submiter]").removeAttr("target");
				});
				$(".editform").submit(function(event){
					if(event.result==true && $(".editform").attr('target')==caller.name){				
						parent.$("#"+window.name).attr("width",1000).parents(".dialog").data("onSize")();
					}
				});				
			});
			</script>
		</s:else>
	</s:else>