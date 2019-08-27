# Grupo14 - DesignMatch.

Proyecto hecho en [Play](https://www.playframework.com/documentation/2.7.x/Introduction) 
usa [sbt](https://www.scala-sbt.org/1.x/docs/index.html)  como build tool, por lo que se debe tener 
instalado para poder compilar y ejecutar la aplicacion. 
Para generacion del proyecto se uso: `sbt new playframework/play-java-seed.g8`.

### Contenido

- Conexion a base de datos.
- Ejecucion local.
- Despliegue.
- Herramientas adicionales.

### Conexion a base de datos

- Se debe crear un servicio de base de datos accesible desde la maquita y actualizar el archivo
    `conf/application.conf ` con las credenciales correctas.

### Ejecucion local

- Teniendo el servicio de la base de datos activo se procede a ejecutar la aplicacion en local con el comando: 
    ```
    sbt run
    ```
    Esto hara build y correra la aplicacion por el puerto `9000` o lo que se tenga configurado en el archivo `build.sbt` 
    ubicado en la raiz del proyecto.
    
    Una vez la aplicacion este esperando por un request, se puede abrir la url ` http://localhost:9000 ` en un web browser 
    para ver el API disponible, visible tambien en el archivo `conf/routes`.

#### Distribucion.
- El comando `sbt dist` construye una version binaria que puede ser desplegada en un servidor.
Creando un archivo zip en el folder `target/universal/` que incluye todos los jars necesarios para ejecutar la aplicacion.

    Luego de descomprimir el archivo se puede proceder a ejecutar el binario:
    ```
    unzip target/universal/designmatch-1.0.0.zip
    
    bash designmatch-1.0.0/bin/designmatch
    ```
    
    Adicional se pueden enviar parametros de configuracion:
    
    ```
    bash designmatch-1.0.0/bin/designmatch -Dhttp.port=1234 -Ddb.default.password=moresecret -Dpidfile.path=other/RUNNING_PID
    ```

#### Herramientas adicionales

- Servicio de base de datos local utilizando Docker: 
    > Si se desea utilizar postgresql para la base de datos y docker para su despliegue local debe
  [instalar docker](https://docs.docker.com/install/linux/docker-ce/ubuntu/) y ejecutar el comando:

    ```shell script
    docker run --name postgres-db -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=123 -e POSTGRES_DB=db -p 5432:5432 -d postgres
    ``` 

- Documentacion del API en Postman:
  > Se debe tener instalado [postman] y acceder al siguiente enlace. 
  [postman link](https://www.getpostman.com/collections/82ad30197a2da9e0fa74)

---
\<\< [volver](../README.md)