package org.firstvision.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("org.firstvision.restapi")
public class MultiUserSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiUserSystemApplication.class, args);
	}
}
