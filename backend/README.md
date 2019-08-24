# Base proyecto grupo 14 backend
====

### Contenido
- Conexion a base de datos.
- Herramientas adicionales.

### Conexion a base de datos
- Debe existir un servicio de base de datos accesible desde la maquita. y se debe actualizar el archivo
    `conf/application.conf ` con las credenciales de correctas.

#### Herramientas adicionales
- Servicio de base de datos local utilizando Docker: 
    > Si se desea utilizar postgresql para la base de datos y docker para su despliegue debe
    [instalar docker](https://docs.docker.com/install/linux/docker-ce/ubuntu/) y ejecutar el comando:

```shell script
docker run --name postgres-db -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=123 -e POSTGRES_DB=db -p 5432:5432 -d postgres
``` 

- Documentacion del API en Postman:
    > Se debe tener instalado [postman] y acceder al siguiente enlace. 
    [postman link](https://www.getpostman.com/collections/82ad30197a2da9e0fa74)