# 🎯 Guess The Number - API REST

<div align="center">

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.4-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![H2 Database](https://img.shields.io/badge/H2-Database-0000BB?style=for-the-badge)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

**Un juego clásico de "Adivina el Número" implementado como API REST**

[Descripción](#-descripción) •
[Instalación](#-instalación) •
[Endpoints](#-endpoints) •
[Cómo Jugar](#-cómo-jugar)

</div>

---

## 📖 Descripción

**Guess The Number** es una API REST desarrollada con Spring Boot que implementa el clásico juego de adivinar un número. El servidor genera un número aleatorio entre 0 y 99, y el jugador debe intentar adivinarlo con un número limitado de intentos según la dificultad elegida.

### 🎮 Mecánica del Juego

1. **Crear un usuario** con nombre de usuario y email
2. **Iniciar una partida** eligiendo la dificultad
3. **Adivinar el número** enviando intentos hasta ganar o quedarse sin intentos

### 📊 Niveles de Dificultad

| Dificultad | Intentos Disponibles |
|------------|---------------------|
| 🟢 EASY    | 10 intentos         |
| 🟡 MEDIUM  | 8 intentos          |
| 🔴 HARD    | 5 intentos          |

### 💬 Respuestas del Juego

- **MAYOR**: El número a adivinar es mayor que el número enviado
- **MENOR**: El número a adivinar es menor que el número enviado
- **GANO**: ¡Adivinaste el número! 🎉
- **PERDIO**: Te quedaste sin intentos 😢

---

## 🛠️ Tecnologías Utilizadas

- **Java 17** - Lenguaje de programación
- **Spring Boot 3.3.4** - Framework principal
- **Spring Data JPA** - Persistencia de datos
- **H2 Database** - Base de datos en memoria
- **Lombok** - Reducción de código boilerplate
- **ModelMapper** - Mapeo entre entidades y DTOs
- **SpringDoc OpenAPI** - Documentación automática de la API (Swagger)

---

## 🚀 Instalación

### Prerrequisitos

- **Java 17** o superior instalado
- **Maven 3.6+** (o usar el wrapper incluido `mvnw`)

### Pasos para ejecutar

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/tu-usuario/GameGuessNumber.git
   cd GameGuessNumber
   ```

2. **Compilar el proyecto**
   ```bash
   # En Linux/macOS
   ./mvnw clean install
   
   # En Windows
   mvnw.cmd clean install
   ```

3. **Ejecutar la aplicación**
   ```bash
   # En Linux/macOS
   ./mvnw spring-boot:run
   
   # En Windows
   mvnw.cmd spring-boot:run
   ```

4. **Verificar que está corriendo**
   
   La aplicación estará disponible en: `http://localhost:8080`
   
   Documentación Swagger UI: `http://localhost:8080/swagger-ui.html`

### Ejecutar como JAR

```bash
# Compilar
./mvnw clean package

# Ejecutar
java -jar target/Application-0.0.1-SNAPSHOT.jar
```

---

## 📡 Endpoints

La API expone los siguientes endpoints bajo el path base `/guess-number`:

### 1️⃣ Crear Usuario

Crea un nuevo usuario en el sistema para poder jugar partidas.

```
POST /guess-number/users
```

#### Request Body

```json
{
  "username": "jugador1",
  "email": "jugador1@email.com"
}
```

#### Response (200 OK)

```json
{
  "id": 1,
  "username": "jugador1",
  "email": "jugador1@email.com"
}
```

#### Errores Posibles

| Código | Descripción |
|--------|-------------|
| 404    | El email ya existe en el sistema |

---

### 2️⃣ Crear Partida

Crea una nueva partida para un usuario específico con la dificultad elegida.

```
POST /guess-number/users/{userId}/matches
```

#### Parámetros de URL

| Parámetro | Tipo   | Descripción              |
|-----------|--------|--------------------------|
| userId    | Long   | ID del usuario (requerido) |

#### Request Body

```json
{
  "difficulty": "EASY"
}
```

> **Valores válidos para `difficulty`:** `EASY`, `MEDIUM`, `HARD`

#### Response (200 OK)

```json
{
  "id": 1,
  "matchDifficulty": "EASY",
  "remainingTries": 10
}
```

#### Errores Posibles

| Código | Descripción |
|--------|-------------|
| 404    | Usuario no encontrado |

---

### 3️⃣ Jugar Partida (Adivinar Número)

Envía un intento para adivinar el número en una partida activa.

```
POST /guess-number/users/{userId}/matches/{matchId}
```

#### Parámetros de URL

| Parámetro | Tipo   | Descripción               |
|-----------|--------|---------------------------|
| userId    | Long   | ID del usuario (requerido)  |
| matchId   | Long   | ID de la partida (requerido) |

#### Request Body

```json
{
  "number": 50
}
```

> **Rango válido:** 0 - 99

#### Response (200 OK)

```json
{
  "matchDto": {
    "id": 1,
    "matchDifficulty": "EASY",
    "remainingTries": 9
  },
  "response": "MAYOR"
}
```

#### Posibles Valores de `response`

| Valor   | Significado |
|---------|-------------|
| MAYOR   | El número secreto es **mayor** que tu intento |
| MENOR   | El número secreto es **menor** que tu intento |
| GANO    | ¡Adivinaste el número! La partida terminó |
| PERDIO  | Te quedaste sin intentos. La partida terminó |

#### Errores Posibles

| Código | Descripción |
|--------|-------------|
| 404    | Usuario o partida no encontrada |
| 404    | La partida ya terminó |
| 404    | La partida no pertenece al usuario |

---

## 🎮 Cómo Jugar

### Ejemplo de Flujo Completo

#### Paso 1: Crear un usuario

```bash
curl -X POST http://localhost:8080/guess-number/users \
  -H "Content-Type: application/json" \
  -d '{"username": "carlos", "email": "carlos@email.com"}'
```

**Respuesta:**
```json
{"id": 1, "username": "carlos", "email": "carlos@email.com"}
```

#### Paso 2: Crear una partida en dificultad MEDIUM

```bash
curl -X POST http://localhost:8080/guess-number/users/1/matches \
  -H "Content-Type: application/json" \
  -d '{"difficulty": "MEDIUM"}'
```

**Respuesta:**
```json
{"id": 1, "matchDifficulty": "MEDIUM", "remainingTries": 8}
```

#### Paso 3: Hacer intentos para adivinar

```bash
# Primer intento: probar con 50
curl -X POST http://localhost:8080/guess-number/users/1/matches/1 \
  -H "Content-Type: application/json" \
  -d '{"number": 50}'
```

**Respuesta (el número es mayor):**
```json
{
  "matchDto": {"id": 1, "matchDifficulty": "MEDIUM", "remainingTries": 7},
  "response": "MAYOR"
}
```

```bash
# Segundo intento: probar con 75
curl -X POST http://localhost:8080/guess-number/users/1/matches/1 \
  -H "Content-Type: application/json" \
  -d '{"number": 75}'
```

**Respuesta (el número es menor):**
```json
{
  "matchDto": {"id": 1, "matchDifficulty": "MEDIUM", "remainingTries": 6},
  "response": "MENOR"
}
```

```bash
# Tercer intento: probar con 63
curl -X POST http://localhost:8080/guess-number/users/1/matches/1 \
  -H "Content-Type: application/json" \
  -d '{"number": 63}'
```

**Respuesta (¡Ganaste!):**
```json
{
  "matchDto": {"id": 1, "matchDifficulty": "MEDIUM", "remainingTries": 6},
  "response": "GANO"
}
```

---

## 📚 Documentación Swagger

Una vez que la aplicación esté corriendo, puedes acceder a la documentación interactiva de la API en:

🔗 **http://localhost:8080/swagger-ui.html**

Desde allí podrás:
- Ver todos los endpoints disponibles
- Probar las llamadas directamente desde el navegador
- Ver los esquemas de request/response

---

## 🗂️ Estructura del Proyecto

```
src/main/java/com/tomas/miproyecto/
├── Application.java              # Clase principal de Spring Boot
├── config/
│   ├── MappersConfig.java        # Configuración de ModelMapper
│   └── SpringDocConfig.java      # Configuración de Swagger/OpenAPI
├── controllers/
│   ├── ControllerExceptionHandler.java  # Manejo global de excepciones
│   ├── MatchController.java      # Controller de partidas (extensible)
│   └── UserController.java       # Controller principal de usuarios y juego
├── dtos/
│   ├── CreateUserMatchDto.java   # DTO para crear partida
│   ├── ErrorApi.java             # DTO para respuestas de error
│   ├── MatchDto.java             # DTO de partida
│   ├── PlayUserMatchDto.java     # DTO para jugar (enviar número)
│   ├── RoundMatchDto.java        # DTO de respuesta de ronda
│   └── UserDto.java              # DTO de usuario
├── entities/
│   ├── MatchEntity.java          # Entidad JPA de partida
│   └── UserEntity.java           # Entidad JPA de usuario
├── models/
│   ├── Match.java                # Modelo de dominio de partida
│   ├── MatchDifficulty.java      # Enum de dificultades
│   ├── MatchStatus.java          # Enum de estados (PLAYING, FINISH)
│   ├── RoundMatch.java           # Modelo de resultado de ronda
│   └── User.java                 # Modelo de dominio de usuario
├── repositories/
│   ├── MatchRepository.java      # Repositorio JPA de partidas
│   └── UserRepository.java       # Repositorio JPA de usuarios
└── services/
    ├── impl/
    │   ├── MatchServiceImpl.java # Implementación lógica de partidas
    │   └── UserServiceImpl.java  # Implementación lógica de usuarios
    ├── MatchService.java         # Interface de servicio de partidas
    └── UserService.java          # Interface de servicio de usuarios
```

---

## 🗃️ Base de Datos

El proyecto utiliza **H2 Database** en memoria, lo que significa que:

- ✅ No requiere instalación ni configuración adicional
- ✅ Se inicializa automáticamente al arrancar la aplicación
- ⚠️ Los datos se pierden al detener la aplicación

### Consola H2 (opcional)

Puedes habilitar la consola H2 agregando en `application.properties`:

```properties
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

Luego accede a: `http://localhost:8080/h2-console`

**Credenciales:**
- JDBC URL: `jdbc:h2:mem:test`
- User: `sa`
- Password: *(vacío)*

---

<div align="center">

**¡Diviértete adivinando números! 🎲**

</div>
