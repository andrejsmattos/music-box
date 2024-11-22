package br.com.andrejsmattos.music_box.repositories;

import br.com.andrejsmattos.music_box.entities.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository extends JpaRepository <Artista, Long> {

}
