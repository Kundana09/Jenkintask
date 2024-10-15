# Use the official OpenJDK 17 slim image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the jar file to the container
COPY target/Task1-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port (use 8081 if that's your server's port)
EXPOSE 8090

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
