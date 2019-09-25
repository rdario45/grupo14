# Grupo14 - Aplicacion Backend.

Proyecto creado con [Play Java](https://www.playframework.com/documentation/2.7.x/Introduction) 
usa [SBT](https://www.scala-sbt.org/1.x/docs/index.html) como *build tool*, por lo que se debe tener 
instalado para poder compilar y ejecutar la aplicacion. Para generacion del proyecto se uso: `sbt new playframework/play-java-seed.g8`.


### Contenido
- Consideraciones previas.
- Despliegue en ambiente local.
- Distribucion en ambientes remotos.
- Herramientas adicionales.

### Consideraciones previas.

- La aplicacion cuenta con un proceso batch que procesa las imagenes cargadas a la aplicacion. para ello es necesario que exista el directorio `/tmp/` o lo que se tenga configurado en el archivo `conf/application.conf` bajo la propiedad `files.workdir`.

### Despliegue en ambiente local.

- **Conexion a base de datos:** <br>
    Se debe tener acceso a un servicio de base de datos y actualizar la configuracion del archivo `conf/application.conf` con las credenciales correctas.

    - **Servicio de base de datos local MySql usando *Docker*:**  <br>
        Si se desea utilizar MySql para la base de datos con docker para el despliegue local debe tener [inatalado docker](https://docs.docker.com/install/linux/docker-ce/ubuntu/) y ejecutar el comando:

        ```shell script
        docker run --name mysql-db -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=dev  -p 3306:3306 -d mysql
        ``` 
        Esto creará una instancia de la base de datos mysql corriendo por el puerto 3306 accesible desde localhost. 


- **Ejecutar la aplicacion en modo desarrollo:** <br>
    Ejecutando el siguiente comando de **SBT** se lanzará la aplicación por el puerto `9000` o lo que se tenga configurado en el archivo `build.sbt` 
    ubicado en la raiz del proyecto.
    ```
    sbt run
    ```
    Una vez la aplicacion esté esperando por un request, se puede abrir la url ` http://localhost:9000 ` en un web browser 
    para ver el API disponible, visible tambien en el archivo `conf/routes`.


#### Distribucion en ambientes remotos.
- El comando `sbt dist` construye una version binaria que puede ser desplegada en un servidor, creando un archivo zip en el folder `target/universal/`.
 
    Luego de descomprimir el archivo se puede proceder a ejecutar el binario:
    ```
    target/universal/designmatch-*.*.*.zip
    ```
    El archivo *.zip* creado utiliza el nombre de la aplicacion y la version, configurable en el archivo `build.sbt` ubicado en la raiz del proyecto.
    
#### Herramientas adicionales

- Documentacion del API en Postman:
  > Se debe tener instalado [postman] y acceder al siguiente enlace. 
  [postman link](https://www.getpostman.com/collections/c9ee43232c4b30f5109f)

---
\<\< [volver](../README.md)
