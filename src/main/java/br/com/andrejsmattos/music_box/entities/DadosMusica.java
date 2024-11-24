package br.com.andrejsmattos.music_box.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosMusica(

        @JsonAlias("name") String titulo,
        @JsonAlias("url") String url,
        @JsonAlias("duration") Long duracao,
        @JsonAlias("listeners") Long ouvintes,
        @JsonAlias("playcount") Long totalReproducoes,
        @JsonAlias("toptags") TopTags topTags

) {
        @JsonIgnoreProperties(ignoreUnknown = true)
        public record TopTags (
                @JsonAlias("tag") List<Tag> tags
        ) {}

        @JsonIgnoreProperties(ignoreUnknown = true)
        public record Tag (
                @JsonAlias("name") String generoMusical
        ) {}
}
