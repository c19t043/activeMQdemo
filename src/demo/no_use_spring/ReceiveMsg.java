package demo.no_use_spring;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

public class ReceiveMsg {
	public static void main(String[] args) throws JMSException {
		ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection conn = null;
		Session session = null;
		try {
			conn = cf.createConnection();
			conn.start();
			session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = new ActiveMQQueue("spitter.queue");
			//Destination destination = session.createQueue("spitter.queue");
			MessageConsumer consumer = session.createConsumer(destination);
			int i = 0;
			do{
				Message message = consumer.receive();
				TextMessage textMessage = (TextMessage) message;
				System.out.println("第"+i+"次接受消息"+textMessage.getText());
				i++;
			}while(true);
		} catch (JMSException e) {
			e.printStackTrace();
		}finally{
			try{
				if(session!=null){
					session.close();
				}
				if(conn != null){
					conn.close();
				}
			}catch(JMSException ex){
				throw ex;
			}
		}
	}
}
