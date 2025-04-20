# P5
Aplicación web que usa Spring JPA para persistir los datos de un API REST de gestión de usuarios.
El API permite el registro de nuevos usuarios y su identificación mediante email y password.
Una vez identificados, se emplea una cookie de sesión para autenticar las peticiones que permiten 
a los usuarios leer, modificar y borrar sus datos. También existe un endpoint para cerrar la sesión.  

## Endpoints

// TODO#1: rellena la tabla siguiente analizando el código del proyecto

| Método | Ruta | Descripción | Respuestas |
|--------|------|-------------|------------|
| POST       |  "/api/users"    |   Registra usuario y lanza excepción si ya existe          |    201 SI OK , 409 SI CONFLICT     |
| POST     |   "/api/users/me/session"   |    Permite login devolviéndote una ResponseEntity si todo bien y si no lanza la excepción        |      201 SI OK , 401 UNAUTHORIZED |
|   DELETE     | "/api/users/me/session"     |   Permite logout devolviéndote una ResponseEntity si todo bien y si no lanza la excepción        |      204 SI OK, 401 SI UNAUTHORIZED      |
|   GET     |   "/api/users/me"   |   Te devuelve la información del profile del usuario si existe un token con dicho Id en la autenticación y si no te lanza una excepción           |      200 SI OK, 401 UNAUTHORIZED      |
|     PUT   |    "/api/users/me"  |   Sirve para actualizar el perfil autenticando las credenciales y si no son correctas lanza una excepción        |   200 SI OK, 401 UNAUTHORIZED         |
|    DELETE    |  "/api/users/me"    |   Elimina un usuario si existe y si no lanza una excepción          |  204 SI OK   , 401 UNAUTHORIZED       |


## Comandos 

- Construcción: 
  ```sh
  ./mvnw clean package
  ```

- Ejecución: 
  ```sh
  ./mvnw spring-boot:run
  ```

- Tests:
  ```sh
  ./mvnw test
  ```
