# --- Creation of events table

# --- !Ups

CREATE TABLE users (
	email varchar(100) NOT NULL,
	"password" varchar(200) NOT NULL,
	state varchar(100) NULL,
	CONSTRAINT users_pkey PRIMARY KEY (email)
);

CREATE TABLE sessions (
	id serial NOT NULL,
	"user" varchar(100) NOT NULL,
	token varchar(200) NOT NULL,
	timestamp numeric(12,2) NOT NULL,
	CONSTRAINT sessions_pkey PRIMARY KEY (id),
	CONSTRAINT sessions_user_fkey FOREIGN KEY ("user") REFERENCES users(email)
);

CREATE TABLE companies (
	id serial NOT NULL,
	name varchar(100) NOT NULL,
	admin varchar(100) NOT NULL,
	CONSTRAINT companies_pkey PRIMARY KEY (id),
	CONSTRAINT companies_admin_fkey FOREIGN KEY (admin) REFERENCES users(email)
);

CREATE TABLE projects (
	id serial NOT NULL,
	name varchar(100) NOT NULL,
	description varchar(200) NOT NULL,
	cost numeric(12,2) NOT NULL,
	company int4 NOT NULL,
	CONSTRAINT projects_pkey PRIMARY KEY (id),
	CONSTRAINT projects_company_fkey FOREIGN KEY (company) REFERENCES companies(id)
);


# --- !Downs
DROP TABLE public.projects;
DROP TABLE public.companies;
DROP TABLE public.sessions;
DROP TABLE public.users;
