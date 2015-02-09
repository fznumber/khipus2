USE EOS;
CREATE TABLE ARTICULOS_PAQUETE
  (
    IDARTICULOSPAQUETE BIGINT(24) NOT NULL ,
    IDPAQUETES         BIGINT(24) ,
    ID_CUENTA          BIGINT (10) ,
    COD_ART            VARCHAR (6) ,
    NO_CIA             VARCHAR (2) ,
    CANTIDAD           BIGINT (10) ,
    COD_ALM            VARCHAR (6) ,
    PRECIO             double (10,2) ,
    TOTAL              double (10,2) ,
    IDCLIENTE          BIGINT(24) NOT NULL
  ) ;
ALTER TABLE ARTICULOS_PAQUETE ADD CONSTRAINT ARTICULOS_PAQUETE_PK PRIMARY KEY ( IDARTICULOSPAQUETE ) ;
CREATE TABLE ARTICULOS_PEDIDO
  (
    IDARTICULOSPEDIDO BIGINT(24) NOT NULL ,
    ID_CUENTA         BIGINT (10) NOT NULL ,
    COD_ART           VARCHAR (6) NOT NULL ,
    NO_CIA            VARCHAR (2) NOT NULL ,
    PEDIDO            BIGINT(24) NOT NULL ,
    ID                VARCHAR (20) NOT NULL ,
    ID1               BIGINT (10) NOT NULL ,
    CANTIDAD          BIGINT (10) ,
    COD_ALM           VARCHAR (6) ,
    PRECIO            double (10,2) ,
    REPOSICION        BIGINT ,
    TOTAL             double (10,2) ,
    PROMOCION         BIGINT ,
    CAJA              BIGINT ,
    PRECIO_INV        BIGINT ,
    TOTAL_INV         BIGINT
  ) ;
ALTER TABLE ARTICULOS_PEDIDO ADD CONSTRAINT ARTICULOS_PEDIDO_PK PRIMARY KEY ( IDARTICULOSPEDIDO ) ;

CREATE TABLE ESTADO_PEDIDOS
  (
    IDESTADOPEDIDO BIGINT(24) NOT NULL ,
    ESTADO    VARCHAR (10) NOT NULL
  ) ;
  
  ALTER TABLE ESTADO_PEDIDOS ADD CONSTRAINT ESTADO_PEDIDOS_PK PRIMARY KEY ( IDESTADOPEDIDO ) ;
  CREATE TABLE INSTITUCION
    (
      IDINSTITUCION BIGINT(24) NOT NULL ,
      RAZONSOCIAL   VARCHAR (100) NOT NULL
    ) ;
  ALTER TABLE INSTITUCION ADD CONSTRAINT INSTITUCION_PK PRIMARY KEY ( IDINSTITUCION ) ;
  CREATE TABLE PAQUETES
    (
      IDPAQUETES   BIGINT(24) NOT NULL ,
      NOMBRE       VARCHAR (100) ,
      FECHA_INICIO DATE ,
      FECHA_FIN    DATE ,
      ACTIVO       VARCHAR (2) ,
      TOTAL        DOUBLE (10,2) ,
      CUENTA_ID    BIGINT
    ) ;
  ALTER TABLE PAQUETES ADD CONSTRAINT PAQUETES_PK PRIMARY KEY ( IDPAQUETES ) ;
  CREATE TABLE PERSONAS
    (
      PI_ID     BIGINT(24) NOT NULL ,
      NRO_DOC   VARCHAR (20) ,
      AP        VARCHAR (65) ,
      AM        VARCHAR (65) ,
      NOM       VARCHAR (100) NOT NULL ,
      SEXO      VARCHAR (1) ,
      EST_CIVIL VARCHAR (1) ,
      FECHA_NAC DATE ,
      CEM_COD   VARCHAR (15) ,
      OCU_COD   VARCHAR (15) ,
      TDO_COD   VARCHAR (15) ,
      SIS_COD   VARCHAR (20) ,
      IDCLIENTE BIGINT(24) NOT NULL
    ) ;
  ALTER TABLE PERSONAS ADD CONSTRAINT PER_PK PRIMARY KEY ( PI_ID ) ;
  CREATE TABLE RETENCION
    (
      IDRETENCION BIGINT(24) NOT NULL ,
      NOMBRE      VARCHAR (50) ,
      PORCENTAGE  double (5,2) ,
      IDCLIENTE   BIGINT(24) NOT NULL
    ) ;
  ALTER TABLE RETENCION ADD CONSTRAINT RETENCION_PK PRIMARY KEY ( IDRETENCION ) ;
  CREATE TABLE TIPOCLIENTE
    (
      IDTIPOCLIENTE BIGINT(24) NOT NULL ,
      NOMBRE        VARCHAR (50)
    ) ;
  ALTER TABLE TIPOCLIENTE ADD CONSTRAINT TIPOCLIENTE_PK PRIMARY KEY ( IDTIPOCLIENTE ) ;
  CREATE TABLE EOS.TIPOPEDIDO
    (
      IDTIPOPEDIDO BIGINT(24) NOT NULL ,
      NOMBRE       VARCHAR (20)
    ) ;
    ALTER TABLE EOS.TIPOPEDIDO ADD CONSTRAINT TIPOPEDIDO_PK PRIMARY KEY ( IDTIPOPEDIDO ) ;
