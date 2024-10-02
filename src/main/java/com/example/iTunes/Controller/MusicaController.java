package com.example.iTunes.Controller;

import com.example.iTunes.Entity.Musica;
import com.example.iTunes.Service.MusicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

    @Autowired
    private MusicaService musicaService;

    @GetMapping
    public List<Musica> obterTodas() {
        return musicaService.obterTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Musica> obterPeloId(@PathVariable Long id) {
        var opt = musicaService.obterPelaId(id);
        return opt.map(musica -> new ResponseEntity<>(musica, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Musica> incluir(@RequestBody Musica musica) {
        musica = musicaService.incluir(musica);
        return new ResponseEntity<>(musica, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Musica> atualizar(@RequestBody Musica musica) {
        try {
            musica = musicaService.atualizar(musica);
            return new ResponseEntity<>(musica, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            musicaService.excluir(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
