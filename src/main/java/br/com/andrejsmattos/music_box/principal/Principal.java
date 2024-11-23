package br.com.andrejsmattos.music_box.principal;

import br.com.andrejsmattos.music_box.entities.*;
import br.com.andrejsmattos.music_box.exceptions.ConversaoJsonException;
import br.com.andrejsmattos.music_box.repositories.ArtistaRepository;
import br.com.andrejsmattos.music_box.repositories.MusicaRepository;
import br.com.andrejsmattos.music_box.services.ConsumoApi;
import br.com.andrejsmattos.music_box.services.ConverteDados;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class Principal {

    private final Scanner sc = new Scanner(System.in);
    private final ConsumoApi consumo = new ConsumoApi();
    private final ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://ws.audioscrobbler.com/2.0/?";
    private final String INFO_ARTISTA = "method=artist.getinfo&artist=";
    private final String BUSCA_ALBUM = "?method=album.search&album=";
    private final String INFO_MUSICA = "method=track.getInfo";
    private final String API_KEY = System.getenv("LAST_FM_API_KEY");
    private final String RESPONSE_FORMAT = "&format=json";

    private List<Artista> artistas = new ArrayList<>();
    private String nomeArtista;

    private List<Musica> musicas = new ArrayList<>();


    @Autowired
    private ArtistaRepository repositorioArtista;
    @Autowired
    private MusicaRepository repositorioMusica;

    public Principal(ArtistaRepository repositorioArtista, MusicaRepository repositorioMusica) throws JsonProcessingException {
        this.repositorioArtista = repositorioArtista;
        this.repositorioMusica = repositorioMusica;
    }

    public void exibeMenu() {
        var opcao = -1;

        while(opcao != 0) {
            var menu = """
                    
                    1 - Cadastrar artistas
                    2 - Cadastrar músicas
                    3 - Listar músicas
                    4 - Listar músicas por artista
                    5 - Pesquisar biografia um artista
                    
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
                    listarMusicasPorArtista();
                    break;
                case 5:
                    pesquisarBiografiaArtista();
                    break;
                case 0:
                    System.out.println("Saindo...\n");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void cadastrarArtistas() {
        var cadastrarNovo = "S";

        while(cadastrarNovo.equalsIgnoreCase("S")) {
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

            System.out.println("\nDeseja cadastrar outro artista (S/N)?");
            cadastrarNovo = sc.nextLine();
        }
    }

    private DadosArtista getDadosArtista() {
        System.out.println("\nDigite o nome do artista para cadastro: ");
        var nome = sc.nextLine();

        var json = consumo.obterDados(ENDERECO + INFO_ARTISTA + nome.replace(" ", "+") + API_KEY + RESPONSE_FORMAT);
        return conversor.obterDados(json, DadosArtista.class);
    }

    private void listarArtistasCadastrados(){
        artistas = repositorioArtista.findAll();
        artistas.stream()
                .sorted(Comparator.comparing(Artista::getTotalReproducoes).reversed())
                .forEach(System.out::println);
    }

    private void cadastrarMusicas() {
        listarArtistasCadastrados();
        System.out.println("\nVocê deseja cadastrar música de que artista?");
        nomeArtista = sc.nextLine();

        Optional<Artista> artistaBusca = repositorioArtista.findByNomeContainingIgnoreCase(nomeArtista);

        if (artistaBusca.isPresent()) {
            var cadastrar = "S";

            while(cadastrar.equalsIgnoreCase("S")) {
                try {
                    DadosMusica dados = getDadosMusica();

                    Musica musica = new Musica();
                    musica.setArtista(artistaBusca.get());
                    musica.setTitulo(dados.titulo());
                    musica.setUrl(dados.url());
                    musica.setDuracao(dados.duracao());
                    musica.setOuvintes(dados.ouvintes());
                    artistaBusca.get().getMusicas().add(musica);
                    if (!dados.topTags().tags().isEmpty()) {
                        musica.setGeneroMusical(dados.topTags().tags().get(0).generoMusical());
                    } else {
                        musica.setGeneroMusical(null);
                    }

                    if (dados.totalReproducoes() < 100) {
                        System.out.println("\nMúsica não existe ou não foi encontrada.");
                    } else {
                        musica.setTotalReproducoes(dados.totalReproducoes());
                        repositorioMusica.save(musica);
                        System.out.println("\n" + musica);
                    }

                } catch (ConversaoJsonException e) {
                    System.err.println(e.getMessage());
                }

                System.out.println("\nDeseja cadastrar outra música deste artista (S/N)?");
                cadastrar = sc.nextLine();
            }
        } else {
            System.out.println("\nCadastre o artista autor desta música antes de cadastrar esta música.");
        }
    }

    private DadosMusica getDadosMusica() {
        System.out.println("\nDigite o nome da música para cadastro: ");
        var tituloMusica = sc.nextLine();

        var json = consumo.obterDados(
                ENDERECO +
                        INFO_MUSICA +
                        API_KEY  +
                        "&artist=" +
                        nomeArtista.replace(" ", "+") +
                        "&track=" +
                        tituloMusica.replace(" ", "+") +
                        RESPONSE_FORMAT);

        DadosMusica dados = conversor.obterDados(json, DadosMusica.class);

        System.out.println("\nJSON: " + json);
        System.out.println("\nDados música: " + dados);

        return dados;
    }

    private void listarMusicas() {
        musicas = repositorioMusica.findAll();
        musicas.stream()
                .sorted(Comparator.comparing(Musica::getTotalReproducoes).reversed())
                .forEach(System.out::println);
    }

    private void listarMusicasPorArtista() {
        System.out.println("\nDe qual artista você deseja listar as músicas? ");
        var nomeArtista = sc.nextLine();
        musicas = repositorioMusica.findByArtista_NomeContainingIgnoreCase(nomeArtista);
        musicas.stream()
                .sorted(Comparator.comparing(Musica::getTotalReproducoes).reversed())
                .forEach(System.out::println);
    }

    private void pesquisarBiografiaArtista() {

    }
}
