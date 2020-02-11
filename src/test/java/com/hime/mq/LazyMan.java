package com.hime.mq;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

//懒汉式，可延迟加载，但是并发效率低
public class LazyMan {

	private static LazyMan lazyMan;
	
	private LazyMan(){
//		if(lazyMan!=null){
//			throw new RuntimeException("请用getInstance()方法创建");
//		}
	}
	
	public synchronized static LazyMan getInstance(){
		if(lazyMan==null){
			lazyMan = new LazyMan();
		}
		return lazyMan;
	}
	
}
