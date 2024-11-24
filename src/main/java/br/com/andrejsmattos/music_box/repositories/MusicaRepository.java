package br.com.andrejsmattos.music_box.repositories;

import br.com.andrejsmattos.music_box.entities.Artista;
import br.com.andrejsmattos.music_box.entities.Musica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MusicaRepository extends JpaRepository<Musica, Long> {

    Optional<Musica> findByTituloContainingIgnoreCase(String tituloMusica);
    List<Musica> findByArtista_NomeContainingIgnoreCase(String nomeArtista);
}