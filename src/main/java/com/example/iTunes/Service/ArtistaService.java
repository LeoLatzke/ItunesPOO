package com.example.iTunes.Service;

import com.example.iTunes.Entity.Artista;
import com.example.iTunes.Repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistaService {
    @Autowired
    private ArtistaRepository artistaRepository;

    public List<Artista> listarArtistas() {
        return artistaRepository.findAll();
    }

    public Optional<Artista> buscarArtistaPorId(Long id) {
        return artistaRepository.findById(id);
    }

    public Artista inserirArtista(Artista artista) {
        artista.setId(0L); // Reset id to ensure new artist is created
        return artistaRepository.save(artista);
    }

    public Artista atualizarArtista(Artista artistaAtualizado) {
        Artista artistaAntigo = artistaRepository.findById(artistaAtualizado.getId())
                .orElseThrow(() -> new RuntimeException("Artista não encontrado"));
        artistaAntigo.setNomeArtista(artistaAtualizado.getNomeArtista());
        return artistaRepository.save(artistaAntigo);
    }

    public void excluirArtista(Long id) {
        artistaRepository.deleteById(id);
    }
}
