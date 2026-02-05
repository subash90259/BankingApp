# 🏦 Banking Application – README

## 📌 Project Overview

This project is a **Java-based Banking Application** developed using **Servlets without JSP**.  
It follows a backend API-driven architecture with built-in security, logging, and database migration support.

The application demonstrates:

- EMI Calculation using Servlets  
- User Registration and Login  
- JWT Token based Authentication  
- Logging mechanism  
- Database version control using Liquibase  

This system is designed as a **modular, secure, and scalable backend application**.

## 🚀 Features Implemented

### 1. EMI Calculation Module

- EMI is calculated based on user input:
  - Loan Amount  
  - Loan Tenure (Years)  
  - Annual Interest Rate  

- Implemented using Servlet:
  - EmiCalculationServlet

- Uses the standard EMI formula:


EMI = [P × r × (1+r)^n] / [(1+r)^n – 1]


- Output is returned as a **JSON response**.

---

### 2. User Authentication Module

#### a) User Registration

- Users can register with:
  - Username  
  - Password  
  - Email  
  - Basic profile details  

- User data is stored securely in the database.

---

#### b) User Login

- Users can log in using valid credentials.
- Password validation is performed.
- On successful login, a **JWT Token is generated**.

---

#### c) JWT Token Security

- JSON Web Token (JWT) is used for authentication.
- After login:
  - Token is issued  
  - Token must be passed in the header for protected APIs  

- JWT is used to secure all banking operations.

---

### 3. Logging Mechanism

- Logging is implemented using **SLF4J / Log4j**.

Logs are maintained for:

- User login attempts  
- EMI calculations  
- API requests and responses  
- Errors and exceptions  

---

### 4. Database Management with Liquibase

- Database schema is managed using **Liquibase**.

Key Features:

- Automatic table creation  
- Versioned database changes  
- Easy migration support  
- Change tracking  

---

## 🛠 Technologies Used

| Layer        | Technology        |
|--------------|------------------|
| Backend      | Java Servlets     |
| Security     | JWT Token         |
| Database     | MySQL 
| Build Tool   | Maven             |
| DB Migration | Liquibase         |
| Logging      | SLF4J / Log4j    |
| API Format   | JSON              |

---

## 📂 Main Components

### Servlets

- RegisterServlet  
- LoginServlet 
- EmiCalculationServlet  
- AuthFilter  

### Utilities

- JWTUtil  
- DBConnection  
- LoggerUtil  

### Configuration Files

- web.xml  
- Liquibase changelog  
- application properties 

## 📡 Available APIs

### User APIs

- POST /register  
- POST /login 

### Banking APIs

- POST /calculateEMI  
- GET /accountDetails  


## 🔐 Security

- Passwords stored securely  
- JWT authentication implemented  
- Filter-based request validation  
- Protected API endpoints  

---

## 📋 Future Enhancements

Planned upgrades for the system:

- Transaction module  
- Account management  
- Role-based access control  
- API rate limiting  
- Audit logs  
- Admin dashboard  

---

## 👨‍💻 Developed Using

- Core Java  
- Servlets  
- JWT  
- Liquibase  
- Logging Framework  




