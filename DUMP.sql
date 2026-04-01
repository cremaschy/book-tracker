/*
SQLyog Community v13.2.0 (64 bit)
MySQL - 10.4.28-MariaDB : Database - aplicativo
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`aplicativo` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `aplicativo`;

/*Table structure for table `autores` */

DROP TABLE IF EXISTS `autores`;

CREATE TABLE `autores` (
  `id_autor` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  PRIMARY KEY (`id_autor`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `autores` */

insert  into `autores`(`id_autor`,`nome`) values 
(1,'Machado de Assis'),
(2,'Clarice Lispector'),
(3,'George Orwell'),
(4,'J.K. Rowling'),
(5,'J.R.R. Tolkien'),
(6,'Stephen King'),
(7,'Agatha Christie');

/*Table structure for table `dailystats` */

DROP TABLE IF EXISTS `dailystats`;

CREATE TABLE `dailystats` (
  `id_ds` int(11) NOT NULL AUTO_INCREMENT,
  `data_leitura` date NOT NULL,
  `total_paginas_lidas` int(11) NOT NULL,
  `tempo_leitura` time DEFAULT NULL,
  `id_livro` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  PRIMARY KEY (`id_ds`),
  KEY `id_livro` (`id_livro`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `dailystats_ibfk_1` FOREIGN KEY (`id_livro`) REFERENCES `livros` (`id_livro`),
  CONSTRAINT `dailystats_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `dailystats` */

insert  into `dailystats`(`id_ds`,`data_leitura`,`total_paginas_lidas`,`tempo_leitura`,`id_livro`,`id_usuario`) values 
(1,'2026-03-20',25,'01:00:00',1,1),
(2,'2026-03-21',30,NULL,1,2),
(3,'2026-03-21',15,'00:45:00',2,3),
(4,'2026-03-22',40,'02:00:00',3,4),
(5,'2026-03-22',10,NULL,4,5),
(6,'2026-03-23',50,'02:10:00',5,1),
(7,'2026-03-23',20,NULL,2,2),
(8,'2026-03-24',35,'01:30:00',3,3),
(9,'2026-03-25',60,'02:30:00',1,4),
(10,'2026-03-25',22,NULL,4,5);

/*Table structure for table `livros` */

DROP TABLE IF EXISTS `livros`;

CREATE TABLE `livros` (
  `id_livro` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) NOT NULL,
  `sinopse` varchar(100) NOT NULL,
  `total_paginas` int(11) NOT NULL,
  `pagina_atual` int(11) DEFAULT NULL,
  `id_autor` int(11) NOT NULL,
  `id_situacao` int(11) NOT NULL,
  PRIMARY KEY (`id_livro`),
  KEY `id_autor` (`id_autor`),
  KEY `id_situacao` (`id_situacao`),
  CONSTRAINT `livros_ibfk_1` FOREIGN KEY (`id_autor`) REFERENCES `autores` (`id_autor`),
  CONSTRAINT `livros_ibfk_2` FOREIGN KEY (`id_situacao`) REFERENCES `situacoes` (`id_situacao`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `livros` */

insert  into `livros`(`id_livro`,`titulo`,`sinopse`,`total_paginas`,`pagina_atual`,`id_autor`,`id_situacao`) values 
(1,'Dom Casmurro','Um romance sobre ciume e memoria.',256,120,1,2),
(2,'A Hora da Estrela','Historia de Macabea no Rio.',96,96,2,4),
(3,'1984','Distopia sobre vigilancia total.',328,NULL,3,1),
(4,'Harry Potter e a Pedra Filosofal','Um jovem descobre que e bruxo.',320,320,4,4),
(5,'O Senhor dos Aneis','Uma jornada para destruir o anel.',1178,300,5,2),
(6,'It: A Coisa','Criancas enfrentam um ser maligno.',1138,NULL,6,3),
(7,'Assassinato no Expresso do Oriente','Um misterio em um trem.',256,NULL,7,1),
(8,'Memorias Postumas de Bras Cubas','Narrativa apos a morte.',208,50,1,2);

/*Table structure for table `situacoes` */

DROP TABLE IF EXISTS `situacoes`;

CREATE TABLE `situacoes` (
  `id_situacao` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(15) NOT NULL,
  PRIMARY KEY (`id_situacao`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `situacoes` */

insert  into `situacoes`(`id_situacao`,`descricao`) values 
(1,'Quero Ler'),
(2,'Estou Lendo'),
(3,'Parei de Ler'),
(4,'Terminei de Ler'),
(5,'Desisti de Ler');

/*Table structure for table `usuarios` */

DROP TABLE IF EXISTS `usuarios`;

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `senha` char(8) NOT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `usuarios` */

insert  into `usuarios`(`id_usuario`,`nome`,`email`,`senha`) values 
(1,'Ana Souza','ana.souza@email.com','12345678'),
(2,'Bruno Lima','bruno.lima@email.com','12345678'),
(3,'Carla Mendes','carla.mendes@email.com','12345678'),
(4,'Diego Alves','diego.alves@email.com','12345678'),
(5,'Fernanda Rocha','fernanda.rocha@email.com','12345678');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
