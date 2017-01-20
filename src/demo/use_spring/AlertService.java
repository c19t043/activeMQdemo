package demo.use_spring;

public interface AlertService {
	void sendSpittleAlert(Spittle spittle);
	void sendDefaultSpittleAlert(Spittle spittle);
	void sendAndConvertDefaultSpittleAlert(Spittle spittle);
	Spittle receiveAndConvertSpittleAlert();
	Spittle receiveSpittleAlert();
}	
