package com.hime.mq;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		List<Integer> numbers01 = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
		List<Integer> numbers02 = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
		//List<Integer> numbers03 = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
		numbers01.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);//降序，否则升序
			}
		});
		System.out.println("匿名内部类："+numbers01);
		
		numbers02.sort( (a1,a2)->a2.compareTo(a1) );
		System.out.println("lambda (a1,a2)->a1.compareTo(a2)："+numbers02);
		
		/*numbers03.sort( Integer::compareTo );
		System.out.println("lambda Integer::compareTo："+numbers03);*/
	}

}
