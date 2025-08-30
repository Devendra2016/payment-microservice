# Enterprise Payment Service

A production-grade Spring Boot service built with Hexagonal Architecture.

## Project Structure

This is a multi-module Maven project:

*   **`domain`**: Core business models and port interfaces. (*No Spring dependencies*)
*   **`application`**: Service classes containing business logic. Implements domain ports.
*   **`infrastructure`**: REST controllers, database repositories, and configuration. Adapts external technologies to application ports.

**Dependency Rule:** `infrastructure` -> `application` -> `domain`. The `domain` module must never depend on any other module.

## Building the Project

From the root directory (`payment-service/`), run:

```bash
mvn clean install