package pl.mkwiecien.legacyerp.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(value = {"pl.mkwiecien.legacyerp"})
@EnableJpaRepositories(value = {"pl.mkwiecien.legacyerp.domain"})
@EntityScan(value = {"pl.mkwiecien.legacyerp.domain"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
