# Multi-stage build für Spring Boot mit Gradle
FROM openjdk:24-jdk-slim AS build

WORKDIR /app

# Gradle Wrapper und Build-Dateien kopieren
COPY gradle/ gradle/
COPY gradlew build.gradle.kts settings.gradle.kts ./

# Dependencies laden
RUN ./gradlew dependencies --no-daemon

# Quellcode kopieren
COPY src/ src/

# JAR bauen
RUN ./gradlew bootJar --no-daemon

# Runtime Image
FROM openjdk:24-jdk-slim

WORKDIR /app

# Non-root User erstellen
RUN groupadd -r spring && useradd -r -g spring spring

# JAR aus Build-Stage kopieren
COPY --from=build /app/build/libs/*.jar app.jar

# Ownership ändern
RUN chown spring:spring app.jar

USER spring

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]