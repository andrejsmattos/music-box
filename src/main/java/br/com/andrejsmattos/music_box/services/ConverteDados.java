package br.com.andrejsmattos.music_box.services;

import br.com.andrejsmattos.music_box.exceptions.ConversaoJsonException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados {

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json);

            JsonNode artistNode = rootNode.path("artist");
            return objectMapper.treeToValue(artistNode, classe);
        } catch (Exception e) {
            throw new ConversaoJsonException("Erro ao converter JSON para " + classe.getSimpleName(), e);
        }
    }
}
