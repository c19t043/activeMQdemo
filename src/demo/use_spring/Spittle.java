package demo.use_spring;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

@SuppressWarnings("serial")
public class Spittle implements MessageConverter{
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Spittle [msg=" + msg + "]";
	}

	@Override
	public Object fromMessage(Message message) throws JMSException,
			MessageConversionException {
		msg = (String) message.getObjectProperty("mes");
		return this;
	}

	@Override
	public Message toMessage(Object arg0, Session message) throws JMSException,
			MessageConversionException {
		return null;
	}
}
