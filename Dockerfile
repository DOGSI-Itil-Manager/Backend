FROM maven:3.8.5-openjdk-11-slim AS package
RUN mkdir /deployment
WORKDIR /deployment
COPY src /deployment/src
COPY pom.xml /deployment/pom.xml
RUN mvn package -DskipTests

FROM openjdk:17-alpine AS deploy
VOLUME /deployment
WORKDIR /deployment
COPY config /deployment/config
COPY --from=package /deployment/target/itil-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "itil-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=prod"]
