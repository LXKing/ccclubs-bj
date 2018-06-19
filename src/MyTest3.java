

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyTest3 {
	public static void main(String[] args) {
		
		try {
			String path = "http://42.96.191.30:1215/issuer";
			//创建连接
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");	
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            
            connection.connect();

            //POST请求
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes("client_id=006&client_secret=e10adc3949ba59abbe56e057f20f883e&grant_type=client_credentials&scope=http%3A%2F%2Flocalhost%2F");
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
            
            reader.close();
            connection.disconnect();
            System.out.println(sb.toString());
            
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}	

}
