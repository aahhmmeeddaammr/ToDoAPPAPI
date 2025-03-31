# Use a valid Maven image with Java 21 (since Java 23 is not available)
FROM maven:3.8.5-openjdk-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Use a valid OpenJDK runtime image
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/ToDoAPP-0.0.1-SNAPSHOT.jar ToDoAPP.jar

# Expose the application port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "ToDoAPP.jar"]
