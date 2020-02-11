package com.hime.mq;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;

public class MyTest {
	
	public static void main(String[] args) throws IOException {
		int n = 67108866;//2^26=67108864
		boolean judgePower = test02(n);
		System.out.println(judgePower?"是2的幂次方":"不是");
		boolean powerOfTwo = isPowerOfTwo(n);
		System.out.println(powerOfTwo?"是2的幂次方":"不是");
		boolean powerOfTwo02 = isPowerOfTwo02(n);
		System.out.println(powerOfTwo02?"是2的幂次方":"不是");
		
		//testRecursion(2);
		//pringAllFile("D:\\Java开发相关\\笔记");//D:\\Java开发相关\\笔记  E:\\个人文件\\党员
		//printFiByIndex(5);
		
	}
	
	//1.理解递归
	public static void testRecursion(int depth){
		System.out.println("抱着");
		if(depth<=0){
			System.out.println("我的小鲤鱼");
		}else{
			testRecursion(--depth);
		}
		System.out.println("的我");
	}
	
	//2.递归练习：斐波那契数列，获取指定位置的数列值,0 1 1 2 3 5 8 13 21...
	public static int printFiByIndex(int index){
		if(index==0){
			System.out.println(0);
			return 0;
		}
		if(index==1){
			System.out.println(1);
			return 1;
		}
		int res = printFiByIndex(index-1)+printFiByIndex(index-2);
		System.out.println(res);
		return res;
	}
	
	//3.递归练习：打印指定目录下的所有目录和文件
	public static void pringAllFile(String path) throws IOException{
		File file = new File(path);
		boolean isDirectory = file.isDirectory();
		if(isDirectory){
			System.out.println("目录："+file.getCanonicalPath()+"--");
			String[] strings = file.list();
			for(String temp:strings){
				pringAllFile(path+"\\"+temp);
			}
		}else{
			System.out.println("\t文件："+file.getCanonicalPath());
		}
	}
	
	//4.二叉树遍历等。。。
	
	public static void afdas(int n){
		int res = n & n-1;
		System.out.println(res);
	}
	
	//错误写法！！！6呢？？？
	public static boolean judgePower(int n){
		if(n<1){
			return false;
		}else if(n==1){
			return true;
		}else if(n%2==0){
			return true;
		}
		return false;
	}
	
	//效率太低，对于非2的幂次方的数来说
	public static boolean test01(int n){
		int i=0;
		while(i<n){//2^i=n 得 i=log2n 而log2n<n 即i<n
			if(Math.pow(2, i)==n){
				return true;
			}
			i++;
		}
		return false;
	}
	
	//整除2得余数，如果余数不为0直接退出,否则余数为0，用商除2的结果再整除2。。。直到商为1可证明
	public static boolean test02(int n){
		if(n<1){
			return false;
		}else if(n==1){
			return true;
		}else{
			if(n%2!=0){
				return false;
			}else{
				//！！注意！这里必须返回!!比如当n为8时直到1返回true，这时递归返回到之前的test02(2)，此时test02(2)==true,这个true是test02(1)时的结果
				return test02(n/2);
			}
		}
	}
	
	/**
	 * 分析，1:1，2:10，4:100，8:1000，16:10000		3:11，5:101，6:110
	 * 结论：2的幂次方的二进制数都是0位为1，其余各位为0
	 * @param n
	 * @return
	 */
	public static boolean isPowerOfTwo(int n) {
        String str = Integer.toBinaryString(n);
        if(n < 1)
            return false;
        else if(str.lastIndexOf("1") == 0)
            return true;
        else
            return false;
	}
	
	/**
	 * 分析	1:1，2:10，4:100，8:1000，16:10000
	 * 		0:0，1:01，3:011，7:0111，15:01111
	 * 结论：如果n是2的幂次方，那么n和n-1与运算后结果为0
	 * @param n
	 * @return
	 */
	public static boolean isPowerOfTwo02(int n) {
		if(n < 1)
			return false;
		else
			return (n & n-1)==0;
	}
	
	@Test
	public void testMy(){
		Long[] ids = {13l,15l,16l,19l,78l};
		System.out.println(Arrays.toString(ids));
		List<Long> list = Arrays.asList(ids);
		System.out.println(list);
		
		String jsonString = JSON.toJSONString(ids);
		System.out.println(jsonString+","+jsonString.contains("["));
		
		List<Long> list2 = JSON.parseArray(jsonString, Long.class);
		System.out.println("list2:"+list2);
		
	}
	
	@Test
	public void testMap1(){
		Map<String, Object> map = new HashMap<>();
		map.put("res", false);
		System.out.println(map.get("res"));
		if(!(boolean) map.get("res")){
			System.out.println("falfafda");
		}
	}
	
}
