# Level Up Gaming - Spring Boot Backend

Este es el backend migrado a Spring Boot para la aplicación Level Up Gaming.

## Requisitos Previos

- Java 17 o superior
- Maven (debes instalarlo ya que no se incluye el wrapper)

## Cómo ejecutar

1. Abre una terminal en esta carpeta (`level-up-gaming-backend-spring`).
2. Ejecuta el siguiente comando para iniciar la aplicación:

```bash
mvn spring-boot:run
```

La aplicación iniciará en el puerto **5000**, reemplazando al backend de Node.js.

## Estructura del Proyecto

- `src/main/java/com/levelup/gaming`: Código fuente Java.
    - `models`: Entidades JPA (Product, User, Order, etc.).
    - `repositories`: Repositorios JPA.
    - `controllers`: Controladores REST.
    - `config`: Configuraciones (Seguridad, Carga de datos iniciales).
- `src/main/resources`: Recursos.
    - `application.properties`: Configuración de la base de datos y puerto.

## Base de Datos

Se utiliza una base de datos en memoria **H2**. Los datos se pierden al reiniciar la aplicación.
Los datos iniciales se cargan automáticamente desde `DataSeeder.java`.

## Endpoints Principales

- `GET /api/products`: Listar productos.
- `POST /api/users/login`: Iniciar sesión.
- `POST /api/orders`: Crear orden.
