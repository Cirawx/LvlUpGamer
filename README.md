LEVEL-UP GAMING E-COMMERCE


======COMO EJECUTAR EL PROYECTO======

1.- Backend(Spring Boot)

OPCION 1
-Abrir PowerShell en la carpeta del proyecto
- mvn spring-boot:run

OPCION 2
-Ejecutar el programa desde tu IDE de preferencia(IntelliJ, Eclipse, VS Code)

2.- Frontend (React)

-Abrir PowerShell en la carpeta del proyecto
- npm install
- npm run dev

La aplicación correra en http://localhost:5173

========Guía de uso========

Flujo de usuario

1.- Registro: Crea una cuenta validando tu RUT y correo.
2.- Compra: Añade productos al carrito. En el checkout, selecciona tu región (Chile) para calcular el envío.
3.- Puntos: Al finalizar la compra, ganarás puntos automáticamente.
4.- Recompensas: Ve a la sección de recompensas y canjea tus puntos por cupones de descuento.
5.- Admin: Ingresa con una cuenta de rol admin para ver el Dashboard y gestionar la tienda (incluyendo la creación de nuevas recompensas).

Endpoints Clave(API)

POST /api/users/login: Autenticación.
POST /api/users/register: Registro de usuarios.
POST /api/users/reset-password: Recuperación de contraseña.
GET /api/products: Catálogo público.
POST /api/orders: Crear orden (requiere token).
GET /api/rewards: Listar recompensas disponibles.
POST /api/rewards/admin: Crear nueva recompensa (Admin).

Documentación de API(Swagger)

El backend incluye documentación interactiva generada automáticamente por Swagger, puedes acceder a ella en: http://localhost:8080/swagger-ui/index.htmlçç


==========Testing==========

Se utiliza Vitest para hacer test unitarios y de integración

-Abrir PowerShell en la carpeta del proyecto frontend
- npm test

