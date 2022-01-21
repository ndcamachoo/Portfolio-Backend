# Imagen que se va a ejecutar - Java 17
FROM openjdk:17-alpine

# Volumen de datos
VOLUME /tmp

# Exponer el puerto
EXPOSE 8761

# Agregar el artefacto al contenedor
ADD ./target/Microservicio-EurekaServer-0.0.1-SNAPSHOT.jar eureka-server.jar

# Ejecutar el artefacto
ENTRYPOINT ["java", "-jar", "/eureka-server.jar"]



