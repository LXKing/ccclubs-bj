package com.ccclubs.util.struct;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.List;

import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.WebHelper;

public class Struct {

	public static boolean	reverse	= false;	// 是否字节逆序

	public byte[] getBytes() throws Exception {
		return Struct.getObjectBytes(this);
	}

	public static int sizeof(Class cls) {
		if (cls.getSuperclass() != Struct.class)
			throw new StructException("Struct.sizeof(Class)只接收计算Struct的子类");
		Object inc = null;
		try {
			inc = cls.newInstance();
		} catch (Exception e) {
			throw new StructException(e);
		}
		return sizeof(inc);
	}

	public static int sizeof(Object object) {
		return sizeofObject(object);
	}

	// 从byte数组中获取java对象
	public static <T> T readObject(byte[] bytes, Class cls) throws Exception {		
		return readObject(new DataInputStream(new ByteArrayInputStream(bytes)), cls);
	}

	// 从流中获取java对象
	public static <T> T readObject(DataInputStream dis, Class cls) throws Exception {
		Object object = cls.newInstance();
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			size size = field.getAnnotation(size.class);
			sizeby sizeby = field.getAnnotation(sizeby.class);
			int length = 0;
			if(size!=null)
				length = size.value();
			else if(sizeby!=null){
				String quoteFieldName = sizeby.value();
				Field quoteField = cls.getDeclaredField(quoteFieldName);
				quoteField.setAccessible(true);
				length = $.parseInt(quoteField.get(object));
			}
			
			Class<?> type = field.getType();
			String typeName = field.getType().getSimpleName();
			String fieldName = field.getName();
			if (typeName.equals("byte[]")){
				byte[] bts = new byte[length];
				dis.read(bts);
				field.set(object,bts);
			}else if (typeName.equals("short[]")){
				short[] shorts = new short[length];
				for(int i=0;i<shorts.length;i++)
					shorts[i] = dis.readShort();
				field.set(object,shorts);
			}else if(type.isArray()){
				Object[] temp = (Object[])Array.newInstance(type.getComponentType(), length);  
				for(int i=0;i<temp.length;i++){
					temp[i] = readObject(dis,type.getComponentType());
				}
				field.set(object,temp);
			}
			if(type.getSuperclass()==Struct.class){
				Object struct = readObject(dis,type);
				field.set(object,struct);
			}
			if (typeName.equals("int") || typeName.equals("Integer"))
				field.setInt(object, reverse ? Integer.reverseBytes(dis.readInt()) : dis.readInt());
			if (typeName.equals("long") || typeName.equals("Long"))
				field.setLong(object, reverse ? Long.reverseBytes(dis.readLong()) : dis.readLong());
			if (typeName.equals("double") || typeName.equals("Double"))
				field.setDouble(object, reverse ? dis.readDouble() : dis.readDouble());
			if (typeName.equals("short") || typeName.equals("Short"))
				field.setShort(object, reverse ? Short.reverseBytes(dis.readShort()) : dis.readShort());
			if (typeName.equals("byte") || typeName.equals("Byte"))
				field.setByte(object, dis.readByte());
			if (typeName.equals("String")) {
				String str = (String) field.get(object);
				if (size == null && sizeby==null)
					throw new StructException("对不起， Struct的子类的String类型属性必须添加自定义注释@size或@index描述其大小,@size(2)或@index({4,5})");
				if (length < 0)
					throw new StructException("对不起， 理论上由客户端发来的Struct的String属性是不可伸缩的,所以类[" + cls.getName() + "]的属性[" + field.getName() + "]@size(" + size + ")这样定义是有问题的");
				byte strBytes[] = new byte[length];
				dis.read(strBytes, 0, length);

				// byte bytes[]=new byte[size];
				// for(int i=0;i<strBytes.length/2;i++){
				// if(strBytes[i*2]==0 && strBytes[i*2+1]==0){
				// size=i*2;
				// break;
				// }
				// bytes[i*2]=strBytes[i*2+1];
				// bytes[i*2+1]=strBytes[i*2];
				// }

				String strStr = new String(strBytes, 0, length);
				field.set(object, strStr);
			}
		}

