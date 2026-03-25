/*
SQLyog Community v13.3.0 (64 bit)
MySQL - 10.4.32-MariaDB : Database - aplicativo
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `autores` */

insert  into `autores`(`id_autor`,`nome`) values 
(1,'George Orwell'),
(2,'J.K. Rowling'),
(3,'J.R.R. Tolkien'),
(4,'Machado de Assis'),
(5,'Clarice Lispector'),
(6,'Stephen King'),
(7,'Agatha Christie'),
(8,'Dan Brown');

/*Table structure for table `livros` */

DROP TABLE IF EXISTS `livros`;

CREATE TABLE `livros` (
  `id_livro` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) NOT NULL,
  `sinopse` varchar(800) NOT NULL,
  `total_paginas` int(11) NOT NULL,
  `pagina_atual` int(11) DEFAULT NULL,
  `id_autor` int(11) NOT NULL,
  `id_situacao` int(11) NOT NULL,
  PRIMARY KEY (`id_livro`),
  KEY `id_autor` (`id_autor`),
  KEY `id_situacao` (`id_situacao`),
  CONSTRAINT `livros_ibfk_1` FOREIGN KEY (`id_autor`) REFERENCES `autores` (`id_autor`),
  CONSTRAINT `livros_ibfk_2` FOREIGN KEY (`id_situacao`) REFERENCES `situacao` (`id_situacao`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `livros` */

insert  into `livros`(`id_livro`,`titulo`,`sinopse`,`total_paginas`,`pagina_atual`,`id_autor`,`id_situacao`) values 
(1,'1984','Uma distopia sobre um regime totalitário e vigilância extrema.',328,120,1,2),
(2,'Harry Potter e a Pedra Filosofal','Um jovem descobre que é um bruxo e vai para Hogwarts.',264,264,2,5),
(3,'O Senhor dos Anéis: A Sociedade do Anel','Um hobbit embarca em uma jornada para destruir um anel.',423,200,3,2),
(4,'Dom Casmurro','Um romance sobre ciúmes e memórias de Bentinho.',256,50,4,2),
(5,'A Hora da Estrela','A história de Macabéa, uma jovem nordestina no Rio.',96,NULL,5,1),
(6,'O Iluminado','Um homem enlouquece em um hotel isolado.',447,100,6,3),
(7,'Assassinato no Expresso do Oriente','Um detetive investiga um assassinato em um trem.',256,NULL,7,1),
(8,'O Código Da Vinci','Um simbologista investiga um mistério envolvendo a Igreja.',489,489,8,5);

/*Table structure for table `situacao` */

DROP TABLE IF EXISTS `situacao`;

CREATE TABLE `situacao` (
  `id_situacao` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(15) NOT NULL,
  PRIMARY KEY (`id_situacao`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `situacao` */

insert  into `situacao`(`id_situacao`,`descricao`) values 
(1,'Quero Ler'),
(2,'Estou Lendo'),
(3,'Parei de Ler'),
(4,'Desisti de Ler'),
(5,'Terminei de Ler');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
