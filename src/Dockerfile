# Estágio de build
FROM ubuntu:latest AS build

RUN apt-get update && apt-get install -y openjdk-17-jdk
WORKDIR /app
COPY . .

RUN apt-get install -y maven
RUN mvn clean install

# Estágio de produção
FROM openjdk:17-jdk-slim

EXPOSE 8081

COPY --from=build /app/target/gerenciadorDeReservas-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
