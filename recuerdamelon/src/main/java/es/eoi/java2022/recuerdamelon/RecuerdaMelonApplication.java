package es.eoi.java2022.recuerdamelon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableAutoConfiguration
public class RecuerdaMelonApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecuerdaMelonApplication.class, args);
	}

	@PostConstruct
	public void init(){
		System.out.println("Hola mundo desde RecuerdaMelonApplication");
	}

}
//@EntityScan("es.eoi.java2022.recuerdamelon.data.entity")
//@EnableJpaRepositories("es.eoi.java2022.recuerdamelon.data.repository")