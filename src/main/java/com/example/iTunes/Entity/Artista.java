package com.example.iTunes.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeArtista;
    @OneToMany(mappedBy = "artista")
    private List<Album> albuns;
    @OneToMany(mappedBy = "artista")
    private List<Musica> musicas;
}
