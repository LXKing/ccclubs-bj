/**修复金额类型**/
update cs_record left join cs_record_type on csr_record_type=csrt_id set cs_record.csr_money_type=cs_record_type.csrt_money_type;
/**把消费记录类型映射到会计科目**/
update cs_record set csr_record_subject=null;
update cs_record_type left join cs_record_subject on csrt_type_name=csrs_name set cs_record_type.csrt_subject=csrs_id;

完了到后台设置下每个消息消费记录类型对应的会计科目

/**修复消费记录会计科目**/
update cs_record left join cs_record_type on csr_record_type=csrt_id set cs_record.csr_record_subject=cs_record_type.csrt_subject



/** 同步消费记录到收支记录表中 **/
truncate table cs_oper_record;
insert into cs_oper_record
(
	csor_host
	,csor_title
	,csor_money_type
	,csor_record_type
	,csor_in
	,csor_out
	,csor_record
	,csor_member
	,csor_order
	,csor_object
	,csor_date_time
	,csor_adder
	,csor_status
)
select csr_host,csr_remark,csr_money_type,csrs_id,if(csrs_is_oper_in=1,abs(csr_amount),0),if(csrs_is_oper_out=1,abs(csr_amount),0),csr_id,csr_member,csr_order,csr_object,csr_add_time,csr_editor,1
from cs_record left join cs_record_subject on csr_record_subject=csrs_id where csrs_is_oper_in=1 or csrs_is_oper_out=1;



/** 同步没有同步的消费记录到收支记录表中 **/
insert into cs_oper_record
(
	csor_host
	,csor_title
	,csor_money_type
	,csor_record_type
	,csor_in
	,csor_out
	,csor_record
	,csor_member
	,csor_order
	,csor_object
	,csor_date_time
	,csor_adder
	,csor_status
)
select csr_host,csr_remark,csr_money_type,csr_record_subject,if(csr_amount<0,abs(csr_amount),0),if(csr_amount>0,abs(csr_amount),0),csr_id,csr_member,csr_order,csr_object,csr_add_time,csr_editor,1
from cs_record left JOIN cs_oper_record on csr_id=csor_record
where csr_record_subject in (15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,36,37,38) and csor_id is null;
