--------------------------------------------------------
--  DDL for Table COMPANIA
--------------------------------------------------------

  CREATE TABLE EOS.COMPANIA 
   (	IDCOMPANIA BIGINT(24), 
		CODIGO VARCHAR(255)
   );
--------------------------------------------------------
--  DDL for Index COMPANIA_PK
--------------------------------------------------------
ALTER TABLE EOS.COMPANIA ADD CONSTRAINT COMPANIA_PK PRIMARY KEY ( IDCOMPANIA) ;

  

CREATE TABLE EOS.DERECHOACCESO
  (
    IDFUNCIONALIDAD BIGINT(24)  NOT NULL ,
    IDROL           BIGINT(24)  NOT NULL ,
    PERMISO         int (3) ,
    IDCOMPANIA      BIGINT(24)  ,
    IDMODULO        BIGINT(24) 
  ) ;
  ALTER TABLE EOS.DERECHOACCESO ADD CONSTRAINT DERECHOACCESO_PK PRIMARY KEY ( IDFUNCIONALIDAD, IDROL ) ;
  
  CREATE TABLE EOS.FUNCIONALIDAD
    (
      IDFUNCIONALIDAD BIGINT(24)  NOT NULL ,
      CODIGO          VARCHAR (40 ) ,
      DESCRIPCION text ,
      IDMODULO      BIGINT(24)  ,
      PERMISO       INT (3) ,
      NOMBRERECURSO VARCHAR (100 ) ,
      IDCOMPANIA    BIGINT(24) 
    ) ;
  ALTER TABLE EOS.FUNCIONALIDAD ADD CONSTRAINT FUNCIONALIDAD_PK PRIMARY KEY ( IDFUNCIONALIDAD ) ;
  CREATE TABLE EOS.MODULO
    (
      IDMODULO BIGINT(24)  NOT NULL ,
      DESCRIPCION text ,
      NOMBRERECURSO VARCHAR (150 ) ,
      IDCOMPANIA    BIGINT(24) 
    ) ;
  ALTER TABLE EOS.MODULO ADD CONSTRAINT MODULO_PK PRIMARY KEY ( IDMODULO ) ;
  CREATE TABLE EOS.MODULOCOMPANIA
    (
      IDCOMPANIA BIGINT(24)  NOT NULL ,
      IDMODULO   BIGINT(24)  NOT NULL ,
      ACTIVO     INT (10)
    ) ;
  ALTER TABLE EOS.MODULOCOMPANIA ADD CONSTRAINT MODULOCOMPANIA_PK PRIMARY KEY ( IDCOMPANIA, IDMODULO ) ;
  CREATE TABLE EOS.ROL
    (
      IDROL BIGINT(24)  NOT NULL ,
      DESCRIPCION text ,
      NOMBRE     VARCHAR (150 ) ,
      VERSION    BIGINT(24)  ,
      IDCOMPANIA BIGINT(24) 
    ) ;
  ALTER TABLE EOS.ROL ADD CONSTRAINT ROL_PK PRIMARY KEY ( IDROL ) ;
  CREATE TABLE EOS.USUARIO
    (
      IDUSUARIO       BIGINT(24)  NOT NULL ,
      FECHACREACION   TIMESTAMP ,
      EMAIL           VARCHAR (100 ) ,
      NUMEROUSUARIO   VARCHAR (4 ) ,
      USUARIOFINANZAS INT (10) ,
      CLAVE           VARCHAR (200 ) ,
      USUARIO         VARCHAR (50 ) ,
      VERSION         BIGINT(24)  ,
      IDCOMPANIA      BIGINT(24) 
    ) ;
  ALTER TABLE EOS.USUARIO ADD CONSTRAINT USUARIO_PK PRIMARY KEY ( IDUSUARIO ) ;
  CREATE TABLE EOS.USUARIOROL
    (
      IDROL     BIGINT(24)  NOT NULL ,
      IDUSUARIO BIGINT(24) 
    ) ;
  ALTER TABLE EOS.DERECHOACCESO ADD CONSTRAINT DERECHOACCESO_FUNCIONALID_FK1 FOREIGN KEY ( IDFUNCIONALIDAD ) REFERENCES EOS.FUNCIONALIDAD ( IDFUNCIONALIDAD ) ;
  ALTER TABLE EOS.DERECHOACCESO ADD CONSTRAINT DERECHOACCESO_MODULOCOMPA_FK1 FOREIGN KEY ( IDCOMPANIA, IDMODULO ) REFERENCES EOS.MODULOCOMPANIA ( IDCOMPANIA, IDMODULO ) ;
  ALTER TABLE EOS.DERECHOACCESO ADD CONSTRAINT DERECHOACCESO_ROL_FK1 FOREIGN KEY ( IDROL ) REFERENCES EOS.ROL ( IDROL ) ;
  ALTER TABLE EOS.FUNCIONALIDAD ADD CONSTRAINT FUNCIONALIDAD_MODULO_FK1 FOREIGN KEY ( IDMODULO ) REFERENCES EOS.MODULO ( IDMODULO ) ;
  ALTER TABLE EOS.MODULOCOMPANIA ADD CONSTRAINT MODULOCOMPANIA_MODULO_FK1 FOREIGN KEY ( IDMODULO ) REFERENCES EOS.MODULO ( IDMODULO ) ;
  ALTER TABLE EOS.USUARIOROL ADD CONSTRAINT USUARIOROL_ROL_FK1 FOREIGN KEY ( IDROL ) REFERENCES EOS.ROL ( IDROL ) ;
  ALTER TABLE EOS.USUARIOROL ADD CONSTRAINT USUARIOROL_USUARIO_FK1 FOREIGN KEY ( IDUSUARIO ) REFERENCES EOS.USUARIO ( IDUSUARIO ) ;  
  ALTER TABLE EOS.USUARIO ADD CONSTRAINT USUARIO_COMPANIA_FK1 FOREIGN KEY ( IDCOMPANIA ) REFERENCES EOS.COMPANIA ( IDCOMPANIA ) ;
