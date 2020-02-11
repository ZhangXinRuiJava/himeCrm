package com.hime.mq;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import org.junit.Test;
import org.springframework.util.DigestUtils;

public class Test2 {

	public static void main(String[] args) {
		//1、使用lambda表达式替代匿名类，比如Runnable
		/*new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Java8之前");
			}
		}).start();
		new Thread( ()->System.out.println("Java8之后") ).start();*/
		
		List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
		primes.stream().map( (n)->++n ).forEach( (n)->System.out.print(n+" ") );//都加1后的值
		System.out.println("\n");
		primes.forEach( (n)->System.out.print(n+" ") );//还是原值
		
		int a = 1;
		//primes.stream().map( n->a++ );
	}
	
	public static void filter(List<String> names, Predicate<String> condition) {
	    /*for(String name: names)  {
	        if(condition.test(name)) {
	            System.out.print(name + " ");
	        }
	    }
	    System.out.println("\n");*/
	    
	    names.stream().filter((name) -> (condition.test(name))).forEach((name) -> {
	        System.out.print(name + " ");
	    });
	    System.out.println("\n");
	    
	    /*names.stream().filter(condition).forEach( (name) -> System.out.print(name + " ") );
	    System.out.println("\n");*/
	}
	
	public static int getFactor(int num){
		List<Integer> list = new ArrayList<>();
		int sum = 0;
		for(int i=1;i<num;i++){
			if(num%i==0){
				list.add(i);
			}
		}
		Iterator<Integer> iterator = list.iterator();
		while(iterator.hasNext()){
			//System.out.println(iterator.next());
			sum += iterator.next();
		}
		return sum;
	}
	
	public static void getPerfectNum(){
		for(int i=1;i<1000;i++){
			int factorSum = getFactor(i);
			if(factorSum==i){
				System.out.print(i+",");
			}
		}
	}
	
	@Test
	public void testTry(){
		try {
			System.out.println("01");
			int a = 1/0;
			//System.out.println("02");
		} catch (Exception e) {
			e.printStackTrace();
			//int a = 1/0;
		} finally {
			System.out.println("finally");
		}
		System.out.println("03");
	}
	
	//1.spring框架自带的
	@Test
	public void testMd5(){
		String md5Pwd = DigestUtils.md5DigestAsHex("123".getBytes());
		System.out.println(md5Pwd+"\t"+md5("123"));
		
		boolean res = DigestUtils.md5DigestAsHex("123".getBytes()).equals(md5Pwd);
		System.out.println(res);
	}
	
	/**
	 * 使用md5的算法进行加密,不可逆的加密方式
	 */
	public static String md5(String plainText) {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(
					plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有md5这个算法！");
		}
		String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
		// 如果生成数字未满32位，需要前面补0
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}

}
