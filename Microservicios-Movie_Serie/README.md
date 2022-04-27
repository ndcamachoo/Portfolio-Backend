![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-%234ea94b.svg?style=for-the-badge&logo=mongodb&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

# Microservicios - Movies/Series Backend

Este proyecto es un simple modelos de código para el patrón de arquitectura de microservicios utilizando Spring Cloud, Spring Boot, Rabbit, Zipkin y Docker.

## Services

Los servicios funcionales siguen estando descompuestos en tres servicios básicos.
- Movie
- Serie
- Catalog

Los servicios _**Movie**_ y _**Serie**_ pueden probarse, construirse y desplegarse de forma independiente entre ellos, pero el 
servicio _**Catalog**_ depende del funcionamiento de los servicios anteriores.

### Auth service
Proporciona varias API para la autenticación y autorización de usuarios con OAuth 2.0 y con el microservicio _**User**_

| Método | Ruta              | Descripción                                   | 
|--------|-------------------|-----------------------------------------------|
| POST   | /security/oauth/token  | Obtiene un nuevo token de acceso y el token de actualización | 

### Movie service
Contiene la API relacionada con la creación y visualización de la información de cada película (Movie). En este servicio también usamos _**RabbitMQ**_ como broker de mensajería para el registro de nuevas películas que posteriormente será un evento para _**Catalog service**_.
Este servicio utiliza como motor de base de datos para su almacenamiento _**MySQL**_.

| Método | Ruta              | Descripción                                   | 
|--------|-------------------|-----------------------------------------------|
| GET   | /movies/{{ID}}  | Obtiene la información de la película en base a su ID |
| POST  | /movies         | Permite el almacenamiento de una nueva película en la base de datos. |

### Serie service
Contiene la API relacionada con la visualización de la información de cada Serie. En este servicio también usamos _**RabbitMQ**_ como broker de mensajería para el registro de nuevas series que posteriormente será un evento para _**Catalog service**_.
Este servicio utiliza como motor de base de datos para su almacenamiento _**MongoDB**_.

| Método | Ruta              | Descripción                                   | 
|--------|-------------------|-----------------------------------------------|
| GET   | /series  | Obtiene todos los registros de series en la base de datos. |
| GET | /series/{{ID}} | Obtiene la información de la serie en base a su ID |
| GET | /series/genres/{genre} | Obtiene todos los registros de series en base a un genero |
| POST | / | Permite el almacenamiento de una nueva serie en la base de datos. |

### Catalog service
Contiene la API relacionada con la visualización de la información de cada catalogo (Catalog). En este servicio también usamos _**RabbitMQ**_ como broker de mensajería suscribiéndose a las queues en las que el servicio _**Movie service**_ y _**Movie Service**_ se comunican, esto con el fin de actualizar los registros de _**Catalog**_ en base a los últimos registros de _**Movie**_ y _**Serie**_.
Este servicio utiliza como motor de base de datos para su almacenamiento _**MongoDB**_.

| Método | Ruta              | Descripción                                   | 
|--------|-------------------|-----------------------------------------------|
| GET   | /catalogs/{genre}  | Obtiene un catálogo de un género en base a los registros de películas y series. |

## Infraestructura
Para la arquitectura de microservicios se basa en Spring Cloud que proporciona 
amplias herramientas de soporte como el balanceador de carga, el registro de servicios, la monitorización y la configuración.

![Arquitectura microservicios del proyecto](https://i.ibb.co/H20mpH9/Arquitectura-Microservicio.png)

### Config
[Spring Cloud Config](http://cloud.spring.io/spring-cloud-config/spring-cloud-config.html) es un servicio de configuración centralizado y escalable horizontalmente para sistemas distribuidos. Utiliza una capa de repositorio que actualmente admite el almacenamiento local y Git. 

[Repositorio de configuración](https://github.com/ndcamachoo/PublicConfigRepo)
    

### Database

Utilizo MySQL para el almacenamiento persistente de datos para varios servicios en esta aplicación. La configuración de los servicios que consumen este motor, son los siguientes:

```yml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: ${DB_USERNAME}
    password: ${DB_PASS}
    url: jdbc:mysql://${DB_HOST}:${DB_PORT}/${DB_NAME}
  jpa:
    database-platform: org.hibernate.dialect.MySQL8Dialect
    hibernate:
      ddl-auto: create-drop
```

### API Gateway

Como se puede ver, hay tres servicios básicos, que exponen la API externa al cliente. En los sistemas, este número puede crecer muy rápidamente, así como la complejidad de todo el sistema. 

En teoría, un cliente podría hacer peticiones a cada uno de los microservicios constantemente. Pero, obviamente, hay desafíos y limitaciones con esta opción, como la necesidad de conocer todas las direcciones de los puntos finales, realizar una solicitud http para cada paquete de información por separado, y fusionar el resultado en el lado del cliente. Otro problema es que los protocolos no son amigables con la web, que podrían ser utilizados en el backend.

Por lo general, un enfoque mucho mejor es utilizar API Gateway ya que se trata de un único punto de entrada al sistema, implementando una gestión de las solicitudes dirigiéndolas al servicio backend correspondiente. 

```yml

spring:
  cloud:
    gateway:
      routes:
        - id: movie-service
          uri: lb://movie-service
          predicates:
            - Path=/movies/**
          filters:
            - name: CircuitBreaker
              args:
                name: breaker-backend
                fallbackUri: forward:/movies/fallback
        - id: serie-service
          uri: lb://serie-service
          predicates:
            - Path=/series/**
          filters:
            - name: CircuitBreaker
              args:
                name: breaker-backend
                fallbackUri: forward:/series/fallback
        - id: catalog-service
          uri: lb://catalog-service
          predicates:
            - Path=/catalogs/**
          filters:
            - name: CircuitBreaker
              args:
                name: breaker-backend
                fallbackUri: forward:/catalogs/fallback
        - id: user-service
          uri: lb://user-service
          predicates:
            - Path=/users/**
        - id: oauth-service
          uri: lb://oauth-service
          predicates:
            - Path=/security/**
          filters:
            - StripPrefix=1

```

### Service Discovery
En este proyecto utilizo Netflix Eureka. Eureka es un buen ejemplo del patrón de descubrimiento del lado del cliente, cuando el cliente es responsable de determinar las ubicaciones de las instancias de servicio disponibles (utilizando el servidor de Registro) y el equilibrio de carga de las solicitudes a través de ellos.

``` yml
spring:
  application:
    name: movie-service

eureka:
  client:
    service-url:
      defaultZone: http://eureka-server:8761/eureka/
```

Ahora, al iniciarse la aplicación, se registrará en el servidor Eureka y proporcionará metadatos, como el host y el puerto, la URL del indicador de salud, la página de inicio, etc. Eureka recibe mensajes de heartbeat de cada instancia perteneciente a un servicio y registrando el servicio dentro del servidor.

## Development 
Para el despliegue, todo el código fuente será compilado y empaquetado como un jar. Estos archivos jar se utilizarán posteriormente para crear la imagen de cada servicio.

Para la ejecución de cada aplicación será implementado mediante un archivo _**Dockerfile**_
como por ejemplo

```Dockerfile
FROM openjdk:17-alpine

VOLUME /tmp

EXPOSE 8080

RUN apk add --update --no-cache curl

ADD ./target/ServiceName-0.0.1-SNAPSHOT.jar servicename.jar

ENTRYPOINT ["java", "-jar", "/servicename.jar"]
```

Para la orquestación y despliegue de todos los servicios mediante los archivos Dockerfile, se utiliza Docker compose y para la ejecución del mismo se utiliza el siguiente comando en la consola

```$xslt
docker compose up -d
```

## Documentación en Postman

https://documenter.getpostman.com/view/16557699/UyrDDvXx

# Autores ✒️

- [Nelson David Camacho Ovalle](https://github.com/ndcamachoo)