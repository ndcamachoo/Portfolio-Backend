FROM openjdk:12
VOLUME /tmp
ADD ./target/springboot-servicio-productos-0.0.1-SNAPSHOT.jar servicio-productos.jar
ENTRYPOINT ["java","-jar","/servicio-productos.jar"]