#  Reserva de Pérgola – Instituto Comercial Rivera Díaz Copiapó

Aplicación web para que los estudiantes del Instituto Comercial Rivera Díaz (Copiapó) reserven un cupo en la **pérgola sustentable**, el espacio habilitado para lectura, trabajo grupal y esparcimiento tras el daño estructural de la biblioteca.

>  **Estado del proyecto: en desarrollo (no funcional todavía).**
> Faltan por completar partes del formulario de reserva. Ver la sección [Pendientes](#-pendientes).

##  ¿Qué problema resuelve?

Al perderse temporalmente el espacio de la biblioteca, los estudiantes quedaban hacinados en pasillos o sentados en el suelo durante las horas libres o el trabajo grupal. Con esta aplicación pueden reservar su hora en la pérgola y asegurar su cupo.

##  Funcionalidades

- Página de bienvenida con acceso directo a la reserva.
- Formulario con datos del estudiante (nombre, RUT, curso) y de la reserva (contenido, fecha y hora).
- El estudiante se registra automáticamente la primera vez que reserva (se identifica por RUT).
- Validaciones:
  - Todos los campos son obligatorios.
  - Nombre entre 3 y 100 caracteres.
  - Formato de RUT válido (ej: `12345678-9`).
  - La fecha no puede estar en el pasado.
  - No permite reservas duplicadas (mismo estudiante, fecha y hora), con aviso en una ventana emergente.
- Mensajes de éxito y error en pantalla.

##  Pendientes

- **Selección de curso:** el curso debe combinar el nivel con la letra (ej: `1A`, `3C`, `4F`) en un único campo; esta parte aún no funciona correctamente.
- **Selección de hora:** la hora y los minutos deben unirse en un único valor de hora (`HH:mm`); esta parte aún no funciona correctamente.
- Validar que la hora no haya pasado cuando la reserva es para hoy.
- Agregar el script SQL de la base de datos (ver [Base de datos](#-base-de-datos)).

##  Tecnologías

- **Java 17**
- **Spring Boot 4.1.0** (Spring Web, Spring Data JPA, Thymeleaf, DevTools)
- **Microsoft SQL Server** con driver `mssql-jdbc 13.4.0`
- **HTML + CSS** para la interfaz
- **Maven** y Apache NetBeans

##  Estructura

```
IcoProgra/
├── pom.xml
├── mssql-jdbc_auth-13.4.0.x64.dll      # Necesaria para autenticación integrada de Windows
└── src/main/
    ├── java/com/IcoProgra/Comercial/
    │   ├── ComercialApplication.java
    │   ├── Controller/                 # ControllerInicio, ControllerReserva
    │   ├── Domain/                     # EstudianteEntity, ReservaEntity
    │   ├── Repository/                 # Acceso a datos (JPA)
    │   └── Service/                    # Lógica y validaciones
    └── resources/
        ├── application.properties
        ├── static/                     # css/ e img/
        └── templates/                  # index.html, Reserva.html
```

## Base de datos

Base de datos: `Ico` (SQL Server). Relación uno a muchos: un **Estudiante** puede tener muchas **Reservas**.

| Estudiante | Reserva |
|---|---|
| Id_Estudiante (PK) | Id_Reserva (PK) |
| Nombre | Id_estudiante (FK) |
| Rut (único) | contenido |
| Curso | Fecha |
| | Hora |

## Instalación

### Requisitos
- JDK 17 o superior
- Maven
- SQL Server con una base de datos llamada `Ico`
- Windows (usa autenticación integrada)

### Pasos
1. Clona el repositorio:
   ```bash
   git clone https://github.com/piexevis/IcoProgra.git
   ```
2. Crea la base de datos `Ico` y las tablas `Estudiante` y `Reserva` según el modelo de arriba.
3. Edita `src/main/resources/application.properties` con tu servidor/instancia:
   ```properties
   spring.datasource.url=jdbc:sqlserver://TU-PC\\TU-INSTANCIA;databaseName=Ico;encrypt=true;trustServerCertificate=true;integratedSecurity=true;
   ```
4. Ejecuta el proyecto:
   ```bash
   mvn spring-boot:run
   ```
5. Abre `http://localhost:8080` en el navegador.

##  Rutas

| Ruta | Descripción |
|---|---|
| `/` | Página de bienvenida |
| `/Reserva` | Formulario de reserva |

## Proyecto académico.
