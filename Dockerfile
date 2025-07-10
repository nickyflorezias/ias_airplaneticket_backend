# Etapa 1: build
FROM gradle:8.5-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle :app-service:bootJar --no-daemon

# Etapa 2: runtime
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/app/build/libs/app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
