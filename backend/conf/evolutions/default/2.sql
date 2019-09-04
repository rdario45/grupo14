# --- Creation of events table

# --- !Ups

INSERT INTO     accounts
(email, password, status)
VALUES('admin.nutresa@yopmail.com', 'A12345*', 'ACTIVE');

INSERT INTO companies
(name, admin)
VALUES('Nutresa SAS', 'admin.nutresa@yopmail.com');

INSERT INTO projects
(name, description, cost, company_id)
VALUES('Naturaeza', 'lorem ipsum.', 50000, 1);


INSERT INTO designs
 (email, firstName, lastName, originalPath, project_id, price)
 VALUES
 ( 'da.torres58@uniandes.edu.co', 'Admin', 'Nutresa', '/home/diego/Imágenes/Captura de pantalla de 2019-08-27 21-39-35.png', 1, 5000),
 ( 'jc.colemenares@uniandes.edu.co', 'Admin', 'Nutresa', '/home/diego/Imágenes/Captura de pantalla de 2019-08-28 21-06-55.png', 1, 5000),
 ( 'rd.fernandez@@uniandes.edu.co', 'Admin', 'Nutresa', '/home/diego/Imágenes/Captura de pantalla de 2019-08-27 21-49-34.png', 1, 5000);



 # --- !Downs
 delete from designs where id = 1;
 delete from projects where id = 1;
 delete from companies where id = 1;
 delete from accounts where email = 'admin.nutresa@yopmail.com'

