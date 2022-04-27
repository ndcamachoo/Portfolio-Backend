FROM openjdk:17-alpine

VOLUME /tmp

ADD ./target/UserService-0.0.1-SNAPSHOT.jar user-service.jar

RUN apk add --update --no-cache curl

ENTRYPOINT ["java", "-jar", "/user-service.jar"]