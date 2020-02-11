package com.hime.mq;

//饿汉式，不能延迟加载，并发效率高
public class HungryMan {
	
	private static HungryMan hungryMan = new HungryMan();
	
	private HungryMan(){
		if(hungryMan!=null){
			throw new RuntimeException("请用getInstance()方法创建");
		}
	}
	
	public static HungryMan getInstance(){
		return hungryMan;
	}
	
}
