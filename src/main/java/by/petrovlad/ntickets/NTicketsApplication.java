package by.petrovlad.ntickets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class NTicketsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NTicketsApplication.class, args);
	}

}
