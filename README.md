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

## Logging

The application uses structured JSON logging. Each log entry includes:
- A `correlationId` to trace requests across services.
- The `application` name.
- The `environment` profile.

To trace a request, provide an `X-Correlation-ID` header. If not provided, one will be generated automatically and returned in the response headers.