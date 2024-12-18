package br.com.andrejsmattos.music_box.entities;

import br.com.andrejsmattos.music_box.utils.DeletaLinksHtml;
import br.com.andrejsmattos.music_box.utils.traducao.ConsultaMyMemory;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static br.com.andrejsmattos.music_box.utils.FormatadorNumero.formatarNumero;

@Entity
@Table(name = "artistas")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String url;
    private Long ouvintes;
    private Long totalReproducoes;
    @Column(length = 5000)
    private String resumo;

    @Enumerated(EnumType.STRING)
    private TipoArtista tipoArtista;

//    @Transient
    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Musica> musicas = new ArrayList<>();

//    @Transient
//    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<Album> albuns = new ArrayList<>();

//    private String paisOrigem;
//    private LocalDate dataNascimento;

    public Artista() {
    }

    public Artista(String nome, String url, Long ouvintes, Long totalReproducoes, String resumo, TipoArtista tipoArtista, List<Musica> musicas, List<Album> albuns) {
        this.nome = nome;
        this.url = url;
        this.ouvintes = ouvintes;
        this.totalReproducoes = totalReproducoes;
        this.resumo = resumo;
        this.tipoArtista = tipoArtista;
        this.musicas = musicas;
//        this.albuns = albuns;
    }

    public Artista(String nome, String url, Long ouvintes, Long totalReproducoes, String resumo, TipoArtista tipoArtista) {
        this.nome = nome;
        this.url = url;
        this.ouvintes = ouvintes;
        this.totalReproducoes = totalReproducoes;
        this.resumo = resumo;
        this.tipoArtista = tipoArtista;
    }

    public Artista(String nome, String url, Long ouvintes, Long totalReproducoes, TipoArtista tipoArtista) {
        this.nome = nome;
        this.url = url;
        this.ouvintes = ouvintes;
        this.totalReproducoes = totalReproducoes;
        this.tipoArtista = tipoArtista;
    }

    public Artista(DadosArtista dadosArtista) {
        this.resumo = resumo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getOuvintes() {
        return ouvintes;
    }

    public void setOuvintes(Long ouvintes) {
        this.ouvintes = ouvintes;
    }

    public Long getTotalReproducoes() {
        return totalReproducoes;
    }

    public void setTotalReproducoes(Long totalReproducoes) {
        this.totalReproducoes = totalReproducoes;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public TipoArtista getTipoArtista() {
        return tipoArtista;
    }

    public void setTipoArtista(TipoArtista tipoArtista) {
        this.tipoArtista = tipoArtista;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

//    public List<Album> getAlbuns() {
//        return albuns;
//    }
//
//    public void setAlbuns(List<Album> albuns) {
//        this.albuns = albuns;
//    }

    @Override
    public String toString() {
        return "Artista: " + nome +
                ", tipoArtista: " + tipoArtista +
                ", url: " + url +
                ", ouvintes: " + formatarNumero(ouvintes) +
                ", totalReproducoes: " + formatarNumero(totalReproducoes);
    }

    public String getResumoTraduzido() {
        if (resumo == null || resumo.isEmpty()) {
            return "Resumo não disponível para tradução.";
        }
        try {
            // Limpar o texto removendo links HTML
            String textoLimpo = DeletaLinksHtml.removerLinksHtml(resumo);

           // System.out.println("Texto para tradução: " + textoLimpo);

            return ConsultaMyMemory.obterTraducao(textoLimpo);
        } catch (Exception e) {
            return "Erro ao traduzir o resumo.";
        }
    }



    public String toStringBiografia() {
    if (resumo == null || resumo.isEmpty()) {
        return "Biografia: Não disponível";
    }
    try {
        return "Biografia: " + getResumoTraduzido();
    } catch (Exception e) {
        return "Erro ao traduzir a biografia";
    }
}


}
