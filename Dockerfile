# Use the official Gradle image to create a build artifact.
# This is based on openjdk:8-jdk.
FROM gradle:8.4-jdk20 AS build

# Copy local code to the container image.
COPY --chown=gradle:gradle . /home/gradle/project

# Set the working directory
WORKDIR /home/gradle/project

# Build the application
RUN gradle build --no-daemon

# Stage 2: Run
FROM openjdk:20-slim

# Copy the jar file from the build stage to the run stage
COPY --from=build /home/gradle/project/build/libs/*.jar /app/app.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]