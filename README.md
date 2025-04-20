# Práctica Final - Aplicación Web Completa (API REST + Frontend)

## ✅ Objetivo
Este proyecto tiene como objetivo desarrollar una **aplicación web completa** que incluye:

- Backend con **API REST** usando Spring Boot
- Persistencia con **Spring Data JPA**
- Seguridad y sesiones con cookies
- **Frontend web** sencillo para registro y login
- Pruebas completas: **unitarias**, **de integración** y **end-to-end (E2E)**

El proyecto parte de una base proporcionada por el profesor y contiene 14 tareas identificadas como `TODO#1` hasta `TODO#14`, que han sido completadas en esta versión.

---

## 📁 Estructura del proyecto

- `src/main/java` → Código backend
- `src/main/resources/static` → Frontend web (HTML, JS)
- `src/test/java` → Tests JUnit (unitarios, integración y E2E)
- `src/test/resources` → Configuración de test
- `README.md` → Este documento

---

## 🧪 Cobertura de Tests

Este proyecto incluye:

- ✅ Tests **unitarios** para validación de modelos (`RegisterRequestUnitTest`)
- ✅ Tests **de integración** con JPA (`RepositoryIntegrationTest`, `UserControllerIntegrationTest`)
- ✅ Tests **end-to-end** para el API y el frontend (`P5ApplicationE2ETest`, `Registro y login` en Cypress)

---