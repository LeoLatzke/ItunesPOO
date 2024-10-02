-- Inserindo usuários
INSERT INTO usuario (id, email, nome, senha) VALUES
(1, 'maria.silva@example.com', 'Maria Silva', 'senhaSegura123'),
(2, 'joao.pereira@example.com', 'João Pereira', 'senhaForte456');

-- Inserindo playlists
INSERT INTO playlist (id, nome_playlist, usuario_id) VALUES
(1, 'Melhores do Rock', 1),  -- Playlist de Maria Silva
(2, 'Top Hits do Pop', 2);    -- Playlist de João Pereira

-- Inserindo artistas
INSERT INTO artista (id, nome_artista) VALUES
(1, 'Legião Urbana'),
(2, 'Anitta');

-- Inserindo álbuns
INSERT INTO album (id, genero, gravadora, titulo, artista_id) VALUES
(1, 'Rock', 'Sony Music', 'O Descobrimento do Brasil', 1),  -- Álbum da Legião Urbana
(2, 'Pop', 'Warner Music', 'Kisses', 2);                    -- Álbum da Anitta

-- Inserindo músicas
INSERT INTO musica (id, duracao, genero, titulo, album_id, artista_id) VALUES
(1, 240, 'Rock', 'Eduardo e Mônica', 1, 1),  -- Música da Legião Urbana
(2, 210, 'Pop', 'Bang', 2, 2);               -- Música da Anitta

-- Inserindo músicas nas playlists
INSERT INTO playlist_musica (playlist_id, musica_id) VALUES
(1, 1),  -- Adicionando "Eduardo e Mônica" à playlist "Melhores do Rock"
(2, 2);  -- Adicionando "Bang" à playlist "Top Hits do Pop"

-- Inserindo músicas dos usuários
INSERT INTO usuario_musica (usuario_id, musica_id) VALUES
(1, 1),  -- Maria Silva tem a música "Eduardo e Mônica"
(2, 2);  -- João Pereira tem a música "Bang"
