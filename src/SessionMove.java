import java.util.Map;
import java.util.Set;

import redis.clients.jedis.JedisPoolConfig;

import com.ccclubs.action.app.official.util.OauthUtils;
import com.ccclubs.helper.redis.DBIndex;
import com.ccclubs.helper.redis.DefaultJRedisClient;
import com.ccclubs.helper.redis.tag.APP_OFFICIAL;


public class SessionMove {
	
	public static void main(String[] args) {
		JedisPoolConfig config = new JedisPoolConfig();
		config = new JedisPoolConfig();
        //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
        //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
        config.setMaxActive(100);
        //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
        config.setMaxIdle(5);
        //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
        config.setMaxWait(1000 * 100);
        //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
        config.setTestOnBorrow(false);
		
//        String pass = "test";
//        String host = "123.57.46.221";		//测试服务器
        String pass = "C!c@9c#l~l^u%84p*";
        String host = "120.26.109.248";		//测试服务器
//	        String host = "127.0.0.1";			//正式环境就用127.0.0.1
        int port = 6188;
        DefaultJRedisClient jr = new DefaultJRedisClient(host, port, pass, config, DBIndex.APP_OFFICIAL);
        
        Set list = jr.keys("*tk*");
        System.out.println(list.size());
        for(String str : (Set<String>)list){
        	str = str.substring(str.indexOf("\"")+1);
        	System.out.println(str+"\t"+jr.getValue(str));
        }
        
        JedisPoolConfig config1 = new JedisPoolConfig();
        config1 = new JedisPoolConfig();
        //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
        //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
        config1.setMaxActive(100);
        //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
        config1.setMaxIdle(5);
        //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
        config1.setMaxWait(1000 * 100);
        //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
        config1.setTestOnBorrow(false);
		
//        String pass = "test";
//        String host = "123.57.46.221";		//测试服务器
        String pass1 = "C!c@9c#l~l^u%84p*";
        String host1 = "123.57.172.114";		//测试服务器
//	        String host = "127.0.0.1";			//正式环境就用127.0.0.1
        int port1 = 6188;
        DefaultJRedisClient jr1 = new DefaultJRedisClient(host1, port1, pass1, config1, DBIndex.APP_OFFICIAL);
        for(String map : (Set<String>)list){
        	map = map.substring(map.indexOf("\"")+1);
        	System.out.println(map+"\t"+jr.getValue(map));
        	Map val = (Map)jr.getValue(map);
        	if(map !=null){
        		jr1.setValue(map, val, 60L * 60 * 24 * 7); //7天过期
        	}
        }
        
	}
}
