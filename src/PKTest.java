import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.lazy3q.web.helper.*;


public class PKTest {
	
	public static void main(String[] args) {
		//教学班
		int[] teach_item = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
			                11, 12, 13, 14, 15, 16, 17, 18,
			                19, 20, 21, 22, 23, 24, 25, 26,
			                27, 28, 29, 30, 31, 32, 33, 34, 35, 36};
		
		//每个教学班平均课程数
		int class_kc_count = 6;
		
		//可用排课时间点
		String[] time_arr = {"周一10:00", "周一13:00", "周一15:00", "周一8:00",
							 "周二10:00", "周二13:00", "周二15:00", "周二8:00",
							 "周三10:00", "周三13:00", "周三15:00", "周三8:00",
							 "周四10:00", "周四13:00", "周四15:00", "周四8:00",
							 "周五10:00", "周五13:00", "周五15:00", "周五8:00",
							};
		
		//教学班课程数初始化
		Map<Integer, List<String>> jxb_kcs = new HashMap<Integer, List<String>>();
		
		//所有教学班所有课程数矩阵
		List<String> all_kc = new ArrayList<String>();
		for(int i =0; i<teach_item.length ; i++){
			List<String> kc = new ArrayList<String>();
			for(int j=0; j<class_kc_count; j++){
				kc.add(teach_item[i]+"_"+j);
				all_kc.add(teach_item[i]+"_"+j);
			}
			jxb_kcs.put(teach_item[i], kc);
		}
		
		System.out.println(all_kc.size());
		//教学班分配时间
		for(int i =0;i<time_arr.length;i++){
			get(all_kc, jxb_kcs, (teach_item.length * class_kc_count / time_arr.length)+1, time_arr[i]);
		}
		
	}
	
	public static void get(List<String> all_kc, Map<Integer, List<String>> jxb_kcs, int avg, String time){
		int count = 0;
		
		//已经排了的班级
		List<Integer> yp = new ArrayList<Integer>();
		
		List<Integer> numlist = new ArrayList<Integer>();
		while(count < avg && all_kc.size() >= avg){
			Random rdm = new Random();
			int num = rdm.nextInt(all_kc.size());
			if(!numlist.contains(num)){
				numlist.add(num);
				count ++;
			}
		}
		
		Collections.reverse(numlist);
		for(int i=0;i<numlist.size();i++){
			System.out.println("教学"+numlist.get(i)+"班->"+time+"=="+numlist.get(i)+"=="+all_kc.size());
		}
		
		int mx = all_kc.size();
		for(int i=mx-1;i>mx-numlist.size();i--){
//			System.out.println(all_kc.size()+"=="+i);
			all_kc.remove(i);
		}
		
//		while(count<avg){
//			Random rdm = new Random();
//			int num = rdm.nextInt(30);
//			System.out.println(num);
//			List<String> kc = jxb_kcs.get(num);
////			System.out.println(count);
//			if(kc!= null && !yp.contains(num)){
//				if(kc.isEmpty()){
//					System.out.println(jxb_kcs);
//					jxb_kcs.remove(num);
//				}					
//				
//				yp.add(num);
//				count ++;
//				System.out.println(count);
//				System.out.println("教学"+num+"班->"+time);
//				jxb_kcs.remove(jxb_kcs.size()-1);
//			}
//			System.out.println("============"+jxb_kcs.size());
//		}
	}
	
}
