# Grupo14 - Infraestructura y Arquitectura.

### Contenido
- Generalidades de Arquitectura e Infraestructura.
- Modelo de datos y diagrama E-R.
- Tecnologias de desarrollo.
- Frameworks, librerias y herramientas.
- Despliegue en ambiente local.
- Despliegue en Amazon Web Services.
- Troubleshooting

### Generalidades de Arquitectura e Infraestructura.

- Diagrama de despliegue:
    
    La aplicación web corre en un servidor dentro de una red privada de la Universidad de los Andes por lo que hay que acceder a través de una VPN, el usuario usando un navegador web ingresa a la aplicación que está alojada en una maquina con ubuntu server de 4 nucleos, 8 GB de memoria RAM  y 40 GB  de disco duro, probablemente HDD. Se usó una aplicación java autocontenida y en el frontend se desarrollo una SPA. 

    La base de datos en MySQL es accedida únicamente desde el servicio de backend de la aplicacion. se usa un servidor web ligero llamado nginx que adicional es proxy reverso y balanceador de carga, (en la aplicación actualmente se usa solo como webserver y proxy reverso). 

    <!-- ![diagrama despliegue](images/diagrama-despliegue.png) -->
    <img src="images/diagrama-despliegue.png" alt="diagrama despliegue" width="800"/>


### Modelo de datos y Diagrama E-R.

- Diagrama E-R:
    
    El diagrama Entidad Relacion de la aplicacion es el siguiente.
    
    <!-- ![diagrama ER](images/diagrama-ER.png) -->
    <img src="images/diagrama-ER.png" alt="diagrama ER" width="800"/>

    Como entidades principales, se ilustran la compañías, proyectos y diseños que se pueden gestionar en el sitio web. Sin embargo, es necesario tener en cuenta el uso de entidades de soporte como el manejo de sesiones, de cuentas y de status en los diseños. 

### Tecnologias base.

| Backend   | Frontend  | Database   |
|-----------|-----------|------------|
|![java](images/java-logo.png) |![web](images/css-html-js.jpeg) |![database](images/mysql-logo.png) |

### Frameworks, librerias y herramientas.

| Docker    | PlayJava  | ReactJs   | Trello    | Slack |
|-----------|-----------|-----------|-----------|-------|
|![Docker](images/docker-logo.jpg) |![play](images/play-logo.png) |![react](images/react-logo.png) |![trello](images/trello-logo.png) |![slack](images/slack-logo.png) |


### Despliegue en ambiente local. (En construccion)

- Base de datos mysql usando docker:
```
docker run --name mysql-db --restart always -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=dev  -p 3306:3306 -d mysql
```
- Ejecucion servidor web local.
```
frontend/ npm run start
```
- Ejecucion servicio backend.
```
backend/ sbt run
```


### Despliegue en Amazon Web Services

#### Servicios utilizados de AWS

| VPC   | Route 53  | EC2   | EBS   | RDS   | SES   |
|-------|-----------|-------|-------|-------|-------|
|![trello](images/aws/vpc-logo.jpeg) |![trello](images/aws/Route53-logo.png) |![EC2](images/aws/ec2-logo.png) |![EBS](images/aws/ebs-logo.png) |![RDS](images/aws/rds-logo.png) |![SES](images/aws/ses-logo.png) |


- Amazon Virtual Private Cloud (VPC)
- Amazon Route 53
- Amazon EC2
- Amazon Elastic Block Store (EBS)
- Amazon Relational Database Service (RDS)
- Amazon Simple Email Service (SES)


posiblemente
- Amazon Elastic Compute Cloud / 

| Elastic IP    |
|---------------|
|![elascticsearch](images/aws/elasticsearch-logo.png) |

#### Proceso de despliegue

Teniendo `$HOME = /home/ubuntu` y los siguientes directorios:
---
    ~
    ├── infra                   # infra resources.
    |     ├── README.md         # this file.
    |     ├── nginx              
    |     |     └── nginx.conf  # production nginx config file
    |     └── config              
    |           └── prod.conf   # production app config file
    ├── app                     # app folder.
    ├── web                     # web application.
    └── data                    # data folder.
---

1. Copie el contenido de la carpeta build (frontend) en `/home/ubuntu/web`

2. Copie el archivo zip generado por play framework en `/home/ubuntu/app`

3. Debe existir un servicio de base de datos (RDS), configure las credenciales de acceso en el archivo `prod.conf`

4. Despliegue de servidor web nginx con la configuracion de produccion.
    
    ```bash
    docker run --name webserver --restart always  \
        -v /home/ubuntu/infra/nginx/nginx.conf:/etc/nginx/nginx.conf:ro \
        -v /home/ubuntu/web/:/usr/share/nginx/html/:ro -d --network host  nginx
    ```

5. Descomprima el archivo zip, ubiquece en el directorio `bin/` y ejecute la aplicacion de backend:

    ```bash
    nohup bash designmatch -Dconfig.file=/home/ubuntu/infra/config/prod.conf &
    ```

### troubleshooting

* Si se encuentra que la maquina no tiene expuestos los puertos necesarios para conectarse por http etc, configure el firewall:

```bash
sudo ufw status verbose
sudo ufw allow ssh
sudo ufw enable
sudo ufw allow http
sudo ufw allow https
sudo ufw allow 3306
````


\<\< [volver](../README.md)