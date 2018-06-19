			<s:if test="#request.type=='from'">
		 	 	<set label="${item.cso_from==0?"网站预订":""}${item.cso_from==1?"后台预订":""}${item.cso_from==2?"微信预订":""}${item.cso_from==3?"App预订":""}${item.cso_from==4?"企业预订":""}${item.cso_from==5?"支付宝预订":""}${empty item.cso_from?"未知来源":""}[${view=="count"?value:(lz:digit(value,2))}${unit}]" value="${lz:digit(value,2)}" /> 
			 </s:if>
			 <s:if test="#request.type=='model'">
		 	 	  <set label="${(get:CsCarModel(item.cso_model)==null)?"未知车型":(get:CsCarModel(item.cso_model).cscmName)}[${view=="count"?value:(lz:digit(value,2))}${unit}]" value="${lz:digit(value,2)}" />
			 </s:if>
			 <s:if test="#request.type=='car'">
		 	 	<set label="${(get:CsCar(item.cso_car)==null)?"未知车牌":(get:CsCar(item.cso_car).cscNumber)}[${view=="count"?value:(lz:digit(value,2))}${unit}]" value="${lz:digit(value,2)}" /> 
			 </s:if>
			 <s:if test="#request.type=='outlets'">
		 	 	<set label="${(get:CsOutlets(item.cso_outlets)==null)?"未知网点":(get:CsOutlets(item.cso_outlets).csoName)}[${view=="count"?value:(lz:digit(value,2))}${unit}]" value="${lz:digit(value,2)}" /> 
			 </s:if>
			 <s:if test="#request.type=='area'">
		 	 	<set label="${(get:SrvProperty(item.cso_area)==null)?"未知区域":(get:SrvProperty(item.cso_area).spName)}[${view=="count"?value:(lz:digit(value,2))}${unit}]" value="${lz:digit(value,2)}" /> 
			 </s:if>