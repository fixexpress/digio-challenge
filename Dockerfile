FROM maven:3.9.4-eclipse-temurin-21 AS builder
WORKDIR /app

# Copia o arquivo pom.xml e o código fonte
COPY pom.xml ./ 
COPY src ./src

# Compila o projeto sem rodar os testes
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copia o JAR gerado para o contêiner final
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta da aplicação
EXPOSE 8080

# Define o comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
