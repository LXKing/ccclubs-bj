package com.ccclubs.action.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.SrvApi;
import com.ccclubs.model.SrvApiClient;
import com.ccclubs.model.SrvProperty;
import com.lazy3q.web.helper.$;

public class WikiAction {
	
	public String doc(){
			
		return "doc";
	}
	
	public String api(){
		if($.getLong("api")!=null){
			SrvApi api = SrvApi.get($.getLong("api"));
			$.setRequest("srvApi",api);
			$.setRequest("list",SrvApi.m().saType(api.getSaType()).saStatus((short)1).list(-1));
		}else if($.getLong("type")!=null){
			$.setRequest("types", SystemHelper.getPropertys("API接口分类", "api_type"));
			$.setRequest("list",  SrvApi.m().saType($.getLong("type")).saStatus((short)1).list(-1));
			$.setRequest("sort", SrvProperty.get($.getLong("type")));
		}else{
			$.setRequest("types", SystemHelper.getPropertys("API接口分类", "api_type"));
		}
		return "api";
	}
	
	
	public String tools(){
		if($.getString("ajax")==null){
			List<SrvProperty> types = SystemHelper.getPropertys("API接口分类", "api_type");
			$.setRequest("types", types);
			SrvApi api = SrvApi.get($.getLong("api"));
			$.setRequest("srvApi",api);
			if(api!=null || !types.isEmpty())
				$.setRequest("list", SrvApi.list($.add("saType",api==null?types.get(0).getSpId():api.getSaType()),-1));
			//获取系统分配的测试客户端帐号
			$.setRequest("testClient", SrvApiClient.m().sacClient("AE14").get());
			return "tools";
		}else{
			if($.getString("md5")!=null){
				return $.SendAjax($.json($.md5($.getString("md5")).toUpperCase()), $.UTF8);
			}else if($.getLong("type")!=null){
				List<SrvApi> list = SrvApi.m().saType($.getLong("type")).saStatus((short)1).list(-1);
				List<Map> array = new ArrayList();
				for(SrvApi api:list){
					array.add($.$("name", api.getSaName()).$("namespace", api.getSaNamespace()).$("id", api.getSaId()));
				}
				return $.SendAjax(array, $.UTF8);
			}else if($.getLong("api")!=null){
				return $.SendAjax(SrvApi.get($.getLong("api")).getSaParams(), $.UTF8);
			}
			return null;
		}
	}

}
