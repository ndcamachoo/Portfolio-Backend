FROM openjdk:17-alpine

VOLUME /tmp

EXPOSE 9100

ADD ./target/OAuthService-0.0.1-SNAPSHOT.jar oauth-service.jar

RUN apk add --update --no-cache curl

ENTRYPOINT ["java", "-jar", "/oauth-service.jar"]