CREATE
  TABLE pago
  (
    id_pago BIGINT(20) NOT NULL ,
    fecha   TIMESTAMP ,
    monto DOUBLE ,
    descripcion       VARCHAR(150) ,
    IDPERSONACLIENTE BIGINT(20) NOT NULL ,
    id_sf_confenc     BIGINT(20) NOT NULL ,
    idusuario     BIGINT(20) NOT NULL ,
    CONSTRAINT pago_PK PRIMARY KEY ( id_pago )
  );
CREATE
  TABLE sf_confdet
  (
    id_sf_confdet  BIGINT(20) NOT NULL ,
    cuenta         VARCHAR (31) ,
    tipomovimiento VARCHAR (20) ,
    id_sf_confenc  BIGINT(20) NOT NULL ,
    CONSTRAINT sf_confdet_PK PRIMARY KEY ( id_sf_confdet )
  );
CREATE
  TABLE sf_confenc
  (
    id_sf_confenc BIGINT(20) NOT NULL ,
    cuenta        VARCHAR (31) ,
    descripcion   VARCHAR (150),
    tipo_doc      VARCHAR (10),t
    glosa         VARCHAR (150),
    idusuario     BIGINT(20) NOT NULL ,
    CONSTRAINT sf_confenc_PK PRIMARY KEY ( id_sf_confenc )
  );  
ALTER TABLE pago ADD CONSTRAINT pago_persona_FK FOREIGN KEY ( IDPERSONACLIENTE) REFERENCES PERSONACLIENTE ( IDPERSONACLIENTE ) ON DELETE NO ACTION ;
ALTER TABLE pago ADD CONSTRAINT pago_usuario_FK FOREIGN KEY ( idusuario) REFERENCES usuario ( idusuario ) ON DELETE NO ACTION ;
ALTER TABLE pago ADD CONSTRAINT pago_sf_confenc_FK FOREIGN KEY ( id_sf_confenc) REFERENCES sf_confenc ( id_sf_confenc ) ON DELETE NO ACTION ;
ALTER TABLE sf_confdet ADD CONSTRAINT sf_confdet_sf_confenc_FK FOREIGN KEY (id_sf_confenc ) REFERENCES sf_confenc ( id_sf_confenc ) ON DELETE NO ACTION ;
ALTER TABLE pedidos ADD id_sf_confenc BIGINT NULL;
ALTER TABLE pedidos ADD CONSTRAINT fk_pedido_sf_confenc FOREIGN KEY (id_sf_confenc) REFERENCES sf_confenc( id_sf_confenc ) ON DELETE NO ACTION;
ALTER TABLE pago ADD id_tmpenc BIGINT(20) NULL;
ALTER TABLE pedidos ADD id_tmpenc BIGINT(20) NULL;
ALTER TABLE pedidos ADD CONSTRAINT pedidos_sf_tmpenc_FK FOREIGN KEY (id_tmpenc ) REFERENCES sf_tmpenc ( id_tmpenc ) ON DELETE NO ACTION ;
ALTER TABLE pago ADD CONSTRAINT pago_sf_tmpenc_FK FOREIGN KEY (id_tmpenc ) REFERENCES sf_tmpenc ( id_tmpenc ) ON DELETE NO ACTION ;
ALTER TABLE `personacliente` ADD deuda DOUBLE NULL;
ALTER TABLE `sf_tmpdet` ADD id_sf_tmpdet BIGINT(20) NOT NULL;
ALTER TABLE sf_tmpdet ADD PRIMARY KEY (id_sf_tmpdet);