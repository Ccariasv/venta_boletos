-- ============================================================================
-- Oracle 21c XE | DDL completo
-- ============================================================================

-- =========================
-- CATÁLOGOS (DOMINIOS)
-- =========================
CREATE TABLE cat_estado_avion (
  id           NUMBER(3) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  codigo       VARCHAR2(30) NOT NULL UNIQUE,
  descripcion  VARCHAR2(200)
);

CREATE TABLE cat_estado_vuelo (
  id           NUMBER(3) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  codigo       VARCHAR2(30) NOT NULL UNIQUE,
  descripcion  VARCHAR2(200)
);

CREATE TABLE cat_estado_boleto (
  id           NUMBER(3) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  codigo       VARCHAR2(30) NOT NULL UNIQUE,
  descripcion  VARCHAR2(200)
);

CREATE TABLE cat_metodo_pago (
  id           NUMBER(3) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  codigo       VARCHAR2(30) NOT NULL UNIQUE,
  descripcion  VARCHAR2(200)
);

CREATE TABLE cat_rol_tripulacion (
  id           NUMBER(3) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  codigo       VARCHAR2(30) NOT NULL UNIQUE,
  descripcion  VARCHAR2(200)
);

CREATE TABLE clases_asiento (
  id            NUMBER(6) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  nombre_clase  VARCHAR2(50) NOT NULL UNIQUE,
  descripcion   VARCHAR2(200)
);

-- =========================
-- ENTIDADES
-- =========================
CREATE TABLE aeropuertos (
  id           NUMBER(10) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  nombre       VARCHAR2(100) NOT NULL,
  ciudad       VARCHAR2(100),
  pais         VARCHAR2(100),
  codigo_iata  CHAR(3)       NOT NULL,
  CONSTRAINT uk_aeropuerto_iata UNIQUE (codigo_iata),
  CONSTRAINT ck_aeropuerto_iata_uc CHECK (codigo_iata = UPPER(codigo_iata))
);

CREATE TABLE rutas (
  id               NUMBER(10) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  a_origen_id      NUMBER(10) NOT NULL,
  a_destino_id     NUMBER(10) NOT NULL,
  duracion_min     NUMBER(5)  NOT NULL,
  distancia_km     NUMBER(7,2),
  CONSTRAINT fk_ruta_origen  FOREIGN KEY (a_origen_id)  REFERENCES aeropuertos(id),
  CONSTRAINT fk_ruta_destino FOREIGN KEY (a_destino_id) REFERENCES aeropuertos(id),
  CONSTRAINT ck_ruta_origen_destino CHECK (a_origen_id <> a_destino_id)
);
CREATE INDEX ix_rutas_origen  ON rutas(a_origen_id);
CREATE INDEX ix_rutas_destino ON rutas(a_destino_id);

CREATE TABLE aviones (
  id               NUMBER(10) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  modelo           VARCHAR2(100) NOT NULL,
  fabricante       VARCHAR2(100),
  capacidad_total  NUMBER(5)     NOT NULL,
  estado_id        NUMBER(3)     NOT NULL,
  matricula        VARCHAR2(20)  NOT NULL,
  CONSTRAINT fk_avion_estado FOREIGN KEY (estado_id) REFERENCES cat_estado_avion(id),
  CONSTRAINT uk_avion_matricula UNIQUE (matricula),
  CONSTRAINT ck_avion_capacidad CHECK (capacidad_total > 0)
);

CREATE TABLE asientos (
  id              NUMBER(10) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  avion_id        NUMBER(10) NOT NULL,
  numero_asiento  VARCHAR2(10) NOT NULL,
  clase_id        NUMBER(6)   NOT NULL,
  CONSTRAINT fk_asiento_avion FOREIGN KEY (avion_id) REFERENCES aviones(id),
  CONSTRAINT fk_asiento_clase FOREIGN KEY (clase_id) REFERENCES clases_asiento(id),
  CONSTRAINT uk_asiento_numero UNIQUE (avion_id, numero_asiento)
);
CREATE INDEX ix_asientos_avion ON asientos(avion_id);

