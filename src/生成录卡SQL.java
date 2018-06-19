import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;


public class 生成录卡SQL {
	
	static String hostId = "1";
	
	public static void main(String[] args) {
		try {
			List<String> contLines = FileUtils.readLines(new File("d:/xxx-5.txt"), "UTF-8");
			StringBuffer sbf = new StringBuffer();
			for(String str : contLines){
				//System.out.println(str);
				String[] strArr = str.split(" ");
				String sql = "INSERT INTO cs_ev_card(csec_host,csec_number,csec_rfid,csec_remark,csec_add_time,csec_flag,csec_status) " +
						"VALUES ('"+hostId+"', '"+strArr[0]+"', '"+strArr[1]+"', '', sysdate(), '0', '1');";
				System.out.println(sql);
				sbf.append(sql).append("\r\n");
			}
			FileUtils.writeStringToFile(new File("d:/xxx-5.txt.sql"), sbf.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
