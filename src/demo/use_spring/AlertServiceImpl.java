package demo.use_spring;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.JmsUtils;

public class AlertServiceImpl implements AlertService {

	private JmsOperations jmsOperations;
	@Override
	public void sendDefaultSpittleAlert(final String message) {
		jmsOperations.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage(message);
			}
		});
	}
	@Override
	public void sendSpittleAlert(final String message) {
		jmsOperations.send("spittle.alert.queue",new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage(message);
			}
		});
	}
	@Override
	public void sendAndConvertDefaultSpittleAlert(String message) {
		jmsOperations.convertAndSend(message);
	}
	@Autowired
	public void setJmsOperations(JmsOperations jmsOperations) {
		this.jmsOperations = jmsOperations;
	}
	@Override
	public String receiveSpittleAlert() {
		Message receive = jmsOperations.receive();
		try {
			return ((TextMessage)receive).getText();
		} catch (JMSException e) {
			throw JmsUtils.convertJmsAccessException(e);
		}
	}
	@Override
	public String receiveAndConvertSpittleAlert() {
		return (String) jmsOperations.receiveAndConvert();
	}
}
