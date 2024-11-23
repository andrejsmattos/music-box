package br.com.andrejsmattos.music_box.repositories;

import br.com.andrejsmattos.music_box.entities.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {

    Optional<Artista> findByNomeContainingIgnoreCase(String nomeArtista);
}