package es.eoi.java2022.recuerdamelon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class RecuerdaMelonApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecuerdaMelonApplication.class, args);
	}

}