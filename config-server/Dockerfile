FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Copy the built JAR
COPY target/config-server-0.0.1-SNAPSHOT.jar app.jar

# Copy configuration files
COPY src/main/resources/configuration/ /config/

EXPOSE 8888

ENTRYPOINT ["java", "-jar", "app.jar"]