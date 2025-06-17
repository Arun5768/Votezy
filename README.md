# Votezy: Secure Online Voting Management System

## Project Overview

**Votezy** is a full-stack web application designed to manage a secure, transparent, and one-time voting process. It simulates an election system where users can register as voters or candidates, cast their votes, and view the election results. The system enforces rules such as one vote per voter and tracks candidate vote counts dynamically.

## Technologies Used

- **Backend Framework**: Spring Boot  
- **Data Persistence**: Spring Data JPA with Hibernate ORM  
- **Database**: MySQL  
- **Validation**: Jakarta Bean Validation (`@Valid`, `@NotBlank`, `@Email`)  
- **REST API**: Spring MVC Controllers  
- **Frontend**: HTML, CSS, JavaScript  
- **Code Simplification**: Lombok for getters, setters, and boilerplate code  
- **Exception Handling**: Custom exceptions and global handler with `@ControllerAdvice`

## Core Functional Modules and Business Logic

### 1. Voter Management
- **Registration**: Voters can register using their name and email. Email validation is applied using `@Email`. Duplicate registration is prevented using `voterRepository.existsByEmail(...)`.
- **Business Rule**: A voter can vote only once, enforced by:
  - A boolean flag `hasVoted` in the `Voter` entity.
  - A unique `@OneToOne` relationship between `Voter` and `Vote` entities.
- **Update/Delete**: Voter details can be updated or deleted. If a voter is deleted after voting, their vote is also removed and the associated candidate’s `voteCount` is decremented.

### 2. Candidate Management
- **Registration**: A candidate registers with their name and party name. Each candidate starts with a vote count of zero.
- **Business Rule**: A candidate can receive many votes (one-to-many relationship). The `Candidate` entity maintains a list of associated votes.
- **Update/Delete**: Candidate details can be updated. When a candidate is deleted, all associated votes are also removed, and candidate-vote links are cleared to maintain data integrity.

### 3. Vote Casting
- A voter can cast a vote for one candidate only. Once voted, the `hasVoted` flag is set to `true`, and the vote is stored in the `Vote` entity.
- **Relationships**:
  - `Vote` has a `@OneToOne` relation with `Voter`.
  - `Vote` has a `@ManyToOne` relation with `Candidate`.
- The candidate's `voteCount` is incremented when a vote is cast and decremented when a vote is deleted.

### 4. Election Result Calculation
- The application generates election results via the `ElectionResult` entity, which includes:
  - The election name.
  - The winning candidate (`@OneToOne` relationship).
  - The total number of votes cast.
- Winner is determined by comparing `voteCount` values of all candidates.

## Entity Relationships and Database Design

### Voter and Vote
- **One-to-One**
- Each voter can vote once.
- Enforced using a unique constraint on `voter_id` in the `Vote` table.

### Candidate and Vote
- **One-to-Many**
- A candidate can receive many votes.
- Handled via `@OneToMany(mappedBy = "candidate")` in the `Candidate` entity.

### ElectionResult and Candidate
- **One-to-One**
- Each election result is linked to one winning candidate.

This structure ensures referential integrity and allows efficient queries for all election operations.

## Exception and Validation Handling

### Validation
- Spring Bean Validation is used to enforce input constraints such as:
  - Email format validation (`@Email`)
  - Mandatory fields (`@NotBlank`)

### Exception Handling
- `ResourceNotFoundException` handles missing entities.
- `DuplicateResourceException` handles duplicate email registration.
- A global exception handler using `@ControllerAdvice` centralizes error responses across the application.

## Application Architecture

The system follows a clean layered architecture:

### Controller Layer
- Handles RESTful HTTP requests.
- Maps endpoints to services.
- Validates input using `@Valid`.

### Service Layer
- Contains business logic.
- Manages entity validation, updates, and transformation.
- Throws domain-specific exceptions.

### Repository Layer
- Interfaces with the MySQL database.
- Uses Spring Data JPA to abstract common queries.

## Key Features

- One-time voting enforcement using entity relationships and flags.
- Candidate vote tracking and real-time result calculation.
- Clean separation of concerns between data access, business logic, and API layers.
- Secure and validated data handling.
- Fully RESTful API endpoints.

## Future Improvements

- Add frontend voting interface for real-time election simulation.
- Enable admin authentication for managing voters and candidates.
- Support for managing multiple elections.
- Integration with third-party analytics or dashboards.
- Add JWT or OAuth-based login for secure user access.
