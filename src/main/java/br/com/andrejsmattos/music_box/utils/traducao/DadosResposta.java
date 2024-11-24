package br.com.andrejsmattos.music_box.utils.traducao;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosResposta(
        @JsonAlias("translatedText") String textoTraduzido) {
}
