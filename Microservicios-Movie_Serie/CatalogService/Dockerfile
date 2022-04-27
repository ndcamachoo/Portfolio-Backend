FROM openjdk:17-alpine

VOLUME /tmp

ADD ./target/CatalogService-0.0.1-SNAPSHOT.jar catalog-service.jar

RUN apk add --update --no-cache curl

ENTRYPOINT ["java", "-jar", "/catalog-service.jar"]