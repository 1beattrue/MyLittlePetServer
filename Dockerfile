FROM gradle:8.4-jdk20 AS build

COPY --chown=gradle:gradle . /home/gradle/project

WORKDIR /home/gradle/project

RUN gradle build --no-daemon

FROM openjdk:20-slim

COPY --from=build /home/gradle/project/build/libs/*.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/app.jar"]