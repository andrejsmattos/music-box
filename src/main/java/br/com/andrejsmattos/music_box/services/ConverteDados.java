package br.com.andrejsmattos.music_box.services;

import br.com.andrejsmattos.music_box.entities.DadosArtista;
import br.com.andrejsmattos.music_box.entities.DadosMusica;
import br.com.andrejsmattos.music_box.exceptions.ConversaoJsonException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados {

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json);

            JsonNode targetNode;
            if (classe.equals(DadosArtista.class)) {
                targetNode = rootNode.path("artist");
            } else if (classe.equals(DadosMusica.class)) {
                targetNode = rootNode.path("track");
//            } else if (classe.equals(DadosAlbum.class)) {
//                targetNode = rootNode.path("album");
            } else {
                throw new IllegalArgumentException("Unsupported class type: " + classe.getSimpleName());
            }

            return objectMapper.treeToValue(targetNode, classe);
        } catch (Exception e) {
            throw new ConversaoJsonException("Erro ao converter JSON para " + classe.getSimpleName(), e);
        }
    }
}