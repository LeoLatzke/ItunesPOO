package com.example.iTunes.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String genero;
    private String gravadora;
    @ManyToOne
    @JoinColumn(name = "artista_id")
    private Artista artista;
    @OneToMany(mappedBy = "album")
    private List<Musica> musicas;
}
