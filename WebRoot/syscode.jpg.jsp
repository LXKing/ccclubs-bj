<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*,java.util.*,java.io.*,javax.naming.*,java.awt.image.*,javax.imageio.*,java.awt.*;" errorPage=""%>
<%!Color getRandColor(int fc, int bc) {//给定范围获得随机颜色
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int rgb = fc + random.nextInt(bc - fc);
		return new Color(rgb, rgb, rgb);
	}%>
<%
	response.setContentType("image/jpeg");
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);

	//		在内存中创建图象		
	int width = 100, height = 30;
	BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

	//		获取图形上下文
	Graphics g = image.getGraphics();

	//		生成随机类
	Random random = new Random();

	//		设定背景色
	g.setColor(getRandColor(250, 255));
	g.fillRect(0, 0, width, height);

	//		设定字体
	g.setFont(new Font("Consola", Font.BOLD, 25));

	//		随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
	g.setColor(getRandColor(225, 250));
	for (int i = 0; i < 155; i++) {
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		int xl = random.nextInt(12);
		int yl = random.nextInt(12);
		g.drawLine(x, y, x + xl, y + yl);
	}

	final String VALID_CHARS = "ABCDEFGHJKMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz123456789";
	//		取随机产生的认证码(4位数字)
	String sRand = "";
	int size = VALID_CHARS.length();
	for (int i = 0; i < 4; i++) {
		String rand = String.valueOf(VALID_CHARS.charAt(random.nextInt(size)));
		sRand += rand;
		//			将认证码显示到图象中
		g.setColor(getRandColor(50, 100));//调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
		//			rand：字符，x坐标，y坐标 ，从左上角（0，0）开始
		g.drawString(rand, 20 * i + 12, 22);
	}

	//		画边框
	//g.setColor(new Color(0,153,204));
	//g.drawRect(0,0,width-1,height-1);

	//		将认证码存入SESSION
	session.setAttribute("verify_code", sRand);
	//		图象生效
	g.dispose();
	ServletOutputStream responseOutputStream = response.getOutputStream();
	//		输出图象到页面
	ImageIO.write(image, "JPEG", responseOutputStream);

	//		以下关闭输入流！
	responseOutputStream.flush();
	responseOutputStream.close();
	out.clear();
	out = pageContext.pushBody();
%>
