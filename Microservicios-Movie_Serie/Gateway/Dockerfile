FROM openjdk:17-alpine

VOLUME /tmp

EXPOSE 8090

RUN apk add --update --no-cache curl

ADD ./target/Gateway-0.0.1-SNAPSHOT.jar gateway.jar

ENTRYPOINT ["java", "-jar", "/gateway.jar"]