CREATE TABLE vuelos (
  id                 NUMBER(10) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  ruta_id            NUMBER(10) NOT NULL,
  avion_id           NUMBER(10) NOT NULL,
  numero_vuelo       VARCHAR2(10) NOT NULL,
  salida_ts          TIMESTAMP WITH TIME ZONE NOT NULL,
  llegada_ts         TIMESTAMP WITH TIME ZONE NOT NULL,
  estado_id          NUMBER(3) NOT NULL,
  CONSTRAINT fk_vuelo_ruta   FOREIGN KEY (ruta_id)  REFERENCES rutas(id),
  CONSTRAINT fk_vuelo_avion  FOREIGN KEY (avion_id) REFERENCES aviones(id),
  CONSTRAINT fk_vuelo_estado FOREIGN KEY (estado_id) REFERENCES cat_estado_vuelo(id),
  CONSTRAINT ck_vuelo_tiempos CHECK (llegada_ts > salida_ts)
);
CREATE UNIQUE INDEX uk_vuelo_numero_fecha
  ON vuelos (numero_vuelo, TRUNC(CAST(salida_ts AT TIME ZONE 'UTC' AS DATE)));
CREATE INDEX ix_vuelos_ruta  ON vuelos(ruta_id);
CREATE INDEX ix_vuelos_avion ON vuelos(avion_id);

CREATE TABLE pasajeros (
  id                   NUMBER(10) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  nombres              VARCHAR2(100) NOT NULL,
  apellidos            VARCHAR2(100) NOT NULL,
  email                VARCHAR2(100),
  telefono             VARCHAR2(20),
  documento_identidad  VARCHAR2(50) NOT NULL,
  CONSTRAINT uk_pasaporte UNIQUE (documento_identidad)
);

CREATE TABLE boletos (
  id             NUMBER(12) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  vuelo_id       NUMBER(10) NOT NULL,
  pasajero_id    NUMBER(10) NOT NULL,
  asiento_id     NUMBER(10) NOT NULL,
  precio         NUMBER(10,2) NOT NULL,
  fecha_emision  DATE DEFAULT SYSDATE NOT NULL,
  estado_id      NUMBER(3) NOT NULL,
  CONSTRAINT fk_boleto_vuelo     FOREIGN KEY (vuelo_id)    REFERENCES vuelos(id),
  CONSTRAINT fk_boleto_pasajero  FOREIGN KEY (pasajero_id) REFERENCES pasajeros(id),
  CONSTRAINT fk_boleto_asiento   FOREIGN KEY (asiento_id)  REFERENCES asientos(id),
  CONSTRAINT fk_boleto_estado    FOREIGN KEY (estado_id)   REFERENCES cat_estado_boleto(id),
  CONSTRAINT ck_boleto_precio    CHECK (precio >= 0)
);
CREATE UNIQUE INDEX uk_boleto_vuelo_asiento ON boletos (vuelo_id, asiento_id);

CREATE TABLE pagos (
  id              NUMBER(12) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  boleto_id       NUMBER(12) NOT NULL,
  metodo_pago_id  NUMBER(3)  NOT NULL,
  monto           NUMBER(10,2) NOT NULL,
  fecha_pago      DATE DEFAULT SYSDATE NOT NULL,
  referencia_ext  VARCHAR2(100),
  CONSTRAINT fk_pago_boleto      FOREIGN KEY (boleto_id)      REFERENCES boletos(id) ON DELETE CASCADE,
  CONSTRAINT fk_pago_metodo      FOREIGN KEY (metodo_pago_id) REFERENCES cat_metodo_pago(id),
  CONSTRAINT ck_pago_monto       CHECK (monto > 0)
);
CREATE INDEX ix_pagos_boleto ON pagos(boleto_id);

CREATE TABLE empleados (
  id          NUMBER(10) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  nombres     VARCHAR2(100) NOT NULL,
  apellidos   VARCHAR2(100) NOT NULL,
  puesto      VARCHAR2(50)  NOT NULL,
  email       VARCHAR2(100),
  telefono    VARCHAR2(20)
);

