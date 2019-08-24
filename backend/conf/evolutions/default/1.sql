# --- Creation of events table

# --- !Ups
CREATE TABLE empresas (
	id serial NOT NULL,
	nombre varchar(100) NOT NULL
);
COMMENT ON TABLE empresas IS 'Almacena los empresas.';

# --- !Downs
drop table empresas;