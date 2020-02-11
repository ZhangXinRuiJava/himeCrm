package com.hime.mq;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

public class Consumer {
	
	@Test
	public void testQueueConsumer() throws JMSException, IOException{
		//1.创建一个ConnectionFactory对象。
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.233.129:61616");
		//2.从ConnectionFactory对象中获得一个Connection对象。
		Connection connection = connectionFactory.createConnection();
		//3.开启连接。调用Connection对象的start方法。
		connection.start();
		//4.使用Connection对象创建一个Session对象。
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//5.使用Session对象创建一个Destination对象。和发送端保持一致queue，并且队列的名称一致。
		Queue queue = session.createQueue("test-zxr-queue");
		//6.使用Session对象创建一个Consumer对象。
		MessageConsumer consumer = session.createConsumer(queue);
		//7.接收消息。
			//7.1同步消费
		/*Message message = consumer.receive();
		TextMessage textMessage = (TextMessage)message;
		System.out.println("textMessage:"+textMessage.getText());*/
			//7.2异步消费
		consumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				//8.打印消息。
				//System.out.println("message:"+message);//不能用，否则打印不出任何消息，也不会有消息出队
				TextMessage textMessage = (TextMessage)message;
				try {
					System.out.println("textMessage:"+textMessage.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		//等待键盘输入，这样生产者往队列中写消息时，消费者可以立即获取消费
		System.in.read();

		//9.关闭资源
		consumer.close();
		session.close();
		connection.close();
	}
	
	//可以多启动几次，模拟多个消费者
	@Test
	public void testTopicConsumer() throws JMSException, IOException{
		//1.创建一个ConnectionFactory对象。
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.233.129:61616");
		//2.从ConnectionFactory对象中获得一个Connection对象。
		Connection connection = connectionFactory.createConnection();
		//3.开启连接。调用Connection对象的start方法。
		connection.start();
		//4.使用Connection对象创建一个Session对象。
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//5.使用Session对象创建一个Destination对象。和发送端保持一致topic，并且主题的名称一致。
		Topic topic = session.createTopic("test-zxr-topic");
		//6.使用Session对象创建一个Consumer对象。
		MessageConsumer consumer = session.createConsumer(topic);
		//7.接收消息。
		/*Message message = consumer.receive();
		TextMessage textMessage = (TextMessage)message;
		System.out.println("textMessage:"+textMessage.getText());*/
		consumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				//8.打印消息。
				TextMessage textMessage = (TextMessage)message;
				try {
					System.out.println("textMessage:"+textMessage.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		System.out.println("消费者2启动了");
		//等待键盘输入，这样生产者往主题中写消息时，消费者可以立即获取消费
		System.in.read();

		//9.关闭资源
		consumer.close();
		session.close();
		connection.close();
	}
	
}
