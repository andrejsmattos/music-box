package br.com.andrejsmattos.music_box.entities;

import br.com.andrejsmattos.music_box.utils.FormatadorDuracao;
import jakarta.persistence.*;

import java.text.NumberFormat;
import java.time.Duration;
import java.util.Locale;

import static br.com.andrejsmattos.music_box.utils.FormatadorNumero.formatarNumero;

@Entity
@Table(name = "musicas")
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String url;
    private String duracao;
    private Long ouvintes;
    private Long totalReproducoes;
    @Enumerated(EnumType.STRING)
    private GeneroMusical generoMusical;

    @ManyToOne
    @JoinColumn(name = "artista_id")
    private Artista artista;

//TODO
//    @OneToOne
//    @JoinColumn(name = "album_id")
//    private Album album;


    public Musica() {
    }

    public Musica(DadosMusica dadosMusica) {
        this.titulo = dadosMusica.titulo();
        this.url = dadosMusica.url();
        this.setDuracao(dadosMusica.duracao());
        this.ouvintes = dadosMusica.ouvintes();
        this.totalReproducoes = dadosMusica.totalReproducoes();
        if (!dadosMusica.topTags().tags().isEmpty()) {
            this.generoMusical = GeneroMusical.fromString(dadosMusica.topTags().tags().get(0).generoMusical());
        } else {
            this.generoMusical = null;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(Long duracao) {
        this.duracao = FormatadorDuracao.formatarDuracao(duracao);
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

    public GeneroMusical getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = GeneroMusical.fromString(generoMusical);
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

//TODO
//    public Album getAlbum() {
//        return album;
//    }
//TODO
//    public void setAlbum(Album album) {
//        this.album = album;
//    }

    @Override
    public String toString() {
        return "Musica: " + titulo +
                ", musica_id: " + id +
                ", url: " + url +
                ", duracao: " + duracao +
                ", ouvintes: " + formatarNumero(ouvintes) +
                ", totalReproducoes: " + formatarNumero(totalReproducoes) +
                ", generoMusical: " + (generoMusical != null ? generoMusical.name() : "null") +
                ", artista: " + artista.getNome() +
                ", artista_id: " + artista.getId();
    }
}