CREATE TABLE turnos_tripulacion (
  id             NUMBER(12) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  empleado_id    NUMBER(10) NOT NULL,
  vuelo_id       NUMBER(10) NOT NULL,
  rol_id         NUMBER(3)  NOT NULL,
  CONSTRAINT fk_turno_empleado  FOREIGN KEY (empleado_id) REFERENCES empleados(id),
  CONSTRAINT fk_turno_vuelo     FOREIGN KEY (vuelo_id)    REFERENCES vuelos(id),
  CONSTRAINT fk_turno_rol       FOREIGN KEY (rol_id)      REFERENCES cat_rol_tripulacion(id),
  CONSTRAINT uk_turno UNIQUE (empleado_id, vuelo_id, rol_id)
);
CREATE INDEX ix_turno_vuelo ON turnos_tripulacion(vuelo_id);

CREATE TABLE checkin (
  id              NUMBER(12) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  boleto_id       NUMBER(12) NOT NULL,
  fecha_checkin   DATE DEFAULT SYSDATE NOT NULL,
  equipaje_flag   CHAR(1) DEFAULT 'N' NOT NULL,
  peso_total_kg   NUMBER(6,2) DEFAULT 0,
  CONSTRAINT fk_checkin_boleto FOREIGN KEY (boleto_id) REFERENCES boletos(id) ON DELETE CASCADE,
  CONSTRAINT ck_checkin_flag   CHECK (equipaje_flag IN ('S','N')),
  CONSTRAINT ck_checkin_peso   CHECK (peso_total_kg >= 0),
  CONSTRAINT uk_checkin_boleto UNIQUE (boleto_id)
);

CREATE TABLE equipaje (
  id            NUMBER(12) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  checkin_id    NUMBER(12) NOT NULL,
  descripcion   VARCHAR2(200),
  peso_kg       NUMBER(6,2) NOT NULL,
  CONSTRAINT fk_equipaje_checkin FOREIGN KEY (checkin_id) REFERENCES checkin(id) ON DELETE CASCADE,
  CONSTRAINT ck_equipaje_peso CHECK (peso_kg > 0)
);
CREATE INDEX ix_equipaje_checkin ON equipaje(checkin_id);

CREATE TABLE roles_sistema (
  id          NUMBER(3) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  codigo      VARCHAR2(30) NOT NULL UNIQUE,
  descripcion VARCHAR2(200)
);

CREATE TABLE usuarios (
  id            NUMBER(10) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  username      VARCHAR2(50)  NOT NULL,
  password_hash VARCHAR2(256) NOT NULL,
  rol_id        NUMBER(3)     NOT NULL,
  empleado_id   NUMBER(10),
  activo_flag   CHAR(1) DEFAULT 'S' NOT NULL,
  CONSTRAINT uk_usuario_username UNIQUE (username),
  CONSTRAINT fk_usuario_rol      FOREIGN KEY (rol_id)      REFERENCES roles_sistema(id),
  CONSTRAINT fk_usuario_empleado FOREIGN KEY (empleado_id) REFERENCES empleados(id),
  CONSTRAINT ck_usuario_activo   CHECK (activo_flag IN ('S','N'))
);

-- Índices opcionales
CREATE INDEX ix_boletos_pasajero ON boletos(pasajero_id);
CREATE INDEX ix_vuelos_estado    ON vuelos(estado_id);
CREATE INDEX ix_boletos_estado   ON boletos(estado_id);
CREATE INDEX ix_pagos_metodo     ON pagos(metodo_pago_id);
CREATE INDEX ix_turno_empleado   ON turnos_tripulacion(empleado_id);
CREATE INDEX ix_asientos_clase   ON asientos(clase_id);

-- Trigger: el asiento debe pertenecer al avión del vuelo
CREATE OR REPLACE TRIGGER trg_boleto_asiento_avion
  BEFORE INSERT OR UPDATE OF vuelo_id, asiento_id ON boletos
  FOR EACH ROW