--------------------------------------------------------
--  DDL for Table EMPLEADO
--------------------------------------------------------

  CREATE TABLE EMPLEADO  
   (IDEMPLEADO BIGINT(24),
    FLAGAFP bigint(10), 
	FLAGCONTROL bigint(10), 
	CODIGOEMPLEADO VARCHAR(255), 
	FECHAINGRESO DATE, 
	FLAGJUBILADO bigint(10), 
	CODIGOMARCACION VARCHAR(100), 
	TIPODEPAGO VARCHAR(20), 
	FLAGRET bigint(10), 
	FECHASALIDA DATE, 
	SALARIO double(13,2), 	
	IDUNIDADNEGOCIO bigint(19), 
	IDCOMPANIA bigint(19)
   );
   ALTER TABLE EMPLEADO ADD CONSTRAINT EMPLEADO_PK PRIMARY KEY ( IDEMPLEADO ) ;
   
   CREATE TABLE DISTRIBUIDOR
  (
    IDDISTRIBUIDOR BIGINT (24) NOT NULL ,
    OBSERVACION    VARCHAR (50)
  ) ;
ALTER TABLE DISTRIBUIDOR ADD CONSTRAINT DISTRIBUIDOR_PK PRIMARY KEY ( IDDISTRIBUIDOR ) ;
CREATE TABLE PEDIDOS
  (
    IDPEDIDOS               BIGINT (24) NOT NULL ,
    DESCRIPCION             VARCHAR (200 ) ,
    ESTADO_PEDIDO           BIGINT (24) NOT NULL ,
    TIPO_PEDIDO             VARCHAR (10 ) NOT NULL ,
    ID                      VARCHAR (20 ) NOT NULL ,
    FECHA_PEDIDO            DATE NOT NULL ,
    IDEMPLEADO              BIGINT (24) NOT NULL ,
    IDDIRECCION             BIGINT (24) ,
    IDZONA                  BIGINT (24) ,
    ID1                     BIGINT (10) NOT NULL ,
    TOTAL                   double (10,2) NOT NULL ,
    FECHA_ENTREGA           DATE ,
    FECHA_A_PAGAR           DATE NOT NULL ,
    OBSERVACION             VARCHAR (100 ) ,
    FACTURA                 VARCHAR (2 ) NOT NULL ,
    SUPERVISOR              BIGINT (24) ,
    PORCEN_DESCUENTO        BIGINT ,
    PORCEN_RETENCION        BIGINT ,
    IDTIPOPEDIDO 			BIGINT (24) NOT NULL ,
    IDCLIENTE               BIGINT (24) NOT NULL ,
    IDDISTRIBUIDOR          BIGINT (24) NOT NULL
  ) ;
  
  CREATE TABLE CLIENTE
  (
    IDCLIENTE     BIGINT (24) NOT NULL ,
    DIRECCION     VARCHAR (100) ,
    TELEFONO      INTEGER ,
    NIT           VARCHAR (40) ,
    CODIGO        VARCHAR (10) ,
    IDINSTITUCION BIGINT (24) NOT NULL ,
    IDTIPOCLIENTE BIGINT (24) NOT NULL
  );
