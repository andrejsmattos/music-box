package br.com.andrejsmattos.music_box.utils.traducao;

import br.com.andrejsmattos.music_box.services.ConsumoApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ConsultaMyMemory {

    private static final int LIMITE_CARACTERES = 450; // Margem de segurança do limite de 500
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final ConsumoApi consumo = new ConsumoApi();
    private static final String MY_MEMORY_API_KEY = System.getenv("MY_MEMORY_API_KEY");


    public static String obterTraducao(String text) {
        if (text == null || text.trim().isEmpty()) {
            return "";
        }

        // Se o texto for menor que o limite, usa o método original
        if (text.length() <= LIMITE_CARACTERES) {
            return traduzirSegmento(text);
        }

        // Divide o texto em segmentos e traduz cada um
        List<String> segmentos = dividirEmSegmentos(text);
        List<String> traducoesSegmentos = new ArrayList<>();

        for (String segmento : segmentos) {
            String traducaoSegmento = traduzirSegmento(segmento);
            traducoesSegmentos.add(traducaoSegmento);

            // Pausa entre requisições para evitar limitação
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Erro durante pausa entre traduções", e);
            }
        }

        // Junta todos os segmentos traduzidos
        return String.join(" ", traducoesSegmentos);
    }

    private static String traduzirSegmento(String texto) {
        try {
            String textoEncoded = URLEncoder.encode(texto, StandardCharsets.UTF_8);
            String langpair = URLEncoder.encode("en|pt-br", StandardCharsets.UTF_8);

            StringBuilder urlBuilder = new StringBuilder()
                    .append("https://api.mymemory.translated.net/get")
                    .append("?q=").append(textoEncoded)
                    .append("&langpair=").append(langpair)
                    .append("&key=").append(MY_MEMORY_API_KEY);

            String json = consumo.obterDados(urlBuilder.toString());

            DadosTraducao traducao = mapper.readValue(json, DadosTraducao.class);
            return traducao.dadosResposta().textoTraduzido();

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao processar resposta da API", e);
        }
    }

    private static List<String> dividirEmSegmentos(String texto) {
        List<String> segmentos = new ArrayList<>();
        StringBuilder segmentoAtual = new StringBuilder();

        String[] sentencas = texto.split("(?<=[.!?])\\s+");

        for (String sentenca : sentencas) {
            if (segmentoAtual.length() + sentenca.length() > LIMITE_CARACTERES) {
                if (!segmentoAtual.isEmpty()) {
                    segmentos.add(segmentoAtual.toString().trim());
                    segmentoAtual.setLength(0);
                }

                if (sentenca.length() > LIMITE_CARACTERES) {
                    String[] palavras = sentenca.split("\\s+");
                    StringBuilder parteAtual = new StringBuilder();

                    for (String palavra : palavras) {
                        if (parteAtual.length() + palavra.length() > LIMITE_CARACTERES) {
                            segmentos.add(parteAtual.toString().trim());
                            parteAtual.setLength(0);
                        }
                        parteAtual.append(palavra).append(" ");
                    }

                    if (!parteAtual.isEmpty()) {
                        segmentoAtual.append(parteAtual);
                    }
                } else {
                    segmentoAtual.append(sentenca).append(" ");
                }
            } else {
                segmentoAtual.append(sentenca).append(" ");
            }
        }

        if (!segmentoAtual.isEmpty()) {
            segmentos.add(segmentoAtual.toString().trim());
        }

        return segmentos;
    }
}

