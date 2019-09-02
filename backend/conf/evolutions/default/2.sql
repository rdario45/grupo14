# --- Creation of events table

# --- !Ups

INSERT INTO dev.accounts
(email, status)
VALUES('admin.nutresa@yopmail.com', 'ACTIVE');

INSERT INTO dev.companies
(id, name, admin)
VALUES(1, 'Nutresa SAS', 'admin.nutresa@yopmail.com');

INSERT INTO dev.projects
(id, name, description, cost, company_id)
VALUES(1, 'Naturaeza', 'lorem ipsum.', 50000, 1);


INSERT INTO dev.designs
(id, email, originalPath, project_id)
VALUES(1, 'admin.nutresa@yopmail.com', 'tmp/imagen1.png', 1);

# --- !Downs
delete from dev.designs where id = 1;
delete from dev.projects where id = 1;
delete from dev.companies where id = 1;
delete from dev.accounts where email = 'admin.nutresa@yopmail.com'
