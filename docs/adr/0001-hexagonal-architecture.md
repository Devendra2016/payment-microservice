# ADR 0001: Use of Hexagonal Architecture

## Status
Accepted

## Context
We are building a long-lived, enterprise-grade payment service that must be:
- Highly testable.
- Independent of any specific web framework or database technology.
- Easy to maintain and evolve over time.

## Decision
We will structure the application using Hexagonal Architecture (Ports and Adapters). The codebase will be split into three physical Maven modules:
- **`domain`**: Contains the core business models and the ports (interfaces) that define how the outside world can interact with the core.
- **`application`**: Contains the implementation of the business logic (services) that orchestrate the use cases. It depends on `domain`.
- **`infrastructure`**: Contains the adapters that implement the ports (e.g., Web Controllers, JPA Repositories). It depends on `application` and `domain`.

This ensures all dependencies point inward toward the domain core. The core has zero dependencies on external frameworks.

## Consequences
**Positive:**
- The core business logic can be tested in complete isolation from infrastructure concerns.
- We can easily swap out technologies (e.g., change from JPA to MongoDB) without touching the core logic.
- The architecture is self-documenting and enforces clean boundaries.

**Negative:**
- Initial setup is more complex than a simple layered architecture.
- Requires more boilerplate code (e.g., mapping between domain objects and persistence entities).
- Developers must be disciplined to avoid breaking architectural boundaries.