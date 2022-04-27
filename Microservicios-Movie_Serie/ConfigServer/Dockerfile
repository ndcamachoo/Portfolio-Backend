FROM openjdk:17-alpine

VOLUME /tmp

EXPOSE 8888

ADD ./target/ConfigServer-0.0.1-SNAPSHOT.jar config-server.jar

RUN apk add --update --no-cache curl

ENTRYPOINT ["java", "-jar", "/config-server.jar"]



