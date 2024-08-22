# Etapa 1: Construção
FROM maven:3.8.6-amazoncorretto-17 AS build
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Runtime
FROM amazoncorretto:17-alpine-jdk
LABEL author="hugo.hbs64"
COPY --from=build /build/target/ocorrencia-api-1.0.0.jar ocorrencia-api-1.0.0.jar
ENTRYPOINT ["java","-jar","/ocorrencia-api-1.0.0.jar"]