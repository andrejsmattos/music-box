package br.com.andrejsmattos.music_box.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "albuns")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate dataLancamento;
    private int numeroFaixas;

//    @Transient
    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Musica> musicas = new ArrayList<>();

//    @Transient
    @ManyToOne
    @JoinColumn(name = "artista_id")
    private Artista artista;

    public Album() {
    }

    public Album(Long id, String nome, LocalDate dataLancamento, int numeroFaixas, List<Musica> musicas, Artista artista) {
        this.id = id;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.numeroFaixas = numeroFaixas;
        this.musicas = musicas;
        this.artista = artista;
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

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public int getNumeroFaixas() {
        return numeroFaixas;
    }

    public void setNumeroFaixas(int numeroFaixas) {
        this.numeroFaixas = numeroFaixas;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        return "Álbum: " + nome + '\'' +
                ", dataLancamento: " + dataLancamento +
                ", numeroFaixas: " + numeroFaixas +
                ", musicas: " + musicas +
                ", artista: " + artista;
    }
}
