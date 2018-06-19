import java.util.Calendar;


public class MyTest4 {
	
	static final String NORMAL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	static java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	static java.text.SimpleDateFormat formatter2 = new java.text.SimpleDateFormat("yyyyMM");
	
	public static void main(String[] args) {
		java.util.Calendar calendar = java.util.Calendar.getInstance();
//		calendar.setTime(new Date());
		calendar.set(java.util.Calendar.HOUR_OF_DAY, 23);
		calendar.set(java.util.Calendar.MINUTE, 59);
		calendar.set(java.util.Calendar.SECOND, 59);
		//calendar.add(java.util.Calendar.DAY_OF_MONTH, 6);
		System.out.println( formatter.format(calendar.getTime()));
		
		System.out.println(formatter2.format(calendar.getTime()));
		
		Calendar c = Calendar.getInstance();
		int hours = c.get(Calendar.HOUR_OF_DAY); // 时
		int minutes = c.get(Calendar.MINUTE);    // 分
		int seconds = c.get(Calendar.SECOND);    // 秒
		System.out.println(seconds/10);
	}
}
