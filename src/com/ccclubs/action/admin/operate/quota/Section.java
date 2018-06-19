package com.ccclubs.action.admin.operate.quota;

import java.util.Calendar;
import java.util.Date;

import com.lazy3q.web.helper.$;

public class Section {	
	
	Date start;
	Date end;
	Type type = null;
	
	enum Type{MONTH,WEEK}
	
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}	
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	
	
	@Override
	public String toString() {
		Calendar calendar = Calendar.getInstance();		
		calendar.setTime(this.getStart());
		String sYear = String.valueOf(calendar.get(Calendar.YEAR)).substring(2)+"年";
		String sMonth = (calendar.get(Calendar.MONTH)+1)+"月";
		String sWeek = this.type==Type.WEEK ? ("第"+calendar.get(Calendar.WEEK_OF_MONTH)+"周") : "";
		return sYear+sMonth+sWeek;
	}
	/**
	 * 取环比时间段
	 * @return
	 */
	public Section getPrev(){	
		Section prev = new Section();	
		prev.setType(this.getType());
		
		prev.setEnd(this.getStart());
		Calendar calendar = Calendar.getInstance();		
		calendar.setTime(this.getStart());//设置为开始时间
		calendar.add(this.type==Type.MONTH ? Calendar.MONTH : Calendar.WEEK_OF_MONTH, -1);//月分减一		
		prev.setStart(calendar.getTime());//开始	
				
		return prev;
	}
	
	
	/**
	 * 取同比时间段
	 * @return
	 */
	public Section getLast(){		
		Section lastSection = new Section();	
		lastSection.setType(this.getType());
		
		Calendar calendar = Calendar.getInstance();		
		calendar.setTime(this.getStart());//设置为开始时间
		
		if(this.type==Type.MONTH){//月同比
			calendar.add(Calendar.YEAR, -1);//年分减一		
			lastSection.setStart(calendar.getTime());//开始		
			calendar.add(Calendar.MONTH, 1);//月分加一		
			lastSection.setEnd(calendar.getTime());
		}else{//周同比
			calendar.add(Calendar.MONTH, -1);//月分减一		
			lastSection.setStart(calendar.getTime());//开始		
			calendar.add(Calendar.WEEK_OF_MONTH, 1);//周分加一		
			lastSection.setEnd(calendar.getTime());
		}		
		
		return lastSection;
	}
	
	
	/**
	 * 根据年，月，周取时间段
	 * @param iYear
	 * @param iSection
	 * @param iWeek
	 * @return
	 */
	public static Section getSection(Integer iYear,Integer iMonth,Integer iWeek){
		Section section = new Section();
			
		Calendar calendar = Calendar.getInstance();			
		calendar.set(iYear, iMonth-1, 1, 0, 0, 0);
		if(iWeek!=null){
			calendar.set(Calendar.WEEK_OF_MONTH, iWeek);
			calendar.set(Calendar.DAY_OF_WEEK, 1);
			section.setType(Type.WEEK);
		}else{
			section.setType(Type.MONTH);
		}
		section.setStart(calendar.getTime());
		calendar.add(iWeek==null ? Calendar.MONTH : Calendar.WEEK_OF_MONTH, 1);//月分或周加一	
		section.setEnd(calendar.getTime());
		
		return section;
	}
	
	
	public static void main(String...strings){
		int year = 2014;
		int month = 5;
		Integer week = null;
		$.trace(Section.getSection(2014, month, week));
		$.trace(Section.getSection(2014, month, week).getPrev());
		$.trace(Section.getSection(2014, month, week).getLast());
	}
	
}
