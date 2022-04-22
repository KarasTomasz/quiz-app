package pl.tkaras;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.tkaras.api.documents.AppUser;
import pl.tkaras.respositories.AppUserRepository;

@SpringBootApplication
public class QuizAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizAppApplication.class, args);
	}


	@Bean
	CommandLineRunner runner(AppUserRepository repository){
		return args -> {
			AppUser appUser = AppUser.builder()
					.firstName("Jan")
					.lastName("Kowalski")
					.email("j.kowalski@o2.pl")
					.build();

			if(!repository.findByEmail(appUser.getEmail()).isPresent()){
				repository.insert(appUser);
				System.out.println("added");
			}
			else {
				System.out.println("already exist");
			}

		};

	}




}
