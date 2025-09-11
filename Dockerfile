# Usamos Maven con Java 21 para compilar la app
FROM maven:3.9.6-amazoncorretto-21 AS build

# Carpeta de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el pom.xml primero (para aprovechar cache de dependencias)
COPY pom.xml .

# Descargamos dependencias sin compilar aún
RUN mvn dependency:go-offline -B

# Ahora copiamos el resto del código fuente
COPY . .

# Compilamos y generamos el JAR
RUN mvn clean package -DskipTests

# Imagen base solo con Java 21 (más ligera que maven)
FROM openjdk:21-jdk-slim

# Carpeta de trabajo
WORKDIR /app

# Copiamos el jar generado desde la etapa anterior
COPY --from=build /app/target/franchise-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto (usa SERVER_PORT si lo pasas como env var)
EXPOSE 8080

# Comando de arranque
ENTRYPOINT ["java", "-jar", "app.jar"]
