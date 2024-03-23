FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN apt-get install manven -y
RUN mvn clean install

FROM openjdk:17-jdk-slim

EXPOSE 8081

COPY --from=build /target/gerenciadorDeReservas-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
