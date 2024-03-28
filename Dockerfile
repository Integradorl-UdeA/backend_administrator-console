LABEL authors="Laura"
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY ./target/consola-lis-api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8092
CMD ["java", "-jar", "app.jar"]