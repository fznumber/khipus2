
CREATE
  TABLE TIPODOC
  (
    IDTIPODOC   BIGINT(20) ,
    NOMBRE      VARCHAR (5) ,
    DESCRIPCION VARCHAR (512),
    CONSTRAINT TIPODOC_PK PRIMARY KEY ( IDTIPODOC )
  );
  
  ALTER TABLE pedidos ADD TIENEFACTURA BOOLEAN NULL;
  ALTER TABLE movimiento ADD CODIGO_QR VARCHAR(100) NOT NULL;
  ALTER TABLE pago CHANGE monto pago DOUBLE;   
  UPDATE `pedidos` SET contabilizado = 0 ;