FROM openjdk:17-alpine

VOLUME /tmp

EXPOSE 8761

RUN apk add --update --no-cache curl

ADD ./target/EurekaServer-0.0.1-SNAPSHOT.jar eureka-server.jar

ENTRYPOINT ["java", "-jar", "/eureka-server.jar"]



