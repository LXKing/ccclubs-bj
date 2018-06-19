package com.ccclubs.plugin;

import java.util.List;

public abstract class BasePlugin {
	
	public String name=null;
	public String caption=null;
	public String welcome=null;
		
	public abstract List<Link> getLinks();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getWelcome() {
		return welcome;
	}

	public void setWelcome(String welcome) {
		this.welcome = welcome;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}
		
	public class Link {
		
		String name;
		String url;
		String icon;
		String path;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getIcon() {
			return icon;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}

	}


	


	
	
}


