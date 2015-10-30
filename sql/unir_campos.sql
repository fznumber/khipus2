USE khipus;
DROP TABLE IF EXISTS `articulos_paquete`;

CREATE TABLE `articulos_paquete` (
  `IDARTICULOSPAQUETE` BIGINT(20) NOT NULL,
  `CANTIDAD` BIGINT(20) DEFAULT NULL,
  `COD_ALM` VARCHAR(255) DEFAULT NULL,
  `COD_ART` VARCHAR(255) DEFAULT NULL,
  `ID_CUENTA` BIGINT(20) DEFAULT NULL,
  `IDCLIENTE` BIGINT(20) DEFAULT NULL,
  `IDPAQUETES` BIGINT(20) DEFAULT NULL,
  `NO_CIA` VARCHAR(255) DEFAULT NULL,
  `PRECIO` DOUBLE DEFAULT NULL,
  `TOTAL` DOUBLE DEFAULT NULL,
  PRIMARY KEY (`IDARTICULOSPAQUETE`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `articulos_paquete` */

/*Table structure for table `articulos_pedido` */

DROP TABLE IF EXISTS `articulos_pedido`;

CREATE TABLE `articulos_pedido` (
  `IDARTICULOSPEDIDO` BIGINT(20) NOT NULL,
  `CAJA` BIGINT(20) DEFAULT NULL,
  `CANTIDAD` INT(11) DEFAULT NULL,
  `COD_ALM` VARCHAR(255) DEFAULT NULL,
  `COD_ART` VARCHAR(255) DEFAULT NULL,
  `NO_CIA` VARCHAR(255) DEFAULT NULL,
  `PRECIO` DOUBLE DEFAULT NULL,
  `PRECIO_INV` DOUBLE DEFAULT NULL,
  `PROMOCION` INT(11) DEFAULT NULL,
  `REPOSICION` INT(11) DEFAULT NULL,
  `TOTAL` INT(11) DEFAULT NULL,
  `TOTAL_INV` INT(11) DEFAULT NULL,
  `IMPORTE` DOUBLE NOT NULL,
  `IDPEDIDOS` BIGINT(20) DEFAULT NULL,
  `estado` VARCHAR(20) DEFAULT NULL,
  `POR_REPONER` INT(11) DEFAULT NULL,
  PRIMARY KEY (`IDARTICULOSPEDIDO`),
  KEY `ARTICULOS_PEDIDO_INV_ARTICULOS_FK_idx` (`COD_ART`,`NO_CIA`),
  KEY `FK_ARTICULOS_PEDIDO_PEDIDOS_idx` (`IDPEDIDOS`),
  CONSTRAINT `ARTICULOS_PEDIDO_INV_ARTICULOS_FK` FOREIGN KEY (`COD_ART`, `NO_CIA`) REFERENCES `inv_articulos` (`COD_ART`, `NO_CIA`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ARTICULOS_PEDIDO_PEDIDOS` FOREIGN KEY (`IDPEDIDOS`) REFERENCES `pedidos` (`IDPEDIDOS`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `articulos_pedido` */

INSERT  INTO `articulos_pedido`(`IDARTICULOSPEDIDO`,`CAJA`,`CANTIDAD`,`COD_ALM`,`COD_ART`,`NO_CIA`,`PRECIO`,`PRECIO_INV`,`PROMOCION`,`REPOSICION`,`TOTAL`,`TOTAL_INV`,`IMPORTE`,`IDPEDIDOS`,`estado`,`POR_REPONER`) VALUES (51,NULL,10,NULL,'148','01',7,7,0,0,10,0,70,2,'RECHAZADO',12),(52,NULL,10,NULL,'128','01',7,7,0,0,10,0,70,2,'RECHAZADO',12),(151,NULL,12,NULL,'149','01',7,7,0,0,12,0,84,4,NULL,NULL),(152,NULL,13,NULL,'118','01',7,7,0,0,13,0,91,4,NULL,NULL),(153,NULL,34,NULL,'119','01',7,7,0,0,34,0,238,4,NULL,NULL),(201,NULL,12,NULL,'118','01',7,7,0,0,12,0,84,5,NULL,NULL),(202,NULL,23,NULL,'150','01',7,7,0,0,23,0,161,5,NULL,NULL),(203,NULL,34,NULL,'129','01',7,7,0,0,34,0,238,5,NULL,NULL),(251,NULL,12,NULL,'118','01',7,7,0,0,12,0,84,6,NULL,NULL),(252,NULL,23,NULL,'149','01',7,7,0,0,23,0,161,6,NULL,NULL),(253,NULL,45,NULL,'131','01',7,7,0,0,45,0,315,6,NULL,NULL),(254,NULL,2,NULL,'124','01',7,7,0,0,2,0,14,6,NULL,NULL),(255,NULL,3,NULL,'124','01',7,7,0,0,3,0,21,6,NULL,NULL),(301,NULL,23,NULL,'118','01',7,7,0,0,23,0,161,7,NULL,NULL),(302,NULL,12,NULL,'149','01',7,7,0,0,12,0,84,7,NULL,NULL),(303,NULL,45,NULL,'129','01',7,7,0,0,45,0,315,7,NULL,NULL),(351,NULL,12,NULL,'118','01',7,7,0,0,12,0,84,8,NULL,NULL),(352,NULL,23,NULL,'148','01',7,7,0,0,23,0,161,8,NULL,NULL),(353,NULL,23,NULL,'150','01',7,7,0,0,23,0,161,8,NULL,NULL),(401,NULL,1,NULL,'148','01',7,7,0,0,1,0,7,9,NULL,0),(402,NULL,2,NULL,'118','01',7,7,0,0,2,0,14,9,NULL,0),(403,NULL,3,NULL,'123','01',7,7,0,0,3,0,21,9,NULL,0),(404,NULL,4,NULL,'124','01',7,7,0,0,4,0,28,9,NULL,0),(405,NULL,5,NULL,'125','01',7,7,0,0,5,0,35,9,NULL,0),(406,NULL,5,NULL,'127','01',7,7,0,0,5,0,35,9,NULL,0),(407,NULL,2,NULL,'149','01',7,7,0,0,2,0,14,9,NULL,0),(408,NULL,3,NULL,'150','01',7,7,0,0,3,0,21,9,NULL,0),(409,NULL,4,NULL,'151','01',7,7,0,0,4,0,28,9,NULL,0),(410,NULL,4,NULL,'128','01',7,7,0,0,4,0,28,9,NULL,0),(411,NULL,5,NULL,'131','01',7,7,0,0,5,0,35,9,NULL,0),(412,NULL,1,NULL,'130','01',7,7,0,0,1,0,7,9,NULL,0),(413,NULL,2,NULL,'133','01',7,7,0,0,2,0,14,9,NULL,0),(414,NULL,3,NULL,'134','01',7,7,0,0,3,0,21,9,NULL,0),(415,NULL,2,NULL,'132','01',7,7,0,0,2,0,14,9,NULL,0),(416,NULL,4,NULL,'135','01',7,7,0,0,4,0,28,9,NULL,0),(417,NULL,5,NULL,'126','01',7,7,0,0,5,0,35,9,NULL,0),(418,NULL,5,NULL,'136','01',7,7,0,0,5,0,35,9,NULL,0),(451,NULL,2,NULL,'148','01',7,7,0,0,2,0,14,10,NULL,0),(452,NULL,3,NULL,'118','01',7,7,0,0,3,0,21,10,NULL,0),(453,NULL,4,NULL,'149','01',7,7,0,0,4,0,28,10,NULL,0),(454,NULL,12,NULL,'151','01',7,7,0,0,12,0,84,10,NULL,0),(455,NULL,2,NULL,'118','01',7,7,0,0,2,0,14,11,'REPUESTO',1),(456,NULL,1,NULL,'124','01',7,7,0,0,1,0,7,11,'REPUESTO',1),(501,NULL,2,NULL,'118','01',7,7,0,0,2,0,14,12,'REPUESTO',1),(502,NULL,3,NULL,'127','01',7,7,0,0,3,0,21,12,'',0),(503,NULL,4,NULL,'136','01',7,7,0,0,4,0,28,12,'',0),(551,NULL,0,NULL,'118','01',7,0,0,1,1,0,0,13,NULL,0),(552,NULL,2,NULL,'124','01',7,7,0,0,2,0,14,13,NULL,0),(601,NULL,0,NULL,'118','01',7,0,0,1,1,0,0,14,NULL,0),(602,NULL,2,NULL,'125','01',7,7,0,0,2,0,14,14,NULL,0),(603,NULL,3,NULL,'148','01',7,7,0,0,3,0,21,14,NULL,0),(651,NULL,0,NULL,'118','01',7,0,0,1,1,0,0,15,NULL,0),(652,NULL,2,NULL,'124','01',7,7,0,0,2,0,14,15,NULL,0),(653,NULL,0,NULL,'118','01',7,0,0,1,1,0,0,16,NULL,0),(654,NULL,0,NULL,'124','01',7,0,0,1,1,0,0,16,NULL,0),(655,NULL,2,NULL,'126','01',7,7,0,0,2,0,14,16,NULL,0),(656,NULL,2,NULL,'125','01',7,7,0,0,2,0,14,17,NULL,0),(701,NULL,12,NULL,'118','01',7,7,0,0,12,0,84,18,NULL,0),(702,NULL,12,NULL,'148','01',7,7,0,0,12,0,84,18,NULL,0),(703,NULL,12,NULL,'124','01',7,7,0,0,12,0,84,18,NULL,0),(704,NULL,2,NULL,'125','01',7,7,0,0,2,0,14,18,NULL,0),(705,NULL,12,NULL,'123','01',7,7,0,0,12,0,84,19,NULL,0),(706,NULL,23,NULL,'127','01',7,7,0,0,23,0,161,19,NULL,0),(707,NULL,34,NULL,'137','01',7,7,0,0,34,0,238,19,NULL,0),(708,NULL,2,NULL,'118','01',7,7,0,0,2,0,14,20,NULL,0),(709,NULL,4,NULL,'125','01',7,7,0,0,4,0,28,20,NULL,0),(710,NULL,5,NULL,'150','01',7,7,0,0,5,0,35,20,NULL,0),(711,NULL,23,NULL,'124','01',7,7,0,0,23,0,161,21,NULL,0),(712,NULL,12,NULL,'135','01',7,7,0,0,12,0,84,21,NULL,0),(713,NULL,23,NULL,'131','01',7,7,0,0,23,0,161,21,NULL,0),(751,NULL,23,NULL,'118','01',7,7,0,0,23,0,161,22,NULL,0),(752,NULL,12,NULL,'148','01',7,7,0,0,12,0,84,22,NULL,0),(753,NULL,12,NULL,'125','01',7,7,0,0,12,0,84,22,NULL,0),(801,NULL,23,NULL,'134','01',7,7,0,0,23,0,161,22,NULL,0),(802,NULL,23,NULL,'134','01',7,7,0,0,23,0,161,22,NULL,0),(851,NULL,12,NULL,'118','01',7,7,0,0,12,0,84,23,'REPUESTO',2),(852,NULL,23,NULL,'125','01',7,7,0,0,23,0,161,23,'REPUESTO',2),(853,NULL,12,NULL,'150','01',7,7,0,0,12,0,84,23,'',0),(854,NULL,23,NULL,'149','01',7,7,0,0,23,0,161,23,'',0),(901,NULL,0,NULL,'118','01',7,0,0,2,2,0,0,24,NULL,0),(902,NULL,0,NULL,'125','01',7,0,0,2,2,0,0,24,NULL,0),(903,NULL,2,NULL,'123','01',7,7,0,0,2,0,14,24,NULL,0),(904,NULL,2,NULL,'118','01',7,7,0,0,2,0,14,25,NULL,0),(905,NULL,3,NULL,'148','01',7,7,0,0,3,0,21,25,NULL,0),(951,NULL,22,NULL,'118','01',7,7,0,0,22,0,154,26,NULL,0),(952,NULL,23,NULL,'150','01',7,7,0,0,23,0,161,26,NULL,0),(953,NULL,12,NULL,'129','01',7,7,0,0,12,0,84,26,NULL,0),(1001,NULL,21,NULL,'118','01',7,7,0,0,21,0,147,27,'REPUESTO',2),(1002,NULL,12,NULL,'148','01',7,7,0,0,12,0,84,27,'REPUESTO',3),(1003,NULL,23,NULL,'128','01',7,7,0,0,23,0,161,27,'REPUESTO',4),(1051,NULL,0,NULL,'118','01',7,0,0,2,2,0,0,28,'RECHAZADO',0),(1052,NULL,0,NULL,'148','01',7,0,0,3,3,0,0,28,'RECHAZADO',0),(1053,NULL,0,NULL,'128','01',7,0,0,4,4,0,0,28,'RECHAZADO',0),(1054,NULL,2,NULL,'149','01',7,7,0,0,2,0,14,28,'RECHAZADO',0),(1055,NULL,3,NULL,'151','01',7,7,0,0,3,0,21,28,'RECHAZADO',0),(1101,NULL,12,NULL,'118','01',7,7,0,0,12,0,84,29,'RECHAZADO',4),(1102,NULL,32,NULL,'123','01',7,7,0,0,32,0,224,29,'RECHAZADO',3),(1103,NULL,34,NULL,'124','01',7,7,0,0,34,0,238,29,'',0),(1151,NULL,12,NULL,'118','01',7,7,0,0,12,0,84,30,'RECHAZADO',12),(1152,NULL,23,NULL,'148','01',7,7,0,0,23,0,161,30,'RECHAZADO',12),(1153,NULL,23,NULL,'123','01',7,7,0,0,23,0,161,30,'',0),(1154,NULL,12,NULL,'124','01',7,7,0,0,12,0,84,30,'',0),(1201,NULL,0,NULL,'118','01',7,0,0,12,12,0,0,31,NULL,0),(1202,NULL,12,NULL,'148','01',7,0,0,12,24,0,84,31,NULL,0),(1203,NULL,12,NULL,'123','01',7,7,0,0,12,0,84,31,NULL,0),(1204,NULL,23,NULL,'149','01',7,7,0,0,23,0,161,31,NULL,0);

/*Table structure for table `codigopedidosecuencia` */

DROP TABLE IF EXISTS `codigopedidosecuencia`;

CREATE TABLE `codigopedidosecuencia` (
  `secuencia` BIGINT(20) NOT NULL,
  PRIMARY KEY (`secuencia`),
  UNIQUE KEY `unique_secuencia` (`secuencia`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `codigopedidosecuencia` */

INSERT  INTO `codigopedidosecuencia`(`secuencia`) VALUES (1),(2),(3),(4),(5),(6),(7),(8),(9),(10),(11),(12),(13),(14),(15),(16),(17),(18),(19),(20),(21),(22),(23),(24),(25),(26),(27),(28),(29),(30);

/*Table structure for table `compania` */

DROP TABLE IF EXISTS `compania`;

CREATE TABLE `compania` (
  `IDCOMPANIA` BIGINT(20) NOT NULL,
  `CODIGO` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`IDCOMPANIA`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `compania` */

INSERT  INTO `compania`(`IDCOMPANIA`,`CODIGO`) VALUES (1,'ILVA');

/*Table structure for table `departamento` */

DROP TABLE IF EXISTS `departamento`;

CREATE TABLE `departamento` (
  `IDDEPARTAMENTO` BIGINT(24) NOT NULL,
  `NOMBRE` VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (`IDDEPARTAMENTO`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `departamento` */

INSERT  INTO `departamento`(`IDDEPARTAMENTO`,`NOMBRE`) VALUES (1,'COCHABAMBA'),(2,'SANTA CRUZ'),(3,'LA PAZ'),(4,'SUCRE'),(5,'ORURO'),(6,'BENI'),(7,'PANDO'),(8,'POTOSI'),(9,'CHUQUISACA');

/*Table structure for table `dosificacion` */

DROP TABLE IF EXISTS `dosificacion`;

CREATE TABLE `dosificacion` (
  `IDDOSIFICACION` BIGINT(24) NOT NULL,
  `NROAUTORIZACION` BIGINT(20) DEFAULT NULL,
  `FECHAVENCIMIENTO` DATE DEFAULT NULL,
  `EST_COD` VARCHAR(30) DEFAULT NULL,
  `LLAVE` VARCHAR(100) DEFAULT NULL,
  `ESTADO` VARCHAR(15) DEFAULT NULL,
  `FACTURADEL` BIGINT(20) DEFAULT NULL,
  `FACTURAAL` BIGINT(20) DEFAULT NULL,
  `NUMEROACTUAL` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`IDDOSIFICACION`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `dosificacion` */

INSERT  INTO `dosificacion`(`IDDOSIFICACION`,`NROAUTORIZACION`,`FECHAVENCIMIENTO`,`EST_COD`,`LLAVE`,`ESTADO`,`FACTURADEL`,`FACTURAAL`,`NUMEROACTUAL`) VALUES (1,790400429977,'2015-12-31','2009','+[+++Y9*3jBAJPtAH98YLUrN)R]%Qd(Dw_Xu2#\\#WJDMZU8f(9R6-22XJZ(5SK$6','ACTIVO',1,9999999,84);

/*Table structure for table `id_gen` */

DROP TABLE IF EXISTS `id_gen`;

CREATE TABLE `id_gen` (
  `GEN_NAME` VARCHAR(30) NOT NULL,
  `GEN_VAL` BIGINT(50) DEFAULT NULL,
  PRIMARY KEY (`GEN_NAME`),
  UNIQUE KEY `GEN_NAME_UNIQUE` (`GEN_NAME`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `id_gen` */

INSERT  INTO `id_gen`(`GEN_NAME`,`GEN_VAL`) VALUES ('ArticulosPedido_Gen',1250),('Impresionfactura_Gen',78),('Movimiento_Gen',20),('PedidosCodigo_Gen',30),('Pedidos_Gen',31),('Persona_GEN',19),('Territoriotrabajo_Gen',5);

/*Table structure for table `impresionfactura` */

DROP TABLE IF EXISTS `impresionfactura`;

CREATE TABLE `impresionfactura` (
  `IDIMPRECIONFACTURA` BIGINT(24) NOT NULL,
  `FECHAIMPRESION` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `TIPO` VARCHAR(10) DEFAULT NULL,
  `IDMOVIMIENTO` BIGINT(24) NOT NULL,
  `IDUSUARIO` BIGINT(20) NOT NULL,
  `IDDOSIFICACION` BIGINT(24) DEFAULT NULL,
  `NROFACTURA` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`IDIMPRECIONFACTURA`),
  KEY `IMPRESIONFACTURA_MOVIMIENTO_FK` (`IDMOVIMIENTO`),
  KEY `IMPRESIONFACTURA_usuario_FK` (`IDUSUARIO`),
  KEY `IMPRESIONFACTURA_dosificacion_idx` (`IDDOSIFICACION`),
  CONSTRAINT `IMPRESIONFACTURA_MOVIMIENTO_FK` FOREIGN KEY (`IDMOVIMIENTO`) REFERENCES `movimiento` (`IDMOVIMIENTO`),
  CONSTRAINT `IMPRESIONFACTURA_dosificacion` FOREIGN KEY (`IDDOSIFICACION`) REFERENCES `dosificacion` (`IDDOSIFICACION`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `IMPRESIONFACTURA_usuario_FK` FOREIGN KEY (`IDUSUARIO`) REFERENCES `usuario` (`IDUSUARIO`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `impresionfactura` */

INSERT  INTO `impresionfactura`(`IDIMPRECIONFACTURA`,`FECHAIMPRESION`,`TIPO`,`IDMOVIMIENTO`,`IDUSUARIO`,`IDDOSIFICACION`,`NROFACTURA`) VALUES (1,'2015-03-30 06:31:04','ORIGINAL',1,1,1,1),(2,'2015-03-30 06:46:52','ORIGINAL',2,1,1,1),(3,'2015-03-30 07:08:28','ORIGINAL',3,1,1,1),(4,'2015-03-30 07:10:24','COPIA',2,1,1,1),(5,'2015-03-30 07:10:24','COPIA',2,1,1,1),(6,'2015-03-30 07:11:46','COPIA',2,1,1,1),(7,'2015-03-30 07:10:24','COPIA',2,1,1,1),(8,'2015-03-30 07:11:46','COPIA',2,1,1,1),(9,'2015-03-30 07:15:13','COPIA',2,1,1,1),(10,'2015-03-30 07:10:24','COPIA',2,1,1,1),(11,'2015-03-30 07:11:46','COPIA',2,1,1,1),(12,'2015-03-30 07:15:13','COPIA',2,1,1,1),(13,'2015-03-30 07:17:52','COPIA',2,1,1,1),(14,'2015-03-30 07:23:21','ORIGINAL',2,1,1,1),(15,'2015-03-30 07:23:21','ORIGINAL',2,1,1,1),(16,'2015-03-30 07:23:59','ORIGINAL',2,1,1,2),(17,'2015-03-30 07:30:52','ORIGINAL',2,1,1,3),(18,'2015-03-30 07:33:46','ORIGINAL',2,1,1,4),(19,'2015-03-30 11:15:11','ORIGINAL',2,1,1,5),(20,'2015-03-30 11:25:39','ORIGINAL',2,1,1,6),(21,'2015-03-30 11:29:31','ORIGINAL',2,1,1,7),(22,'2015-03-30 11:44:59','ORIGINAL',2,1,1,8),(23,'2015-03-30 11:54:51','ORIGINAL',4,1,1,9),(24,'2015-03-30 16:44:56','ORIGINAL',2,1,1,10),(25,'2015-03-31 00:18:06','ORIGINAL',2,1,1,11),(26,'2015-03-31 00:44:56','ORIGINAL',2,1,1,12),(27,'2015-03-31 10:32:19','COPIA',2,1,1,13),(28,'2015-03-31 10:43:09','ORIGINAL',2,1,1,14),(29,'2015-03-31 10:49:47','ORIGINAL',2,1,1,15),(30,'2015-03-31 10:49:47','ORIGINAL',2,1,1,15),(31,'2015-03-31 10:51:28','ORIGINAL',2,1,1,16),(32,'2015-03-31 11:06:35','COPIA',2,1,1,17),(33,'2015-03-31 11:06:35','COPIA',2,1,1,17),(34,'2015-03-31 11:06:47','ORIGINAL',2,1,1,18),(35,'2015-03-31 15:13:36','ORIGINAL',2,1,1,19),(36,'2015-03-31 15:13:37','ORIGINAL',3,1,1,20),(37,'2015-03-31 15:13:37','ORIGINAL',5,1,1,21),(38,'2015-03-31 15:13:37','ORIGINAL',6,1,1,22),(39,'2015-03-31 15:13:38','ORIGINAL',7,1,1,23),(40,'2015-03-31 15:13:38','ORIGINAL',8,1,1,24),(41,'2015-03-31 15:13:38','ORIGINAL',4,1,1,25),(42,'2015-03-31 15:47:20','COPIA',2,1,1,26),(43,'2015-03-31 15:47:23','COPIA',3,1,1,27),(44,'2015-03-31 15:47:26','COPIA',5,1,1,28),(45,'2015-03-31 15:47:27','COPIA',6,1,1,29),(46,'2015-03-31 15:47:28','COPIA',7,1,1,30),(47,'2015-03-31 15:47:29','COPIA',8,1,1,31),(48,'2015-03-31 15:47:30','COPIA',4,1,1,32),(49,'2015-03-31 15:53:20','ORIGINAL',4,1,1,33),(50,'2015-03-31 15:56:31','COPIA',8,1,1,34),(51,'2015-03-31 15:56:31','COPIA',8,1,1,34),(52,'2015-03-31 15:57:11','COPIA',8,1,1,35),(53,'2015-03-31 15:57:33','COPIA',4,1,1,36),(54,'2015-03-31 23:49:32','COPIA',2,1,1,37),(55,'2015-03-31 23:49:32','COPIA',2,1,1,37),(56,'2015-03-31 23:50:25','ORIGINAL',2,1,1,38),(57,'2015-03-31 23:50:26','ORIGINAL',3,1,1,39),(58,'2015-03-31 23:50:26','ORIGINAL',5,1,1,40),(59,'2015-03-31 23:50:26','ORIGINAL',6,1,1,41),(60,'2015-03-31 23:50:26','ORIGINAL',7,1,1,42),(61,'2015-03-31 23:50:27','ORIGINAL',8,1,1,43),(62,'2015-03-31 23:50:27','ORIGINAL',4,1,1,44),(63,'2015-04-02 10:40:29','ORIGINAL',5,1,1,45),(64,'2015-04-02 10:40:44','ORIGINAL',8,1,1,46),(65,'2015-04-02 10:40:45','ORIGINAL',4,1,1,47),(66,'2015-04-02 10:40:45','ORIGINAL',9,1,1,48),(67,'2015-04-02 10:40:52','ORIGINAL',10,1,1,49),(68,'2015-04-02 10:40:52','ORIGINAL',11,1,1,50),(69,'2015-04-02 10:40:52','ORIGINAL',12,1,1,51),(70,'2015-04-02 11:20:37','ORIGINAL',13,1,1,59),(71,'2015-04-02 11:20:37','ORIGINAL',14,1,1,59),(72,'2015-04-02 11:23:56','ORIGINAL',14,1,1,67),(73,'2015-04-02 11:45:52','ORIGINAL',15,1,1,76),(74,'2015-04-02 11:45:53','ORIGINAL',16,1,1,77),(75,'2015-04-02 11:45:53','ORIGINAL',17,1,1,78),(76,'2015-04-03 15:47:37','ORIGINAL',18,1,1,79),(77,'2015-04-03 23:22:25','ORIGINAL',19,1,1,80),(78,'2015-04-04 01:38:45','ORIGINAL',20,1,1,82);

/*Table structure for table `movimiento` */

DROP TABLE IF EXISTS `movimiento`;

CREATE TABLE `movimiento` (
  `IDMOVIMIENTO` BIGINT(24) NOT NULL,
  `GLOSA` VARCHAR(300) DEFAULT NULL,
  `ESTADO` VARCHAR(15) DEFAULT NULL,
  `CODESTRUCT` VARCHAR(30) DEFAULT NULL,
  `MONEDA` VARCHAR(10) DEFAULT NULL,
  `NROFACTURA` VARCHAR(30) DEFAULT NULL,
  `CODIGOCONTROL` VARCHAR(30) DEFAULT NULL,
  `TIPOPAGO` VARCHAR(10) DEFAULT NULL,
  `TIPOCAMBIO` DOUBLE DEFAULT NULL,
  `FECHAREGISTRO` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`IDMOVIMIENTO`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `movimiento` */

INSERT  INTO `movimiento`(`IDMOVIMIENTO`,`GLOSA`,`ESTADO`,`CODESTRUCT`,`MONEDA`,`NROFACTURA`,`CODIGOCONTROL`,`TIPOPAGO`,`TIPOCAMBIO`,`FECHAREGISTRO`) VALUES (1,'','PENDIENTE','2009','BS',NULL,'A7-EE-AF-5B',NULL,6.69,'2015-03-30 06:31:04'),(2,'','PENDIENTE','2009','BS',NULL,'A7-EE-AF-5B',NULL,6.69,'2015-03-30 06:46:52'),(3,'','PENDIENTE','2009','BS',NULL,'AA-96-D6-97-83',NULL,6.69,'2015-03-30 07:08:28'),(4,'','PENDIENTE','2009','BS',NULL,NULL,NULL,6.69,'2015-03-30 11:54:51'),(5,'','PENDIENTE','2009','BS',NULL,'34-1C-E7-FB',NULL,6.69,'2015-03-31 15:13:37'),(6,'','PENDIENTE','2009','BS',NULL,'43-B3-40-A1-DD',NULL,6.69,'2015-03-31 15:13:37'),(7,'','PENDIENTE','2009','BS',NULL,'8D-73-BE-E8-6C',NULL,6.69,'2015-03-31 15:13:38'),(8,'','PENDIENTE','2009','BS',NULL,'D4-AA-BA-3C-4D',NULL,6.69,'2015-03-31 15:13:38'),(9,'','PENDIENTE','2009','BS',NULL,NULL,NULL,6.69,'2015-04-02 10:40:45'),(10,'','PENDIENTE','2009','BS',NULL,NULL,NULL,6.69,'2015-04-02 10:40:52'),(11,'','PENDIENTE','2009','BS',NULL,NULL,NULL,6.69,'2015-04-02 10:40:52'),(12,'','PENDIENTE','2009','BS',NULL,NULL,NULL,6.69,'2015-04-02 10:40:52'),(13,'','PENDIENTE','2009','BS',NULL,'9F-FF-3D-DB',NULL,6.69,'2015-04-02 11:20:37'),(14,'','PENDIENTE','2009','BS',NULL,'9F-FF-3D-DB',NULL,6.69,'2015-04-02 11:20:37'),(15,'','PENDIENTE','2009','BS',NULL,'D3-B9-92-04',NULL,6.69,'2015-04-02 11:45:52'),(16,'','PENDIENTE','2009','BS',NULL,'14-81-32-8C-75',NULL,6.69,'2015-04-02 11:45:53'),(17,'','PENDIENTE','2009','BS',NULL,'48-F3-A4-99',NULL,6.69,'2015-04-02 11:45:53'),(18,'','PENDIENTE','2009','BS',NULL,'15-B6-B0-FB-70',NULL,6.69,'2015-04-03 15:47:37'),(19,'','PENDIENTE','2009','BS',NULL,'E6-9A-82-00',NULL,6.69,'2015-04-03 23:22:25'),(20,'','PENDIENTE','2009','BS',NULL,'AA-8D-82-C4-C4',NULL,6.69,'2015-04-04 01:38:45');

/*Table structure for table `pedidos` */

DROP TABLE IF EXISTS `pedidos`;

CREATE TABLE `pedidos` (
  `IDPEDIDOS` BIGINT(20) NOT NULL,
  `DESCRIPCION` VARCHAR(255) DEFAULT NULL,
  `FACTURA` VARCHAR(255) DEFAULT NULL,
  `FECHA_A_PAGAR` DATE DEFAULT NULL,
  `FECHA_ENTREGA` DATE DEFAULT NULL,
  `FECHA_PEDIDO` DATE DEFAULT NULL,
  `IDDIRECCION` BIGINT(20) DEFAULT NULL,
  `OBSERVACION` VARCHAR(255) DEFAULT NULL,
  `PORCENTAJECOMISION` DOUBLE DEFAULT NULL,
  `PORCENTAJEGARANTIA` DOUBLE DEFAULT NULL,
  `SUPERVISOR` BIGINT(20) DEFAULT NULL,
  `TOTAL` DOUBLE DEFAULT NULL,
  `ESTADO_PEDIDO` BIGINT(20) DEFAULT NULL,
  `IDCLIENTE` BIGINT(20) DEFAULT NULL,
  `IDTIPOPEDIDO` BIGINT(20) DEFAULT NULL,
  `ESTADO` VARCHAR(15) DEFAULT NULL,
  `TOTALIMPORTE` DOUBLE NOT NULL,
  `IDUSUARIO` BIGINT(20) DEFAULT NULL,
  `VALORCOMISION` DOUBLE DEFAULT NULL,
  `VALORGARANTIA` DOUBLE DEFAULT NULL,
  `CODIGO` BIGINT(20) DEFAULT NULL,
  `CON_REPOSICION` INT(11) DEFAULT NULL,
  `IDMOVIMIENTO` BIGINT(24) DEFAULT NULL,
  `PEDIDOSREPOSICION` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`IDPEDIDOS`),
  KEY `FK_pedidos_IDTIPOPEDIDO` (`IDTIPOPEDIDO`),
  KEY `FK_pedidos_ESTADO_PEDIDO` (`ESTADO_PEDIDO`),
  KEY `FK_pedidos_usuario_idx` (`IDUSUARIO`),
  KEY `fk_pedidos_cliente_idx` (`IDCLIENTE`),
  KEY `fk_pedidos_movimiento_idx` (`IDMOVIMIENTO`),
  KEY `fk_pedidos_reposicion_idx` (`PEDIDOSREPOSICION`),
  CONSTRAINT `FK_pedidos_ESTADO_PEDIDO` FOREIGN KEY (`ESTADO_PEDIDO`) REFERENCES `estado_pedidos` (`IDESTADOPEDIDO`),
  CONSTRAINT `FK_pedidos_IDTIPOPEDIDO` FOREIGN KEY (`IDTIPOPEDIDO`) REFERENCES `tipopedido` (`IDTIPOPEDIDO`),
  CONSTRAINT `FK_pedidos_usuario` FOREIGN KEY (`IDUSUARIO`) REFERENCES `usuario` (`IDUSUARIO`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedidos_cliente` FOREIGN KEY (`IDCLIENTE`) REFERENCES `persona` (`IDPERSONA`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedidos_movimiento` FOREIGN KEY (`IDMOVIMIENTO`) REFERENCES `movimiento` (`IDMOVIMIENTO`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedidos_reposicion` FOREIGN KEY (`PEDIDOSREPOSICION`) REFERENCES `pedidos` (`IDPEDIDOS`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `pedidos` */

INSERT  INTO `pedidos`(`IDPEDIDOS`,`DESCRIPCION`,`FACTURA`,`FECHA_A_PAGAR`,`FECHA_ENTREGA`,`FECHA_PEDIDO`,`IDDIRECCION`,`OBSERVACION`,`PORCENTAJECOMISION`,`PORCENTAJEGARANTIA`,`SUPERVISOR`,`TOTAL`,`ESTADO_PEDIDO`,`IDCLIENTE`,`IDTIPOPEDIDO`,`ESTADO`,`TOTALIMPORTE`,`IDUSUARIO`,`VALORCOMISION`,`VALORGARANTIA`,`CODIGO`,`CON_REPOSICION`,`IDMOVIMIENTO`,`PEDIDOSREPOSICION`) VALUES (2,NULL,NULL,NULL,'2015-03-24','2015-03-24',NULL,'prueba',0.2,7.7,NULL,0,NULL,8,1,'ENTREGADO',140,NULL,0,0,1,NULL,2,NULL),(4,NULL,NULL,NULL,'2015-03-25','2015-03-25',NULL,'gjgghj',1.5,7.2,NULL,0,NULL,9,1,'ENTREGADO',413,NULL,0,0,3,NULL,3,NULL),(5,NULL,NULL,NULL,'2015-03-25','2015-03-25',NULL,NULL,0.3,0.4,NULL,0,NULL,7,1,'PREPARAR',483,NULL,0,0,4,NULL,5,NULL),(6,NULL,NULL,NULL,'2015-03-27','2015-03-27',NULL,NULL,0,0,NULL,0,NULL,12,1,'PREPARAR',595,NULL,0,0,5,NULL,6,NULL),(7,NULL,NULL,NULL,'2015-03-28','2015-03-27',NULL,NULL,0,0,NULL,0,NULL,11,1,'PREPARAR',560,NULL,0,0,6,NULL,7,NULL),(8,NULL,NULL,NULL,'2015-03-28','2015-03-27',NULL,NULL,0,0,NULL,0,NULL,10,1,'PREPARAR',406,NULL,0,0,7,NULL,8,NULL),(9,NULL,NULL,NULL,'2015-03-30','2015-03-30',NULL,NULL,0,0,NULL,0,NULL,13,1,'PREPARAR',420,NULL,0,0,8,NULL,4,NULL),(10,NULL,NULL,NULL,'2015-04-01','2015-04-01',NULL,NULL,1.5,7.2,NULL,0,NULL,9,1,'PREPARAR',147,NULL,0,0,9,0,NULL,NULL),(11,NULL,NULL,NULL,'2015-04-01','2015-04-01',NULL,NULL,0,0,NULL,0,NULL,11,1,'ENTREGADO',21,NULL,0,0,10,NULL,NULL,NULL),(12,NULL,NULL,NULL,'2015-04-01','2015-04-01',NULL,NULL,0,0,NULL,0,NULL,13,1,'ENTREGADO',63,NULL,0,0,11,NULL,9,NULL),(13,NULL,NULL,NULL,'2015-04-01','2015-04-01',NULL,NULL,0,0,NULL,0,NULL,13,1,'PREPARAR',14,NULL,0,0,12,NULL,10,NULL),(14,NULL,NULL,NULL,'2015-04-02','2015-04-02',NULL,NULL,0,0,NULL,0,NULL,13,1,'PREPARAR',35,NULL,0,0,13,NULL,11,NULL),(15,NULL,NULL,NULL,'2015-04-02','2015-04-02',NULL,NULL,0,0,NULL,0,NULL,13,1,'PREPARAR',14,NULL,0,0,14,NULL,12,NULL),(16,NULL,NULL,NULL,'2015-04-03','2015-04-02',NULL,NULL,0,0,NULL,0,NULL,11,1,'PREPARAR',14,NULL,0,0,15,0,NULL,NULL),(17,NULL,NULL,NULL,'2015-04-09','2015-04-02',NULL,NULL,0,0,NULL,0,NULL,11,1,'PREPARAR',14,NULL,0,0,16,0,NULL,NULL),(18,NULL,NULL,NULL,'2015-04-02','2015-04-02',NULL,NULL,0,0,NULL,0,NULL,10,1,'PREPARAR',266,NULL,0,0,17,0,14,NULL),(19,NULL,NULL,NULL,'2015-04-02','2015-04-02',NULL,NULL,0.3,0.4,NULL,0,NULL,7,1,'PREPARAR',483,NULL,0,0,18,0,15,NULL),(20,NULL,NULL,NULL,'2015-04-02','2015-04-02',NULL,NULL,0.3,0.4,NULL,0,NULL,7,1,'PREPARAR',77,NULL,0,0,19,0,16,NULL),(21,NULL,NULL,NULL,'2015-04-03','2015-04-02',NULL,NULL,0.3,0.4,NULL,0,NULL,7,1,'PREPARAR',406,NULL,0,0,20,0,17,NULL),(22,NULL,NULL,NULL,'2015-04-02','2015-04-02',NULL,NULL,0.3,0.4,NULL,0,NULL,7,1,'ENTREGADO',651,NULL,0,0,21,0,NULL,NULL),(23,NULL,NULL,NULL,'2015-04-04','2015-04-03',NULL,NULL,7,0,NULL,0,NULL,18,1,'ENTREGADO',490,NULL,0,0,22,1,18,NULL),(24,NULL,NULL,NULL,'2015-04-04','2015-04-03',NULL,NULL,7,0,NULL,0,NULL,18,1,'PENDIENTE',14,NULL,0,0,23,1,NULL,NULL),(25,NULL,NULL,NULL,'2015-04-10','2015-04-03',NULL,NULL,7,0,NULL,0,NULL,18,1,'PENDIENTE',35,NULL,0,0,24,1,NULL,NULL),(26,NULL,NULL,NULL,'2015-04-03','2015-04-03',NULL,NULL,0,0,NULL,0,NULL,19,1,'PENDIENTE',399,NULL,0,0,25,0,NULL,26),(27,NULL,NULL,NULL,'2015-04-03','2015-04-03',NULL,NULL,0,0,NULL,0,NULL,12,1,'ENTREGADO',392,NULL,0,0,26,0,19,NULL),(28,NULL,NULL,NULL,'2015-04-04','2015-04-04',NULL,'asdfsdf',0,0,NULL,0,NULL,12,1,'ANULADO',35,NULL,0,0,27,0,NULL,NULL),(29,NULL,NULL,NULL,'2015-04-04','2015-04-04',NULL,NULL,0,0,NULL,0,NULL,11,1,'ENTREGADO',546,NULL,0,0,28,0,20,NULL),(30,NULL,NULL,NULL,'2015-04-05','2015-04-04',NULL,'',0.5,1.2,NULL,0,NULL,17,1,'ENTREGADO',490,NULL,0,0,29,0,NULL,NULL),(31,NULL,NULL,NULL,'2015-04-04','2015-04-04',NULL,'prueba',0.5,1.2,NULL,0,NULL,17,1,'ANULADO',329,NULL,0,0,30,0,NULL,NULL);

/*Table structure for table `persona` */

DROP TABLE IF EXISTS `persona`;

CREATE TABLE `persona` (
  `IDPERSONA` BIGINT(20) NOT NULL,
  `AM` VARCHAR(255) DEFAULT NULL,
  `AP` VARCHAR(255) DEFAULT NULL,
  `CEM_COD` VARCHAR(255) DEFAULT NULL,
  `EST_CIVIL` VARCHAR(255) DEFAULT NULL,
  `FECHA_NAC` DATE DEFAULT NULL,
  `NOM` VARCHAR(255) DEFAULT NULL,
  `NRO_DOC` VARCHAR(255) DEFAULT NULL,
  `OCU_COD` VARCHAR(255) DEFAULT NULL,
  `SEXO` VARCHAR(255) DEFAULT NULL,
  `SIS_COD` VARCHAR(255) DEFAULT NULL,
  `TDO_COD` VARCHAR(255) DEFAULT NULL,
  `TIPO_PERSONA` VARCHAR(45) DEFAULT NULL,
  `DIRECCION` VARCHAR(100) DEFAULT NULL,
  `TELEFONO` INT(11) DEFAULT NULL,
  `NIT` VARCHAR(20) DEFAULT NULL,
  `CODIGOCLIENTE` VARCHAR(10) DEFAULT NULL,
  `RAZONSOCIAL` VARCHAR(45) DEFAULT NULL,
  `OBSERVACION` VARCHAR(100) DEFAULT NULL,
  `IDRETENCION` BIGINT(20) DEFAULT NULL,
  `IDTIPOCLIENTE` BIGINT(20) DEFAULT NULL,
  `IDDEPARTAMENTO` BIGINT(24) DEFAULT NULL,
  `PORCENTAJECOMISION` DOUBLE DEFAULT NULL,
  `PORCENTAJEGARANTIA` DOUBLE DEFAULT NULL,
  `IDTERRITORIOTRABAJO` BIGINT(24) DEFAULT NULL,
  `con_factura` INT(11) NOT NULL,
  PRIMARY KEY (`IDPERSONA`),
  UNIQUE KEY `IDPERSONA_UNIQUE` (`IDPERSONA`),
  KEY `FK_PERSONAS_TIPOCLIENTE_idx` (`IDTIPOCLIENTE`),
  KEY `FK_PERSONAS_DEPARTAMENTO_idx` (`IDDEPARTAMENTO`),
  KEY `FK_PERSONA_TERRITORIO_idx` (`IDTERRITORIOTRABAJO`),
  CONSTRAINT `FK_PERSONAS_DEPARTAMENTO` FOREIGN KEY (`IDDEPARTAMENTO`) REFERENCES `departamento` (`IDDEPARTAMENTO`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PERSONAS_TIPOCLIENTE` FOREIGN KEY (`IDTIPOCLIENTE`) REFERENCES `tipocliente` (`IDTIPOCLIENTE`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PERSONA_TERRITORIO` FOREIGN KEY (`IDTERRITORIOTRABAJO`) REFERENCES `territoriotrabajo` (`IDTERRITORIOTRABAJO`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `persona` */

INSERT  INTO `persona`(`IDPERSONA`,`AM`,`AP`,`CEM_COD`,`EST_CIVIL`,`FECHA_NAC`,`NOM`,`NRO_DOC`,`OCU_COD`,`SEXO`,`SIS_COD`,`TDO_COD`,`TIPO_PERSONA`,`DIRECCION`,`TELEFONO`,`NIT`,`CODIGOCLIENTE`,`RAZONSOCIAL`,`OBSERVACION`,`IDRETENCION`,`IDTIPOCLIENTE`,`IDDEPARTAMENTO`,`PORCENTAJECOMISION`,`PORCENTAJEGARANTIA`,`IDTERRITORIOTRABAJO`,`con_factura`) VALUES (1,'Fernandez','Loza',NULL,NULL,NULL,'Pedro','12312',NULL,'MASCULINO',NULL,NULL,'distribuidor','Calle Constantino',NULL,'',NULL,'',NULL,NULL,NULL,NULL,NULL,0,NULL,1),(2,'Rancio','Villa',NULL,NULL,NULL,'Marcela','45678889',NULL,'FEMENINO',NULL,NULL,'distribuidor','Calle Aparicio',NULL,'',NULL,'',NULL,NULL,NULL,NULL,NULL,0,NULL,0),(6,'','',NULL,NULL,NULL,'',NULL,NULL,'',NULL,NULL,'institucion','calle Lapaz',23466,'432',NULL,'institucion',NULL,NULL,1,NULL,4,0,1,1),(7,'','',NULL,NULL,NULL,'','432423',NULL,'FEMENINO',NULL,NULL,'institucion','Calle cerca',234324,'23432',NULL,'IC norte',NULL,NULL,1,NULL,0.3,0.4,1,1),(8,'Zapatero','Rodriguez',NULL,NULL,NULL,'Marcela','1233',NULL,'MASCULINO',NULL,NULL,'cliente','Calle Ramirez',433255,'23434',NULL,'',NULL,NULL,5,NULL,0.2,7.7,3,0),(9,'Loza','Ramires',NULL,NULL,NULL,'Pedro','4334434',NULL,'MASCULINO',NULL,NULL,'cliente','Calle ',3455,'',NULL,'',NULL,NULL,2,NULL,1.5,7.2,1,0),(10,'Vallejos','Claros',NULL,NULL,NULL,'Samuel','344355',NULL,'MASCULINO',NULL,NULL,'cliente','Calle Sucre',4435643,'34344',NULL,'',NULL,NULL,1,NULL,0,0,1,1),(11,'Zico','Zapatero',NULL,NULL,NULL,'Maria','435666',NULL,'FEMENINO',NULL,NULL,'cliente','Calle Carmen ',4465565,'3434551',NULL,'',NULL,NULL,2,NULL,0,0,1,0),(12,'Solis','Ramirez',NULL,NULL,NULL,'Miguel','4354',NULL,'MASCULINO',NULL,NULL,'cliente','Calle Zapatero',234356,'3434567',NULL,'',NULL,NULL,1,NULL,0,0,1,0),(13,'','',NULL,NULL,NULL,'',NULL,NULL,'',NULL,NULL,'institucion','Calle Tantos',443255,'34355',NULL,'Ilva',NULL,NULL,5,NULL,0,0,1,1),(14,'Fernandez','Salvador',NULL,NULL,NULL,'Pedro','2324',NULL,'MASCULINO',NULL,NULL,'distribuidor','calle ',34355,'43434',NULL,'',NULL,NULL,NULL,NULL,0,0,NULL,0),(15,'Davalos','Aranivar',NULL,NULL,NULL,'Claudia','4346657',NULL,'FEMENINO',NULL,NULL,'distribuidor','Calle',446565,'455767',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,0),(16,'Davila','Gonzales',NULL,NULL,NULL,'Tania','54657657',NULL,'FEMENINO',NULL,NULL,'distribuidor','Calle Davalos',4457675,'324545',NULL,'',NULL,NULL,NULL,NULL,0,0,NULL,0),(17,'','',NULL,NULL,NULL,'',NULL,NULL,'',NULL,NULL,'institucion','Calle Antofagasta',434354,'543646',NULL,'Elfec',NULL,NULL,5,NULL,0.5,1.2,5,0),(18,'Maldonado','Ramirez',NULL,NULL,NULL,'Marcela','432545',NULL,'FEMENINO',NULL,NULL,'cliente','Calle Quiñoz',343254,'',NULL,'',NULL,NULL,1,NULL,7,0,3,0),(19,'Alvares','Flores',NULL,NULL,NULL,'Tania','43543',NULL,'FEMENINO',NULL,NULL,'cliente','Calle',3454,'35434345',NULL,'',NULL,NULL,1,NULL,0,0,3,1);

/*Table structure for table `promocion` */

DROP TABLE IF EXISTS `promocion`;

CREATE TABLE `promocion` (
  `IDPROMOCION` BIGINT(24) NOT NULL,
  `NOMBRE` VARCHAR(50) DEFAULT NULL,
  `FECHAINICIO` DATE DEFAULT NULL,
  `FECHAFIN` DATE DEFAULT NULL,
  `ESTADO` VARCHAR(15) DEFAULT NULL,
  `TOTAL` DOUBLE DEFAULT NULL,
  PRIMARY KEY (`IDPROMOCION`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Table structure for table `territoriotrabajo` */

DROP TABLE IF EXISTS `territoriotrabajo`;

CREATE TABLE `territoriotrabajo` (
  `IDTERRITORIOTRABAJO` BIGINT(24) NOT NULL,
  `NOMBRE` VARCHAR(100) DEFAULT NULL,
  `PAIS` VARCHAR(50) DEFAULT NULL,
  `DESCRIPCION` VARCHAR(50) DEFAULT NULL,
  `IDDISTRIBUIDOR` BIGINT(20) DEFAULT NULL,
  `IDDEPARTAMENTO` BIGINT(24) DEFAULT NULL,
  PRIMARY KEY (`IDTERRITORIOTRABAJO`),
  KEY `fk_territorio_departamento_idx` (`IDDEPARTAMENTO`),
  KEY `fk_territorio_persona_idx` (`IDDISTRIBUIDOR`),
  CONSTRAINT `fk_territorio_departamento` FOREIGN KEY (`IDDEPARTAMENTO`) REFERENCES `departamento` (`IDDEPARTAMENTO`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_territorio_persona` FOREIGN KEY (`IDDISTRIBUIDOR`) REFERENCES `persona` (`IDPERSONA`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `territoriotrabajo` */

INSERT  INTO `territoriotrabajo`(`IDTERRITORIOTRABAJO`,`NOMBRE`,`PAIS`,`DESCRIPCION`,`IDDISTRIBUIDOR`,`IDDEPARTAMENTO`) VALUES (1,'Punata','Bolivia','descripcion',2,1),(3,'Cochabamba Central','Bolivia','',1,1),(4,'Vinto','Bolivia','',1,1),(5,'Quillacollo','Bolivia','nose',14,1);

/*Table structure for table `tipocliente` */

DROP TABLE IF EXISTS `tipocliente`;

CREATE TABLE `tipocliente` (
  `IDTIPOCLIENTE` BIGINT(20) NOT NULL,
  `NOMBRE` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`IDTIPOCLIENTE`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `tipocliente` */

INSERT  INTO `tipocliente`(`IDTIPOCLIENTE`,`NOMBRE`) VALUES (1,'Tienda'),(2,'Supermercado'),(3,'Micromercado'),(4,'Desayuno Escolar'),(5,'Agencia');

/*Table structure for table `tipopedido` */

DROP TABLE IF EXISTS `tipopedido`;

CREATE TABLE `tipopedido` (
  `IDTIPOPEDIDO` BIGINT(20) NOT NULL,
  `NOMBRE` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`IDTIPOPEDIDO`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `tipopedido` */

INSERT  INTO `tipopedido`(`IDTIPOPEDIDO`,`NOMBRE`) VALUES (1,'PEDIDO'),(2,'DEGUSACION'),(3,'REFRIGERIO');

/*Table structure for table `ventaarticulo` */

DROP TABLE IF EXISTS `ventaarticulo`;

CREATE TABLE `ventaarticulo` (
  `IDVENTAARTICULO` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `PRECIO` DOUBLE DEFAULT NULL,
  `NO_CIA` VARCHAR(255) DEFAULT NULL,
  `COD_ART` VARCHAR(255) DEFAULT NULL,
  `IDPAQUETE` BIGINT(20) DEFAULT NULL,
  `IDPROMOCION` BIGINT(24) DEFAULT NULL,
  PRIMARY KEY (`IDVENTAARTICULO`),
  KEY `FK_ventaarticulo_COD_ART` (`COD_ART`,`NO_CIA`),
  KEY `FK_ventaarticulo_paquete_idx` (`IDPAQUETE`),
  KEY `FK_ventaarticulo_promocion_idx` (`IDPROMOCION`),
  CONSTRAINT `FK_ventaarticulo_COD_ART` FOREIGN KEY (`COD_ART`, `NO_CIA`) REFERENCES `inv_articulos` (`COD_ART`, `NO_CIA`),
  CONSTRAINT `FK_ventaarticulo_paquete` FOREIGN KEY (`IDPAQUETE`) REFERENCES `ventaarticulo` (`IDVENTAARTICULO`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ventaarticulo_promocion` FOREIGN KEY (`IDPROMOCION`) REFERENCES `promocion` (`IDPROMOCION`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

/*Data for the table `ventaarticulo` */

INSERT  INTO `ventaarticulo`(`IDVENTAARTICULO`,`PRECIO`,`NO_CIA`,`COD_ART`,`IDPAQUETE`,`IDPROMOCION`) VALUES (2,7,'01','118',NULL,NULL),(3,7,'01','119',NULL,NULL),(4,7,'01','120',NULL,NULL),(5,7,'01','121',NULL,NULL),(6,7,'01','122',NULL,NULL),(7,7,'01','123',NULL,NULL),(8,7,'01','124',NULL,NULL),(9,7,'01','125',NULL,NULL),(10,7,'01','126',NULL,NULL),(11,7,'01','127',NULL,NULL),(12,7,'01','128',NULL,NULL),(13,7,'01','129',NULL,NULL),(14,7,'01','130',NULL,NULL),(15,7,'01','131',NULL,NULL),(16,7,'01','132',NULL,NULL),(17,7,'01','133',NULL,NULL),(18,7,'01','134',NULL,NULL),(19,7,'01','135',NULL,NULL),(20,7,'01','136',NULL,NULL),(21,7,'01','137',NULL,NULL),(22,7,'01','138',NULL,NULL),(23,7,'01','139',NULL,NULL),(24,7,'01','140',NULL,NULL),(25,7,'01','141',NULL,NULL),(26,7,'01','142',NULL,NULL),(27,7,'01','143',NULL,NULL),(28,7,'01','144',NULL,NULL),(29,7,'01','145',NULL,NULL),(30,7,'01','146',NULL,NULL),(31,7,'01','147',NULL,NULL),(32,7,'01','148',NULL,NULL),(33,7,'01','149',NULL,NULL),(34,7,'01','150',NULL,NULL),(35,7,'01','151',NULL,NULL);

/*Table structure for table `ventacliente` */

DROP TABLE IF EXISTS `ventacliente`;

CREATE TABLE `ventacliente` (
  `IDVENTACLIENTE` BIGINT(24) NOT NULL,
  `PRECIOESPECIAL` DOUBLE DEFAULT NULL,
  `IDVENTAARTICULO` BIGINT(24) NOT NULL,
  `IDPERSONA` BIGINT(20) NOT NULL,
  PRIMARY KEY (`IDVENTACLIENTE`),
  KEY `VentaCliente_CLIENTE_FK` (`IDPERSONA`),
  KEY `VentaCliente_VENTAARTICULO_FK` (`IDVENTAARTICULO`),
  CONSTRAINT `VentaCliente_CLIENTE_FK` FOREIGN KEY (`IDPERSONA`) REFERENCES `persona` (`IDPERSONA`),
  CONSTRAINT `VentaCliente_VENTAARTICULO_FK` FOREIGN KEY (`IDVENTAARTICULO`) REFERENCES `ventaarticulo` (`IDVENTAARTICULO`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `ventacliente` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;