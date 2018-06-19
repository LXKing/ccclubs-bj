import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SessionTest {
	
	static String sessionid = null;
	static final String path = "http://test.api.ccclubs.com//oauth.code.do";
	
	public static void main(String[] args) {
		
		try {
			//创建连接
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            if(sessionid !=null){
            	connection.setRequestProperty("cookie", sessionid);
            }
            connection.connect();

            //POST请求
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            //out.writeBytes(null);
            out.flush();
            out.close();

            //读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "UTF-8");
                sb.append(lines);
            }
            
            //如果第一次sessionid为空时获取并赋值给全局变量，供下次调用时使用
            if(sessionid == null){
            	Map<String, String> cookies = readCookies(connection);
            	if(cookies.get("JSESSIONID") !=null){
            		sessionid = "JSESSIONID="+cookies.get("JSESSIONID");
            	}
            }
			
            
            reader.close();
            connection.disconnect();
            System.out.println(sb.toString());
            
            //==========================================测试第二次调用的场景==================================================
            
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            connection.setRequestProperty("cookie", sessionid);
            connection.connect();
            
            //POST请求
            DataOutputStream out1 = new DataOutputStream(connection.getOutputStream());
            //out.writeBytes(null);
            out1.flush();
            out1.close();

            //读取响应
            BufferedReader reader1 = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String lines1;
            StringBuffer sb1 = new StringBuffer("");
            while ((lines1 = reader1.readLine()) != null) {
                lines1 = new String(lines1.getBytes(), "UTF-8");
                sb1.append(lines1);
            }
            reader1.close();
            connection.disconnect();
            System.out.println(sb1.toString());
            
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	static Map<String, String> readCookies(HttpURLConnection url_con){
		Map<String, String> cookies = new HashMap<String, String>();
		List<String> cookieList = url_con.getHeaderFields().get("Set-Cookie");
		if(cookieList != null){
			for(String cookie : cookieList){
				int p = cookie.indexOf("=");
				String key = cookie.substring(0, p);
				String value = cookie.substring(p+1, cookie.indexOf(";"));
				cookies.put(key, value);
			}
		}
		return cookies;
	}
}
