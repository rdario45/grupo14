# Grupo14 - Infraestructura y Arquitectura.

### Contenido
- Generalidades de Arquitectura e Infraestructura.
- Modelo de datos y diagrama E-R.
- Tecnologias de desarrollo.
- Frameworks, librerias y herramientas.


#### Generalidades de Arquitectura e Infraestructura.

- Diagrama de despliegue:
    
    La aplicación web corre en un servidor dentro de una red privada de la Universidad de los Andes por lo que hay que acceder a través de una VPN, el usuario usando un navegador web ingresa a la aplicación que está alojada en una maquina con ubuntu server de 4 nucleos, 8 GB de memoria RAM  y 40 GB  de disco duro, probablemente HDD. Se usó una aplicación java autocontenida y en el frontend se desarrollo una SPA. 

    La base de datos en MySQL es accedida únicamente desde el servicio de backend de la aplicacion. se usa un servidor web ligero llamado nginx que adicional es proxy reverso y balanceador de carga, (en la aplicación actualmente se usa solo como webserver y proxy reverso). 

    <!-- ![diagrama despliegue](images/diagrama-despliegue.png) -->
    <img src="images/diagrama-despliegue.png" alt="diagrama despliegue" width="800"/>


#### Modelo de datos y Diagrama E-R.

- Diagrama E-R:
    
    El diagrama Entidad Relacion de la aplicacion es el siguiente.
    
    <!-- ![diagrama ER](images/diagrama-ER.png) -->
    <img src="images/diagrama-ER.png" alt="diagrama ER" width="800"/>

    Como entidades principales, se ilustran la compañías, proyectos y diseños que se pueden gestionar en el sitio web. Sin embargo, es necesario tener en cuenta el uso de entidades de soporte como el manejo de sesiones, de cuentas y de status en los diseños. 

#### Tecnologias base.

| Backend   | Frontend  | Database   |
|-----------|-----------|------------|
|![java](images/java-logo.png) |![web](images/css-html-js.jpeg) |![database](images/mysql-logo.png) |

#### Frameworks, librerias y herramientas.

| Docker    | PlayJava  | ReactJs   | Trello    | Slack |
|-----------|-----------|-----------|-----------|-------|
|![Docker](images/docker-logo.jpg) |![play](images/play-logo.png) |![react](images/react-logo.png) |![trello](images/trello-logo.png) |![slack](images/slack-logo.png) |

#### Servicios utilizados de AWS

| VPC   | Route 53  | EBS   | RDS   |
|-------|-----------|-------|-------|
|![trello](images/aws/vpc-logo.jpeg) |![trello](images/aws/Route53-logo.png) |![EBS](images/aws/ebs-logo.png) |![RDS](images/aws/rds-logo.png) |


- Amazon Virtual Private Cloud (VPC)
- Amazon Route 53
- Amazon Elastic Block Store (EBS)
- Amazon Relational Database Service (RDS)

posiblemente
- Amazon Elastic Compute Cloud / 

| Elastic IP    |
|---------------|
|![elascticsearch](images/aws/elasticsearch-logo.png) |


## En construccion:

### Despliegue en AWS

1. Teniendo `$HOME = /home/ubuntu` crear las carpetas app, infra y www.

#### Despliegue de base de datos en docker (simplificado)
docker run --name mysql-db -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=dev  -p 3306:3306 -d mysql

#### ejecucion de la aplicacion backend en el server
```
unzip target/universal/designmatch-1.0.0.zip \  
bash designmatch-1.0.0/bin/designmatch
bash designmatch-1.0.0/bin/designmatch -Dhttp.port=1234 -Ddb.default.password=moresecret -Dpidfile.path=other/RUNNING_PID

```


\<\< [volver](../README.md)