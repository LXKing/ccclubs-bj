package com.ccclubs.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import com.lazy3q.web.helper.$;

//
/**
 * 上传工具类 支持上传文件和参数，[目前仅支持单张图片上传]
 */
public class UploadUtil {
	private static UploadUtil	uploadUtil;
	private static final String	BOUNDARY		= UUID.randomUUID().toString(); // 边界标识
	// 随机生成
	private static final String	PREFIX			= "--";
	private static final String	LINE_END		= "\r\n";
	private static final String	CONTENT_TYPE	= "multipart/form-data";		// 内容类型

	private UploadUtil() {

	}

	/**
	 * 单例模式获取上传工具类
	 * 
	 * @return
	 */
	public static UploadUtil getInstance() {
		if (null == uploadUtil) {
			uploadUtil = new UploadUtil();
		}
		return uploadUtil;
	}

	private int					readTimeOut		= 10 * 1000;	// 读取超时
	private int					connectTimeout	= 10 * 1000;	// 超时时间
	private static int			requestTime		= 0;			// 请求使用多长时间
	private static final String	CHARSET			= "utf-8";		// 设置编码

	/**
	 * android上传文件到服务器
	 * 
	 * @param filePath
	 *            需要上传的文件的路径
	 * @param fileKey
	 *            在网页上<input type=file name=xxx/> xxx就是这里的fileKey
	 * @param RequestURL
	 *            请求的URL
	 */
	public String uploadFile(String filePath, String fileKey, String fileName, String RequestURL, Map<String, Object> param) {
		if (filePath == null) {
			return null;
		}
		try {
			File file = new File(filePath);
			return uploadFile(file, fileKey, fileName, RequestURL, param);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 上传文件到服务器
	 * 
	 * @param file
	 *            需要上传的文件
	 * @param fileKey
	 *            在网页上<input type=file name=xxx/> xxx就是这里的fileKey
	 * @param RequestURL
	 *            请求的URL
	 */
	public String uploadFile(final File file, final String fileKey, final String fileName, final String RequestURL, final Map<String, Object> param) {
		if (file == null) {
			return null;
		}
		return toUploadFile(file, fileKey, fileName, RequestURL, param);
	}

	private String toUploadFile(File file, String fileKey, String fileName, String RequestURL, Map<String, Object> param) {
		String result = null;
		requestTime = 0;

		long requestTime = System.currentTimeMillis();
		long responseTime = 0;

		try {
			URL url = new URL(RequestURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(readTimeOut);
			conn.setConnectTimeout(connectTimeout);
			conn.setDoInput(true); // 允许输入流
			conn.setDoOutput(true); // 允许输出流
			conn.setUseCaches(false); // 不允许使用缓存
			conn.setRequestMethod("POST"); // 请求方式
			conn.setRequestProperty("Charset", CHARSET); // 设置编码
			conn.setRequestProperty("connection", "keep-alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);

			// 当文件不为空，把文件包装并且上传
			DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
			StringBuffer sb = null;
			String params = "";

			/*******************************************************************
			 * 以下是用于上传参数
			 */
			if (param != null && param.size() > 0) {
				Iterator<String> it = param.keySet().iterator();
				while (it.hasNext()) {
					sb = null;
					sb = new StringBuffer();
					String key = it.next();
					String value = param.get(key).toString();
					sb.append(PREFIX).append(BOUNDARY).append(LINE_END);
					sb.append("Content-Disposition: form-data; name=\"").append(key).append("\"").append(LINE_END).append(LINE_END);
					sb.append(value).append(LINE_END);
					params = sb.toString();
					dos.write(params.getBytes());
				}
			}

			sb = null;
			params = null;
			sb = new StringBuffer();
			/**
			 * 这里重点注意： name里面的值为服务器端需要key 只有这个key 才可以得到对应的文件
			 * filename是文件的名字，包含后缀名的 比如:abc.png
			 */
			sb.append(PREFIX).append(BOUNDARY).append(LINE_END);
			sb.append("Content-Disposition:form-data; name=\"" + fileKey + "\"; filename=\"" + fileName + "\"" + LINE_END);
			sb.append("Content-Type:image/*" + LINE_END); // 这里配置的Content-type很重要的用于服务器端辨别文件的类型的
			sb.append(LINE_END);
			params = sb.toString();
			sb = null;
			dos.write(params.getBytes());

			/** 上传文件 */
			InputStream is = new FileInputStream(file);
			byte[] bytes = new byte[1024];
			int len = 0;
			int curLen = 0;
			while ((len = is.read(bytes)) != -1) {
				curLen += len;
				dos.write(bytes, 0, len);
			}
			is.close();

			dos.write(LINE_END.getBytes());
			byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes();
			dos.write(end_data);
			dos.flush();

			// 获取响应码 200=成功 当响应成功，获取响应的流
			int res = conn.getResponseCode();
			responseTime = System.currentTimeMillis();
			this.requestTime = (int) ((responseTime - requestTime) / 1000);
			if (res == 200) {
				StringBuffer sb1 = new StringBuffer();
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb1.append(line);
				}
				reader.close();
				result = sb1.toString();
				$.trace("result : " + result);
				return result;
			} else {
				return result;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			return result;
		}
	}

	public int getReadTimeOut() {
		return readTimeOut;
	}

	public void setReadTimeOut(int readTimeOut) {
		this.readTimeOut = readTimeOut;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	// 获取上传使用的时间
	public static int getRequestTime() {
		return requestTime;
	}
}
