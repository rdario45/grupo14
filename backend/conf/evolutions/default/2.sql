# --- creacion tabla proyectos.

# --- !Ups
CREATE TABLE proyectos (
	id serial NOT NULL,
	nombre varchar(100) NOT NULL,
    descripcion varchar(200) NOT NULL,
    valor_estimado NUMERIC(12,2) NOT NULL
);
COMMENT ON TABLE proyectos IS 'Almacena los proyectos.';

# --- !Downs
drop table proyectos;