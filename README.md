# Wompi API Automation Framework

Este proyecto es un framework de automatización de pruebas para la API de Wompi, desarrollado con Java, Cucumber y Rest Assured. El objetivo es validar transacciones mediante pruebas automatizadas estructuradas bajo BDD.

---

## 📌 Tecnologías utilizadas

- Java 11+
- Maven
- Cucumber (BDD)
- Rest Assured
- JUnit / TestNG
- Jackson (serialización JSON)
- ExtentReports (opcional para reportes)
- Git

---

## 📁 Estructura del proyecto
wompi-api-automation/
│── pom.xml
│── README.md
│
└── src/test
├── java
│ ├── client # Cliente para consumo de API
│ ├── builders # Construcción de request bodies
│ ├── utils # Utilidades (config, firma, helpers)
│ ├── stepdefinitions # Definición de pasos BDD (Cucumber)
│ └── runners # Runner de ejecución de tests
│
└── resources
├── features # Escenarios Gherkin
└── config.properties # Configuración del entorno


---

## 🚀 Objetivo del proyecto

Automatizar la validación de transacciones en la API de Wompi, asegurando:

- Correcta creación de transacciones
- Validación de respuestas de la API
- Manejo de firmas de seguridad
- Reutilización de componentes de prueba
- Escenarios legibles en lenguaje Gherkin

---

## 🧪 Estrategia de pruebas

Se implementó un enfoque BDD:

- **Feature files**: describen escenarios en lenguaje natural
- **Step Definitions**: conectan los pasos con la lógica de automatización
- **Client Layer**: encapsula las llamadas HTTP a la API
- **Builders**: generan payloads dinámicos
- **Utils**: manejo de configuración y firma de seguridad

---

## 🧱 Arquitectura del framework

El proyecto sigue una arquitectura en capas:

1. **Feature Layer (Cucumber)**
   - Define escenarios de negocio

2. **Step Definitions**
   - Traduce Gherkin a código Java

3. **Client Layer**
   - Maneja consumo de API (Rest Assured)

4. **Builder Layer**
   - Construcción de requests dinámicos

5. **Utils Layer**
   - Configuración, firma, helpers

---

## ▶️ Ejecución del proyecto

Ejecutar todas las pruebas:

```bash
mvn clean test