package es.eoi.java2022.recuerdamelon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"es.eoi.java2022.recuerdamelon.data"})
public class RecuerdaMelonApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecuerdaMelonApplication.class, args);
	}

}
//@EntityScan("es.eoi.java2022.recuerdamelon.data.entity")
//@EnableJpaRepositories("es.eoi.java2022.recuerdamelon.data.repository")