package com.hime.mq;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程未解之谜？为什么不是1000？
 * @author zxr 张新锐
 * 类描述：ThreadTest 
 * 创建时间：2019年3月8日 下午9:52:22   
 * 修改人：zxr   
 * 修改时间：2019年3月8日 下午9:52:22
 */
public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
    	final AtomicInteger atomicInteger = new AtomicInteger(0);
    	
        for (int i = 0; i < 1000; i++) {
            /*new Thread(new Runnable() {
                @Override
                public void run() {
                	atomicInteger.getAndIncrement();
                	System.out.println(atomicInteger);//看其他线程运行完没有
                }
            }).start();*/
        	new Thread( ()->{atomicInteger.getAndIncrement();System.out.println(atomicInteger);} ).start();
        }
        Thread.sleep(1);//停顿一下就行
        System.out.println("结果"+atomicInteger.toString());//因为此时其他线程可能还没运行完
    	
    }

}
