CREATE DATABASE aplicativo;

USE aplicativo;

CREATE TABLE situacao(
	id_situacao INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	descricao VARCHAR(15) NOT NULL
);

CREATE TABLE autores(
	id_autor INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(100) NOT NULL
);

CREATE TABLE livros(
	id_livro INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	titulo VARCHAR(100) NOT NULL,
	sinopse VARCHAR(800) NOT NULL,
	total_paginas INT NOT NULL,
	pagina_atual INT,
	
	# Foreign Key
	id_autor INT NOT NULL,
	FOREIGN KEY (id_autor) REFERENCES autores(id_autor),
	
	id_situacao INT NOT NULL,
	FOREIGN KEY (id_situacao) REFERENCES situacao(id_situacao)
);

INSERT INTO situacao
(descricao)
VALUES
('Quero Ler'),
('Estou Lendo'),
('Parei de Ler'),
('Desisti de Ler'),
('Terminei de Ler');

INSERT INTO autores (nome) VALUES
('George Orwell'),
('J.K. Rowling'),
('J.R.R. Tolkien'),
('Machado de Assis'),
('Clarice Lispector'),
('Stephen King'),
('Agatha Christie'),
('Dan Brown');

INSERT INTO livros 
(titulo, sinopse, total_paginas, pagina_atual, id_autor, id_situacao)
VALUES
('1984', 'Uma distopia sobre um regime totalitário e vigilância extrema.', 328, 120, 1, 2),
('Harry Potter e a Pedra Filosofal', 'Um jovem descobre que é um bruxo e vai para Hogwarts.', 264, 264, 2, 5),
('O Senhor dos Anéis: A Sociedade do Anel', 'Um hobbit embarca em uma jornada para destruir um anel.', 423, 200, 3, 2),
('Dom Casmurro', 'Um romance sobre ciúmes e memórias de Bentinho.', 256, 50, 4, 2),
('A Hora da Estrela', 'A história de Macabéa, uma jovem nordestina no Rio.', 96, NULL, 5, 1),
('O Iluminado', 'Um homem enlouquece em um hotel isolado.', 447, 100, 6, 3),
('Assassinato no Expresso do Oriente', 'Um detetive investiga um assassinato em um trem.', 256, NULL, 7, 1),
('O Código Da Vinci', 'Um simbologista investiga um mistério envolvendo a Igreja.', 489, 489, 8, 5);