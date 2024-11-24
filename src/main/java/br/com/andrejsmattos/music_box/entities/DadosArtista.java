package br.com.andrejsmattos.music_box.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosArtista(

        @JsonAlias("name") String nome,
        @JsonAlias("url") String url,
//        @JsonAlias("image") List<Imagem> imagens,
        @JsonAlias("stats") Estatisticas estatisticas,
        @JsonAlias("similar") Similares similares,
//        @JsonAlias("tags") GeneroMusical generoMusical,
        @JsonAlias("bio") Biografia biografia
        ) {

//    @JsonIgnoreProperties(ignoreUnknown = true)
//    public record Imagem(
//            @JsonAlias("#text") String url,
//            @JsonAlias("size") String tamanho
//            ) {}
//
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Estatisticas(
            @JsonAlias("listeners") Long ouvintes,
            @JsonAlias("playcount") Long totalReproducoes
            ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Similares(
            @JsonAlias("artist") List<ArtistaSimilar> artistas
            ) {

        @JsonIgnoreProperties(ignoreUnknown = true)
        public record ArtistaSimilar(
                @JsonAlias("name") String nome
        ) {}
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Biografia(
            @JsonAlias("summary") String resumo
    ) {}
}
