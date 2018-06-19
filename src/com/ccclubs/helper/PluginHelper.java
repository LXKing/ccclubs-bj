package com.ccclubs.helper;

import java.util.ArrayList;
import java.util.List;

import com.ccclubs.plugin.BasePlugin;
import com.lazy3q.web.helper.ConfigHelper;
import com.lazy3q.xml.Item;
import com.lazy3q.xml.Xml;

public class PluginHelper {

	static List<BasePlugin> plugins = null;
	
	public static BasePlugin getPlugin(String name){
		plugins = getPlugins();		
		for(BasePlugin plugin:plugins){
			if(plugin.getName().equals(name))
				return plugin;
		}
		return null;
	}	
	public static synchronized List<BasePlugin> getPlugins(){	
		if(plugins!=null)
			return plugins;		
		plugins = new ArrayList();
		
		Xml proXml=new Xml();		
		String charset="UTF-8";
		try{
			if(ClassLoader.class.getResource("/plugins.xml")!=null)
				proXml.Parse(PluginHelper.class.getResourceAsStream("/plugins.xml"));	
			else if(Thread.currentThread().getContextClassLoader().getResource("/plugins.xml")!=null)
				proXml.Parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("/plugins.xml"));	
			else if(ConfigHelper.class.getResource("/plugins.xml")!=null)
				proXml.Parse(PluginHelper.class.getResourceAsStream("/plugins.xml"));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		Item root = proXml.getRoot();
		
		List<Item> pluginItems = root.getItemList();
		for(Item pluginItem:pluginItems)
		{
			if(pluginItem.getName().equals("plugin"))
			{
				String pluginClass = pluginItem.getAttribute("class");
				BasePlugin plugin = null;
				try {
					plugin = (BasePlugin) Class.forName(pluginClass).newInstance();
					plugin.setName(pluginItem.getAttribute("name"));
					plugin.setCaption(pluginItem.getAttribute("caption"));
					plugin.setWelcome(pluginItem.getAttribute("welcome"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				plugins.add(plugin);
			}
		}
		return plugins;
	}	
	
}
