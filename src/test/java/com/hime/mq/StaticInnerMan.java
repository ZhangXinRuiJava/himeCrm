package com.hime.mq;

//静态内部类式，可延迟加载，并发效率高
public class StaticInnerMan {
	
	private StaticInnerMan(){}
	
	//静态内部类特性：第一次引用时才加载，所以具有唯一性和延迟加载性
	private static class Inner{
		private static StaticInnerMan staticInnerMan = new StaticInnerMan();
	}
	
	public static StaticInnerMan getInstance(){
		return Inner.staticInnerMan;
	}
	
}
