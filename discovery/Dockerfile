# Use OpenJDK 17 as base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR file (replace with your JAR name)
COPY target/discovery-0.0.1-SNAPSHOT.jar app.jar

# Expose Eureka's default port
EXPOSE 8761

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]