package com.example.iTunes.Controller;

import com.example.iTunes.Entity.Musica;
import com.example.iTunes.Entity.Playlist;
import com.example.iTunes.Service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;

    @GetMapping
    public List<Playlist> obterTodos() {
        return playlistService.obterTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Playlist> obterPeloId(@PathVariable Long id) {
        var opt = playlistService.obterPeloId(id);
        return opt.map(playlist -> new ResponseEntity<>(playlist, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Playlist> incluir(@RequestBody Playlist playlist) {
        playlist = playlistService.incluir(playlist);
        return new ResponseEntity<>(playlist, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Playlist> atualizar(@RequestBody Playlist playlist) {
        try {
            playlist = playlistService.atualizar(playlist);
            return new ResponseEntity<>(playlist, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            playlistService.excluir(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para adicionar música à playlist
    @PostMapping("/{playlistId}/musica")
    public ResponseEntity<Playlist> adicionarMusica(@PathVariable Long playlistId, @RequestBody Musica musica) {
        try {
            Playlist updatedPlaylist = playlistService.adicionarMusica(playlistId, musica);
            return new ResponseEntity<>(updatedPlaylist, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
