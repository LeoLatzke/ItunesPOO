package com.example.iTunes.Controller;

import com.example.iTunes.Entity.Musica;
import com.example.iTunes.Entity.Usuario;
import com.example.iTunes.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    // Obter todos os usuários
    @GetMapping
    public List<Usuario> obterTodos() {
        return usuarioService.obterTodos();
    }

    // Obter usuário pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obterPeloId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.obterPeloId(id);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Incluir novo usuário
    @PostMapping
    public ResponseEntity<Usuario> incluir(@RequestBody Usuario usuario) {
        usuario.setId(0); // Reseta o ID para garantir que um novo usuário seja criado
        Usuario novoUsuario = usuarioService.incluir(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    // Atualizar usuário existente
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            usuario = usuarioService.atualizar(usuario);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Excluir usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!usuarioService.obterPeloId(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        usuarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    // Método para adicionar música ao usuário
    @PostMapping("/{usuarioId}/musicas")
    public ResponseEntity<Usuario> adicionarMusica(@PathVariable Long usuarioId, @RequestBody Musica musica) {
        Usuario usuario = usuarioService.obterPeloId(usuarioId).orElseThrow(() ->
                new RuntimeException("Usuário não encontrado")
        );
        usuario.getMusicas().add(musica); // Adiciona a música à lista
        Usuario usuarioAtualizado = usuarioService.atualizar(usuario); // Salva o usuário atualizado
        return ResponseEntity.ok(usuarioAtualizado); // Retorna o usuário atualizado
    }
}
