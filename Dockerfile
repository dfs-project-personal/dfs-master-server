# Use OpenJDK 21 as the base image
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the Maven executable to the container image
COPY mvnw .
COPY .mvn .mvn

# Copy the project files
COPY pom.xml .
COPY src src

# Build the application
RUN ./mvnw package -DskipTests

# Extract the built JAR file
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# Set the startup command to execute the application
ENTRYPOINT ["java", "-cp", "app:app/lib/*", "com.dfs.dfsmasterserver.DfsMasterServerApplication"]