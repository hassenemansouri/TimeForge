FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the built JAR file (replace with your actual JAR name)
COPY target/gateway-0.0.1-SNAPSHOT.jar app.jar

# Expose the gateway port
EXPOSE 8222

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]