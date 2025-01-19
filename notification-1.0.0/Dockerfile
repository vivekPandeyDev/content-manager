# Use official OpenJDK runtime
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the built JAR file to the container
COPY target/notification-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]


# Copy wait-for-it script
#COPY wait-for-it.sh /usr/local/bin/wait-for-it
#RUN chmod +x /usr/local/bin/wait-for-it

# Use the script in the entrypoint
#ENTRYPOINT ["wait-for-it", "rabbitmq:5672", "--", "java", "-jar", "app.jar"]

