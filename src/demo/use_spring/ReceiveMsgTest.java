package demo.use_spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ReceiveMsgTest {
	
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/demo/use_spring/config.xml");
		AlertService alertService = ac.getBean(AlertService.class);
		int i = 0;
		do{
			Spittle message  = alertService.receiveSpittleAlert();
			System.out.println("第"+i+"次接受消息"+message);
			i++;
		}while(true);
	}
}
