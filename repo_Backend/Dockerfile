# =========================
# BUILD STAGE
# =========================
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copiamos todo el código fuente del proyecto
COPY . .

# REPARADO: Usamos 'mvn' global en lugar de './mvnw' para saltarnos el archivo faltante
RUN mvn clean package -DskipTests


# =========================
# RUNTIME STAGE
# =========================
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Seguridad (usuario no root)
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# Copiar jar generado
COPY --from=build /app/target/*.jar app.jar

# Puerto backend (Asegúrate de que coincida con el manifest de K8s, en tu service pusimos 3000)
EXPOSE 3000

# Ejecutar app
ENTRYPOINT ["java", "-jar", "app.jar"]
