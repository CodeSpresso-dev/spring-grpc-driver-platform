# spring-grpc-driver-platform

A modular backend system designed as an architectural case study for exploring service boundaries, Clean Architecture, and inter-service communication patterns in distributed systems.

---

## Overview

Modern backend systems often start simple but gradually evolve into tightly coupled architectures where business logic, infrastructure concerns, and service interactions become mixed into together.

This project was built to explore how such a system can be redesigned to improve:

* Maintainability
* Modularity
* Testability
* Separation of concerns
* Service independence

The goal was to simulate a realistic architectural evolution of a backend platform.

---

## System Architecture

The system is designed as a multi-service platform composed of:

* Driver Service
* Telemetry Service

Each service is independently structured using Clean Architecture principles.

At the platform level, a modular Maven structure is used to enforce service boundaries.

```
spring-grpc-driver-platform
│
├── common (shared module for reusable code across all modules; no bootstrap configuration included)
├── deployment
│
├── driver-service
│   ├── domain
│   ├── application
│   ├── infrastructure
│   └── presentation
│
└── telemetry-service
    ├── domain
    ├── application
    ├── infrastructure
    └── presentation
```

---

## Architectural Principles

Each service follows Clean Architecture principles:

* Domain layer contains business rules and is framework-independent
* Application layer orchestrates use cases
* Infrastructure layer handles external concerns (DB, messaging, etc.)
* Presentation layer exposes REST and gRPC APIs

The dependency rule is strictly enforced from outer layers to inner layers.

---

## Design Goals

The architecture was designed with the following goals:

* Clear service boundaries
* Independent business logic per service
* Reduced coupling between components
* Explicit ownership of responsibilities
* Improved testability
* Easier long-term evolution of the system

---

## Inter-Service Communication

After defining service boundaries, communication between services became a key architectural concern.

The following approaches were evaluated:

* Direct service dependencies
* REST APIs
* Messaging systems
* gRPC

The final decision was based on the following requirements:

* Synchronous communication
* Strongly typed contracts
* Explicit API definitions
* Generated client stubs

gRPC was selected as the communication mechanism between services.
And performance improvements were considered a secondary benefit.

---

## Why Clean Architecture

Clean Architecture was applied to ensure:

* Business logic is independent of frameworks
* Core domain remains testable in isolation
* Infrastructure concerns do not leak into business rules
* System remains adaptable to future changes

This separation enables each service to evolve independently over time.

---

## Testing Strategy

The system includes multiple levels of automated testing:

* Unit tests for domain logic
* Application-level tests for use cases
* Integration tests for service interactions

Testing is designed to validate business behavior independently of infrastructure implementation.

---

## Key Architectural Decisions (ADRs)

The following architectural decisions are documented:

* Adoption of Clean Architecture per service 
* Multi-module Maven structure for service isolation
* Selection of gRPC for inter-service sync communication

---

## Technology Stack

* Java 21
* Spring Boot
* gRPC
* Protocol Buffers
* Maven Multi-Module
* JUnit 5
* Docker

---

## Key Takeaway

It is an exploration of how architectural decisions shape the long-term maintainability and evolution of backend systems.

## Benchmark Results

A simple performance comparison was conducted between REST and gRPC communication under identical load conditions.

### Observations

- gRPC showed lower average latency
- gRPC reduced payload size due to Protobuf serialization
- gRPC handled concurrent load more efficiently

### Key Insight

Performance differences were not the primary motivation for adopting gRPC.

The main driver was:

- Strongly typed contracts
- Explicit service boundaries
- Generated client communication

Performance improvements were a secondary benefit.
