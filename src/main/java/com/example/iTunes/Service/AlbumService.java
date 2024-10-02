package com.example.iTunes.Service;

import com.example.iTunes.Entity.Album;
import com.example.iTunes.Repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    public List<Album> obterTodos() {
        return albumRepository.findAll();
    }

    public Optional<Album> obterPeloId(Long id) {
        return albumRepository.findById(id);
    }

    public Album incluir(Album album) {
        album.setId(0L); // Reset id to ensure new album is created
        return albumRepository.save(album);
    }

    public Album atualizar(Album album) {
        Album antigo = albumRepository.findById(album.getId()).orElse(null);
        if (antigo == null) {
            throw new RuntimeException("Álbum não encontrado");
        }
        antigo.setTitulo(album.getTitulo());
        antigo.setGenero(album.getGenero());
        antigo.setGravadora(album.getGravadora());
        antigo.setArtista(album.getArtista());
        return albumRepository.save(antigo);
    }

    public void excluir(Long id) {
        albumRepository.deleteById(id);
    }
}
