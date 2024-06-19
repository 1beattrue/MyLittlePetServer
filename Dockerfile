# Use a multi-stage build to reduce the final image size

# Stage 1: Build
FROM gradle:8.1.1-jdk17 AS build
WORKDIR /app
COPY . /app

# Build the application
RUN gradle installDist --no-daemon

# Stage 2: Run
FROM openjdk:17-jdk-slim

# Define environment variables
ENV APP_HOME /app
WORKDIR $APP_HOME

# Copy only the distribution from the build stage
COPY --from=build /app/build/install /app

# Expose port for application
EXPOSE 8080

# Start the application
ENTRYPOINT ["./my-little-pet/bin/my-little-pet"]
