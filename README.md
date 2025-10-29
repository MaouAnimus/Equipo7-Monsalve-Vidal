# Huerto Hogar – Microservicios

Este proyecto está hecho en el entorno Spring Tools Suite en Spring Boot.  
Cada servicio tiene sus correspondientes endpoints, y acá se detalla la forma de probar cada microservicio.

---

## 1. Usuario Microservice

- **Archivo:** usuario-microservice  
- **Puerto:** 8080  
- **Nombre interno:** usuario-microservice  

**Endpoints principales**

- **GET** - `http://localhost:8080/usuarios`  
  - Obtener todos los usuarios  

- **GET** - `http://localhost:8080/usuarios/{id}`  
  - Obtener usuario por ID  

- **POST** - `http://localhost:8080/usuarios`  
  - Crear nuevo usuario  
  ```json
  {
    "nombre": "Jonathan",
    "correo": "jon.vidals@example.com",
    "password": "1234"
  }
  ```

- **PUT** - `http://localhost:8080/usuarios/{id}`  
  - Actualizar usuario  
  ```json
  {
    "nombre": "Jonathan Vidal"
  }
  ```

- **DELETE** - `http://localhost:8080/usuarios/{id}`  
  - Eliminar usuario  

---

## 2. Core Microservice

- **Archivo:** core-microservice  
- **Puerto:** 8082  
- **Nombre interno:** core-microservice  

**Endpoints principales**

- **GET** - `http://localhost:8082/productos`  
  - Obtener todos los productos  

- **GET** - `http://localhost:8082/productos/{id}`  
  - Obtener producto por ID  

- **POST** - `http://localhost:8082/productos`  
  - Crear producto nuevo  
  ```json
  {
    "nombre": "Tomate Cherry",
    "precio": 1200,
    "stock": 50,
    "categoria": "Hortalizas"
  }
  ```

- **PUT** - `http://localhost:8082/productos/{id}`  
  - Actualizar producto  
  ```json
  {
    "stock": 30
  }
  ```

- **DELETE** - `http://localhost:8082/productos/{id}`  
  - Eliminar producto  

---

## 3. Carrito Microservice

- **Archivo:** carrito-microservice  
- **Puerto:** 8083  
- **Nombre interno:** carrito-microservice  

**Endpoints principales**

- **GET** - `http://localhost:8083/carrito`  
  - Obtener todos los carritos  

- **GET** - `http://localhost:8083/carrito/{id}`  
  - Obtener carrito por ID  

- **POST** - `http://localhost:8083/carrito`  
  - Crear un carrito  
  ```json
  {
    "usuarioId": 1,
    "productoId": 2,
    "cantidad": 3
  }
  ```

- **PUT** - `http://localhost:8083/carrito/{id}`  
  - Actualizar cantidad o producto  
  ```json
  {
    "cantidad": 5
  }
  ```

- **DELETE** - `http://localhost:8083/carrito/{id}`  
  - Eliminar carrito  
