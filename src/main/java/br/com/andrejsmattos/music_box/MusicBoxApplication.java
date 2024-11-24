package br.com.andrejsmattos.music_box;

import br.com.andrejsmattos.music_box.principal.Principal;
import br.com.andrejsmattos.music_box.repositories.ArtistaRepository;
import br.com.andrejsmattos.music_box.repositories.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicBoxApplication implements CommandLineRunner {

	@Autowired
	private ArtistaRepository repositorioArtista;

	@Autowired
	private MusicaRepository repositorioMusica;

	public static void main(String[] args) {
		SpringApplication.run(MusicBoxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorioArtista, repositorioMusica);
		principal.exibeMenu();
	}
}
