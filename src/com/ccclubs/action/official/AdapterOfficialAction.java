package com.ccclubs.action.official;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ccclubs.action.unit.UnitLoginHelper;
import com.ccclubs.model.CsArea;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsCarModel;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsUnitInfo;
import com.ccclubs.model.CsUnitPerson;
import com.lazy3q.lang.type;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.WebHelper.LzMap;

public class AdapterOfficialAction {
	public String person_inquire() {
		try{
 			Map<String,Object> memparams=new HashMap();
			LzMap params = $.Map();
			String strTerm=$.getString("value");
			String strDefinex = " csup_info = ("+UnitLoginHelper.getLogin().getCsuuUnitInfo()+") ";
			if(!$.empty(strTerm)){
				strDefinex += " and (";
				strDefinex += "csup_name like '%"+strTerm.replaceAll("'", "''")+"%'";
				strDefinex += " and csup_status = 1";
				strDefinex+=")";				
			}
			params.put("definex",strDefinex);
			
			List<CsUnitPerson> list = CsUnitPerson.list(params, 10);
			List<Map> result = new java.util.ArrayList();
			//String template = "{name}({mobile})[{username}]<{email}>";
			for(CsUnitPerson csUnitPerson:list){
				Map map = new HashMap();
				map.put("value", $.zerofill(csUnitPerson.getCsupId(), 6));
				csUnitPerson.setCsupName(csUnitPerson.getCsupName()+"("+csUnitPerson.get$csupMember().getCsmMobile()+")");
				map.put("text",csUnitPerson.getKeyValue());
				result.add(map);
			}
			return $.SendAjax(result, "UTF-8");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax("[]", "UTF-8");
		}
	}
	
	public String car_query() {
		try{
			LzMap params = $.Map();
			CsUnitInfo unitInfo = UnitLoginHelper.getLogin().get$csuuUnitInfo();
			if(unitInfo.getCsuiOutlets()!=null){
				params.put(CsCar.F.cscOutlets, unitInfo.getCsuiOutlets());
			}else{
				params.put(CsCar.F.cscHost, unitInfo.getCsuiHost());
			}
			params.add(CsCar.F.cscStatus, 1);
			String strTerm=$.getString("value");
			if(!$.empty(strTerm)){
				String strDefinex = "(";
				strDefinex += "csc_number like '%"+strTerm.replaceAll("'", "''")+"%'";
				strDefinex+=")";
				params.put("definex",strDefinex);
			}
			List<CsCar> list = CsCar.list(params, 10);
			List<Map> result = new java.util.ArrayList();
			String template = "{name}({mobile})[{username}]<{email}>";
			for(CsCar csCar:list){
				Map map = new HashMap();
				map.put("value", $.zerofill(csCar.getCscId().toString(), 6));			
				map.put("text",csCar.getCscCarNo());
				result.add(map);
			}
			return $.SendAjax(result, "UTF-8");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax("[]", "UTF-8");
		}
	}
	
	public String carmodel_query() {
		try{
 			Map<String,Object> params=new HashMap();		
			//把request传过来的(String)参数转换成java对象			
			Field[] fields = CsCarModel.F.class.getFields();
			for(Field field:fields){
				type type = field.getAnnotation(type.class);
				params.put(field.getName(), $.getObject(field.getName(), type.value()));
			}
			
			//取排序参数
			String strAsc=$.getString("asc");
			params.put("asc", strAsc);
			String strDesc=$.getString("desc");
			params.put("desc",strDesc);
			if($.empty(strAsc) && $.empty(strDesc))
				params.put("desc","cscm_id");
								
			String strTerm=$.getString("value");
			if(!$.empty(strTerm)){
				if(strTerm.toLowerCase().startsWith("id:"))//如果查询条件以id:开头，则按ID查询
					params.put("cscmId", strTerm.toLowerCase().replaceFirst("id:", ""));					
				else//按标识查询，模糊查询请带%
				{
					String strDefinex = "";
					strDefinex+=" or cscm_name like '%"+strTerm.replaceAll("'", "''")+"%'";
					strDefinex="(2=1 "+strDefinex+")";
					params.put("definex",strDefinex);
				}					
			}

			
			Boolean bObject=$.getBoolean("object",false);
			
			List<CsCarModel> list = CsCarModel.list(params, $.getInteger("size",10));
			List<Map> result = new java.util.ArrayList();
			for(CsCarModel csCarModel:list){
				Map map = new HashMap();
				map.put("value",csCarModel.getCscmId().toString());
				String strText = "";
				strText+=csCarModel.getCscmName()+",";
				map.put("text", $.js(strText));
				if(bObject==true)
					map.put("object",csCarModel);
				result.add(map);
			}
			return $.SendAjax(result, "UTF-8");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax("[]", "UTF-8");
		}	
	}

	public String outlets_tree() {
		try{
			CsUnitInfo unitInfo = UnitLoginHelper.getLogin().get$csuuUnitInfo();
			if(unitInfo.getCsuiOutlets()==null){
				Long parent = $.getLong("parent",0l);
				if(parent.longValue()==0){
					LzMap params = $.add(CsArea.F.csaHost,unitInfo.getCsuiHost());
					List<CsArea> list = CsArea.list(params, -1);
					String strAjax="";
					for(CsArea o:list){
						if(!strAjax.equals(""))
							strAjax+=",";
						strAjax+="{";
						strAjax+="\"value\":\""+o.getCsaId()+"\"";
						strAjax+=",";
						strAjax+="\"text\":\""+$.js(o.getCsaName())+"\"";
						strAjax+=",";
						strAjax+="\"more\":true";
						strAjax+=",";
						strAjax+="\"invalid\":true";					
						strAjax+="}";
					}
					return $.SendAjax("["+strAjax+"]", "UTF-8");
				}else{
					List<CsOutlets> list = CsOutlets.list($.add("csoArea", parent), -1);
					List<Map> result = new java.util.ArrayList();
					for(CsOutlets csOutlets:list){
						Map map = new HashMap();
						map.put("value", $.zerofill(csOutlets.getCsoId().toString(), 6));
						map.put("text", $.js(""+csOutlets.getCsoName()));
						result.add(map);
					}
					return $.SendAjax(result, "UTF-8");
				}
			}else{
				List<Map> result = new java.util.ArrayList();
				{
					Map map = new HashMap();
					List<CsOutlets> csOutletsList = unitInfo.get$csuiOutlets();
					if(csOutletsList!=null)
					for(CsOutlets csOutlets: csOutletsList){
						map.put("value", $.zerofill(csOutlets.getCsoId().toString(), 6));
						map.put("text", $.js(""+csOutlets.getCsoName()));
						result.add(map);
					}
				}
				return $.SendAjax(result, "UTF-8");
			}
						
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax("[]", "UTF-8");
		}
	}
	
}
