# --- Creation of events table

# --- !Ups

INSERT INTO dev.accounts
(email, password, status)
VALUES('admin.nutresa@yopmail.com', 'A12345*', 'ACTIVE');

INSERT INTO dev.companies
(name, admin)
VALUES('Nutresa SAS', 'admin.nutresa@yopmail.com');

INSERT INTO dev.projects
(name, description, cost, company_id)
VALUES('Naturaeza', 'lorem ipsum.', 50000, 1);


INSERT INTO dev.designs
(email, firstName, lastName, fileName, originalPath, project_id, price)
VALUES('admin.nutresa@yopmail.com', 'Admin', 'Nutresa', 'imagen1.png', 'tmp/imagen1.png', 1, 5000);

# --- !Downs
delete from dev.designs where id = 1;
delete from dev.projects where id = 1;
delete from dev.companies where id = 1;
delete from dev.accounts where email = 'admin.nutresa@yopmail.com'
