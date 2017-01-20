package demo.no_use_spring;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

public class SendMsg {
	public static void main(String[] args) throws JMSException, InterruptedException {
		ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection conn = null;
		Session session = null;
		try {
			conn = cf.createConnection();
			conn.start();
			session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = new ActiveMQQueue("spitter.queue");
			//Destination destination = session.createQueue("spitter.queue");
			MessageProducer producer = session.createProducer(destination);
			TextMessage message = session.createTextMessage();
			int i = 0;
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			do{
				String time = df.format(new Date());
				String content = "第"+i+"次发送消息"+time;
				message.setText(time);
				System.out.println(content);
				producer.send(message);
				Thread.sleep(1000);
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
