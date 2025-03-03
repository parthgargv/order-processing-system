# Use a lightweight JDK image
FROM openjdk:17-jdk-slim

# Set work directory
WORKDIR /app

# Copy the JAR file
COPY target/order-processing.jar app.jar

# Expose the application's port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
