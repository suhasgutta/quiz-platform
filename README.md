#  Quiz Platform

A full-stack Quiz Platform built using **Spring Boot**, **JWT Authentication**, and **PostgreSQL**.

This project demonstrates secure REST API development with role-based access control and stateless authentication.

---

## 🛠 Tech Stack

- Java 21
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- PostgreSQL
- Maven
- React (Frontend - in progress)

---

##  Features Implemented

- User Registration with BCrypt password encryption
- User Login with JWT token generation
- Stateless authentication using JWT
- Custom JWT Authentication Filter
- Role-based access (USER / ADMIN ready)
- Protected API endpoints
- PostgreSQL integration with JPA/Hibernate

---

##  Authentication Flow

1. User registers → Password stored securely (BCrypt)
2. User logs in → JWT token generated
3. Client sends token in `Authorization: Bearer <token>`
4. JWT Filter validates token
5. SecurityContext updated
6. Protected endpoints accessible

---

##  Project Structure


Quiz-Platform
├── Backend
│ ├── controller
│ ├── service
│ ├── repository
│ ├── security
│ └── model
└── Frontend (coming soon)


---

##  How to Run

1. Install PostgreSQL
2. Create database:

quiz_platform

3. Update `application.properties`
4. Run:

mvn spring-boot:run


---

##  Future Enhancements

- Admin module for managing topics & questions
- Quiz attempt history
- Leaderboard system
- React frontend integration
- Docker containerization
- CI/CD pipeline

---

##  Author

Suhas Gutta