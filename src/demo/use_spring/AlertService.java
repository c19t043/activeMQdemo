package demo.use_spring;

public interface AlertService {
	void sendSpittleAlert(String message);
	void sendDefaultSpittleAlert(String message);
	void sendAndConvertDefaultSpittleAlert(String message);
	String receiveAndConvertSpittleAlert();
	String receiveSpittleAlert();
}	
