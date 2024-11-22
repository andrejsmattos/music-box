package br.com.andrejsmattos.music_box.principal;

import br.com.andrejsmattos.music_box.entities.Artista;
import br.com.andrejsmattos.music_box.entities.DadosArtista;
import br.com.andrejsmattos.music_box.entities.TipoArtista;
import br.com.andrejsmattos.music_box.repositories.ArtistaRepository;
import br.com.andrejsmattos.music_box.services.ConsumoApi;
import br.com.andrejsmattos.music_box.services.ConverteDados;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    private final Scanner sc = new Scanner(System.in);
    private final ConsumoApi consumo = new ConsumoApi();
    private final ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://ws.audioscrobbler.com/2.0/?";
    private final String BUSCA_ARTISTA = "method=artist.getinfo&artist=";
    private final String BUSCA_ALBUM = "?method=album.search&album=";
    private final String BUSCA_MUSICA = "method=track.search&track=";
    private final String API_KEY = System.getenv("LAST_FM_API_KEY");

    @Autowired
    private ArtistaRepository repositorioArtista;

    public Principal(ArtistaRepository repositorioArtista) throws JsonProcessingException {
        this.repositorioArtista = repositorioArtista;
    }

    public void exibeMenu() {
        var opcao = -1;

        while(opcao != 0) {
            var menu = """
                    
                    1 - Cadastrar artistas
                    2 - Cadastrar músicas
                    3 - Listar músicas
                    4 - Buscar músicas por artista
                    5 - Pesquisar dados sobre um artista
                    
                    0 - Sair
                    """;

            System.out.println(menu);
            try {
                opcao = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Insira um número válido.");
                sc.nextLine();
                continue;
            }

            switch (opcao) {
                case 1:
                    cadastrarArtistas();
                    break;
                case 2:
                    cadastrarMusicas();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    buscarMusicasPorArtista();
                    break;
                case 5:
                    pesquisarSobreArtista();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void cadastrarArtistas() {
        DadosArtista dados = getDadosArtista();

        System.out.println("\nInforme o tipo desse artista (solo, dupla ou banda): ");
        var tipo = sc.nextLine();
        TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());

        Artista artista = new Artista();
        artista.setNome(dados.nome());
        artista.setTipoArtista(tipoArtista);
        artista.setUrl(dados.url());
        artista.setOuvintes(dados.estatisticas().ouvintes());
        artista.setTotalReproducoes(dados.estatisticas().totalReproducoes());
        artista.setResumo(dados.biografia().resumo());

        repositorioArtista.save(artista);
        System.out.println("\n" + artista);
    }

    private DadosArtista getDadosArtista() {
        System.out.println("\nDigite o nome do artista para cadastro: ");
        var nome = sc.nextLine();

        var json = consumo.obterDados(ENDERECO + BUSCA_ARTISTA + nome.replace(" ", "+") + API_KEY);
        DadosArtista dados = conversor.obterDados(json, DadosArtista.class);
//        System.out.println("Retorno json:" + json);
//        System.out.println("DadosArtista: " + dados);  // Verifique o objeto de saída

        return dados;
    }

    private void cadastrarMusicas() {

    }

    private void listarMusicas() {

    }

    private void buscarMusicasPorArtista() {

    }

    private void pesquisarSobreArtista() {

    }
}