		return (T) object;
	}

	// 转java对象到数组
	public static byte[] getObjectBytes(Object object) throws Exception {
		ByteArrayOutputStream baOs = new ByteArrayOutputStream();
		write(new DataOutputStream(baOs), object);
		return baOs.toByteArray();
	}
	
	public static byte[] getObjectBytesExt(Object object) throws Exception {
		ByteArrayOutputStream baOs = new ByteArrayOutputStream();
		writeExt(new DataOutputStream(baOs), object);
		return baOs.toByteArray();
	}

	/**
	 * 写数据到输出流
	 * 
	 * @param dos
	 * @param writeObject
	 * @throws Exception
	 */
	public static void write(DataOutputStream dos, Object writeObject) throws Exception {
		Class cls = writeObject.getClass();
		if (writeObject instanceof java.util.List) {
			for (Object object : (List) writeObject)
				write(dos, object);
		} else if (cls.isArray()) {
			for (Object object : (Object[]) writeObject)
				write(dos, object);
		} else if (writeObject.getClass().getSuperclass() == Struct.class) {
			Field[] fields = writeObject.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				String typeName = field.getType().getSimpleName();
				if (typeName.equals("int") || typeName.equals("Integer"))
					dos.writeInt(reverse ? Integer.reverseBytes(field.getInt(writeObject)) : field.getInt(writeObject));
				else if (typeName.equals("long") || typeName.equals("Long"))
					dos.writeLong(reverse ? Long.reverseBytes(field.getLong(writeObject)) : field.getLong(writeObject));
				else if (typeName.equals("short") || typeName.equals("Short"))
					dos.writeShort(reverse ? Short.reverseBytes(field.getShort(writeObject)) : field.getShort(writeObject));
				else if (typeName.equals("byte") || typeName.equals("Byte"))
					dos.writeByte(field.getByte(writeObject));
				else if (typeName.equals("String")) {
					String str = (String) field.get(writeObject);
					if (str == null)
						str = "";
					str = str.replaceAll("　", " ");
					byte[] strBytes = str.getBytes();
					size annotation = field.getAnnotation(size.class);
					if (annotation == null)
						throw new StructException("对不起， Struct的子类的String类型属性必须添加自定义注释@size描述其大小,@size(-1)表示可伸缩大小");
					int size = annotation.value();
					str = str.substring(0, size);
					strBytes = str.getBytes();
					// System.err.println(writeObject.getClass().getName()+"类的["+field.getName()+"]字段内容["+str+"]长度不能超过其声明的(bytelen)长度"+size);
					size = strBytes.length;
					dos.write(strBytes);
				} else if (typeName.equals("byte[]")) {
					byte[] bytes = (byte[]) field.get(writeObject);
					// new java.io.FileOutputStream(new
					// java.io.File("f://aaa.gif")).write(bytes); //暂时注掉
					//System.out.println("图像大小 - " + bytes.length);
					dos.write(bytes);
				} else {
					write(dos, field.get(writeObject));
				}
			}
		} else
			throw new StructException("不支持的类型[" + writeObject.getClass().getName() + "]， struct框架返回单元类只能是Struct的子类");
	}
	
	/**
	 * 写数据到输出流
	 * 
	 * @param dos
	 * @param writeObject
	 * @throws Exception
	 */
	public static void writeExt(DataOutputStream dos, Object writeObject) throws Exception {
		Class cls = writeObject.getClass();
		if (writeObject instanceof java.util.List) {
			for (Object object : (List) writeObject)
				writeExt(dos, object);
		} else if (cls.isArray()) {
			for (Object object : (Object[]) writeObject)
				writeExt(dos, object);
		} else if (writeObject.getClass().getSuperclass() == Struct.class) {
			Field[] fields = writeObject.getClass().getDeclaredFields();
			Field[] fields_sort = new Field[fields.length+10];
			
			for (int i =0; i<fields.length; i++) {
				Field field = fields[i];
				field.setAccessible(true);
				
				Ignore ignore = field.getAnnotation(Ignore.class);
				if(ignore !=null && ignore.value()){
					continue;
				}
				
				OrderNumber orderNum = field.getAnnotation(OrderNumber.class);
				if(orderNum == null)
					throw new Exception("属性OrderNumber不可为空！");
				int number = orderNum.value();
				fields_sort[number] = field;
			}
			
			if(fields.length!=fields_sort.length){
				System.out.println("filter.....");
			}
			
			for(Field field : fields_sort){
				if(field == null) continue;
					field.setAccessible(true);
					String typeName = field.getType().getSimpleName();
					if (typeName.equals("int") || typeName.equals("Integer"))
						dos.writeInt(reverse ? Integer.reverseBytes(field.getInt(writeObject)) : field.getInt(writeObject));
					else if (typeName.equals("long") || typeName.equals("Long"))
						dos.writeLong(reverse ? Long.reverseBytes(field.getLong(writeObject)) : field.getLong(writeObject));
					else if (typeName.equals("short") || typeName.equals("Short"))
						dos.writeShort(reverse ? Short.reverseBytes(field.getShort(writeObject)) : field.getShort(writeObject));
					else if (typeName.equals("byte") || typeName.equals("Byte"))
						dos.writeByte(field.getByte(writeObject));
					else if (typeName.equals("String")) {
						String str = (String) field.get(writeObject);
						if (str == null)
							str = "";
						str = str.replaceAll("　", " ");
						byte[] strBytes = str.getBytes();
						size annotation = field.getAnnotation(size.class);
						if (annotation == null)
							throw new StructException("对不起， Struct的子类的String类型属性必须添加自定义注释@size描述其大小,@size(-1)表示可伸缩大小");
						int size = annotation.value();
						
						if(str.length()>=size){
							str = str.substring(0, size);
							strBytes = str.getBytes();
						}else{
							Pad pad = field.getAnnotation(Pad.class);
							if(pad!=null){
								if(pad.type() == PadType.LEFT){
									strBytes = lpadBytes(size, str.getBytes());  
								}else if(pad.type() == PadType.RIRHT){
									strBytes = Struct.rpadBytes(size, str.getBytes(), (byte)0x00);
								}
							}else{
								strBytes = lpadBytes(size, str.getBytes());  
							}
						}
						
						size = strBytes.length;
						dos.write(strBytes);
					} else if (typeName.equals("byte[]")) {
						byte[] bytes = (byte[]) field.get(writeObject);
						dos.write(bytes);
					} else {
						writeExt(dos, field.get(writeObject));
					}
					printHex(field, writeObject);
			}
		} else
			throw new StructException("不支持的类型[" + writeObject.getClass().getName() + "]， struct框架返回单元类只能是Struct的子类");
	}
	
	private static void printHex(Field field, Object writeObject){
		try{
			ByteArrayOutputStream baOs = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(baOs);
			field.setAccessible(true);
			String typeName = field.getType().getSimpleName();
			if (typeName.equals("int") || typeName.equals("Integer"))
				dos.writeInt(reverse ? Integer.reverseBytes(field.getInt(writeObject)) : field.getInt(writeObject));
			else if (typeName.equals("long") || typeName.equals("Long"))
				dos.writeLong(reverse ? Long.reverseBytes(field.getLong(writeObject)) : field.getLong(writeObject));
			else if (typeName.equals("short") || typeName.equals("Short"))
				dos.writeShort(reverse ? Short.reverseBytes(field.getShort(writeObject)) : field.getShort(writeObject));
			else if (typeName.equals("byte") || typeName.equals("Byte"))
				dos.writeByte(field.getByte(writeObject));
			else if (typeName.equals("String")) {
				String str = (String) field.get(writeObject);
				if (str == null)
					str = "";
				str = str.replaceAll("　", " ");
				byte[] strBytes = str.getBytes();
				size annotation = field.getAnnotation(size.class);
				if (annotation == null)
					throw new StructException("对不起， Struct的子类的String类型属性必须添加自定义注释@size描述其大小,@size(-1)表示可伸缩大小");
				int size = annotation.value();
				
				if(str.length()>=size){
					str = str.substring(0, size);
					strBytes = str.getBytes();
				}else{
					Pad pad = field.getAnnotation(Pad.class);
					if(pad!=null){
						if(pad.type() == PadType.LEFT){
							strBytes = lpadBytes(size, str.getBytes());  
						}else if(pad.type() == PadType.RIRHT){
							strBytes = Struct.rpadBytes(size, str.getBytes(), (byte)0x00);
						}
					}else{
						strBytes = lpadBytes(size, str.getBytes());  
					}
				}
				
				size = strBytes.length;
				dos.write(strBytes);
			} else if (typeName.equals("byte[]")) {
				byte[] bytes = (byte[]) field.get(writeObject);
				dos.write(bytes);
			}
			System.out.println(field.getName()+":\t\t"+bytesToHexString(baOs.toByteArray()));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * ################################计算对象大小################################
	 * 
	 * @param object
	 * @return
	 */
	public static int sizeofObject(Object sizeObject) {
		if (sizeObject == null)
			return 0;
		Class cls = sizeObject.getClass();
		if (sizeObject instanceof java.util.List) {
			int len = 0;
			for (Object object : ((List) sizeObject)) {
				len += sizeofObject(object);
			}
			return len;
		} else if (cls.isArray()) {
			int len = 0;
			for (Object object : ((Object[]) sizeObject)) {
				len += sizeofObject(object);
			}
			return len;
		} else if (sizeObject.getClass().getSuperclass() == Struct.class) {
			int len = 0;

			Field[] fields = sizeObject.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				String typeName = field.getType().getSimpleName();
				// System.out.println(typeName);
				if (typeName.equals("int") || typeName.equals("Integer"))
					len += 4;
				else if (typeName.equals("long") || typeName.equals("Long"))
					len += 8;
				else if (typeName.equals("short") || typeName.equals("Short"))
					len += 2;
				else if (typeName.equals("byte") || typeName.equals("Byte"))
					len += 1;
				else if (typeName.equals("String")) {
					size annotation = field.getAnnotation(size.class);
					if (annotation == null)
						throw new StructException("对不起， Struct的子类的String类型属性必须添加自定义注释@size描述其大小,@size(-1)表示可伸缩大小");
					int size = annotation.value();
					if (size > -1)
						len += size;
					else {
						field.setAccessible(true);
						String sValue = null;
						try {
							sValue = (String) field.get(sizeObject);
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (sValue != null) {
							len += sValue.length();
						}
					}
				} else if (typeName.equals("byte[]")) {
					byte[] bytes = {};
					try {
						bytes = (byte[]) field.get(sizeObject);
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					len += bytes.length;
				} else {
					Object value = null;
					try {
						value = field.get(sizeObject);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (value == null)
						throw new StructException("对不起， " + field.getName() + "不能为空.");
					len += sizeofObject(value);
				}
			}
			return len;
		} else
			throw new StructException("不支持的类型[" + sizeObject.getClass().getName() + "]， struct框架返回单元类只能是Struct的子类");
	}

	/** ################################计算对象大小结束################################* */

	static ThreadLocal	local	= new ThreadLocal();

	private static void comein() {
		Integer index = (Integer) local.get();
		local.set(index == null ? 0 : index + 1);
	}

	private static void getout() {
		Integer index = (Integer) local.get();
		local.set(index == null ? 0 : index - 1);
	}

	private static void println(Object... values) {
		Integer index = (Integer) local.get();
		index = index == null ? 0 : index;
		for (int i = 0; i < index; i++)
			System.out.print("\t");
		for (Object value : values) {
			System.out.print(value);
			System.out.print("\t");
		}
		System.out.print("\n");
	}

	/**
	 * 补空对齐
	 * 
	 * @param value
	 * @param max
	 * @return
	 */
	public static String align(String value, String what) {
		if (value == null)
			value = "";
		Integer max = 15;
		String strValue = value.toString();
		if (strValue.length() >= max)
			return strValue;
		String strZero = "";
		for (int i = 0; i < max - strValue.length(); i++)
			strZero += what;
		return strValue + strZero;
	}

	/**
	 * 打印结构体内存结构
	 * 
	 * @param traceObject
	 */
	public static int trace(Object traceObject) {
		if (traceObject == null)
			throw new StructException("对不起， Struct内容不应该存在空值NullPointExcepiton");
		comein();
		int len = 0;
		Class cls = traceObject.getClass();
		if (traceObject instanceof java.util.List) {
			int i = 0;
			for (Object object : ((List) traceObject)) {
				println("数组项[" + i++ + "]");
				len += trace(object);
			}
		} else if (cls.isArray()) {
			int i = 0;
			for (Object object : ((Object[]) traceObject)) {
				println("数组项[" + i++ + "]");
				len += trace(object);
			}
		} else if (traceObject.getClass().getSuperclass() == Struct.class) {

			Field[] fields = traceObject.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);

				String typeName = field.getType().getSimpleName();
				int size = 0;
				String strBytes = "";

				Object value = null;
				try {
					value = field.get(traceObject);
				} catch (Exception ex) {
				}

				if (typeName.equals("int") || typeName.equals("Integer")) {
					size = 4;
					strBytes = $.zerofill(Integer.toHexString((Integer) value), size * 2);
				} else if (typeName.equals("long") || typeName.equals("Long")) {
					size = 8;
					strBytes = $.zerofill(Long.toHexString((Long) value), size * 2);
				} else if (typeName.equals("short") || typeName.equals("Short")) {
					size = 2;
					strBytes = $.zerofill(Integer.toHexString((Short) value), size * 2);
				} else if (typeName.equals("byte") || typeName.equals("Byte")) {
					size = 1;
					strBytes = $.zerofill(Integer.toHexString((Byte) value), size * 2);
				} else if (typeName.equals("String")) {
					size annotation = field.getAnnotation(size.class);
					if (annotation == null)
						throw new StructException("对不起， Struct的子类的String类型属性必须添加自定义注释@size描述其大小,@size(-1)表示可伸缩大小");
					size = annotation.value();
					len += size;
					String sValue = (String) value;
					if (sValue != null) {
						byte[] sBytes = sValue.getBytes();
						byte bytes[] = sBytes;
						for (Byte b : bytes) {
							String string = WebHelper.zerofill(Integer.toHexString(b & 0xFF), 2);
							strBytes += string;
						}
					}
				} else if (typeName.equals("byte[]")) {
					byte[] bytes = {};
					try {
						bytes = (byte[]) field.get(traceObject);
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					for (Byte b : bytes) {
						String string = WebHelper.zerofill(Integer.toHexString(b & 0xFF), 2);
						strBytes += string;
					}
				} else {
					if (value == null)
						throw new StructException("对不起， " + field.getName() + "不能为空.");
					size = sizeof(value);
				}
				len += size;

				println(field.getType().getSimpleName(), align(field.getName(), " "), size, value, strBytes);
			}

		} else
			throw new StructException("不支持的类型[" + traceObject.getClass().getName() + "]， struct框架返回单元类只能是Struct的子类");

		getout();

		return len;
	}

	public void trace() throws Exception {
		byte[] bytes = this.getBytes();
		for (Byte b : bytes) {
			System.out.print(WebHelper.zerofill(Integer.toHexString(b & 0xFF), 2));
		}
		System.out.println();
	}

	public static void trace(byte[] bytes) throws Exception {
		// byte[] bytes = this.getBytes();
		for (Byte b : bytes) {
			System.out.print(WebHelper.zerofill(Integer.toHexString(b & 0xFF), 2));
		}
		System.out.println();
	}
	
	 /** Convert byte[] to hex string.这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。  
	  *  @param src byte[] data  
	  *  @return hex string  
	  */     
	public static String bytesToHexString(byte[] src){  
	    StringBuilder stringBuilder = new StringBuilder("");  
	    if (src == null || src.length <= 0) {  
	        return null;  
	    }  
	    for (int i = 0; i < src.length; i++) {  
	        int v = src[i] & 0xFF;  
	        String hv = Integer.toHexString(v);  
	        if (hv.length() < 2) {  
	            stringBuilder.append(0);  
	        }  
	        stringBuilder.append(hv);  
	    }  
	    return stringBuilder.toString();  
	} 
	
	public static byte[] getBytesFromString(String packetData){
		int length = packetData.length() / 2;
        byte[] bytes = new byte[length];
        byte[] source = packetData.getBytes();
        for (int i = 0; i < bytes.length; ++i) {
            byte bh = Byte.decode("0x" + new String(new byte[]{source[i * 2]})).byteValue();
            bh = (byte)(bh << 4);
            byte bl = Byte.decode("0x" + new String(new byte[]{source[i * 2 + 1]})).byteValue();
            bytes[i] = (byte)(bh ^ bl);
        }
		return bytes;
	}
	
	/**
	 * BCC校验算法
	 * @param data
	 * @return
	 */
	public static String getBCC(byte[] data) {
		  String ret = "";
		  byte BCC[]= new byte[1];
		  for(int i=0;i<data.length;i++){
			  BCC[0]=(byte) (BCC[0] ^ data[i]);
		  }
		  String hex = Integer.toHexString(BCC[0] & 0xFF);
		  if (hex.length() == 1) {
			 hex = '0' + hex;
		  }
		  ret += hex.toUpperCase();
		  return ret;
	}
	
//	/**
//	 * long类型转换成dword类型
//	 * @param value
//	 * @return
//	 */
//	public static byte[] long2Dword(long value) {
//	    byte[] data = new byte[4];
//	    for (int i = 0; i < data.length; i++) {
//	        data[i] = (byte) (value >> (8 * i));
//	    }
//	    return data;
//	}
	
	/**
	 * @see lpadBytes
	 * @param size
	 * @param source
	 * @return
	 */
	public static byte[] lpadBytes(int size, byte[] source){
		return lpadBytes(size, source, (byte)0x00);
	}
	
	/**
	 * byte补全
	 * @param size  
	 * @param source  
	 * @return
	 */
	public static byte[] rpadBytes(int size, byte[] source, byte value){
		if(size == source.length)return source;
		byte[] data = new byte[size];
		for(int i=0;i<(source.length>size?size:source.length);i++){
			data[i] = source[i];
		}
		for(int i = source.length; i<size; i++){
			data[i] = value;
		}
		return data;
	}
	
	/**
	 * byte补全
	 * @param size  
	 * @param source  
	 * @return
	 */
	public static byte[] lpadBytes(int size, byte[] source, byte value){
		if(size == source.length)return source;
		byte[] data = new byte[size];
		int useSize = data.length;
		if(source !=null){
			useSize = size - source.length;
			if(useSize<=0)useSize = 0;
			for(int i=useSize, sourceIndex=0; i<data.length; i++, sourceIndex++){
				data[i] = source[sourceIndex];
			}
		}
		for(int i = 0; i<useSize; i++){
			data[i] = value;
		}
		return data;
	}
}
