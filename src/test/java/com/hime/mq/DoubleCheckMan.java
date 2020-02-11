package com.hime.mq;

//双重检测锁式，第一次上锁后面直接获取，因此可延迟加载，并发效率高
public class DoubleCheckMan {
	
	private static volatile DoubleCheckMan doubleCheckMan;
	
	private DoubleCheckMan(){}
	
	public static DoubleCheckMan getInstance(){
		if(doubleCheckMan==null){
			synchronized (DoubleCheckMan.class) {
				if(doubleCheckMan==null){
					doubleCheckMan = new DoubleCheckMan();
				}
			}
		}
		return doubleCheckMan;
	}
	
}
