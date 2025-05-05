# Use OpenJDK 17 as base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR file (replace with your JAR name)
COPY target/User_StrategicPartnership-0.0.1-SNAPSHOT.jar app.jar


EXPOSE 8100

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]