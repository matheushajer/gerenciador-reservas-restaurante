FROM maven:3.8.4-openjdk-17
WORKDIR /app

RUN git clone https://github.com/matheushajer/gerenciador-reservas-restaurante

COPY . /app

WORKDIR /app/gerenciador-reservas-restaurante
RUN mvn clean test
RUN mkdir -p /app/src/main/resources/static/test-reports/jacoco
RUN cp -r /app/gerenciador-reservas-restaurante/target/site/jacoco/* /app/src/main/resources/static/test-reports/jacoco/

RUN mvn package

RUN cp target/*.jar /app/

EXPOSE 8081

CMD ["java", "-jar", "/app/gerenciadorDeReservas-0.0.1-SNAPSHOT.jar"]