DECLARE
  v_avion_vuelo   aviones.id%TYPE;
  v_avion_asiento aviones.id%TYPE;
BEGIN
  SELECT avion_id INTO v_avion_vuelo  FROM vuelos   WHERE id = :NEW.vuelo_id;
  SELECT avion_id INTO v_avion_asiento FROM asientos WHERE id = :NEW.asiento_id;
  IF v_avion_vuelo <> v_avion_asiento THEN
    RAISE_APPLICATION_ERROR(-20001, 'El asiento no pertenece al avión asignado al vuelo.');
  END IF;
END;
/

-- Comentarios
COMMENT ON TABLE aeropuertos IS 'Catálogo de aeropuertos (IATA único)';
COMMENT ON COLUMN vuelos.numero_vuelo IS 'Identificador comercial del vuelo (p. ej., TA123)';

-- Seeds mínimos
INSERT INTO cat_estado_avion (codigo, descripcion) VALUES ('ACTIVO', 'Disponible para volar');
INSERT INTO cat_estado_avion (codigo, descripcion) VALUES ('MANTENIMIENTO', 'En mantenimiento');
INSERT INTO cat_estado_avion (codigo, descripcion) VALUES ('RETIRADO', 'Fuera de servicio');

INSERT INTO cat_estado_vuelo (codigo, descripcion) VALUES ('PROGRAMADO', 'Programado');
INSERT INTO cat_estado_vuelo (codigo, descripcion) VALUES ('EMBARCANDO', 'Embarcando');
INSERT INTO cat_estado_vuelo (codigo, descripcion) VALUES ('EN_VUELO', 'En vuelo');
INSERT INTO cat_estado_vuelo (codigo, descripcion) VALUES ('ATRASADO', 'Atrasado');
INSERT INTO cat_estado_vuelo (codigo, descripcion) VALUES ('CANCELADO', 'Cancelado');
INSERT INTO cat_estado_vuelo (codigo, descripcion) VALUES ('ARRIBADO', 'Arribado');

INSERT INTO cat_estado_boleto (codigo, descripcion) VALUES ('RESERVADO', 'Reserva registrada');
INSERT INTO cat_estado_boleto (codigo, descripcion) VALUES ('EMITIDO', 'Boleto emitido');
INSERT INTO cat_estado_boleto (codigo, descripcion) VALUES ('CANCELADO', 'Boleto cancelado');
INSERT INTO cat_estado_boleto (codigo, descripcion) VALUES ('USADO', 'Boleto utilizado');

INSERT INTO cat_metodo_pago (codigo, descripcion) VALUES ('TARJETA', 'Tarjeta de crédito/débito');
INSERT INTO cat_metodo_pago (codigo, descripcion) VALUES ('TRANSFERENCIA', 'Transferencia bancaria');
INSERT INTO cat_metodo_pago (codigo, descripcion) VALUES ('EFECTIVO', 'Pago en efectivo');

INSERT INTO clases_asiento (nombre_clase, descripcion) VALUES ('ECONOMY', 'Clase económica');
INSERT INTO clases_asiento (nombre_clase, descripcion) VALUES ('BUSINESS', 'Clase ejecutiva');

INSERT INTO cat_rol_tripulacion (codigo, descripcion) VALUES ('PILOTO', 'Piloto al mando');
INSERT INTO cat_rol_tripulacion (codigo, descripcion) VALUES ('COPILOTO', 'Primer oficial');
INSERT INTO cat_rol_tripulacion (codigo, descripcion) VALUES ('TCP', 'Tripulante de cabina');

INSERT INTO roles_sistema (codigo, descripcion) VALUES ('ADMIN', 'Administrador');
INSERT INTO roles_sistema (codigo, descripcion) VALUES ('CAJERO', 'Caja/ventas');
INSERT INTO roles_sistema (codigo, descripcion) VALUES ('AGENTE', 'Agente de mostrador');
