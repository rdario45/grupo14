# --- Creation of events table

# --- !Ups

CREATE TABLE accounts (
	email varchar(100) NOT NULL,
	status varchar(10) NULL,
	CONSTRAINT accounts_pkey PRIMARY KEY (email)
);

CREATE TABLE sessions (
	id serial PRIMARY KEY,
	account varchar(100) NOT NULL,
	token TEXT NOT NULL,
	timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT sessions_user_fkey FOREIGN KEY (account) REFERENCES accounts(email)
);

CREATE TABLE companies (
	id serial PRIMARY KEY,
	name varchar(100) NOT NULL,
	admin varchar(100) NOT NULL

);

CREATE TABLE projects (
	id serial PRIMARY KEY ,
	name varchar(100) NOT NULL,
	description varchar(200) NOT NULL,
	cost numeric(12,2) NOT NULL,
	company_id int4 NOT NULL

);

CREATE TABLE design (
  id serial PRIMARY KEY,
  `email` varchar(45) DEFAULT NULL,
  `resized` boolean DEFAULT false,
  `originalPath` varchar(250) DEFAULT NULL,
  `resizedPath` varchar(250) DEFAULT NULL
) ;




insert into design (id, email, resized, originalPath)
values
(1,'diegoatorres@gmail.com',false,'/home/diego/Imágenes/Captura de pantalla de 2019-08-27 21-39-35.png'),
(2,'da.torres58@uniandes.edu.co',false,'/home/diego/Imágenes/Captura de pantalla de 2019-08-27 21-40-26.png'),
(3,'ruben@uniandes.edu.co',false,'/home/diego/Imágenes/Captura de pantalla de 2019-08-27 21-40-27.png'),
(4,'camilo@uniandes.edu.co',true,'/home/diego/Imágenes/Captura de pantalla de 2019-08-27 21-40-36.png');

# --- !Downs
DROP TABLE projects;
DROP TABLE companies;
DROP TABLE sessions;
DROP TABLE design;