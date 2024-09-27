# Use OpenJDK 21 as the base image
FROM eclipse-temurin:21-jdk-alpine AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the Maven executable to the container image
COPY mvnw .
COPY .mvn .mvn

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN ./mvnw dependency:go-offline

# Copy the project source
COPY src src

# Build the application inside the container
RUN ./mvnw clean package -DskipTests

# Package the application
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the builder stage to the new image
COPY --from=builder /app/target/*.jar app.jar

# Set the ENTRYPOINT command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]