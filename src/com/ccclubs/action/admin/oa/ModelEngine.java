package com.ccclubs.action.admin.oa;

import lazy3q.code.model.Model;

import com.ccclubs.model.SrvModel;

public class ModelEngine {

	/**
	 * 保存表到数据库
	 * 生成代码到项目
	 * 加载代码到内存
	 * @param srvModel
	 */
	public static void createModel(SrvModel srvModel) {
		Model model = getAptModel(srvModel);
		saveModelTable(srvModel);
	}

	/**
	 * 根据SrvModel获取AptModel
	 * @param srvModel
	 * @return
	 */
	private static Model getAptModel(SrvModel srvModel) {
		Model model = new Model();
		model.setCaption(srvModel.getSmName());
		model.setAction(srvModel.getSmAction());
		model.setName("Sm"+srvModel.getSmAction());
		model.setTable("sm_"+srvModel.getSmAction());
		
		return null;
	}

	/**
	 * 创建、保存表结构
	 * @param srvModel
	 */
	private static void saveModelTable(SrvModel srvModel) {
		
		
		
	}

}
