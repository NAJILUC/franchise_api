# Franchise API

Proyecto desarrollado con **Spring Boot 3.5.5** como prueba técnica.  
La API expone endpoints para la gestión de franquicias, sucursales y productos.

---

## 🚀 Requisitos previos

Asegúrate de tener instalado en tu máquina:

- [Java 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
- [Maven 3.9+](https://maven.apache.org/)
- [PostgreSQL 15+](https://www.postgresql.org/download/)

---

## ⚙️ Configuración de la base de datos

En el archivo `application.properties` debes configurar la conexión a PostgreSQL:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/franchise_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
spring.jpa.hibernate.ddl-auto=update
```

---

## ▶️ Cómo correr el proyecto

### 1. Clonar el repositorio:

git clone https://github.com/NAJILUC/franchise_api.git 

cd franchise

### 2. Compilar y empaquetar con Maven:

mvn clean install

### 3. Ejecutar la aplicación:

mvn spring-boot:run

### Por defecto, la API se levanta en: http://localhost:8080/franchise-api/v1

---
## 📖 Documentación Swagger

Una vez levantada la aplicación, puedes acceder a la documentación generada automáticamente con Swagger UI en:

👉 http://localhost:8080/swagger-ui/index.html