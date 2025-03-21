package med.voll.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ApiApplication {
	private static ApplicationContext applicationContext;


	public static void main(String[] args) {
		applicationContext = SpringApplication.run(ApiApplication.class, args);
		
    }

}
