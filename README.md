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
  ```

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


