package br.com.andrejsmattos.music_box.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "musicas")
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    @Enumerated(EnumType.STRING)
    private GeneroMusical generoMusical;

    @ManyToOne
    @JoinColumn(name = "artista_id")
    private Artista artista;

    @OneToOne
    @JoinColumn(name = "album_id")
    private Album album;


//    private int duracaoSegundos;
//    private LocalDate dataLancamento;


    public Musica() {
    }

    public Musica(Long id, String nome, GeneroMusical generoMusical, Artista artista, Album album) {
        this.id = id;
        this.nome = nome;
        this.generoMusical = generoMusical;
        this.artista = artista;
        this.album = album;
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

    public GeneroMusical getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(GeneroMusical generoMusical) {
        this.generoMusical = generoMusical;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "Música: " + nome + '\'' +
                ", generoMusical: " + generoMusical +
                ", artista: " + artista +
                ", álbum: " + album;
    }
}
