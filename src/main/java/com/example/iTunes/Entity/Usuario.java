package com.example.iTunes.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String email;
    private String senha;
    @OneToMany(mappedBy = "usuario")
    private List<Playlist>playlists;
    @ManyToMany
    @JoinTable(name = "usuario_Musica",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns =  @JoinColumn(name = "musica_id"))
    private List<Musica>musicas;
}
