CREATE DATABASE aplicativo;

USE aplicativo;

CREATE TABLE usuarios(
    id_usuario INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100)
);

CREATE TABLE autores(
    id_autor INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE situacoes(
    id_situacao INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(15) NOT NULL
);

CREATE TABLE livros(
    id_livro INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    sinopse VARCHAR(100) NOT NULL,
    total_paginas INT NOT NULL,
    pagina_atual INT,
    
    # Foreign Key de Autor
    id_autor INT NOT NULL,
    FOREIGN KEY (id_autor) REFERENCES autores(id_autor),
    
    # Foreign Key de Situacao
    id_situacao INT NOT NULL,
    FOREIGN KEY (id_situacao) REFERENCES situacoes(id_situacao)
);

CREATE TABLE DailyStats(
    id_ds INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    data_leitura DATE NOT NULL,
    total_paginas_lidas INT NOT NULL,
    tempo_leitura TIME, 
    
    # Foreign Key de Usuário
    id_usuario INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

--          ###########

INSERT INTO situacoes
(descricao)
VALUES
('Quero Ler'),
('Estou Lendo'),
('Parei de Ler'),
('Terminei de Ler'),
('Desisti de Ler');

INSERT INTO usuarios (nome, email) VALUES
('Ana Souza', 'ana.souza@email.com'),
('Bruno Lima', 'bruno.lima@email.com'),
('Carla Mendes', 'carla.mendes@email.com'),
('Diego Alves', 'diego.alves@email.com'),
('Fernanda Rocha', 'fernanda.rocha@email.com');

INSERT INTO autores (nome) VALUES
('Machado de Assis'),
('Clarice Lispector'),
('George Orwell'),
('J.K. Rowling'),
('J.R.R. Tolkien'),
('Stephen King'),
('Agatha Christie');

INSERT INTO livros 
(titulo, sinopse, total_paginas, pagina_atual, id_autor, id_situacao) 
VALUES
('Dom Casmurro', 'Um romance sobre ciume e memoria.', 256, 120, 1, 2),
('A Hora da Estrela', 'Historia de Macabea no Rio.', 96, 96, 2, 4),
('1984', 'Distopia sobre vigilancia total.', 328, NULL, 3, 1),
('Harry Potter e a Pedra Filosofal', 'Um jovem descobre que e bruxo.', 320, 320, 4, 4),
('O Senhor dos Aneis', 'Uma jornada para destruir o anel.', 1178, 300, 5, 2),
('It: A Coisa', 'Criancas enfrentam um ser maligno.', 1138, NULL, 6, 3),
('Assassinato no Expresso do Oriente', 'Um misterio em um trem.', 256, NULL, 7, 1),
('Memorias Postumas de Bras Cubas', 'Narrativa apos a morte.', 208, 50, 1, 2);

INSERT INTO DailyStats 
(data_leitura, total_paginas_lidas, tempo_leitura, id_usuario) 
VALUES
('2026-03-20', 25, '01:00:00', 1),
('2026-03-21', 30, NULL, 1),
('2026-03-21', 15, '00:45:00', 2),
('2026-03-22', 40, '02:00:00', 3),
('2026-03-22', 10, NULL, 4),
('2026-03-23', 50, '02:10:00', 5),
('2026-03-23', 20, NULL, 2),
('2026-03-24', 35, '01:30:00', 3),
('2026-03-25', 60, '02:30:00', 1),
('2026-03-25', 22, NULL, 4);
