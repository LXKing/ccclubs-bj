import java.io.Serializable;

import redis.clients.jedis.JedisPoolConfig;

import com.ccclubs.helper.redis.DBIndex;
import com.ccclubs.helper.redis.DefaultJRedisClient;


public class JRedisDemo {
	
	static final DefaultJRedisClient<String, User> jr = new DefaultJRedisClient<String, User>(DBIndex.APP_WEIXIN);
	static{
		if(jr == null){
			try{
//				JedisPoolConfig config = new JedisPoolConfig();
//				config = new JedisPoolConfig();
//		        //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
//		        //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
//		        config.setMaxActive(100);
//		        //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
//		        config.setMaxIdle(5);
//		        //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
//		        config.setMaxWait(1000 * 100);
//		        //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
//		        config.setTestOnBorrow(false);
//				
//		        String pass = "test";
//		        String host = "123.57.46.221";		//测试服务器
//	//	        String host = "127.0.0.1";			//正式环境就用127.0.0.1
//		        int port = 6188;
//				jr = new DefaultJRedisClient<String, Object>(host, port, pass, config, true);
			}catch(Exception e){
				
			}
			
		}
	}
	
	public static void main(String[] args) {
		try{
			jr.set("test", "1");
			System.out.println(jr.get("test"));

//			User us = new User();
//			us.setName("test测试");
//			us.setPhone("15666666666");
////			us.setSex(22);
//			jr.setValue("user-test", us);
			User u = (User)jr.getValue("user-test");
			Object o = jr.getValue("user-test");
			System.out.println(u);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	static class User implements Serializable{
		
		private static final long serialVersionUID = 8772188827597016141L;

		private String name;
		
		private String phone;

//		private int sex;
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

//		public int getSex() {
//			return sex;
//		}
//
//		public void setSex(int sex) {
//			this.sex = sex;
//		}
		
		
	}
	
}
