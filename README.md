# Laboratorio VII — Preparación para Examen Final

**Curso:** Programación II

## Objetivo

Reforzar los contenidos del Módulo III desarrollando tres APIs REST con Java y Spring Boot. Las tres usan arquitectura por capas, almacenamiento en memoria, validaciones, manejo de excepciones, protección mediante API Key y pruebas unitarias.

## Estructura del repositorio

```
LABORATORIO 7/
├── Ejercicio01_ApiProfesores/    API de profesores   (puerto 8081)
├── Ejercicio02_ApiProveedores/   API de proveedores  (puerto 8082)
└── Ejercicio03_ApiEmpleados/     API de empleados    (puerto 8083)
```

Cada ejercicio es un proyecto Maven independiente organizado por capas:

```
src/main/java/com/lab/<dominio>/
├── controller/   Endpoints REST, recibe y devuelve DTOs
├── dto/          Objetos de entrada (con validaciones) y de salida
├── mapper/       Conversión entre DTOs y entidades
├── model/        Entidad y enum de estado
├── repository/   Interfaz e implementación con lista en memoria
├── service/      Interfaz e implementación con la lógica de negocio
├── exception/    Excepción personalizada y manejador global de errores
└── security/     Filtro que valida la API Key
src/test/java/...  Pruebas unitarias del service (JUnit 5 + Mockito)
```

## Endpoints

Todas las solicitudes deben incluir el header `X-API-KEY`.

| Ejercicio | Método | Ruta | Descripción |
|---|---|---|---|
| Profesores | POST | `/api/profesores` | Crear profesor |
| | GET | `/api/profesores?especialidad=` | Consultar profesores, con filtro opcional |
| | GET | `/api/profesores/{id}` | Consultar profesor por id |
| Proveedores | POST | `/api/proveedores` | Crear proveedor |
| | GET | `/api/proveedores?categoria=` | Consultar proveedores, con filtro opcional |
| | GET | `/api/proveedores/{id}` | Consultar proveedor por id |
| Empleados | POST | `/api/empleados` | Crear empleado |
| | GET | `/api/empleados?especialidad=` | Consultar empleados, con filtro opcional |
| | GET | `/api/empleados/{id}` | Consultar empleado por id |

| API | API Key |
|---|---|
| Profesores | `progra2-lab7-profesores` |
| Proveedores | `progra2-lab7-proveedores` |
| Empleados | `progra2-lab7-empleados` |

### Códigos de estado

| Código | Caso |
|---|---|
| 201 Created | Registro creado correctamente |
| 200 OK | Consulta exitosa |
| 400 Bad Request | Nombre o apellido vacío, correo inválido, estado inválido o id no numérico |
| 401 Unauthorized | API Key ausente o incorrecta |
| 404 Not Found | El registro consultado no existe |

El campo `estado` acepta los valores `ACTIVO` o `INACTIVO`.

## Ejecución

Requisitos: JDK 17 y Maven.

```bash
cd Ejercicio01_ApiProfesores
mvn test               # pruebas unitarias
mvn spring-boot:run    # levanta la API
```

Ejemplo de solicitud:

```bash
curl -X POST http://localhost:8081/api/profesores \
  -H "X-API-KEY: progra2-lab7-profesores" \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Ana","apellido":"Lopez","correo":"ana.lopez@universidad.edu","especialidad":"Matematica","estado":"ACTIVO"}'
```
