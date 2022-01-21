# Imagen que se va a ejecutar - Java 17
FROM openjdk:17-alpine

# Volumen de datos
VOLUME /tmp

# Agregar el artefacto al contenedor
ADD ./target/Microservicio-Usuarios-0.0.1-SNAPSHOT.jar microservice-usuarios.jar

# Ejecutar el artefacto
ENTRYPOINT ["java", "-jar", "/microservice-usuarios.jar"]