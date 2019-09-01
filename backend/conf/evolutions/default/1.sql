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

CREATE TABLE designs (
  id serial PRIMARY KEY,
  email varchar(45) DEFAULT NULL,
  designStatus varchar(10) DEFAULT 'PROCESSING',
  originalPath varchar(250) NOT NULL,
  resizedPath varchar(250) DEFAULT NULL
) ;

# --- !Downs
DROP TABLE projects;
DROP TABLE companies;
DROP TABLE sessions;
DROP TABLE design;