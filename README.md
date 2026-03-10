# Quiz Platform Backend

This project is a backend service for a Quiz Platform built using **Spring Boot**, **Spring Security**, **JWT authentication**, and **PostgreSQL**.

The goal of the project is to implement a secure REST API where users can attempt quizzes, submit answers, and view leaderboard rankings. The backend follows a layered architecture with controllers, services, repositories, and security components.

---

## Tech Stack

- Java 21
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- PostgreSQL
- Spring Data JPA / Hibernate
- Maven

The frontend will be implemented later using React.

---

## Features Implemented

### Authentication & Security

- User registration with BCrypt password hashing
- Login API that generates a JWT token
- Stateless authentication using JWT
- Custom JWT authentication filter
- Protected API endpoints using Spring Security
- Foundation for role-based access (USER / ADMIN)

---

### Quiz Management

- Create quiz topics
- Create questions with multiple options
- Store correct answer index
- Fetch random questions from a topic

---

### Quiz Engine

- Users can fetch random quiz questions
- Submit answers for a quiz
- Backend validations implemented:
  - Check if question exists
  - Ensure question belongs to the selected topic
  - Prevent duplicate question submissions
  - Validate answer index

- Score and percentage calculation
- Quiz attempts stored in the database

---

### Leaderboard

- Track quiz attempts
- Calculate performance for each attempt
- Topic-based leaderboard showing top performers

---

## Authentication Flow

1. User registers → password stored using BCrypt  
2. User logs in → server generates a JWT token  
3. Client sends token in request header
4. JWT filter intercepts the request  
5. Token is validated and user is authenticated  
6. Spring Security allows access to protected APIs  

---

## Project Structure
Quiz-Platform
│
├── Backend
│ ├── controller # REST API controllers
│ ├── service # business logic
│ ├── repository # JPA repositories
│ ├── model # entity classes
│ ├── dto # request / response objects
│ └── security # JWT and security configuration
│
└── Frontend (coming later)


---

## Running the Project

### 1. Install PostgreSQL

Create a database named:quiz_platform

---

### 2. Configure database connection

Update `application.properties`:
spring.datasource.url=jdbc:postgresql://localhost:5432/quiz_platform
spring.datasource.username=postgres
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

### 3. Run the application
mvn spring-boot:run

The server will start on:


http://localhost:8080


---

## Example APIs

Register user


POST /api/auth/register


Login


POST /api/auth/login


Fetch quiz questions


GET /api/user/topics/{topicId}/questions


Submit quiz


POST /api/user/quiz/submit


View leaderboard


GET /api/user/topics/{topicId}/leaderboard


---

## Planned Improvements

- Admin APIs for managing topics and questions
- Restrict multiple attempts for a quiz
- Quiz attempt history
- React frontend
- Docker support
- CI/CD pipeline
- Caching leaderboard results

---

## Author

Suhas Gutta  
Software Engineer | Java Backend Developer
