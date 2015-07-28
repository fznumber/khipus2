CREATE
  TABLE TIPODOC
  (
    IDTIPODOC   BIGINT(20) ,
    NOMBRE      VARCHAR (5) ,
    DESCRIPCION VARCHAR (512),
    CONSTRAINT TIPODOC_PK PRIMARY KEY ( IDTIPODOC )
  );
  
  ALTER TABLE khipus.pedidos ADD TIENEFACTURA BOOLEAN NULL;
  ALTER TABLE khipus.movimiento ADD CODIGO_QR VARCHAR(100) NOT NULL;
  ALTER TABLE khipus.pago CHANGE monto pago DOUBLE;   
  UPDATE `pedidos` SET contabilizado = 0 ;
  ALTER TABLE sf_tmpenc ADD NOMBRECLIENTE VARCHAR(100) NULL;
  ALTER TABLE sf_tmpenc ADD DEBE DOUBLE NULL;
  ALTER TABLE sf_tmpenc ADD HABER DOUBLE NULL;
  ALTER TABLE sf_tmpenc ADD IDPERSONACLIENTE BIGINT(20) NULL;
  ALTER TABLE ventadirecta CHANGE CODIGO CODIGO BIGINT;
  ALTER TABLE `khipus`.`articulos_pedido` CHANGE COLUMN `tipo` `tipo` VARCHAR(30) NULL ;