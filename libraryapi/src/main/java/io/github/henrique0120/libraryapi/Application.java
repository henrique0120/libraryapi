package io.github.henrique0120.libraryapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//para @EntityListeners, @CreatedDate e @LastModifiedDate
@EnableJpaAuditing
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
