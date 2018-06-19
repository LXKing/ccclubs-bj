import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class SocketTest {

	/**
	 * @Title:intToHexString
	 * @Description:10进制数字转成16进制
	 * @param a
	 *            转化数据
	 * @param len
	 *            占用字节数
	 * @return
	 * @throws
	 */
	private static String intToHexString(int a, int len) {
		len <<= 1;
		String hexString = Integer.toHexString(a);
		int b = len - hexString.length();
		if (b > 0) {
			for (int i = 0; i < b; i++) {
				hexString = "0" + hexString;
			}
		}
		return hexString;
	}
	
	public static Map<String, String> sendTCPRequest(String IP, String port, String reqData, String reqCharset, byte[] b){
	    Map<String, String> respMap = new HashMap<String, String>();
	    OutputStream out = null;      //写
	    InputStream in = null;        //读
	    String localPort = null;      //本地绑定的端口(java socket, client, /127.0.0.1:50804 => /127.0.0.1:9901)
	    String respData = null;       //响应报文
	    String respDataHex = null;    //远程主机响应的原始字节的十六进制表示
	    Socket socket = new Socket(); //客户机
	    try {
	      socket.setTcpNoDelay(true);
	      socket.setReuseAddress(true);
	      socket.setSoTimeout(30000);
	      socket.setSoLinger(true, 5);
	      socket.setSendBufferSize(1024);
	      socket.setReceiveBufferSize(1024);
	      socket.setKeepAlive(true);
	      socket.connect(new InetSocketAddress(IP, Integer.parseInt(port)), 30000);
	      localPort = String.valueOf(socket.getLocalPort());
	      /**
	       * 发送HTTP请求
	       */
	      out = socket.getOutputStream();
	      out.write(b);
	      /**
	       * 接收HTTP响应
	       */
	      in = socket.getInputStream();
	      ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
	      byte[] buffer = new byte[512];
	      int len = -1;
	      while((len=in.read(buffer)) != -1){
	        bytesOut.write(buffer, 0, len);
	      }
	      /**
	       * 解码HTTP响应的完整报文
	       */
	      respData = bytesOut.toString(reqCharset);
	      respDataHex = formatToHexStringWithASCII(bytesOut.toByteArray());
	    } catch (Exception e) {
	      System.out.println("与[" + IP + ":" + port + "]通信遇到异常,堆栈信息如下");
	      e.printStackTrace();
	    } finally {
	      if (null!=socket && socket.isConnected() && !socket.isClosed()) {
	        try {
	          socket.close();
	        } catch (IOException e) {
	          System.out.println("关闭客户机Socket时发生异常,堆栈信息如下");
	          e.printStackTrace();
	        }
	      }
	    }
	    respMap.put("localPort", localPort);
	    respMap.put("reqData", reqData);
	    respMap.put("respData", respData);
	    respMap.put("respDataHex", respDataHex);
	    return respMap;
	  }
	
	 /**
	   * 通过ASCII码将十进制的字节数组格式化为十六进制字符串
	   * @see 该方法会将字节数组中的所有字节均格式化为字符串
	   * @see 使用说明详见<code>formatToHexStringWithASCII(byte[], int, int)</code>方法
	   */
	  private static String formatToHexStringWithASCII(byte[] data){
	    return formatToHexStringWithASCII(data, 0, data.length);
	  }
	  
	  
	  /**
	   * 通过ASCII码将十进制的字节数组格式化为十六进制字符串
	   * @see 该方法常用于字符串的十六进制打印,打印时左侧为十六进制数值,右侧为对应的字符串原文
	   * @see 在构造右侧的字符串原文时,该方法内部使用的是平台的默认字符集,来解码byte[]数组
	   * @see 该方法在将字节转为十六进制时,默认使用的是<code>java.util.Locale.getDefault()</code>
	   * @see 详见String.format(String, Object...)方法和new String(byte[], int, int)构造方法
	   * @param data   十进制的字节数组
	   * @param offset 数组下标,标记从数组的第几个字节开始格式化输出
	   * @param length 格式长度,其不得大于数组长度,否则抛出java.lang.ArrayIndexOutOfBoundsException
	   * @return 格式化后的十六进制字符串
	   */
	  private static String formatToHexStringWithASCII(byte[] data, int offset, int length){
	    int end = offset + length;
	    StringBuilder sb = new StringBuilder();
	    StringBuilder sb2 = new StringBuilder();
	    sb.append("\r\n------------------------------------------------------------------------");
	    boolean chineseCutFlag = false;
	    for(int i=offset; i<end; i+=16){
	      sb.append(String.format("\r\n%04X: ", i-offset)); //X或x表示将结果格式化为十六进制整数
	      sb2.setLength(0);
	      for(int j=i; j<i+16; j++){
	        if(j < end){
	          byte b = data[j];
	          if(b >= 0){ //ENG ASCII
	            sb.append(String.format("%02X ", b));
	            if(b<32 || b>126){ //不可见字符
	              sb2.append(" ");
	            }else{
	              sb2.append((char)b);
	            }
	          }else{ //CHA ASCII
	            if(j == i+15){ //汉字前半个字节
	              sb.append(String.format("%02X ", data[j]));
	              chineseCutFlag = true;
	              String s = new String(data, j, 2);
	              sb2.append(s);
	            }else if(j == i&&chineseCutFlag){ //后半个字节
	              sb.append(String.format("%02X ", data[j]));
	              chineseCutFlag = false;
	              String s = new String(data, j, 1);
	              sb2.append(s);
	            }else{
	              sb.append(String.format("%02X %02X ", data[j], data[j + 1]));
	              String s = new String(data, j, 2);
	              sb2.append(s);
	              j++;
	            }
	          }
	        }else{
	          sb.append("   ");
	        }
	      }
	      sb.append("| ");
	      sb.append(sb2.toString());
	    }
	    sb.append("\r\n------------------------------------------------------------------------");
	    return sb.toString();
	}
	
	public static void main(String args[]) throws Exception {
		
	
		
		//为了简单起见，所有的异常都直接往外抛  
	      //与服务端建立连接  
//		try{
//	      Socket client = new Socket(host, port);  
//	      //建立连接后就可以往服务端写数据了  
//	      Writer writer = new OutputStreamWriter(client.getOutputStream());  
//	      writer.write(val);  
//	      writer.flush();//写完后要记得flush  
//	      writer.close();  
//	      client.close();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		String host = "103.44.145.243"; // 要连接的服务端IP地址
//		int port = 35562; // 要连接的服务端对应的监听端口
		int port = 35128; // 要连接的服务端对应的监听端口
//		int port = 54781; // 要连接的服务端对应的监听端口
        
//			Socket socket = null;
//	        try {
//	            socket = new Socket(host, port);
//	            
//	        	LoginCMD login = new LoginCMD();
//	    		String val = login.getHexString(socket.getLocalPort());
//	    		// 为了简单起见，所有的异常都直接往外抛
//	    	
//	            //向服务器端第一次发送字符串
//	            OutputStream netOut = socket.getOutputStream();
//	            DataOutputStream doc = new DataOutputStream(netOut);
//	            DataInputStream in = new DataInputStream(socket.getInputStream());
//	            //向服务器端第二次发送字符串
//	            doc.writeUTF(val);
//	            String res = in.readUTF();
//	            System.out.println(res);
//	           // doc.writeUTF("bye");
////	            res = in.readUTF();
////	            System.out.println(res);
//	            doc.close();
//	            in.close();
//	        } catch (UnknownHostException e) {
//	            e.printStackTrace();
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        } {
//	            if (socket != null) {
//	                try {
//	                    socket.close();
//	                } catch (IOException e) {
//	                }
//	            }
//	        }
////		
//		try {
//			// 1.建立客户端socket连接，指定服务器位置及端口
//			Socket socket = new Socket(host, port);
//			
//			//开启两个线程，一个负责读，一个负责写  
//            new Thread(new ReadHandlerThread(socket)).start();  
//            new Thread(new WriteHandlerThread(socket)).start();  
//			
//			LoginReq login = new LoginReq();
//    		byte[] val = login.getHexString(socket.getLocalPort());
//			
//			// 2.得到socket读写流
//			OutputStream os = socket.getOutputStream();
//			PrintWriter pw = new PrintWriter(os);
//			// 输入流
//			InputStream is = socket.getInputStream();
//			BufferedReader br = new BufferedReader(new InputStreamReader(is));
//			// 3.利用流按照一定的操作，对socket进行读写操作
//			os.write(val);
//			os.flush();
//			//socket.shutdownOutput();
//			// 接收服务器的相应
//			String reply = null;
//			while (!((reply = br.readLine()) == null)) {
//				System.out.println("接收服务器的信息：" + reply);
//			}
//			// 4.关闭资源
//			br.close();
//			is.close();
//			pw.close();
//			os.close();
//			//socket.close();
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch(Exception e){
//			e.printStackTrace();
//		}
		
//		LoginReq login = new LoginReq();
//		sendTCPRequest(host, ""+port, "", "UTF-8", login.getHexString(8899));

	}
}
