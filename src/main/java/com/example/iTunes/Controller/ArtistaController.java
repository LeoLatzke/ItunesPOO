package com.example.iTunes.Controller;

import com.example.iTunes.Entity.Artista;
import com.example.iTunes.Service.ArtistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artista")
public class ArtistaController {
    @Autowired
    private ArtistaService artistaService;

    @GetMapping
    public List<Artista> obterTodos() {
        return artistaService.listarArtistas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artista> obterPeloId(@PathVariable Long id) {
        var opt = artistaService.buscarArtistaPorId(id);
        return opt.map(artista -> new ResponseEntity<>(artista, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Artista> incluir(@RequestBody Artista artista) {
        artista = artistaService.inserirArtista(artista);
        return new ResponseEntity<>(artista, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artista> atualizar(@PathVariable Long id, @RequestBody Artista artistaAtualizado) {
        try {
            artistaAtualizado.setId(id); // Definindo o ID do artista a ser atualizado
            var artista = artistaService.atualizarArtista(artistaAtualizado);
            return new ResponseEntity<>(artista, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            artistaService.excluirArtista(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
