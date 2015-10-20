ALTER TABLE `tipodoc` 
CHANGE COLUMN `NOMBRE` `NOMBRE` VARCHAR(5) NOT NULL ;
ALTER TABLE `tipodoc` 
ADD UNIQUE INDEX `NOMBRE_UNIQUE` (`NOMBRE` ASC);
CREATE TABLE OPERACIONES
  (
    IDOPERACIONES BIGINT NOT NULL ,
    descripcion   VARCHAR (100) ,
    NOMBRE        VARCHAR (30) ,
    CONSTRAINT OPERACIONES_PK PRIMARY KEY ( IDOPERACIONES )
  );
ALTER TABLE pago DROP FOREIGN KEY pago_sf_confenc_FK;
DROP INDEX pago_sf_confenc_FK ON khipus.pago;
ALTER TABLE pago DROP id_sf_confenc;  