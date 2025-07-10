# Etapa 1: build
FROM gradle:8.5-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle :app-service:bootJar --no-daemon

# Etapa 2: runtime
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/application/app-service/build/libs/app-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
