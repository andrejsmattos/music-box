package br.com.andrejsmattos.music_box.services;

import br.com.andrejsmattos.music_box.exceptions.ConversaoJsonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados {

    private final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // Ignora propriedades desconhecidas

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new ConversaoJsonException("Erro ao converter JSON para " + classe.getSimpleName(), e);
        }
    }
}
