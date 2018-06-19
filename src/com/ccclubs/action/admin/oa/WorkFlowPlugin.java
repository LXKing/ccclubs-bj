package com.ccclubs.action.admin.oa;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lazy3q.code.model.Model;
import lazy3q.code.model.Option;
import lazy3q.code.model.Project;
import lazy3q.code.model.Property;

import com.ccclubs.helper.LoginHelper;
import com.ccclubs.model.SrvWorkflow;
import com.ccclubs.plugin.BasePlugin;
import com.ccclubs.plugin.BasePlugin.Link;
import com.ccclubs.service.admin.ISrvWorkflowService;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.ConfigHelper;
import com.lazy3q.web.helper.Lazy;
import com.lazy3q.web.helper.$;
import com.lazy3q.xml.Item;
import com.lazy3q.xml.Xml;

public class WorkFlowPlugin extends BasePlugin{
	
	/**
	 * 在编辑之前读取当前工作流程的配置信息
	 * @param controller
	 * @param object
	 */
	public static void before(Map controller,Object object){
		controller.remove("before");
		Long workflow = Long.parseLong(controller.get("workflow").toString());
		String process = controller.get("process").toString();
		Map<String, Element> elements = WorkFlowPlugin.getWorkFlowElements(workflow);
		Element element = WorkFlowPlugin.getWorkFlowElement(workflow, process);
		if(element==null)return;
		//把流程配置中的参数复制到原参数中
		Map<String,Object> map = element.controller;
		for(Entry<String,Object> entry:map.entrySet()){
			controller.put(entry.getKey(), entry.getValue());
		}
		//获取当前流程的按钮
		Nodal nodal = (Nodal) element;
		List<Map> submits = new ArrayList();
		for(Arrow arrow:nodal.getTargets()){
			Map submit = new HashMap();
			submit.put("name", arrow.getName());
			submit.put("value", arrow.getId());
			submits.add(submit);
		}
		controller.put("submits", submits);
	}
	
	public static SrvWorkflow getSrvWorkflow(Long workflow){
		ISrvWorkflowService srvWorkflowService = Lazy.GetSpringBean("srvWorkflowService");
		return srvWorkflowService.getSrvWorkflowById(workflow);
	}
		
