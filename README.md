A Spring Boot REST API for managing a library system with book borrowing and returning functionality, entity relationships, business logic, and proper exception handling.

This project was built to move beyond basic CRUD and understand real-world backend design, including transactional workflows and relational data modeling.

-> Features

Add new users

Add new books

View all books

Borrow a book

Return a borrowed book

View borrow history of a user

Automatic stock update on borrow/return

Global exception handling with proper HTTP status codes

-> Tech Stack

Java

Spring Boot

Spring Data JPA (Hibernate)

MySQL

Maven

-> Project Architecture

The application follows a clean layered architecture:

Controller → Service → Repository → Database

Layer Responsibilities

Controller: Handles HTTP requests and responses

Service: Contains business logic (borrow/return rules)

Repository: Handles database operations using JPA

Database: MySQL relational database

🗄 Database Design
Entities

User

Book

BorrowRecord

Relationships

One User → Many BorrowRecords

One Book → Many BorrowRecords

Relationships are implemented using @ManyToOne mappings from BorrowRecord.

-> API Endpoints

User APIs

Method	Endpoint	Description

POST	/users	Add a new user

Book APIs

Method	Endpoint	Description

POST	/books	Add a new book

GET	/books	Get all books

Borrow APIs

Method	Endpoint	Description

POST	/borrow/{userId}/{bookId}	Borrow a book

POST	/return/{borrowRecordId}	Return a borrowed book

GET	/borrow/history/{userId}	Get borrow history of a user

-> Exception Handling

Custom exceptions for business errors

Global exception handler using @RestControllerAdvice

Meaningful error messages with correct HTTP status codes

Example

404 NOT FOUND → User / Book / BorrowRecord not found

400 BAD REQUEST → Book not available, already returned

-> How to Run the Project

Clone the repository

Create a MySQL database:

CREATE DATABASE library_management;


Update database credentials in application.properties

Run the application

Test APIs using Postman or Thunder Client

-> Learning Outcomes

Designing real-world backend workflows

Implementing JPA entity relationships

Service-layer business logic

Exception handling best practices

RESTful API design

Database consistency and validation

End-to-end backend development
