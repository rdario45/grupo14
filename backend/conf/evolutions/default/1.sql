# --- Creation of events table

# --- !Ups


CREATE TABLE accounts (
	email varchar(100) NOT NULL,
	password varchar(200) NOT NULL,
	status varchar(10) NULL,
	CONSTRAINT accounts_pkey PRIMARY KEY (email)
);

CREATE TABLE sessions (
	id int(11) NOT NULL AUTO_INCREMENT,
	account varchar(100) NOT NULL,
	token TEXT NOT NULL,
	timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT sessions_pkey PRIMARY KEY (id),
	CONSTRAINT sessions_user_fkey FOREIGN KEY (account) REFERENCES accounts(email)
);

CREATE TABLE companies (
	id int(11) NOT NULL AUTO_INCREMENT,
	name varchar(100) NOT NULL,
	admin varchar(100) NOT NULL,
	CONSTRAINT companies_pkey PRIMARY KEY (id),
	CONSTRAINT companies_account_fkey FOREIGN KEY (admin) REFERENCES accounts(email)
);

CREATE TABLE projects (
	id int(11) NOT NULL AUTO_INCREMENT,
	name varchar(100) NOT NULL,
	description varchar(200) NOT NULL,
	cost numeric(12,2) NOT NULL,
	company_id int(11) NOT NULL,
	CONSTRAINT projects_pkey PRIMARY KEY(id),
	CONSTRAINT projects_company_fkey FOREIGN KEY (company_id) REFERENCES companies(id)
);

CREATE TABLE designs (
  id int(11) NOT NULL AUTO_INCREMENT,
  email varchar(45) DEFAULT NULL,
  firstName varchar(100) DEFAULT NULL,
  lastName varchar(100) DEFAULT NULL,
  designStatus varchar(10) DEFAULT 'PROCESSING',
  fileName varchar(50) NOT NULL,
  originalPath varchar(250) NOT NULL,
  resizedPath varchar(250) DEFAULT NULL,
  timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  project_id int(11) NOT NULL,
  CONSTRAINT designs_pkey PRIMARY KEY(id),
  CONSTRAINT designs_project_fkey FOREIGN KEY (project_id) REFERENCES companies(id)
);

# --- !Downs
DROP TABLE designs;
DROP TABLE projects;
DROP TABLE companies;
DROP TABLE sessions;
DROP TABLE accounts;