ALTER TABLE CLIENTE ADD CONSTRAINT CLIENTE_PK PRIMARY KEY ( IDCLIENTE ) ;
CREATE TABLE INV_ARTICULOS
  (
    NO_CIA           VARCHAR (2 ) NOT NULL ,
    COD_ART          VARCHAR (6 ) NOT NULL ,
    CONTROL_VALORADO VARCHAR (255 ) ,
    CANTIAD_EQUI     double (10,2) ,
    COD_GRU          VARCHAR (3 ) ,
    COD_MED_MAY      VARCHAR (6 ) ,
    SALDO_MON        double (16,6) ,
    STOCKMAXIMO      double (16,6) ,
    STOCKMINIMO      double (16,6) ,
    DESCRI           VARCHAR (100 ) ,
    NOMBRECORTO      VARCHAR (14 ) ,
    CUENTA_ART       VARCHAR (31 ) ,
    VENDIBLE         VARCHAR (255 ) ,
    ESTADO           VARCHAR (3 ) ,
    COD_SUB          VARCHAR (3 ) ,
    COSTO_UNI        double (16,6) ,
    COD_MED          VARCHAR (6 ) ,
    VERSION          BIGINT (19)
  );
 ALTER TABLE INV_ARTICULOS ADD CONSTRAINT INV_ARTICULOS_PK PRIMARY KEY ( NO_CIA, COD_ART );
 CREATE TABLE INV_GRUPOS
  (
    NO_CIA     VARCHAR (2) NOT NULL ,
    COD_GRU    VARCHAR (3) NOT NULL ,
    CUENTA_INV VARCHAR (31) ,
    DESCRI     VARCHAR (100) ,
    VERSION    bigint (19) ,
    TIPO       VARCHAR (50)
  );
 ALTER TABLE INV_GRUPOS ADD CONSTRAINT INV_GRUPOS_PK PRIMARY KEY ( NO_CIA, COD_GRU );
  CREATE TABLE VENTAARTICULO
    (
      IDVENTAARTICULO BIGINT (24) NOT NULL ,
      PRECIO          BIGINT ,
      PRECIOESPECIAL  BIGINT ,
      IDCLIENTE       BIGINT (24) NOT NULL ,
      NO_CIA          VARCHAR (2 ) NOT NULL ,
      COD_ART         VARCHAR (6 ) NOT NULL
    );
  ALTER TABLE VENTAARTICULO ADD CONSTRAINT VENTAARTICULO_PK PRIMARY KEY ( IDVENTAARTICULO ) ;
  
  ALTER TABLE CLIENTE ADD CONSTRAINT CLIENTE_INSTITUCION_FK FOREIGN KEY ( IDINSTITUCION ) REFERENCES INSTITUCION ( IDINSTITUCION );
  ALTER TABLE CLIENTE ADD CONSTRAINT CLIENTE_TIPOCLIENTE_FK FOREIGN KEY ( IDTIPOCLIENTE ) REFERENCES TIPOCLIENTE ( IDTIPOCLIENTE );
  ALTER TABLE INV_ARTICULOS ADD CONSTRAINT INV_ARTICULOS_FK1 FOREIGN KEY ( NO_CIA, COD_GRU ) REFERENCES INV_GRUPOS ( NO_CIA, COD_GRU );
  ALTER TABLE VENTAARTICULO ADD CONSTRAINT VENTAARTICULO_CLIENTE_FK FOREIGN KEY ( IDCLIENTE ) REFERENCES CLIENTE ( IDCLIENTE );
  ALTER TABLE VENTAARTICULO ADD CONSTRAINT VENTAARTICULO_INV_ARTICULOS_FK FOREIGN KEY ( NO_CIA, COD_ART ) REFERENCES INV_ARTICULOS ( NO_CIA, COD_ART );

  
  ALTER TABLE PEDIDOS ADD CONSTRAINT PEDIDOS_PK PRIMARY KEY ( IDPEDIDOS ) ;
  ALTER TABLE PEDIDOS ADD CONSTRAINT PEDIDOS_CLIENTE_FK FOREIGN KEY ( IDCLIENTE ) REFERENCES CLIENTE ( IDCLIENTE ) ;
  ALTER TABLE PEDIDOS ADD CONSTRAINT PEDIDOS_DISTRIBUIDOR_FK FOREIGN KEY ( IDDISTRIBUIDOR ) REFERENCES DISTRIBUIDOR ( IDDISTRIBUIDOR ) ;
  ALTER TABLE PEDIDOS ADD CONSTRAINT PEDIDOS_EMPLEADO_FK FOREIGN KEY ( IDEMPLEADO ) REFERENCES EOS.EMPLEADO ( IDEMPLEADO ) ;
  ALTER TABLE PEDIDOS ADD CONSTRAINT PEDIDOS_ESTADO_PEDIDOS_FK FOREIGN KEY ( ESTADO_PEDIDO ) REFERENCES ESTADO_PEDIDOS ( IDESTADOPEDIDO ) ;
  ALTER TABLE PEDIDOS ADD CONSTRAINT PEDIDOS_TIPOPEDIDO_FK FOREIGN KEY ( IDTIPOPEDIDO ) REFERENCES EOS.TIPOPEDIDO ( IDTIPOPEDIDO ) ;

      
  ALTER TABLE ARTICULOS_PAQUETE ADD CONSTRAINT ARTICULOS_PAQUETE_CLIENTE_FK FOREIGN KEY ( IDCLIENTE ) REFERENCES CLIENTE ( IDCLIENTE ) ;
  ALTER TABLE ARTICULOS_PAQUETE ADD CONSTRAINT PAQ_ARTI_FK FOREIGN KEY ( IDPAQUETES ) REFERENCES PAQUETES ( IDPAQUETES ) ON
  DELETE CASCADE ;
  ALTER TABLE ARTICULOS_PEDIDO ADD CONSTRAINT ART_PEDIDO_PEDIDOS_FK FOREIGN KEY ( PEDIDO ) REFERENCES PEDIDOS ( IDPEDIDOS ) ;  
  ALTER TABLE PERSONAS ADD CONSTRAINT PERSONAS_CLIENTE_FK FOREIGN KEY ( IDCLIENTE ) REFERENCES CLIENTE ( IDCLIENTE ) ;
  ALTER TABLE RETENCION ADD CONSTRAINT RETENCION_CLIENTE_FK FOREIGN KEY ( IDCLIENTE ) REFERENCES CLIENTE ( IDCLIENTE ) ;
  