-----------------
/*
use eos;
create table TUsuario
(
codigoUsuario char(15) not null,
nombre varchar(30) not null,
apellidoPaterno varchar(20) not null,
apellidoMaterno varchar(20) not null,
correoElectronico varchar(30) not null,
contrasenia varchar(700) not null,
fechaNacimiento date not null,
sexo bool not null,
telefono varchar(20) not null,
fechaRegistro timestamp not null default current_timestamp,
fechaModificacion timestamp not null default current_timestamp on update current_timestamp,
primary key(codigoUsuario)
);

create table TUsuarioAmigo
(
codigoUsuarioAmigo char(15) not null,
codigoUsuario char(15) not null,
nombre varchar(30) not null,
apellidoPaterno varchar(20) not null,
apellidoMaterno varchar(20) not null,
correoElectronico varchar(30) not null,
contrasenia varchar(700) not null,
fechaNacimiento date not null,
sexo bool not null,
telefono varchar(20) not null,
fechaRegistro timestamp not null default current_timestamp,
fechaModificacion timestamp not null default current_timestamp on update current_timestamp,
foreign key(codigoUsuario) references TUsuario(codigoUsuario)
on update cascade on delete cascade,
primary key(codigoUsuarioAmigo)
);

create table TUsuarioAmigoTelefono
(
codigoTUsuarioAmigoTelefono char(15) not null,
codigoUsuarioAmigo char(15) not null,
descripcion text not null,
telefono varchar(20) not null,
fechaRegistro timestamp not null default current_timestamp,
fechaModificacion timestamp not null default current_timestamp on update current_timestamp,
foreign key(codigoUsuarioAmigo) references TUsuarioAmigo(codigoUsuarioAmigo)
on update cascade on delete cascade,
primary key(codigoTUsuarioAmigoTelefono)
);

create table TUnidadMedida
(
codigoUnidadMedida char(15) not null,
nombre varchar(30) not null,
descripcion text not null,
fechaRegistro timestamp not null default current_timestamp,
fechaModificacion timestamp not null default current_timestamp on update current_timestamp,
primary key (codigoUnidadMedida)
);

create table TActividad
(
codigoActividad char(15) not null,
codigoUsuario char(15) not null,
nombre varchar(700) not null,
descripcion text not null,
fechaInicio datetime not null,
fechaFin datetime not null,
presupuestoTotal decimal(8, 2) not null,
estado char(1) not null,
fechaRegistro timestamp not null default current_timestamp,
fechaModificacion timestamp not null default current_timestamp on update current_timestamp,
foreign key(codigoUsuario) references TUsuario(codigoUsuario)
on update cascade on delete cascade,
primary key(codigoActividad)
);

create table TActividadPresupuesto
(
codigoActividadPresupuesto char(15) not null,
codigoActividad char(15) not null,
codigoUnidadMedida char(15) not null,
descripcion text not null,
precioUnitario decimal(8, 2) not null,
cantidad float not null,
fechaRegistro timestamp not null default current_timestamp,
fechaModificacion timestamp not null default current_timestamp on update current_timestamp,
foreign key(codigoActividad) references TActividad(codigoActividad) on update cascade on delete cascade,
foreign key(codigoUnidadMedida) references TUnidadMedida(codigoUnidadMedida)
on update cascade on delete cascade,
primary key(codigoActividadPresupuesto)
);

create table TActividadParticipante
(
codigoActividadParticipante char(15) not null,
codigoActividad char(15) not null,
codigoUsuarioAmigo char(15) not null,
fechaRegistro timestamp not null default current_timestamp,
fechaModificacion timestamp not null default current_timestamp on update current_timestamp,
foreign key(codigoActividad) references TActividad(codigoActividad)
on update cascade on delete cascade,
foreign key(codigoUsuarioAmigo) references TUsuarioAmigo(codigoUsuarioAmigo)
on update cascade on delete cascade,
primary key(codigoActividadParticipante)
);

create table TActividadComentario
(
codigoActividadComentario char(15) not null,
codigoActividad char(15) not null,
codigoUsuarioAmigo char(15) not null,
comentario text not null,
fechaRegistro timestamp not null default current_timestamp,
fechaModificacion timestamp not null default current_timestamp on update current_timestamp,
foreign key(codigoActividad) references TActividad(codigoActividad)
on update cascade on delete cascade,
foreign key(codigoUsuarioAmigo) references TUsuarioAmigo(codigoUsuarioAmigo)
on update cascade on delete cascade,
primary key(codigoActividadComentario)
);
*/




