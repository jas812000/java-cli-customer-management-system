# Java CLI Customer Management System

## Overview
The Java CLI Customer Management System is a modular Java backend application designed to 
manage customer records in memory through a command-line interface.

The project focuses on backend fundamentals such as domain modeling, input validation, 
bounded storage, query logic, and automated testing rather than UI concerns. It 
demonstrates how a small but well-structured backend system can be built, tested, and 
packaged using standard Java tooling.

---

## Features
- Interactive CLI menu for managing customers
- Add single or multiple customers with validated input
- Five-digit customer ID validation (including leading zeros)
- Retrieve customers by unique ID
- Filter customers by inclusive sales range
- Fixed-capacity repository enforcing storage limits
- Defensive handling of invalid user input
- Automated JUnit tests covering repository and input logic

---

## Architecture Overview
The system follows a layered, object-oriented architecture:

### UI / Input Layer
Handles all console interaction and input validation, ensuring malformed or invalid input 
does not propagate into the core domain logic.

### Service Layer
Coordinates application use cases such as adding customers, querying data, and enforcing 
business rules.

### Domain Model
`Customer` represents an immutable domain entity containing identifying information and 
total sales.

### Repository Layer
`CustomerRepository` manages bounded in-memory storage, lookup by ID, range-based queries, 
and defensive copying of internal state.

---

## Data Management Model
- All data is stored **in memory** (no database or filesystem persistence)
- Repository enforces a fixed maximum capacity
- Customer records are queried via controlled repository methods
- This design simulates backend data access patterns while remaining simple and testable

---

## Customer State & Validation
- Customer IDs must be exactly five digits
- Invalid numeric input is rejected and re-prompted
- Sales range queries automatically normalize reversed bounds
- Repository state cannot be mutated externally

---

## Error Handling Strategy
The system enforces correctness through defensive programming:
- Invalid input is detected and re-requested without crashing
- Repository capacity limits are enforced
- Missing customer lookups return null instead of throwing
- Query methods return empty collections when no matches exist

---

## Build & Test

### Prerequisites
- Java 17+
- Maven 3.8+

### Run Tests
```bash
mvn test
```

---

## Run (CLI)
```bash
mvn -q exec:java
```

The CLI menu allows users to:
- Add customers
- Display all customers
- Search by customer ID
- Retrieve customers within a sales range
- Exit the program cleanly

---

## Tools & Technologies
- **Language**: Java 17
- **Build Tool**: Maven
- **Testing**: JUnit 5
- **Architecture**: Layered OOP design
- **Interface**: Command Line (CLI)

---

## Purpose

This project serves as a backend engineering case study demonstrating:
- Clean Java project structuring with Maven
- Layered application architecture
- Defensive input validation
- In-memory repository design with bounded capacity
- Query logic and edge-case handling
- Automated unit testing with JUnit
- Translation of backend design concepts into working Java code

---

## License

This project is licensed under the MIT License. See the LICENSE file for details.

---

