# Quiz Microservice Application

This project demonstrates a basic **Quiz Application** designed initially as a monolithic application and then refactored into a **microservice architecture** using Spring Boot. The application is composed of two core microservices: **Quiz Microservice** and **Question Microservice**, which communicate via **Feign Client**. Additionally, the application leverages **Eureka Service Registry** for service discovery and an **API Gateway** to provide a single entry point for external requests.

## Architecture Overview

- **Monolithic Application**: Initially, the entire quiz application was a single monolithic service containing both quiz and question logic.
- **Microservices Refactoring**:
  - **Quiz Microservice**: Manages quiz metadata and handles quiz creation.
  - **Question Microservice**: Manages questions, including question retrieval and CRUD operations for question data.
- **Inter-Service Communication**: The two services communicate via **Feign Client** for REST API calls.
- **Service Discovery**: **Eureka Service Registry** is used to register both microservices, allowing dynamic service discovery.
- **API Gateway**: Serves as the single point of access for clients, routing all incoming requests to the respective services.

## Technologies Used

- **Spring Boot**: To build and structure the microservices.
- **Eureka Service Registry**: For service registration and discovery.
- **Feign Client**: For easy, declarative HTTP client communication between services.
- **Spring Cloud Gateway**: As an API Gateway for routing requests to the respective services.
- **MySql Databse**: For data persistence (in-memory database for demo purposes).
- **Maven**: For project build and dependency management.

## Features

1. **Quiz Microservice**:
   - Create a new quiz.
   - Communicates with the Question Microservice to fetch associated questions.

2. **Question Microservice**:
   - CRUD operations for quiz questions.
   - Provide question data for specific quizzes upon request by the Quiz Microservice.

3. **API Gateway**:
   - Provides a unified access point for both microservices.
  
4. **Service Discovery**:
   - **Eureka**: Automatically discovers and registers both microservices, making it easier to scale and manage the services.

