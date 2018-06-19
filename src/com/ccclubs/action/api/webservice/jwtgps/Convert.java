package com.ccclubs.action.api.webservice.jwtgps;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.ccclubs.util.struct.Struct;

public class Convert {
	
	public static byte getColor(Short color){
		if(color!=null){
			if(color.intValue() == 0){			//黑色
				return 3;
			}else if(color.intValue() == 1){	//白色
				return 4;
			}else if(color.intValue() == 4){	//黄色
				return 2;
			}else if(color.intValue() == 7){	//蓝色
				return 1;
			}
		}
		return 9;
	}
	
	
	public static byte[] replace(byte[] btArr){
		String hexStr = Struct.bytesToHexString(btArr).toLowerCase();
		hexStr = hexStr.substring(2, hexStr.length());
		hexStr = hexStr.substring(0,hexStr.length()-2);
		
		hexStr = hexStr.replace("5b", "5a01");
		hexStr = hexStr.replace("5a", "5a02");
		hexStr = hexStr.replace("5d", "5e01");
		hexStr = hexStr.replace("5e", "5e02");
		
		return Struct.getBytesFromString("5b"+hexStr+"5d");
	}
	
	public static byte[] replace2(byte[] btArr){
		byte _5a = 0x5a;
		byte _5b = 0x5b;
		byte _5d = 0x5d;
		byte _5e = 0x5e;
		
		ByteArrayOutputStream baOs = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baOs);
		for(int i=0; i<btArr.length; i++){
			try {
				byte bt = btArr[i];
				if(i == 0 || i == btArr.length-1){
					dos.writeByte(bt);
				}else{
					if(bt == _5b){
						dos.writeByte(0x5a);
						dos.writeByte(0x01);
					}else if(bt == _5a){
						dos.writeByte(0x5a);
						dos.writeByte(0x02);
					}else if(bt == _5d){
						dos.writeByte(0x5e);
						dos.writeByte(0x01);
					}else if(bt == _5e){
						dos.writeByte(0x5e);
						dos.writeByte(0x02);
					}else{
						dos.writeByte(bt);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		return baOs.toByteArray();
	}
	
	public static void main(String[] args) {
		System.out.println(Struct.getBytesFromString("5a"));
		System.out.println(Struct.getBytesFromString("5b"));
		System.out.println(Struct.getBytesFromString("5d"));
		System.out.println(Struct.getBytesFromString("5e"));
	}
}
