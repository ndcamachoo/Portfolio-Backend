# Imagen que se va a ejecutar - Java 17
FROM openjdk:17-alpine

# Volumen de datos
VOLUME /tmp

# Exponer el puerto
EXPOSE 8888

# Agregar el artefacto al contenedor
ADD ./target/Microservicio-ConfigServer-0.0.1-SNAPSHOT.jar config-server.jar

# Ejecutar el artefacto
ENTRYPOINT ["java", "-jar", "/config-server.jar"]



