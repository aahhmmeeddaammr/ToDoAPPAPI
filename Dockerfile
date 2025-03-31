# Use a valid Maven image with OpenJDK 17 or 21
FROM maven:3.8.5-openjdk-17 AS build

# Set working directory inside the container
WORKDIR /app

# Copy all project files
COPY . .

# Build the application
RUN mvn clean package -DskipTests

# Use a valid OpenJDK runtime image
FROM eclipse-temurin:21-jdk-slim
WORKDIR /app

# Copy the built JAR from the previous stage
COPY --from=build /app/target/ToDoAPP-0.0.1-SNAPSHOT.jar ToDoAPP.jar

# Expose the application port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "ToDoAPP.jar"]
