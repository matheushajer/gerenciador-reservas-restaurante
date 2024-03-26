FROM maven:3.8.4-openjdk-17 as build

WORKDIR /app

COPY . /app

RUN mvn clean package -DskipTests

WORKDIR /app

EXPOSE 8081

CMD ["java", "-jar", "/app/target/gerenciadorDeReservas-0.0.1-SNAPSHOT.jar"]