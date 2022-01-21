FROM openjdk:12
VOLUME /tmp
EXPOSE 9100
ADD ./target/springboot-servicio-oauth-0.0.1-SNAPSHOT.jar servicio-oauth.jar
ENTRYPOINT ["java","-jar","/servicio-oauth.jar"]