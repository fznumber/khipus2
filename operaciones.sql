/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.6.23-log : Database - khipus1
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `operaciones` */

DROP TABLE IF EXISTS `operaciones`;

CREATE TABLE `operaciones` (
  `IDOPERACIONES` int(11) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `NOMBRE` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`IDOPERACIONES`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `operaciones` */

insert  into `operaciones`(`IDOPERACIONES`,`descripcion`,`NOMBRE`) values (1,'pedidos con factura','PEDIDOCONFACTURA'),(2,'pedidos sin factura','PEDIDOSINFACTURA'),(3,'pago del cliente por banco','PAGOBANCO'),(4,'pago del cliente por caja','PAGOCAJA'),(5,'venta directa sin factura','VENTASINFACTURA'),(6,'venta directa con factura','VENTACONFACTURA');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
