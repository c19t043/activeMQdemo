package demo.use_spring;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.JmsUtils;

public class AlertServiceImpl implements AlertService {

	private JmsOperations jmsOperations;
	@Override
	public void sendDefaultSpittleAlert(final Spittle spittle) {
		jmsOperations.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage(spittle);
			}
		});
	}
	@Override
	public void sendSpittleAlert(final Spittle spittle) {
		jmsOperations.send("spittle.alert.queue",new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage(spittle);
			}
		});
	}
	@Override
	public void sendAndConvertDefaultSpittleAlert(Spittle spittle) {
		jmsOperations.convertAndSend(spittle);
	}
	@Autowired
	public void setJmsOperations(JmsOperations jmsOperations) {
		this.jmsOperations = jmsOperations;
	}
	@Override
	public Spittle receiveSpittleAlert() {
		ObjectMessage objectMessage = (ObjectMessage) jmsOperations.receive();
		try {
			return (Spittle) objectMessage.getObject();
		} catch (JMSException e) {
			throw JmsUtils.convertJmsAccessException(e);
		}
	}
	@Override
	public Spittle receiveAndConvertSpittleAlert() {
		return (Spittle) jmsOperations.receiveAndConvert();
	}
}
