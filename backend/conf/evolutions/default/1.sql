# --- Creation of events table

# --- !Ups

CREATE TABLE accounts (
	email varchar(100) NOT NULL,
	"password" varchar(200) NOT NULL,
	status varchar(10) NULL,
	CONSTRAINT accounts_pkey PRIMARY KEY (email)
);

CREATE TABLE sessions (
	id serial NOT NULL,
	account varchar(100) NOT NULL,
	token TEXT NOT NULL,
	timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT sessions_pkey PRIMARY KEY (id),
	CONSTRAINT sessions_user_fkey FOREIGN KEY (account) REFERENCES accounts(email)
);

CREATE TABLE companies (
	id serial NOT NULL,
	name varchar(100) NOT NULL,
	admin varchar(100) NOT NULL,
	CONSTRAINT companies_pkey PRIMARY KEY (id),
	CONSTRAINT companies_admin_fkey FOREIGN KEY (admin) REFERENCES accounts(email)
);

CREATE TABLE projects (
	id serial NOT NULL,
	name varchar(100) NOT NULL,
	description varchar(200) NOT NULL,
	cost numeric(12,2) NOT NULL,
	company_id int4 NOT NULL,
	CONSTRAINT projects_pkey PRIMARY KEY (id),
	CONSTRAINT projects_company_fkey FOREIGN KEY (company_id) REFERENCES companies(id)
);

# --- !Downs
DROP TABLE public.projects;
DROP TABLE public.companies;
DROP TABLE public.sessions;
DROP TABLE public.users;