	/**
	 * 获取工作流菜单项
	 * @return
	 */
	public List<Link> getLinks(){
		return new ArrayList();		
	}
	
	
	public static List<Link> getWorkFlowLinks(){
		return new WorkFlowPlugin().getLinks();
	}	
	
	
	/**
	 * 获取classpath下的config.xml
	 * @return
	 */
	private static Xml GetConfigXml() {
		Xml proXml = new Xml();
		String charset="UTF-8";
		try{
			if(ClassLoader.class.getResource("/config.xml")!=null)
				proXml.Parse(WorkFlowPlugin.class.getResourceAsStream("/config.xml"));	
			else if(Thread.currentThread().getContextClassLoader().getResource("/config.xml")!=null)
				proXml.Parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("/config.xml"));	
			else if(ConfigHelper.class.getResource("/config.xml")!=null)
				proXml.Parse(WorkFlowPlugin.class.getResourceAsStream("/config.xml"));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return proXml;
	}
	
	
	static List<Model> models = new ArrayList();
	static Project project = null;
	public static Project GetProject(){
		if(project!=null)return project;
		project = new Project();
		Xml proXml=GetConfigXml();
		Item projectItem = proXml.getRoot();	
		Field[] projectAttributes = project.getClass().getFields();
		//根据常量和xml配置项目
		for(Field projectAttribute:projectAttributes){
			String xmlAttribute = projectItem.getAttribute(projectAttribute.getName());
			if(xmlAttribute==null)continue;
			try{
				projectAttribute.setAccessible(true);//设置允许访问
				projectAttribute.set(project, xmlAttribute);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return project;
	}
	public static Model getModel(String name){
		List<Model> list = GetAllModels();
		for(Model model:list){
			if(model.getName().equals(name))
				return model;
		}
		return null;		
	}
	/**
	 * 获取系统表单
	 * @return
	 */
	public static List GetAllModels(){		
		if(!models.isEmpty())
			return models;		
		Xml proXml=GetConfigXml();
		Item root = proXml.getRoot();		
		//取xml中的Model的字段
		List<Item> modelItems = root.getItemList();
		//遍历，生成一个个的Model对象，放到project的modes列表里
		for(Item modelItem:modelItems)
		{
			if(modelItem.getName().equals("Model"))//对象
			{
				Model model = getModelFromXmlItem(modelItem);
				models.add(model);
			}
		}		
		return models;
	}
	
	public static Model getModelFromXmlItem(Item modelItem) {
		Model model=new Model();
		
		Field[] modelAttributes = model.getClass().getFields();
		//遍历Model对象各属性并根据xml里读来的数据赋值
		for(Field modelAttribute:modelAttributes)
		{
			String key=modelAttribute.getName();
			String xmlAttribute=modelItem.getAttribute(key);
			String classType = modelAttribute.getType().getSimpleName();//取model对象字段的类型
			try{
				modelAttribute.set(model, Convert(xmlAttribute, classType));
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}		
		List<Item> cpItems = modelItem.getItemList();
		for(Item cpItem:cpItems)
		{
			
			if(cpItem.getName().equals("Property"))
			{
			   //遍历，生成一个个的Property对象，放到model的propertys列表里					
				Property property=new Property();
				model.addPropertys(property);
				Field[] propertyAttributes = property.getClass().getFields();
				//遍历Property对象各属性并根据xml里读来的数据赋值
				for(Field propertyAttribute:propertyAttributes)
				{
					String key=propertyAttribute.getName();
					String xmlAttribute=cpItem.getAttribute(key);
					String classType = propertyAttribute.getType().getSimpleName();//取Property对象字段的类型
					try{
						propertyAttribute.set(property, Convert(xmlAttribute, classType));
					}catch(Exception ex){
						ex.printStackTrace();
					}					
				}
				
				//取xml中的Option的字段
				List<Item> optionItems = cpItem.getItemList();
				//遍历，生成一个个的Option对象，放到model的options列表里
				for(Item optionItem:optionItems)
				{
					Option option=new Option();
					property.addOptions(option);
					Field[] optionAttributes = option.getClass().getFields();
					//遍历Option对象各属性并根据xml里读来的数据赋值
					for(Field optionAttribute:optionAttributes)
					{
						String key=optionAttribute.getName();
						String xmlAttribute=optionItem.getAttribute(key);
						String classType = optionAttribute.getType().getSimpleName();//取Option对象字段的类型
						try{
							optionAttribute.set(option, Convert(xmlAttribute, classType));
						}catch(Exception ex){
							ex.printStackTrace();
						}
					}
				}
			}			
		}
		return model;
	}
	
	/**
	 * 从String转到指定类型值
	 * @param value
	 * @param type
	 * @return
	 */
	public static Object Convert(String value,String type)
	{
		if(type==null)
			return null;
		if(type.equals("Boolean"))
		{
			return value==null?false:Boolean.valueOf(value);
		}
		else if(type.equals("String"))
		{
			return value==null?"":value;
		}
		else if(type.equals("Integer"))
		{
			return value==null?0:Integer.parseInt(value);
		}
		else if(type.equals("Long"))
		{
			return value==null?0L:Long.parseLong(value);
		}
		else
			throw new RuntimeException("不能将["+value+"]转换为类型["+type+"]");		
	}
			
	
	public static Element getWorkFlowElement(Long workflow,String process){
		Map<String, Element> elements = WorkFlowPlugin.getWorkFlowElements(workflow);
		Element element = null;
		if(elements==null)return null;
		//解析元素节点配置
		for(Entry<String,Element> entry:elements.entrySet()){
			if(Lazy.equals(entry.getKey(), process))
				return entry.getValue();
		}
		return null;
	}
	
	
	
	public static Map<String,Element> getWorkFlowElements(Long workflow){
		Map<String,Element> elements = Lazy.GetRequest("workflow-elements-"+workflow);
		if(elements!=null)
			return elements;
		else
			elements=new HashMap();		
	
		SrvWorkflow srvWorkflow = WorkFlowPlugin.getSrvWorkflow(workflow);
		Map<String,Map> uimap = Lazy.eval(srvWorkflow.getSwPaint());
		Map<String,Map> configmap = Lazy.eval(srvWorkflow.getSwConfig());
		//解析所有元素节点
		for(Entry<String,Map> entry:uimap.entrySet()){
			Element element = null;
			String uuid = entry.getKey();
			String type = entry.getValue().get("type").toString();
			if(type.equals("Start"))
				element = new Start();
			else if(type.equals("End"))
				element = new End();
			else if(type.equals("Process"))
				element = new Process();
			else if(type.equals("Condition"))
				element = new Condition();
			else if(type.equals("Arrow"))
				element = new Arrow();
			element.setId(uuid);
			element.setName(entry.getValue().get("caption").toString());	
			element.setType(type);
			elements.put(uuid, element);
		}
		//解析关联关系
		for(Entry<String,Map> entry:uimap.entrySet()){
			String uuid = entry.getKey();
			String type = entry.getValue().get("type").toString();
			if(type.equals("Arrow")){
				
				Map startMap = ((Map)entry.getValue().get("start"));
				Map finishMap = ((Map)entry.getValue().get("finish"));
				Arrow arrow = (Arrow) elements.get(uuid);
				
				Nodal source =startMap==null ? null : (Nodal) elements.get(startMap.get("paint"));	
				if(source!=null){
					arrow.setSource(source);	
					source.addTarget(arrow);
				}
				
				Nodal target =finishMap==null ? null : (Nodal) elements.get(finishMap.get("paint"));
				if(target!=null){
					arrow.setTarget(target);
					target.addSource(arrow);
				}
			}else if(type.equals("Start")){
				;
			}else if(type.equals("End")){
				;
			}else if(type.equals("Process")){
				
			}else if(type.equals("Condition")){
				;
			}
		}
		//解析元素节点配置
		for(Entry<String,Map> entry:configmap.entrySet()){
			String uuid = entry.getKey();
			Element element = elements.get(uuid);
			element.setController(entry.getValue());
		}
		
		Lazy.SetRequest("workflow-elements-"+workflow, elements);
		
		return elements;
	}
	
	/**
	 * 获取工作流的开始节点列表
	 * @param id
	 * @return
	 */
	public static Start getWorkFlowStart(Long id){
		Map<String,Element> elements = getWorkFlowElements(id);
		if(elements==null)
			return null;
		//解析元素节点配置
		for(Entry<String,Element> entry:elements.entrySet()){
			String uuid = entry.getKey();
			Element element = elements.get(uuid);
			if(element.getClass()==Start.class)
				return (Start) element;
		}
		return null;
	}
	
	/**
	 * 获取工作流的结束节点列表
	 * @param id
	 * @return
	 */
	public static List<End> getWorkFlowEnds(Long id){
		Map<String,Element> elements = getWorkFlowElements(id);
		List<End> ends = new ArrayList();
		if(elements==null)
			return ends;
		//解析元素节点配置
		for(Entry<String,Element> entry:elements.entrySet()){
			String uuid = entry.getKey();
			Element element = elements.get(uuid);
			if(element.getClass()==End.class)
				ends.add((End) element);
		}
		return ends;
	}
		
	
	
	/**
	 * 检测流程的有效性
	 * @return
	 */
	public static List<String> warns(Long id){
		final List<String> warns = new ArrayList();
		
		//流程所有元素
		Map<String, Element> elements = WorkFlowPlugin.getWorkFlowElements(id);
		
		
		/**###################检测流程节点完整性######################**/
		Start start = getWorkFlowStart(id);
		if(start==null){
			warns.add("没有定义开始节点");
			return warns;
		}
		if(getWorkFlowEnds(id).isEmpty()){
			warns.add("没有定义结束节点");
			return warns;
		}			
		
		final List<String> checked = new ArrayList();
		final List<End> ends = new ArrayList();
		
		new Function(){
			@Override
			public <T> T execute(Object... object) {					
				Nodal nodal = (Nodal) object[0];
				if(checked.indexOf(nodal.getId())>-1)
					return null;
				else
					checked.add(nodal.getId());
				if(nodal.getClass()==End.class){
					ends.add((End) nodal);
				}
				List<Arrow> targets = nodal.getTargets();
				if(targets.isEmpty() && nodal.getClass()!=End.class)
					warns.add("["+nodal.getName()+"]没有流转");
				for(Arrow arrow:targets){
					checked.add(arrow.getId());
					if(arrow.target==null){
						warns.add("箭头["+arrow.getName()+"]没有目标");
						continue;
					}
					if(arrow.source==null){
						warns.add("箭头["+arrow.getName()+"]没有起点");
						continue;
					}
					this.execute(arrow.target);
				}
				return null;
			}				
		}.execute(start);	
		
		if(ends.isEmpty()){
			warns.add("流程从开始到结束未连通");
		}
		
		
		//解析元素是否与整体分离
		for(Entry<String,Element> entry:elements.entrySet()){
			if(checked.indexOf(entry.getKey())==-1)
				warns.add("["+entry.getValue().getName()+"]与流程分离");
		}
		/**###################检测流程节点完整性结束######################**/
		
		
		
		
		/**###################检测流程的配置######################**/
		for(Entry<String,Element> entry:elements.entrySet()){
			Element element = entry.getValue();
			if(element.getClass()==Start.class||element.getClass()==Process.class){
				Map controller = element.controller;
				if(controller==null){
					warns.add("["+entry.getValue().getName()+"]没有配置信息");
				}else{
					if(controller.get("designate")==null){
						warns.add("["+entry.getValue().getName()+"]没有指定角色方式");
					}else{
						String designate=(String) controller.get("designate");
						if((Lazy.equals(designate, "{1}") || Lazy.equals(designate, "{2}")) 
								&& (controller.get("roles")==null  || Lazy.empty((String)controller.get("roles")))){
							warns.add("["+entry.getValue().getName()+"]为任务分派或系统指定时，必须配置角色");
						}
					}
					if(controller.get("fields")==null){
						warns.add("["+entry.getValue().getName()+"]没有配置表单");
					}
				}				
			}
			if(element.getClass()==Arrow.class){
				Arrow  arrow = (Arrow) element;
				Map controller = element.controller;
				Nodal source = arrow.getSource();
				if(source==null)source=new Nodal();
				Nodal target = arrow.getTarget();
				if(target==null)target=new Nodal();
				if(controller==null){
					warns.add("("+source.getName()+")-["+entry.getValue().getName()+"]->("+target.name+")没有配置信息");
				}else{ 
					if(arrow.getSource().getClass()==Condition.class){
						if(controller.get("relation")==null)
							warns.add("箭头("+source.getName()+")-["+entry.getValue().getName()+"]->("+target.name+")没有指定条件关系");
						if(controller.get("conditions")==null || ((List)controller.get("conditions")).size()==0)
							warns.add("箭头("+source.getName()+")-["+entry.getValue().getName()+"]->("+target.name+")没有配置转跳条件");						
					}else if(controller.get("synergy")==null){					
						warns.add("箭头("+source.getName()+")-["+entry.getValue().getName()+"]->("+target.name+")没有指定协同机制");
					}
				}
			}
		}
		
		
		
					
		return warns;
	}
	
	
	public static class Element{
		String id;
		String name;
		String type;
		Map controller=new HashMap();//配置信息
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public Map getController() {
			return controller;
		}
		public void setController(Map controller) {
			this.controller = controller;
		}
	}
	
	
	public static class Arrow extends Element{
		Nodal target;
		Nodal source;
		public Nodal getTarget() {
			return target;
		}
		public void setTarget(Nodal target) {
			this.target = target;
		}
		public Nodal getSource() {
			return source;
		}
		public void setSource(Nodal source) {
			this.source = source;
		}
	}
	
	
	public static class Nodal extends Element{
		List<Arrow> sources=new ArrayList();
		List<Arrow> targets=new ArrayList();
		public boolean addSource(Arrow arrow) {
			return sources.add(arrow);
		}
		public List<Arrow> getSources() {
			return sources;
		}
		public void addTarget(Arrow arrow) {
			targets.add(arrow);
		}
		public List<Arrow> getTargets() {
			return targets;
		}		
	}	
	public static class Process extends Nodal{
		
	}
	public static class Start extends Nodal{

	}
	public static class End extends Nodal{
		
	}
	public static class Condition extends Nodal{

	}
	

	
	
	
	
	public static void main(String...aa){
		/*
		String expressions = "\\[4\\]|\\[2\\]|\\(6\\)|\\{\\*\\}";
		String limits="{*}[1](3)[1](3)[67][27]";
		Matcher match = Pattern.compile(expressions).matcher(limits);
		System.out.println(match.find());
		*/
		
		$.trace($.json(WorkFlowPlugin.getModel("SrvField")));
		
	}


	

}
