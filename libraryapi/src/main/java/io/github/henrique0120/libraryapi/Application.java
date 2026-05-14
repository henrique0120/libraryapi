package io.github.henrique0120.libraryapi;

import io.github.henrique0120.libraryapi.model.Autor;
import io.github.henrique0120.libraryapi.repository.AutorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class Application {



	public static void main(String[] args) {
		var context = SpringApplication.run(Application.class, args);
		AutorRepository repository = context.getBean(AutorRepository.class);

		exemploSalvarRegistro(repository);
	}

	public static void exemploSalvarRegistro(AutorRepository autorRepository){
		Autor autor = new Autor();
		autor.setNome("Henrique");
		autor.setNacionalidade("Brasileira");
		autor.setDataNascimento(LocalDate.of(2001, 11, 28));

		var autorSalvo = autorRepository.save(autor);
		System.out.println(autorSalvo);
	}

}
