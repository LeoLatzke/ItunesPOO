package com.example.iTunes.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private int duracao;
    private String genero;
    @ManyToOne
    @JoinColumn(name = "artista_id")
    private Artista artista;
    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;
}
