package com.example.iTunes.Service;

import com.example.iTunes.Entity.Musica;
import com.example.iTunes.Entity.Usuario;
import com.example.iTunes.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Obter todos os usuários
    public List<Usuario> obterTodos() {
        return usuarioRepository.findAll();
    }

    // Obter usuário pelo ID
    public Optional<Usuario> obterPeloId(Long id) {
        return usuarioRepository.findById(id);
    }

    // Incluir novo usuário
    public Usuario incluir(Usuario usuario) {
        usuario.setId(0); // Reseta o ID para garantir que um novo usuário seja criado
        return usuarioRepository.save(usuario);
    }

    // Atualizar usuário existente
    public Usuario atualizar(Usuario usuario) {
        Usuario antiga = usuarioRepository.findById(usuario.getId()).orElse(null);
        if (antiga == null) {
            throw new RuntimeException("Usuário não encontrado");
        }
        antiga.setNome(usuario.getNome());
        antiga.setEmail(usuario.getEmail());
        antiga.setSenha(usuario.getSenha());
        return usuarioRepository.save(antiga);
    }

    // Excluir usuário
    public void excluir(Long id) {
        usuarioRepository.deleteById(id);
    }

    // Método para adicionar música ao usuário
    public Usuario adicionarMusica(Long usuarioId, Musica musica) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() ->
                new RuntimeException("Usuário não encontrado")
        );
        usuario.getMusicas().add(musica); // Adiciona a música à lista
        return usuarioRepository.save(usuario); // Salva o usuário atualizado
    }
}
