FROM maven:3.8.4-openjdk-17-slim AS build
LABEL authors="Laura"
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests -Pprod

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/consola-lis-api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8092
CMD ["java", "-jar", "app.jar"]
