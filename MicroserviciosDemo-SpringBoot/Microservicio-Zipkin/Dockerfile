# Imagen que se va a ejecutar - Java 17
FROM openjdk:17-alpine

# Volumen de datos
VOLUME /tmp

# Exponer el puerto
EXPOSE 9411

# Agregar el artefacto al contenedor
ADD ./zipkin-server-2.23.16-exec.jar zipkin-server.jar

# Ejecutar el artefacto
ENTRYPOINT ["java", "-jar", "/zipkin-server.jar"]
