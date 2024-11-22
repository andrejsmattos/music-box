package br.com.andrejsmattos.music_box.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artistas")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private TipoArtista tipoArtista;

//    @Transient
    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Musica> musicas = new ArrayList<>();

//    @Transient
    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Album> albuns = new ArrayList<>();

//    private String paisOrigem;
//    private LocalDate dataNascimento;

    public Artista() {
    }

    public Artista(Long id, String nome, TipoArtista tipoArtista, List<Musica> musicas, List<Album> albuns) {
        this.id = id;
        this.nome = nome;
        this.tipoArtista = tipoArtista;
        this.musicas = musicas;
        this.albuns = albuns;
    }

    public Artista(String nomeArtista, TipoArtista tipoArtista) {
        this.nome = nome;
        this.tipoArtista = tipoArtista;
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

    public TipoArtista getCategoria() {
        return tipoArtista;
    }

    public void setCategoria(TipoArtista tipoArtista) {
        this.tipoArtista = tipoArtista;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public List<Album> getAlbuns() {
        return albuns;
    }

    public void setAlbuns(List<Album> albuns) {
        this.albuns = albuns;
    }

    @Override
    public String toString() {
        return
                "Artista: " + nome + '\'' +
                ", categoria: " + tipoArtista +
                ", musicas: " + musicas +
                ", albuns: " + albuns;
    }
}
