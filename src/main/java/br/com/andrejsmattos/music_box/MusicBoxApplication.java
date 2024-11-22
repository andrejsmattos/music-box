package br.com.andrejsmattos.music_box;

import br.com.andrejsmattos.music_box.principal.Principal;
import br.com.andrejsmattos.music_box.repositories.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicBoxApplication implements CommandLineRunner {

	@Autowired
	private ArtistaRepository repositorioArtista;

	public static void main(String[] args) {
		SpringApplication.run(MusicBoxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorioArtista);
		principal.exibeMenu();
	}
}
