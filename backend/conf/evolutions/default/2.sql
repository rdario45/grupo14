# --- Creation of events table

# --- !Ups


insert into accounts (email, status) values ('info@cocacola.com', 'ACTIVE');
insert into companies (name, admin) values ('Coca-cola', 'info@cocacola.com');
insert into projects (name, description, cost, company_id) values ('Publicidad Colombia', 'Publicidad navidad Colombia', 50000,
			(select id from companies where admin = 'info@cocacola.com')
        );



insert into designs (id, email, originalPath, project_id)
values
(1,'diegoatorres@gmail.com','/home/diego/Imágenes/Captura de pantalla de 2019-08-27 21-39-35.png', (select id from projects where name = 'Publicidad Colombia')),
(2,'da.torres58@uniandes.edu.co','/home/diego/Imágenes/Captura de pantalla de 2019-08-27 21-40-26.png', (select id from projects where name = 'Publicidad Colombia')),
(3,'ruben@uniandes.edu.co','/home/diego/Imágenes/Captura de pantalla de 2019-08-27 21-40-27.png', (select id from projects where name = 'Publicidad Colombia')),
(4,'camilo@uniandes.edu.co','/home/diego/Imágenes/Captura de pantalla de 2019-08-27 21-40-36.png', (select id from projects where name = 'Publicidad Colombia'));

# --- !Downs
