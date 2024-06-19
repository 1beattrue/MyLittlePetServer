# Use the official Gradle image to create a build artifact.
# This is based on openjdk:8-jdk.
FROM gradle:8.4-jdk20 AS build

WORKDIR /usr/src/app
COPY . .

WORKDIR /usr/src/app/target

EXPOSE 8080

CMD ["java", "-jar", "petServer-0.0.1-SNAPSHOT.jar"]