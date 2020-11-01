package eu.crystalsystem.ugalmap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import eu.crystalsystem.ugalmap.utils.UserUtil;

@SpringBootApplication
public class RestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

	
	@Bean
	public UserUtil userUtil() {
	    return new UserUtil();
	}

}
