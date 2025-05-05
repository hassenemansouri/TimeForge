FROM openjdk:17-jdk-slim

# Install curl for health checks
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY target/Project_Task-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8400

ENTRYPOINT ["java", "-jar", "app.jar"]