# Imagen que se va a ejecutar - Java 17
FROM openjdk:17-alpine

# Volumen de datos
VOLUME /tmp

# Exponer el puerto
EXPOSE 8002

# Agregar el artefacto al contenedor
ADD ./target/Microservicio-Item-0.0.1-SNAPSHOT.jar microservice-items.jar

# Ejecutar el artefacto
ENTRYPOINT ["java", "-jar", "/microservice-items.jar"]