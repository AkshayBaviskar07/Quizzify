# Quizzify

### Introduction

>Quizzify is a Spring Boot-based microservices web application designed for creating, managing, and taking quizzes. It employs a modular architecture for scalability and maintainability.

### Microservices
- api-gateway: Acts as the single entry point for all API requests, routing them to the appropriate microservices.
- service-registry: Enables service discovery, allowing microservices to locate each other dynamically.
- question-service: Handles CRUD operations (create, read, update, delete) for questions, providing a RESTful API for managing question data.
- quiz-service: Manages quizzes, including creating, retrieving, updating, and taking quizzes. It utilizes the question-service to access questions.

### API Testing

The application's APIs are thoroughly tested using Postman, a popular API client. Postman allows for sending various HTTP requests (GET, POST, PUT, DELETE) with customizable headers, body payloads, and query parameters. This ensures that the APIs function as intended and handle diverse input scenarios.

### Unit Testing

Individual methods within the microservices are rigorously tested using JUnit, a widely-used Java unit testing framework. JUnit facilitates testing specific functionalities of your code in isolation.

### Mockito

Mocking dependencies within unit tests improves test reliability and maintainability. Mockito, a mocking framework, allows you to create mock objects that simulate the behavior of real dependencies without requiring their actual implementation. This isolates your code from external factors and focuses on testing its core logic.

### Prerequisites

- Java 8 or later
- Maven 
- Basic understanding of Spring Boot and microservices architecture

### Installation

**Clone this repository:**
```Bash
git clone https://github.com/AkshayBaviskar07/Quizzify
```
Use code with caution.

**Install dependencies:**
```Bash
mvn install
```
Use code with caution.

#### Running the Application

1. Start the service-registry (Eureka Server):
```Bash
mvn spring-boot:run -p service-registry
```
Use code with caution.

2. Start the api-gateway:
```Bash
mvn spring-boot:run -p api-gateway
```
Use code with caution.

3. Start the question-service:
```Bash
mvn spring-boot:run -p question-service
```
Use code with caution.

4. Start the quiz-service:
```Bash
mvn spring-boot:run -p quiz-service
```
Use code with caution.

Once all services are running, you can interact with the application through the API gateway using tools like Postman.

### Development

1. Clone the repository as mentioned above.
2. Install dependencies using `mvn install`.
3. Use an IDE like Spring Tool Suite or IntelliJ IDEA for development.
4. Start the services as shown in the "Running the Application" section.
5. Make code changes and test them using JUnit and Mockito.
6. Commit your changes and push them to your remote repository.

### API Documentation

***For documentation api please refer to following links:***

1. [Api-Gateway](https://web.postman.co/workspace/Quiz-Application-Api~a78e2a2e-2be5-413f-92a6-bbfd64df67bb/collection/31938115-b2679b6b-75ad-4937-8fde-fcd876438ef8?action=share&source=copy-link&creator=31938115) : 
Provides documentation for api-gateway.

2. [Question-Service](https://web.postman.co/workspace/Quiz-Application-Api~a78e2a2e-2be5-413f-92a6-bbfd64df67bb/collection/31938115-e4465fd1-e7a6-468c-88a5-12e116fa617e?action=share&source=copy-link&creator=31938115) : Provides documentation for question service.

3. [Quiz-Service](https://web.postman.co/workspace/Quiz-Application-Api~a78e2a2e-2be5-413f-92a6-bbfd64df67bb/collection/31938115-b9c13727-735c-4416-9e1c-92b5ecfae82d?action=share&source=copy-link&creator=31938115) : Provides documentation for quiz service.

### Health Checks:

Spring Actuator exposes health endpoints at the following paths:

- `/health`: Overall application health
- `/health/liveness`: Liveness probe
- `/health/readiness`: Readiness probe

These health checks can be used to determine whether the application is in a healthy state.

### Additional Notes:

- Consider using a database MySQL  for persistence in production.