FROM openjdk:17-alpine

VOLUME /tmp

ADD ./target/SerieService-0.0.1-SNAPSHOT.jar serie-service.jar

RUN apk add --update --no-cache curl

ENTRYPOINT ["java", "-jar", "/serie-service.jar"]