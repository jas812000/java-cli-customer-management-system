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

---

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




















# Java CLI Customer Management System

## Overview
This project is a modular Java command-line application for managing customer records in memory. It supports validated customer entry, indexed retrieval by unique identifier, range-based queries on sales data, and formatted output for review.

The system is designed to emphasize clean architecture, separation of concerns, and maintainable object-oriented design rather than persistence or external dependencies.

---

## Problem Scope
The application addresses a common business need: maintaining a bounded set of customer records while ensuring data integrity and predictable behavior in a text-based environment.

Core requirements include:
- Enforcing a fixed customer capacity
- Validating structured user input (IDs, numeric ranges)
- Supporting efficient lookup and filtering operations
- Keeping user interface logic separate from data and business rules

---

## Architecture Overview
The system is intentionally decomposed into small, focused classes, each with a single responsibility.

### Core Components

- **Customer**
  - Immutable domain model representing a single customer record
  - Encapsulates name, validated five-digit ID, and total sales

- **CustomerRepository**
  - Manages storage and retrieval of customer objects
  - Enforces capacity constraints
  - Provides indexed lookup and range-based queries

- **Input**
  - Centralizes all console input and validation logic
  - Prevents duplicated validation code and Scanner misuse
  - Ensures consistent enforcement of numeric and format rules

- **CustomerService**
  - Implements application use cases
  - Coordinates input, repository operations, and output
  - Acts as a service layer between UI and data storage

- **Final_Execution**
  - Application entry point
  - Owns the menu loop and delegates behavior to the service layer

This structure allows individual components to evolve independently and simplifies testing and future extension.

---

## Key Design Decisions

### Separation of Concerns
User interaction, validation, data storage, and business logic are intentionally isolated into distinct classes. This reduces coupling and makes the system easier to reason about and modify.

### Centralized Validation
All input validation is handled in a dedicated component rather than scattered throughout the codebase. This avoids duplication and enforces consistent rules.

### Immutable Data Model
Customer records are immutable after creation, preventing accidental mutation and simplifying reasoning about system state.

### Defensive Data Access
The repository returns defensive copies of collections to protect internal state from unintended modification.

---

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/<your-username>/java-cli-customer-management-system.git

2. Compile the source files:
   ```bash
   javac *.java

3. Run the application:
   ```bash
   java Final_Execution
    ```

The application will launch into an interactive menu-driven interface.

---

## Limitations and Future Work
- Data is stored in memory only and does not persist between runs
- No duplicate ID enforcement is currently implemented
- Future extensions could include file persistence, unit testing, or alternative user interfaces

--- 

## Engineering Focus
This project emphasizes:
- Object-oriented design
- Maintainable architecture
- Input validation strategies
- Clear responsibility boundaries in small systems
It is intended as a concise, self-contained example of disciplined Java system design in a command-line environment.

---

## License
© 2025 James Stevens. All rights reserved.

This source code is provided for educational, evaluation, and portfolio review purposes.
Permission is granted to clone and run the code locally for non-commercial review.

No permission is granted to copy, modify, redistribute, or use this code in
commercial or production systems without explicit written consent from the author.

---
