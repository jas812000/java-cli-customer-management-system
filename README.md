# Java CLI Customer Management System

A command-line Java application for managing customer records using a layered architecture, validated user input, bounded in-memory storage, and automated testing.

## Overview

The Java CLI Customer Management System demonstrates core backend development concepts in a small, focused application. Users can add and retrieve customer records through an interactive CLI while the application enforces customer ID uniqueness, validates input, manages repository capacity, and supports sales-range queries.

The project separates application startup, user input, business operations, domain data, and repository access into distinct responsibilities.

---

## Features

- Add individual or multiple customer records
- Display all stored customers
- Search for a customer by unique five-digit ID
- Filter customers by an inclusive sales range
- Preserve leading zeros when customer IDs are displayed
- Prevent duplicate customer IDs
- Reject negative and non-finite sales values
- Enforce fixed repository capacity
- Normalize reversed sales-range bounds
- Handle invalid console input without terminating the application
- Protect repository state through defensive copying
- Automated unit tests for repository and input behavior

---

## Architecture

The application uses a small layered design:

### Application Entry Point

`Main` creates the application dependencies and delegates execution to `CustomerService`.

### Service Layer

`CustomerService` controls the application lifecycle and coordinates customer-management operations, including menu routing, customer creation, searches, and result presentation.

### Input Layer

`Input` centralizes console input and validation, including:

- Menu selections
- Non-empty customer names
- Five-digit customer IDs
- Minimum integer values
- Finite numeric values
- Non-negative sales values

### Domain Model

`Customer` is an immutable domain object containing:

- Customer name
- Customer ID
- Total sales

### Repository Layer

`CustomerRepository` provides bounded in-memory storage and:

- Enforces repository capacity
- Prevents duplicate customer IDs
- Retrieves customers by ID
- Filters customers by sales range
- Returns defensive copies of stored records

---

## Project Structure

```text
src/
├── main/java/com/jamesstevens/customers/
│   ├── Customer.java
│   ├── CustomerRepository.java
│   ├── CustomerService.java
│   ├── Input.java
│   └── Main.java
└── test/java/com/jamesstevens/customers/
    ├── CustomerRepositoryTest.java
    └── InputTest.java
```

---

## Validation and Error Handling

The application handles invalid input through controlled validation and re-prompting.

Key rules include:

- Customer IDs must contain exactly five digits
- Customer IDs must be unique
- Leading-zero IDs such as `00012` are supported
- Sales values cannot be negative
- `NaN` and infinite numeric values are rejected
- Repository capacity cannot be less than one
- New customers cannot be added after repository capacity is reached
- Reversed sales-range bounds are automatically normalized
- Missing customer lookups are handled without terminating the program

---

## Build and Test

### Prerequisites

- Java 17+
- Maven 3.8+

### Run the Tests

```bash
mvn clean test
```

The project currently includes **16 automated JUnit tests** covering repository behavior and input validation.

### Run the Application

```bash
mvn -q exec:java
```

---

## Technology Stack

- **Java:** 17
- **Build Tool:** Maven
- **Testing:** JUnit 5
- **Architecture:** Layered object-oriented design
- **Interface:** Command line
- **Storage:** In-memory repository

---

## Engineering Concepts Demonstrated

This project demonstrates:

- Object-oriented Java design
- Separation of responsibilities
- Dependency composition through a minimal application entry point
- Repository-pattern fundamentals
- Immutable domain modeling
- Defensive copying
- Input validation and error handling
- Data-integrity rules
- Range-based query logic
- Unit testing with JUnit 5
- Maven-based build and execution

---

## Limitations

Customer records exist only for the lifetime of the running application. The project intentionally does not use database or filesystem persistence.

This keeps the project focused on Java fundamentals, application structure, validation, repository behavior, and automated testing.

---

## License

This project is licensed under the MIT License. See `LICENSE` for details.
