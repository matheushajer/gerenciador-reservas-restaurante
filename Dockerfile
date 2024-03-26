FROM maven:3.8.4-openjdk-17 as build

WORKDIR /app

COPY . /app

RUN mvn clean package -DskipTests

FROM openjdk:17

COPY --from=build /app/target/gerenciadorDeReservas-0.0.1-SNAPSHOT.jar /app/gerenciadorDeReservas.jar

WORKDIR /app

EXPOSE 8081

CMD ["java", "-jar", "gerenciadorDeReservas.jar"]
