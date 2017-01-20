package demo.use_spring;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class SendMsgTest {
	public static void main(String[] args) throws InterruptedException {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/demo/use_spring/config.xml");
		AlertService alertService = ac.getBean(AlertService.class);
		Spittle spittle = new Spittle();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int i = 0;
		do{
			String time = df.format(new Date());
			String content = "第"+i+"次发送消息"+time;
			spittle.setMsg(content);
			alertService.sendAndConvertDefaultSpittleAlert(spittle);
			System.out.println(content);
			Thread.sleep(1000);
			i++;
		}while(true);
	}
}
