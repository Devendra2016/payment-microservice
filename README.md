[![CI](https://github.com/Devendra2016/payment-microservice/actions/workflows/ci.yml/badge.svg)](https://github.com/Devendra2016/payment-microservice/actions)
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
## Code Review Checklist

Every Pull Request should be reviewed against the following criteria:
- **Architecture:** Does the change respect the Hexagonal Architecture boundaries? (e.g., no business logic in controllers, no framework code in the domain)
- **Code Quality:** Is the code clean, readable, and well-commented? Does it pass static analysis (`mvn checkstyle:check spotbugs:check`)?
- **Tests:** Are there appropriate tests for the change? Do existing tests pass?
- **Documentation:** Is the OpenAPI spec updated? Are the CHANGELOG and README updated if necessary?
- **Functionality:** Does the change actually do what it says it does?