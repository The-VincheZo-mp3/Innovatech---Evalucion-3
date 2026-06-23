# =========================
# BUILD STAGE
# =========================
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests


# =========================
# RUNTIME STAGE
# =========================
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# seguridad (usuario no root)
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# copiar jar generado
COPY --from=build /app/target/*.jar app.jar

# puerto backend
EXPOSE 8080

# ejecutar app
ENTRYPOINT ["java", "-jar", "app.jar"]