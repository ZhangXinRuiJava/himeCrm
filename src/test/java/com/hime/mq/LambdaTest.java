package com.hime.mq;

public class LambdaTest {

    public interface TestInterface{
        public void test1();
        public void test2();
    }

    public static void doSomething(TestInterface test){
        test.test1();
    }
    
    public static void main(String[] args) {
    	/*doSomething(new TestInterface() {
			@Override
			public void test1() {
				System.out.println("HelloWorld");
			}
		});*/
    	
        //doSomething(()-> System.out.println("HelloWorld"));
    }
}
