Huerto Hogar – Microservicios

Este proyecto esta hecho en el entorno spring tools suite en Spring Boot, cada servicio tiene sus correspondientes endpoints, y acá daré la forma de probar cada 
microservicio 
1. Usuario Microservice

Archivo: usuario-microservice
Puerto: 8080
Nombre interno: usuario-microservice

Endpoints principales

GET	http://localhost:8080/api/usuarios	Obtener todos los usuarios	—
GET	http://localhost:8080/api/usuarios/{id}	Obtener usuario por ID	—
POST	http://localhost:8080/api/usuarios	Crear nuevo usuario	
{ 
"nombre": "Jonathan", 
"correo": "jon.vidals@example.com", 
"password": "1234" 
}
PUT	http://localhost:8080/api/usuarios/{id}	Actualizar usuario	
{ "nombre": "Jonathan Vidal" }
DELETE	http://localhost:8080/api/usuarios/{id}

-----------------------------------------------
2. Core Microservice

Archivo: core-microservice
Puerto: 8082
Nombre interno: core-microservice

Endpoints principales

GET	http://localhost:8082/api/productos	Obtener todos los productos	—
GET	http://localhost:8082/api/productos/{id}	Obtener producto por ID	—
POST	http://localhost:8082/api/productos	Crear producto nuevo	{ "nombre": "Tomate Cherry", 
"precio": 1200, 
"stock": 50, 
"categoria": "Hortalizas" }
PUT	http://localhost:8082/api/productos/{id}	Actualizar producto	{ "stock": 30 }
DELETE	http://localhost:8082/api/productos/{id}

—-------------------------------------------
3. Carrito Microservice

Archivo: carrito-microservice
Puerto: 8083
Nombre interno: carrito-microservice

Endpoints principales
Método	Endpoint	Descripción	Ejemplo cuerpo (JSON)
GET	http://localhost:8083/api/carrito	Obtener todos los carritos	—
GET	http://localhost:8083/api/carrito/{id}	Obtener carrito por ID	—
POST	http://localhost:8083/api/carrito	Crear un carrito	{ "usuarioId": 1,
"productoId": 2, 
"cantidad": 3 
}
PUT	http://localhost:8083/api/carrito/{id}	Actualizar cantidad o producto	{ "cantidad": 5 }
DELETE	http://localhost:8083/api/carrito/{id}
