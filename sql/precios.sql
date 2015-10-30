/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.5.41-0ubuntu0.14.04.1 : Database - khipus
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `ventaarticulo` */

DROP TABLE IF EXISTS `ventaarticulo`;

CREATE TABLE `ventaarticulo` (
  `IDVENTAARTICULO` bigint(20) NOT NULL AUTO_INCREMENT,
  `PRECIO` double DEFAULT NULL,
  `no_cia` varchar(2) DEFAULT NULL,
  `cod_art` varchar(6) DEFAULT NULL,
  `IDPROMOCION` bigint(24) DEFAULT NULL,
  `TIPO` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`IDVENTAARTICULO`),
  KEY `FK_ventaarticulo_promocion_idx` (`IDPROMOCION`),
  KEY `fk_ventart_invart` (`no_cia`,`cod_art`),
  CONSTRAINT `FK_ventaarticulo_promocion` FOREIGN KEY (`IDPROMOCION`) REFERENCES `promocion` (`IDPROMOCION`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ventart_invart` FOREIGN KEY (`no_cia`, `cod_art`) REFERENCES `inv_articulos` (`no_cia`, `cod_art`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

/*Data for the table `ventaarticulo` */

insert  into `ventaarticulo`(`IDVENTAARTICULO`,`PRECIO`,`no_cia`,`cod_art`,`IDPROMOCION`,`TIPO`) values (2,4.6,'01','118',NULL,''),(3,6,'01','119',NULL,''),(4,7,'01','120',NULL,''),(5,1,'01','121',NULL,''),(6,7,'01','122',NULL,''),(7,1,'01','123',NULL,''),(8,7,'01','124',NULL,''),(9,7,'01','125',NULL,''),(10,6.5,'01','126',NULL,''),(11,1,'01','127',NULL,''),(12,14.5,'01','128',NULL,''),(13,14.5,'01','129',NULL,''),(14,14.5,'01','130',NULL,''),(15,14.5,'01','131',NULL,''),(16,14.5,'01','132',NULL,''),(17,14.5,'01','133',NULL,''),(18,13,'01','134',1,''),(19,12,'01','135',NULL,''),(20,0.46,'01','136',NULL,''),(21,7,'01','137',NULL,''),(22,0.46,'01','138',NULL,''),(23,7,'01','139',NULL,''),(24,7,'01','140',NULL,''),(25,7,'01','141',NULL,''),(26,7,'01','142',NULL,''),(27,0.46,'01','143',NULL,''),(28,0.38,'01','144',NULL,''),(29,0.38,'01','145',NULL,''),(30,0.38,'01','146',NULL,''),(31,7,'01','147',NULL,''),(32,35,'01','148',NULL,''),(33,7,'01','149',NULL,''),(34,7,'01','150',NULL,''),(35,30,'01','151',NULL,''),(36,12,'01','777',NULL,'COMBO');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
