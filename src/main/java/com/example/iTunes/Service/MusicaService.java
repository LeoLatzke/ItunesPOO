package com.example.iTunes.Service;

import com.example.iTunes.Entity.Musica;
import com.example.iTunes.Repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicaService {

    @Autowired
    private MusicaRepository musicaRepository;

    public List<Musica> obterTodas() {
        return musicaRepository.findAll();
    }

    public Optional<Musica> obterPelaId(Long id) {
        return musicaRepository.findById(id);
    }

    public Musica incluir(Musica musica) {
        musica.setId(0L); // Reset id to ensure new music is created
        return musicaRepository.save(musica);
    }

    public Musica atualizar(Musica musica) {
        Musica antiga = musicaRepository.findById(musica.getId()).orElse(null);
        if (antiga == null) {
            throw new RuntimeException("Música não encontrada");
        }
        antiga.setTitulo(musica.getTitulo());
        antiga.setDuracao(musica.getDuracao());
        antiga.setGenero(musica.getGenero());
        antiga.setArtista(musica.getArtista());
        antiga.setAlbum(musica.getAlbum());
        return musicaRepository.save(antiga);
    }

    public void excluir(Long id) {
        musicaRepository.deleteById(id);
    }
}
