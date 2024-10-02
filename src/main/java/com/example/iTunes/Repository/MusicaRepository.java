package com.example.iTunes.Repository;

import com.example.iTunes.Entity.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Long> {
}
