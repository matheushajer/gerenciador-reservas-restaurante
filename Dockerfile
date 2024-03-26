FROM maven:3.8.4-openjdk-17

RUN mvn clean package -DskipTests

# Expoe a porta 8081
EXPOSE 8081
WORKDIR /app

# Define o comando para iniciar a API
CMD java -jar $(ls | grep .jar)
