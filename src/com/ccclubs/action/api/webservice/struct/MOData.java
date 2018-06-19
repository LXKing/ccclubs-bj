package com.ccclubs.action.api.webservice.struct;

import java.util.ArrayList;
import java.util.List;

import com.ccclubs.util.struct.Struct;
import com.ccclubs.util.struct.size;
import com.ccclubs.util.struct.sizeby;

public class MOData extends Struct {
	public @size(2) String 起始符 = "##";
	public byte 命令标识 = 0;
	public byte 应答标识 = 0;
	public @size(17) String 识别码vin = "";
	public byte 数据加密方式 = 0;
	public short 数据单元长度 = 0;	
	public @sizeby("数据单元长度") byte[] bytes;
	public byte 结束符;
	
	public List<Struct> datas = new ArrayList();
	
	public static class 单体蓄电池电压 extends Struct{
		public short 单体蓄电池总数;
		public byte 动力蓄电池总数;
		public @sizeby("动力蓄电池总数") 电压包[] 电压值列表;
		public static class 电压包 extends Struct{
			byte 序号;
			byte 电池数;
			@sizeby("电池数") short[] 电池电压值;
		}
	}
	public static class 极值数据 extends Struct{
		public @size(18) byte[] 垃圾一大堆;
		public byte SOC;
		public @size(9) byte[] 垃圾再一大堆;
	}
	public static class 动力蓄电池包温度数据  extends Struct{
		public short 单体蓄探针总数;
		public byte 动力蓄电池总数;
		public @sizeby("动力蓄电池总数") 电压包[] 电压值列表;
		public static class 电压包 extends Struct{
			byte 序号;
			byte 电池数;
			@sizeby("电池数") byte[] 电池电压值;
		}
	}
	public static class 整车数据 extends Struct{
		public short 车速;//单位：0.1公里
		public int 里程;//单位：0.1公里
		public byte 档位;
		public byte 加速踏板行程值;
		public byte 制动踏板行程值;
		public byte 充放电状态;
		public byte 电机控制器温度;
		public short 电机转速;
		public byte 电机温度;
		public short 电机电压;
		public short 电机电流;
		public byte 空调设定温度;
		public @size(7) byte[] 预留;
	}
	public static class 定位数据 extends Struct{
		public byte 定位状态;
		public int 经度;
		public int 纬度;
		public short 速度;
		public short 方向;
		public @size(4) byte[] 预留;
	}
	public static class 报警数据 extends Struct{
		public short 垃圾;
		public byte n;
		public @sizeby("n") byte[] 垃圾堆;
		public byte N;
		public @sizeby("N") byte[] 垃圾桶;
		public byte M;
		public @sizeby("M") byte[] 垃圾箱;
	}
	public static class 续航数据 extends Struct{
		public short 续航里程;
	}
	
}
