package com.hime.mq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.Test;

public class Producer {
	
	@Test
	public void testQueueProducer() throws JMSException{
		//1.创建连接工厂ConnectionFactory，需要指定brokerURL服务器的ip及端口号
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.233.129:61616");
		//2.创建连接Connection
		Connection connection = connectionFactory.createConnection();
		//3.开启连接connection.start()
		connection.start();
		//4.创建Session,参数一：是否开启事务，true：开启事务，则参数二忽略。参数二：消息的应答模式：自动应答、手动应答。
		//当第一个参数为false时，才有意义。一般是自动应答。
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//5.创建目标对象Destination(Queue,Topic)，此处创建Queue 参数为队列名称
		Queue queue = session.createQueue("test-zxr-queue");
		//6.创建生产者对象
		MessageProducer producer = session.createProducer(queue);
		//6.创建消息对象TextMessage
		/*TextMessage message = new ActiveMQTextMessage();
		message.setText("hello,这是Producer发出的信息01");*/
		TextMessage message = session.createTextMessage("hello,这是Producer发出的信息01");
		//7.producer发送信息
		producer.send(message);
		//8.关闭各种资源
		producer.close();
		session.close();
		connection.close();
	}
	
	@Test
	public void testTopicProducer() throws JMSException{
		//1.创建连接工厂ConnectionFactory，需要指定brokerURL服务器的ip及端口号
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.233.129:61616");
		//2.创建连接Connection
		Connection connection = connectionFactory.createConnection();
		//3.开启连接connection.start()
		connection.start();
		//4.创建Session
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//5.创建目标对象Destination(Queue,Topic)，此处创建Topic 参数为主题名称
		Topic topic = session.createTopic("test-zxr-topic");
		//6.创建生产者对象
		MessageProducer producer = session.createProducer(topic);
		//6.创建消息对象TextMessage
		TextMessage message = session.createTextMessage("hello,这是Producer群发的信息01");
		//7.producer发送信息
		producer.send(message);
		//8.关闭各种资源
		producer.close();
		session.close();
		connection.close();
	}
	
}
