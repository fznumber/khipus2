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

/*Table structure for table `tipocliente` */

DROP TABLE IF EXISTS `tipocliente`;

CREATE TABLE `tipocliente` (
  `IDTIPOCLIENTE` BIGINT(20) NOT NULL,
  `NOMBRE` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`IDTIPOCLIENTE`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `tipocliente` */

INSERT  INTO `tipocliente`(`IDTIPOCLIENTE`,`NOMBRE`) VALUES (1,'Tienda'),(2,'Supermercado'),(3,'Micromercado'),(4,'Desayuno Escolar'),(5,'Agencia');

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
  KEY `fk_territorio_CLIENTE_idx` (`IDDISTRIBUIDOR`),
  CONSTRAINT `fk_territorio_departamento` FOREIGN KEY (`IDDEPARTAMENTO`) REFERENCES `departamento` (`IDDEPARTAMENTO`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=utf8;

  ALTER TABLE territoriotrabajo ADD CONSTRAINT fk_territori_cliente FOREIGN KEY ( IDDISTRIBUIDOR ) REFERENCES PERSONACLIENTE ( IDPERSONACLIENTE ) ;

/*Data for the table `territoriotrabajo` */

INSERT  INTO `territoriotrabajo`(`IDTERRITORIOTRABAJO`,`NOMBRE`,`PAIS`,`DESCRIPCION`,`IDDISTRIBUIDOR`,`IDDEPARTAMENTO`) VALUES (1,'Punata','Bolivia','descripcion',2,1),(3,'Cochabamba Central','Bolivia','',1,1),(4,'Vinto','Bolivia','',1,1),(5,'Quillacollo','Bolivia','nose',14,1);


/*Table structure for table `	` */

DROP TABLE IF EXISTS `PERSONACLIENTE`;

CREATE TABLE `PERSONACLIENTE` (
  `IDPERSONACLIENTE` BIGINT(20) NOT NULL,
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
  PRIMARY KEY (`IDPERSONACLIENTE`),
  UNIQUE KEY `IDCLIENTE_UNIQUE` (`IDPERSONACLIENTE`),
  KEY `FK_CLIENTES_TIPOCLIENTE_idx` (`IDTIPOCLIENTE`),
  KEY `FK_CLIENTES_DEPARTAMENTO_idx` (`IDDEPARTAMENTO`),
  KEY `FK_CLIENTE_TERRITORIO_idx` (`IDTERRITORIOTRABAJO`),
  CONSTRAINT `FK_CLIENTES_DEPARTAMENTO` FOREIGN KEY (`IDDEPARTAMENTO`) REFERENCES `departamento` (`IDDEPARTAMENTO`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_CLIENTES_TIPOCLIENTE` FOREIGN KEY (`IDTIPOCLIENTE`) REFERENCES `tipocliente` (`IDTIPOCLIENTE`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_CLIENTE_TERRITORIO` FOREIGN KEY (`IDTERRITORIOTRABAJO`) REFERENCES `territoriotrabajo` (`IDTERRITORIOTRABAJO`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `CLIENTE` */

INSERT  INTO `PERSONACLIENTE`(`IDPERSONACLIENTE`,`AM`,`AP`,`CEM_COD`,`EST_CIVIL`,`FECHA_NAC`,`NOM`,`NRO_DOC`,`OCU_COD`,`SEXO`,`SIS_COD`,`TDO_COD`,`TIPO_PERSONA`,`DIRECCION`,`TELEFONO`,`NIT`,`CODIGOCLIENTE`,`RAZONSOCIAL`,`OBSERVACION`,`IDRETENCION`,`IDTIPOCLIENTE`,`IDDEPARTAMENTO`,`PORCENTAJECOMISION`,`PORCENTAJEGARANTIA`,`IDTERRITORIOTRABAJO`,`con_factura`) VALUES (1,'Fernandez','Loza',NULL,NULL,NULL,'Pedro','12312',NULL,'MASCULINO',NULL,NULL,'distribuidor','Calle Constantino',NULL,'',NULL,'',NULL,NULL,NULL,NULL,NULL,0,NULL,1),(2,'Rancio','Villa',NULL,NULL,NULL,'Marcela','45678889',NULL,'FEMENINO',NULL,NULL,'distribuidor','Calle Aparicio',NULL,'',NULL,'',NULL,NULL,NULL,NULL,NULL,0,NULL,0),(6,'','',NULL,NULL,NULL,'',NULL,NULL,'',NULL,NULL,'institucion','calle Lapaz',23466,'432',NULL,'institucion',NULL,NULL,1,NULL,4,0,1,1),(7,'','',NULL,NULL,NULL,'','432423',NULL,'FEMENINO',NULL,NULL,'institucion','Calle cerca',234324,'23432',NULL,'IC norte',NULL,NULL,1,NULL,0.3,0.4,1,1),(8,'Zapatero','Rodriguez',NULL,NULL,NULL,'Marcela','1233',NULL,'MASCULINO',NULL,NULL,'cliente','Calle Ramirez',433255,'23434',NULL,'',NULL,NULL,5,NULL,0.2,7.7,3,0),(9,'Loza','Ramires',NULL,NULL,NULL,'Pedro','4334434',NULL,'MASCULINO',NULL,NULL,'cliente','Calle ',3455,'',NULL,'',NULL,NULL,2,NULL,1.5,7.2,1,0),(10,'Vallejos','Claros',NULL,NULL,NULL,'Samuel','344355',NULL,'MASCULINO',NULL,NULL,'cliente','Calle Sucre',4435643,'34344',NULL,'',NULL,NULL,1,NULL,0,0,1,1),(11,'Zico','Zapatero',NULL,NULL,NULL,'Maria','435666',NULL,'FEMENINO',NULL,NULL,'cliente','Calle Carmen ',4465565,'3434551',NULL,'',NULL,NULL,2,NULL,0,0,1,0),(12,'Solis','Ramirez',NULL,NULL,NULL,'Miguel','4354',NULL,'MASCULINO',NULL,NULL,'cliente','Calle Zapatero',234356,'3434567',NULL,'',NULL,NULL,1,NULL,0,0,1,0),(13,'','',NULL,NULL,NULL,'',NULL,NULL,'',NULL,NULL,'institucion','Calle Tantos',443255,'34355',NULL,'Ilva',NULL,NULL,5,NULL,0,0,1,1),(14,'Fernandez','Salvador',NULL,NULL,NULL,'Pedro','2324',NULL,'MASCULINO',NULL,NULL,'distribuidor','calle ',34355,'43434',NULL,'',NULL,NULL,NULL,NULL,0,0,NULL,0),(15,'Davalos','Aranivar',NULL,NULL,NULL,'Claudia','4346657',NULL,'FEMENINO',NULL,NULL,'distribuidor','Calle',446565,'455767',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,0),(16,'Davila','Gonzales',NULL,NULL,NULL,'Tania','54657657',NULL,'FEMENINO',NULL,NULL,'distribuidor','Calle Davalos',4457675,'324545',NULL,'',NULL,NULL,NULL,NULL,0,0,NULL,0),(17,'','',NULL,NULL,NULL,'',NULL,NULL,'',NULL,NULL,'institucion','Calle Antofagasta',434354,'543646',NULL,'Elfec',NULL,NULL,5,NULL,0.5,1.2,5,0),(18,'Maldonado','Ramirez',NULL,NULL,NULL,'Marcela','432545',NULL,'FEMENINO',NULL,NULL,'cliente','Calle Quiñoz',343254,'',NULL,'',NULL,NULL,1,NULL,7,0,3,0),(19,'Alvares','Flores',NULL,NULL,NULL,'Tania','43543',NULL,'FEMENINO',NULL,NULL,'cliente','Calle',3454,'35434345',NULL,'',NULL,NULL,1,NULL,0,0,3,1);

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

/*Table structure for table `tipopedido` */

DROP TABLE IF EXISTS `tipopedido`;

CREATE TABLE `tipopedido` (
  `IDTIPOPEDIDO` BIGINT(20) NOT NULL,
  `NOMBRE` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`IDTIPOPEDIDO`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `tipopedido` */

INSERT  INTO `tipopedido`(`IDTIPOPEDIDO`,`NOMBRE`) VALUES (1,'PEDIDO'),(2,'DEGUSACION'),(3,'REFRIGERIO');

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
  KEY `FK_pedidos_usuario_idx` (`IDUSUARIO`),
  KEY `fk_pedidos_cliente_idx` (`IDCLIENTE`),
  KEY `fk_pedidos_movimiento_idx` (`IDMOVIMIENTO`),
  KEY `fk_pedidos_reposicion_idx` (`PEDIDOSREPOSICION`),  
  CONSTRAINT `FK_pedidos_IDTIPOPEDIDO` FOREIGN KEY (`IDTIPOPEDIDO`) REFERENCES `tipopedido` (`IDTIPOPEDIDO`),
  CONSTRAINT `FK_pedidos_usuario` FOREIGN KEY (`IDUSUARIO`) REFERENCES `usuario` (`IDUSUARIO`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedidos_cliente` FOREIGN KEY (`IDCLIENTE`) REFERENCES `PERSONACLIENTE` (`IDPERSONACLIENTE`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedidos_movimiento` FOREIGN KEY (`IDMOVIMIENTO`) REFERENCES `movimiento` (`IDMOVIMIENTO`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedidos_reposicion` FOREIGN KEY (`PEDIDOSREPOSICION`) REFERENCES `pedidos` (`IDPEDIDOS`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Table structure for table `articulos_pedido` */

DROP TABLE IF EXISTS `articulos_pedido`;

CREATE TABLE `articulos_pedido` (
  `IDARTICULOSPEDIDO` BIGINT(20) NOT NULL,
  `CAJA` BIGINT(20) DEFAULT NULL,
  `CANTIDAD` INT(11) DEFAULT NULL,
  `COD_ALM` VARCHAR(255) DEFAULT NULL,
  `cod_art` VARCHAR(6) DEFAULT NULL,
  `no_cia` VARCHAR(2) DEFAULT NULL,
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
  KEY `FK_ARTICULOS_PEDIDO_PEDIDOS_idx` (`IDPEDIDOS`),  
  CONSTRAINT `FK_ARTICULOS_PEDIDO_PEDIDOS` FOREIGN KEY (`IDPEDIDOS`) REFERENCES `pedidos` (`IDPEDIDOS`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=utf8;

ALTER TABLE articulos_pedido ADD CONSTRAINT fk_artpedido_invart FOREIGN KEY ( no_cia,cod_art ) REFERENCES inv_articulos ( no_cia,cod_art );

/*Table structure for table `codigopedidosecuencia` */

DROP TABLE IF EXISTS `codigopedidosecuencia`;

CREATE TABLE `codigopedidosecuencia` (
  `secuencia` BIGINT(20) NOT NULL,
  PRIMARY KEY (`secuencia`),
  UNIQUE KEY `unique_secuencia` (`secuencia`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


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

/*Table structure for table `ventaarticulo` */

DROP TABLE IF EXISTS `ventaarticulo`;

CREATE TABLE `ventaarticulo` (
  `IDVENTAARTICULO` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `PRECIO` DOUBLE DEFAULT NULL,
  `no_cia` VARCHAR(2) DEFAULT NULL,
  `cod_art` VARCHAR(6) DEFAULT NULL,
  `IDPAQUETE` BIGINT(20) DEFAULT NULL,
  `IDPROMOCION` BIGINT(24) DEFAULT NULL,
  PRIMARY KEY (`IDVENTAARTICULO`),  
  KEY `FK_ventaarticulo_paquete_idx` (`IDPAQUETE`),
  KEY `FK_ventaarticulo_promocion_idx` (`IDPROMOCION`),  
  CONSTRAINT `FK_ventaarticulo_paquete` FOREIGN KEY (`IDPAQUETE`) REFERENCES `ventaarticulo` (`IDVENTAARTICULO`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ventaarticulo_promocion` FOREIGN KEY (`IDPROMOCION`) REFERENCES `promocion` (`IDPROMOCION`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

ALTER TABLE ventaarticulo ADD CONSTRAINT fk_ventart_invart FOREIGN KEY ( no_cia,cod_art ) REFERENCES inv_articulos ( no_cia,cod_art );
/*Data for the table `ventaarticulo` */

INSERT  INTO `ventaarticulo`(`IDVENTAARTICULO`,`PRECIO`,`NO_CIA`,`COD_ART`,`IDPAQUETE`,`IDPROMOCION`) VALUES (2,7,'01','118',NULL,NULL),(3,7,'01','119',NULL,NULL),(4,7,'01','120',NULL,NULL),(5,7,'01','121',NULL,NULL),(6,7,'01','122',NULL,NULL),(7,7,'01','123',NULL,NULL),(8,7,'01','124',NULL,NULL),(9,7,'01','125',NULL,NULL),(10,7,'01','126',NULL,NULL),(11,7,'01','127',NULL,NULL),(12,7,'01','128',NULL,NULL),(13,7,'01','129',NULL,NULL),(14,7,'01','130',NULL,NULL),(15,7,'01','131',NULL,NULL),(16,7,'01','132',NULL,NULL),(17,7,'01','133',NULL,NULL),(18,7,'01','134',NULL,NULL),(19,7,'01','135',NULL,NULL),(20,7,'01','136',NULL,NULL),(21,7,'01','137',NULL,NULL),(22,7,'01','138',NULL,NULL),(23,7,'01','139',NULL,NULL),(24,7,'01','140',NULL,NULL),(25,7,'01','141',NULL,NULL),(26,7,'01','142',NULL,NULL),(27,7,'01','143',NULL,NULL),(28,7,'01','144',NULL,NULL),(29,7,'01','145',NULL,NULL),(30,7,'01','146',NULL,NULL),(31,7,'01','147',NULL,NULL),(32,7,'01','148',NULL,NULL),(33,7,'01','149',NULL,NULL),(34,7,'01','150',NULL,NULL),(35,7,'01','151',NULL,NULL);

/*Table structure for table `ventacliente` */

DROP TABLE IF EXISTS `ventacliente`;

CREATE TABLE `ventacliente` (
  `IDVENTACLIENTE` BIGINT(24) NOT NULL,
  `PRECIOESPECIAL` DOUBLE DEFAULT NULL,
  `IDVENTAARTICULO` BIGINT(24) NOT NULL,
  `IDCLIENTE` BIGINT(20) NOT NULL,
  PRIMARY KEY (`IDVENTACLIENTE`),
  KEY `VentaCliente_CLIENTE_FK` (`IDCLIENTE`),
  KEY `VentaCliente_VENTAARTICULO_FK` (`IDVENTAARTICULO`),
  CONSTRAINT `VentaCliente_CLIENTE_FK` FOREIGN KEY (`IDCLIENTE`) REFERENCES `PERSONACLIENTE` (`IDPERSONACLIENTE`),
  CONSTRAINT `VentaCliente_VENTAARTICULO_FK` FOREIGN KEY (`IDVENTAARTICULO`) REFERENCES `ventaarticulo` (`IDVENTAARTICULO`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `ventacliente` */
