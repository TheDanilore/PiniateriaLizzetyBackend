# Piñateria Lizzety

API REST para gestion de inventario de Piñateria Lizzety.


---

## Componentes

El proyecto se ha desarrollado utilizando las siguientes tecnologías:

- **Java JDK 17**
- **SpringBoot 3.x**
- **MySQL** (Base de datos)
- **Docker Compose** (Entorno de contenedores)
- **MailHog** (Gestor de correos para desarrollo)

---

## Preparación del Entorno

El proyecto cuenta con una implementación de Docker Compose para facilitar la configuración del entorno de desarrollo.

> ⚠️ Si no estás familiarizado con Docker, puedes optar por otra configuración para preparar tu entorno. Si decides
> hacerlo, omite los pasos 1.

Instrucciones para iniciar el proyecto

1. Levantar los contenedores con Docker Compose:

```bash
docker compose up -d
```

2. Limpia y reconstruye el proyecto:

```bash
./gradlew clean build
```

3. Correr Spring Boot:

```bash
./gradlew bootRun
```

**¡Y listo!** Ahora puedes empezar a desarrollar.

## Uso

La API estará disponible en: http://localhost:8080/api/

### (Opcional) Verificar correos en MailHog

MailHog está configurado para capturar los correos enviados en desarrollo. Accede a: [http://localhost:8025](http://localhost:8025).

### Endpoints Disponibles

#### 1. Autenticación

- **POST /api/login**: Inicia sesión y devuelve un token JWT.
- **POST /api/register**: Registra un nuevo usuario.
- **POST /api/logout**: Salir Sesión del usuario.

#### 2. GESTION DE PRODUCTOS (productos, proveedores, unidades de medida, ubicaciones, categoria producto)

- **POST /api/productos**: Permite registrar un nuevo producto
- **GET /api/productos**: Permite listar los producto
- **GET /api/productos/{id}**: Permite obtener un producto
- **PUT /api/productos**: Permite actualizar un producto
- **DELETE /api/productos**: Permite eliminar un producto

#### 3. GESTION DE USUARIOS

- **POST /api/usuarios**: Permite registrar un nuevo usuario
- **GET /api/usuarios**: Permite listar los usuarios
- **GET /api/usuarios/{id}**: Permite obtener un usuario
- **PUT /api/usuarios**: Permite actualizar un usuario
- **DELETE /api/usuarios**: Permite eliminar un usuario


#### 4. GESTION DE UNIDADES DE INVENTARIO (colores, longitudes,tamaño, variaciones, inventario)



**Autor**: [TheDanilore].

