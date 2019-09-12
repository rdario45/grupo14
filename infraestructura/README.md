# Grupo14 - Infraestructura y Arquitectura.

### Contenido
- Generalidades de Arquitectura e Infraestructura.
- Modelo de datos y Diagrama E-R.
- Tecnologias de desarrollo base.
- Frameworks, librerias y herramientas.

#### Modelo ER.

El modelo Entidad Relacion de la aplicacion es el siguiente:

![diagrama ER](images/diagrama-ER.png)


#### Tecnologias base.

| Backend                      | Frontend                       | Database                          |
|------------------------------|--------------------------------|-----------------------------------|
|![java](images/java-logo.png) |![web](images/css-html-js.jpeg) |![database](images/mysql-logo.png) |

#### Frameworks, librerias y herramientas.

| PlayJava                     | ReactJs                        | Docker                           | JWT                        | Trello                           |
|------------------------------|--------------------------------|----------------------------------|----------------------------|----------------------------------|
|![play](images/play-logo.png) |![react](images/react-logo.png) |![Docker](images/docker-logo.jpg) |![jwt](images/jwt-logo.png) |![trello](images/trello-logo.png) |



### en construccion

#### Suponiendo los siguientes archivos en el server
 (estructura de carpetas en el servidor.)

#### Despliegue de base de datos en docker (simplificado)
docker run --name mysql-db -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=dev  -p 3306:3306 -d mysql

#### ejecucion de la aplicacion backend en el server
```
unzip target/universal/designmatch-1.0.0.zip \  
bash designmatch-1.0.0/bin/designmatch
bash designmatch-1.0.0/bin/designmatch -Dhttp.port=1234 -Ddb.default.password=moresecret -Dpidfile.path=other/RUNNING_PID

```


\<\< [volver](../README.md)